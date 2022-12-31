package com.cart.utilities;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

public class Constants {
	
	public static final String TEST_DATA_SHEET_PATH = "./src/test/resources/Testdata/registerData.xlsx";
	
	public static final int DEFAULT_TIME_OUT=5;
	public static final String LOGIN_PAGE_TITLE ="Account Login";
	public static final String DEMO_PAGE_TITLE="demo title";
	public static final String LOGIN_PAGE_FRACTION_URL="account/login";
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	public static final List<String> list=new ArrayList<String>(Arrays.asList("My Account","My Orders",
			 "My Affiliate Account","Newsletter"));
	public static final String SEARCH_PAGE_TITLE = "Search - macbook";
	public static final List<String> SEARCH_PAGE_PRODUCTS_LIST = Arrays.asList("MacBook","MacBook Air","MacBook Pro");
	public static final String ACCOUNT_PAGE_SEARCH_PRODUCT = "macbook";
	public static final int SEARCH_PAGE_PRODUCT_COUNT =3;
	public static final String SEARCH_PAGE_PRODUCT_TEXT ="Search - macbook";
	public static final String PRODUCT_INFO_PAGE_HEADER = "MacBook";
	public static final String PRODUCT_INFO_SUCCESS_MESSAGE="Success: You have added MacBook to your shopping cart!";
	public static final String REGISTER_SUCCESS_MESSAG = "Your Account Has Been Created";
	public static final String TEST_DATA_SHEET_NAME ="register";
	public static final String PRODUCT_TEST_DATA_SHEET_PATH = "product";
}
