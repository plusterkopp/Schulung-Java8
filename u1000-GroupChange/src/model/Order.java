package model;

public class Order {
	private final int customerNumber;

	private final int productNumber;

	private final int amount;

	public Order(int customerNumber, int productNumber, int amount) {
		this.customerNumber = customerNumber;
		this.productNumber = productNumber;
		this.amount = amount;
	}

	public int getCustomerNumber() {
		return customerNumber;
	}

	public int getProductNumber() {
		return productNumber;
	}

	public int getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return "Order [" + customerNumber + ", " + productNumber + ", " + amount + "]";
	}

}
