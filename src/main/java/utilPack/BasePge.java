package utilPack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

//import com.sun.tools.javac.util.List;

import jdk.internal.vm.annotation.Hidden;
import objectRepository.Invoices_Obj;

public class BasePge {
	protected WebDriver driver;
	WebElement webElement;
	List<WebElement> webElements;
	Assert asrt;
	JavascriptExecutor js;
	Select select;
	String data;
	int exitVal;
	Invoices_Obj AcademicObj = new Invoices_Obj();
	public BasePge(WebDriver driver) {
		this.driver=driver;	
	}
	
	
	/// <summary>
    /// To click an element with given timeout
    /// </summary>
    
	public void buttonClick(By locator) {
		WebDriverWait wait = getWait();
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		webElement =driver.findElement(locator);
		highLighterMethod(driver, webElement); 
		webElement.click();
	}
	
	/// <summary>
    /// To enter value to an element with given timeout
    /// </summary>
	public void setData(By locator, String text) {
		WebDriverWait wait = getWait();
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		webElement =driver.findElement(locator);
		highLighterMethod(driver, webElement); 
		webElement.clear();
		webElement.sendKeys(text);
		
	}
	/// <summary>
    /// To get innertext
    /// </summary>
	public String GetText(By locator) {
		WebDriverWait wait = getWait();
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		webElement =driver.findElement(locator);
		data = webElement.getText(); 
		return data;
	}
	
