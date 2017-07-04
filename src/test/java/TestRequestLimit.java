import org.testng.Reporter;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by ondra
 */
public class TestRequestLimit {

    /* according to https://github.com/nasa/api-docs/blob/master/source/index.md
     * demo key rates are 30 per hour and 50 per day
     * Last updated 2015, might be outdated */
    @Test
    public void testDemoKeyValues () throws Exception {
        String address = "https://api.nasa.gov/planetary/sounds?q=apollo&api_key=DEMO_KEY";
        int rateLimitExpected = 30;
        int rateLimit = -1;
        int rateLimitRemaining = -1;

        Map<String, List<String>> map = Downloader.getHeader(address);

        if(map.containsKey("X-RateLimit-Limit")){
            try{
                rateLimit = Integer.parseInt(map.get("X-RateLimit-Limit").get(0));
            } catch (NumberFormatException nfe){
                nfe.printStackTrace();
            }
        }
        if(map.containsKey("X-RateLimit-Remaining")){
            try{
                rateLimitRemaining = Integer.parseInt(map.get("X-RateLimit-Remaining").get(0));
            } catch (NumberFormatException nfe){
                nfe.printStackTrace();
            }
        }

        Reporter.log("Total rate limit: " + rateLimit + "Rate limit remaining: " + rateLimitRemaining +
                "\nTotal rate limit expected: " + rateLimitExpected);

        assert rateLimit == rateLimitExpected;
        assert rateLimitRemaining < rateLimitExpected;
    }

    /* according to https://github.com/nasa/api-docs/blob/master/source/index.md
     * developer key rates are 1000 per hour
     * Last updated 2015, might be outdated */
    @Test
    public void testDeveloperKeyValues () throws Exception {
        String address = "https://api.nasa.gov/planetary/sounds?q=apollo&api_key=" + Downloader.API_KEY;
        int rateLimitExpected = 1000;
        int rateLimit = -1;
        int rateLimitRemaining = -1;

        Map<String, List<String>> map = Downloader.getHeader(address);

        if(map.containsKey("X-RateLimit-Limit")){
            try{
                rateLimit = Integer.parseInt(map.get("X-RateLimit-Limit").get(0));
            } catch (NumberFormatException nfe){
                nfe.printStackTrace();
            }
        }
        if(map.containsKey("X-RateLimit-Remaining")){
            try{
                rateLimitRemaining = Integer.parseInt(map.get("X-RateLimit-Remaining").get(0));
            } catch (NumberFormatException nfe){
                nfe.printStackTrace();
            }
        }

        Reporter.log("Rate limit: " + rateLimit + "Rate limit remaining: " + rateLimitRemaining +
                "\nRate limit expected: " + rateLimitExpected);


        assert rateLimit == rateLimitExpected;
        assert rateLimitRemaining < rateLimitExpected;
    }

    @Test
    public void testDeveloperKeyValuesDecrease () throws Exception {
        String address = "https://api.nasa.gov/planetary/sounds?q=apollo&api_key=" + Downloader.API_KEY;
        int rateLimitFirst = -1;
        int rateLimitSecond = -1;

        Map<String, List<String>> map1 = Downloader.getHeader(address);

        if(map1.containsKey("X-RateLimit-Remaining")){
            try{
                rateLimitFirst = Integer.parseInt(map1.get("X-RateLimit-Remaining").get(0));
            } catch (NumberFormatException nfe){
                nfe.printStackTrace();
            }
        }

        Map<String, List<String>> map2 = Downloader.getHeader(address);

        if(map2.containsKey("X-RateLimit-Remaining")){
            try{
                rateLimitSecond = Integer.parseInt(map2.get("X-RateLimit-Remaining").get(0));
            } catch (NumberFormatException nfe){
                nfe.printStackTrace();
            }
        }
        Reporter.log("Rate limit 1: " + rateLimitFirst + "\nRate limit 2: " + rateLimitSecond);

        assert rateLimitSecond == (rateLimitFirst -1);
    }

    @Test
    public void testDemoKeyValuesDecrease () throws Exception {
        String address = "https://api.nasa.gov/planetary/sounds?q=apollo&api_key=DEMO_KEY";
        int rateLimitFirst = -1;
        int rateLimitSecond = -1;

        Map<String, List<String>> map1 = Downloader.getHeader(address);

        if(map1.containsKey("X-RateLimit-Remaining")){
            try{
                rateLimitFirst = Integer.parseInt(map1.get("X-RateLimit-Remaining").get(0));
            } catch (NumberFormatException nfe){
                nfe.printStackTrace();
            }
        }

        Map<String, List<String>> map2 = Downloader.getHeader(address);

        if(map2.containsKey("X-RateLimit-Remaining")){
            try{
                rateLimitSecond = Integer.parseInt(map2.get("X-RateLimit-Remaining").get(0));
            } catch (NumberFormatException nfe){
                nfe.printStackTrace();
            }
        }

        Reporter.log("Rate limit 1: " + rateLimitFirst + "\nRate limit 2: " + rateLimitSecond);

        assert rateLimitSecond == (rateLimitFirst -1);
    }

    // commented out since it takes too much time, running in multiple threads might solve this issue
    /*
    @Test (expectedExceptions = java.io.IOException.class)
    public void testDepletingRateLimit () throws Exception {
        String address = "https://api.nasa.gov/planetary/sounds?q=apollo&api_key=" + Downloader.API_KEY_2;

        for(int i = 0; i < 1500; i++) {
            Downloader.download(address);
        }

    }
    */

}
