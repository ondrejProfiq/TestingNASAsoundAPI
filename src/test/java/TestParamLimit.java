/**
 * Created by ondra
 */
import org.json.*;
import org.testng.annotations.Test;


public class TestParamLimit {

    @Test
    public void testResponseLengthNoParam () throws Exception {
        int expectedLength = 10;
        // no limit parameter should default to 10 records
        String address = "https://api.nasa.gov/planetary/sounds?api_key=" + Downloader.API_KEY;
        String response = "";

        response = Downloader.download(address);
        JSONObject obj = new JSONObject(response);
        JSONArray arr = obj.getJSONArray("results");
        assert arr.length() == expectedLength;
    }

    @Test
    public void testResponseLength20 () throws Exception {
        int limit = 20;
        // limit response length to 20;
        String address = "https://api.nasa.gov/planetary/sounds?limit=" + limit + "&api_key=" + Downloader.API_KEY;
        String response = "";

        response = Downloader.download(address);
        JSONObject obj = new JSONObject(response);
        JSONArray arr = obj.getJSONArray("results");
        assert arr.length() == limit;
    }

    @Test
    public void testResponseLengthMax () throws Exception {
        int totalCountTracks = 64;
        // set limit above actual count of tracks on server and see what it does
        String address = "https://api.nasa.gov/planetary/sounds?limit=99999&api_key=" + Downloader.API_KEY;
        String response = "";

        response = Downloader.download(address);
        JSONObject obj = new JSONObject(response);
        JSONArray arr = obj.getJSONArray("results");
        assert arr.length() == totalCountTracks;
    }

    @Test
    public void testNegativeValues () throws Exception {
        int limit = -1;
        int defaultLimit = 10;
        // set limit to negative value, see how that is handled
        String address = "https://api.nasa.gov/planetary/sounds?limit=" + limit + "&api_key=" + Downloader.API_KEY;
        String response = "";

        response = Downloader.download(address);
        JSONObject obj = new JSONObject(response);
        JSONArray arr = obj.getJSONArray("results");
        // expect fallback to default limit value
        assert arr.length() == defaultLimit;
    }

    @Test
    public void testZero () throws Exception {
        int limit = 0;
        // set limit to zero, see if length is zero
        String address = "https://api.nasa.gov/planetary/sounds?limit=" + limit + "&api_key=" + Downloader.API_KEY;
        String response = "";
        response = Downloader.download(address);
        JSONObject obj = new JSONObject(response);
        JSONArray arr = obj.getJSONArray("results");
        assert arr.length() == limit;
    }

    @Test
    public void testString () throws Exception {
        String limit = "ten";
        int defaultLimit = 10;
        // set limit to some not parsable string and see how that is handled
        String address = "https://api.nasa.gov/planetary/sounds?limit=" + limit + "&api_key=" + Downloader.API_KEY;
        String response = "";

        response = Downloader.download(address);
        JSONObject obj = new JSONObject(response);
        JSONArray arr = obj.getJSONArray("results");
        // expect fallback to default limit value
        assert arr.length() == defaultLimit;
    }
}
