package JIRA_RestAssured_Requests;

import static io.restassured.RestAssured.given;
import java.io.File;
import java.io.IOException;
import org.testng.annotations.Test;
import Resources.Utility;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateIssueWithComments_AttachmentJira extends Utility {

	/*
	 * Here i use SessionFilter class to hold session, class declare in Utility Class and use that session in to complete class.
	 */
	
	
	public static String bug_key;
	public static String bug_id;

	@Test(priority = 1)
	public void createIssueTest() throws IOException {
		String currentDate = currentDateTime();
		Response res = given().
				header("Content-Type", "application/json").header("Cookie", "JSESSIONID=" + sessionID).
body("{\r\n" + 
		"	\"fields\":\r\n" + 
		"	{\r\n" + 
		"		\"project\":\r\n" + 
		"		{\r\n" + 
		"\r\n" + 
		"		\"key\": \"RES\"\r\n" + 
		"		},\r\n" + 
		"		\"summary\":\""+currentDate+"\",\r\n" + 
		"\r\n" + 
		"	\"description\":\""+prop.getProperty("DESCRIPTION")+"\",\r\n" + 
		"		\"issuetype\":\r\n" + 
		"		{\r\n" + 
		"		\"name\":\"Bug\"\r\n" + 
		"		},\r\n" + 
		"		\"priority\": {\r\n" + 
		"      \"id\": \"2\"\r\n" +
		"	}\r\n" + 
		"}\r\n" + 
		"}").when().log().all()
		.post("/rest/api/2/issue").then().log().all().assertThat().statusCode(201).extract().response();

		result = res.asString();
		jsonPath = new JsonPath(result);
		bug_key = jsonPath.get("key");
		bug_id =jsonPath.get("id");
		System.out.println("Bug ID is === > " + bug_id);
		System.out.println("Bug Key is === > " + bug_key);

	}

	@Test(priority = 2)
	public void addCommentTest() {
		
		Response res = given().
		header("Content-Type","application/json").
		body("{\r\n" + 
				"	\"body\" : \""+prop.getProperty("COMMENT")+"\",\r\n" + 
				"	\"visibility\" : {\r\n" + 
				"		\"type\" : \"role\",\r\n" + 
				"		\"value\" : \"Administrators\"\r\n" + 
				"	}\r\n" + 
				"}\r\n" + 
				"  ")
		.filter(sessionFilter)
		.when().
		post("/rest/api/2/issue/"+bug_id+"/comment").
		then().
		assertThat().statusCode(201).
		extract().response();
		
	}
	
	
	@Test(priority=3)
	public void addAttachmentTest() throws InterruptedException {
		Thread.sleep(2000);
		
		Response res =given()
		.header("X-Atlassian-Token","no-check").filter(sessionFilter)
		.header("Content-Type","multipart/form-data")
		.multiPart("file", new File("AddAttachmentFile.txt"))
		.pathParam("IssueId", bug_id)
		.filter(sessionFilter)
		.when()
		.post("/rest/api/2/issue/{IssueId}/attachments")
		.then().log().all()
		.assertThat().statusCode(200)
		.extract().response();
		
		String result = res.asString();
		JsonPath js = new JsonPath(result);
		
	}
}
