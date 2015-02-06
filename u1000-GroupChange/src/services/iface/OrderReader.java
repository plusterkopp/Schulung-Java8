package services.iface;

import model.Order;


public interface OrderReader {
	public abstract void close();
	public abstract Order read();
}
