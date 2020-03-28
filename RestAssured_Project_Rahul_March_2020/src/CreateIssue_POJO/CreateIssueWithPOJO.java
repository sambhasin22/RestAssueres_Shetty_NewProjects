package CreateIssue_POJO;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import Resources.Utility;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class CreateIssueWithPOJO extends Utility {

	@Test
	public void test() {
	
		System.out.println("Session Id is " + sessionID);
		
		fieldsMain fm = new fieldsMain();
		ProjectClass pc = new ProjectClass();
		IssueType it = new IssueType();
		it.setName("Bug");
		pc.setKey("RAH");
		
		fm.setDescription("First POJO Implementation by Sumit Bhasin");
		fm.setSummary("26/03/2020");
		fm.setProject(pc);
		fm.setIssuetype(it);

		Response res = given().log().all()
		.header("Content-Type","application/json")
		.header("Cookie:","JSESSIONID="+sessionID)
		.body(fm)
		.when()
		.post("/rest/api/2/issue")
		.then().log().all()
		.assertThat().statusCode(201)  // ISSUE PERSIST IN THIS LINE
		.extract().response();
		
		String result = res.asString();
		JsonPath js = new JsonPath(result);
		
		System.out.println("Bug id is == > " + js.get("key"));
		
			
	}

}
