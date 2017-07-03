import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class TryOutIdeas {

    private static final String API_KEY = "rJh6i4JLqs5HqTJcuDgA0qojWq1UY45Oq5qDS3sF";

    // we will rely on the JSON parser class for this
    public static void main (String args[]) {
        int totalCountTracks = 64;
        // set limit above actual count of tracks on server and see what it does
        String address = "https://api.soundcloud.com/tracks/18a835738/download";
        String response = "";
        try {
            response = Downloader.download(address);
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
        System.out.println(response);
    }
}