package utils;

import util.ReflectUtil;


public abstract class Event<H> {

	private final Class<H> handlerInterface;

	public Event(Class<H> handlerInterface) {
		this.handlerInterface = handlerInterface;
		//final ParameterizedType t = (ParameterizedType) this.getClass().getGenericSuperclass();
		//this.handlerInterface = (Class<H>) t.getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	public Class<? extends State> dispatch(State state) {
		if (this.handlerInterface.isAssignableFrom(state.getClass())) {
			final H handler = (H) state;
			return this.dispatch(handler);
		}
		return null;
	}

	public abstract Class<? extends State> dispatch(H handler);
	
	@Override
	public String toString() {
		return ReflectUtil.stringOf(this);
	}

}
