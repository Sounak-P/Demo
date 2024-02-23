package com.automation.framework.action;

import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import javax.naming.TimeLimitExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.automation.framework.core.BaseClass;
import com.automation.framework.exception.BrowserException;
import com.automation.framework.exception.ElementNotFoundException;
import com.automation.framework.exception.ObjectNameNotFoundException;
import com.automation.framework.interfaces.Elements;

import io.netty.handler.timeout.TimeoutException;

public class action_keywords extends BaseClass implements Elements {

	public action_keywords(WebDriver driver) throws IOException {
		BaseClass.driver = driver;

	}

	public void click(WebElement locator)
			throws BrowserException, ElementNotFoundException, ObjectNameNotFoundException {

		if (driver == null) {
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null) {
			try {
				if (locator.isDisplayed() && locator.isEnabled()) {
					locator.click();
					logger.info("locator xpath is displayed on the screen ");
				} else {
					logger.error(
							"element found and element is not cliable. Please use click tUsingJavascript keyword to perform this action"
									+ locator);
					throw new ElementNotFoundException("Element is not found failed to click");
				}
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				logger.error("Invalid locator or locator not found:," + locator + " ," + locator
						+ " locator does not exists in the screen to click");
			}
		}
	}

	public String getText(WebElement locator)
			throws BrowserException, ElementNotFoundException, ObjectNameNotFoundException {

		String value = null;
		try {

			if (driver == null) {
				throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
			} else if (locator != null) {
				logger.info("locator value is not null");
				try {
					if (locator.getText() != null && locator.getText() != "") {
						value = locator.getText();
						logger.info("extracted text value isue " + value);
						logger.info("Successfully extracted the text value from the element");
					} else {
						try {
							throw new Exception();
						} catch (Exception e) {
							logger.error(
									"element found and element text is empty. Please use getTextUsingJavascript keyword to perform this action");
							e.printStackTrace();
						}

					}

				} catch (InvalidSelectorException e) {
					logger.error("Invalid xpath, verify the xpath syntax " + locator);
				}
			}
		} catch (NoSuchElementException e) {
			logger.error("Invalid locator or locator not found:," + locator + " ," + locator
					+ " locator does not exists in the screen to fetch text");
		} catch (WebDriverException e) {
			logger.error("Error in Webdriver" + e);
		}
		return value;

	}

	public String getElementAttributeValue(WebElement locator, String param)
			throws BrowserException, ElementNotFoundException, ObjectNameNotFoundException {
		String value = null;
		try {
			if (driver == null) {
				throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
			} else if (locator != null) {
				logger.info("locator value is not null");
				try {
					if (locator.getText() != null && locator.getText() != "") {
						value = locator.getAttribute(param);
						logger.info("extracted element attribute " + param + " is " + value);
						logger.info("Successfully extracted the attribute " + param + " from the element");
					} else {
						try {
							throw new Exception();
						} catch (Exception e) {
							logger.error("element found and element attribute" + param
									+ " is empty. Please use other keyword to perform this action");
							e.printStackTrace();
						}

					}

				} catch (InvalidSelectorException e) {
					logger.error("Invalid xpath, verify the xpath syntax " + locator);
				}
			}
		} catch (NoSuchElementException e) {
			logger.error("Invalid locator or locator not found:," + locator + " ," + locator
					+ " locator does not exists in the screen to fetch text");
		} catch (WebDriverException e) {
			logger.error("Error in Webdriver" + e);
		}
		return value;
	}

