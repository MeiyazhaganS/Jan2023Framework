package com.cart.pagetest;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cart.BaseTest.Basetest;
import com.cart.utilities.Error;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("TRADE-22011 Login page for opencart application...")
@Story("TRADE-220116 Design Login Page Features" )
public class LoginPageNegativeTest extends Basetest{
	
	
	@DataProvider
	public Object[][] getLoginNegativeData() {
		return new Object[][] {
			{"runner@gmail.com","invalidcredentials"},
			{"abctest@gmail.com","tomjerry"},
			{"   ","test@123"},
			{"test@gmail@com","evv12222"},
			{"   ","   "}
		};
	}

	
	@Test(dataProvider = "getLoginNegativeData")
	@Description("Login page Test with invalid credentials.....")
	@Severity(SeverityLevel.NORMAL)
	public void LoginInvalidTest(String userName,String password) {
		Boolean result=loginpage.doInvalidLogin(userName,password);
		Assert.assertTrue(result,Error.LOGIN_PAGE_ERROR_MESSAGE_NOT_DISPLAYED);
	}
}
  