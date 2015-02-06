package util;

import java.lang.reflect.Method;

public interface After {
	abstract public void after(Method m, Object[] args, Object result);
}
