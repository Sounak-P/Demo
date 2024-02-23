package com.pge.application.PageObjects;

import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.NoSuchElementException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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

public class TAMI_TOC_Menu extends BaseClass {

	public static ObjectRead objectRead;
	public static Elements actions;
	public static Logger logger;

	/******************************************************************************************************************/
	@FindBy(xpath = "(//*[@id='Chevron'])[1]")
	WebElement expandLeftPanel;

	@FindBy(xpath = "//input[@class='searchBox']")
	WebElement searchBox;

	@FindBy(xpath = "//div[contains(text(),'Search Breadcrumb')]")
	WebElement breadCrumbFrame;

	@FindBy(xpath = "//button[normalize-space()='Apply']")
	WebElement btnApply;

	@FindBy(xpath = "//div[contains(text(),'User Created')]")
	WebElement UserCreated;

	@FindBy(xpath = "//button[@class='maEdit']")
	WebElement CasualMarkupEditBtn;

	@FindBy(xpath = "//span[normalize-space()='Markup - Points']")
	WebElement MarkUpPoints;

	@FindBy(xpath = "//div[contains(text(),'Title')]/following::input[@type='text'][1]")
	WebElement inputTitle;

	@FindBy(xpath = "//div[contains(text(),'Expiration Date')]/following::input[@type='text'][1]")
	WebElement inputExpirationDate;

	@FindBy(xpath = "//input[@id='btnEditorUpdate']")
	WebElement btnSave;

	@FindBy(xpath = "//input[@id='btnEditorDelete']")
	WebElement btnDelete;

	@FindBy(xpath = "//input[@id='btnEditorDeleteConfirm']")
	WebElement btnYes;

	@FindBy(xpath = "//img[@title='Casual Markup']")
	WebElement toolsCasualMarkUp;

	@FindBy(xpath = "//button[normalize-space()='Apply']")
	WebElement btnCasualMarkUpApply;

	@FindBy(xpath = "//div[normalize-space()='2 mi']")
	WebElement btn2mile;

	/******************************************************************************************************************/

	public TAMI_TOC_Menu(WebDriver driver) throws Exception {
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
	public void funcExpandLeftPanel(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {

			expandLeftPanel.click();
			actions.Wait(Configutil.low);

			if (searchBox.isDisplayed()) {
				ExecutionReport.logMessage(report, Status.PASS, "Expand Left Panel",
						"Left button has been clicked successfully");

			} else {
				ExecutionReport.logMessage(report, Status.FAIL, "Expand Left Panel",
						"Left button has been clicked successfully");

			}

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Expand Left Panel",
					"Left button has been clicked successfully");

		}
	}

