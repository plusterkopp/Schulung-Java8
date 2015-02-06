package util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TraceHandler implements InvocationHandler {
	
	private final Object target;

	public TraceHandler(Object target) {
		this.target = target;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println(">> " + method.getName() + " " + Arrays.toString(args));
		Object result = method.invoke(this.target, args);
		System.out.println("<< " + method.getName() + " " + Arrays.toString(args) + " -> " + result);
		return result;
	}
}
