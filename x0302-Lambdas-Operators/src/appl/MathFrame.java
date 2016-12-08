package appl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MathFrame extends JFrame {

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

	// private void registerListeners() {
	// this.buttonPlus.addActionListener(e -> this.onCalc(new BinaryOperator() {
	// public int apply(int x, int y) {
	// return x + y;
	// }
	// }));
	// this.buttonMinus.addActionListener(e -> this.onCalc(new BinaryOperator()
	// {
	// public int apply(int x, int y) {
	// return x - y;
	// }
	// }));
	// }

	private void registerListeners() {
		buttonPlus.addActionListener( e -> {
			System.out.println( "xxx");
			// this.onCalc(new BinaryOperator() { public int apply(int x, int y)
			// { return x + y; } });
			onCalc( ( x, y) -> x + y);
		});
		buttonMinus.addActionListener( ( ActionEvent e) -> onCalc( ( x, y) -> x - y));
	}

	private void onCalc( BinaryOperator op) {
		try {
			int x = Integer.parseInt( textFieldX.getText());
			int y = Integer.parseInt( textFieldY.getText());
			int result = op.apply( x, y);
			textFieldResult.setText( String.valueOf( result));
		}
		catch (NumberFormatException e) {
			textFieldResult.setText( "Illegal input");
		}
	}
}
