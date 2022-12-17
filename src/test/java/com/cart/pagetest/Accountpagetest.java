package com.cart.pagetest;

import java.util.List;

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
@Epic("TRADE-22012 Account page for opencart application...")
@Story("TRADE-220126 Design Account Page Features" )
public class Accountpagetest extends Basetest{

	@BeforeClass
	public void accountPageSetup() {
		accountpage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}	
	@Test
	@Description("Account page title test")
	@Severity(SeverityLevel.NORMAL)
	public void accountpageTitleTest() {
		String accPageTitle=accountpage.accountpageTitle();
		Assert.assertEquals(accPageTitle,Constants.ACCOUNT_PAGE_TITLE);
	}
	@Test
	@Description("Account page logo test")
	@Severity(SeverityLevel.NORMAL)
	public void accountPageLogoTest() {
		Assert.assertTrue(accountpage.accountsLogoExist());
	}
	@Test
	@Description("Search Exist test")
	@Severity(SeverityLevel.CRITICAL)
	public void accountSearchBoxTest() {
		Assert.assertTrue(accountpage.doSearchExist());
	}
	@Test
	@Description("Account Sections Test")
	@Severity(SeverityLevel.NORMAL)
	public void accountContentsTest() {
		List<String> lis=accountpage.AccountsContentList();
		System.out.println("actual contents "+ lis);
		Assert.assertEquals(lis,Constants.list);
	}
}
