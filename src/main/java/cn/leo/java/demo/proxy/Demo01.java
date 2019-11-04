package cn.leo.java.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * java 动态代理
 * @author leo
 *
 */
public class Demo01 {
	public static void main(String[] args) {
		// 保存动态代理生成的类到文件中, 需要在项目根目录下创建com/sun/proxy/目录 
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true"); 
		Person proxy = (Person) Proxy.newProxyInstance(Demo01.class.getClassLoader(), new Class[]{Person.class}, new ManHandler(new Man()));
		proxy.say();
	}
}

class Man implements Person{
	@Override
	public void say() {
		System.out.println("我是男人");
	}
	
}

class ManHandler implements InvocationHandler{
	private Object target;
	
	public ManHandler(Object target){
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println(">>>>");
		Object invoke = method.invoke(target, args);
		System.out.println("<<<<");
		return invoke;
	}
	
}
