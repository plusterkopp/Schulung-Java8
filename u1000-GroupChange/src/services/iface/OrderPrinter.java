package services.iface;

import model.Customer;
import model.Order;
import model.Product;

public interface OrderPrinter {
	public abstract void printBegin();
	public abstract void printGroupBegin(Customer customer);
	public abstract void printPosition(Order order, Product product, int value);
	public abstract void printGroupEnd(int groupSum);
	public abstract void printEnd(int totalSum);
}
