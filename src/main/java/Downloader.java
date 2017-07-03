/**
 * Created by ondra on 29.6.17.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class Downloader {
    public static final String API_KEY = "rJh6i4JLqs5HqTJcuDgA0qojWq1UY45Oq5qDS3sF";
    public static final String API_KEY_2 = "4zEoms78OihAkm93u2cFpTfwwcXsdYHZq4akm0Tw";

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

    public static Map<String, List<String>> getHeader (String address) throws IOException{
        InputStream is = null;
        BufferedReader br;
        String line;

        URL url = new URL(address);
        URLConnection con = url.openConnection();  // throws an IOException
        Map<String, List<String>> headers = con.getHeaderFields();
        return headers;

    }
}