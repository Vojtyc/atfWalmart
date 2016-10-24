package com.walmart.atf.core.web.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.walmart.atf.core.web.WebPage;
import com.walmart.atf.core.web.elements.TextInput;
import com.walmart.atf.core.web.elements.Button;
import com.walmart.atf.core.web.elements.Link;
import com.walmart.atf.core.web.elements.Text;


public class LoginPage extends WebPage<LoginPage> {
	
	private static final String PAGE_URL = "https://www.walmart.com/account/login";

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public LoginPage load() {
		driver.get(PAGE_URL);
		return this;
	}

	@Override
	public boolean isAvailable() {
		return getUsernameInput().isAvailable() && 
			    getPasswordInput().isAvailable() &&
			    getLoginButton().isAvailable() &&
			    getCreateOneNew().isAvailable();
	}
	
	public AccountPage successfulLogin(String username, String password){
		getUsernameInput().inputText(username);
		getPasswordInput().inputText(password);
		getLoginButton().click();
		return new AccountPage(driver).waitUntilAvailable();		
	}
	
	public LoginPage unSuccessfulLogin(String username, String password){
		getUsernameInput().inputText(username);
		getPasswordInput().inputText(password);
		getLoginButton().click();
		return new LoginPage(driver).waitUntilAvailable();		
	}
	
	public String getEmailErrorMessage(){
		return new Text(driver, By.xpath("//p[@data-reactid='.0.1.0.1.2:0.0.0.2']")).getText();
	}
	
	public String getPasswordErrorMessage(){
		return new Text(driver, By.xpath("//p[@data-reactid='.0.1.0.1.2:0.1.0.2']")).getText();
	}
	
	public String getErrorMessage(){
		//return new Text(driver, By.xpath("//span[contains(@class, 'alert-error')]")).getText();
		return new Text(driver, By.xpath("//span[text()='Your password and email do not match. Please try again or ']")).getText();
	}
	
	private TextInput getUsernameInput(){
		return new TextInput(driver, By.name("email"));
	}
	
	private TextInput getPasswordInput(){
		return new TextInput(driver, By.name("password"));
	}
	
	private Button getLoginButton(){
		return new Button(driver, By.xpath("//button[@data-tl-id='signin-submit-btn']"));
	}
	
	private Link getCreateOneNew(){
		return new Link(driver, By.xpath("//button[@data-tl-id='signin-sign-up-btn']"));
	}
	

}
