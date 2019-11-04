package cn.leo.java.demo.serialize;


import lombok.Data;

import java.io.*;

public class Demo01 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person tom = new Person("Tom");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(tom);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Object o = objectInputStream.readObject();
        System.out.println(o);
    }

    @Data
    private static class Person implements Serializable {


        private static final long serialVersionUID = 4813866994228597867L;

        public Person(String name) {
            this.name = name;
        }

        public String name;
    }
}



