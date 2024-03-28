package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="AllUserData")
	public  String[][] getAllData() throws IOException 
	{
		String filePath = System.getProperty("user.dir")+"//TestData//apiTestData.xlsx";
		ExcelUtility xl = new ExcelUtility(filePath);
		
		
		int totalrowcount = xl.getRowCount("Sheet1");
		int totalcellcount=xl.getCellCount("Sheet1",1);//any row number we can pass that will count total number of rows
		
		String userData[][] = new String [totalrowcount][totalcellcount];
		
		for(int rowno=1;rowno<=totalrowcount;rowno++)
		{
			for(int colno=0;colno<totalcellcount;colno++)
			{
				userData[rowno-1][colno]= xl.getCellData("Sheet1",rowno,colno);
			}
		}
		return userData;
	}
	
	@DataProvider(name="UserNameDataProvider")
	public String [] deleteAllUser() throws IOException
	{
		String filePath = System.getProperty("user.dir")+"//TestData//apiTestData.xlsx";
		ExcelUtility xl = new ExcelUtility(filePath);
		
		int totalrowcount=xl.getRowCount("Sheet1");
		
		String userNameData[] = new String[totalrowcount];
		
		for(int rowno =1;rowno<=totalrowcount;rowno++)
		{
			userNameData[rowno-1]= xl.getCellData("Sheet1",rowno,1);
		}
		
		return userNameData;
		
	}

}
