package cn.leo.java.demo.vm.classload.demo04;

import java.io.IOException;
import java.io.InputStream;

/**
 * -XX:+TraceClassLoading 查看类加载
 */
public class Demo04 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                InputStream is = getResourceAsStream(name);
                if (is == null) {
                    return super.loadClass(name);
                }
                try {
                    byte[] bytes = new byte[is.available()];
                    is.read(bytes);
                    return defineClass(bytes, 0, bytes.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        Class<?> aClass = classLoader.loadClass("cn/leo/java/demo/vm/classload/demo04/Demo04.class");
        Object newInstance = aClass.newInstance();
        System.out.println(newInstance.getClass());
        Class<?> demo04Class = Demo04.class;
        System.out.println(demo04Class);

        System.out.println(newInstance instanceof Demo04);
    }

    private void print() {
        System.out.println("print method");
    }
}
