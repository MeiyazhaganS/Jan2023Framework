package com.cart.utilities;

public class Error {
/*
 * Instead of writing hard coded create ref variable in this class
 * and use it anywhere in testNG test class or base class
 */
	public static final String LOGIN_PAGE_ERR_MSG=" Warning: No match for E-Mail Address and/or Password.";
	public static final String SEARCH_PAGE_STATUS_MSG="There is no product that matches the search criteria.";
	/*
	 * framework negative error strings...
	 */
	public static final String LOGIN_PAGE_ERROR_MESSAGE_NOT_DISPLAYED="login page error /warning are not displayed after entering invalid credentials";
	public static final String LOGIN_PAGE_TITLE_MISMATCHES="Login page title mismatch";
	public static final String BROWSER_NOT_FOUND_ERR_MSG="please pass the right browser";
}
