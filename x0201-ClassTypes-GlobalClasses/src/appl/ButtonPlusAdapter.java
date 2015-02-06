package appl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import util.Features;

public class ButtonPlusAdapter implements ActionListener {
	final MathFrame mathFrame;
	public ButtonPlusAdapter(MathFrame mathFrame) {
		this.mathFrame = mathFrame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Features.print(this.getClass());
		mathFrame.onPlus();
	}

}