	/// <summary>
    /// To Switch Window Frame
    /// </summary>
	public void switchToFrame(By locator) {
		WebDriverWait wait = getWait();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
		webElement =driver.findElement(locator);
		driver.switchTo().frame(webElement);
		
	}
	
	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}
	
	
	
	/// <summary>
    /// Validate Text
    /// </summary>
	
	public void validateText(By locator,String expectedText) {
		WebDriverWait wait = getWait();
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		webElement = driver.findElement(locator);
		String actualText = webElement.getText();
		asrt.assertEquals(actualText, expectedText);
		
		
	}
	
	/// <summary>
    /// Validate Text (Return by boolean)
    /// </summary>
   public boolean validateText(By locator) {
	    WebDriverWait wait = getWait();	    
	    wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		webElement = driver.findElement(locator);
		boolean status = webElement.isDisplayed();
		asrt.assertTrue(status);
		return status;		
	}
   
   /// <summary>
   /// Fluent wait
   /// </summary>
   public WebDriverWait getWait() {   //explicit wait
	   
	   WebDriverWait wait = new WebDriverWait(driver, 60);
	   wait.pollingEvery(250,TimeUnit.MILLISECONDS);                //WebDriverWait wait = new WebDriverWait(driver, 60);
	   wait.ignoring(NoSuchElementException.class);                 // wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	   return wait;
	   
   }
	
   /// <summary>
   /// Fluent wait
   /// </summary>
   public void excuteJsClick(By locator) {
	   
	    js = (JavascriptExecutor)driver;
	    WebDriverWait wait = getWait();
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		webElement =driver.findElement(locator);
		highLighterMethod(driver, webElement); 
	    js.executeScript("arguments[0].click();", webElement);
   }
   
   /// <summary>
   /// Select values in the dropdown
   /// </summary>
   
   public void selectorByVisibleText(By locator,String text) {     //drpdown
	    WebDriverWait wait = getWait();
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		webElement = driver.findElement(locator);
		highLighterMethod(driver, webElement); 
		select = new Select(webElement);
	    select.selectByVisibleText(text);
	   
   }
   
   /// <summary>
   /// autoSuggestiveDropDown
   /// </summary>
   public void autoSuggestiveDropDown(By locator,String text) {
	    WebDriverWait wait = getWait();
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    ArrayList<WebElement> elementList = new ArrayList<>(driver.findElements(locator)) ;
	     for(int i=0;i<elementList.size();i++) {
	    	 
	    	  if(elementList.get(i).getText().equalsIgnoreCase(text)) {
	    		  elementList.get(i).click();
	    		  break;
	    	  }
	     }
	     
   }
   
   /// <summary>
   /// isExists Validation
   /// </summary>
   public boolean isExists(By locator) {
	    boolean status= false;
	    WebDriverWait wait = getWait();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	   
		try {
			webElement = driver.findElement(locator);
			if(webElement.isDisplayed())
				status=true;
				return status;
		}
		catch(Exception e) {
			e.printStackTrace();
			return status;
		}
		   
   }
   /// <summary>
   /// Method that highLights elements
   /// </summary>
   public void highLighterMethod(WebDriver driver, WebElement element){
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    String bgColor = element.getCssValue("backgroundColor");
	        js.executeScript("arguments[0].setAttribute('style', 'background: "+bgColor+"; border: 2px solid red;');", element);
	       
	        }
   
   /// <summary>
   /// Select checkBox
   /// </summary>
   public void selectCheckBox(By locator) {
	   webElement  = driver.findElement(locator);
	    boolean isSelected = webElement.isSelected();
		//performing click operation if element is not checked
	 if(isSelected == false) {
		 webElement.click();
	    
	  }
   }
   
   
   /// <summary>
   /// Verifying that modules can be seen
   /// </summary>
   public void checkSubMenue(By locator,java.util.List<String>list) {
	   
	   WebDriverWait wait = getWait();
	   wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	   
	   webElements = (List<WebElement>)driver.findElements(locator);
	   
	   for(int i=0;i<list.size();i++) {
		   
		   for(int j=0;j<webElements.size();j++) {
			   
			   String actualText = webElements.get(j).getText();
			   String expectedText = list.get(i);
			   
			   if(actualText.equals(expectedText)) {
				   
				   asrt.assertEquals(actualText,expectedText);
				   break;
			   }
			   
		   }
	   }	   
	   
   }
   
   
   /// <summary>
   /// Selecting Multiple values from the Dropdown
   /// </summary>
   
   public void multiSelectDropdown(By locator,java.util.List<String>list) {
	   WebDriverWait wait = getWait();
	   wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	   webElements = (List<WebElement>)driver.findElements(locator);
	   highLighterMethod(driver, webElement);
	   
       for(int i=0;i<list.size();i++) {
		   
		   for(int j=0;j<webElements.size();j++) {
			   
			   String actualText = webElements.get(j).getText();
			   String expectedText = list.get(i);
			   
			   if(actualText.equals(expectedText)) {
				   
				   webElements.get(j).click();
				   break;
			   }
			   
		   }
	   }	   
   }
   
   
   /// <summary>
   /// Get Value
   /// </summary>
   public String GetValue(By locator) {
		WebDriverWait wait = getWait();
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		webElement =driver.findElement(locator);
		data = webElement.getAttribute("value"); 
		return data;
	}
	
   
   /// <summary>
   /// Getting Attributes of Elements
   /// </summary>
   public String GetAttribte(By locator,String AttributeName) {
		WebDriverWait wait = getWait();
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		webElement =driver.findElement(locator);
		data = webElement.getAttribute(AttributeName); 
		return data;
	}
	
   
	/// <summary>
   /// To Hover an element with given timeout
   /// </summary>
   public void Hover(By locator) {
	   webElement  = driver.findElement(locator);
	   Actions action = new Actions(driver);
	   action.moveToElement(webElement).perform();
   }
   
   
	/// <summary>
   /// To create a random string by passing number of characters
   /// </summary>
   public static String CreateRandom(int n)
   {
   
    // choose a Character random from this String
    String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
           + "0123456789"
           + "abcdefghijklmnopqrstuvxyz";
   
    // create StringBuffer size of AlphaNumericString
    StringBuilder sb = new StringBuilder(n);
   
    for (int i = 0; i < n; i++) {
   
     // generate a random number between
     // 0 to AlphaNumericString variable length
     int index
      = (int)(AlphaNumericString.length()
        * Math.random());
   
     // add Character one by one in end of sb
     sb.append(AlphaNumericString
        .charAt(index));
    }
   
    return sb.toString();
   }
   
	/// <summary>
	/// To fetch elements
	/// </summary>
	public List<WebElement> GetElement(By locator) {
		WebDriverWait wait = getWait();
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		List<WebElement> elements = driver.findElements(locator);
		return elements;
	}
	
	/// <summary>
	/// To upload file
	/// </summary>
	public void chooseUploadFile(String UploadFile)throws InterruptedException{
		String Uploadfile= System.getProperty("user.dir")+"\\src\\Resources\\"+UploadFile;
		//driver.findElement(class.element).sendKeys(Uploadfile);
	}
	
	/// <summary>
    /// Scroll to element
    /// </summary>
    public void ScrollToElement(By locator) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		webElement =driver.findElement(locator);
		js.executeScript("arguments[0].scrollIntoView();", webElement);
		highLighterMethod(driver, webElement);
	}
    
    /// <summary>
  	/// To Fetch Table Data with table locator,Row Number and Column Name as Parameters
  	/// </summary>
  	public String getTableData(By locator, int rowNumber, String columnName) 
  	{		
  		webElement = driver.findElement(locator);
  		
          // Find the column index based on the column name
          WebElement headerRow = webElement.findElement(By.tagName("thead")).findElement(By.tagName("tr"));
          int columnIndex = 0;
          for (WebElement headerCell : headerRow.findElements(By.tagName("th"))) {
              if (headerCell.getText().equals(columnName)) {
                  break;
              }
              columnIndex++;
          }

          // Find the cell at the specified index and column
          WebElement dataRow = webElement.findElement(By.tagName("tbody")).findElements(By.tagName("tr")).get(rowNumber-1);
          WebElement dataCell = dataRow.findElements(By.tagName("td")).get(columnIndex);

          // Return the text of the cell
          return dataCell.getText();
      }
  	
  	/// <summary>
  	/// Refresh Page
  	/// </summary>
  	public void refreshPage() {
        driver.navigate().refresh();
    }
  	
    }


	


