package interceptorClasses;

import domain.Kweet;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Created by Nekkyou on 22-3-2017.
 */
@Interceptor
@Trends
public class VolgTrendInterceptor {

	@AroundInvoke
	public Object replaceMethod(InvocationContext context) throws Exception {
		Object[] objects = context.getParameters();
		Kweet kweet = (Kweet) objects[0];
		String message = kweet.getMessage();

		message = message.replaceAll("vet", "hard");
		message = message.replaceAll("cool", "dik");

		kweet.setMessage(message);

		objects[0] = kweet;

		context.setParameters(objects);

		return context.proceed();
	}
}
