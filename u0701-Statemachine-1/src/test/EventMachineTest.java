package test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import states.Context;
import states.Context.StateX;
import states.Context.StateY;
import states.Context.StateZ;
import utils.StateMachine;
import events.EventA;
import events.EventB;
import events.EventC;

public class EventMachineTest {

	private Context c;
	private StateMachine m;
	
	@Before
	public void setUp() {
		c = new Context();
		m = c.stateMachine;

		
	}
	@Test
	public void testInitialState() {
		Assert.assertSame(m.current(), StateX.class);
	}

	@Test
	public void testStateXEventA() {
		boolean result = m.dispatch(new EventA("Hello"));
		Assert.assertTrue(result);
		Assert.assertSame(m.current(), StateX.class);
	}
	
	@Test
	public void testComplexTransistions() {
		Boolean result = m.dispatch(new EventB(42));
		Assert.assertTrue(result);
		Assert.assertSame(m.current(), StateY.class);
		result = m.dispatch(new EventA("Hello"));
		Assert.assertTrue(result);
		Assert.assertSame(m.current(), StateZ.class);
	}
	
	@Test
	public void testIllegalEvent() {
		Boolean result = m.dispatch(new EventC(3.14));
		Assert.assertFalse(result);
		Assert.assertSame(m.current(), StateX.class);
	}
	
	@Test
	public void testWork() {
		m.dispatch(new EventA("Hello"));
		m.dispatch(new EventA("Hello"));
		m.dispatch(new EventA("Hello"));
		m.dispatch(new EventA("Hello"));
		m.dispatch(new EventA("Hello"));
		Assert.assertEquals(5, c.xa);
	}
	
	
}
