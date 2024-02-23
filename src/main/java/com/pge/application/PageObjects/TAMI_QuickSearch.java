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

public class TAMI_QuickSearch extends BaseClass {

	public static ObjectRead objectRead;
	public static Elements actions;
	public static Logger logger;

	/******************************************************************************************************************/
	@FindBy(xpath = "//input[@id='detailSearch']")
	WebElement detailSearchCheckBox;

	@FindBy(xpath = "(//*[contains(text(),'Select Layers')]/following-sibling::div/input)[2]")
	WebElement EnterSelectLayer;

	@FindBy(xpath = "//input[@id='QuickSearchid']")
	WebElement SearchText;

	@FindBy(css = "#dsUpdateExpression")
	WebElement AddExperessionSymbol;

	@FindBy(xpath = "//input[@id='QuickSearchid']/following::button[text()='Search']")
	WebElement searchButton;

	@FindBy(xpath = "//span[normalize-space()='Search Result']//following::div[@class='MuiDataGrid-cellContent']")
	WebElement SelectFirstResult;

	// @FindBy(xpath="//div[@id='RightPanel']/div")
	@FindBy(xpath = "(//*[@id='Chevron'])[2]")
	WebElement closeRightPanel;

	@FindBy(xpath = "//div[@id='btmDrawer']/div[2]")
	WebElement closeBottomDrawer;

	@FindBy(xpath = "(//*[@id='Chevron'])[1]")
	WebElement closeLeftPanel;

	@FindBy(xpath = "//div[@role='application']//canvas")
	WebElement canvas;

	@FindBy(xpath = "//div[@role='dialog']/article")
	WebElement popupDailogBox;

	@FindBy(xpath = "//img[@aria-label='Legend']")
	WebElement iconLegend;

	@FindBy(xpath = "//div[@id='legendContainer']//*[name()='svg']//*[name()='g']//*[name()='image']")
	WebElement imageProp;

	@FindBy(xpath = "//div[@id='legendContainer']//img[@class='esri-legend__symbol']")
	WebElement imageProp2;

	@FindBy(xpath = "//*[name()='g']/*[name()='circle' or name()='path']")
	WebElement imageProp3;

	@FindBy(xpath = "//span[normalize-space()='Latitude (Y):']/following::input[@type='number']")
	WebElement LatitudeY;

	@FindBy(xpath = "//span[normalize-space()='Longitude (X):']/following::input[@type='number']")
	WebElement LongitudeX;

	@FindBy(xpath = "//button[normalize-space()='Locate']")
	WebElement btnLocate;

	//@FindBy(xpath = "//span[normalize-space()='Zoom to']")
	@FindBy(xpath = "//div[@title='Zoom to']/span[1]")
	WebElement ZoomToLoc;

	@FindBy(xpath = "//div[@title='Close']")
	WebElement ClosePopUp;

	@FindBy(xpath = "//span[contains(text(),'Lat/Lon outside of CA.')]")
	WebElement LatLonOutside;

	@FindBy(xpath = "//span[contains(text(),'Lat/Lon outside of CA.')]/following::button[contains(text(),'Yes')]")
	WebElement btnYes;

	/******************************************************************************************************************/

