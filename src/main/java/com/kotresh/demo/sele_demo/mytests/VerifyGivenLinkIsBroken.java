package com.kotresh.demo.sele_demo.mytests;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.kotresh.demo.sele_demo.Config.ConfigPropReader;
import com.kotresh.demo.sele_demo.DriversRoom.DriverMethods;
import com.kotresh.demo.sele_demo.Util.ElementUtil;
import com.kotresh.demo.sele_demo.Util.ExcelUtils;
import com.kotresh.demo.sele_demo.pageObjects.objLoginPage;

public class VerifyGivenLinkIsBroken {

	DriverMethods dm;
	ConfigPropReader cp;
	WebDriver driver;	
	objLoginPage loginPage;
	ElementUtil webObj;
	Properties prop;
// Time recorder.
	long startTime = 0;
	long endTime = 0;
	long deltaTime = 0;		
	static String strTDFile="./src/main/java/com/kotresh/demo/sele_demo/testData/TestData.xlsx";
//	String excelFilePath=prop.getProperty("excelFilePath");
	
//creating object of ExcelUtils class
    static ExcelUtils excelUtils = new ExcelUtils();	
 
	@BeforeTest
	public void setup() throws IOException 
	{
		cp = new ConfigPropReader();
		prop=cp.LoadConfigFile();
		loginPage = new	objLoginPage(driver);	
		webObj = new ElementUtil(driver);
//Set Start Time		
		startTime = System.currentTimeMillis();
		
	}
	
	@Test
	public void VerifyLoginPage() throws InterruptedException, IOException 
	{
// Verify Link is Broken or Not
		webObj.verifyLinks(prop.getProperty("URL"));
	}
	@AfterTest
	public void setDown() 
	{
	
		endTime=System.currentTimeMillis();
		deltaTime = endTime - startTime;
		System.out.println("*******************************************************************");
		System.out.println("Total Execution Time " + (double)(deltaTime/1000) + " seconds.");
		System.out.println("*******************************************************************");
		Reporter.log("Total Execution Time " + (double)(deltaTime/1000) + " seconds.");
		
	}

}
