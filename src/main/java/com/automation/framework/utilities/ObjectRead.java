package com.automation.framework.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.automation.framework.exception.DriverScriptException;
import com.automation.framework.utilities.ExcelUtil;

public class ObjectRead {

	/** The Constant LOG. */
	private static  Logger logger = LogManager.getLogger(ObjectRead.class);
	
	

	/**
	 * Gets the test data array from a consolidated testData file
	 *
	 * @param testData sheet name
	 * @return the test data array
	 * @throws DriverScriptException 
	 * 
	 */
	public static Object[][] getConsolidatedTestDataArray(String testCaseName, String testDataSheetName) throws DriverScriptException{   
		Object[][] testDataArray = null;
		Hashtable<String, String> hashtable = null;
		String execute;
		ExcelUtil dataUtil= new ExcelUtil();
		
		
		try{
			
			
			String path= System.getProperty("user.dir")+ "\\testData";
			System.out.println(path);
			
			String fileName = searchFile(new File(path),"//testData.xlsx");
			
			if(fileName!=null){
				
				dataUtil.openWorkBook(new File(fileName));
				File file=new File(System.getProperty("user.dir")+ "\\testData\\testData.xlsx");
				FileInputStream fi=new FileInputStream(file);
				XSSFWorkbook wb=new XSSFWorkbook(fi);
				
				XSSFRow Row= wb.getSheet(testDataSheetName).getRow(0);
				
				int RowCOunt= wb.getSheet(testDataSheetName).getLastRowNum();
				System.out.println("Row Count= "+RowCOunt);
								
				int totalCols=Row.getLastCellNum();;
				System.out.println("Total Columns= "+totalCols);
//				for(int i=0;i<totalCols;i++)
//				{
//					System.out.println(i+": "+wb.getSheet(testDataSheetName).getRow(2).getCell(i).getStringCellValue());
//				}
				if (dataUtil.isSheetExists(testDataSheetName)) {
					logger.info(testDataSheetName+" sheet exists in testData.xlsx file");
					int cj, ci;
					int startRow = 1;
					int startCol = 1;
					boolean isSkipped = false;
					int totalExecutableRows = dataUtil.getTotalRowCountWithExecuteFlag(testCaseName,testDataSheetName);
					int totalRows = dataUtil.getTotalRowCount(testDataSheetName)+1;
					//int totalCols = xlUtil.getTotalColumnsInRow(testDataSheetName);
					
					
					testDataArray=new Object[totalExecutableRows][1];
					ci=0;
					for(int i=startRow; i < totalRows;i++,ci++){
						cj=0;
						execute = dataUtil.getCellValue(testDataSheetName,i, 0);
						if ("End".equals(execute)) {
							break;
						}
						
						hashtable = new Hashtable<String, String>();
						for(int j=startCol; j < totalCols;j++,cj++){
							if((dataUtil.getCellValue(testDataSheetName, i, 0).equalsIgnoreCase("Y") && dataUtil.getCellValue(testDataSheetName, i, 2).equalsIgnoreCase(testCaseName))) {
								System.out.println(dataUtil.getCellValue(testDataSheetName, 2, j)+":"+ dataUtil.getCellValue(testDataSheetName, i, j));
								hashtable.put(dataUtil.getCellValue(testDataSheetName, 2, j), dataUtil.getCellValue(testDataSheetName, i, j));
							}
							else {
								isSkipped = true;
								ci=ci-1;
								break;
							}	
						}
						if(isSkipped){
							logger.info("Row "+(i+1)+" had been skipped");
							isSkipped = !isSkipped;
						} else{
							testDataArray[ci][0] = hashtable;
							logger.info(testDataArray[ci][0].toString());
						}
					}
				}
				else {
					logger.error( "Provided worksheet in testData.xlsx file does not exist");
					throw new DriverScriptException(new Throwable("Provided worksheet in testData.xlsx file does not exist"));
				}
			}else{
				logger.error("testData.xlsx file does not exist");
				throw new DriverScriptException(new Throwable("testData.xlsx file does not exist"));
			}
		}catch(DriverScriptException exx){
			
			throw new DriverScriptException(new Throwable(exx.getCause()));
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return(testDataArray);
	}
	
	
	public static String searchFile(File folder, String fileNameToSearch) throws DriverScriptException {

		if (folder.isDirectory()) {
			File absoluteFile = folder.getAbsoluteFile();
			logger.info("Searching directory ... " + absoluteFile);
			String file  = null;
			File checkFile = new File(absoluteFile.getPath(),fileNameToSearch);
			if(checkFile.exists()){
				return checkFile.getAbsolutePath().toString();	
			}
			else{
				for (int i = 0; i < folder.listFiles().length; i++) {
					File recurssiveFolder = folder.listFiles()[i];

					if (recurssiveFolder.isDirectory()) {
						logger.info("Searching directory ... " + recurssiveFolder);
						File temp = new File(recurssiveFolder.getAbsoluteFile().getPath(),fileNameToSearch);
						logger.info(temp + " file");
						if(temp.exists()){
							logger.info("File exists");
							file = temp.getAbsolutePath().toString();
							return file;
						}
					}
				}
			}
			return file;
		}else{
			logger.error(folder + " folder does not exists");
			throw new DriverScriptException(folder+" folder does not exists");
		}
	}
}
