package services.iface;

import model.Customer;
import model.Product;


public interface Database {
	public abstract Customer findCustomer(int number);
	public abstract Product findProduct(int number);
}
