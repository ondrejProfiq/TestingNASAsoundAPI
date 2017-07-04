import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * Created by ondra
 */
public class TestMethods {

    @Test(expectedExceptions = java.io.IOException.class)
    public void TestMethodPOST () throws Exception {
        String address = "https://api.nasa.gov/planetary/sounds?api_key=" + Downloader.API_KEY;
        String response = Downloader.download(address, Downloader.Method.POST.getName());
        // if Downloader doesn't throw exception we will see what it got
        Reporter.log(response);
    }

    @Test(expectedExceptions = java.io.IOException.class)
    public void TestMethodDELETE () throws Exception {
        String address = "https://api.nasa.gov/planetary/sounds?api_key=" + Downloader.API_KEY;
        String response = Downloader.download(address, Downloader.Method.DELETE.getName());
        // if Downloader doesn't throw exception we will see what it got
        Reporter.log(response);
    }

    @Test(expectedExceptions = java.io.IOException.class)
    public void TestMethodPUT () throws Exception {
        String address = "https://api.nasa.gov/planetary/sounds?api_key=" + Downloader.API_KEY;
        String response = Downloader.download(address, Downloader.Method.PUT.getName());
        // if Downloader doesn't throw exception we will see what it got
        Reporter.log(response);
    }

    @Test
    public void TestMethodGET () throws Exception {
        String address = "https://api.nasa.gov/planetary/sounds?api_key=" + Downloader.API_KEY;
        String response = null;
        response = Downloader.download(address, Downloader.Method.GET.getName());
        // if Downloader doesn't throw exception we will see what it got
        Reporter.log(response);
        assert response != null;
    }
}
