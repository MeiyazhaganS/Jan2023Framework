package com.cart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cart.utilities.Constants;
import com.cart.utilities.Elementutils;
import com.cart.utilities.Error;

import io.qameta.allure.Step;

public class SearchResultpage {

	private WebDriver driver;//driver not required as we are using eleUtils 
	// it is required when you are navigating to next landing page
	private Elementutils eleUtils;
//	private By searchText = By.name("search");
//	private By searchBox = By.cssSelector(".input-group-btn");
//	private By products=By.xpath("//div[contains(@class,'product-layout')]");
	private By products = By.cssSelector("div.caption a");
	private By searchProdList = By.cssSelector("div#content h4");
	private By searchHeader=By.cssSelector("div#content h1");
	private By statusMessage=By.xpath("//*[@id='content']/p[2]");
	private By InvalidSearchBox=By.xpath("//*[@id=\"search\"]/input");
	private By searchBox = By.cssSelector(".input-group-btn");
	public SearchResultpage(WebDriver driver) {
		this.driver = driver;
		eleUtils = new Elementutils(driver);
	}
	@Step("getSearchPageTitle")
	public String searchPageTitle() {
		return eleUtils.getTitle();
	}
	@Step("getSearchPageHeaderValue")
	public String getSearchheaderValue() {
		if(eleUtils.doIsDisplayed(searchHeader)) {
			return eleUtils.doELementGetText(searchHeader);
		} 
		return null;
	}
	@Step("getProductSearchCount")
	public int getProductsCount() {
		return eleUtils.waitForElementsToBeVisible(products, Constants.DEFAULT_TIME_OUT).size();
	}
	@Step("getProductSearchList")
	public List<String> productItems() {
		if (eleUtils.doIsDisplayed(searchProdList)) {
			// List<WebElement> prodList = eleUtils.getElements(products);
			List<WebElement> prodList =eleUtils.waitForElementsToBeVisible(products, Constants.DEFAULT_TIME_OUT);
			List<String> textList = new ArrayList<>();
			System.out.println("Total prodcuts are " + prodList.size());
			// prodList.parallelStream().forEach(e->e.getText());
			for (WebElement eleList : prodList) {
				String eleText = eleList.getText();
				textList.add(eleText);
			}
			return textList;
		} else {
			return null;
		}
	}
	@Step("Select Product {0}")
	public Productinfopage navigateProductInfo(String prodName) {
			List<WebElement> ele=eleUtils.waitForElementsToBeVisible(products,Constants.DEFAULT_TIME_OUT);
			for(WebElement eList: ele) {
				if(eList.getText().equals(prodName)) {	
				eList.click();
					break;
				}
			}
		return new Productinfopage(driver);
	}
	@Step("Select Invalid Product {0}")
	public Boolean InvalidProductInfo(String prodName) {
	WebElement search_ele=eleUtils.waitForElementToBeVisible(Constants.DEFAULT_TIME_OUT, InvalidSearchBox);
	eleUtils.doSendKeys(InvalidSearchBox, prodName);
	eleUtils.doClick(searchBox);
	String actual_status=eleUtils.waitForElementToBeVisible(Constants.DEFAULT_TIME_OUT,statusMessage).getText();
	if(actual_status.equals(Error.SEARCH_PAGE_STATUS_MSG)) {
		return true;
	}
	return false;
	}
}
