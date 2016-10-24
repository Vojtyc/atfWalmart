package com.walmart.atf.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.Map;
import org.testng.annotations.Test;
import com.walmart.atf.core.BaseTest;
import com.walmart.atf.core.CsvDataProvider;
import com.walmart.atf.core.web.pages.SignUpPage;

public class SignUpTest extends BaseTest {
	
	@Test(description="create existing user", dataProvider="CsvDataProvider", dataProviderClass = CsvDataProvider.class)
	public void createExistingUser(Map<String, String> testData){
		SignUpPage targetPage = new SignUpPage(driver).
		load().unSuccessfulSignup(
				testData.get("firstname"),
				testData.get("lastname"),
				testData.get("email"),
				testData.get("password"));		
		assertThat("Page Error message should be as expected", targetPage.getNewexistingUserErrorMessage(), is(equalTo(testData.get("error"))));
		try {Thread.sleep(2000);			
		} catch (Exception e) {
			}
	}
	
	@Test(description="SignUp errors checking", dataProvider="CsvDataProvider", dataProviderClass = CsvDataProvider.class)
	public void signupErrorsChecking(Map<String, String> testData){
		SignUpPage targetPage = new SignUpPage(driver).
		load().unSuccessfulSignup(
				testData.get("firstname"),
				testData.get("lastname"),
				testData.get("email"),
				testData.get("password"));
		if (testData.get("firstname") == null){
			assertThat("Page Error message should be as expected", targetPage.getSignUpEmptyFirstNameErrorMessage(), is(equalTo(testData.get("error"))));	
		}
		if (testData.get("lastname") == null){
			assertThat("Page Error message should be as expected", targetPage.getSignUpEmptyLastNameErrorMessage(), is(equalTo(testData.get("error"))));	
		}
		if (testData.get("email") == null){
			assertThat("Page Error message should be as expected", targetPage.getSignUpEmptyEmailErrorMessage(), is(equalTo(testData.get("error"))));	
		}
		if (testData.get("password") == null){
			assertThat("Page Error message should be as expected", targetPage.getSignUpEmptyPasswordErrorMessage(), is(equalTo(testData.get("error"))));	
		}
		if (testData.get("password").length() < 6 || testData.get("password").length() > 12){
			assertThat("Page Error message should be as expected", targetPage.getSigupWrongPasswordErrorMessage(), is(equalTo(testData.get("error"))));	
		}
		
		try {Thread.sleep(2000);			
		} catch (Exception e) {
			}
	}
	
	@Test(description="successful SignUp", dataProvider="CsvDataProvider", dataProviderClass = CsvDataProvider.class, dependsOnMethods = {"createExistingUser", "signupErrorsChecking"})
	public void signupCorrectData(Map<String, String> testData){
		new SignUpPage(driver).
		load().successfuSignup(
				testData.get("firstname"),
				testData.get("lastname"),
				testData.get("email"),
				testData.get("password")
				);
		try {Thread.sleep(2000);			
		} catch (Exception e) {
			}
	}
	
	
}
