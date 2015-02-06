package events;

import utils.Event;
import utils.State;

public class EventC extends Event<EventC.Handler> {

	public interface Handler {
		public abstract Class<? extends State> handle(EventC e);
	}

	public Class<? extends State> dispatch(Handler handler) {
		return handler.handle(this);
	}

	public double d;

	public EventC(double d) {
		super(Handler.class);
		this.d = d;
	}
}
