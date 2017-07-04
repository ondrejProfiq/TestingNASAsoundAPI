import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by ondra
 */
public class TestMethods {
    /*
     * TODO: It would perhaps be a good idea to test for exact http response code
     * as IOException might happen for other reasons, this is true for some other tests too
    */
    @Test(expectedExceptions = java.io.IOException.class)
    public void testMethodPOST () throws Exception {
        String address = "https://api.nasa.gov/planetary/sounds?api_key=" + Downloader.API_KEY;
        String response = Downloader.download(address, Downloader.Method.POST.getName());
        // if Downloader doesn't throw exception we will see what it got
        Reporter.log(response);
    }

    @Test(expectedExceptions = java.io.IOException.class)
    public void testMethodDELETE () throws Exception {
        String address = "https://api.nasa.gov/planetary/sounds?api_key=" + Downloader.API_KEY;
        String response = Downloader.download(address, Downloader.Method.DELETE.getName());
        // if Downloader doesn't throw exception we will see what it got
        Reporter.log(response);
    }

    @Test(expectedExceptions = java.io.IOException.class)
    public void testMethodPUT () throws Exception {
        String address = "https://api.nasa.gov/planetary/sounds?api_key=" + Downloader.API_KEY;
        String response = Downloader.download(address, Downloader.Method.PUT.getName());
        // if Downloader doesn't throw exception we will see what it got
        Reporter.log(response);
    }

    @Test
    public void testMethodGET () throws Exception {
        String address = "https://api.nasa.gov/planetary/sounds?api_key=" + Downloader.API_KEY;
        String response = null;
        response = Downloader.download(address, Downloader.Method.GET.getName());
        // if Downloader doesn't throw exception we will see what it got
        Reporter.log(response);
        assert response != null;
    }
}
