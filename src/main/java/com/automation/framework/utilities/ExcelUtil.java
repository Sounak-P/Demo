package com.automation.framework.utilities;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Hashtable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.automation.framework.exception.DriverScriptException;

public class ExcelUtil {

	
	/** The Constant logger. */
	private static  Logger logger = LogManager.getLogger(ExcelUtil.class);

	/** The wb. */
	private static Workbook wb;

	
	
		/**
		 * This method is to open a workbook excel.
		 * 
		 * @param workbookPath
		 *            the workbook path
		 * @return the workbook
		 * @throws DriverScriptException
		 */

		public void openWorkBook(File workbookPath) throws DriverScriptException {
			if (logger.isDebugEnabled()) {
				logger.info("openWorkBook(File) - start"); //$NON-NLS-1$
			}

			closeIfOpen();
			InputStream inputStream = null;
			logger.info("Opening workbook");
			try {
				inputStream = new BufferedInputStream(new FileInputStream(
						workbookPath));
				wb = WorkbookFactory.create(inputStream);

			} catch (FileNotFoundException exception) {
				throw new DriverScriptException(" WorkBook file path not found");
			} catch (Exception e) {
				logger.error("openWorkBook(File)"+ e); //$NON-NLS-1$

				logger.error( "openWorkBook opening " + workbookPath + ", "
						+ e);
			} finally {
				if (inputStream != null) {
					try {
						inputStream.close();

					} catch (Exception e) {
						logger.error("openWorkBook(File)"+ e); //$NON-NLS-1$

						logger.error( "openWorkBook Closing" + workbookPath
								+ ", " + e);

					}
				}
			}

			if (logger.isDebugEnabled()) {
				logger.info("openWorkBook(File) - end"); //$NON-NLS-1$
			}
		}
		
		public void saveWorkbook(File workbookPath) throws Exception{
			FileOutputStream oFOS = null;
			
			try {
				oFOS = new FileOutputStream(workbookPath);
				wb.write(oFOS);
			}
			catch(Exception e) {
				logger.error( "Error while saving workbook");
			}
		}

		/**
		 * Close if open.
		 */
		private void closeIfOpen() {
			if (logger.isDebugEnabled()) {
				logger.info("closeIfOpen() - start"); //$NON-NLS-1$
			}
			
			try {
				if (wb != null) {
					// WorkbookFactory.

				}
			} catch (Exception e) {
				logger.error("closeIfOpen()" + e); //$NON-NLS-1$

				logger.error( "closeIfOpen " + e);
			}

			if (logger.isDebugEnabled()) {
				logger.info("closeIfOpen() - end"); //$NON-NLS-1$
			}
		}

		/**
		 * This method is used to get the cell value for string format.
		 *
		 * @param sheetName
		 *            the sheet name
		 * @param iRow
		 *            the row no
		 * @param iCol
		 *            the column no
		 * @return the string
		 */

		public String getCellValue(String sheetName, int iRow, int iCol) {
			if (logger.isDebugEnabled()) {
				logger.info("getCellValue(String, int, int) - start"); //$NON-NLS-1$
			}

			Cell xlCell = null;
			String cellValue = null;
			try {
				xlCell = getCell(wb, sheetName, iRow, iCol);

				if (xlCell == null) {
					cellValue = "";
				} else {
					cellValue = xlCell.getStringCellValue();
				}
			} catch (IllegalStateException e) {
				cellValue = String.valueOf((int) xlCell.getNumericCellValue());
			} catch (java.lang.Exception e) {
				cellValue = "";
			}
			if (cellValue != null) {
				if (logger.isDebugEnabled()) {
					logger.info("getCellValue(String, int, int) - end"); //$NON-NLS-1$
				}
				return cellValue;
			} else {
				if (logger.isDebugEnabled()) {
					logger.info("getCellValue(String, int, int) - end"); //$NON-NLS-1$
				}
				return "-EOF-";
			}
		}
		
		/**
		 * This method is used to set the cell value for string format.
		 *
		 * @param sheetName
		 *            the sheet name
		 * @param cellValue
		 * 				the cell value to be set
		 * @param iRow
		 *            the row no
		 * @param iCol
		 *            the column no
		 * @return void
		 */

