import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Excel {
    private String excelFilePath;


    public Excel(String excelFilePath) {
        this.excelFilePath = excelFilePath;
    }

    public List<BankAccountBalance> getBankAccountBalance() {
        List<BankAccountBalance> bankAccountBalanceList = new ArrayList<>();

        try (XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(excelFilePath))) {

            XSSFSheet sheet = workbook.getSheet("EXTRAS DE CONT TUNAT"); // get Sheet 2 and assign it to sheet
            int firstRow = sheet.getFirstRowNum();
            int lastRow = sheet.getLastRowNum();

            BankAccountBalance bankAccountBalance = null;
            for (int rowNumber = firstRow; rowNumber < lastRow; rowNumber++) {
                Row rowData = sheet.getRow(rowNumber);

                if (rowData == null) {
                    // This whole row is empty
                    // Handle it as needed
                    System.out.println("Row: " + rowNumber + " from sheet: " + sheet.getSheetName() + " is empty");
                    continue;
                }

                int lastColumn = rowData.getLastCellNum();

                for (int columnNumber = 2; columnNumber < lastColumn; columnNumber++) {
                    // the data needed begins at column C
                    Cell cell = rowData.getCell(columnNumber, Row.RETURN_BLANK_AS_NULL);
                    if (cell == null) {
                        // The spreadsheet is empty in this cell
                        System.out.println("Cell from row: "
                                + rowNumber + " and column: "
                                + columnNumber + " from sheet: "
                                + sheet.getSheetName() + " is empty");
                    } else {

                        if (columnNumber == 3){
                            bankAccountBalance = new BankAccountBalance();
//                            System.out.println("Setting the cash: " + cell.getNumericCellValue());
                            bankAccountBalance.setCashIn(String.valueOf(cell.getNumericCellValue()));
                        } else if (cell.getStringCellValue().contains("Ordonator:") && bankAccountBalance != null){
//                            System.out.println("Setting the provider: " + cell.getStringCellValue());
                            bankAccountBalance.setMoneyProvider(cell.getStringCellValue().split(":")[1]);
                            bankAccountBalanceList.add(bankAccountBalance);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bankAccountBalanceList;
    }

    public List<Order> getOrders(){

        List<Order> orderList = new ArrayList<>();

        try (XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(excelFilePath))) {

            XSSFSheet sheet = workbook.getSheet("FISIER CU TRIMISE"); // get Sheet 2 and assign it to sheet
            int firstRow = sheet.getFirstRowNum();
            int lastRow = sheet.getLastRowNum();

            for (int rowNumber = firstRow; rowNumber < lastRow; rowNumber++) {
                Row rowData = sheet.getRow(rowNumber);
                Order order = new Order();

                if (rowData == null) {
                    // This whole row is empty
                    // Handle it as needed
                    System.out.println("Row: " + rowNumber + " from sheet: " + sheet.getSheetName() + " is empty");
                    continue;
                }

                // get invoice number
                Cell cell = rowData.getCell(2, Row.RETURN_BLANK_AS_NULL);
                if (cell == null) {
                    // The spreadsheet is empty in this cell
                    System.out.println("Cell from row: "
                            + rowNumber + " and column: "
                            + 0 + " from sheet: "
                            + sheet.getSheetName() + " is empty");
                } else {
//                    System.out.println("Setting the invoice number: " + cell.getStringCellValue());
                    order.setInvoiceNumber(cell.getStringCellValue());
                }

                // get invoice value
                cell = rowData.getCell(13, Row.RETURN_BLANK_AS_NULL);
                if (cell == null) {
                    // The spreadsheet is empty in this cell
                    System.out.println("Cell from row: "
                            + rowNumber + " and column: "
                            + 0 + " from sheet: "
                            + sheet.getSheetName() + " is empty");
                } else {
//                    System.out.println("Setting the invoice value: " + String.valueOf(cell.getNumericCellValue()));
                    order.setInvoiceValue(String.valueOf(cell.getNumericCellValue()));
                }

                // get delivery provider
                cell = rowData.getCell(14, Row.RETURN_BLANK_AS_NULL);
                if (cell == null) {
                    // The spreadsheet is empty in this cell
                    System.out.println("Cell from row: "
                            + rowNumber + " and column: "
                            + 0 + " from sheet: "
                            + sheet.getSheetName() + " is empty");
                } else {
//                    System.out.println("Setting the deliveryType: " + cell.getStringCellValue());
                    order.setDeliveryType(cell.getStringCellValue());
                }

                orderList.add(order);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    return orderList;
}
}
