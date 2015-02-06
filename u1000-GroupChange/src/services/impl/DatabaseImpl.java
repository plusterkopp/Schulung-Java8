package services.impl;

import java.util.HashMap;
import java.util.Map;

import services.iface.Database;
import model.Customer;
import model.Product;

public class DatabaseImpl implements Database {
	private final Map<Integer,Customer> customers = new HashMap<>();
	private final Map<Integer,Product> products = new HashMap<>();
	
	public DatabaseImpl() {
		this.customers.put(1000, new Customer(1000, "Meier"));
		this.customers.put(2000, new Customer(2000, "Mueller"));
		this.customers.put(3000, new Customer(3000, "Schulte"));
		this.products.put(100, new Product(100, "Veltins", 11));
		this.products.put(200, new Product(200, "Bitburger", 22));
		this.products.put(300, new Product(300, "Krombacher", 33));
	}
	
	public Customer findCustomer(int number) {
		return this.customers.get(number);
	}
	public Product findProduct(int number) {
		return this.products.get(number);
	}
}
