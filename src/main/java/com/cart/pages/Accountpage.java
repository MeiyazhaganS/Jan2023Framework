package com.cart.pages;

import java.util.ArrayList;


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cart.utilities.Constants;
import com.cart.utilities.Elementutils;


public class Accountpage {

	private WebDriver driver;
	private Elementutils eleUtils;
	private By searchText = By.name("search");
	private By searchBox = By.cssSelector(".input-group-btn");
	private By HeaderLogo = By.cssSelector("div#logo");
	private By HeaderContent = By.cssSelector("div#content h2");

	public Accountpage(WebDriver driver) {
		this.driver = driver;
		eleUtils = new Elementutils(driver);
	}

	public String accountpageTitle() {
		return driver.getTitle();
	}

	public boolean isAccountPageExist() {
		if (accountpageTitle().equals(Constants.ACCOUNT_PAGE_TITLE)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean accountsLogoExist() {
		// return driver.findElement(HeaderLogo).isDisplayed();
		return eleUtils.doIsDisplayed(HeaderLogo);
	}

	public boolean doSearchExist() {
		// return driver.findElement(searchBox).isEnabled();
		return eleUtils.doIsEnabled(searchBox);
	}

	public List<String> AccountsContentList() {
		//List<WebElement> contentsList = driver.findElements(HeaderContent);
		List<WebElement> contentsList=eleUtils.waitForElementsToBeVisible(HeaderContent,Constants.DEFAULT_TIME_OUT);
		List<String> textList = new ArrayList<>();
		for (WebElement ele : contentsList) {
			if (!(ele == null)) {
				String eleText = ele.getText();
				textList.add(eleText);
			}
		}
		return textList;
	}

	public SearchResultpage doSearch(String productName) {
//		driver.findElement(searchText).sendKeys("macbook");
//		driver.findElement(searchBox).click();
		if (doSearchExist()) {
			eleUtils.doSendKeys(searchText,productName);
			eleUtils.doClick(searchBox);
			return new SearchResultpage(driver);
		} else {
			return null;
		}
	}
	
	public SearchResultpage InvalidProductSearch(String prodName) {
		if(doSearchExist()) {
			eleUtils.doSendKeys(searchText, prodName);
			eleUtils.doClick(searchBox);
			return new SearchResultpage(driver);
		}else {
			return null;
		}
	}
	
}
