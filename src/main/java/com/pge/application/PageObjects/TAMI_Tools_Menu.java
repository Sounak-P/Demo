package com.pge.application.PageObjects;

import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.python.modules.thread.thread;
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

public class TAMI_Tools_Menu extends BaseClass {

	public static ObjectRead objectRead;
	public static Elements actions;
	public static Logger logger;

	/******************************************************************************************************************/

	// @FindBy(xpath = "//div[@id='btmDrawer']/div")
	@FindBy(xpath = "(//*[@data-testid='ExpandCircleDownIcon'])[4]")
	WebElement expandBottomPanel;

	@FindBy(xpath = "//div[@class='tabTitle']")
	WebElement toolsTabTitle;

	@FindBy(xpath = "//img[@aria-label='Search']")
	WebElement btnSearch;

	@FindBy(xpath = "//span[normalize-space()='Search']")
	WebElement SearchHeader;

	@FindBy(xpath = "//span[contains(text(),'Attribute')]")
	WebElement tabAttribute;

	@FindBy(xpath = "//span[contains(text(),'X,Y')]")
	WebElement tabXY;

	@FindBy(xpath = "//input[@id='detailSearch']")
	WebElement detailSearchCheckBox;

	@FindBy(xpath = "//input[@id='quickSearch']")
	WebElement quickSearchCheckBox;

	@FindBy(xpath = "//*[contains(text(),'Select Layers')]/following-sibling::div/input")
	WebElement EnterSelectLayer;

	@FindBy(xpath = "//textarea[@id='filterCondition']")
	WebElement filterCondition;

	@FindBy(xpath = "//button[normalize-space()='Locate']")
	WebElement btnLocate;

	@FindBy(xpath = "//img[@aria-label='Profile']")
	WebElement btnProfile;

	@FindBy(xpath = "//input[@id='react-select-2-input']")
	WebElement SelectProfile;

	@FindBy(xpath = "//div[@id='react-select-2-listbox']/div/div[contains(text(),'Casual Markup')]")
	WebElement dropDownProfile;

	@FindBy(xpath = "//button[normalize-space()='View']")
	WebElement viewButton;

	@FindBy(xpath = "//*[@aria-label='Clearance Filter']")
	WebElement btnClearanceFilter;

	@FindBy(xpath = "//div[@class='filter-checkbox']/label/input")
	List<WebElement> checkBoxes;

	@FindBy(xpath = "//button[normalize-space()='Apply']")
	WebElement btnApply;

	@FindBy(xpath = "//img[@class='nav-zoom-in']")
	WebElement btnZoomIn;

	@FindBy(xpath = "//input[@id='chkClearanceFilterOverdue']")
	WebElement ClearanceFilterOverdue;

	@FindBy(xpath = "//span[normalize-space()='Street:']/following::div/input")
	WebElement inputStreet;

	@FindBy(xpath = "//span[normalize-space()='City:']/following::div/input")
	WebElement inputCity;

	@FindBy(xpath = "//span[normalize-space()='Zipcode:']/following::div/input")
	WebElement inputZipcode;

	@FindBy(xpath = "(//button[normalize-space()='Search'])[1]")
	WebElement btnStreetSearch;

	@FindBy(xpath = "//span[normalize-space()='Address Results']//following::div[@class='MuiDataGrid-cellContent']")
	WebElement StreetAddressResults;

	@FindBy(xpath = "//header[@class='esri-popup__header']")
	WebElement headerPopUp;

	@FindBy(xpath = "//span[contains(text(),'Mile Point')]")
	WebElement MilePoint;

	@FindBy(xpath = "//input[@id='routenumberText']")
	WebElement inputRouteNumber;

	@FindBy(xpath = "//input[@id='milepointText']")
	WebElement inputMilePoint;

	@FindBy(xpath = "(//div[@data-field='route'])[2]")
	WebElement selectRoute;

	@FindBy(xpath = "//span[contains(text(),'Plats')]")
	WebElement platTab;

	@FindBy(xpath = "//input[@id='Text1']")
	WebElement EnterPlatName;

	@FindBy(css = ".MuiDataGrid-cell--textLeft")
	WebElement selectPlatResult;

	@FindBy(xpath = "//img[@aria-label='Buffer']")
	WebElement btnBuffer;

	@FindBy(xpath = "//div[contains(text(),'Select')]/following-sibling::div")
	WebElement SelectDropDownBuffer;

	@FindBy(xpath = "//div[@id='react-select-2-option-0']")
	WebElement SelectDropDownBufferOption;

	@FindBy(xpath = "//input[@id='proximity_dist']")
	WebElement bufferWidth;

	@FindBy(xpath = "//input[@id='appliedlayerlist']")
	WebElement SelectionLayerClick;

