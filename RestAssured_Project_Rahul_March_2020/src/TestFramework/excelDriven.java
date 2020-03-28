package TestFramework;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class excelDriven {
	
	
	@Test
	public void excel_export() {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		Response res = given().
		body("{\r\n" + 
				"\r\n" + 
				"\"name\":\"Testing for Selenium\",\r\n" + 
				"\"isbn\":\"abc\",\r\n" + 
				"\"aisle\":\"100\",\r\n" + 
				"\"author\":\"John foe\"\r\n" + 
				"}\r\n" + 
				"").
		when().
		post("Library/Addbook.php").
		then().assertThat().statusCode(200).
		and().contentType(ContentType.JSON).
		extract().response();
		
		System.out.println(res.asString());
		

		
		
	}
	

}
