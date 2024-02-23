package com.automation.framework.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;

import com.automation.framework.exception.DriverScriptException;
import com.automation.framework.utilities.ExcelUtil;

public class globalConfig {

	public static Logger logger;
	public static ExcelUtil xlUtil;
	public static HashMap<String, String> globalParaMap;

	public globalConfig() throws Exception {
		logger = LogManager.getLogger(globalConfig.class);
		xlUtil = new ExcelUtil();
	}

	/**
	 * @Method: getGlobalConfigData
	 * @Descp: Read the global parameter data
	 * @param file
	 * @throws FileNotFoundException
	 * @throws FileFormatException
	 * @throws DriverScriptException
	 */
	public void getGlobalConfigData(String file)
			throws FileNotFoundException, FileFormatException, DriverScriptException {
		logger.info("get global config Data method starts");
		globalParaMap = new HashMap<String, String>();
		try {
			if (file != null) {
				xlUtil.openWorkBook(new File(file));
				logger.info("oprn workbook method starts");
				String sheetName = xlUtil.getSheetNames(0);
				logger.info("Workbook active sheet name: " + sheetName);

				if (sheetName.equalsIgnoreCase("Global_config")) {
					logger.info("Workbook active sheet name: " + sheetName + "matched the actual file sheetName");
					int RowCount = xlUtil.getTotalRowCount(sheetName);

					for (int row = 0; row < RowCount; row++) {
						String globalParamName = xlUtil.getCellValue(sheetName, row, 0);
						String globalParamValue = xlUtil.getCellValue(sheetName, row, 1);
						globalParaMap.put(globalParamName, globalParamValue);
						logger.info(
								"global config Name: " + globalParamName + " global config value: " + globalParamValue);
					}
				} else {
					logger.error("Active sheet is not same as actual sheet name GlobalConfig");
					throw new FileNotFoundException("active work book is not present");
				}

			}

		} catch (DriverScriptException e) {
			logger.error("failed to capture the data from global config file");
			e.printStackTrace();
		}
	}

}