	@FindBy(xpath = "//div[@class='contextOption'][contains(text(),'Selection for Buffer')]")
	WebElement SelectionForBuffer;

	@FindBy(css = ".MuiDataGrid-cell--textLeft")
	WebElement bufferResult;

	@FindBy(css = "[aria-label='Network Tracing']")
	WebElement NetworkTracing;

	@FindBy(id = "add-flag")
	WebElement AddSqueezePoint;

	@FindBy(id = "runTrace")
	WebElement runTrace;

	@FindBy(id = "add-flag1")
	WebElement btnClear;

	@FindBy(xpath = "//img[@class='nav-select']")
	WebElement navSelectZoomIn;

	@FindBy(xpath = "//img[@aria-label='Plume']")
	WebElement iconPlume;

	@FindBy(xpath = "//input[@id='windSpeedSelect']")
	WebElement WindSpeedSelect;

	@FindBy(xpath = "//input[@id='windDirectionSelect']")
	WebElement windDirection;

	@FindBy(xpath = "//input[@id='diameterSelect']")
	WebElement diameterSelect;

	@FindBy(xpath = "//input[@id='pressureSelect']")
	WebElement PressureSelect;

	@FindBy(xpath = "//input[@value='open']")
	WebElement TerrainSelectOpen;

	@FindBy(xpath = "//input[@value='urban']")
	WebElement TerrainSelectObstructed;

	@FindBy(xpath = "//button[normalize-space()='Generate']")
	WebElement btnGenerate;
	
	@FindBy(xpath="//span[@class='heading'][normalize-space()='Search']")
	WebElement headingSearch;
	
	@FindBy(xpath="//img[@aria-label='Settings']")
	WebElement OpenSettings;
	
	@FindBy(xpath="//input[@name='Feet']")
	WebElement checkBoxFeet;
	
	@FindBy(xpath="//input[@name='Mile']")
	WebElement checkboxMile;
	
	
	
	@FindBy(xpath="//*[contains(text(),'Measurement Tools')]")
	WebElement menuMeasurementTool;
	
	@FindBy(xpath="//*[contains(text(),'Measurement Tools')]/div/div/div[contains(text(),'Distance')]") 
	WebElement menuDistance;
	
	@FindBy(xpath="//*[contains(text(),'Measurement Tools')]/div/div/div[contains(text(),'Perimeter')]")
	WebElement menuPerimeter;
	
	@FindBy(xpath="//*[@aria-label='Heatmap']")	
	WebElement iconHeatmap;
	
	@FindBy(xpath="//div[contains(text(),'Select options')]/following-sibling::div")
	WebElement SelectOptions;
	
	@FindBy(xpath="//div[contains(@id,'react-select')][contains(text(),'Airports')]") 
	WebElement dropdownOptions;
	
	@FindBy(xpath="//input[@value='cluster']")	
	WebElement radioBtnCluster;
	
	@FindBy(xpath="//input[@value='heatmap']")	
	WebElement radioHeatMap;

	@FindBy(xpath="//button[normalize-space()='Reset']")
	WebElement btnReset;
	
	@FindBy(xpath="//*[@aria-label='Jobaid']")	
	WebElement JobAid;
	
	@FindBy(xpath="//*[@aria-label='Support']")	
	WebElement Support;
	
	@FindBy(xpath="//*[@aria-label='Percipitation']")	
	WebElement btnPrecipitation;
	
	
	@FindBy(xpath="//div[@id='flipbook']/div/span")
	WebElement dropdownPrecipitation;
	
	@FindBy(id="openPrecipitationView")	
	WebElement iframePrecipitation;

	@FindBy  (xpath="//ul[@id='ddlimg1_listbox']/li")	
	List<WebElement> dropdownList; 

	@FindBy(xpath="//*[@class='fb-loader']/following-sibling::img[1]")	
	WebElement imagePrecipitation;
	
	@FindBy(xpath="//input[@id='windSpeedSelect']")
	WebElement windSpeedDropDown;
	
	@FindBy(xpath="//li[@id='windSpeedSelect-option-2']")	
	WebElement windSpeedDropDownOption2;
	
	
	@FindBy(xpath="//input[@id='windDirectionSelect']")	
	WebElement windDirectionSelect;
	
	@FindBy(xpath="//li[@id='windDirectionSelect-option-1']")	
	WebElement windDirectionSelectOption2;
	//@FindBy(xpath="")	WebElement
	//@FindBy(xpath="")	WebElement

	/******************************************************************************************************************/

