package com.automation.framework.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.automation.framework.core.BaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExecutionReport extends BaseClass {

	public static ExtentTest test;
	public static boolean failedScreenShot = true;
	public static boolean passedScreenshot = true;
	public static int failCount = 0;

	public ExecutionReport(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * "logmeesage status"
	 * @param logType
	 * @return
	 * @throws IOException
	 * @author D6DE
	 */
	public static void logMessage(ExtentTest report, Status logType, String smallMessage, String detailedMessage) throws IOException {

		
		try {
		String screenShotPath = "";
		
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement element = stackTrace[2];
		String className = getClassname(element.getClassName());
		

		if (logType.equals(Status.FAIL)) {
			failCount++;
			if (failedScreenShot)
				screenShotPath = takeExecutionScreenshot(driver);
			report.createNode("Class Name: "+className+" || "
					+ "Method Name: " + element.getMethodName()+" || " + "Step performed: " + smallMessage).fail(
					 detailedMessage).addScreenCaptureFromPath(screenShotPath);
			
			logger.error("Class Name: "+className+" || "
					+ "Method Name: " + element.getMethodName()+" || " + "Step performed: " + smallMessage+" || "+"Message: "
					+ detailedMessage);
		
		}else if (logType.equals(Status.WARNING)) {
			if (passedScreenshot)
				screenShotPath = takeExecutionScreenshot(driver);
			report.createNode("Step performed: " + smallMessage).warning(
					detailedMessage).addScreenCaptureFromPath(screenShotPath);
			
			logger.info("Class Name: "+className+" || "
					+ "Method Name: " + element.getMethodName()+" || " + "Step performed: " + smallMessage+" || "+"Message: "
					+ detailedMessage);

		} else if (logType.equals(Status.PASS)) {
			if (passedScreenshot)
				screenShotPath = takeExecutionScreenshot(driver);
			report.createNode("Step performed: " + smallMessage).pass(
					detailedMessage).addScreenCaptureFromPath(screenShotPath);
			
			logger.info("Class Name: "+className+" || "
					+ "Method Name: " + element.getMethodName()+" || " + "Step performed: " + smallMessage+" || "+"Message: "
					+ detailedMessage);

		} else if ((logType.equals(Status.INFO))) {
			report.createNode("Step performed:" + smallMessage).info(
					 detailedMessage);
			logger.info("Class Name: "+className+" || "
					+ "Method Name: " + element.getMethodName()+" || " + "Step performed: " + smallMessage+" || "+"Message: "
					+ detailedMessage);
		}
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		

	}

	/**
	 * @Method takeExecutionScreenshot Capture screenshots
	 * @param driver
	 * @return
	 * @author D6DE
	 */
	public static String takeExecutionScreenshot(WebDriver driver) {

		String reportFolderName = System.getProperty("ReportFolderName");
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
		Random rand = new Random();
		int random_No = rand.nextInt(99999) + 1;
		String fileName = (dateFormat.format(date)) + "" + random_No + ".png";

		File newScreenShot = new File("./reports/" + reportFolderName + "/SS/" + fileName);
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(scrFile, newScreenShot);
		} catch (IOException e) {

		}

		return "SS/" + fileName;
	}

	/**
	 * Gets the class name.
	 * 
	 * @method: getClassname
	 * @param className
	 * @return the classname
	 * @author D6DE
	 */
	public static String getClassname(String className) {
		int counter = 0;
		for (int i = className.length() - 1; i > 0; i--) {
			if (className.charAt(i) == '.') {
				break;
			}
			counter = counter + 1;
		}
		className = className.substring(className.length() - counter, className.length());
		return className;
	}
}
