package org.abos.twi.knowledge.wiki;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.abos.common.CollectionUtil;
import org.abos.twi.knowledge.core.Book;
import org.abos.twi.knowledge.core.Volume;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public final class WikiHelper {

    private static final String WIKI_URL = "https://wiki.wanderinginn.com/";

    private static final String API_URL = WIKI_URL + "api.php?";

    private static final String CATEGORY_PREFIX = "Category:";

    private static final String CHAPTER_LIST = "Chapter List";

    private static final String VOLUME = "Volume";

    private static final String BOOK = "Book";

    private static final Logger LOGGER = LogManager.getLogger(WikiHelper.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private WikiHelper() {
        /* No instantiation. */
    }

    private static String sanitizePageName(final String name) {
        if (name == null) {
            return null;
        }
        return name.replace(' ', '_')
                .replace("&", "%26");
    }

    private static String getQueryResponse(final String query) throws IOException {
        final URL url = new URL(API_URL + query);
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
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

    public static String fetchPage(final String name) throws IOException {
        return getQueryResponse("action=parse&prop=wikitext&page=" + sanitizePageName(name));
    }

    public static List<String> fetchCategory(final String name, boolean pages, boolean subCategories) throws IOException {
        final List<String> result = new LinkedList<>();
        if (!pages && !subCategories) {
            return result;
        }
        final String response = getQueryResponse("action=query&format=json&list=categorymembers&cmlimit=100&cmtitle=Category:" + sanitizePageName(name));
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
        return result;
    }

    public static List<Volume> fetchVolumes() throws IOException {
        final List<String> volTitles = fetchCategory("Volumes", true, false);
        volTitles.remove(CHAPTER_LIST);
        final List<Volume> result = new LinkedList<>();
        for (String title : volTitles) {
            result.add(new Volume(title, WIKI_URL + sanitizePageName(title)));
        }
        Collections.sort(result);
        return result;
    }

    public static Book fetchBook(final List<Volume> volumes, final String title) throws IOException {
        final String content = fetchPage(title);
        Integer volOrd = null;
        Volume volume = null;
        if (title.startsWith(VOLUME)) {
            volOrd = 1;
            volume = CollectionUtil.getByName(volumes, title);
        }
        else if (title.startsWith(BOOK)) {
            // get the order out of the chapter list table
            final int chapterListIndex = content.indexOf("{{" + CHAPTER_LIST);
            if (chapterListIndex != -1) {
                final String tableLine = content.substring(chapterListIndex + CHAPTER_LIST.length()+2, chapterListIndex + content.substring(chapterListIndex).indexOf("}}"));
                final int colIndex = tableLine.lastIndexOf('|');
                if (colIndex != 0) {
                    final String volOrdStr = tableLine.substring(colIndex+1).trim();
                    try {
                        volOrd = Integer.parseInt(volOrdStr);
                    }
                    catch (NumberFormatException ex) {
                        LOGGER.warn("Unknown volume number {}!", volOrdStr);
                    }
                }
            }
            // get the volume out of the first paragraph
            // TODO
        }
        String name = title.replace(VOLUME, BOOK).replace(" (Archived)", "");

        return null;
    }

    public static List<Book> fetchBooks(final List<Volume> volumes) throws IOException {
        final List<String> bookTitles = fetchCategory("EBooks", true, false);
        bookTitles.remove(CHAPTER_LIST);
        bookTitles.remove("Ebook");
        final List<Book> result = new LinkedList<>();
        for (String title : bookTitles) {
            result.add(fetchBook(volumes, title));
        }
        //Collections.sort(result);
        return result;
    }

}
