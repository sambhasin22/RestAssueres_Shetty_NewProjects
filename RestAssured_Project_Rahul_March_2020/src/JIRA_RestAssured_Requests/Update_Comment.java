package JIRA_RestAssured_Requests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import Resources.Utility;
import io.restassured.path.json.JsonPath;

public class Update_Comment extends Utility {

	@Test
	public void update_commentTest() {
		CreateIssueWithComments_AttachmentJira cwc = new CreateIssueWithComments_AttachmentJira();
		JsonPath completeJSON = cwc.jsonPath;
		String issueID = completeJSON.get("id");
		String issueKey = completeJSON.get("key");

		given().header("Cookie", "JSESSIONID=" + sessionID).header("Content-Type", "application/json")
				.body("{\r\n" + 
						"	\"body\" : \"Update Comment second time by sumit bhasin\",\r\n" + 
						"	\"visibility\" : {\r\n" + 
						"		\"type\" : \"role\",\r\n" + 
						"		\"value\" : \"Administrators\"\r\n" + 
						"	}\r\n" + 
						"}\r\n" + 
						"  ")
				/*body("{\r\n" + "	\"body\" : \"" + prop.getProperty("EDIT_COMMENT") + "\",\r\n"
						+ "	\"visibility\" : {\r\n" + "		\"type\" : \"role\",\r\n"
						+ "		\"value\" : \"Administrators\"\r\n" + "	}\r\n" + "}\r\n" + "  ")*/
				.when().put("/rest/api/2/issue/" + issueKey + "/comment/" + issueID + "").then().assertThat()
				.statusCode(200);

	}

}
