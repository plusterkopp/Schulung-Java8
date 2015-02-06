package events;

import utils.Event;
import utils.State;

public class EventA extends Event<EventA.Handler> {
	
	public interface Handler {
		public abstract Class<? extends State> handle(EventA e);
	}

	public Class<? extends State> dispatch(Handler handler) {
		return handler.handle(this);
	}
	
	public final String s;
	
	public EventA(String s) {
		super(Handler.class);
		this.s = s;
	}
}
