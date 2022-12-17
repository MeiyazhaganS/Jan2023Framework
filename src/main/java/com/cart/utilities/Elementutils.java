package com.cart.utilities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cart.factory.DriverFactory;

public class Elementutils {

	private WebDriver driver;
	private WebDriverWait wait;
	private JavaScriptutils jsutils;
	
	public Elementutils(WebDriver driver) {
		this.driver = driver;
		jsutils=new JavaScriptutils(driver);
	}

	public WebElement getElement(By Locator) {
		WebElement element=driver.findElement(Locator);
		if (Boolean.parseBoolean(DriverFactory.highlight)) {
			jsutils.flash(element);
		}
		return element;
	}

	public List<WebElement> getElements(By Locator) {
		return driver.findElements(Locator);
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public void doClick(By Locator) {
		getElement(Locator).click();
	}

	public void doSendKeys(By Locator, String text) {
		WebElement ele = getElement(Locator);
		ele.clear();
		ele.sendKeys(text);
	}

	public String doELementGetText(By Locator) {
		return getElement(Locator).getText();
	}

	public List<String> doELementsGetText(By Locator) {
		List<WebElement> eleList = getElements(Locator);
		List<String> text = new ArrayList<>();
		for (WebElement ele : eleList) {
			String eleText = ele.getText();
			text.add(eleText);
		}
		return text;
	}

	public boolean doIsDisplayed(By Locator) {
		return getElement(Locator).isDisplayed();
	}

	public boolean doIsEnabled(By Locator) {
		return getElement(Locator).isEnabled();
	}

	public String waitForTitleIs(int timeout, String title) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		if (wait.until(ExpectedConditions.titleIs(title))) {
			return driver.getTitle();
		} else {
			System.out.println("title is incorrect");
			return null;
		}
	}

	public String waitForURL(int timeout, String url) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		if (wait.until(ExpectedConditions.urlContains(url))) {
			return driver.getCurrentUrl();
		} else {
			return null;
		}
	}

	// This does not necessarily mean that the element is visible
	public WebElement waitForElementPresent(int timeout, By Locator) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(Locator));
	}

	/*
	 * Visibility means that the element is not only displayed but also has a height
	 * and width that is greater than 0. polling time = 500 ms
	 */
	public WebElement waitForElementToBeVisible(int timeout, By Locator) {
//wait = new WebDriverWait(driver,Duration.ofSeconds(timeout), poolingTime);-->deprecated
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
	}

	public List<WebElement> waitForElementsToBeVisible(By Locator, int timeout) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Locator));
	}
}