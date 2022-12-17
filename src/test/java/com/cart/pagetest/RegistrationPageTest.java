package com.cart.pagetest;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cart.BaseTest.Basetest;
import com.cart.utilities.Constants;
import com.cart.utilities.Excelutil;

public class RegistrationPageTest extends Basetest {

	@BeforeTest
	public void regPageTest() {
		registerpage = loginpage.naviagteToRegisterLink();
	}

	public String getRandomEmail() {
		Random random = new Random();
		String email = "JanAutomation" + random.nextInt(1000) + "@gmail.com";
		return email;
	}

//	@DataProvider
//	public Object[][] getRegisterationData() {
//		return new Object[][] { { "Hari", "info", "567898765", "hariinfo", "hariinfo", "Yes" },
//				{ "Meiy", "info", "123455432", "meiyinfo", "Meiyinfo", "Yes" } };
//	}

	
	  @DataProvider 
	  public Object[][] getRegisterationData(){ 
		  return Excelutil.getDataFromExcel(Constants.TEST_DATA_SHEET_NAME); 
		  }
	 

	@Test(dataProvider = "getRegisterationData")
	public void userRegisterationTest(String firstName, String lastName, String phoneNum, String password,
			String confirmpaswrod, String subscribe) {
		// registerpage.accountRegister("tom", "jerry", "tomjerry@gmail.com",
		// "123456789", "tomjerry", "tomjerry", "Yes");
		// registerpage.accountRegister("tom", "jerry", getRandomEmail(), "123456789",
		// "tomjerry", "tomjerry", "Yes");
		// in order to enter second data use data provider
		Assert.assertTrue(registerpage.accountRegister(firstName, lastName, getRandomEmail(), phoneNum, password,
				confirmpaswrod, subscribe));
		// time consumed using data provider-4188 & excelutil-5493
	}
}