	public TAMI_Tools_Menu(WebDriver driver) throws Exception {
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
	public void funcExpandBottomPanel(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {

			expandBottomPanel.click();
			actions.Wait(Configutil.low);

			if (toolsTabTitle.isDisplayed()) {
				ExecutionReport.logMessage(report, Status.PASS, "Expand bottom Panel",
						"Bottom panel has been clicked successfully");

			} else {
				ExecutionReport.logMessage(report, Status.FAIL, "Expand bottom Panel",
						"Bottom panel has been clicked successfully");

			}

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Expand bottom Panel",
					"Bottom panel has been clicked successfully");

		}
	}

	public void funcOpenSearch(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {

			btnSearch.click();
			actions.Wait(Configutil.low);

			if (SearchHeader.isDisplayed()) {
				ExecutionReport.logMessage(report, Status.PASS, "Click Search Tool button",
						"Search Tool button has been clicked successfully");

			} else {
				ExecutionReport.logMessage(report, Status.FAIL, "Click Search Tool button",
						"Search Tool button has been clicked successfully");

			}

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Click Search Tool button",
					"Search Tool button has been clicked successfully");

		}
	}

	public void funcOpenProfile(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {

			String iniScale = driver.findElement(By.xpath("//*[@id='ScaleBar']//div[@class='esri-scale-bar__label'][2]")).getText();
			
			btnProfile.click();
			actions.Wait(Configutil.low);

			if (SelectProfile.isDisplayed()) {
				ExecutionReport.logMessage(report, Status.PASS, "Click Profile Tool button",
						"Profile Tool button has been clicked successfully");

			} else {
				ExecutionReport.logMessage(report, Status.FAIL, "Click Profile Tool button",
						"Profile Tool button has been clicked successfully");
			}

			SelectProfile.click();
			actions.Wait(Configutil.low);

			driver.findElement(By.xpath("//div[@id='react-select-2-listbox']/div/div[contains(text(),'"
					+ testData.get("SearchCriteria1") + "')]")).click();
			actions.Wait(Configutil.low);

			ExecutionReport.logMessage(report, Status.PASS, "Select existing profile from drop down menu",
					"Successfully selected drop down menu");

			viewButton.click();
			actions.Wait(Configutil.high);
				
			ExecutionReport.logMessage(report, Status.PASS, "Click View button",
					"View button has been clicked successfully");
			
			actions.Wait(Configutil.high);
			String fiScale = driver.findElement(By.xpath("//*[@id='ScaleBar']//div[@class='esri-scale-bar__label'][2]")).getText();

			
			if (headingSearch.isDisplayed() && !(iniScale.contentEquals(fiScale)) ) {
				actions.Wait(Configutil.high);
				ExecutionReport.logMessage(report, Status.PASS, "Validate Profile tool",
						"Profile tool validated successfully");
			} else {
				ExecutionReport.logMessage(report, Status.FAIL, "Validate Profile tool",
						"Profile tool validated successfully");
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Validate Profile tool",
					"Profile tool validated successfully");

		}
	}
	
	public void funcOpenDistanceUnit(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {

			OpenSettings.click();
			actions.Wait(Configutil.low);
			
			ExecutionReport.logMessage(report, Status.PASS, "Open Settings from tools button",
					"Settings tool opened successfully");
			
			
			checkBoxFeet.click();
			actions.Wait(Configutil.low);
			
			ExecutionReport.logMessage(report, Status.PASS, "Select feet checkbox",
					"Feet checkbox selected successfully");
			
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int width = (int) (screenSize.getWidth() / 2);
			int height = (int) (screenSize.getHeight() / 2);
			actions.Wait(Configutil.low);
			
			Robot r = new Robot();
			r.mouseMove(width - 100, height - 50);
			actions.Wait(Configutil.low);
			r.mousePress(InputEvent.BUTTON3_MASK);
			r.mouseRelease(InputEvent.BUTTON3_MASK);
			actions.Wait(Configutil.low);
			
			ExecutionReport.logMessage(report, Status.PASS, "Right Click on Map",
					"Right Click on Map is successful");
			
			Actions a = new Actions(driver);
			a.moveToElement(menuMeasurementTool).build().perform();
			actions.Wait(Configutil.low);
			
			ExecutionReport.logMessage(report, Status.PASS, "Click on the Measurement Tool",
					"Measurement Tool is successfully clicked");
			
			menuDistance.click();
			actions.Wait(Configutil.low);

			ExecutionReport.logMessage(report, Status.PASS, "Click on the menu Distance",
					"Menu Distance is successfully clicked");
			
			r.mouseMove(width, height);
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			actions.Wait(Configutil.high);

			r.mouseMove(width - 100, height - 100);
			actions.Wait(Configutil.low);
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			actions.Wait(Configutil.high);
			
			ExecutionReport.logMessage(report, Status.WARNING, "Plot the points in the Map and distance should be displayed betwwen the points",
					"Distance betwwen the ponts in Feet is displayed");
			
			checkboxMile.click();
			actions.Wait(Configutil.low);
			
			ExecutionReport.logMessage(report, Status.PASS, "Click on the Mile checkbox",
					"Mile checkbox is successfully clicked");
			
			r.mouseMove(width - 100, height - 50);
			actions.Wait(Configutil.low);
			r.mousePress(InputEvent.BUTTON3_MASK);
			r.mouseRelease(InputEvent.BUTTON3_MASK);
			actions.Wait(Configutil.low);
			ExecutionReport.logMessage(report, Status.PASS, "Right Click on Map",
					"Right Click on Map is successful");
						

			a.moveToElement(menuMeasurementTool).build().perform();			
			actions.Wait(Configutil.low);
			ExecutionReport.logMessage(report, Status.PASS, "Click on the Measurement Tool",
					"Measurement Tool is successfully clicked");
			
			menuPerimeter.click();
			actions.Wait(Configutil.low);
			ExecutionReport.logMessage(report, Status.PASS, "Click on the menu Perimeter",
					"Menu Perimeter is successfully clicked");

			r.mouseMove(width + 200, height - 50);
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			actions.Wait(Configutil.high);

			r.mouseMove(width + 200, height - 100);
			actions.Wait(Configutil.low);
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			
			r.mouseMove(width +300, height - 100);
			actions.Wait(Configutil.low);
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			actions.Wait(Configutil.high);
			ExecutionReport.logMessage(report, Status.WARNING, "Plot the points in the Map and perimeter should be displayed between the points",
					"Perimeter between the ponts in Feet is displayed");
			
			

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Validate Profile tool",
					"Profile tool validated successfully");

		}
	}
	
	public void funcOpenBufferToolValidate(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {

			btnBuffer.click();
			actions.Wait(Configutil.low);

			SelectDropDownBuffer.click();
			actions.Wait(Configutil.low);
			SelectDropDownBufferOption.click();
			actions.Wait(Configutil.low);

			ExecutionReport.logMessage(report, Status.PASS, "Select Buffer Layer",
					" Buffer Layer selected successfully : " + testData.get("LayerName").trim());

			bufferWidth.clear();
			bufferWidth.sendKeys("4");
			actions.Wait(Configutil.low);

			ExecutionReport.logMessage(report, Status.PASS, "Enter Buffer Width",
					" Buffer Width entered successfully : 4");

			SelectionLayerClick.click();
			actions.Wait(Configutil.low);

			driver.findElement(By.xpath("//ul[@class='optionContainerBuffer']//span[contains(text(),'"
					+ testData.get("SearchCriteria1") + "')]/preceding-sibling::input")).click();
			actions.Wait(Configutil.low);

			ExecutionReport.logMessage(report, Status.PASS, "Select Selection Layer",
					" Selection Layer selected successfully : " + testData.get("SearchCriteria1"));

			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int width = (int) (screenSize.getWidth() / 2);
			int height = (int) (screenSize.getHeight() / 2);
			actions.Wait(Configutil.low);

			Robot r = new Robot();
			r.mouseMove(width + 200, height);
			actions.Wait(Configutil.low);
			r.mousePress(InputEvent.BUTTON3_MASK);
			r.mouseRelease(InputEvent.BUTTON3_MASK);
			actions.Wait(Configutil.low);

			ExecutionReport.logMessage(report, Status.PASS, "Right Click on the Map", "Right Clicked Successfully");

			if (SelectionForBuffer.isDisplayed()) {
				SelectionForBuffer.click();
				actions.Wait(Configutil.high);
				ExecutionReport.logMessage(report, Status.PASS, "Click on the Selection For Buffer",
						"Selection For Buffer is clicked Successful");

			} else {

				ExecutionReport.logMessage(report, Status.FAIL, "Click on the Selection For Buffer",
						"Selection For Buffer is clicked Successful");
			}

			if (bufferResult.isDisplayed()) {
				bufferResult.click();
				actions.Wait(Configutil.medium);

				ExecutionReport.logMessage(report, Status.PASS, "Buffer Result should be displayed in Right Pane",
						"Select Buffer Result displayed in Right Pane is successful");
			} else {
				ExecutionReport.logMessage(report, Status.FAIL, "Buffer Result should be displayed in Right Pane",
						"Buffer Result is not displayed in Right Pane");
			}

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Buffer Result should be displayed in Right Pane",
					"Buffer Result is not displayed in Right Pane");

		}
	}

	public void funcOpenNetworkTracingToolValidate(Hashtable<String, String> testData, ExtentTest report)
			throws Exception {
		try {

			NetworkTracing.click();
			actions.Wait(Configutil.high);

			ExecutionReport.logMessage(report, Status.PASS, "Click on Network Tracing Button",
					"Network Tracing Button has been clicked successfully");

			AddSqueezePoint.click();
			actions.Wait(Configutil.medium);

			ExecutionReport.logMessage(report, Status.PASS, "Click on Add Squeeze Point",
					"Add Squeeze Point has been clicked successfully");

			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int width = (int) (screenSize.getWidth() / 2);
			int height = (int) (screenSize.getHeight() / 2);
			actions.Wait(Configutil.low);

			Robot r = new Robot();
			r.mouseMove(width - 100, height - 50);
			actions.Wait(Configutil.low);
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			actions.Wait(Configutil.high);

			r.mouseMove(width - 100, height - 30);
			actions.Wait(Configutil.low);
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			actions.Wait(Configutil.high);

			ExecutionReport.logMessage(report, Status.WARNING, "Squeeze Points should be added on Maps",
					"Squeeze Points added on Maps successfully");
			actions.Wait(Configutil.high);

			runTrace.click();
			actions.Wait(Configutil.high);

			ExecutionReport.logMessage(report, Status.PASS, "Click on Run Trace button",
					"Run Trace button clicked successfully");

			// Zoom in to Map

			navSelectZoomIn.click();
			actions.Wait(Configutil.low);

			r.mouseMove(width - 120, height - 60);
			actions.Wait(Configutil.low);
			// r.keyPress(KeyEvent.VK_SHIFT);
			r.mousePress(InputEvent.BUTTON1_MASK);
			Thread.sleep(1000);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			actions.Wait(Configutil.low);
			r.mouseMove(width - 80, height - 20);
			actions.Wait(Configutil.low);
			// r.keyRelease(KeyEvent.VK_SHIFT);
			r.mousePress(InputEvent.BUTTON1_MASK);
			Thread.sleep(1000);
			r.mouseRelease(InputEvent.BUTTON1_MASK);

			actions.Wait(Configutil.high);

			ExecutionReport.logMessage(report, Status.WARNING, "Zoom into Maps and Trace should be displayed",
					"Zoom into Maps is successful and trace is displayed");

			btnClear.click();
			actions.Wait(Configutil.medium);

			ExecutionReport.logMessage(report, Status.WARNING, "Click on Clear Button",
					"Clear Button is clicked successfully and Trace is cleared");

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Zoom into Maps and Trace should be displayed",
					"Zoom into Maps is successful and trace is displayed");

		}
	}

	public void funcOpenGasPlumeModelValidate(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {

			iconPlume.click();
			actions.Wait(Configutil.high);

			ExecutionReport.logMessage(report, Status.PASS, "Click on Gas Plume Button",
					"Gas Plume Button has been clicked successfully");

			actions.Wait(Configutil.high);

			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int width = (int) (screenSize.getWidth() / 2);
			int height = (int) (screenSize.getHeight() / 2);
			actions.Wait(Configutil.low);

			Robot r = new Robot();

			// Zoom in to Map

			navSelectZoomIn.click();
			actions.Wait(Configutil.low);

			r.mouseMove(width - 120, height - 60);
			actions.Wait(Configutil.low);
			// r.keyPress(KeyEvent.VK_SHIFT);
			r.mousePress(InputEvent.BUTTON1_MASK);
			Thread.sleep(1000);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			actions.Wait(Configutil.low);
			r.mouseMove(width - 118, height - 58);
			actions.Wait(Configutil.low);
			// r.keyRelease(KeyEvent.VK_SHIFT);
			r.mousePress(InputEvent.BUTTON1_MASK);
			Thread.sleep(1000);
			r.mouseRelease(InputEvent.BUTTON1_MASK);

			actions.Wait(Configutil.high);

			ExecutionReport.logMessage(report, Status.PASS, "Zoom into Map", "Zoom into Map is successful");

			// Pattern

			String imageRepo = System.getProperty("user.dir") + "\\imageRepository\\";
			Screen s = new Screen();

			Pattern AddFilterCriteria = new Pattern(imageRepo + testData.get("SearchCriteria1") + ".png");
			// s.mouseMove(AddFilterCriteria);
			s.click(AddFilterCriteria);
			actions.Wait(Configutil.medium);
			windSpeedDropDown.click();
			actions.Wait(Configutil.low);
			windSpeedDropDownOption2.click();
			actions.Wait(Configutil.low);
			windDirectionSelect.click();
			actions.Wait(Configutil.low);
			windDirectionSelectOption2.click();		
			actions.Wait(Configutil.low);
			ExecutionReport.logMessage(report, Status.PASS, "Click on Gas Pipeline",
					"Gas Pipeline is successful clicked");
			actions.Wait(Configutil.high);
			btnGenerate.click();
			actions.Wait(Configutil.high);

			ExecutionReport.logMessage(report, Status.WARNING, "Click on Generate Button",
					"Generate Button is clicked successfully and Trace is displayed");

			TerrainSelectObstructed.click();
			actions.Wait(Configutil.high);
			btnGenerate.click();
			actions.Wait(Configutil.high);

			ExecutionReport.logMessage(report, Status.WARNING, "Select Obstructed and Click on Generate Button",
					"Generate Button is clicked successfully and Trace is displayed");

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Select Obstructed and Click on Generate Button",
					"Generate Button is clicked successfully and Trace is displayed");

		}
	}

	public void funcOpenAttribute(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {

			tabAttribute.click();
			actions.Wait(Configutil.low);

			if (detailSearchCheckBox.isDisplayed() && quickSearchCheckBox.isDisplayed()) {
				ExecutionReport.logMessage(report, Status.PASS, "Click on Attribute Tab",
						"Attribute Tab has been clicked successfully");

			} else {
				ExecutionReport.logMessage(report, Status.FAIL, "Click on Attribute Tab",
						"Attribute Tab has been clicked successfully");

			}

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Click on Attribute Tab",
					"Attribute Tab has been clicked successfully");

		}
	}

	public void funcOpenXY(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {

			tabXY.click();
			actions.Wait(Configutil.low);

			if (btnLocate.isDisplayed()) {
				ExecutionReport.logMessage(report, Status.PASS, "Click on XY Tab",
						"XY Tab has been clicked successfully");

			} else {
				ExecutionReport.logMessage(report, Status.FAIL, "Click on XY Tab",
						"XY Tab has been clicked successfully");

			}

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Click on XY Tab", "XY Tab has been clicked successfully");

		}
	}

	public void funcStreetSearch(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {

			inputCity.sendKeys(testData.get("SearchCriteria1"));
			actions.Wait(Configutil.low);

			ExecutionReport.logMessage(report, Status.PASS, "Enter City", "City entered successfully");

			inputStreet.sendKeys(testData.get("SelectField"));
			actions.Wait(Configutil.low);

			ExecutionReport.logMessage(report, Status.PASS, "Enter Street", "Street entered successfully");

			inputZipcode.sendKeys(testData.get("Condition"));
			actions.Wait(Configutil.low);

			ExecutionReport.logMessage(report, Status.PASS, "Enter ZipCode", "ZipCode entered successfully");

			btnStreetSearch.click();
			actions.Wait(Configutil.medium);

			ExecutionReport.logMessage(report, Status.PASS, "Click on Search Button",
					"Search Button clicked successfully");

			StreetAddressResults.click();
			actions.Wait(Configutil.medium);

			ExecutionReport.logMessage(report, Status.PASS, "Select Result from Address Results",
					"Address Results selected successfully");

			if (headerPopUp.isDisplayed()) {
				ExecutionReport.logMessage(report, Status.PASS, "Verify Street Search",
						"Street Search verified successfully");

			} else {
				ExecutionReport.logMessage(report, Status.FAIL, "Verify Street Search",
						"Street Search verified successfully");

			}

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Click on XY Tab", "XY Tab has been clicked successfully");

		}
	}

	public void funcRouteMilePointSearch(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {

			MilePoint.click();
			actions.Wait(Configutil.low);

			inputRouteNumber.sendKeys(testData.get("SearchCriteria1"));
			actions.Wait(Configutil.low);

			ExecutionReport.logMessage(report, Status.PASS, "Enter Route Number", "Route Number entered successfully");

			inputMilePoint.sendKeys(testData.get("SelectField"));
			actions.Wait(Configutil.low);

			ExecutionReport.logMessage(report, Status.PASS, "Enter Mile Point", "Mile Point entered successfully");

			btnLocate.click();
			actions.Wait(Configutil.high);

			ExecutionReport.logMessage(report, Status.PASS, "Click on Locate Button",
					"Locate Button clicked successfully");

			selectRoute.click();
			actions.Wait(Configutil.medium);

			ExecutionReport.logMessage(report, Status.PASS, "Select Route from Results",
					"Route Results selected successfully");

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Click on XY Tab", "XY Tab has been clicked successfully");

		}
	}

	public void funcPlatSearch(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {

			platTab.click();
			actions.Wait(Configutil.low);

			EnterPlatName.sendKeys(testData.get("SearchCriteria1"));
			actions.Wait(Configutil.low);

			ExecutionReport.logMessage(report, Status.PASS, "Enter Plat Name", "Plat Name entered successfully");

			btnStreetSearch.click();
			actions.Wait(Configutil.high);

			ExecutionReport.logMessage(report, Status.PASS, "Click on Search Button",
					"Search Button clicked successfully");

			selectPlatResult.click();
			actions.Wait(Configutil.medium);

			ExecutionReport.logMessage(report, Status.PASS, "Select Plat from Results",
					"Plat Results selected successfully");

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Select Plat from Results",
					"Plat Results selected successfully");

		}
	}

	public void funcClearanceFilter(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {

			btnZoomIn.click();
			actions.Wait(Configutil.low);

			btnZoomIn.click();
			actions.Wait(Configutil.low);

			btnClearanceFilter.click();
			actions.Wait(Configutil.low);

			ExecutionReport.logMessage(report, Status.PASS, "Click on 'Clearance icon'",
					"'Clearance icon' Tab has been clicked successfully");

			// Deselect All
			for (Iterator iterator = checkBoxes.iterator(); iterator.hasNext();) {
				WebElement ele = (WebElement) iterator.next();

				if (ele.isSelected()) {
					ele.click();

				}
			}

			btnApply.click();
			actions.Wait(Configutil.medium);

			ExecutionReport.logMessage(report, Status.PASS, "Click on Apply button",
					"Apply button clicked successfully");

			int position = 1;

			// select one by one
			for (Iterator iterator2 = checkBoxes.iterator(); iterator2.hasNext();) {
				WebElement ele2 = (WebElement) iterator2.next();

				ele2.click();
				actions.Wait(Configutil.low);

				String label = driver
						.findElement(By.xpath("(//div[@class='filter-checkbox']/label/span)[" + position + "]"))
						.getText();

				ExecutionReport.logMessage(report, Status.PASS, "Select Clearance Filter :" + label,
						"Successfully selected Clearance Filter :" + label);
				actions.Wait(Configutil.medium);

				btnApply.click();
				actions.Wait(Configutil.medium);

				ExecutionReport.logMessage(report, Status.PASS, "Validate Icons :" + label,
						"Validate correct Icons for :" + label);

				ele2.click();
				actions.Wait(Configutil.low);

				position++;

			}

			ClearanceFilterOverdue.click();
			actions.Wait(Configutil.medium);

			ExecutionReport.logMessage(report, Status.PASS, "Validate Icons : Overdue ",
					"Validate correct Icons for : Overdue");

			/*
			 * ClearanceFilterOverdue.click(); actions.Wait(Configutil.medium);
			 */

			/*
			 * if (btnLocate.isDisplayed() ) { ExecutionReport.logMessage(report,
			 * Status.PASS, "Click on XY Tab", "XY Tab has been clicked successfully");
			 * 
			 * } else { ExecutionReport.logMessage(report, Status.FAIL, "Click on XY Tab",
			 * "XY Tab has been clicked successfully");
			 * 
			 * }
			 */

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Click on XY Tab", "XY Tab has been clicked successfully");

		}
	}

	public void openDetailSearch(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {

			detailSearchCheckBox.click();
			actions.Wait(Configutil.low);

			if (EnterSelectLayer.isDisplayed()) {
				ExecutionReport.logMessage(report, Status.PASS, "Click Detail Search Radio Botton",
						"Detail Search Radio Botton has been clicked successfully");

			} else {
				ExecutionReport.logMessage(report, Status.FAIL, "Click Detail Search Radio Botton",
						"Detail Search Radio Botton has been clicked successfully");

			}

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Click on Attribute Tab",
					"Attribute Tab has been clicked successfully");

		}
	}

	public void openQuickSearch(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {

			quickSearchCheckBox.click();
			actions.Wait(Configutil.low);

			ExecutionReport.logMessage(report, Status.PASS, "Click Quick Search Radio Botton",
					"Quick Search Radio Botton has been clicked successfully");

			/*
			 * if (EnterSelectLayer.isDisplayed()) {
			 * 
			 * } else { ExecutionReport.logMessage(report, Status.FAIL,
			 * "Click Quick Search Radio Botton",
			 * "Quick Search Radio Botton has been clicked successfully");
			 * 
			 * }
			 */

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Click Quick Search Radio Botton",
					"Quick Search Radio Botton has been clicked successfully");

		}
	}
	
	public void openJobAidValidate(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {
			String titleChild = null;
			String parentTitle=driver.getTitle();
			
			JobAid.click();
			actions.Wait(Configutil.low);

			ExecutionReport.logMessage(report, Status.PASS, "Click Job Aid Botton",
					"Job Aid Botton has been clicked successfully");

			
			Set<String> allWin =driver.getWindowHandles();
			
			for (String string : allWin) {
				titleChild = driver.switchTo().window(string).getTitle();
			}
			
			if (!parentTitle.contains(titleChild)) {
				ExecutionReport.logMessage(report, Status.PASS, "Job Aid page should open in new window",
						"Job aid page opened successfully");
			} else {
				ExecutionReport.logMessage(report, Status.FAIL, "Job Aid page should open in new window",
						"Job aid page opened successfully");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Job Aid page should open in new window",
					"Job aid page opened successfully");

		}
	}
	
	public void openPrecipitationValidate(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {
			btnPrecipitation.click();
			actions.Wait(Configutil.low);

			ExecutionReport.logMessage(report, Status.PASS, "Click Precipitation Botton",
					"Precipitation Botton has been clicked successfully");
		
			
			driver.switchTo().frame(iframePrecipitation);
			actions.Wait(Configutil.low);
			
			//Select tagS = new Select(driver.findElement(By.xpath("//select[@id='ddlimg1']")));
			for (WebElement ele : dropdownList) {
				
				String iniSource = imagePrecipitation.getAttribute("src");
				
				dropdownPrecipitation.click();
				actions.Wait(Configutil.low);
				ExecutionReport.logMessage(report, Status.PASS, "Click on dropdown Botton to expand",
						"DropDown Botton has been clicked successfully");
				
				ele.click();
				actions.Wait(Configutil.medium);
				
				String fiSource = imagePrecipitation.getAttribute("src");
				
				if (!iniSource.contentEquals(fiSource)) {
					ExecutionReport.logMessage(report, Status.PASS, "Select :"+ ele.getText() +" from the dropdown",
							"Successfully selected :"+ ele.getText() +" from the dropdown");
				} else {
					ExecutionReport.logMessage(report, Status.FAIL, "Select :"+ ele.getText() +" from the dropdown",
							"Successfully selected :"+ ele.getText() +" from the dropdown");
				}

				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Precipation validation",
					"Precipation validation failed");

		}
	}
	
	public void openSupportValidate(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {
			String titleChild = null;
			String parentTitle=driver.getTitle();
			
			Support.click();
			actions.Wait(Configutil.low);

			ExecutionReport.logMessage(report, Status.PASS, "Click Support Botton",
					"Support Botton has been clicked successfully");

			
			Set<String> allWin =driver.getWindowHandles();
			
			for (String string : allWin) {
				titleChild = driver.switchTo().window(string).getTitle();
			}
			
			if (titleChild.contains("Support")) {
				ExecutionReport.logMessage(report, Status.PASS, "Support page should open in new window",
						"Support page opened successfully");
			} else {
				ExecutionReport.logMessage(report, Status.FAIL, "Support Aid page should open in new window",
						"Support page opened successfully");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Support page should open in new window",
					"Support page opened successfully");

		}
	}
	
	public void HeatMapValidation(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		try {

			iconHeatmap.click();
			actions.Wait(Configutil.low);
			
			ExecutionReport.logMessage(report, Status.PASS, "Click on Icon Heat Map",
					"Icon Heat Map clicked successfully");
			
			radioBtnCluster.click();
			actions.Wait(Configutil.low);
			
			ExecutionReport.logMessage(report, Status.PASS, "Click Cluster Radio button",
					"Cluster Radio button clicked successfully");
			
			SelectOptions.click();
			actions.Wait(Configutil.low);
			
			ExecutionReport.logMessage(report, Status.PASS, "Select Layer from the dropdown",
					"List of available dropdown is displayed");
			
			driver.findElement(By.xpath("//div[contains(@id,'react-select')][contains(text(),'"+ testData.get("LayerName").trim()+ "')]")).click();
			actions.Wait(Configutil.low);
			
			ExecutionReport.logMessage(report, Status.PASS, "Select Layer from the dropdown :" +testData.get("LayerName"),
					"Successfully selected :" +testData.get("LayerName")+" from the dropdown");
			
			btnApply.click();
			actions.Wait(Configutil.high);
			
			ExecutionReport.logMessage(report, Status.WARNING, "Click Apply button",
					"Apply button  clicked successfully and Cluster is displayed in the map");
			
			btnReset.click();
			actions.Wait(Configutil.low);
			
			ExecutionReport.logMessage(report, Status.WARNING, "Click Reset button",
					"Reset button clicked successfully and Cluster is removed from the map");
			
			radioHeatMap.click();
			actions.Wait(Configutil.low);
			
			ExecutionReport.logMessage(report, Status.PASS, "Click HeatMap Radio button",
					"HeatMap Radio button clicked successfully");
			
			btnApply.click();
			actions.Wait(Configutil.high);
			
	
			ExecutionReport.logMessage(report, Status.WARNING, "Click Apply button",
					"Apply button  clicked successfully and HeatMap is displayed in the map");
			
			

		} catch (Exception e) {
			e.printStackTrace();
			ExecutionReport.logMessage(report, Status.FAIL, "Click Apply button",
					"Apply button clicked unsuccessfully and HeatMap is not displayed in the map");
			
		}
	}

}
