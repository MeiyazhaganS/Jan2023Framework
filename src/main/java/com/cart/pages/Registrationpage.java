package com.cart.pages;

import java.util.List;

import org.apache.poi.hssf.record.cont.ContinuableRecord;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cart.utilities.Constants;
import com.cart.utilities.Elementutils;

public class Registrationpage {

	private WebDriver driver;
	private Elementutils eleUtils;
	private By firstName=By.name("firstname");
	private By lastName=By.name("lastname");
	private By email=By.name("email");
	private By telePhone=By.name("telephone");
	private By password=By.name("password");
	private By confirm=By.name("confirm");
//	private By Input=By.cssSelector("div.col-sm-10 input");
	private By SubscribeYes=By.xpath("//div[@class='col-sm-10']//input[@name='newsletter' and @value='1']");
	private By SubscribeNo=By.xpath("//div[@class='col-sm-10']//input[@name='newsletter' and @value='0']");
	private By agreeCheckBox=By.name("agree");
	private By logout=By.linkText("Logout");
	private By register=By.linkText("Register");
	private By continuebtn=By.xpath("//input[@type='submit' and @value='Continue']");
	private By successMessag=By.cssSelector("div#content h1");
	public Registrationpage(WebDriver driver) {
		this.driver=driver;
		eleUtils= new Elementutils(driver);
	}
	
	public boolean accountRegister(String firstName,String lastName,String email,
		String telephone,String password,String confirm,String subscribe) {
		eleUtils.waitForElementToBeVisible(Constants.DEFAULT_TIME_OUT, this.firstName).sendKeys(firstName);
		eleUtils.doSendKeys(this.lastName,lastName);
		eleUtils.doSendKeys(this.email,email);
		eleUtils.doSendKeys(this.telePhone,telephone);
		eleUtils.doSendKeys(this.password,password);
		eleUtils.doSendKeys(this.confirm,confirm);
	
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtils.doClick(SubscribeYes);
		}else {
			eleUtils.doClick(SubscribeNo);
		}
		
		eleUtils.doClick(agreeCheckBox);
		eleUtils.doClick(continuebtn);
	
		if(getRegisterSuccessMessage().contains(Constants.REGISTER_SUCCESS_MESSAG)) {
			goToRegisterPage(); //used to register new register again  
			return true;
		}
		return false;
	}
	
	private String getRegisterSuccessMessage() {
		return eleUtils.waitForElementToBeVisible(Constants.DEFAULT_TIME_OUT, successMessag).getText();
	}
	
	private void goToRegisterPage() {
		eleUtils.doClick(logout);
		eleUtils.doClick(register);
	}
}
