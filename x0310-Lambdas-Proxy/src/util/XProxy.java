package util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class XProxy {
	@SuppressWarnings("unchecked")
	public static <T> T create(final Class<T> iface, final T target, Before before, After after) {
		InvocationHandler handler = new InvocationHandler() {
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				if (before != null)
					before.before(method, args);
				Object result = method.invoke(target, args);
				if (after != null)
					after.after(method, args, result);
				return result;
			}
		};
		return (T) Proxy.newProxyInstance(
				ClassLoader.getSystemClassLoader(),
				new Class<?>[] { iface }, handler);
	}
	public static <T> T create(final Class<T> iface, final T target, After after) {
		return create(iface, target, null, after);
	}
	public static <T> T create(final Class<T> iface, final T target, Before before) {
		return create(iface, target, before, null);
	}
}