	public TAMI_QuickSearch(WebDriver driver) throws Exception {
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
	public void QuickSearchSelectLayer(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {
			 String searchInput;
			// detailSearchCheckBox.click();
			// actions.Wait(Configutil.low);

			if (EnterSelectLayer.isDisplayed()) {
				ExecutionReport.logMessage(report, Status.PASS, "Click Quick Search Radio Botton",
						"Quick Search Radio Botton has been clicked successfully");

			} else {
				ExecutionReport.logMessage(report, Status.FAIL, "Click Quick Search Radio Botton",
						"Quick Search Radio Botton has been clicked successfully");

			}

			EnterSelectLayer.click();
			actions.Wait(Configutil.low);
			
			switch (testData.get("LayerName").trim()) {
			case "GD Clearance Archive - Points":
			case "GD Clearance Archive - Polygons":
			case "GD Clearance Archive - Polylines":
			case "GD Clearance Flags - Active Points":
			case "GD Clearance Flags - Active Polygons":
			case "GD Clearance Flags - Active Polylines":
			case "GD Clearance Shapes - Active Polygons":
			case "GD Clearance Shapes - Active Polylines": {
				EnterSelectLayer.sendKeys("Clearances - Distribution");
				searchInput = "Clearances - Distribution";
				break;
			}
			case "GT Clearance Archive - Points":
			case "GT Clearance Archive - Polygons":
			case "GT Clearance Archive - Polylines":
			case "GT Clearance Flags - Active Points":
			case "GT Clearance Flags - Active Polygons":
			case "GT Clearance Flags - Active Polylines":
			case "GT Clearance Shapes - Active Polygons":
			case "GT Clearance Shapes - Active Polylines": {
				EnterSelectLayer.sendKeys("Clearances - Transmission");
				searchInput = "Clearances - Transmission";
				break;
			}
			case "Clearance Counts by District": {
				EnterSelectLayer.sendKeys("Clearances");
				searchInput = "Clearances";
				break;
			}
			case "GDGIS - Gas Service Lines": {
				EnterSelectLayer.sendKeys("GDGIS - Gas Services");
				searchInput = "GDGIS - Gas Service";
				break;
			}
			
			default:
				EnterSelectLayer.sendKeys(testData.get("LayerName").trim());
				searchInput = testData.get("LayerName").trim();
				break;
			}
			
			actions.Wait(Configutil.low);

			
			
			try {
				driver.findElement(By.xpath("//div[contains(@class,'react-select__option')][text()='"+searchInput+"']")).click();
				 actions.Wait(Configutil.low);
				ExecutionReport.logMessage(report, Status.PASS, "Select Main Layer from dropdown",
						" Main Layer from dropdown selected successfully");
			} catch (Exception e) {
				
				  Actions action = new Actions(driver);
				  
				  
				  action.sendKeys(Keys.UP).build().perform();
				  action.release().build().perform(); actions.Wait(Configutil.low);
				  
				  action.sendKeys(Keys.ENTER).build().perform();
				  action.release().build().perform(); actions.Wait(Configutil.low);
				  
					ExecutionReport.logMessage(report, Status.WARNING, "Select Main Layer from dropdown",
							"Main Layer from dropdown selected successfully.Actual match not found selected nearest match");
				 
			}

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Select Main Layer from dropdown",
					" Main Layer from dropdown selected successfully");

		}
	}

	public void QuickSearchFillDetailsAndSearch(Hashtable<String, String> testData, ExtentTest report,
			String SearchCriteria) throws Exception {
		try {

			SearchText.sendKeys(SearchCriteria);
			actions.Wait(Configutil.low);

			ExecutionReport.logMessage(report, Status.PASS, "Enter Search Criteria",
					"Enter Search Criteria has been entered successfully");

			searchButton.click();
			actions.Wait(Configutil.high);

			try {
				if (SelectFirstResult.isDisplayed()) {
					ExecutionReport.logMessage(report, Status.PASS, "Click on Search Button",
							" Search Button is clicked successfully");

				} 
			} catch (Exception e) {

				
				 actions.Wait(Configutil.medium);
				 

				if (SelectFirstResult.isDisplayed()) {
					ExecutionReport.logMessage(report, Status.PASS, "Clicking on Search Button again",
							" Search Result is getting displayed");

				} else {

					searchButton.click();
					actions.Wait(Configutil.medium);
					actions.Wait(Configutil.high);

					if (SelectFirstResult.isDisplayed()) {
						ExecutionReport.logMessage(report, Status.PASS, "Clicking on Search Button again",
								" Search Result is getting displayed");

					} else {

						ExecutionReport.logMessage(report, Status.FAIL, "Click on Search Button",
								"Search Result is not getting displayed");
						throw new Exception("Search Result is not getting displayed");
					}
				}

			}

		} catch (Exception e) {
			ExecutionReport.logMessage(report, Status.FAIL, "Click on Search Button", e.getMessage());

		}
	}

