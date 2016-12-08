package appl;

import util.TraceHandler;
import util.XProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Application {
	public static void main(String[] args) {
		{
			final MathServiceImpl mathServiceImpl = new MathServiceImpl();
			final InvocationHandler traceHandler = new TraceHandler(mathServiceImpl);
			final MathService mathService = (MathService) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
					new Class[] { MathService.class }, traceHandler);

			System.out.println(mathService.sum(40, 2));
			System.out.println(mathService.diff(40, 2));
		}
		{
			final MathServiceImpl mathServiceImpl = new MathServiceImpl();
			final MathService mathService = XProxy.create(MathService.class, mathServiceImpl,
					(m, a) -> System.out.println(">> " + m.getName() + " " + Arrays.toString(a)),
					(m, a, r) -> System.out.println("<< " + m.getName() + " " + Arrays.toString(a) + " -> " + r));
			System.out.println(mathService.sum(40, 2));
			System.out.println(mathService.diff(40, 2));
		}
	}
}