package com.cart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cart.utilities.Elementutils;

public class Productinfopage {

	private WebDriver driver;
	private Elementutils eleUtils;
	private By Header=By.cssSelector("div.col-sm-4 h1");
	private By images=By.cssSelector("div#content img");
//	private By profInfo=By.cssSelector("div.col-sm-4 li");
	private By productMetaData=By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData=By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By quantity=By.id("input-quantity");
	private By addToCart=By.id("button-cart");
	private By successMessage=By.cssSelector("div.alert.alert-success.alert-dismissible");
//	private By cart=By.id("cart");
	private Map<String,String> storeMap=new LinkedHashMap<>();
	
	public Productinfopage(WebDriver driver) {
		this.driver=driver; 
		eleUtils=new Elementutils(driver);
	}

	public String productInfoHeader() {
		return eleUtils.doELementGetText(Header);
	}
	public int productImageCount() {
		return eleUtils.getElements(images).size();
	}
	public Map<String,String> productMetaInfo() {
		storeMap.put("product", productInfoHeader());
		storeMap.put("productimages",String.valueOf(productImageCount()));
		productDetails();
		productPriceDetails();
		return storeMap;
	}
	public boolean productQuantity() {
		return eleUtils.getElement(quantity).isDisplayed();
	}
	public String productAddToCart(String qty) {
		if(productQuantity()) {
			eleUtils.getElement(quantity).sendKeys(qty);
			eleUtils.getElement(addToCart).click();
			eleUtils.waitForElementToBeVisible(8000,successMessage);
			return eleUtils.doELementGetText(successMessage).trim();
		}else {
			return null;
		}	
	}
	
	public String productSuccessMessage() {
		return eleUtils.getElement(successMessage).getText();
	}
	
	private void productPriceDetails() {
		List<WebElement> pdata=eleUtils.getElements(productPriceData);
		String price=pdata.get(0).getText().trim();
		String Externalprive=pdata.get(1).getText().trim();
		storeMap.put(price, Externalprive);
	}
	private void productDetails() {
		/*
		 * 
Brand: Apple
Product Code: Product 16
Reward Points: 600
Availability: In Stock
		 */
		List<WebElement> mdata=eleUtils.getElements(productMetaData);
		for(WebElement eList:mdata) {
			String data=eList.getText().trim();
			String dt[]=data.split(":");
			String metaKey=dt[0];
			String metaValue=dt[1].trim();
			storeMap.put(metaKey, metaValue);
		}
	}
	
	public void naviageToViewCart() {
		
	}
}
