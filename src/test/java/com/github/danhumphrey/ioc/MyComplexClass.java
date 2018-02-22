package com.github.danhumphrey.ioc;

public class MyComplexClass implements MyTestInterface{

	private String name;

	public MyComplexClass(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "My name is " + this.name;
	}

	@Override
	public boolean someInterfaceMethod() {
		return false;
	}
}

