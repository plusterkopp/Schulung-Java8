package appl;

import model.Customer;
import model.Order;
import model.Product;
import services.iface.Database;
import services.iface.OrderPrinter;
import services.iface.OrderReader;


public class OrderGroupChangeProcessor {
	
	private final OrderReader reader;
	private final Database database;
	private final OrderPrinter printer;
	
	public OrderGroupChangeProcessor(OrderReader reader, Database database, OrderPrinter printer) {
		this.reader = reader;
		this.database = database;
		this.printer = printer;
	}

	public void run() {
		Order order = this.reader.read();
		this.printer.printBegin();
		int totalSum = 0;
		while(order != null) {
			int groupSum = 0;
			final int customerNumber = order.getCustomerNumber();
			final Customer customer = this.database.findCustomer(customerNumber);
			this.printer.printGroupBegin(customer);
			while(order != null && order.getCustomerNumber() == customerNumber) {
				final Product product = this.database.findProduct(order.getProductNumber());
				final int value = order.getAmount() * product.getPrice();
				this.printer.printPosition(order, product, value);
				groupSum += value;
				order = this.reader.read();
			}
			this.printer.printGroupEnd(groupSum);
			totalSum += groupSum;
		}
		this.printer.printEnd(totalSum);
		this.reader.close();
	}
}
