import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//@Slf4j
public class Excel {

    private final static Logger logger = LoggerFactory.getLogger(Excel.class);

    private String excelFilePath;


    public Excel(String excelFilePath) {
        this.excelFilePath = excelFilePath;
    }

    public List<BankAccountBalance> getBankAccountBalance() {
        List<BankAccountBalance> bankAccountBalanceList = new ArrayList<>();

        logger.info("Processing the Banck Account Balance");
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
                    logger.warn("Row: {} from sheet: {} is empty", rowNumber, sheet.getSheetName());
                    continue;
                }

                int lastColumn = rowData.getLastCellNum();

                for (int columnNumber = 2; columnNumber < lastColumn; columnNumber++) {
                    // the data needed begins at column C
                    Cell cell = rowData.getCell(columnNumber, Row.RETURN_BLANK_AS_NULL);
                    if (cell == null) {
                        // The spreadsheet is empty in this cell
                        logger.warn("Cell from row {} and column {} from sheet {} is empty",
                                rowNumber, columnNumber, sheet.getSheetName());
                    } else {

                        if (columnNumber == 3){
                            bankAccountBalance = new BankAccountBalance();
                            logger.info("Setting the cash: {}", cell.getNumericCellValue());
                            bankAccountBalance.setCashIn(String.valueOf(cell.getNumericCellValue()));
                        } else if (cell.getStringCellValue().contains("Ordonator:") && bankAccountBalance != null){
                            logger.info("Setting the provider: ", cell.getStringCellValue());
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
        logger.info("Processing the Orders");

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
                    logger.warn("Row {} from sheet {} is empty", rowNumber, sheet.getSheetName());
                    continue;
                }

                // get invoice number
                Cell cell = rowData.getCell(1, Row.RETURN_BLANK_AS_NULL);
                if (cell == null) {
                    // The spreadsheet is empty in this cell
                    logger.warn("Cell from row {} and column {} from sheet {} is empty",
                            rowNumber, 0, sheet.getSheetName());
                } else {
                    logger.info("Setting the invoice number {}", cell.getStringCellValue());
                    order.setInvoiceNumber(cell.getStringCellValue());
                }

                // get invoice value
                cell = rowData.getCell(3, Row.RETURN_BLANK_AS_NULL);
                if (cell == null) {
                    // The spreadsheet is empty in this cell
                    logger.warn("Cell from row {} and column {} from sheet {} is empty{}",
                            rowNumber, 0, sheet.getSheetName());
                } else {
                    logger.info("Setting the invoice value {}", String.valueOf(cell.getNumericCellValue()));
                    order.setInvoiceValue(String.valueOf(cell.getNumericCellValue()));
                }

                // get delivery provider/deliveryType
                cell = rowData.getCell(4, Row.RETURN_BLANK_AS_NULL);
                if (cell == null) {
                    logger.warn("Cell from row {} and column {} from sheet {} is empty{}",
                            rowNumber, 0, sheet.getSheetName());
                } else {
                    logger.info("Setting the deliveryType {}", cell.getStringCellValue());
                    order.setDeliveryType(cell.getStringCellValue());
                }

                // get cashInType
                cell = rowData.getCell(2, Row.RETURN_BLANK_AS_NULL);
                if (cell == null) {
                    logger.warn("Cell from row {} and column {} from sheet {} is empty{}",
                            rowNumber, 0, sheet.getSheetName());
                } else {
                    logger.info("Setting the cashInType {}", cell.getStringCellValue());
                    order.setCashInType(cell.getStringCellValue());
                }

                // get Recipient
                cell = rowData.getCell(5, Row.RETURN_BLANK_AS_NULL);
                if (cell == null) {
                    logger.warn("Cell from row {} and column {} from sheet {} is empty{}",
                            rowNumber, 0, sheet.getSheetName());
                } else {
                    logger.info("Setting the Recipient {}", cell.getStringCellValue());
                    order.setRecipient(cell.getStringCellValue());
                }

                // get Date
                cell = rowData.getCell(0, Row.RETURN_BLANK_AS_NULL);
                if (cell == null) {
                    logger.warn("Cell from row {} and column {} from sheet {} is empty{}",
                            rowNumber, 0, sheet.getSheetName());
                } else {
                    logger.info("Setting the Date {}", cell.getDateCellValue());
                    order.setInvoiceDate(cell.getDateCellValue());
                }

                orderList.add(order);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    return orderList;
}
}
