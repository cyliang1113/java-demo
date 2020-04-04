package cn.leo.java.demo.gc.metaspace;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Metaspace(方法区, non heap) 通过cglib不断增加新的类, 元空间不断增大
 * -XX:MaxMetaspaceSize=400m可以设置元空间大小
 * -Xms200m -Xmx200m -XX:MaxMetaspaceSize=400m -XX:+PrintGCDetails
 * -XX:+PrintFlagsInitial
 * @author leo
 */
public class Demo01 {

	public static void main(String[] args) {
		for (; ; ) {
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(OOMObject.class);
			enhancer.setUseCache(false);
			enhancer.setCallback(new MethodInterceptor() {
				@Override
				public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
					return methodProxy.invokeSuper(o, args);
				}
			});
			enhancer.create();
			System.out.println(System.currentTimeMillis());
		}
	}
	static class OOMObject {

	}
}
