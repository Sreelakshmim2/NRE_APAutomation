package testPage.MavenPageObject;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import extentReporterPack.ExtObjRepo;
import extentReporterPack.log;
import objectRepository.Invoices_Obj;

import utilPack.BasePge;
import utilPack.ExcelUtility;


import java.io.IOException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import jxl.read.biff.BiffException;
import utilPack.BaseTest;

public class Invoices  extends BasePge  {
	BasePge base= new BasePge(driver);

	 public Invoices(WebDriver driver) 
	 {
		super(driver);
		base = new BasePge(driver);
	 }
	 
	/// <summary>
	    /// Test Case Title :
	    /// Automation ID : 
	    /// @Author  : 
	    /// </summary>
		
}