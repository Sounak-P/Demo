package com.automation.framework.interfaces;

import javax.naming.TimeLimitExceededException;

import org.openqa.selenium.WebElement;

import com.automation.framework.exception.BrowserException;
import com.automation.framework.exception.ElementNotFoundException;
import com.automation.framework.exception.ObjectNameNotFoundException;

import io.netty.handler.timeout.TimeoutException;

public interface Elements {

		
		public void click(WebElement locator) throws BrowserException, ElementNotFoundException, ObjectNameNotFoundException;
		
		public String getText(WebElement locator) throws BrowserException, ElementNotFoundException, ObjectNameNotFoundException ;
		
		public String getElementAttributeValue(WebElement locator, String param) throws BrowserException, ElementNotFoundException, ObjectNameNotFoundException;
		
		public void doubleClick(WebElement locator) throws BrowserException, ElementNotFoundException, ObjectNameNotFoundException;

		public void rightClick(WebElement locator) throws BrowserException, ElementNotFoundException, ObjectNameNotFoundException;
		
		public void IsElementExist(WebElement locator) throws BrowserException, ElementNotFoundException, ObjectNameNotFoundException;
		
		public void isElementDisplayed(WebElement locator) throws BrowserException, ElementNotFoundException, ObjectNameNotFoundException;
				
		public void mouseHover(WebElement locator) throws BrowserException, ElementNotFoundException, ObjectNameNotFoundException;
		
		public void clear(WebElement locator)throws BrowserException, ElementNotFoundException, ObjectNameNotFoundException ;
		
		public void SendKey(WebElement locator, String param)throws BrowserException, ElementNotFoundException, ObjectNameNotFoundException ;
		
		public long Wait(long time) throws Exception;
		
		public long ImplicitWait(long time) throws TimeoutException, TimeLimitExceededException;
		
		public long ExplicitWait(WebElement locator, long time)throws TimeoutException, TimeLimitExceededException;
}
