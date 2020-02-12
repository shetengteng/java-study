package com.stt.thread;


import java.util.concurrent.atomic.AtomicReference;

class User{
	String name;
	int age;
	public User(String n, int age) {
		this.name = n;
		this.age = age;
	}
	@Override
	public String toString() {
		return name +":"+age;
	}
}

public class ch04_atomicReferenceDemo {
	public static void main(String[] args) {

		User u1 = new User("u1",22);
		User u2 = new User("u2",23);

		AtomicReference<User> ru1 = new AtomicReference<>(u1);

		System.out.println(ru1.compareAndSet(u1,u2)+" current:"+ru1.get());
		System.out.println(ru1.compareAndSet(u1,u2)+" current:"+ru1.get());

	}
}
