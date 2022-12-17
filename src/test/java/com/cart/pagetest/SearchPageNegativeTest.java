package com.cart.pagetest;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cart.BaseTest.Basetest;
 

public class SearchPageNegativeTest extends Basetest{
	
	@BeforeTest
	public void searchPageTest() {
		accountpage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		searchpage=accountpage.InvalidProductSearch("t-shirt");		
	}
	@DataProvider
	public Object[][] getInvalidProductName() {
		return new Object[][] {
			{"roses"},
			{"macbook"},
			{"keys"},
		};
	}
	
	@Test(dataProvider = "getInvalidProductName")
	public void searchInvalidProductTest(String prodText) {
	Boolean results=searchpage.InvalidProductInfo(prodText);	
	Assert.assertTrue(results,"you have searched right product");
	}

	@Test
	public void sum() {
		int a=10;int b=30;
		int sum=a+b;
		Assert.assertEquals(sum, 49,"...sum is not correct");
		// if actual & expected are not matching the text passed as string
	//will ve displayed in testNG and reports if assertion is getting failed
	}
}
