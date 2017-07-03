import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Test;


/**
 * Created by ondra
 */
public class TestParamQ {

    // Should get all tracks unfiltered
    @Test
    public void testWithNoQParam () throws Exception {
        String address = "https://api.nasa.gov/planetary/sounds?limit=99999&api_key=" + Downloader.API_KEY;
        String response = "";
        int allTracksLength = 64;

        response = Downloader.download(address);

        JSONObject obj = new JSONObject(response);
        JSONArray arr = obj.getJSONArray("results");
        assert arr.length() == allTracksLength;
    }

    // We have no documentation so let's suppose that the word should be contained in either track name or description
    @Test
    public void testWithSetQParam () throws Exception {
        String searchParam = "Voyager";
        String address = "https://api.nasa.gov/planetary/sounds?q=" + searchParam + "&api_key=" + Downloader.API_KEY;
        String response = "";

        response = Downloader.download(address);

        JSONObject obj = new JSONObject(response);
        JSONArray arr = obj.getJSONArray("results");
        for( int i = 0; i < arr.length(); i++) {
            JSONObject track = new JSONObject(arr.get(i).toString());
            boolean pass = false;

            if(track.getString("description") != null) {
                pass = track.getString("description").contains(searchParam);
            }
            if(!pass && (track.getString("title") != null) ) {
                pass = track.getString("title").contains(searchParam);
            }

            if(!pass){
                Reporter.log("Title: \'" + track.getString("title") + "\' nor description: \'" +
                        track.getString("description") + "\' contains searched word: " + searchParam);
            } else {
                Reporter.log(track.getString("title") + " - Passed");
            }

            assert pass;
        }
    }
}
