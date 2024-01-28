package org.abos.twi.knowledge.wiki;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.abos.twi.knowledge.core.Volume;

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

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private WikiHelper() {
        /* No instantiation. */
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

    private static String sanitizePageName(final String name) {
        if (name == null) {
            return null;
        }
        return name.replace(' ', '_')
                .replace("&", "%26");
    }

    public static List<String> fetchCategory(final String name, boolean pages, boolean subCategories) throws IOException {
        if (!pages && !subCategories) {
            return List.of();
        }
        final String response = getQueryResponse("action=query&format=json&list=categorymembers&cmlimit=100&cmtitle=Category:" + sanitizePageName(name));
        final ObjectNode responseNode = MAPPER.readValue(response, ObjectNode.class);
        final ArrayNode content = (ArrayNode)responseNode.get("query").get("categorymembers");
        final List<String> result = new LinkedList<>();
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

}
