package cn.leo.java.demo.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;


/**
 * 多线程控制:
 * 1, 临界资源不能被多线程同时访问, 需要依次去访问 ,这是简单的多线程控制; (互斥)
 * 2, 多线程之间需要协调工作, 如: 生产者消费者问题; (同步)
 */
public class Demo01 {
    public static void main(String[] args) {
        ReentrantLock rl = new ReentrantLock();


    }
}
