// For testing of ideas and concepts

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class TryOutIdeas {

    private static final String API_KEY = "rJh6i4JLqs5HqTJcuDgA0qojWq1UY45Oq5qDS3sF";

    // we will rely on the JSON parser class for this
    public static void main (String args[]) throws Exception {
        URL url = new URL("https://api.nasa.gov/planetary/sounds?api_key=rJh6i4JLqs5HqTJcuDgA0qojWq1UY45Oq5qDS3sF");
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestMethod("GET");
        BufferedReader br = null;
        StringBuilder builder = new StringBuilder();
        String line;

        br = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));

        while ((line = br.readLine()) != null) {
            builder.append(line);
        }

        System.out.println(httpCon.getResponseCode());
        // throws  exception
        System.out.println(builder.toString());
    }
}