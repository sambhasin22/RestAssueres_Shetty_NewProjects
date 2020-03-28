package OAuth2_0;


import static io.restassured.RestAssured.given;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class OAuthTest {

	public static void main(String[] args) throws InterruptedException {

			
	/*System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\ChromeExe\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
	Thread.sleep(4000);
	driver.findElement(By.xpath("//input[@type='email' and @jsname='YPqjbf']")).sendKeys("sambhasin4@gmail.com");
	Thread.sleep(4000);
	driver.findElement(By.xpath("//input[@type='email' and @jsname='YPqjbf']")).sendKeys(Keys.ENTER);*/
	String url ="https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&"
			+ "code=4%2FxQFChYTHOe9UEK7oa5xjuhaFWqWYc7eVID29W9UjeMH4ecvEB86HbkTI4FSMLb62N-2PzB6s_8zJZdArL-t2UGk&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent#";
	
	String first[] = url.split("code=");
	String second = first[1];
	String secondCode[] = second.split("&scope");
	String finalCode = secondCode[0];
		
		Response  accessToken =given().urlEncodingEnabled(false)
				.queryParams("code", finalCode)
				.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParams("grant_type", "authorization_code")
		
		.when().log().all()
	    .post("https://www.googleapis.com/oauth2/v4/token")
	    .then()
	    .extract().response();
		String tokenResult = accessToken.asString();
		System.out.println("Code Id is === > " + tokenResult);
		
		
		
	Response response=	given()
		.queryParam("access_token" ,"")
		.when().log().all()
		.get("https://rahulshettyacademy.com/getCourse.php")
		.then()
		.extract().response();
			String result = response.asString();
			JsonPath js = new JsonPath(result);
			
			System.out.println("********** " + result);
		
		
		
		
		
	}

}
