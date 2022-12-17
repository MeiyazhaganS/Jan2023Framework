package com.cart.pagetest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.cart.BaseTest.Basetest;
import com.cart.utilities.Constants;
import com.cart.utilities.Error;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("TRADE-22011 Login page for opencart application...")
@Story("TRADE-220116 Design Login Page Features" )
public class Loginpagetest extends Basetest {

	@Test
	@Description("Login Page Title Test...")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageTitleTest() {
		String actualTitle=loginpage.getLoginPageTitle();
		System.out.println("actual Title "+actualTitle);
		Assert.assertEquals(actualTitle,Constants.LOGIN_PAGE_TITLE,Error.LOGIN_PAGE_TITLE_MISMATCHES);
		//Assert.assertEquals("actual","expected","optional");
	}
	@Test
	@Description("Login Page Url Test")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageURLTest() {
		String actualurl=loginpage.getLoginPageUrl();
		System.out.println("actual url "+actualurl);
		Assert.assertTrue(actualurl.contains(Constants.LOGIN_PAGE_FRACTION_URL));
	}
	@Test
	@Description("Login forgetpwd link Test")
	@Severity(SeverityLevel.CRITICAL)
	public void forgetPasswordCheck() {
		Assert.assertTrue(loginpage.isforgetPassword());
	}
	@Test(priority = 1)
	@Description("Login page Test")
	@Severity(SeverityLevel.BLOCKER)
	public void doLogin() {
		accountpage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertTrue(accountpage.isAccountPageExist());
	}
	@Test
	@Description("Registration Test")
	@Severity(SeverityLevel.CRITICAL)
	public void regsiterLinkCheck() {
		Assert.assertTrue(loginpage.isRegisterLink());
	}
	
}
