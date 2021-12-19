package com.kotresh.demo.sele_demo.pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.kotresh.demo.sele_demo.Util.ElementUtil;

public class objLoginPage {	
	private WebDriver driver;
	private static ElementUtil elementUtil;
	
	//Constructor for WebDriver/ElementUtil
	public objLoginPage(WebDriver driver) {
		this.driver=driver;
		elementUtil = new ElementUtil(driver); 
	}
	
	public static WebElement btnLogin(WebDriver driver) 
	{		
		return elementUtil.getElement("xpath", "//span[contains(text(),'Log In')]");
		
	}
	public static WebElement txtEmail(WebDriver driver) 
	{		
		return elementUtil.getElement("xpath", "//input[@name='email']");		
	}
	
	public static WebElement txtPwd(WebDriver driver) 
	{		
		return elementUtil.getElement("xpath", "//input[@name='password']");		
	}
	
	public static WebElement btnSubmit(WebDriver driver) 
	{		
		return elementUtil.getElement("xpath", "//div[text()='Login']");		
	}

	public static WebElement eleUserInfo(WebDriver driver) 
	{		
		return elementUtil.getElement("xpath", "//span[@class='user-display'][contains(text(),'KOTRAGOWDA CHIKKAGOWDRU')]");		
	}
	
	public static WebElement btnSettings(WebDriver driver) 
	{		
		return elementUtil.getElement("xpath", "//i[@class='settings icon'][1]");		
	}
	
	public static WebElement btnLogout(WebDriver driver) 
	{		
		return elementUtil.getElement("xpath", "//*[contains(text(),'Log Out')]");		
	}
}
