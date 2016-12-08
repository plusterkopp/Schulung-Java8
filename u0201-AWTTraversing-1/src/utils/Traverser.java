package utils;

import java.awt.*;

public class Traverser {
	public static void traverse( Component component, Handler handler) {
		handler.handle( component);
		if ( component instanceof Container) {
			Container container = ( Container) component;
			for ( int i = 0; i < container.getComponentCount(); i++) {
				traverse( container.getComponent( i), handler);
			}
		}
	}
}
