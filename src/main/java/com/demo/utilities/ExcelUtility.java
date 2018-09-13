package com.demo.utilities;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtility {

    private static XSSFSheet ExcelWSheet;

    private static XSSFWorkbook ExcelWBook;

    private static XSSFCell Cell;

    private static XSSFRow Row;

    public static Object[][] getDataAsArray(String FilePath, String SheetName) throws Exception {

        Object[][] tabArray = null;

        try {

            FileInputStream ExcelFile = new FileInputStream(FilePath);

            // Access the required test data sheet

            ExcelWBook = new XSSFWorkbook(ExcelFile);

            ExcelWSheet = ExcelWBook.getSheet(SheetName);


            int startRow = 1;

            int startCol = 0;

            int ci,cj;

            int totalRows = ExcelWSheet.getLastRowNum();

            //int totalCols = 2;
            int totalCols = ExcelWSheet.getRow(0).getLastCellNum();

            tabArray=new String[totalRows][totalCols];

            ci=0;

            for (int i=startRow;i<=totalRows;i++, ci++) {

                cj=0;
                startCol=ExcelWSheet.getRow(0).getFirstCellNum();

                for (int j=startCol;j<totalCols;j++, cj++){

                    try {
                        tabArray[ci][cj] = getCellData(i, j);
                        System.out.println(tabArray[ci][cj]);
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                }

            }

        }catch (FileNotFoundException e){

            System.out.println("Could not read the Excel sheet");

            e.printStackTrace();

        }catch (IOException e){

            System.out.println("Could not read the Excel sheet");

            e.printStackTrace();

        }

        return(tabArray);

    }

    public static Object getCellData(int RowNum, int ColNum) throws Exception {

        try {

            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

            if(HSSFCell.CELL_TYPE_NUMERIC == Cell.getCellType()){
                return Helper.getStringValue(Cell.getNumericCellValue());
            }else if(HSSFCell.CELL_TYPE_STRING == Cell.getCellType()){
                return Cell.getStringCellValue();
            }else if(HSSFCell.CELL_TYPE_BOOLEAN == Cell.getCellType()){
                return Helper.getStringValue(Cell.getBooleanCellValue());
            }else if (HSSFCell.CELL_TYPE_BLANK == Cell.getCellType()){
                return null;
            }else{
                return null;
            }

        }catch (Exception e){

            System.out.println(e.getMessage());

            throw (e);

        }

    }


    /**
     * Objective - Method to get TestCaseId object from TestCaseId column in excel file
     * @param FilePath Excel filePath to read
     * @param SheetName Excel sheet name
     * @return Object as array
     * @throws Exception if file not available
     */
    public static Object[][] getDataAsArrayWithTestCaseId(String FilePath, String SheetName) throws Exception {

        return Helper.populateTestCaseId(getDataAsArray(FilePath, SheetName));

    }
}
