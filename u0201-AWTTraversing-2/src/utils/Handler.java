package utils;

import java.awt.Component;


public interface Handler<T extends Component> {
	public abstract void handle(T c);
}
