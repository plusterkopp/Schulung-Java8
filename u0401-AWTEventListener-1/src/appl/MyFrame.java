package appl;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame extends Frame {
	private final TextField textFieldFoo = new TextField("Foo", 10);
	private final TextField textFieldBar = new TextField("World", 10);

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

		textFieldFoo.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldFoo.setBackground(Color.yellow);
			}
			@Override
			public void focusLost(FocusEvent e) {
				textFieldFoo.setBackground(Color.white);
			}
		});

		setVisible(true);
	}
}
