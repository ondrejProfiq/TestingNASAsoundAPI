/**
 * Created by ondra
 */
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class Downloader {
    public static final String API_KEY = "rJh6i4JLqs5HqTJcuDgA0qojWq1UY45Oq5qDS3sF";
    public static final String API_KEY_2 = "4zEoms78OihAkm93u2cFpTfwwcXsdYHZq4akm0Tw";
    public enum Method {
        GET("GET"),
        PUT("PUT"),
        POST("POST"),
        DELETE("DELETE");

        private final String name;

        Method (String name) {
            this.name = name;
        }

        String getName() {
            return this.name;
        }
    }

    public static String download (String address) throws IOException {
        StringBuilder builder = new StringBuilder();
        InputStream is = null;
        BufferedReader br;
        String line;

        URL url = new URL(address);
        is = url.openStream();  // throws an IOException
        br = new BufferedReader(new InputStreamReader(is));

        while ((line = br.readLine()) != null) {
            builder.append(line);
        }

        try {
            if (is != null) is.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return builder.toString();
    }

    public static String download ( String address, String method ) throws IOException {
        URL url = new URL(address);
        BufferedReader br;
        StringBuilder builder = new StringBuilder();
        String line;

        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestMethod(method);

        br = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));

        while ((line = br.readLine()) != null) {
            builder.append(line);
        }

        return builder.toString();
    }

    public static Map<String, List<String>> getHeader (String address) throws IOException{
        URL url = new URL(address);
        URLConnection con = url.openConnection();  // throws an IOException
        return con.getHeaderFields();
    }
}