	public void LayerValidationFillDetailsAndSearch(Hashtable<String, String> testData, ExtentTest report, String locX,
			String locY) throws Exception {
		try {
			int n = 0;
			LongitudeX.clear();
			LongitudeX.sendKeys(locX);
			actions.Wait(Configutil.low);
			LatitudeY.clear();
			LatitudeY.sendKeys(locY);
			actions.Wait(Configutil.low);

			ExecutionReport.logMessage(report, Status.PASS, "Enter Search Criteria",
					"Enter Search Criteria has been entered successfully");

			btnLocate.click();
			actions.Wait(Configutil.medium);

			try {
				if (LatLonOutside.isDisplayed()) {
					btnYes.click();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				if (ZoomToLoc.isDisplayed()) {
					ExecutionReport.logMessage(report, Status.PASS, "Click on Locate Button",
							" Locate Button is clicked successfully");

				} else {
					ExecutionReport.logMessage(report, Status.FAIL, "Click on Locate Button",
							" Locate Button is clicked successfully");

				}
			} catch (Exception e) {

				btnLocate.click();
				actions.Wait(Configutil.medium);

				if (ZoomToLoc.isDisplayed()) {
					ExecutionReport.logMessage(report, Status.PASS, "Clicking on Locate Button again",
							" Locate Marker is getting displayed");

				} else {
					ExecutionReport.logMessage(report, Status.FAIL, "Clicking on Locate Button again",
							" Locate Result is getting displayed");
					throw new Exception("Locate Marker is not getting displayed");
				}

			}

			switch ((testData.get("LayerName"))) {
			case "Gas Pressure-Mains(Large Scale)":
				n = 4;
				break;
			case "GDGIS - Feature Annotations":
				n = 8;
				break;

			default:
				n = 5;
				break;
			}
			
			for (int i = 0; i < n; i++) {
				ZoomToLoc.click();
				actions.Wait(Configutil.low);

			}
			ClosePopUp.click();
			actions.Wait(Configutil.low);

		} catch (Exception e) {
			ExecutionReport.logMessage(report, Status.FAIL, "Click on Locate Button", e.getMessage());

		}
	}

	public void QuickSearchSelectResultAndValidate(Hashtable<String, String> testData, ExtentTest report)
			throws Exception {
		try {

			SelectFirstResult.click();
			actions.Wait(Configutil.low);

			ExecutionReport.logMessage(report, Status.PASS, "Select Result", "Result has been selected successfully");

			closeRightPanel.click();
			actions.Wait(Configutil.low);

			// closeBottomDrawer.click();
			// actions.Wait(Configutil.low);

			closeLeftPanel.click();
			actions.Wait(Configutil.low);

			ExecutionReport.logMessage(report, Status.PASS, "Close All Panel", "All panel Closed successfully");

			canvas.click();
			// ClickOnImage(testData, report);
			actions.Wait(Configutil.high);

			try {
				canvas.click();
				actions.Wait(Configutil.high);

				if (popupDailogBox.isDisplayed()) {
					ExecutionReport.logMessage(report, Status.PASS, "Click on the icon and validate info window",
							"Icon clicked successfully and info window validated successfully");

				} else {
					ExecutionReport.logMessage(report, Status.WARNING, "Click on the icon and validate info window",
							"Icon clicked successfully and info window validation failed");

				}

			} catch (Exception e) {

				ClickOnCenter(testData, report);
				actions.Wait(Configutil.high);

				if (popupDailogBox.isDisplayed()) {
					ExecutionReport.logMessage(report, Status.PASS, "Click on the icon and validate info window",
							"Icon clicked successfully and info window validated successfully");

				} else {
					ExecutionReport.logMessage(report, Status.WARNING, "Click on the icon and validate info window",
							"Icon clicked successfully and info window validation failed");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.WARNING, "Click on the icon and validate info window",
					"Icon clicked successfully and info window validation failed");
		}
	}

	public void LayerValidateIcons(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {

			/*
			 * closeRightPanel.click(); actions.Wait(Configutil.low);
			 * 
			 * // closeBottomDrawer.click(); // actions.Wait(Configutil.low);
			 * 
			 * closeLeftPanel.click(); actions.Wait(Configutil.low);
			 * 
			 * ExecutionReport.logMessage(report, Status.PASS, "Close All Panel",
			 * "All panel Closed successfully");
			 */
			// canvas.click();
			// ClickOnImage(testData, report);
			actions.Wait(Configutil.medium);

			try {
				canvas.click();
				actions.Wait(Configutil.high);

				if (popupDailogBox.isDisplayed()) {
					ExecutionReport.logMessage(report, Status.PASS, "Click on the icon and validate info window",
							"Icon clicked successfully and info window validated successfully");

				} else {
					ExecutionReport.logMessage(report, Status.FAIL, "Click on the icon and validate info window",
							"Icon clicked successfully and info window validated successfully");

				}

			} catch (Exception e) {

				// ClickOnCenter(testData, report);
				ClickOnMarker(testData, report);
				actions.Wait(Configutil.medium);

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
			ExecutionReport.logMessage(report, Status.FAIL, "Click on the icon and validate info window",
					"Failed : Icon clicked successfully and info window validated successfully");

		}
	}

	public void ClickOnImage(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {

			String imageRepo = System.getProperty("user.dir") + "\\imageRepository\\";
			Screen s = new Screen();
			try {
				Pattern AddFilterCriteria = new Pattern(imageRepo + testData.get("LayerName") + ".png");
				s.find(AddFilterCriteria);
				s.click(AddFilterCriteria);
			} catch (Exception e) {
				ExecutionReport.logMessage(report, Status.FAIL, "Click on the icon and validate info window",
						e.getMessage());

			}

			actions.Wait(Configutil.low);

			if (popupDailogBox.isDisplayed()) {
				ExecutionReport.logMessage(report, Status.PASS, "Click on the icon and validate info window",
						"Icon clicked successfully and info window validated successfully");

			} else {
				ExecutionReport.logMessage(report, Status.FAIL, "Click on the icon and validate info window",
						"Icon clicked successfully and info window validated successfully");

			}

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Click on the icon and validate info window",
					"Icon clicked successfully and info window validated successfully");

		}
	}

	public void ClickOnCenter(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {

			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int width = (int) (screenSize.getWidth() / 2);
			int height = (int) (screenSize.getHeight() / 2);
			actions.Wait(Configutil.low);
			Robot r = new Robot();
			r.mouseMove(width + 35, height - 70);
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

	public void ClickOnMarker(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {

			String imageRepo = System.getProperty("user.dir") + "\\imageRepository\\";
			Screen s = new Screen();
			try {
				Pattern AddFilterCriteria = new Pattern(imageRepo + "GeocodeMarker.png");
				s.find(AddFilterCriteria);
				s.click(AddFilterCriteria);
			} catch (Exception e) {
				ExecutionReport.logMessage(report, Status.FAIL, "Click on the icon and validate info window",
						e.getMessage());

			}

			actions.Wait(Configutil.low);

			if (popupDailogBox.isDisplayed()) {
				ExecutionReport.logMessage(report, Status.PASS, "Click on the icon and validate info window",
						"Icon clicked successfully and info window validated successfully");

			} else {
				ExecutionReport.logMessage(report, Status.FAIL, "Click on the icon and validate info window",
						"Icon clicked successfully and info window validated successfully");

			}

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Click on the icon and validate info window",
					"Icon clicked successfully and info window validated successfully");

		}
	}

	public void ValidateImageFromLegend(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {

			iconLegend.click();
			actions.Wait(Configutil.medium);

			ExecutionReport.logMessage(report, Status.PASS, "Click on the icon of legend tool",
					"Legend tool is successfully opened");

			try {
				imageProp.isDisplayed();
				if (imageProp.getAttribute("href").toLowerCase()
						.contains(testData.get("SearchCriteria1").toLowerCase())) {
					ExecutionReport.logMessage(report, Status.PASS,
							"Validate the Icon properties contains :" + testData.get("SearchCriteria1"),
							"Icon validated successfully with :" + imageProp.getAttribute("href"));

				} else {
					ExecutionReport.logMessage(report, Status.FAIL,
							"Validate the Icon properties contains :" + testData.get("SearchCriteria1"),
							"Unable to validate Icon :" + imageProp.getAttribute("href"));

				}

			} catch (Exception e) {

				imageProp2.isDisplayed();

				if (imageProp2.getAttribute("src").toLowerCase()
						.contains(testData.get("SearchCriteria1").toLowerCase())) {
					ExecutionReport.logMessage(report, Status.PASS,
							"Validate the Icon properties contains :" + testData.get("SearchCriteria1"),
							"Icon validated successfully with :" + imageProp2.getAttribute("src"));

				} else {
					ExecutionReport.logMessage(report, Status.FAIL,
							"Validate the Icon properties contains :" + testData.get("SearchCriteria1"),
							"Unable to validate Icon :" + imageProp2.getAttribute("href"));

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Validate Image from legend tool", e.getMessage());

		}
	}

	public void ValidatePropFromLegend(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {

			iconLegend.click();
			actions.Wait(Configutil.medium);

			ExecutionReport.logMessage(report, Status.PASS, "Click on the icon of legend tool",
					"Legend tool is successfully opened");

			try {
				imageProp3.isDisplayed();
				if (imageProp3.getAttribute("fill").toLowerCase()
						.contains(testData.get("SearchCriteria1").toLowerCase())) {
					ExecutionReport.logMessage(report, Status.PASS,
							"Validate the Icon properties contains :" + testData.get("SearchCriteria1"),
							"Icon validated successfully with :" + imageProp3.getAttribute("fill"));

				} else {
					ExecutionReport.logMessage(report, Status.FAIL,
							"Validate the Icon properties contains :" + testData.get("SearchCriteria1"),
							"Unable to validate Icon :" + imageProp3.getAttribute("fill"));

				}

			} catch (Exception e) {

				ExecutionReport.logMessage(report, Status.FAIL,
						"Validate the Icon properties contains :" + testData.get("SearchCriteria1"),
						"Unable to validate Icon :" + imageProp3.getAttribute("fill"));

				/*
				 * imageProp2.isDisplayed();
				 * 
				 * if (imageProp2.getAttribute("src").toLowerCase()
				 * .contains(testData.get("SearchCriteria1").toLowerCase())) {
				 * ExecutionReport.logMessage(report, Status.PASS,
				 * "Validate the Icon properties contains :" + testData.get("SearchCriteria1"),
				 * "Icon validated successfully with :" + imageProp2.getAttribute("src"));
				 * 
				 * } else { ExecutionReport.logMessage(report, Status.FAIL,
				 * "Validate the Icon properties contains :" + testData.get("SearchCriteria1"),
				 * "Unable to validate Icon :" + imageProp2.getAttribute("href"));
				 * 
				 * }
				 */
			}

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Validate Image from legend tool", e.getMessage());

		}
	}

}
