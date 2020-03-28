package JIRA_RestAssured_Requests;

import org.testng.Assert;
import org.testng.annotations.Test;
import Resources.Utility;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetIssue extends Utility {

	@Test
	public void getIssueTest() {
							// To bypass HHTPS Validation use 'relaxHTTPValidation'
		Response res = given().relaxedHTTPSValidation().filter(sessionFilter).queryParam("fields", "comment", "summary")
				.pathParam("IssueId", prop.getProperty("GET_ISSUE")).when().get("/rest/api/2/issue/{IssueId}").then()
				.log().all().extract().response();

		String result = res.asString();
		JsonPath js = new JsonPath(result);
		System.out.println(js);

		String key_Id = prop.getProperty("COMMENT_ID");
		int comment_index = js.getInt("fields.comment.comments.size()");
		for (int i = 0; i < comment_index; i++) {
			String matchRecord = js.get("fields.comment.comments[" + i + "].body").toString();
				System.out.println("*********** " + matchRecord);

			if (matchRecord.equalsIgnoreCase("JIAR API Testing,Second Time")) {
				Assert.assertTrue(true);
			}else {
				Assert.assertTrue(false);
			}
			break;
		}

	}

}
