package com.automation.framework.core;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;

import com.automation.framework.config.Configutil;
import com.automation.framework.config.globalConfig;
import com.automation.framework.utilities.ExecutionReport;
import com.automation.framework.utilities.Status;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class BaseClass {

	public static WebDriver driver;
	public static Properties prop;
	public static globalConfig globalParam;
	public static ExtentReports extent;
	public static String folderName = "";
	public static ExtentTest report;

	public static String UserHome = System.getProperty("user.home");
	public static Logger logger = LogManager.getLogger(BaseClass.class);
	public static String UserID = System.getProperty("user.name");
	private static String BrowserName = null;

	public BaseClass() {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream("./config/globalParameter.properties");
			prop.load(fis);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param Data
	 * @throws Exception
	 */
	
	public static void initializeDriver(Hashtable<String, String> Data) throws Exception {
		logger.info("iniitialize driver method starts");
		try {
			BrowserName = Data.get("Browser");
			logger.info("Browser Name: " + BrowserName);

			if (BrowserName.trim().toLowerCase().equals("chrome")) {
				//System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				//WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();

				try {
					String downloadFilepath = System.getProperty("user.dir") + "\\Downloads";
					HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
					chromePrefs.put("profile.default_content_settings.popups", 0);
					chromePrefs.put("download.default_directory", downloadFilepath);
					chromePrefs.put("safebrowsing.enabled", "true");

					options.setExperimentalOption("prefs", chromePrefs);
					options.addArguments("start-maximized");

					//String localAppData = System.getenv("LocalAppData");
					//options.addArguments("user-data-dir=" + localAppData + "\\Google\\Chrome\\User Data\\");
					//options.addArguments("--profile-directory=Default");

					/**
					 * 
					 * disabling infobars
					 * 
					 */

					options.addArguments("disable-infobars");

					/**
					 * 
					 * disabling extensions
					 * 
					 */

					options.addArguments("--disable-extensions");
//
//					/**
//					 * 
//					 * applicable to windows os only
//					 * 
//					 */
//
//					options.addArguments("--disable-gpu");
//
					/**
					 * 
					 * overcome limited resource problems
					 * 
					 */

					options.addArguments("--disable-dev-shm-usage");

					/**
					 * 
					 * Bypass OS security model
					 * 
					 */

					options.addArguments("--no-sandbox");
					/**
					 * Head less execution
					 */

					// options.addArguments("--headless");
					driver = new ChromeDriver(options);
					driver.manage().deleteAllCookies();
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
					driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
					// driver.manage().window().maximize();

					logger.info("browser driver initialised successfully");
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("failed to initialise and load the driver");
				}

			} else if (BrowserName.trim().toLowerCase().equals("edge")) {
				// pass the user profile path

				//System.setProperty("webdriver.edge.driver", "./drivers/msedgedriver.exe");
				//WebDriverManager.edgedriver().setup();
				
				String downloadFilepath = System.getProperty("user.dir") + "\\Downloads";
				EdgeOptions opt = new EdgeOptions();
				HashMap<String, Object> edgePrefs = new HashMap<String, Object>();
				edgePrefs.put("profile.default_content_settings.popups", 0);
				// edgePrefs.put("download.default_directory", downloadFilepath1);
				edgePrefs.put("safebrowsing.enabled", "true");
				edgePrefs.put("InPrivate", true);

				// options.setExperimentalOption("prefs", edgePrefs);

				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
				cap.setCapability(EdgeOptions.CAPABILITY, opt);

				opt.addArguments("download.default_directory", downloadFilepath);
				opt.addArguments("--user-data-dir=" + UserHome + "\\AppData\\Local\\Microsoft\\Edge\\User Data\\");
				opt.addArguments("start-maximized");
				opt.addArguments("--remote-allow-origins=*");

//				/**
//				 * 
//				 * disabling infobars
//				 * 
//				 */
//
//				opt.addArguments("disable-infobars");
//
//				/**
//				 * 
//				 * disabling extensions
//				 * 
//				 */
//
//				opt.addArguments("--disable-extensions");
//
//				/**
//				 * 
//				 * applicable to windows os only
//				 * 
//				 */
//
//				opt.addArguments("--disable-gpu");
//
//				/**
//				 * 
//				 * overcome limited resource problems
//				 * 
//				 */
//
//				opt.addArguments("--disable-dev-shm-usage");
//
//				/**
//				 * 
//				 * Bypass OS security model
//				 * 
//				 */
//
//				opt.addArguments("--no-sandbox");
//				/**
//				 * Head less execution
//				 */

				// options.addArguments("--headless");

				opt.addArguments("--ignore-certificate-errors-spki-list");

				opt.addArguments("--ignore-ssl-errors");

				driver = new EdgeDriver(opt);

				//driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Configutil.defaultWait));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Configutil.Page_load_timeOut));
				//driver.get("edge://settings/clearBrowserData");
				//driver.findElement(By.id("clear-now")).sendKeys(Keys.ENTER);

				logger.info("Edge borwser initiated successfully");
			} else {
				logger.info("failed to initiate Edge browser");
				throw new Exception();

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("failed to initiate Edge browser");

		}

	}

	/**
	 * 
	 * @param locator
	 * @param timeUnit
	 * @return
	 * @throws NoSuchElementException
	 */

	public boolean checkElementexist(WebDriver driver, WebElement locator, long timeUnit)
			throws NoSuchElementException {
		boolean ret = false;
		try {

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeUnit));

			if (locator.isDisplayed()) {
				ret = true;
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeUnit));
				logger.info("locator is displayed on the page");
			} else {
				ret = false;
				logger.info("locator is not displayed on the page");
			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			ret = false;
			logger.error("Failed to validate the locator");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		return ret;
	}

	/**
	 * @getTittle
	 * @return
	 * @throws Exception
	 */
	public String getTittle() throws Exception {

		String title = null;
		try {
			title = driver.getTitle();

			System.out.println("url title is:: " + title);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return title;

	}

	/**
	 * 
	 * @throws Exception
	 */
	public void initializeConfig() throws Exception {
		try {
			globalParam = new globalConfig();

			String filePath = System.getProperty("user.dir") + "\\config\\Global_Configuration.xlsx";
			globalParam.getGlobalConfigData(filePath);

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("failed to load the global config file");
		} finally {
			try {
				Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
				//Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
				Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
				Runtime.getRuntime().exec("taskkill /F /IM safari.exe");
				Runtime.getRuntime().exec("taskkill /F /IM opera.exe");
				Runtime.getRuntime().exec("taskkill /F /IM winword.exe");
				Runtime.getRuntime().exec("taskkill /F /IM msedge.exe");
				// Runtime.getRuntime().exec("taskkill /F /IM excel.exe");
				Runtime.getRuntime().exec("taskkill /F /IM AcroRd32.exe");
				Runtime.getRuntime().exec("taskkill /IM chromedriver.exe /F");

			} catch (IOException e) {
				logger.error("Task kill method fails as a result of IOException");
				e.printStackTrace();
			}
		}
		if (logger.isDebugEnabled()) {
			logger.error("initializeconfig method: End");
		}

	}

	@BeforeSuite
	public static void getReportObject(ITestContext context) {

		createReportFolderStructure();
		String path = System.getProperty("user.dir") + "\\reports\\" + System.getProperty("ReportFolderName")
				+ "\\Result.html";

		ExtentSparkReporter html = new ExtentSparkReporter(path);
		html.config().setReportName("TAMI Automation Report");
		html.config().setDocumentTitle("Execution Results");

		extent = new ExtentReports();
		extent.attachReporter(html);
		extent.setSystemInfo("Execution Mode", "Regression");
		extent.setSystemInfo("Module", "TAMI");

	}

	public static BaseClass createReportFolderStructure() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
		folderName = (dateFormat.format(date));
		File dir1 = new File("./reports/" + folderName);
		dir1.mkdir();
		logger.info("folder creation completed with folder name " + folderName);

		File dir2 = new File("./report/" + folderName + "/SS");
		dir2.mkdir();
		System.setProperty("ReportFolderName", folderName);
		logger.info("screenshot folder creation completed");
		return null;
	}

	@AfterClass
	public void flushReport() throws Exception {
		extent.flush();
	}
	
	
	

	/*check the fole is downloaded in the mentioned directory*/
	public void check_file_downLoad_status(String fileName) throws Exception {

		try {
			if (BrowserName.equalsIgnoreCase("Edge")) {
				/* Automate download popup for edge browser */
				this.SetDefault_download();

				/*
				 * check the default download folder is having the downloaded .csv file or not
				 */
				String Home = System.getProperty("user.home");
				File file = new File(Home + "/Downloads/" + fileName + ".csv");

				Thread.sleep(3000);

				do {
					break;

				} while (file.exists());

				/*
				 * Copy the downloaded .csv file from default download folder to project
				 * download folder.
				 */

				File source = new File(Home + "/Downloads/" + fileName + ".csv");
				File dest = new File(System.getProperty("user.dir") + "\\Downloads\\" + fileName + ".csv");
				try {
					FileUtils.copyFile(source, dest);
					while (dest.exists()) {
						logger.info("File Download" + fileName + " file downloaded successfully in path '"
								+ dest.getPath() + " '");
						break;
					}
				} catch (IOException e) {
					e.printStackTrace();

				}

				/*
				 * /*This is the validation in down load folder of project dir and for chrome
				 * browser
				 */

			}

			else {
				File fl = new File(System.getProperty("user.dir") + "\\Downloads\\" + fileName + ".csv");
				/* check the file is exist in the give path or else wait */
				Thread.sleep(3000);

				/* Once the file exist pass the message */
				while (fl.exists()) {
					logger.info("File Download" + fileName + " file downloaded successfully in path '" + fl.getPath()
							+ " '");
					break;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Download failed: No such file downloaded in the directory");
			Assert.fail();
		}
	}

	/* use keyboard action to download file for edge browser only */
	public void SetDefault_download() {
		try {

			Robot R = new Robot();

			R.keyPress(KeyEvent.VK_CONTROL);
			R.keyPress(KeyEvent.VK_J);
			Thread.sleep(2000);

			R.keyRelease(KeyEvent.VK_J);
			R.keyRelease(KeyEvent.VK_CONTROL);
			// Thread.sleep(2000);

			R.keyPress(KeyEvent.VK_CONTROL);
			R.keyPress(KeyEvent.VK_J);
			Thread.sleep(2000);

			R.keyRelease(KeyEvent.VK_J);
			R.keyRelease(KeyEvent.VK_CONTROL);
			// Thread.sleep(2000);

			R.keyPress(KeyEvent.VK_TAB);
			R.keyRelease(KeyEvent.VK_TAB);

			R.keyPress(KeyEvent.VK_TAB);
			R.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(2000);

			R.keyPress(KeyEvent.VK_ENTER);
			R.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);

			R.keyPress(KeyEvent.VK_ENTER);
			R.keyRelease(KeyEvent.VK_ENTER);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/* csv file delete from folder location */

	public void FileDeletfromderectory() throws Exception {
		try {
			if (BrowserName.equals("Edge")) {
				File foldr = new File(UserHome + "/Downloads");

				for (File fileFrmt : foldr.listFiles()) {
					if (fileFrmt.getName().contains(".csv")) {
						fileFrmt.delete();
					}
				}

				File file = new File(System.getProperty("user.dir") + "\\Downloads\\");

				FileUtils.cleanDirectory(file);
				/*
				 * if(file.exists()){ file.delete();
				 */
				System.out.println("Files are deleted");
			}

			else if (BrowserName.equals("Chrome")) {

				File file = new File(System.getProperty("user.dir") + "\\Downloads\\");

				FileUtils.cleanDirectory(file);
				/*
				 * if(file.exists()){ file.delete();
				 */
				System.out.println("Files are deleted");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("file deleted successfully");
		}

	}
}
