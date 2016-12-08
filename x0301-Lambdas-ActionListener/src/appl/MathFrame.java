package appl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MathFrame extends JFrame {

	private final JTextField textFieldX = new JTextField(10);
	private final JTextField textFieldY = new JTextField(10);
	private final JButton buttonPlus = new JButton("Plus");
	private final JButton buttonMinus = new JButton("Minus");
	private final JTextField textFieldResult = new JTextField(10);

	public MathFrame() {
		setLayout(new FlowLayout());
		this.add(textFieldX);
		this.add(textFieldY);
		this.add(buttonPlus);
		this.add(buttonMinus);
		this.add(textFieldResult);
		registerListeners();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	//	private void registerListeners() {
	//		this.buttonPlus.addActionListener(new ActionListener() {
	//			public void actionPerformed(ActionEvent e) {
	//				MathFrame.this.onPlus();
	//			}
	//		});
	//		this.buttonMinus.addActionListener(new ActionListener() {
	//			public void actionPerformed(ActionEvent e) {
	//				MathFrame.this.onMinus();
	//			}
	//		});
	//	}
	private void registerListeners() {
		buttonPlus.addActionListener((ActionEvent e) -> { onPlus(); });
		buttonMinus.addActionListener((ActionEvent e) -> { onMinus(); });
	}
	//	private void registerListeners() {
	//		this.buttonPlus.addActionListener((ActionEvent e) -> this.onPlus());
	//		this.buttonMinus.addActionListener((ActionEvent e) -> this.onMinus());
	//	}
	//	private void registerListeners() {
	//		this.buttonPlus.addActionListener((e) -> this.onPlus());
	//		this.buttonMinus.addActionListener((e) -> this.onMinus());
	//	}
	//	private void registerListeners() {
	//		this.buttonPlus.addActionListener(e -> this.onPlus());
	//		this.buttonMinus.addActionListener(e -> this.onMinus());
	//	}



	private void onPlus() {

		//		ActionListener l = (e -> System.out.println("Hello"));
		//		l.actionPerformed(new ActionEvent(this, 0, ""));

		try {
			int x = Integer.parseInt(textFieldX.getText());
			int y = Integer.parseInt(textFieldY.getText());
			int result = x + y;
			textFieldResult.setText(String.valueOf(result));
		}
		catch(NumberFormatException e) {
			textFieldResult.setText("Illegal input");
		}
	}

	private void onMinus() {
		try {
			int x = Integer.parseInt(textFieldX.getText());
			int y = Integer.parseInt(textFieldY.getText());
			int result = x - y;
			textFieldResult.setText(String.valueOf(result));
		}
		catch(NumberFormatException e) {
			textFieldResult.setText("Illegal input");
		}
	}

}
