package utils;

import util.ReflectUtil;


public abstract class State {
	protected void enter() {
	}
	protected void exit() {
	}
	@Override
	public String toString() {
		return ReflectUtil.stringOf(this);
	}
}
