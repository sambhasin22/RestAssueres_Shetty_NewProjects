//package src_Test_DataUtil;

package DataUtil;

import java.util.Hashtable;

import ExcelData.Xls_Reader;

//import src_Test_Resources.Xls_Reader;
//import src_Base_BaseTest.BaseTest;


public class DataUtil /*extends BaseTest*/{
	 //private static int indexFrom;
	// private static int indexTo;
	
		
	public static Object[][]getdata(Xls_Reader xls,String testCaseName){
		
		
		String sheetName="TestData";
			
		int testCaseStartRow =1;
		
		while(!xls.getCellData(sheetName, 0, testCaseStartRow).equals(testCaseName))
			testCaseStartRow++;
		
		//System.out.println("Test Case Start From the Row Number -- " +testCaseStartRow);
		
		// Total Rows of Test Data
		
		int colStartfromRow =testCaseStartRow+1;
		int rowStartFromRow =testCaseStartRow+2;
		
		int rows=0;
		while(!xls.getCellData(sheetName, 0, rowStartFromRow+rows).equals(""))
			rows++;
		
		//System.out.println("Total number of rows of Data  -- " +rows);
		
		
		// Total Cols of Test Data
		
		int cols=0;
		while(!xls.getCellData(sheetName, cols, colStartfromRow).equals(""))
		cols++;
		//System.out.println("Total number of cols of Data  -- " +cols);
		
			
		Object[][] data = new Object[rows][1];
		
		  Hashtable<String, String>table;
		  int rowdata=0;
		  for(int rowNum=rowStartFromRow;rowNum<rowStartFromRow+rows;rowNum++){
			  table= new Hashtable<String,String>();
			  for(int colNum=0;colNum<cols;colNum++){
				  
				  String key = xls.getCellData(sheetName, colNum, colStartfromRow);
				  String value = xls.getCellData(sheetName, colNum, rowNum);
				  table.put(key, value);
			   }
			  data[rowdata][0]=table;
			  rowdata++;
		  }
			
		return data;
			
	}
	
	
	
	public Class<?> autoDataWithinRange(int from, int to){
		
		//indexFrom = from;
		
		//indexTo   = to;
		
		
		return DataUtil.class;
		
		
	}

	
	
	public static Object[][]ApprovedRejectt(Xls_Reader xls,String testCaseName){
		
		
		String sheetName="ApprovedReject";
		//String testCaseName1="AgencyEngineer";
		
		int testCaseStartRow =1;
		
		while(!xls.getCellData(sheetName, 0, testCaseStartRow).equals(testCaseName))
			testCaseStartRow++;
		
		//System.out.println("Test Case Start From the Row Number -- " +testCaseStartRow);
		
		// Total Rows of Test Data
		
		int colStartfromRow =testCaseStartRow+1;
		int rowStartFromRow =testCaseStartRow+2;
		
		int rows=0;
		while(!xls.getCellData(sheetName, 0, rowStartFromRow+rows).equals(""))
			rows++;
		
		//System.out.println("Total number of rows of Data  -- " +rows);
		
		
		// Total Cols of Test Data
		
		int cols=0;
		while(!xls.getCellData(sheetName, cols, colStartfromRow).equals(""))
		cols++;
		//System.out.println("Total number of cols of Data  -- " +cols);
		
			
		Object[][] data = new Object[rows][1];
		
		  Hashtable<String, String>table;
		  int rowdata=0;
		  for(int rowNum=rowStartFromRow;rowNum<rowStartFromRow+rows;rowNum++){
			  table= new Hashtable<String,String>();
			  for(int colNum=0;colNum<cols;colNum++){
				  
				  String key = xls.getCellData(sheetName, colNum, colStartfromRow);
				  String value = xls.getCellData(sheetName, colNum, rowNum);
				  table.put(key, value);
			   }
			  data[rowdata][0]=table;
			  rowdata++;
		  }
			
		return data;
		
}
	
	
	
	
	
	/*public Object[][]AddLinkData(){
	Init();
	Xls_Reader xl = new Xls_Reader(prop.getProperty("ExcelDataPath"));
	
	String sheetName="LoginTest";
	String testCaseName="AddLink";
	
	int testCaseStartRow =1;
	
	while(!xl.getCellData(sheetName, 0, testCaseStartRow).equals(testCaseName))
		testCaseStartRow++;
	
	//System.out.println("Test Case Start From the Row Number -- " +testCaseStartRow);
	
	// Total Rows of Test Data
	
	int colStartfromRow =testCaseStartRow+1;
	int rowStartFromRow =testCaseStartRow+2;
	
	int rows=0;
	while(!xl.getCellData(sheetName, 0, rowStartFromRow+rows).equals(""))
		rows++;
	
	//System.out.println("Total number of rows of Data  -- " +rows);
	
	
	// Total Cols of Test Data
	
	int cols=0;
	while(!xl.getCellData(sheetName, cols, colStartfromRow).equals(""))
	cols++;
	//System.out.println("Total number of cols of Data  -- " +cols);
	
		
	Object[][] data = new Object[rows][1];
	
	  Hashtable<String, String>table;
	  int rowdata=0;
	  for(int rowNum=rowStartFromRow;rowNum<rowStartFromRow+rows;rowNum++){
		  table= new Hashtable<String,String>();
		  for(int colNum=0;colNum<cols;colNum++){
			  
			  String key = xl.getCellData(sheetName, colNum, colStartfromRow);
			  String value = xl.getCellData(sheetName, colNum, rowNum);
			  table.put(key, value);
		   }
		  data[rowdata][0]=table;
		  rowdata++;
	  }
		
	return data;
		
}
	*/
	public static boolean isRunnable(String testName, Xls_Reader xls){
		String sheet="TestCases";
		//String sheet="TestData";
		int rows = xls.getRowCount(sheet);
		for(int r=2;r<=rows;r++){
			String tName=xls.getCellData(sheet, "TCID", r);
			if(tName.equals(testName)){
				String runmode=xls.getCellData(sheet, "Runmode", r);
				if(runmode.equals("Y"))
					return true;
				else
					return false;

			}
		}
		return false;
	}

}
