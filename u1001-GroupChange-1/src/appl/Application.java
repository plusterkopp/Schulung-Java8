package appl;

import services.iface.Database;
import services.iface.OrderPrinter;
import services.iface.OrderReader;
import services.impl.DatabaseImpl;
import services.impl.OrderPrinterImpl;
import services.impl.OrderReaderImpl;


public class Application {
	public static void main(String[] args) {
		OrderReader reader = new OrderReaderImpl("src/orders.txt");
		Database database = new DatabaseImpl();
		OrderPrinter printer = new OrderPrinterImpl();
		OrderGroupChangeProcessor p = new OrderGroupChangeProcessor(reader, database, printer);
		p.run();
	}
}