		public void setCellValue(String sheetName, String cellValue, int iRow, int iCol) {
			if (logger.isDebugEnabled()) {
				logger.info("setCellValue(String, int, int) - start"); //$NON-NLS-1$
			}

			Cell xlCell = null;
			//String cellValue = null;
			String sCheckValue = null;
			
			try {
				xlCell = getCell(wb, sheetName, iRow, iCol);
				
				xlCell.setCellValue(cellValue);
				Thread.sleep(2000);
				sCheckValue = xlCell.getStringCellValue();
				
				/*if (xlCell == null) {
					cellValue = "";
				} else {
					cellValue = xlCell.getStringCellValue();
				}*/
			} catch (IllegalStateException e) {
				cellValue = String.valueOf((int) xlCell.getNumericCellValue());
			} catch (java.lang.Exception e) {
				cellValue = "";
			}
			if (cellValue == sCheckValue) {
				if (logger.isDebugEnabled()) {
					logger.info("getCellValue(String, int, int) - end - String has been set successfully"); //$NON-NLS-1$
				}
				//return cellValue;
			} else {
				if (logger.isDebugEnabled()) {
					logger.info("getCellValue(String, int, int) - end - String has not been set successfully"); //$NON-NLS-1$
				}
				//return "-EOF-";
			}
		}

		/**
		 * This method is used to get the cell value for numeric format.
		 *
		 * @param sheetName
		 *            the sheet name
		 * @param iRow
		 *            the row no
		 * @param iCol
		 *            the column no
		 * @return the double
		 */

		public double getCellNumericValue(String sheetName, int iRow, int iCol) {
			if (logger.isDebugEnabled()) {
				logger.info("getCellNumericValue(String, int, int) - start"); //$NON-NLS-1$
			}

			Cell xlCell;
			double cellValue = 0;
			try {
				xlCell = getCell(wb, sheetName, iRow, iCol);
				if (xlCell == null) {
					cellValue = 0;
				} else {
					cellValue = xlCell.getNumericCellValue();
				}
			} catch (NullPointerException e) {
				logger.error("getCellNumericValue(String, int, int)" +e ); //$NON-NLS-1$

				cellValue = 0;
			}

			if (logger.isDebugEnabled()) {
				logger.info("getCellNumericValue(String, int, int) - end"); //$NON-NLS-1$
			}
			return cellValue;
		}

		/**
		 * This method is used to get the total row count in the sheet.
		 *
		 * @param sheetName
		 *            the sheet name
		 * @return the int
		 * @throws DriverScriptException 
		 */

		public int getTotalRowCount(String sheetName) throws DriverScriptException {
			if (logger.isDebugEnabled()) {
				logger.info("getTotalRowCount(String) - start"); //$NON-NLS-1$
			}

			Sheet xlSheet;
			xlSheet = wb.getSheet(sheetName);
			if(xlSheet==null){
				throw new DriverScriptException(new Throwable( sheetName+" does not exists"));
			}
			int returnint = xlSheet.getLastRowNum();
			if (logger.isDebugEnabled()) {
				logger.info("getTotalRowCount(String) - end"); //$NON-NLS-1$
			}
			return returnint;
		}

		/**
		 * This method is used to get the total column count in the sheet.
		 *
		 * @param sheetName
		 *            the sheet name
		 * @return the int
		 */
			public int getTotalColumnCount(String sheetName,int row) {
				if (logger.isDebugEnabled()) {
					logger.info("getTotalColumnCount(String) - start"); //$NON-NLS-1$
				}

				int columncount = 0;
				Sheet xlSheet;
				xlSheet = wb.getSheet(sheetName);
			Row row2 = xlSheet.getRow(row);
				for (int colcnt = 0; colcnt <= row2.getLastCellNum(); colcnt++) {
					columncount = columncount + 1;
				}
				if (logger.isDebugEnabled()) {
					logger.info("getTotalColumnCount(String) - end"); //$NON-NLS-1$
				}
				return columncount;
			}
		
