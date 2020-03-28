package JIRA_RestAssured_Requests;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import DataUtil.DataUtil;
import ExcelData.Xls_Reader;
import Resources.Utility;

public class createCusotmer_ExcelData extends Utility {

	@Test(dataProvider="getData")
	public void createCusomerTest(Hashtable<String, String>table) {
		
		System.out.println("Testing ");

	}

	@DataProvider
	public Object[][] getData() {

		Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir") + "\\src\\ExcelData\\caredataa.xlsx");
		Object[][] data = DataUtil.getdata(xls, "AddCustomer");
		return data;

	}

}
