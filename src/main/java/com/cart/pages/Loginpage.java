package com.cart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cart.utilities.Constants;
import com.cart.utilities.Elementutils;
import com.cart.utilities.Error;

import io.qameta.allure.Step;

public class Loginpage  {

	private WebDriver driver;
	private Elementutils eleUtils;
	private By email=By.id("input-email");
	private By password=By.id("input-password");
	private By forgetPassword=By.linkText("Forgotten Password");
	private By loginBtn=By.xpath("//input[@type='submit']");
	private By registerLink=By.linkText("Register");
	private By MyRepo=By.linkText("My Repo");
	private By loginErrorMessage=By.cssSelector("div.alert.alert-danger.alert-dismissible");
	public Loginpage(WebDriver driver) {
		this.driver=driver;
		eleUtils=new Elementutils(driver);
	}
	@Step("getting login page title...")
	public String getLoginPageTitle() {
		//return driver.getTitle();
		return eleUtils.waitForTitleIs(Constants.DEFAULT_TIME_OUT,Constants.LOGIN_PAGE_TITLE);
	}
	@Step("getting login page url ...")
	public String getLoginPageUrl() {
		//return driver.getCurrentUrl();
		return eleUtils.waitForURL(Constants.DEFAULT_TIME_OUT,Constants.LOGIN_PAGE_FRACTION_URL);	
	}
	@Step("Checking forget pwsd link is displayed or not...")
	public boolean isforgetPassword() {
	//return driver.findElement(forgetPassword).isDisplayed();
	return eleUtils.getElement(forgetPassword).isEnabled();
	}
	@Step("login into application with username{0} and password{1}")
	public Accountpage doLogin(String userName,String pswrd) {
//		driver.findElement(email).sendKeys(userName);
//		driver.findElement(password).sendKeys(pswrd);
//		driver.findElement(loginBtn).click(); 
		//eleUtils.getElement(email).sendKeys(userName);
		eleUtils.waitForElementToBeVisible(Constants.DEFAULT_TIME_OUT, email).sendKeys(userName);
		eleUtils.doSendKeys(password, pswrd);
		eleUtils.doClick(loginBtn);
		return new Accountpage(driver);
	}
	@Step("Login into application with invalid username{0} and password{1}")
	public boolean doInvalidLogin(String userName,String pswrd) {
		WebElement email_ele=eleUtils.waitForElementToBeVisible(Constants.DEFAULT_TIME_OUT, email);
		email_ele.clear();
		email_ele.sendKeys(userName);
		eleUtils.doSendKeys(password, pswrd);;
		eleUtils.doClick(loginBtn);
		String actualErrorMessage=eleUtils.doELementGetText(loginErrorMessage);
		System.out.println(actualErrorMessage);
		if(actualErrorMessage.contains(Error.LOGIN_PAGE_ERR_MSG)) {
			return true;
		}
		return false;
	}
	@Step("Checking register link is exist or not...")
	public boolean isRegisterLink() {
	//	return driver.findElement(registerLink).isDisplayed();
		return eleUtils.waitForElementToBeVisible(Constants.DEFAULT_TIME_OUT,registerLink).isEnabled();
	}
	@Step("Navigate to register page")
	public Registrationpage naviagteToRegisterLink() {
		if(isRegisterLink()) {
			eleUtils.doClick(registerLink);
			return new Registrationpage(driver);
		}
		return null;
	}
	
}
