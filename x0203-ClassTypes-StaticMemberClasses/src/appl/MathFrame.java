package appl;

import util.Features;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MathFrame extends JFrame {

	private static class ButtonPlusAdapter implements ActionListener {
		final MathFrame mathFrame;

		public ButtonPlusAdapter(MathFrame mathFrame) {
			this.mathFrame = mathFrame;
		}

		@Override
		public void actionPerformed( ActionEvent e) {
			Features.print( this.getClass());
			mathFrame.onPlus();
		}
	}

	private static class ButtonMinusAdapter implements ActionListener {
		final MathFrame mathFrame;

		public ButtonMinusAdapter(MathFrame mathFrame) {
			this.mathFrame = mathFrame;
		}

		@Override
		public void actionPerformed( ActionEvent e) {
			mathFrame.onMinus();
		}
	}

	private final JTextField textFieldX = new JTextField( 10);
	private final JTextField textFieldY = new JTextField( 10);
	private final JButton buttonPlus = new JButton( "Plus");
	private final JButton buttonMinus = new JButton( "Minus");
	private final JTextField textFieldResult = new JTextField( 10);

	public MathFrame() {
		setLayout( new FlowLayout());
		this.add( textFieldX);
		this.add( textFieldY);
		this.add( buttonPlus);
		this.add( buttonMinus);
		this.add( textFieldResult);
		registerListeners();
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible( true);
	}

	private void registerListeners() {
		buttonPlus.addActionListener( new ButtonPlusAdapter( this));
		buttonMinus.addActionListener( new ButtonMinusAdapter( this));
	}

	private void onPlus() {
		try {
			int x = Integer.parseInt( textFieldX.getText());
			int y = Integer.parseInt( textFieldY.getText());
			int result = x + y;
			textFieldResult.setText( String.valueOf( result));
		}
		catch (NumberFormatException e) {
			textFieldResult.setText( "Illegal input");
		}
	}

	private void onMinus() {
		try {
			int x = Integer.parseInt( textFieldX.getText());
			int y = Integer.parseInt( textFieldY.getText());
			int result = x - y;
			textFieldResult.setText( String.valueOf( result));
		}
		catch (NumberFormatException e) {
			textFieldResult.setText( "Illegal input");
		}
	}

}
