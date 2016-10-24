package com.walmart.atf.core.web.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.walmart.atf.core.web.WebPage;
import com.walmart.atf.core.web.elements.TextInput;
import com.walmart.atf.core.web.elements.Button;
import com.walmart.atf.core.web.elements.Text;


public class SignUpPage extends WebPage<SignUpPage> {
	
	private static final String PAGE_URL = "https://www.walmart.com/account/signup";

	public SignUpPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public SignUpPage load() {
		driver.get(PAGE_URL);
		return this;
	}

	@Override
	public boolean isAvailable() {
		return getFirstNameInput().isAvailable() &&
				getLastNameInput().isAvailable() &&
				getEmailInput().isAvailable() &&
				getPasswordInput().isAvailable() &&
				getCreateAccountButton().isAvailable();
				
	}
	
	
	public SignUpPage unSuccessfulSignup(String firstname, String lastname, String email, String password){
		getFirstNameInput().inputText(firstname);
		getLastNameInput().inputText(lastname);
		getEmailInput().inputText(email);
		getPasswordInput().inputText(password);
		getCreateAccountButton().click();
		return new SignUpPage(driver).waitUntilAvailable();
	}
	
	public AccountPage successfuSignup(String firstname, String lastname, String email, String password){
		getFirstNameInput().inputText(firstname);
		getLastNameInput().inputText(lastname);
		getEmailInput().inputText(email);
		getPasswordInput().inputText(password);
		getCreateAccountButton().click();
		return new AccountPage(driver).waitUntilAvailable();		
	}
	
	public String getSigupWrongPasswordErrorMessage(){
		return new Text(driver, By.xpath("//p[contains(text(), 'Your password must contain between 6 and 12 characters')]")).getText();
	}
	
	public String getNewexistingUserErrorMessage(){
		return new Text(driver, By.xpath("//span[contains(text(),  'The email address you entered')]")).getText();
	}
	
	public String getSignUpEmptyFirstNameErrorMessage(){
		return new Text(driver, By.xpath("//p[@data-reactid='.0.1.0.1.3.0.2']")).getText();
		//return new Text(driver, By.xpath("//span[text()='First name']/following-sibling::p")).getText();
	}
	
	public String getSignUpEmptyLastNameErrorMessage(){
		return new Text(driver, By.xpath("//p[@data-reactid='.0.1.0.1.3.1.2']")).getText();
	}
	
	public String getSignUpEmptyEmailErrorMessage(){
		return new Text(driver, By.xpath("//p[@data-reactid='.0.1.0.1.3.2.2']")).getText();
	}
	
	public String getSignUpEmptyPasswordErrorMessage(){
		return new Text(driver, By.xpath("//p[@data-reactid='.0.1.0.1.3.4.1.2']")).getText();
	}
	
	private TextInput getFirstNameInput(){
		return new TextInput(driver, By.name("firstName"));
	}
	
	private TextInput getLastNameInput(){
		return new TextInput(driver, By.name("lastName"));
	}
	
	private TextInput getEmailInput(){
		return new TextInput(driver, By.name("email"));
	}
	
	private TextInput getPasswordInput(){
		return new TextInput(driver, By.name("password"));
	}
	
	private Button getCreateAccountButton(){
		return new Button(driver, By.xpath("//button[@data-tl-id='signup-submit-btn']"));
	}
		
}
