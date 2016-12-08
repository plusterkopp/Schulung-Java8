package services.impl;

import model.Order;
import services.iface.OrderReader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class OrderReaderImpl implements OrderReader {
	private BufferedReader reader;

	public OrderReaderImpl(String filename) {
		try {
			this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void close() {
		if (reader == null)
			throw new RuntimeException("reader not open");
		try {
			reader.close();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Order read() {
		try {
			for(String line = reader.readLine(); line != null; line = reader.readLine()) {
				line = line.trim();
				if (line.isEmpty())
					continue;
				String[] tokens = line.split(",");
				int customerNumber = Integer.parseInt(tokens[0].trim());
				int productNumber = Integer.parseInt(tokens[1].trim());
				int amount = Integer.parseInt(tokens[2].trim());
				return new Order(customerNumber, productNumber, amount);
			}
			return null;
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
