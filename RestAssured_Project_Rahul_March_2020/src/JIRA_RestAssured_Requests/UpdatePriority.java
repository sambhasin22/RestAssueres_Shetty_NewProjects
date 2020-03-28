package JIRA_RestAssured_Requests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import Resources.Utility;

public class UpdatePriority extends Utility {
	private String IssueId;

	@Test
	public void updatePriorityTest() {
		CreateIssueWithComments_AttachmentJira cc = new CreateIssueWithComments_AttachmentJira();
		IssueId = cc.bug_id;

		given().header("Content-Type", "application/json").header("Cookie", "JSESSIONID=" + sessionID)
				.body("{\"update\":{\"priority\":[{\"set\":{\"name\" : \""+prop.getProperty("DEFECT_PRIORITY_LOWEST")+"\"}}]}}").when()
				.put("/rest/api/2/issue/"+prop.getProperty("BUG_ID_UPDATE_PRIORITY")+"").then().log().all();
	}

}
