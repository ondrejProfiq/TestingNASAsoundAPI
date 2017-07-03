import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;


/**
 * Created by ondra
 */
public class TestResponseJSON {
    private final String API_KEY = "rJh6i4JLqs5HqTJcuDgA0qojWq1UY45Oq5qDS3sF";

    // we will rely on the JSON parser class for this
    // since everything that is tested here is contained in the following test, this one could belong to sanity suite
    // and the other one to full regression suite
    @Test(groups = { "regression", "sanity"})
    public void testJSONValid () throws Exception {
        String address = "https://api.nasa.gov/planetary/sounds?api_key=" + Downloader.API_KEY;
        String response = "";

        response = Downloader.download(address);

        JSONObject obj = new JSONObject(response);
        JSONArray arr = obj.getJSONArray("results");

        // Test individual JSONs are indeed valid JSONs
        for(int i = 0; i < arr.length(); i++) {
            JSONObject toTest = new JSONObject(arr.get(i).toString());
            assert toTest.has("id");
        }
    }

    @Test(groups = { "regression"})
    public void testJSONContainsAllSections () throws Exception {
        String[] sections = {"duration", "license", "tag_list", "download_url", "description", "id", "title", "last_modified", "stream_url"};
        String address = "https://api.nasa.gov/planetary/sounds?api_key=" + Downloader.API_KEY;
        String response = "";

        response = Downloader.download(address);

        JSONObject obj = new JSONObject(response);
        JSONArray arr = obj.getJSONArray("results");

        // Test that individual JSONs contain all sections
        for(int i = 0; i < arr.length(); i++) {
            JSONObject toTest = new JSONObject(arr.get(i).toString());
            for(String section : sections) {
                assert toTest.has(section);
            }
        }
    }
}
