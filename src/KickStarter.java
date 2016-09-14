import java.util.List;

public class KickStarter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        String excelFilePath = "C:\\Users\\Bogdan\\workspace\\cuba\\src\\data.xlsx";
        Excel excel = new Excel(excelFilePath);

        List<BankAccountBalance> bankAccountBalanceList = excel.getBankAccountBalance();

        List<Order> orderList = excel.getOrders();


        // mai jos am printat cele 2 liste pentru a vedea ce contin. 
        System.out.println("The final list [size: " + bankAccountBalanceList.size() + "]: ");
        for (BankAccountBalance bankAccountBalance : bankAccountBalanceList){
            System.out.println("Provider: " + bankAccountBalance.getMoneyProvider()
                    + " Cash: " + bankAccountBalance.getCashIn());
        }

        System.out.println("The final list [size: " + orderList.size() + "]: ");
        for (Order order : orderList){
            System.out.println("Invoice number: " + order.getInvoiceNumber()
                    + " invoice value: " + order.getInvoiceValue()
                    + " invoice date: " + order.getInvoiceDate()
                    + " cash in type: " + order.getCashInType()
                    + " recipient: " + order.getRecipient()
                    + " delivery type: " + order.getDeliveryType());
        }

	}

}
