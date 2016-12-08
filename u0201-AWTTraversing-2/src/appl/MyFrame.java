package appl;

import utils.Handler;
import utils.Traverser;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame extends Frame {
	private final Panel panelLeft = new Panel();
	private final Panel panelRight = new Panel();
	private final Button buttonHello = new Button( "Hello");
	private final Button buttonWorld = new Button( "World");
	private final TextField textFieldFoo = new TextField( "Foo", 10);
	private final TextArea textAreaBar = new TextArea( "World", 2, 10);

	public MyFrame() {
		super( "Version 1");
		setLayout( new FlowLayout());
		panelLeft.setLayout( new FlowLayout());
		panelRight.setLayout( new FlowLayout());
		this.add( panelLeft);
		this.add( panelRight);
		panelLeft.add( buttonHello);
		panelLeft.add( textFieldFoo);
		panelRight.add( buttonWorld);
		panelRight.add( textAreaBar);
		pack();
		setVisible( true);
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing( WindowEvent e) {
				MyFrame.this.dispose();
			}
		});
		buttonHello.addActionListener( e -> textComponentsToUpperCase());
		buttonWorld.addActionListener( e -> textComponentsToLowerCase());
	}

	private void textComponentsToUpperCase() {
		Traverser.traverse( this, TextComponent.class, new Handler<TextComponent>() {
			@Override
			public void handle( TextComponent tc) {
				tc.setText( tc.getText().toUpperCase());
			}
		});
	}

	private void textComponentsToLowerCase() {
		Traverser.<TextComponent>traverse( this, TextComponent.class, c -> {
			c.setText( c.getText().toLowerCase());
		});
	}

}
