package com.walmart.atf.core.web.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.walmart.atf.core.web.WebComponent;

public class Text extends WebComponent<Text>{

	public Text(WebDriver driver, By findByMethod){
		super(driver, findByMethod);
	}
	
	
}
