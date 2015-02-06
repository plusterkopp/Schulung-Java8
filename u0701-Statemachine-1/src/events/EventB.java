package events;

import utils.Event;
import utils.State;

public class EventB extends Event<EventB.Handler> {
	
	public interface Handler {
		public abstract Class<? extends State> handle(EventB e);
	}

	public Class<? extends State> dispatch(Handler handler) {
		return handler.handle(this);
	}
	
	public int i;
	
	public EventB(int i) {
		super(Handler.class);
		this.i = i;
	}
}
