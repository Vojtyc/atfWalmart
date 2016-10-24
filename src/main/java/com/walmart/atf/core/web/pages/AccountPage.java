package com.walmart.atf.core.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.walmart.atf.core.web.WebPage;
import com.walmart.atf.core.web.elements.Link;

public class AccountPage extends WebPage<AccountPage> {
	
	private static final String PAGE_URL = "https://www.walmart.com/account";

	public AccountPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public AccountPage load() {
		driver.get(PAGE_URL);
		return this;
	}

	@Override
	public boolean isAvailable() {
		
		return getChangePasswordLink().isAvailable();
	}
	
	public LoginPage loadAsAnonymousUser(){
		load();
		return new LoginPage(driver).waitUntilAvailable();
	}
	
	private Link getChangePasswordLink(){
		return new Link(driver, By.xpath("//a[contains(text(), 'Change your password')]"));
	}

}
