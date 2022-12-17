package com.cart.utilities;

public interface Browser {
	// static final by default
	public String CHROME_BROWSER_NAME="chrome";
	public String EDGE_BROWSER_NAME="edge";
	public String FIREFOX_BROWSER_NAME="firfox";

	public String CHROME_BROWSER_BINARY_KEY="webdriver.chrome.driver";
	public String FIREFOX_BROWSER_BINARY_KEY="webdriver.gecko.driver";

	public String CHROME_DRIVER_PATH="./OnlineCart/src/test/resources/drivers/chromedriver";
	public String FIREFOX_DRIVER_PATH="./OnlineCart/src/test/resources/drivers/geckodriver";

}
