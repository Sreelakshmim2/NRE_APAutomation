package utilPack;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import extentReporterPack.ExtObjRepo;
import extentReporterPack.log;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest extends ExtObjRepo {

	@BeforeMethod
	@Parameters("Browser")
	public void initializeDriver(String browser) {
		try {
			if (browser.equalsIgnoreCase("Chrome")) {
				ChromeOptions options = new ChromeOptions();
				options.setAcceptInsecureCerts(true); // This is to handle the ssl certification issue of chrome browser
				options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
				options.addArguments("--incognito");
				options.addArguments("argument");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(options);
			} else if (browser.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/Utils/geckodriver.exe");
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("Edge")) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/Utils/EdgeDriver.exe");
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			} 
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log.info("Error while invoking the browser" + e.getMessage());
		}
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.navigate().to("https://fintech-prod.us.auth0.com/login?state=hKFo2SBmQWpJeWo1R1JuUkZCZ1VwZm0waklZajE0TVlkUnRuQ6FupWxvZ2luo3RpZNkgdFBraDBUQXcwamc2R2hzOGhaaE1UUnUteWJaUUV5QnejY2lk2SB0RXlUSWkyNnpBZ0x1RldtQ2pudUZRR3prS2dhS2dRUQ&client=tEyTIi26zAgLuFWmCjnuFQGzkKgaKgQQ&protocol=oauth2&audience=https%3A%2F%2Fkong.app.fintech.com&mode=signin&connection=&redirect_uri=https%3A%2F%2Fapp.fintech.com%2Fretailer%3F&scope=openid%20profile%20email%20offline_access&response_type=code&response_mode=query&nonce=OHh0U0J%2BMHo4dX5fUHNqU2YtREkwUVlScmpkdi10WEVWU3pOQm1PNXRTNA%3D%3D&code_challenge=s_2msWu7fDHMxMjF6ncasmTKtPiH7Lc36QW2uN_FbiY&code_challenge_method=S256&auth0Client=eyJuYW1lIjoiYXV0aDAtcmVhY3QiLCJ2ZXJzaW9uIjoiMS4xMC4yIn0%3D");
	}
	
	@AfterMethod
	public void closeDriver() {
		if (driver!=null) {
			 driver.quit();
		}
	}
	public WebDriver getDriver() {
		return driver;
	}
	
	
}
