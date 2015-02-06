package states;

import static util.ReflectUtil.stringOf;
import utils.State;
import utils.StateMachine;
import events.EventA;
import events.EventB;
import events.EventC;


public class Context {
	
	public final StateMachine stateMachine = new StateMachine(this, StateX.class);
	
	public class StateX extends State implements EventA.Handler, EventB.Handler {
		@Override
		protected void enter() {
			System.out.println(">>> " + stringOf(this));
		}
		@Override
		protected void exit() {
			System.out.println("<<< " + stringOf(this));
		}
		
		public Class<? extends State> handle(EventA e) {
			Context.this.xa++;
			return this.getClass();
		}

		public Class<? extends State> handle(EventB e) {
			Context.this.xb++;
			return StateY.class;
		}
	}
	
	public class StateY extends State implements EventA.Handler, EventB.Handler, EventC.Handler {

		public Class<? extends State> handle(EventA e) {
			Context.this.ya++;
			return StateZ.class;
		}
		
		public Class<? extends State> handle(EventB e) {
			Context.this.yb++;
			return this.getClass();
		}

		public Class<? extends State> handle(EventC e) {
			Context.this.yc++;
			return StateX.class;
		}
	}

	public class StateZ extends State implements EventB.Handler, EventC.Handler {

		public Class<? extends State> handle(EventB e) {
			Context.this.zb++;
			return e.i >= 0 ? StateY.class : StateX.class;
		}
		
		@Override
		public Class<? extends State> handle(EventC e) {
			Context.this.zc++;
			return StateX.class;
		}
	}

	public int xa;
	public int xb;
	public int xc;
	public int ya;
	public int yb;
	public int yc;
	public int za;
	public int zb;
	public int zc;
	
	@Override
	public String toString() {
		return stringOf(this);
	}
}
