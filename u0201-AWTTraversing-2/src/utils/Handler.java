package utils;

import java.awt.*;


public interface Handler<T extends Component> {
	public abstract void handle(T c);
}
