package com.beiyuan.appyujing.data;

public class MyClass {

	// 班级

	private String className;

	public MyClass(String name) {
		this.className = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String toString() {
		return "MyClass [className=" + className + "]";
	}

}
