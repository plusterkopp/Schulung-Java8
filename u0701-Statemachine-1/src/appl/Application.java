package appl;

import events.EventA;
import events.EventB;
import events.EventC;
import states.Context;
import utils.StateMachine;

public class Application {

	public static void main(String[] args) {
		final Context c = new Context();
		final StateMachine m = c.stateMachine;
		
		m.dispatch(new EventA("Hello"));
		m.dispatch(new EventB(42));
		m.dispatch(new EventB(43));
		m.dispatch(new EventC(3.14));
		m.dispatch(new EventA("World"));
		m.dispatch(new EventC(2.71));
		m.dispatch(new EventA("OOD"));
		m.dispatch(new EventB(77));
		m.dispatch(new EventA("Pascal"));
		m.dispatch(new EventB(44));
		m.dispatch(new EventA("Oberon"));
		m.dispatch(new EventC(9.81));
		System.out.println(c);
	}

}





