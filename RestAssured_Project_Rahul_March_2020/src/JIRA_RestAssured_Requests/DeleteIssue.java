package JIRA_RestAssured_Requests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import Resources.Utility;

public class DeleteIssue extends Utility {
	private String IssueId;

	@Test
	public void deleteIssueTest() throws InterruptedException {
		
		CreateIssueWithComments_AttachmentJira cc = new CreateIssueWithComments_AttachmentJira();
		IssueId = cc.bug_id;
		System.out.println("Deleted Bug is === > " + IssueId);
		given().header("Content-Type", "application/json").header("Cookie", "JSESSIONID=" + sessionID).when()
				.delete("/rest/api/2/issue/" + IssueId + "").then().log().body().assertThat().statusCode(204);

	}

}
