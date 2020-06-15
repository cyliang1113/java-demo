package cn.leo.java.demo.serialize;

import java.io.*;

public class Demo02 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Person tom = new Person("Tom", 25);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("F:/xx.data"));

        oos.writeObject(tom);

        oos.flush();

        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("F:/xx.data"));

        Object o = ois.readObject();

        System.out.println(o.getClass());

    }

    public static class Person implements Serializable{
        private static final long serialVersionUID = 5607190525122542388L;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String name;
        public Integer age;
    }
}