		/**
		 * This method is used to get the total column count in the sheet.
		 *
		 * @param sheetName
		 *            the sheet name
		 * @return the int
		 */
		public int getTotalColumnsInRow(String sheetName) {
			if (logger.isDebugEnabled()) {
				logger.info("getTotalColumnCount(String) - start"); //$NON-NLS-1$
			}

			int columncount = 0;
			Sheet xlSheet;
			xlSheet = wb.getSheet(sheetName);
			columncount = xlSheet.getRow(0).getLastCellNum();
			if (logger.isDebugEnabled()) {
				logger.info("getTotalColumnCount(String) - end"); //$NON-NLS-1$
			}
			return columncount;
		}

		/**
		 * Gets the number of sheets.
		 * 
		 * @return the number of sheets
		 */
		public int getNumberOfSheets() {
			if (logger.isDebugEnabled()) {
				logger.info("getNumberOfSheets() - start"); //$NON-NLS-1$
			}

			int returnint = wb.getNumberOfSheets();
			if (logger.isDebugEnabled()) {
				logger.info("getNumberOfSheets() - end"); //$NON-NLS-1$
			}
			return returnint;
		}

		/**
		 * isSheetExists.
		 *
		 * @param sheetName
		 *            the sheet name
		 * @return true, if is sheet exists
		 * @returns whether sheet exists
		 */
		public boolean isSheetExists(String sheetName) {
			if (logger.isDebugEnabled()) {
				logger.info("isSheetExists(String) - start"); //$NON-NLS-1$
			}

			Sheet xlSheet = null;
			if (sheetName != null) {

				xlSheet = wb.getSheet(sheetName);
			}
			if (xlSheet != null) {
				if (logger.isDebugEnabled()) {
					logger.info("isSheetExists(String) - end"); //$NON-NLS-1$
				}
				return true;
			} else {
				if (logger.isDebugEnabled()) {
					logger.info("isSheetExists(String) - end"); //$NON-NLS-1$
				}
				return false;
			}
		}

		/**
		 * Gets the sheet names.
		 * 
		 * @param index
		 *            the index
		 * @return the sheet names
		 */
		public String getSheetNames(int index) {
			if (logger.isDebugEnabled()) {
				logger.info("getSheetNames(int) - start"); //$NON-NLS-1$
			}

			String sheetName = null;
			sheetName = wb.getSheetName(index);

			if (logger.isDebugEnabled()) {
				logger.info("getSheetNames(int) - end"); //$NON-NLS-1$
			}
			return sheetName;
		}

		/**
		 * Gets the sheet count.
		 *
		 * @return the sheet count
		 */
		public int getSheetCount() {
			if (logger.isDebugEnabled()) {
				logger.info("getSheetCount() - start"); //$NON-NLS-1$
			}

			int sheetCount = 0;
			sheetCount = wb.getNumberOfSheets();

			if (logger.isDebugEnabled()) {
				logger.info("getSheetCount() - end"); //$NON-NLS-1$
			}
			return sheetCount;

		}

		/**
		 * This method is used to get the cell value in the sheet.
		 *
		 * @param wb
		 *            the workbook excel
		 * @param sheetName
		 *            the sheet name
		 * @param iRow
		 *            the row no
		 * @param iCol
		 *            the column no
		 * @return the cell
		 */

		public Cell getCell(Workbook wb, String sheetName, int iRow, int iCol) {
			if (logger.isDebugEnabled()) {
				logger.info("getCell(Workbook, String, int, int) - start"); //$NON-NLS-1$
			}

			Sheet xlSheet;
			Row oRow;
			Cell oCell;

			xlSheet = wb.getSheet(sheetName);
			oRow = xlSheet.getRow(iRow);
			oCell = oRow.getCell(iCol);

			if (logger.isDebugEnabled()) {
				logger.info("getCell(Workbook, String, int, int) - end"); //$NON-NLS-1$
			}
			return oCell;
		}

