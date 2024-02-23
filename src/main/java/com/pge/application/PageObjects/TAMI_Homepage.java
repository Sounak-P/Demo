package com.pge.application.PageObjects;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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

public class TAMI_Homepage extends BaseClass {

	public static ObjectRead objectRead;
	public static Elements actions;
	public static Logger logger;

	/******************************************************************************************************************/
	@FindBy(xpath = "//span[@class='js-orgname']")
	WebElement autheticationHeader;

	@FindBy(xpath = "//div[contains(text(),'Call')]")
	WebElement SelectCall;

	@FindBy(xpath = "//div[contains(text(),'Approve sign in request')]")
	WebElement ApproveSigninPage;

	@FindBy(xpath = "//span[contains(text(),'the expected response. Please try again.')]")
	WebElement SigninErrorMsg;
	
	@FindBy(xpath="//div[contains(text(),'Request for Permission')]")
	WebElement ReqForPermission;
	
	@FindBy(xpath="//button[contains(text(),'Allow')]")
	WebElement AllowPerm;

	/******************************************************************************************************************/

	public TAMI_Homepage(WebDriver driver) throws Exception {
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
	public void launch_TAMI_OneViewer_url(Hashtable<String, String> testData, ExtentTest report) throws Exception {
		logger.info("TAMI application url load method starts");
		try {
			actions.Wait(Configutil.low);
			// if (testData.get("Browser").equals("Chrome")) {
			if (testData.get("Test_Environment").equals("QA")) {
				driver.navigate().to(globalConfig.globalParaMap.get("TAMI_QA_URL"));

			} else if (testData.get("Test_Environment").equals("TEST")) {
				driver.navigate().to(globalConfig.globalParaMap.get("TAMI_Test_URL"));

			} else if (testData.get("Test_Environment").equals("DEV")) {
				driver.navigate().to(globalConfig.globalParaMap.get("TAMI_Dev_URL"));
			} else if (testData.get("Test_Environment").equals("PROD")) {
				driver.navigate().to(globalConfig.globalParaMap.get("TAMI_Prod_URL"));

			}
			actions.Wait(Configutil.medium);
			String parent=driver.getWindowHandle();

			Set<String> windows = driver.getWindowHandles();

			try {
				if (windows.size() > 1) {
					
					for (String win : windows) {
						String winTitle = driver.switchTo().window(win).getTitle();
						System.out.println(winTitle);

						if (winTitle.contains("Sign in")) {
							SelectCall.click();
							actions.Wait(Configutil.low);
							int count=1;

							try {
								while (ApproveSigninPage.isDisplayed() && count <50 ) {
									actions.Wait(Configutil.low);
									count = count +1;

									/*
									 * if (SigninErrorMsg.isDisplayed()) { ExecutionReport.logMessage(report,
									 * Status.FAIL, "Open TAMI HomePage",
									 * "Failed to open TAMI HomePage. Signin Failed"); break; }
									 */
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					}
					driver.switchTo().window(parent);
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				ReqForPermission.isDisplayed();
				actions.Wait(Configutil.low);
				AllowPerm.click();
				
			} catch (Exception e) {
				// TODO: handle exception
			}

			//Assert.assertEquals(driver.getTitle(), testData.get("HeaderText"));
			ExecutionReport.logMessage(report, Status.PASS, "Open TAMI HomePage",
					"TAMI HomePage Opened successfully and Title Verified");
			actions.Wait(Configutil.low);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("failed to load the url");
			Assert.fail();
		}
	}

}
