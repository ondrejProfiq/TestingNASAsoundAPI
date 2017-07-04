# NASA api tests

We could divide these tests into several categories:
1. Testing of downloaded data, in our case array of JSONs
 - JSON has valid format
 - JSON contains valid data, here it should primarily be stream URL and download URL. There is however problem, the URLs point to SoundCloud API, for which I would need developer key, it isn't impossible to obtain but it takes some time to get. For this reason these tests were not automated. 
2. Testing of different parameters submitted through the query to the server
 - **q** - Search query parameter. There is no documentation provided so I was only guessing what it should do. I assumed it should filter results based on title or description. There are tests for these test cases in file TestParamQ. These tests show that API doesn't behave as expected. Might be caused by a bug.    
 - **limit** - Limit parameter supposed to limit number of tracks in one response to specified length. Tests for this parameter are automated in file TestParamLimit. I found out that server expects this parameter to be integer and isn't secured against unexpected input. Submitting string as Limit parameter results in server error which definitely deserves looking into.
 - **api_key** - This parameter should guard the API against unauthorized use. User can get his own key <a href='https://api.nasa.gov/#apply-for-an-api-key'>here</a>, there is also demo key with limited request rate. I tested that keys work and you can't get results without one. No problems found here. 
4. Request rate limit - There is specified request rate for developers keys and for demo keys. Demo key should have 30 an hour and 50 per day and IP address according to <a href='https://github.com/nasa/api-docs/blob/master/source/index.md'>this</a> document and developers keys should have rate limited to max 1000 requests per hour and IP address according to the same document. I tested if these limits are adhered. No problems found here
5. Different types of requests - API should respond only to GET requests. Tests how the API responds to other types of requests are automated in file TestMethods.java.
6. Performance tests - Test how the server behaves under heavy load, how many requests is it able to handle. I didn't implement any of these tests, there are specialized tools for this kind of testing.

# Usage 

You need maven installed in your computer and of course java. Just clone this repository to your computer and in the root folder run `mvn test` . Test report is then generated in file _../TestingNASAsoundAPI/target/surefire-reports/index.html_