		/**
		 * This method is used to get the row number for a particular ALMID and Business Component
		 * @param sheetName
		 *             the sheet name
		 * @param idValue
		 *             the ALMID value
		 * @param businessComponentValue
		 *             the business component value
		 * @return
		 *             the row number of ALMID
		 */
		public int getRowNumberForId(String sheetName, String idValue, String businessComponentValue){
			Sheet xlSheet;
			int rowNum = 0;

			xlSheet = wb.getSheet(sheetName);
			for (Row oRow : xlSheet) {
				for (Cell oCell : oRow) {
					if (oCell.getStringCellValue() != null) {
						String idCellString = oCell.getRichStringCellValue().getString().trim();
						if (idCellString.equals(idValue) ) {
							for (Cell innerRowCell : oRow) {
								if (innerRowCell.getStringCellValue() != null) {
									String businessComponentString = innerRowCell.getRichStringCellValue().getString().trim();
									if(businessComponentString.equals(businessComponentValue)){
										rowNum=oRow.getRowNum();  
									}
								}
							}
						}
					}
				}       
			}
			return rowNum;
		}
		
		
		public int getColumnIndex(String sheetName, String sHeader){
			Sheet xlSheet;
			int iSearchedColIndex = 0;
			
			xlSheet = wb.getSheet(sheetName);
			int iTotalColumn = xlSheet.getRow(0).getPhysicalNumberOfCells();
			
			for (int i=0;i<=iTotalColumn;i++){
				if (getCellValue(sheetName, 0, i).equals(sHeader)){
					iSearchedColIndex = i;
					break;
				}
			}
			
			return iSearchedColIndex;		
		}
		
		/**
		 * This method is used to get the set of values(key,value) for a particular ALMID and Business Component
		 * @param sheetName
		 *             the sheet name
		 * @param columnCount
		 *             the number of columns to be fetched
		 * @param idValue
		 *              the ALMID value
		 * @param businessComponentValue
		 *              the business component value
		 * @return
		 *              map consisting of all the values for the particular row.
		 */
		public Hashtable<String, String> getFieldValues(String sheetName, int columnCount, String idValue, String businessComponentValue){
			int rowNum = getRowNumberForId(sheetName, idValue, businessComponentValue);
			Hashtable<String, String> fieldValues = new Hashtable<String, String>();
			//int columnCount = xlSheet.getRow(rowNum).getPhysicalNumberOfCells();
			/*Start from third column(i.e,colNum=2) since field values are in*/
			for(int i=2;i<columnCount+2;i++){
				String cellValue = getCellValue(sheetName, rowNum, i);
				String[] mapValue = cellValue.split("=");
				String key = mapValue[0];
				String value = mapValue[1];
				fieldValues.put(key,value);
			}

			return fieldValues;
		}
		
		/**
		 * This method is used to get the data from global parameters sheet.
		 * @param sheetName
		 * @return
		 */
		public Hashtable<String, String> getGlobalParams(String sheetName){
			Sheet xlSheet;
			xlSheet = wb.getSheet(sheetName);
			Hashtable<String, String> globalParams = new Hashtable<String, String>();
			for (Row selectedRow : xlSheet) {
				String key = getCellValue(sheetName, selectedRow.getRowNum(), 0);
				String data = getCellValue(sheetName, selectedRow.getRowNum(), 1);
				globalParams.put(key, data);
			}

			return globalParams;
		}

		public int getTotalRowCountWithExecuteFlag(String testCaseName, String sheetName) throws DriverScriptException{
			if (logger.isDebugEnabled()) {
				logger.info("getTotalRowCount(String) - start"); //$NON-NLS-1$
			}

			Sheet xlSheet;
			xlSheet = wb.getSheet(sheetName);
			if(xlSheet==null){
				throw new DriverScriptException(new Throwable( sheetName+" does not exists"));
			}
			int totalExecutableRows =0;
			int returnint = xlSheet.getLastRowNum();
			for(int i=1; i<=returnint;i++){
				if("Y".equalsIgnoreCase(getCellValue(sheetName, i, 0)) && testCaseName.equalsIgnoreCase(getCellValue(sheetName, i, 2))){
					totalExecutableRows++;
				} else if("EndOfSheet".equalsIgnoreCase(getCellValue(sheetName, i, 0))){
					break;
				}
			}
			if (logger.isDebugEnabled()) {
				logger.info("getTotalRowCount(String) - end"); //$NON-NLS-1$
			}
			return totalExecutableRows;
		}

	}
