package Resources;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Utility {
	public Properties prop;
	public FileInputStream fs;
	public String result;
	public static JsonPath jsonPath;
	public static String sessionID = null;
	public SessionFilter sessionFilter;

	@BeforeTest
	public void init() throws IOException {
		sessionFilter = new SessionFilter();
		initializePropertiyFile();
		RestAssured.baseURI = prop.getProperty("JIRA_HOST");

		try {
			sessionID = createSession_Test();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String currentDateTime() {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		return formatter.format(date);

	}

	public void initializePropertiyFile() throws IOException {
		try {
			if (prop == null) {
				prop = new Properties();
				fs = new FileInputStream(System.getProperty("user.dir") + "\\src\\config.properties");
				prop.load(fs);
			}
		} catch (FileNotFoundException ex) {

			System.out.println(ex.getMessage());
		}

	}

	public String createSession_Test() throws IOException {

		RestAssured.baseURI = prop.getProperty("JIRA_HOST");
		Response res = given().header("Content-Type", "application/json")
				.body("{ \"username\": \"sambhasin24\", \"password\": \"sambhasin24\" }").filter(sessionFilter).when()
				.post("/rest/auth/1/session").then().extract().response();

		String comleteValue = res.asString();
		JsonPath jsonPath = new JsonPath(comleteValue);
		sessionID = jsonPath.get("session.value");
		return sessionID;

	}

	public String getValueFromJSON(Response val, String field) {
		result = val.asString();
		jsonPath = new JsonPath(result);
		String record = jsonPath.get(field);
		return record;
	}

}
