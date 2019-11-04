package cn.leo.java.demo.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

import cn.leo.java.demo.protobuf.PersonProto.Person;
import cn.leo.java.demo.protobuf.PersonProto.Person.Builder;

public class Demo {
	public static void main(String[] args) throws InvalidProtocolBufferException {
		Builder builder = PersonProto.Person.newBuilder();
		builder.setId(100);
		builder.setName("ant112");
		// builder.setEmail("ghb@soecode.com");
		PersonProto.Person person = builder.build();
		System.out.println(person.toString());

		byte[] byteArray = person.toByteArray(); // 序列化
		System.out.println(byteArray.length);
		for (byte b : byteArray) {
			System.out.print(b);
		}
		
		System.out.println("");
		System.out.println("");

		Person person2 = PersonProto.Person.parseFrom(byteArray); // 反序列化
		System.out.println(person2.toString());

	}
}
