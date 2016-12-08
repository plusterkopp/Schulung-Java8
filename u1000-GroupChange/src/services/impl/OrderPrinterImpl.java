package services.impl;

import model.Customer;
import model.Order;
import model.Product;
import services.iface.OrderPrinter;

public class OrderPrinterImpl implements OrderPrinter {
	public void printBegin() {
		System.out.println("Orders");
	}
	public void printGroupBegin(Customer customer) {
		System.out.printf("   %4d %-10s\n", customer.getNumber(), customer.getName());
	}
	public void printPosition(Order order, Product product, int value) {
		System.out.printf("       %4d %-10s %4d  %4d   %5d\n", 
				order.getProductNumber(), product.getName(), order.getAmount(), product.getPrice(), value);
	}
	public void printGroupEnd(int groupSum) {
		System.out.printf("                                    -----\n");
		System.out.printf("                                    %5d\n", groupSum);
	}
	public void printEnd(int totalSum) {
		System.out.printf("                                    =====\n");
		System.out.printf("                                    %5d\n", totalSum);
	}
}
