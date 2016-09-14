import java.util.Date;

public class Order {
	private Date invoiceDate;
	private String invoiceNumber;
	private String cashInType;
	private String invoiceValue;
	private String deliveryType;
	private String recipient;

	public Date getInvoiceDate() {	return invoiceDate;	}

	public void setInvoiceDate(Date invoiceDate) {this.invoiceDate = invoiceDate;}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getCashInType() {	return cashInType;	}

	public void setCashInType(String cashInType) {this.cashInType = cashInType;	}

	public String getInvoiceValue() {
		return invoiceValue;
	}

	public void setInvoiceValue(String invoiceValue) {
		this.invoiceValue = invoiceValue;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getRecipient() {return recipient;	}

	public void setRecipient(String recipient) {this.recipient = recipient;	}

}
