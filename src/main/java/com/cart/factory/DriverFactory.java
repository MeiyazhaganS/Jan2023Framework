package com.cart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.cart.utilities.Browser;
import com.cart.utilities.Error;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public Properties prop;
	public WebDriver driver;
	public static String highlight;
	public OptionsManager optionsmanager;
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<>(); // jdk 1.8

	public static final Logger log=Logger.getLogger(DriverFactory.class);
	
	public WebDriver init_driver(Properties prop) { // call by reference
		//String browser=System.getProperty("browser").trim();
		String browser = prop.getProperty("browser").trim();
		log.info("Browser Name is "+browser);
		
		highlight = prop.getProperty("highlight");
		optionsmanager = new OptionsManager(prop);
		if (browser.equalsIgnoreCase(Browser.CHROME_BROWSER_NAME)) {
		log.info("running test on chrome driver");
			//if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
		//	System.setProperty(Browser.CHROME_BROWSER_BINARY_KEY,Browser.CHROME_DRIVER_PATH);
			// driver= new ChromeDriver(optionsmanager.getChromeOptions());
			tldriver.set(new ChromeDriver(optionsmanager.getChromeOptions()));
		//} else if (browser.equalsIgnoreCase("edge")) {
		} else if (browser.equalsIgnoreCase(Browser.EDGE_BROWSER_NAME)) {
			WebDriverManager.edgedriver().setup();
			// driver= new EdgeDriver();
			tldriver.set(new EdgeDriver());
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver= new FirefoxDriver(optionsmanager.getFirefoxOptions());
			tldriver.set(new FirefoxDriver(optionsmanager.getFirefoxOptions()));
		} else {
			//System.out.println("please pass the right browser"+browser);
			System.out.println(Error.BROWSER_NOT_FOUND_ERR_MSG+browser);
		}
//		driver.manage().deleteAllCookies();
//		driver.manage().window().fullscreen();
//		driver.get(prop.getProperty("url"));
//		return driver;

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().fullscreen();
		getDriver().get(prop.getProperty("url"));
		log.info( prop.getProperty("url")+" url is landed...");
		return getDriver();
	}

	public static WebDriver getDriver() {
		return tldriver.get();// Returns the value in the current thread's loca copy of this
		// webdriver driver();(thread-local variable).
		// if u want to get driver in another utilities or class u simply
		// write Driverfactory.getDriver(); so that u can use driver in that class
		// u can also use driver.get() but in case of threadLocal u need to use above
	}

	public Properties init_prop() {
		prop = new Properties();
		FileInputStream ip = null;
		//Denv="qa"
		String envName = System.getProperty("env");
		System.out.println("running test on this specific environment " + envName);
		log.info("Running test on environment: "+envName);
		if (envName == null) {
			log.info("no env is given...hence running it on QA");
			try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			try {
				switch (envName.toLowerCase()) {
				case "qa":
					log.info("running it on qa");
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "uat":
					log.info("running it on uat");
					ip = new FileInputStream("./src/test/resources/config/uay.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/prod.config.properties");
					break;
				default:
					System.out.println("please pass right environment.... "+envName);
					log.error("please pass right environment.... ");
					log.warn("env is not found...");
					log.fatal("env is not found");// take care of all multi-threading concepts
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			prop.load(ip); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	// selenium responsibility to take screenshots
	// testNG doesn't interact with browser
	// user.dir- current project directory and u create one folder at run time
	public String getScreenshots() {
		File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(scrFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
