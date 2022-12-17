package com.cart.pagetest;

import org.testng.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cart.BaseTest.Basetest;
import com.cart.utilities.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("TRADE-22013 Search page for opencart application...")
@Story("TRADE-220136 Design Search Page Features" )
public class SearchResultPageTest extends Basetest{

	@BeforeClass
	public void searchTest() {
		accountpage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		searchpage=accountpage.doSearch(Constants.ACCOUNT_PAGE_SEARCH_PRODUCT);	
	}
	@Test
	public void searchText() {
		//Assert.assertEquals(searchpage.getSearchheaderValue(),"Search - +"+"macbook");
		Assert.assertTrue(searchpage.getSearchheaderValue().contains("macbook"));
	}
	@Test 
	@Description("Search page title test")
	@Severity(SeverityLevel.NORMAL)
	public void searchPageTitleTest() {
		Assert.assertEquals(searchpage.searchPageTitle(),Constants.SEARCH_PAGE_TITLE);
	}
	@Test
	@Description("check product count test after search")
	@Severity(SeverityLevel.CRITICAL)
	public void searchProdCountTest() {
		Assert.assertEquals(searchpage.getProductsCount(),Constants.SEARCH_PAGE_PRODUCT_COUNT);
	} 
	@Test
	@Description("Search page header test")
	@Severity(SeverityLevel.NORMAL)
	public void searchProdHeader() {
		Assert.assertEquals(searchpage.getSearchheaderValue(),Constants.SEARCH_PAGE_PRODUCT_TEXT);
	}
	@Test
	@Description("check product list test after search")
	@Severity(SeverityLevel.CRITICAL)
	public void searchPageProductTest() {
		Assert.assertEquals(searchpage.productItems(),Constants.SEARCH_PAGE_PRODUCTS_LIST);
	}
}
