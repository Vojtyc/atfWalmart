package com.walmart.atf.core;

import static com.walmart.atf.core.DriverMaster.getDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class BaseTest {
	
	protected WebDriver driver;
	
	@BeforeClass(alwaysRun = true)
	@Parameters({"browser"})
	public void setUp(@Optional("firefox") String browser){
		driver = getDriver(browser);
				
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown(){
		driver.quit();
	}

}
