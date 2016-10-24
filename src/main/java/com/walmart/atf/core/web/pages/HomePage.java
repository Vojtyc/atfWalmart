package com.walmart.atf.core.web.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.walmart.atf.core.web.WebPage;
import com.walmart.atf.core.web.elements.Link;


public class HomePage extends WebPage<HomePage> {
	
	private static final String PAGE_URL = "https://www.walmart.com/";

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@Override
	public HomePage load() {
		driver.get(PAGE_URL);
		return this;
	}

	@Override
	public boolean isAvailable() {
		return getMyAccountLink().isAvailable();
				
	}
	
	/*public LoginPage openHomePage(){
		load().waitUntilAvailable().gotoLoginPage();
		return new LoginPage(driver).waitUntilAvailable();
	}
	
		
	public LoginPage gotoLoginPage(){
		getSigninLink().click();
		return new LoginPage(driver).waitUntilAvailable();
	}*/
	
		
	private Link getSigninLink(){
		return new Link(driver, By.xpath("//a[@href='/account/login']"));
	}
	
	private Link getMyAccountLink(){
		return new Link(driver, By.xpath("//a[@href='/account']"));
	}
}
