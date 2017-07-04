import org.testng.Reporter;
import org.testng.annotations.Test;


/**
 * Created by ondra
 */
public class TestParamApiKey {

    @Test(expectedExceptions = java.io.IOException.class)
    public void testWithNoKey () throws Exception {
        String address = "https://api.nasa.gov/planetary/sounds";
        String response = Downloader.download(address);
        // if Downloader doesn't throw exception we will see what it got
        Reporter.log(response);
    }

    @Test(expectedExceptions = java.io.IOException.class)
    public void testWithWrongKey () throws Exception {
        String address = "https://api.nasa.gov/planetary/sounds?api_key=WrongKey";
        String response = Downloader.download(address);
        // if Downloader doesn't throw exception we will see what it got
        Reporter.log(response);
    }

    @Test
    public void testWithRightKey () throws Exception {
        String address = "https://api.nasa.gov/planetary/sounds?api_key=DEMO_KEY";
        // should throw no exception
        String response = Downloader.download(address);
        // print what we got
        Reporter.log(response);
    }

    @Test
    public void testWithGeneratedKey () throws Exception {
        String address = "https://api.nasa.gov/planetary/sounds?api_key=" + Downloader.API_KEY;
        // should throw no exception
        String response = Downloader.download(address);
        // print what we got
        Reporter.log(response);
    }
}
