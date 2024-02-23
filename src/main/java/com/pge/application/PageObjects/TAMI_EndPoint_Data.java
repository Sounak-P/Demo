package com.pge.application.PageObjects;

import java.util.HashMap;
import java.util.Hashtable;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.automation.framework.action.action_keywords;
import com.automation.framework.config.Configutil;
import com.automation.framework.core.BaseClass;
import com.automation.framework.interfaces.Elements;
import com.automation.framework.utilities.ObjectRead;
import com.automation.framework.utilities.Status;
import com.automation.framework.utilities.ExecutionReport;
import com.aventstack.extentreports.ExtentTest;

public class TAMI_EndPoint_Data extends BaseClass {

	public static ObjectRead objectRead;
	public static Elements actions;
	public static Logger logger;

	/******************************************************************************************************************/
	@FindBy(xpath = "//input[@id='detailSearch']")
	WebElement detailSearchCheckBox;

	@FindBy(xpath = "//input[@id='where' or @name='where']")
	WebElement QueryWhereField;

	@FindBy(xpath = "//input[@id='outFields' or @name='outFields' ]")
	WebElement QueryOutFields;
	
	@FindBy(xpath = "//input[@id='outSR' or @name='outSR' ]")
	WebElement QueryOutputSpatial;

	@FindBy(xpath = "//input[@value='Query (GET)']")
	WebElement QueryGETButton;

	@FindBy(xpath = "//div[@class='rbody']//i[1]")
	WebElement textBody;

	@FindBy(css = "body > div.restBody > i")
	WebElement alternateTextBody;

	/******************************************************************************************************************/

