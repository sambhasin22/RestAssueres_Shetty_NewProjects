package JIRA_RestAssured_Requests;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import Resources.Utility;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AssignIssue extends Utility {

	private String IssueId;

	@Test
	public void assignIssueTest() {
		CreateIssueWithComments_AttachmentJira cc = new CreateIssueWithComments_AttachmentJira();
		IssueId = cc.bug_id;
		
		Response res = given().header("Content-Type", "application/json").header("Cookie", "JSESSIONID=" + sessionID)
				.body("{\r\n" + "	\"name\" : \"" + prop.getProperty("ASSIGN_ISSUE") + "\"\r\n" + "}").when()
				.put("/rest/api/2/issue/" + IssueId + "/assignee").then().assertThat().statusCode(204).extract()
				.response();

		String result = res.asString();
		JsonPath jsonPath = new JsonPath(result);

	}

}
