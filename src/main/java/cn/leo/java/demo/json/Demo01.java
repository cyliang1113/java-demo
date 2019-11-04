package cn.leo.java.demo.json;

import com.alibaba.fastjson.JSON;

import javax.naming.Name;

public class Demo01 {
    public static void main(String[] args) {
        Person tom = new Person("tom");
        String s = JSON.toJSONString(tom);
        System.out.print(s);
    }

    private static class Person{
        private String name;

        Person(String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
