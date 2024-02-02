package org.abos.twi.knowledge.wiki;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.abos.common.CollectionUtil;
import org.abos.common.LogUtil;
import org.abos.twi.knowledge.core.publication.Book;
import org.abos.twi.knowledge.core.publication.Chapter;
import org.abos.twi.knowledge.core.Character;
import org.abos.twi.knowledge.core.publication.Volume;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public final class WikiHelper {

    public static final String WIKI_URL = "https://wiki.wanderinginn.com/";

    private static final String API_URL = WIKI_URL + "api.php?";

    private static final String CATEGORY_PREFIX = "Category:";

    private static final String CHAPTER_LIST = "Chapter List";

    private static final String VOLUME = "Volume";

    private static final String BOOK = "Book";

    private  static final String CONTINUE = "continue";

    private static final Logger LOGGER = LogManager.getLogger(WikiHelper.class);

    private static final String ERROR_FETCH = "Error occurred fetching ";

    private static final DateTimeFormatter WIKI_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMMM d, uuuu", Locale.US);

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final Set<String> BOOK_FREE_VOLUMES = Set.of("Volume 1", "Volume 7", "Volume 8", "Volume 9", "Volume 10");

    private final String moduleRef;

    private final List<Volume> cachedVolumes = new LinkedList<>();

    private final Map<Volume, String> cachedVolumePages = new TreeMap<>();

    private final List<Book> cachedBooks = new LinkedList<>();

    private final Map<Book, String> cachedBookPages = new TreeMap<>();

    public WikiHelper() throws IOException {
        moduleRef = fetchPage("Module:Ref");
    }

    public static String sanitizePageName(final String name) {
        if (name == null) {
            return null;
        }
        return name.replace(' ', '_')
                .replace("&", "%26");
    }

    private static int getTemplateValueEndIndex(final String postKeyString, final boolean startsWithTemplate) {
        int endIndex = postKeyString.indexOf("\\n");
        int bracketIndex = postKeyString.indexOf("}}");
        if (bracketIndex >= 0 && startsWithTemplate) {
            final int oldBracketIndex = bracketIndex;
            bracketIndex = postKeyString.substring(bracketIndex+2).indexOf("}}");
            if (bracketIndex >= 0) {
                bracketIndex += oldBracketIndex + 2;
            }
        }
        if (bracketIndex >= 0 && (endIndex == -1 || bracketIndex < endIndex)) {
            endIndex = bracketIndex;
        }
        int pipeIndex = postKeyString.indexOf('|');
        if (pipeIndex >= 0 && startsWithTemplate) {
            final int oldPipeIndex = pipeIndex;
            pipeIndex = postKeyString.substring(pipeIndex+1).indexOf('|');
            if (pipeIndex >= 0) {
                pipeIndex += oldPipeIndex + 1;
            }
        }
        if (pipeIndex >= 0 && (endIndex == -1 || pipeIndex < endIndex)) {
            endIndex = pipeIndex;
        }
        if (endIndex == -1) {
            endIndex = postKeyString.length();
        }
        return endIndex;
    }

    /**
     * Extracts the value of the given key from a template.
     * @param content should start with the template start, or immediately thereafter
     * @param key the key to fetch the value from
     * @return {@code null} if the key couldn't be found
     */
    private static String extractTemplateValue(final String content, final String key) {
        int keyIndex = content.indexOf(key);
        if (keyIndex != -1) {
            keyIndex += key.length();
            while (keyIndex < content.length() && content.charAt(keyIndex) != '=') {
                keyIndex++;
            }
            keyIndex++;
            // in case there is no '=' or nothing after it
            if (keyIndex >= content.length()) {
                return null;
            }
            final String postKeyString = content.substring(keyIndex);
            int endIndex = getTemplateValueEndIndex(postKeyString, postKeyString.startsWith("{{"));
            return postKeyString.substring(0, endIndex).trim();
        }
        return null;
    }

    /**
     * Executes the given query against the Wiki.
     * @param query the query to execute against the Wiki
     * @return the main body of the query response
     * @throws IOException If an I/O error occurs.
     */
    private static String getQueryResponse(final String query) throws IOException {
        final URL url = new URL(API_URL + query);
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("User-Agent", WikiHelper.class.getName() + ", GitHub: https://github.com/Ayutac");
        connection.setRequestMethod("GET");
        final String response;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            final StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
            response = responseBuilder.toString();
        }
        return response;
    }

    private static String simplifyChapterName(final String name) {
        return name.toUpperCase()
                .replaceAll("\\s+", "")
                .replace('-','–')
                .replace('‒','–')
                .replace('—','–')
                .replace("–", "")
                .replace("(", "")
                .replace(")", "")
                .replace("PT.", "PT")
                .replace("CHAPTER", "")
                .replace("INTERLUDE", "")
                .replace("MINISTORIES", "");
    }

    private String resolveRef(final String chapterName) throws IllegalStateException {
        // follows the implementation on https://wiki.wanderinginn.com/Module:Ref
        final String simplifiedChapterName = simplifyChapterName(chapterName);
        final int lineIndex = moduleRef.indexOf(simplifiedChapterName);
        if (lineIndex == -1) {
            throw new IllegalStateException("Unknown sanitized ref " + simplifiedChapterName + " from " + chapterName);
        }
        final String line = moduleRef.substring(lineIndex, lineIndex + moduleRef.substring(lineIndex).indexOf("\n"));
        int linkIndex = line.indexOf("[\"link\"]");
        if (linkIndex == -1) {
            throw new IllegalStateException("Missing [\"link\"] for " + simplifiedChapterName + " from " + chapterName);
        }
        while (linkIndex < line.length() && line.charAt(linkIndex) != '=') {
            linkIndex++;
        }
        linkIndex++;
        if (linkIndex >= line.length()) {
            throw new IllegalStateException("Missing '=' after [\"link\"] for " +  simplifiedChapterName + " from " + chapterName);
        }
        final int bracketIndex = line.lastIndexOf('}');
        if (bracketIndex < linkIndex) {
            throw new IllegalStateException("Missing closing '}' after [\"link\"] for " +  simplifiedChapterName + " from " + chapterName);
        }
        final String quotedLink = line.substring(linkIndex, bracketIndex).trim();
        if (quotedLink.startsWith("\"") && quotedLink.endsWith("\"")) {
            return quotedLink.substring(2, quotedLink.length()-2);
        }
        return quotedLink;
    }

    public static String fetchPage(final String name) throws IOException {
        final String response = getQueryResponse("action=parse&prop=wikitext&format=json&page=" + sanitizePageName(name));
        final ObjectNode responseNode = MAPPER.readValue(response, ObjectNode.class);
        return responseNode.get("parse").get("wikitext").get("*").textValue();
    }

    public static List<String> fetchCategory(final String name, boolean pages, boolean subCategories) throws IOException {
        final List<String> result = new LinkedList<>();
        if (!pages && !subCategories) {
            return result;
        }
        final String baseQuery = "action=query&format=json&list=categorymembers&cmlimit=500&cmtitle=Category:" + sanitizePageName(name);
        String continueSuffix = "";
        while (continueSuffix != null) {
            final String response = getQueryResponse(baseQuery + continueSuffix);
            final ObjectNode responseNode = MAPPER.readValue(response, ObjectNode.class);
            final ArrayNode content = (ArrayNode)responseNode.get("query").get("categorymembers");
            String title;
            for (JsonNode node : content) {
                if (node instanceof ObjectNode entry) {
                    title = entry.get("title").textValue();
                    if ((title.startsWith(CATEGORY_PREFIX) && subCategories) || (!title.startsWith(CATEGORY_PREFIX) && pages)) {
                        result.add(title);
                    }
                }
            }
            final JsonNode continueNode = responseNode.get(CONTINUE);
            // yes, getting continue two times in a row is correct
            if (continueNode != null && continueNode.get(CONTINUE).textValue().contains("||")) {
                continueSuffix = "&cm" + CONTINUE + "=" + continueNode.get("cm" + CONTINUE).textValue();
            }
            else {
                continueSuffix = null;
            }
        }
        return result;
    }

    public List<Volume> fetchVolumes() throws IOException {
        LOGGER.info("Fetching volumes from Wiki...");
        final Instant start = Instant.now();
        final List<String> volTitles = fetchCategory("Volumes", true, false);
        volTitles.remove(CHAPTER_LIST);
        final List<Volume> result = new LinkedList<>();
        for (String title : volTitles) {
            result.add(new Volume(title, WIKI_URL + sanitizePageName(title)));
        }
        final Duration time = Duration.between(start, Instant.now());
        LOGGER.info(LogUtil.LOG_TIME_MSG, "Fetching volumes from Wiki", time.toMinutes(), time.toSecondsPart());
        Collections.sort(result);
        cachedVolumes.clear();
        cachedVolumes.addAll(result);
        return result;
    }

    public Book fetchBook(final String title) throws IOException {
        final String content = fetchPage(title);
        Integer volOrd = null;
        Volume volume = null;
        String publicationLink = null;
        LocalDate publicationDate = null;
        String audibleLink = null;
        LocalDate audibleDate = null;
        String name = title.replace(VOLUME, BOOK).replace(" (Archived)", "");
        if (name.startsWith(BOOK)) {
            // get the order out of the chapter list table
            final int chapterListIndex = content.indexOf("{{" + CHAPTER_LIST);
            if (chapterListIndex != -1) {
                final String table = content.substring(chapterListIndex + CHAPTER_LIST.length()+2, chapterListIndex + content.substring(chapterListIndex).indexOf("}}"));
                final int colIndex = table.lastIndexOf('|');
                if (colIndex != 0) {
                    final String volOrdStr = table.substring(colIndex+1).trim();
                    try {
                        volOrd = Integer.parseInt(volOrdStr);
                    }
                    catch (NumberFormatException ex) {
                        LOGGER.warn("Unknown volume number {}!", volOrdStr);
                    }
                }
            }
            // get the publication data
            final int publicationsIndex = content.indexOf("{{BookPublications");
            if (publicationsIndex != -1) {
                final String publications = content.substring(publicationsIndex, publicationsIndex + content.substring(publicationsIndex).indexOf("}}"));
                final String bookName = extractTemplateValue(publications, "bookName");
                if (bookName != null) {
                    name += ": " + bookName;
                }
                String dateStr = extractTemplateValue(publications, "bookPublishDate");
                if (dateStr != null && !dateStr.isEmpty()) {
                    try {
                        publicationDate = WIKI_DATE_FORMATTER.parse(dateStr, LocalDate::from);
                    } catch (DateTimeParseException ex) {
                        LOGGER.warn("Unknown date " + dateStr);
                    }
                }
                publicationLink = extractTemplateValue(publications, "bookAmazonLink");
                dateStr = extractTemplateValue(publications, "audioBookPublishDate");
                if (dateStr == null || dateStr.isEmpty()) {
                    audibleDate = publicationDate;
                }
                else {
                    try {
                        audibleDate = WIKI_DATE_FORMATTER.parse(dateStr, LocalDate::from);
                    }
                    catch (DateTimeParseException ex) {
                        LOGGER.warn("Unknown date " + dateStr);
                    }
                }
                audibleLink = extractTemplateValue(publications, "audioBookLink");
            }
        }
        // gravesong and comic
        else if (title.startsWith("Gravesong")) {
            publicationDate = LocalDate.of(2022, Month.DECEMBER, 1);
            publicationLink = "https://yonder.onelink.me/6vpb/gravesong";
        }
        else if (title.contains("The Last Tide")) {
            publicationDate = LocalDate.of(2020, Month.JANUARY, 26);
            publicationLink = "https://www.cloudscapecomics.com/product/the-last-tide-soft-cover/";
        }
        // get the volume
        if (title.startsWith(VOLUME)) {
            volume = CollectionUtil.getByName(cachedVolumes, title);
        }
        // ... out of the intro
        else {
            final int introIndex = content.indexOf("{{BookIntro");
            if (introIndex != -1) {
                final String bookIntro = content.substring(introIndex, introIndex + content.substring(introIndex).indexOf("}}"));
                final String volumeStr = extractTemplateValue(bookIntro, "volumeNumber");
                if (volumeStr != null) {
                    volume = CollectionUtil.getByName(cachedVolumes, VOLUME + " " + volumeStr);
                }
            }
        }
        final Book result = new Book(name, volOrd, volume, WIKI_URL + sanitizePageName(title), publicationLink, publicationDate, audibleLink, audibleDate);
        cachedBookPages.put(result, content);
        return result;
    }

    public List<Book> fetchBooks() throws IOException {
        LOGGER.info("Fetching books from Wiki...");
        final Instant start = Instant.now();
        final List<String> bookTitles = fetchCategory("EBooks", true, false);
        bookTitles.remove(CHAPTER_LIST);
        bookTitles.remove("Ebook");
        final List<Book> result = new LinkedList<>();
        for (String title : bookTitles) {
            try {
                result.add(fetchBook(title));
            }
            catch (IOException ex) {
                LOGGER.error(ERROR_FETCH + title, ex);
            }
        }
        final Duration time = Duration.between(start, Instant.now());
        LOGGER.info(LogUtil.LOG_TIME_MSG, "Fetching books from Wiki", time.toMinutes(), time.toSecondsPart());
        Collections.sort(result);
        cachedBooks.clear();
        cachedBooks.addAll(result);
        return result;
    }

    private record Ordering(Integer volume, Integer book) {
        public static final Ordering EMPTY = new Ordering(null, null);
    }

    /**
     * Calculates the ordering of the given chapter within its volume and book.
     * @param chapterTitle the chapter page name
     * @param volume the volume the chapter belongs to
     * @return the ordering within the volume and book, not {@code null} but might contain it
     * @throws IOException If an I/O error occurs.
     */
    private Ordering calcOrdering(final String chapterTitle, final Volume volume) throws IOException {
        if (volume == null) {
            return Ordering.EMPTY;
        }
        if (!cachedVolumePages.containsKey(volume)) {
            cachedVolumePages.put(volume, fetchPage(volume.name()));
        }
        final String volContent = cachedVolumePages.get(volume);
        final int chapterListIndex = volContent.indexOf("{{" + CHAPTER_LIST);
        Integer volOrd = null;
        Integer bookOrd = null;
        if (chapterListIndex != -1) {
            String table = volContent.substring(chapterListIndex + CHAPTER_LIST.length()+3, chapterListIndex + volContent.substring(chapterListIndex).lastIndexOf("}}"));
            final String[] tableLines = table.split("\n");
            int volOrdIndex = 0;
            int bookOrdIndex = 0;
            int bookNumber = 1;
            for (String line : tableLines) {
                volOrdIndex++;
                if (!line.endsWith(" " + bookNumber + " }}")) {
                    bookNumber++;
                    bookOrdIndex = 1;
                }
                else {
                    bookOrdIndex++;
                }
                if (line.contains(chapterTitle)) {
                    volOrd = volOrdIndex;
                    if (!BOOK_FREE_VOLUMES.contains(volume.name())) {
                        bookOrd = bookOrdIndex;
                    }
                    break;
                }
            }
        }
        return new Ordering(volOrd, bookOrd);
    }

    /**
     * Fetches the chapter page and parses it into a {@link Chapter}.
     * @param title the chapter page name
     * @return the {@link Chapter} behind the chapter page, not {@code null}
     * @throws IllegalArgumentException If some illegal argument exception occurs, e.g. if the word count cannot be parsed.
     * @throws IllegalStateException If some information is missing from the response.
     * @throws DateTimeParseException If the release date couldn't be parsed.
     * @throws IOException If an I/O error occurs.
     */
    public Chapter fetchChapter(final String title) throws IllegalArgumentException, IllegalStateException, DateTimeParseException, IOException {
        final String content = fetchPage(title);
        final int infoboxIndex = content.indexOf("{{Infobox_episode");
        if (infoboxIndex == -1) {
            throw new IllegalStateException("Infobox missing for " + title + "!");
        }
        final String infobox = content.substring(infoboxIndex);
        // get release date
        final String dateStr = extractTemplateValue(infobox, "published");
        if (dateStr == null || dateStr.isEmpty()) {
            throw new IllegalStateException("Release date missing for " + title + "!");
        }
        final LocalDate date = WIKI_DATE_FORMATTER.parse(dateStr, LocalDate::from);
        // get word count
        String wordStr = extractTemplateValue(infobox, "wordcount");
        if (wordStr == null || wordStr.isEmpty()) {
            throw new IllegalStateException("Word count missing for " + title + "!");
        }
        final int wordStrSpace = wordStr.indexOf(' ');
        if (wordStrSpace != -1) {
            wordStr = wordStr.substring(0, wordStrSpace);
        }
        final int wordCount;
        if (title.equals("Mini Stories – Crabs and Drinks")) {
            wordCount = 5248;
        }
        else {
            wordCount = Integer.parseInt(wordStr.replace(",", ""));
        }
        // get link
        final String linkRaw = extractTemplateValue(infobox, "link");
        final String link;
        if (linkRaw != null && linkRaw.startsWith("[")) {
            link = linkRaw.substring(1, linkRaw.indexOf(' '));
        }
        else if (linkRaw != null && linkRaw.startsWith("{{chl")) {
            final String linkRef = linkRaw.substring(linkRaw.indexOf('|')+1, linkRaw.indexOf("}}")).trim();
            link = resolveRef(linkRef);
        }
        else {
            throw new IllegalStateException("Unknown link type: " + linkRaw);
        }
        // get volume
        Volume volume = null;
        for (Volume checkVol : cachedVolumes) {
            if (content.contains("[[" + CATEGORY_PREFIX + checkVol.name() + "]]")) {
                volume = checkVol;
                break;
            }
        }
        // get book
        Book book = null;
        if (volume != null && volume.name().equals("Volume 1 (Archived)")) {
            for (Book checkBook : cachedBooks) {
                if (checkBook.getTitleFreeName().equals(Book.BOOK1)) {
                    book = checkBook;
                    break;
                }
            }
        }
        else if (volume != null && volume.name().equals("Volume 2")) {
            for (Book checkBook : cachedBooks) {
                if (checkBook.getTitleFreeName().equals(Book.BOOK2)) {
                    book = checkBook;
                    break;
                }
            }
        }
        else {
            for (Book checkBook : cachedBooks) {
                if (content.contains("[[" + CATEGORY_PREFIX + checkBook.getTitleFreeName() + "]]")) {
                    book = checkBook;
                    break;
                }
            }
        }
        // get volume and book order
        final Ordering ordering = calcOrdering(title, volume);
        return new Chapter(title, ordering.volume(), ordering.book(), date, wordCount, book, volume, link, WIKI_URL + sanitizePageName(title));
    }

    public List<Chapter> fetchChapters() throws IOException {
        LOGGER.info("Fetching chapters from Wiki...");
        final Instant start = Instant.now();
        final List<String> chapterTitles = fetchCategory("Chapters", true, false);
        chapterTitles.remove(CHAPTER_LIST);
        chapterTitles.remove("Chapter Overview");
        chapterTitles.remove("Chapter Timeline");
        chapterTitles.remove("Lettered Chapters");
        chapterTitles.remove("Chronology Fanworks");
        chapterTitles.remove("Chatroom Chapters");
        chapterTitles.remove("Interlude");
        chapterTitles.remove("Chapter 2.06 (April Fool)");
        chapterTitles.remove("Chapter 5.54 (Non-Canon)");
        chapterTitles.remove("Erin Meets Minecraft (Non-Canon Short Story)");
        chapterTitles.remove("Chapter 9.27.5 LS");
        chapterTitles.remove("The Depthless Doctor");
        chapterTitles.addAll(fetchCategory("Volume 1 (Archived)", true, false));
        final List <Chapter> result = new LinkedList<>();
        for (String title: chapterTitles) {
            try {
                result.add(fetchChapter(title));
            }
            catch (RuntimeException | IOException ex) {
                LOGGER.error(ERROR_FETCH + title, ex);
            }
        }
        final Duration time = Duration.between(start, Instant.now());
        LOGGER.info(LogUtil.LOG_TIME_MSG, "Fetching chapters from Wiki", time.toMinutes(), time.toSecondsPart());
        Collections.sort(result);
        return result;
    }

    public Character fetchCharacter(final String name) {
        return new Character(WIKI_URL + sanitizePageName(name));
    }

    public List<Character> fetchCharacters() throws IOException {
        LOGGER.info("Fetching characters from Wiki...");
        final Instant start = Instant.now();
        final List<String> characterNames = fetchCategory("Characters", true, false);
        characterNames.remove("Cargo Character Test");
        characterNames.remove("Template:Character Preload");
        characterNames.remove("The Wandering Inn Wiki:New Character Layout");
        final List <Character> result = new LinkedList<>();
        for (String name: characterNames) {
            try {
                result.add(fetchCharacter(name));
            }
            catch (RuntimeException ex) {
                LOGGER.error(ERROR_FETCH + name, ex);
            }
        }
        final Duration time = Duration.between(start, Instant.now());
        LOGGER.info(LogUtil.LOG_TIME_MSG, "Fetching characters from Wiki", time.toMinutes(), time.toSecondsPart());
        Collections.sort(result, Comparator.comparing(character -> character.wikiLink()));
        return result;

    }

}
