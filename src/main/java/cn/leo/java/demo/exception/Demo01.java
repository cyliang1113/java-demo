package cn.leo.java.demo.exception;

/**
 * 不受检异常, RuntimeException及其子类, 不是必须try-catch
 * 受检异常, 除了RuntimeException及其子类以外, 必须try-catch
 */
public class Demo01 {
    public static void main(String[] args) {
//        hello();
        hello2();
    }

    public static void hello() throws NullPointerException{
        throw new NullPointerException();
    }
    
    public static void hello2() throws IllegalArgumentException{
        throw new IllegalArgumentException();
    }
    
}
