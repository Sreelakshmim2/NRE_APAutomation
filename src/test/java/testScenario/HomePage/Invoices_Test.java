package testScenario.HomePage;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.read.biff.BiffException;
import testPage.MavenPageObject.Invoices;
import utilPack.BaseTest;
import utilPack.ExcelDataSourceInfo;
import utilPack.ExcelUtility;

public class Invoices_Test extends BaseTest  {
	
	

	String path =System.getProperty("user.dir")+"\\src\\Resources\\TestData.xls"; 
	String sheetName = "testData"; 
	
	
	@DataProvider(name ="Invoices") 
	public Object[][] createData(Method method) throws BiffException, IOException{ 
	ExcelDataSourceInfo info = method.getAnnotation(ExcelDataSourceInfo.class);
	String testName =info.TestName();
	Object[][] retObjArr = ExcelUtility.getMapArray(path, sheetName, testName);
	return retObjArr; }
	
	@ExcelDataSourceInfo(TestName = "TC01")
	@Test(dataProvider ="Invoices") 
	public void TC01(Map<Object,Object> map) throws Exception 
	{	

		Invoices Invoices = new Invoices(getDriver());	
		//Invoices.TC01(map);
	}
	

}