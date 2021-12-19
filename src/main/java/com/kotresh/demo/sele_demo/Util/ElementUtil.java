package com.kotresh.demo.sele_demo.Util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;

public class ElementUtil {
	
	private WebDriver driver;

	public ElementUtil(WebDriver driver) {		
		this.driver=driver;
	}
	
	public WebElement getElemnt(By locator) 
	{
		return driver.findElement(locator);
	}
	public WebElement getElement(String locatorType,String locatorValue) 
	{
		return driver.findElement(getBy(locatorType,locatorValue));
	}
	
	public By getBy(String locatorType,String locatorValue) 
	{
		By locator=null;
		switch (locatorType.toLowerCase()) {
		case "id": 
			locator=By.id(locatorValue);
			break;
		case "name": 
			locator=By.name(locatorValue);
			break;
		case "xpath": 
			locator=By.xpath(locatorValue);
			break;
		case "css": 
			locator=By.cssSelector(locatorValue);
			break;
		case "linktext": 
			locator=By.linkText(locatorValue);
			break;			
		default:
			locator=null;
			break;
		}
		return locator;
		
	}
	
	@Step("Performed Click Action on Link/Button/Image")
	public boolean VerifyLinksOrButtons(WebElement ele, String ObjVal) {
			waitTill(20);
			// Initialize and wait till element(link) became clickable - timeout in given seconds
			WebElement firstResult = new WebDriverWait(driver, 40)
			        .until(ExpectedConditions.elementToBeClickable(ele));
			// Print the first result
			if (firstResult.isDisplayed()) {										
				System.out.println("Click Action Peroformed on the "+firstResult.getText()+"  WebElement(Link/Button/Image)");		
				firstResult.click();
				return true;
			} else {				
				System.out.print("Object not displayed/identified Or Bug.!!!");			
				return false;
			}			
				
	}	
	@Step("Performed Entry Action on WebEdit Field")
	public boolean VerifyWebEdits(WebElement ele, String ObjVal) {
//			waitTill(2);
			// Initialize and wait till element(link) became clickable - timeout in given seconds
			WebElement firstResult = new WebDriverWait(driver, 40)
			        .until(ExpectedConditions.elementToBeClickable(ele));
			
			if (firstResult.isDisplayed()) {				
				System.out.println("Set Action Peroformed on the WebElement(WebEdit)");		
				firstResult.sendKeys(ObjVal);
				return true;
			} else {				
				System.out.println("Object not displayed/identified Or Bug.!!!");	
				return false;
			}		
	}

	@SuppressWarnings("deprecation")
	public void waitTill(int intWait) 
	{
		driver.manage().timeouts().implicitlyWait(intWait,TimeUnit.SECONDS);
	}
	
	@Step("Verifying the given link is broken or not")
	public void verifyLinks(String linkUrl)
    {
        try
        {
            URL url = new URL(linkUrl);

            //Now we will be creating url connection and getting the response code
            HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
            httpURLConnect.setConnectTimeout(5000);
            httpURLConnect.connect();
            if(httpURLConnect.getResponseCode()>=400)
            {
            	System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage()+"is a broken link");
            }    
       
            //Fetching and Printing the response code obtained
            else{
                System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage());
            }
        }catch (Exception e) {
      }
    }
}
	
	
	