	public void doubleClick(WebElement locator)
			throws BrowserException, ElementNotFoundException, ObjectNameNotFoundException {
		WebElement element = null;
		logger.info("locator is " + locator);
		if (driver == null) {
			logger.error("Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null) {
			try {
				Actions action = new Actions(driver);

				if (locator.isEnabled()) {
					logger.info(locator + " element is enabled");
					if (locator.isDisplayed()) {
						logger.info(locator + " element is displayed");
						action.doubleClick(element).perform();
						logger.info(locator + " is double clicked");
						logger.info("Double Click successful");
					} else {
						logger.error(locator + " element found, but its not displayed");

					}
				} else {
					logger.error(locator + " element found, but its disabled");
					throw new BrowserException(new Throwable(locator + " element found, but its not displayed"));
				}
			} catch (InvalidSelectorException e) {
				logger.error("Invalid xpath, verify the xpath syntax " + locator);
				throw new BrowserException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				logger.error("Invalid locator or locator not found:," + locator + " ," + locator
						+ " locator does not exists in the screen to perform double click operation");
				throw new BrowserException(new Throwable("Invalid locator or locator not found:," + locator + " ,"
						+ locator + " locator does not exists in the screen to perform double click operation"));
			} catch (WebDriverException e) {
				logger.error("Error in Webdriver" + e);

			}
		} else {
			logger.error("Incorrect parameters or error while parsing parameter");
			throw new BrowserException(new Throwable("Incorrect parameters or error while parsing parameter"));
		}
		logger.info("Double Click keyword is executed");
	}

	public void rightClick(WebElement locator) throws BrowserException, ElementNotFoundException,
			ObjectNameNotFoundException, ElementNotInteractableException {

		if (driver == null) {
			logger.error("Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null) {
			try {
				Actions action = new Actions(driver);

				if (locator.isEnabled()) {
					logger.info(locator + " element is enabled");
					if (locator.isDisplayed()) {
						logger.info(locator + " element is displayed");
						action.contextClick(locator).perform();
						logger.info("Right click on " + locator + " is successful");
					} else {
						logger.error("element found but it's not displayed");
					}
				} else {
					logger.error("element is not enabled: unable to click");
					throw new ElementNotInteractableException(locator + " element found, but its not displayed");
				}
			} catch (InvalidSelectorException e) {
				logger.error("Invalid xpath, verify the xpath syntax " + locator);
				throw new BrowserException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				logger.error("Invalid locator or locator not found:," + locator + " ," + locator
						+ " locator does not exists in the screen to perform right click operation");
				throw new BrowserException(new Throwable("Invalid locator or locator not found:," + locator + " ,"
						+ locator + " locator does not exists in the screen to perform right click operation"));
			} catch (WebDriverException e) {
				logger.error("Error in Webdriver" + e);
			}
		}

	}

	public void IsElementExist(WebElement locator)
			throws BrowserException, ElementNotFoundException, ObjectNameNotFoundException {

	}

	public void isElementDisplayed(WebElement locator)
			throws BrowserException, ElementNotFoundException, ObjectNameNotFoundException {
		// TODO Auto-generated method stub

	}

	public void mouseHover(WebElement locator)
			throws BrowserException, ElementNotFoundException, ObjectNameNotFoundException {
		// TODO Auto-generated method stub

	}

	public void clear(WebElement locator)
			throws BrowserException, ElementNotFoundException, ObjectNameNotFoundException {

		if (driver == null) {
			logger.error("Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));
		} else if (locator != null) {
			try {

				if (locator.isEnabled()) {
					logger.info(locator + " element is enabled");
					if (locator.isDisplayed()) {
						logger.info(locator + " element is displayed");
						locator.clear();
						logger.info(locator + " clear action is performed");
					} else {
						logger.error("element found but it's not displayed");
					}
				} else {
					logger.error("element is not enabled: unable to click");
					throw new ElementNotInteractableException(locator + " element found, but its not displayed");
				}
			} catch (InvalidSelectorException e) {
				logger.error("Invalid xpath, verify the xpath syntax " + locator);
				throw new BrowserException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				logger.error("Invalid locator or locator not found:," + locator + " ," + locator
						+ " locator does not exists in the screen to perform clear operation");
				throw new BrowserException(new Throwable("Invalid locator or locator not found:," + locator + " ,"
						+ locator + " locator does not exists in the screen to perform clear operation"));
			} catch (WebDriverException e) {
				logger.error("Error in Webdriver" + e);
			}
		}

	}

	@Override
	public long Wait(long time) throws Exception {
		long timeUnit = 0;
		try {
			if (driver != null) {
				logger.info("driver is not null");
				timeUnit = time * 1000;
				Thread.sleep(timeUnit);
				timeUnit = time;
				logger.info("Implicit wait applier for " + time + " seconds");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timeUnit;

	}

	@Override
	public void SendKey(WebElement locator, String inputValue)
			throws BrowserException, ElementNotFoundException, ObjectNameNotFoundException {
		if (driver == null) {
			logger.error("Browser is not launched or Driver is failed to initialize");
			throw new BrowserException(new Throwable("Browser is not launched or Driver is failed to initialize"));

		} else if (locator != null) {
			try {
				if (locator.isEnabled()) {
					logger.info(locator + " element is enabled");
					if (locator.isDisplayed()) {
						logger.info(locator + " element is displayed");
						locator.click();
						Thread.sleep(2000);
						logger.info(locator + " clear functionality is performed");
						locator.sendKeys(inputValue);
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
						logger.info(locator + " sendkey action is performed");
					} else {
						logger.error("element found but it's not displayed");
					}
				} else {
					logger.error("element is not enabled: unable to click");
					throw new ElementNotInteractableException(locator + " element found, but its not displayed");
				}
			} catch (InvalidSelectorException e) {
				logger.error("Invalid xpath, verify the xpath syntax " + locator);
				throw new BrowserException(new Throwable("Invalid xpath, verify the xpath syntax: " + locator));
			} catch (NoSuchElementException e) {
				logger.error("Invalid locator or locator not found:," + locator + " ," + locator
						+ " locator does not exists in the screen to perform clear operation");
				throw new BrowserException(new Throwable("Invalid locator or locator not found:," + locator + " ,"
						+ locator + " locator does not exists in the screen to perform clear operation"));
			} catch (WebDriverException e) {
				logger.error("Error in Webdriver" + e);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public long ImplicitWait(long time) throws TimeoutException, TimeLimitExceededException {

		long timeUnit = time;
		try {
			if (driver != null) {
				logger.info("driver is not null");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
				logger.info("implicit wait time " + timeUnit);
			} else {
				logger.info("driver is null");
				throw new WebDriverException();
			}
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		return timeUnit;
	}

	@Override
	public long ExplicitWait(WebElement locator, long time) throws TimeoutException, TimeLimitExceededException {
		// TODO Auto-generated method stub

		long timeUnit = time;

		try {
			if (driver != null) {
				logger.info("driver is not null");

				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeUnit));

				wait.until(ExpectedConditions.visibilityOf(locator));
				logger.error("explicit wait until condition is not satisfied");
			} else {
				logger.error("driver is null");
				throw new WebDriverException();

			}

		} catch (TimeoutException e) {
			logger.error("time out exception occured ", e);
		}
		return time;
	}

}