package extentReporterPack;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utilPack.BasePge;
import utilPack.BaseTest;

public class ListenersImplementation extends ExtObjRepo implements ITestListener {
	
	public void onTestStart(ITestResult result) {
		test =extent.createTest(result.getMethod().getMethodName());
		log.info("Test Started Successfully");
		test.info("Test Started Successfully");
	}

	
	public void onTestSuccess(ITestResult result) {
		test.pass(result.getName() + " testCase passed successfully.");
		System.out.println("The name of the testcase passed is :"+ result.getName());
	}

	
	public void onTestFailure(ITestResult result) {
		test.fail(result.getName() + " testcase failed.");
		test.fail(result.getThrowable());
		System.out.println("The name of the testcase failed is :"+ result.getName());
		
		//add screenshot for failed cases
		String screenshotpath= TakeScreenShotFailure();
		test.addScreenCaptureFromPath(screenshotpath, "Screenshot of failed "+result.getName());
	}

	
	public void onTestSkipped(ITestResult result) {
		test.skip(result.getName() + " testcase failed.");
		System.out.println("The name of the testcase Skipped is :"+ result.getName());
	}

	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	
	public void onStart(ITestContext context) {
		
		extent = ExtentSetup.setupExtentReport();
	}

	
	public void onFinish(ITestContext context) {
		extent.flush();
		
	}
	
	public String TakeScreenShotFailure() {
	      
		File src =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		Date date = new Date();
		String actualDate = format.format(date);
		
		String screenshotpath = System.getProperty("user.dir")+"/Reports/Screenshots/"+actualDate+".jpeg";
		File dest = new File(screenshotpath);
		
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenshotpath;
		
	}

}
