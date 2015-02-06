package utils;

import java.awt.Component;
import java.awt.Container;

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

	@SuppressWarnings( "unchecked")
	public static <T extends Component> void traverse( Component component, Class<T> upperClass, Handler<T> handler) {
		if ( upperClass.isAssignableFrom( component.getClass())) {
			handler.handle( ( T) component);
		}
		if ( component instanceof Container) {
			Container container = ( Container) component;
			for ( int i = 0; i < container.getComponentCount(); i++) {
				traverse( container.getComponent( i), upperClass, handler);
			}
		}
	}

}