	public TAMI_EndPoint_Data(WebDriver driver) throws Exception {
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
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> RetriveData(Hashtable<String, String> testData, ExtentTest report) throws Exception {

		HashMap<String, String> hm = new HashMap<String, String>();
		WebElement records = null;
		String completeInfo = null;
		String originalWindow = driver.getWindowHandle();

		try {

			
			driver.switchTo().newWindow(WindowType.TAB);
			actions.Wait(Configutil.low);

			for (String windowHandle : driver.getWindowHandles()) {
				if (!originalWindow.contentEquals(windowHandle)) {
					driver.switchTo().window(windowHandle);
					break;
				}
			}
			
			//System.out.println(testData.get("EndPoint"));
//			
//			if (testData.get("EndPoint") != null) {
//				ExecutionReport.logMessage(report, Status.FAIL, "Skip 'Get Query' Click. Direct URL to retrived Data",
//						"Direct URL retrived Data is successfully");
//				throw new Exception("Endpoint is not provided");
//			}
			
			driver.navigate().to(testData.get("EndPoint"));
			actions.Wait(Configutil.high);

			if (testData.get("EndPointQueryWhere").isEmpty()) {
				ExecutionReport.logMessage(report, Status.PASS, "Skip 'Get Query' Click. Direct URL to retrived Data",
						"Direct URL retrived Data is successfully");
				

				if (!QueryOutFields.getAttribute("value").contains("*")) {
					QueryOutFields.sendKeys("*");

				}

			} else {

				QueryWhereField.sendKeys(testData.get("EndPointQueryWhere"));

				QueryOutFields.sendKeys("*");
			}

			try {
				QueryGETButton.click();
				actions.Wait(Configutil.low);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				textBody.isDisplayed();
				records = textBody;
			} catch (Exception e) {
				// TODO: handle exception

				try {
					alternateTextBody.isDisplayed();
					records = alternateTextBody;

				} catch (Exception e2) {
					// TODO: handle exception
					ExecutionReport.logMessage(report, Status.FAIL, "Retrieve Data EndPoint",
							"Retrieve data from endpoint failed. Error message : " + e2.getMessage());
					throw new Exception("Unable to retrive text from the endpoint");
				}
			}

			switch (records.getAttribute("textContent")) {
			case "No records found.":
			case "No results found.":

				ExecutionReport.logMessage(report, Status.WARNING, "Retrieve Data EndPoint",
						"Endpoint error message : " + records.getAttribute("textContent"));

				break;
				
			default:

				// code for default case

				// driverHeadless.findElement(By.xpath("//div[@class='rbody']//i[text()='OBJECTID:
				// ']//following::a[1]")).click();

				try {

					String completeInfoInitial = (driver.findElement(By.xpath("//*[@class='rbody']"))
							.getAttribute("textContent"));

					/*
					 * String[] completeInfoFinal= completeInfoInitial.split("# records:");
					 * 
					 * completeInfo = completeInfoFinal[1];
					 */

					System.out.println(completeInfoInitial);

					String[] iniDetails = completeInfoInitial.split("\\r?\\n");
					
					System.out.println(iniDetails);

					for (int i = 0; i < iniDetails.length; i++) {

						if (iniDetails[i].contains(":")
								&& (iniDetails[i].contains(testData.get("EndPointSearchCriteria")))) {
							String finalDetails[] = iniDetails[i].split(":", 2);

							if (finalDetails[0].trim().contentEquals(testData.get("EndPointSearchCriteria"))) {
								hm.put(finalDetails[0].trim(), (finalDetails[1]).trim());
								break;
							}

						}

					}

					if (hm.isEmpty()) {
						ExecutionReport.logMessage(report, Status.PASS, "Retrieve Data EndPoint",
								"Retrieve data from endpoint server is unsuccessful");

						throw new Exception(
								"Retrieve data from endpoint server is unsuccessful as SearchText not found");
					}

				} catch (Exception e) {

					completeInfo = driver.findElement(By.xpath("//table[@class='ftrTable']"))
							.getAttribute("textContent");

					System.out.println(completeInfo);

					String[] iniDetails = completeInfo.split("\\r?\\n");

					for (int i = 0; i < iniDetails.length; i++) {

						if (iniDetails[i].contains(":") && (iniDetails[i].contains(testData.get("EndPointSearchCriteria")))) {
							String finalDetails[] = iniDetails[i].split(":", 2);

							if (finalDetails[0].trim().contentEquals(testData.get("EndPointSearchCriteria"))) {
								hm.put(finalDetails[0].trim(), (finalDetails[1]).trim());
								break;
							}

						}
						// test.createNode("Retrieve Data EndPoint").fail("Retrieve data from endpoint
						// server is unsuccessful");

					}

					// COCDriverScript.logMessage("Retrieve Data EndPoint", "Retrieve data from
					// endpoint server is successful", Status.PASS);
					ExecutionReport.logMessage(report, Status.PASS, "Retrieve Data EndPoint",
							"Retrieve data from endpoint server is successful");

					//driver.close();
					driver.switchTo().window(originalWindow);
					break;
				}
				driver.switchTo().window(originalWindow);
				//driver.close();
			}

			// ExecutionReport.logMessage(report, Status.PASS, "", "");

		} catch (Exception e) {

			//String errorMsg = driver.findElement(By.xpath("//body/div[1]/div")).getAttribute("innerText");
			ExecutionReport.logMessage(report, Status.FAIL, "Retrieve Data EndPoint",
					"Retrieve data from endpoint failed. Error message : " + e.getMessage());
			throw new Exception("Retrieve data from endpoint server is unsuccessful");

		}

		return hm;
	}
	
	
	public HashMap<String, String> RetriveLoc(Hashtable<String, String> testData, ExtentTest report) throws Exception {

		HashMap<String, String> hm = new HashMap<String, String>();
		WebElement records = null;
		String completeInfo = null;
		String originalWindow = driver.getWindowHandle();

		try {

			
			driver.switchTo().newWindow(WindowType.TAB);
			actions.Wait(Configutil.low);

			for (String windowHandle : driver.getWindowHandles()) {
				if (!originalWindow.contentEquals(windowHandle)) {
					driver.switchTo().window(windowHandle);
					break;
				}
			}
			
			//System.out.println(testData.get("EndPoint"));
//			
//			if (testData.get("EndPoint") != null) {
//				ExecutionReport.logMessage(report, Status.FAIL, "Skip 'Get Query' Click. Direct URL to retrived Data",
//						"Direct URL retrived Data is successfully");
//				throw new Exception("Endpoint is not provided");
//			}
			
			driver.navigate().to(testData.get("EndPoint"));
			actions.Wait(Configutil.high);

			if (testData.get("EndPointQueryWhere").isEmpty()) {
				ExecutionReport.logMessage(report, Status.PASS, "Skip 'Get Query' Click. Direct URL to retrived Data",
						"Direct URL retrived Data is successfully");
				
				if (!QueryOutputSpatial.getAttribute("value").contains("4326")) {
					QueryOutputSpatial.sendKeys("4326");

				}
				

			} else {

				QueryWhereField.sendKeys(testData.get("EndPointQueryWhere"));

				QueryOutFields.sendKeys("*");
			}

			try {
				QueryGETButton.click();
				actions.Wait(Configutil.low);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				textBody.isDisplayed();
				records = textBody;
			} catch (Exception e) {
				// TODO: handle exception

				try {
					alternateTextBody.isDisplayed();
					records = alternateTextBody;

				} catch (Exception e2) {
					// TODO: handle exception
					ExecutionReport.logMessage(report, Status.FAIL, "Retrieve Data EndPoint",
							"Retrieve data from endpoint failed. Error message : " + e2.getMessage());
					throw new Exception("Unable to retrive text from the endpoint");
				}
			}

			switch (records.getAttribute("textContent")) {
			case "No records found.":
			case "No results found.":

				ExecutionReport.logMessage(report, Status.WARNING, "Retrieve Data EndPoint",
						"Endpoint error message : " + records.getAttribute("textContent"));

				break;
				
			default:

				// code for default case

				// driverHeadless.findElement(By.xpath("//div[@class='rbody']//i[text()='OBJECTID:
				// ']//following::a[1]")).click();

				try {

					String completeInfoInitial = (driver.findElement(By.xpath("//*[@class='rbody']"))
							.getAttribute("textContent"));

					/*
					 * String[] completeInfoFinal= completeInfoInitial.split("# records:");
					 * 
					 * completeInfo = completeInfoFinal[1];
					 */

					System.out.println(completeInfoInitial);

					String[] iniDetails = completeInfoInitial.split("\\r?\\n");
					
					System.out.println(iniDetails);
					

					for (int i = 0; i < iniDetails.length; i++) {

						if (iniDetails[i].contains(":") && !(iniDetails[i].contentEquals("Polygon:"))
								&& !(iniDetails[i].contentEquals("Multipoint:"))
								&& !(iniDetails[i].contentEquals("Polyline:"))) {
							String finalDetails[] = iniDetails[i].split(":", 2);

							if (finalDetails[0].trim().contentEquals("X")) {
								hm.put(finalDetails[0].trim(), (finalDetails[1]).trim());
							} else if (finalDetails[0].trim().contentEquals("Y")) {
								hm.put(finalDetails[0].trim(), (finalDetails[1]).trim());
								break;
							}

							// System.out.println(finalDetails[0]+ ">>>>" + (finalDetails[1]).trim());
						} else if ((iniDetails[i].contentEquals("Polygon:"))
								|| (iniDetails[i].contentEquals("Multipoint:"))
								|| (iniDetails[i].contentEquals("Polyline:"))) {
							// System.out.println("Add polygon");
							String finalDetails[] = iniDetails[i + 1].split(",", 2);
							if (finalDetails.length == 2) {
								hm.put("X", (finalDetails[0]).replace("[", "").trim());
								hm.put("Y", (finalDetails[1]).replace("]", "").trim());
								break;
							}

						}

					}

				} catch (Exception e) {

					completeInfo = driver.findElement(By.xpath("//table[@class='ftrTable']"))
							.getAttribute("textContent");

					System.out.println(completeInfo);

					String[] iniDetails = completeInfo.split("\\r?\\n");

					for (int i = 0; i < iniDetails.length; i++) {

						if ((iniDetails[i].contains("Ring0:")) || (iniDetails[i].contains("Path0:"))) {

							String removeRing[] = iniDetails[i].split(":", 2);
							String finalDetails[] = removeRing[1].split(",", 3);
							hm.put("X", (finalDetails[0]).replace("[", "").trim());
							hm.put("Y", (finalDetails[1]).replace("]", "").trim());
							break;
						} else if ((iniDetails[i].contains("Point:"))) {
							String removeRing1[] = iniDetails[i+1].split(":", 2);
							String removeRing2[] = iniDetails[i+2].split(":", 2);
							hm.put("X", (removeRing1[1]).trim());
							hm.put("Y", (removeRing2[1]).trim());
							break;
						}

					}
					// test.createNode("Retrieve Data EndPoint").fail("Retrieve data from endpoint
					// server is unsuccessful");
				}

				ExecutionReport.logMessage(report, Status.PASS, "Retrieve Data EndPoint",
						"Retrieve data from endpoint server is successful");			
				driver.switchTo().window(originalWindow);
				break;
				
			}
					
			driver.switchTo().window(originalWindow);

		} catch (Exception e) {

			//String errorMsg = driver.findElement(By.xpath("//body/div[1]/div")).getAttribute("innerText");
			ExecutionReport.logMessage(report, Status.FAIL, "Retrieve Data EndPoint",
					"Retrieve data from endpoint failed. Error message : " + e.getMessage());
			throw new Exception("Retrieve data from endpoint server is unsuccessful");

		}

		return hm;
	}

}
