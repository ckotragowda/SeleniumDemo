package com.kotresh.demo.sele_demo.mytests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.kotresh.demo.sele_demo.Config.ConfigPropReader;
import com.kotresh.demo.sele_demo.DriversRoom.DriverMethods;
import com.kotresh.demo.sele_demo.Util.ElementUtil;
import com.kotresh.demo.sele_demo.Util.ExcelUtils;
import com.kotresh.demo.sele_demo.listeners.TestAllureListener;
import com.kotresh.demo.sele_demo.pageObjects.objLoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners({TestAllureListener.class})
public class LoginTest_TC1 {
	DriverMethods dm;
	ConfigPropReader cp;
	WebDriver driver;	
	objLoginPage loginPage;
	ElementUtil webObj;
	java.util.Properties prop;
	
// Time recorder.
	long startTime = 0;
	long endTime = 0;
	long deltaTime = 0;

	static String strTDFile="./src/main/java/com/kotresh/demo/sele_demo/testData/TestData.xlsx";

//creating object of ExcelUtils class
    static ExcelUtils excelUtils = new ExcelUtils();	
    
    @BeforeTest   
    @Parameters("browser")
	public void setup(String browser) throws IOException 
	{
    	System.out.println("*******Browser is from testNg-"+browser);    	
//Load Config Properties file
		cp = new ConfigPropReader();
		prop=cp.LoadConfigFile();
		dm = new DriverMethods();
		
//Launch Browser		
//From Command Line
//		String BROWSER = System.getProperty("x").trim().replace("\"", "");;
//From Config file
//	browser=prop.getProperty("BROWSER").trim().replace("\"", "");		
		
		driver=dm.initBrowser(browser, prop);
//Set Start Time		
		startTime = System.currentTimeMillis();		
	}
	
	@Test(priority=1,description="verifying login functionality")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: Verify Login Functionality")
	@Story("Story Name: To Check Login Behaviour")
	public void VerifyLoginPage() throws InterruptedException, IOException 
	{
		
		loginPage = new	objLoginPage(driver);	
		webObj = new ElementUtil(driver);

//calling the ExcelUtils class method to initialize the workbook and sheet
        excelUtils.setExcelFile(strTDFile,"TD_LoginPage");
    
		//Click on Login
        
		webObj.VerifyLinksOrButtons(loginPage.btnLogin(driver), "");				
		//Enter Login details		
		webObj.VerifyWebEdits(loginPage.txtEmail(driver), excelUtils.getCellData(1,0));
		webObj.VerifyWebEdits(loginPage.txtPwd(driver), excelUtils.getCellData(1,1));
		//Click on Submit
		webObj.VerifyLinksOrButtons(loginPage.btnSubmit(driver), "");
		//Assert Login Successful
		Assert.assertTrue(loginPage.btnSettings(driver).isDisplayed());
		//Write Result To Excel
		if(loginPage.btnSettings(driver).isDisplayed()){
			excelUtils.setCellValue(1,2,"PASS",strTDFile);
        }else{
        	excelUtils.setCellValue(1,2,"FAIL",strTDFile);
        }

		//Click on Settings
		webObj.VerifyLinksOrButtons(loginPage.btnSettings(driver), "");
		//Click on Logout
		webObj.VerifyLinksOrButtons(loginPage.btnLogout(driver), "");		
	}
	
	@Test(priority=2,description="verifying link status")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: Verify The Given Link is Broken or OK")
	@Story("Story Name: To Check Broken Link")
	public void CheckHttpRequest() {		
		webObj.verifyLinks(prop.getProperty("URL"));
	}

	@AfterTest
	public void setDown() 
	{
		driver.quit();
		endTime=System.currentTimeMillis();
		deltaTime = endTime - startTime;
		System.out.println("*******************************************************************");
		System.out.println("Total Execution Time " + (double)(deltaTime/1000) + " seconds.");
		System.out.println("*******************************************************************");
		Reporter.log("Total Execution Time " + (double)(deltaTime/1000) + " seconds.");
		
	}

}
