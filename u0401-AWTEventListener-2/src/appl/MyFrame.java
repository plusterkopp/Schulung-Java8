package appl;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.annotation.Native;
import java.util.function.Consumer;

public class MyFrame extends Frame {
	private final TextField textFieldFoo = new TextField("Foo", 10);
	private final TextField textFieldBar = new TextField("World", 10);

	interface Handler<T extends AWTEvent> {
		void handle( T e);
	};
	
	class FocusDelegator implements FocusListener {
		final Consumer<FocusEvent> focusGainedHandler;
		final Consumer<FocusEvent> focusLostHandler;
		
		public FocusDelegator( Consumer<FocusEvent> focusGainedHandler, Consumer<FocusEvent> focusLostHandler) {
			this.focusGainedHandler = focusGainedHandler;
			this.focusLostHandler = focusLostHandler;
		}

		@Override
		public void focusGained( FocusEvent e) {
			focusGainedHandler.accept( e);
		}
		@Override
		public void focusLost( FocusEvent e) {
			focusLostHandler.accept( e);
		}
		
	}

	public MyFrame() {
		super("Version 1");
		setLayout(new FlowLayout());
		this.add(textFieldFoo);
		this.add(textFieldBar);
		pack();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				MyFrame.this.dispose();
			}
		});

		textFieldFoo.addFocusListener( new FocusDelegator( 
				e -> textFieldFoo.setBackground(Color.yellow), 
				e -> textFieldFoo.setBackground(Color.white)));

		setVisible(true);
	}
}
