package com.pge.application.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.framework.core.BaseClass;
import com.automation.framework.exception.DriverScriptException;
import com.automation.framework.utilities.ExecutionReport;
import com.automation.framework.utilities.ObjectRead;
import com.aventstack.extentreports.ExtentTest;
import com.pge.application.PageObjects.TAMI_DetailSearch;
import com.pge.application.PageObjects.TAMI_EndPoint_Data;
import com.pge.application.PageObjects.TAMI_Homepage;
import com.pge.application.PageObjects.TAMI_QuickSearch;
import com.pge.application.PageObjects.TAMI_TOC_Menu;
import com.pge.application.PageObjects.TAMI_Tools_Menu;

public class TAMI_LayerValidation_Airports_Test extends BaseClass {

	public TAMI_Homepage homePage;
	public TAMI_TOC_Menu toc;
	public TAMI_Tools_Menu tools;
	public TAMI_DetailSearch detailSearch;
	public TAMI_EndPoint_Data data;
	public TAMI_QuickSearch Search;
	public ExtentTest report;

	public TAMI_LayerValidation_Airports_Test() throws IOException {
		super();

	}

	@DataProvider(name = "testDataProvider")
	public Object[][] testDataProvider() throws DriverScriptException {
		return ObjectRead.getConsolidatedTestDataArray(this.getClass().getSimpleName().toString(), "LayerValidation");
	}

	@BeforeTest
	public void reportsetup() throws Exception {
		initializeConfig();
	}

	@Test(dataProvider = "testDataProvider")
	public void functionalTest(Hashtable<String, String> testData, ITestContext context) throws Exception {
		try {
			report = extent.createTest(this.getClass().getSimpleName()).assignCategory("LayerValidation");
			initializeDriver(testData);

			homePage = new TAMI_Homepage(driver);
			toc = new TAMI_TOC_Menu(driver);
			tools = new TAMI_Tools_Menu(driver);
			data = new TAMI_EndPoint_Data(driver);

			homePage.launch_TAMI_OneViewer_url(testData, report);

			toc.funcExpandLeftPanel(testData, report);
			toc.funcSearchLayer(testData, report);

			tools.funcExpandBottomPanel(testData, report);
			tools.funcOpenSearch(testData, report);
			tools.funcOpenXY(testData, report);

			Search = new TAMI_QuickSearch(driver);

			HashMap<String, String> endpointData = data.RetriveLoc(testData, report);
			if (!endpointData.isEmpty()) {
			Search.LayerValidationFillDetailsAndSearch(testData, report,
					endpointData.get("X"),endpointData.get("Y"));
			Search.LayerValidateIcons(testData, report);
			Search.ValidateImageFromLegend(testData, report);
			}

			if (ExecutionReport.failCount > 0) {
				Assert.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
			throw new DriverScriptException("Error in execution");

		} finally {
			// driver.close();
			driver.quit();
		}
	}

}
