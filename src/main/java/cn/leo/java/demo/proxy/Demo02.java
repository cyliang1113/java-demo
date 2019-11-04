package cn.leo.java.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * java 动态代理
 * 接口没实现类, 直接使用动态代理为接口生成实现类, mybatis也是使用类似的方式
 * @author leo
 *
 */
public class Demo02 {
	public static void main(String[] args) {
		// 保存动态代理生成的类到文件中, 需要在项目根目录下创建com/sun/proxy/目录 
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true"); 
		Person proxy = (Person) Proxy.newProxyInstance(Demo02.class.getClassLoader(), new Class[]{Person.class}, new WonmanHandler());
		proxy.say();
		
	}
}

class WonmanHandler implements InvocationHandler{

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println(proxy.getClass().getName());
		System.out.println("接口:");
		Class<?>[] interfaces = proxy.getClass().getInterfaces();
		for (Class<?> interf : interfaces) {
			System.out.println(interf.getName());
		}
		Class<?> superclass = proxy.getClass().getSuperclass();
		System.out.println("父类:");
		System.out.println(superclass.getName());
		System.out.println("方法:");
		System.out.println(method.getName());
		return null;
	}
	
}
