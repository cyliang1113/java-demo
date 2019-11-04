package cn.leo.java.demo.exception;

import java.io.FileNotFoundException;

/**
 * 不受检异常, RuntimeException及其子类, 不是必须try-catch
 * 受检异常, 除了RuntimeException及其子类以外, 必须try-catch
 */
public class Demo02 {
    public static void main(String[] args) {
        try {
			hello();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }

    public static void hello() throws FileNotFoundException{
        throw new FileNotFoundException();
    }
    
    
}
