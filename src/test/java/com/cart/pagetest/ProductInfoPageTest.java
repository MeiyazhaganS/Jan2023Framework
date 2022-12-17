package com.cart.pagetest;

import java.util.Map;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cart.BaseTest.Basetest;
import com.cart.utilities.Constants;

public class ProductInfoPageTest extends Basetest {

	@BeforeClass
	public void productInfoSetup() {
		accountpage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"MacBook","MacBook Pro"},
			{"MacBook","MacBook Air"},
			{"Apple","Apple Cinema 30\""},
		};
	}
	@Test(dataProvider = "getProductData")
	public void produtInfoHeaderTest(String productName,String mainProductName) {
		searchpage=accountpage.doSearch(productName);
		prodinfopage=searchpage.navigateProductInfo(mainProductName);
	// above two line u can use it in before class but what if u want to 
 //test for multiple data. so writing inside method u can use with dataprovider
		Assert.assertEquals(prodinfopage.productInfoHeader(),mainProductName);
	// this is called data-driven approach.
	}
	
	@DataProvider
	public Object[][] getProductImgData() {
		return new Object[][] {
			{"MacBook","MacBook Air",4},
			{"MacBook","MacBook Pro",4},
			{"MacBook","MacBook",5},
		};
	}
	
//	@DataProvider
//	public Object[][] getProductImgData() {
//		Object[][] productData=Excelutil.getDataFromExcel(Constants.PRODUCT_TEST_DATA_SHEET_PATH); 
//		return productData;
//	}
	@Test(dataProvider ="getProductImgData" )
	public void productImagesTest(String productName,String mainproductName, int imgCount) {
		searchpage=accountpage.doSearch(productName);
		prodinfopage=searchpage.navigateProductInfo(mainproductName);
		Assert.assertEquals(prodinfopage.productImageCount(),imgCount);
	}
	
	@Test
	public void productAddToCartTest() {
		searchpage=accountpage.doSearch(Constants.ACCOUNT_PAGE_SEARCH_PRODUCT);
		prodinfopage=searchpage.navigateProductInfo(Constants.PRODUCT_INFO_PAGE_HEADER);
		Assert.assertTrue(prodinfopage.productAddToCart("1").contains(Constants.PRODUCT_INFO_SUCCESS_MESSAGE));
	}
	
	@Test
	public void productInfoTest() {
		//Arrange
		searchpage=accountpage.doSearch(Constants.ACCOUNT_PAGE_SEARCH_PRODUCT);
		//Act
		prodinfopage=searchpage.navigateProductInfo("MacBook Pro");
		Map<String,String>store=prodinfopage.productMetaInfo();
		store.forEach((k,v)->System.out.println(k +":"+v));
		//Assert-AAA rule to be followed-unit test,integration test.
		//only one assert per single test
		softassert.assertEquals(store.get("Brand"),"Apple");
		softassert.assertEquals(store.get("Product Code"),"Product 18");
		softassert.assertEquals(store.get("Reward Points"),"800");
		softassert.assertEquals(store.get("Availability"),"In Stock");
		softassert.assertAll();
	}
}

