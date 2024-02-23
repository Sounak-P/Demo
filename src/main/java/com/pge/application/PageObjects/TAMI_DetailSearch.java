package com.pge.application.PageObjects;

import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Hashtable;
import java.util.NoSuchElementException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.automation.framework.action.action_keywords;
import com.automation.framework.config.Configutil;
import com.automation.framework.config.globalConfig;
import com.automation.framework.core.BaseClass;
import com.automation.framework.interfaces.Elements;
import com.automation.framework.utilities.ObjectRead;
import com.automation.framework.utilities.Status;
import com.automation.framework.utilities.ExecutionReport;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;


public class TAMI_DetailSearch extends BaseClass {

	public static ObjectRead objectRead;
	public static Elements actions;
	public static Logger logger;


	/******************************************************************************************************************/
	@FindBy(xpath="//input[@id='detailSearch']")
	WebElement detailSearchCheckBox;
	
	@FindBy(xpath="//*[contains(text(),'Select Layers')]/following-sibling::div/input")
	WebElement EnterSelectLayer;
	
	@FindBy(xpath="//input[@id='DetailSearchid']")
	WebElement SearchText;
	
	@FindBy(css="#dsUpdateExpression")
	WebElement AddExperessionSymbol;
	
	@FindBy(xpath="(//button[contains(text(),'Search')])[1]")
	WebElement searchButton;
	
	@FindBy(xpath="//span[normalize-space()='Search Result']//following::div[@class='MuiDataGrid-cellContent']")
	WebElement SelectFirstResult;
	
	@FindBy(xpath="(//*[@id='Chevron'])[2]")
	WebElement closeRightPanel;
	
	@FindBy(xpath="//div[@id='btmDrawer']/div[2]")
	WebElement closeBottomDrawer;
	
	@FindBy(xpath="(//*[@id='Chevron'])[1]")
	WebElement closeLeftPanel;
	
	@FindBy(xpath="//div[@role='application']//canvas")
	WebElement canvas;
	
	@FindBy(xpath="//div[@role='dialog']/article")
	WebElement popupDailogBox;
	
	@FindBy(xpath="//span[normalize-space()='Select Field:']/parent::div/following-sibling::div/div[contains(@class,'react-select__control')]")
	WebElement SelectField;

	@FindBy(xpath="//span[normalize-space()='Condition:']/parent::div/following-sibling::div/div[contains(@class,'react-select__control')]")
	WebElement SelectCondition;
	
	/******************************************************************************************************************/

	public TAMI_DetailSearch(WebDriver driver) throws Exception {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		actions = new action_keywords(driver);
		logger = LogManager.getLogger(this.getClass().getSimpleName());
		actions.Wait(Configutil.low);
		objectRead = new ObjectRead();

	}

