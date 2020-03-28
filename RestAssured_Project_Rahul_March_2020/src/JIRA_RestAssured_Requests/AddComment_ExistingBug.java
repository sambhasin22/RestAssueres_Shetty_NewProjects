package JIRA_RestAssured_Requests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import Resources.Utility;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AddComment_ExistingBug extends Utility{

	// Using Session Filter teach by Shetty in JIRA tutorials
	
	
	@Test
	public void addComment_ExistingButTest() {
		
		Response result = given().log().all()
		 .header("Accept","application/json")
		 .header("Content-Type", "application/json")
		 //.header("Cookie", "JSESSIONID=" + sessionID)
		 .pathParam("key", prop.getProperty("BUG_ID_UPDATE_COMMENT_EXIATINGBUG"))
		 .pathParam("id",prop.getProperty("COMMENT_ID"))
		 .body("{\r\n" + 
		 		"\"visibility\": {\r\n" + 
		 		"    \"type\": \"role\",\r\n" + 
		 		"    \"value\": \"Administrators\"\r\n" + 
		 		"  },\r\n" + 
		 		"  \"body\" :\r\n" + 
		 		"			\"Updated the comment by JIRA Updated API By Sumit Bhasin ======== **** \"\r\n" + 
		 		"  \r\n" + 
		 		"}\r\n" + 
		 		"  ").filter(sessionFilter)  // this is the new implementation
		 .when().log().all()
		 .put("/rest/api/2/issue/{key}/comment/{id}")
		 .then().log().all()
		 .assertThat().statusCode(200)
		 .extract().response();
		
		String rs = result.asString();
		JsonPath js = new JsonPath(rs);
		System.out.println("Complete JSON Response is == > " + js);
		
			
		
	}
	
	
}
