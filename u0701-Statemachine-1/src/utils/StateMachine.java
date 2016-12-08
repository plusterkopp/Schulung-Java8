package utils;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

public class StateMachine {
	
	private State currentState;
	
	private final Map<Class<? extends State>, State> stateMap = new HashMap<>();
	
	@SuppressWarnings("unchecked")
	public StateMachine(Object context, Class<? extends State> startClass) {
		try {
			final Class<?> contextClass = context.getClass();
			for (final Class<?> cls : contextClass.getClasses()) {
				if (State.class.isAssignableFrom(cls)) {
					final State state = (State)cls.getConstructor(contextClass).newInstance(context);
					this.stateMap.put((Class<? extends State>) cls, state);
				}
			}
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		this.currentState = stateMap.get(startClass);
		if (this.currentState == null)
			throw new RuntimeException("illegal startClass");
		this.currentState.enter();
	}
	
	public StateMachine(State... states) {
		if (states.length == 0)
			throw new IllegalArgumentException();
		this.currentState = states[0];
		for (State state : states) 
			this.stateMap.put(state.getClass(), state);
		this.currentState.enter();
	}
	
	public boolean dispatch(Event<?> e) {
		if (this.currentState == null)
			throw new IllegalStateException("must initialize currentState");
		final String msg = currentState + " # " + e;
		final Class<? extends State> stateClass = e.dispatch(currentState);
		if (stateClass != null) {
			out.printf("%-40s ==> %s\n", msg, stateClass.getSimpleName());
			this.currentState.exit();
			this.currentState = stateMap.get(stateClass);
			this.currentState.enter();
			return true;
		}
		out.printf("%-40s ==> error: cannot dispatch\n", msg);
		return false;
	}
	
	public Class<? extends State> current() {
		return this.currentState.getClass();
	}
}