	/**
	 * @author S6P1
	 * @param testData
	 * @throws Exception
	 */
	public void DetailSearchSelectLayer(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {
			
			detailSearchCheckBox.click();
			actions.Wait(Configutil.low);
			
			
			if (EnterSelectLayer.isDisplayed()) {
				ExecutionReport.logMessage(report, Status.PASS, "Click Detail Search Radio Botton", "Detail Search Radio Botton has been clicked successfully");

			} else {
				ExecutionReport.logMessage(report, Status.FAIL, "Click Detail Search Radio Botton", "Detail Search Radio Botton has been clicked successfully");

			}
			
			EnterSelectLayer.click();
			actions.Wait(Configutil.low);
			EnterSelectLayer.sendKeys(testData.get("LayerName"));
			actions.Wait(Configutil.low);
			
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ENTER).build().perform();
			action.release().build().perform();			
			actions.Wait(Configutil.low);

			action.sendKeys(Keys.PAGE_DOWN).build().perform();
			action.release().build().perform();
			actions.Wait(Configutil.low);
			

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Click on Attribute Tab", "Attribute Tab has been clicked successfully");

		}
	}
	
	
	public void DetailSearchFillDetailsAndSearch(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {
			
			
	
			SelectField.click();
			actions.Wait(Configutil.medium);
			WebElement field =  driver.findElement(By.xpath("//div[contains(@class,'react-select__option')][normalize-space()='"+testData.get("SelectField")+"']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", field);
			actions.Wait(Configutil.low);
			
			SearchText.sendKeys(testData.get("SearchText"));
			actions.Wait(Configutil.low);
			
			SelectCondition.click();
			actions.Wait(Configutil.low);
			WebElement condition =  driver.findElement(By.xpath("//div[contains(@class,'react-select__option')][normalize-space()='"+testData.get("Condition")+"']"));
			actions.Wait(Configutil.low);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", condition);
			actions.Wait(Configutil.low);

			((JavascriptExecutor) driver).executeScript("arguments[0].click();", condition);
			actions.Wait(Configutil.low);

			AddExperessionSymbol.click();
			actions.Wait(Configutil.low);
			
			ExecutionReport.logMessage(report, Status.PASS, "Enter Search Criteria", "Enter Search Criteria has been entered successfully");

			
			
			searchButton.click();
			actions.Wait(Configutil.medium);
			
			if (SelectFirstResult.isDisplayed()) {
				ExecutionReport.logMessage(report, Status.PASS, "Click on Search Button", " Search Button is clicked successfully");

			} else {
				ExecutionReport.logMessage(report, Status.FAIL, "Click on Search Button", " Search Button is clicked successfully");

			}
			

			

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Click on Search Button", " Search Button is clicked successfully");

		}
	}
	
	public void DetailSearchSelectResultAndValidate(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {

			
			SelectFirstResult.click();
			actions.Wait(Configutil.low);
			
			ExecutionReport.logMessage(report, Status.PASS, "Select Result", "Result has been selected successfully");
			
			closeRightPanel.click();
			actions.Wait(Configutil.low);
			
			closeLeftPanel.click();
			actions.Wait(Configutil.low);
			
			ExecutionReport.logMessage(report, Status.PASS, "Close All Panel", "All panel Closed successfully");

			
			/*
			 * canvas.click(); actions.Wait(Configutil.medium);
			 * 
			 * //ClickOnPattern(testData, report);
			 * 
			 * actions.Wait(Configutil.low);
			 * 
			 * 
			 * if (popupDailogBox.isDisplayed()) { ExecutionReport.logMessage(report,
			 * Status.PASS, "Click on the icon and validate info window",
			 * "Icon clicked successfully and info window validated successfully");
			 * 
			 * } else { ExecutionReport.logMessage(report, Status.FAIL,
			 * "Click on the icon and validate info window",
			 * "Icon clicked successfully and info window validated successfully");
			 * 
			 * }
			 */
			
			canvas.click();
			// ClickOnImage(testData, report);
			actions.Wait(Configutil.high);
			
			try {
				//canvas.click();
				Actions a = new Actions(driver);
				a.moveToElement(canvas, 5, 5).click().build().perform();
				
				actions.Wait(Configutil.high);
				
				if (popupDailogBox.isDisplayed()) {
					ExecutionReport.logMessage(report, Status.PASS, "Click on the icon and validate info window",
							"Icon clicked successfully and info window validated successfully");

				} else {
					ExecutionReport.logMessage(report, Status.FAIL, "Click on the icon and validate info window",
							"Icon clicked successfully and info window validated successfully");

				}
				
			} catch (Exception e) {
				
				ClickOnCenter(testData, report);
				actions.Wait(Configutil.high);

				if (popupDailogBox.isDisplayed()) {
					ExecutionReport.logMessage(report, Status.PASS, "Click on the icon and validate info window",
							"Icon clicked successfully and info window validated successfully");

				} else {
					ExecutionReport.logMessage(report, Status.FAIL, "Click on the icon and validate info window",
							"Icon clicked successfully and info window validated successfully");

				}
				
			}

			
			
			

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Click on the icon and validate info window", "Icon clicked successfully and info window validated successfully");

		}
	}
	
	public void ClickOnCenter(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {

			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int width = (int) (screenSize.getWidth() / 2);
			int height = (int) (screenSize.getHeight() / 2);
			actions.Wait(Configutil.low);
			Robot r = new Robot();
			r.mouseMove(width+35, height-70);
			actions.Wait(Configutil.low);
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			actions.Wait(Configutil.low);

			
			/*
			 * if (popupDailogBox.isDisplayed()) { ExecutionReport.logMessage(report,
			 * Status.PASS, "Click on the icon and validate info window",
			 * "Icon clicked successfully and info window validated successfully");
			 * 
			 * } else { ExecutionReport.logMessage(report, Status.FAIL,
			 * "Click on the icon and validate info window",
			 * "Icon clicked successfully and info window validated successfully");
			 * 
			 * }
			 */

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Click on the icon and validate info window",
					"Icon clicked successfully and info window validated successfully");

		}
	}
	
	public void ClickOnPattern(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {

			
			
			String imageRepo = System.getProperty("user.dir") + "\\objectRepository\\imageRepository\\";
			Screen s = new Screen();
			
			try {
				Pattern AddFilterCriteria = new Pattern(imageRepo + testData.get("LayerName") +".png");
				s.click(AddFilterCriteria);
			} catch (Exception e) {

			}
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Click on the icon and validate info window", "Icon clicked successfully and info window validated successfully");

		}
	}
	
}
