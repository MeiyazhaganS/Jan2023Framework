package com.cart.BaseTest;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.cart.factory.DriverFactory;
import com.cart.pages.Accountpage;
import com.cart.pages.Loginpage;
import com.cart.pages.Productinfopage;
import com.cart.pages.Registrationpage;
import com.cart.pages.SearchResultpage;

public class Basetest extends DriverFactory {

	public DriverFactory df;
	public Properties prop;
	public WebDriver driver;
	public Loginpage loginpage;
	public Accountpage accountpage;
	public SearchResultpage searchpage;
	public Productinfopage prodinfopage;
	public Registrationpage registerpage;
	public SoftAssert softassert;
	
	@BeforeTest
	public void setup() {
	df=new DriverFactory();
	prop=df.init_prop();
	driver=df.init_driver(prop);
	loginpage= new Loginpage(driver); 
	softassert=new SoftAssert();
	}
	@AfterTest
	public void tearDown() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8000));
		driver.quit();
	}
}
