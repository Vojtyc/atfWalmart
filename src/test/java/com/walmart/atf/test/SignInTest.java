package com.walmart.atf.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.Map;
import org.testng.annotations.Test;
import com.walmart.atf.core.BaseTest;
import com.walmart.atf.core.CsvDataProvider;
import com.walmart.atf.core.web.pages.AccountPage;
import com.walmart.atf.core.web.pages.LoginPage;

public class SignInTest extends BaseTest {
	
	@Test(description="login with empty data", dataProvider="CsvDataProvider", dataProviderClass = CsvDataProvider.class)
	public void loginEmptyData(Map<String, String> testData){
		LoginPage targetPage = new LoginPage(driver).
		load().
		unSuccessfulLogin(testData.get("email"), testData.get("password"));
		if (testData.get("password") == null){
			assertThat("Page Error message should be as expected", targetPage.getPasswordErrorMessage(), is(equalTo(testData.get("error"))));
		}	
		if (testData.get("email") == null){
			assertThat("Page Error message should be as expected", targetPage.getEmailErrorMessage(), is(equalTo(testData.get("error"))));
		}	
			try {Thread.sleep(2000);			
		} catch (Exception e) {
			}
	}
	
	@Test(description="login with wrong data", dataProvider="CsvDataProvider", dataProviderClass = CsvDataProvider.class)
	public void loginWrongData(Map<String, String> testData){
		LoginPage targetPage = new LoginPage(driver)
				.load()
				.unSuccessfulLogin(testData.get("email"), testData.get("password"));
		assertThat("Page Error message should be as expected", targetPage.getErrorMessage(), is(equalTo(testData.get("error"))));
		try {Thread.sleep(2000);			
		} catch (Exception e) {
			}
	}
	
	
	@Test(description="successful login", dataProvider="CsvDataProvider", dataProviderClass = CsvDataProvider.class, dependsOnMethods = {"loginWrongData", "loginEmptyData"})
		public void loginCorrectData(Map<String, String> testData){
			new AccountPage(driver).
			loadAsAnonymousUser().
			successfulLogin(testData.get("email"), testData.get("password"));
			try {Thread.sleep(2000);			
			} catch (Exception e) {
				}
	}

	
}