	public void CasualMarkUpEdit(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {

			UserCreated.click();
			actions.Wait(Configutil.low);
			
			ExecutionReport.logMessage(report, Status.PASS, "Click on User Created",
					"Click on User Created successfully");

			CasualMarkupEditBtn.click();
			actions.Wait(Configutil.high);
			ExecutionReport.logMessage(report, Status.PASS, "Click on Casual Markup Edit Button",
					"Casual Markup Edit button is clicked successfully");
			

			actions.Wait(Configutil.high);

			MarkUpPoints.click();
			actions.Wait(Configutil.low);
			
			ExecutionReport.logMessage(report, Status.PASS, "Click on User Created",
					"Click on User Created successfully");

			// click on canvas center

			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int width = (int) (screenSize.getWidth() / 2);
			int height = (int) (screenSize.getHeight() / 2);
			actions.Wait(Configutil.low);

			Robot r = new Robot();
			r.mouseMove(width, height);
			actions.Wait(Configutil.low);
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			actions.Wait(Configutil.high);

			inputTitle.sendKeys("Automation");
			actions.Wait(Configutil.low);
			ExecutionReport.logMessage(report, Status.PASS, "Enter Title",
					"Enter Title entered successfully");
			
			// todo -date generation

			Calendar calendar = Calendar.getInstance();
			// add one day to the date/calendar
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			// now get "tomorrow"
			Date tomorrow = calendar.getTime();
			// Creating date format
			DateFormat simple = new SimpleDateFormat("MM/dd/yyyy");
			// Formatting Date according to the given format
			// System.out.println(simple.format(tomorrow));

			inputExpirationDate.clear();
			inputExpirationDate.sendKeys(simple.format(tomorrow));
			actions.Wait(Configutil.low);
			
			ExecutionReport.logMessage(report, Status.PASS, "Enter Expiry Date",
					"Expiry Date entered successfully");
			
			btnSave.click();
			actions.Wait(Configutil.high);
			actions.Wait(Configutil.high);
			
			ExecutionReport.logMessage(report, Status.PASS, "Click on Save button",
					"Save button clicked successfully");
			
			
			int c = 0;
			do {
				r.mouseMove(width + c, height + c);
				actions.Wait(Configutil.low);
				r.mousePress(InputEvent.BUTTON1_MASK);
				r.mouseRelease(InputEvent.BUTTON1_MASK);
				actions.Wait(Configutil.high);
				c+=1;
			} while (!btnDelete.isDisplayed() && c<10);
			
			
			
			btnDelete.click();
			actions.Wait(Configutil.low);
			
			ExecutionReport.logMessage(report, Status.PASS, "Click on Delete button",
					"Delete button clicked successfully");
			btnYes.click();
			actions.Wait(Configutil.low);

			ExecutionReport.logMessage(report, Status.PASS, "Click on Yes",
					"Yes clicked successfully");

			

			/*
			 * driver.findElement(By.xpath(
			 * "//div[contains(text(),'test_point_s3')]/preceding::input[@type='checkbox' and @aria-label='Select row']"
			 * )) .click(); actions.Wait(Configutil.low);
			 */

			

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Casual Markup Validation",
					"Casual Markup Validation failed");

		}
	}

	public void funcSearchLayer(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {

			searchBox.click();
			actions.Wait(Configutil.low);
			searchBox.sendKeys(testData.get("LayerName").trim());
			actions.Wait(Configutil.low);
			ExecutionReport.logMessage(report, Status.PASS, "Search Layer from Search Box",
					"Entered : " + testData.get("LayerName") + " is successful");

			// driver.findElement(By.xpath("//div[contains(text(),'Airports')]/preceding-sibling::div/input")).click();
			
			/*
			 * switch (testData.get("LayerName").trim()) { case "Grade 1 Leaks - Active":
			 * driver.findElement(By
			 * .xpath("(//div[contains(text(),'Grade 1 Leaks')]/preceding-sibling::div/input)[2]"
			 * )) .click(); break;
			 * 
			 * default: driver.findElement(By .xpath("//div[contains(text(),'" +
			 * testData.get("LayerName") + "')]/preceding-sibling::div/input")) .click();
			 * break; }
			 */
			
			
			driver.findElement(By
					.xpath("//div[contains(text(),'" + testData.get("LayerName") + "')]/preceding-sibling::div/input"))
					.click();
			actions.Wait(Configutil.low);

			if (breadCrumbFrame.isDisplayed()) {

				ExecutionReport.logMessage(report, Status.PASS, "Select the CheckBox : " + testData.get("LayerName"),
						testData.get("LayerName") + " Check Box is successfully selected");

			} else {
				ExecutionReport.logMessage(report, Status.FAIL, "Select the CheckBox : " + testData.get("LayerName"),
						testData.get("LayerName") + " Check Box is selection failed");
				throw new Exception(testData.get("LayerName") + " Check Box selection failed");
			}

			if (testData.get("LayerName").contains("Field Orders")) {
				btnApply.click();
				actions.Wait(Configutil.low);

			}

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Search Layer from TOC Menu",
					testData.get("LayerName") + " Check Box selection failed");
			throw new Exception(testData.get("LayerName") + " Check Box selection failed");

		}
	}

	public void funcSearchLayer2(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {

			searchBox.click();
			actions.Wait(Configutil.low);
			searchBox.clear();
			actions.Wait(Configutil.low);
			searchBox.sendKeys(testData.get("SearchCriteria1").trim());
			actions.Wait(Configutil.low);
			ExecutionReport.logMessage(report, Status.PASS, "Search Layer from Search Box",
					"Entered : " + testData.get("LayerName") + " is successful");

			// driver.findElement(By.xpath("//div[contains(text(),'Airports')]/preceding-sibling::div/input")).click();
			driver.findElement(By.xpath(
					"//div[contains(text(),'" + testData.get("SearchCriteria1") + "')]/preceding-sibling::div/input"))
					.click();
			actions.Wait(Configutil.low);

			if (breadCrumbFrame.isDisplayed()) {

				ExecutionReport.logMessage(report, Status.PASS, "Select the CheckBox : " + testData.get("LayerName"),
						testData.get("LayerName") + " Check Box is successfully selected");

			} else {
				ExecutionReport.logMessage(report, Status.FAIL, "Select the CheckBox : " + testData.get("LayerName"),
						testData.get("LayerName") + " Check Box is selection failed");
				throw new Exception(testData.get("LayerName") + " Check Box selection failed");
			}

			/*
			 * if (testData.get("LayerName").contains("Field Orders")) { btnApply.click();
			 * actions.Wait(Configutil.low);
			 * 
			 * }
			 */

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Search Layer from TOC Menu",
					testData.get("LayerName") + " Check Box selection failed");
			throw new Exception(testData.get("LayerName") + " Check Box selection failed");

		}
	}

}
