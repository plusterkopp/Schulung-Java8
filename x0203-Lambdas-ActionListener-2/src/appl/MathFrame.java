package appl;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class MathFrame extends JFrame {
	
	private final JTextField textFieldX = new JTextField(10);
	private final JTextField textFieldY = new JTextField(10);
	private final JButton buttonSum = new JButton("Sum");
	private final JButton buttonDiff = new JButton("Diff");
	private final JTextField textFieldResult = new JTextField(10);
	
	public MathFrame() {
		this.setLayout(new FlowLayout());
		this.add(this.textFieldX);
		this.add(this.textFieldY);
		this.add(this.buttonSum);
		this.add(this.buttonDiff);
		this.add(this.textFieldResult);
		
		this.buttonSum.addActionListener((e) -> this.onSum());
		this.buttonDiff.addActionListener((e) -> this.onDiff());

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	public void onSum() {
		try {
			int x = Integer.parseInt(this.textFieldX.getText());
			int y = Integer.parseInt(this.textFieldY.getText());
			int result = x + y;
			this.textFieldResult.setText(String.valueOf(result));
		}
		catch(NumberFormatException e) {
			this.textFieldResult.setText("Bad input");
		}
	}
	
	public void onDiff() {
		try {
			int x = Integer.parseInt(this.textFieldX.getText());
			int y = Integer.parseInt(this.textFieldY.getText());
			int result = x - y;
			this.textFieldResult.setText(String.valueOf(result));
		}
		catch(NumberFormatException e) {
			this.textFieldResult.setText("Bad input");
		}
	}

}
