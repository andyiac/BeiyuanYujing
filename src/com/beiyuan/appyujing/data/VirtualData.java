package com.beiyuan.appyujing.data;

import java.util.ArrayList;
import java.util.List;


public class VirtualData {

	public List<Institute> getInstituteInfo() {
		// TODO Auto-generated method stub
		List<Institute> mlists = new ArrayList<Institute>();
		mlists.add(new Institute("信工学院"));
		mlists.add(new Institute("农林学院"));
		mlists.add(new Institute("经管学院"));
		mlists.add(new Institute("外国语学院"));
		mlists.add(new Institute("医学院"));
		return mlists;
 }


	public List<Specialty> getSpecialtyInfo() {
		// TODO Auto-generated method stub
		List<Specialty> mlists = new ArrayList<Specialty>();
		mlists.add(new Specialty("信工"));
		mlists.add(new Specialty("电子"));
		mlists.add(new Specialty("信息管理与信息系统"));
		mlists.add(new Specialty("计算机科学与技术"));
		return mlists;
	}


	public List<MyClass> getClassInfo() {
		// TODO Auto-generated method stub
		List<MyClass> mlists = new ArrayList<MyClass>();
		mlists.add(new MyClass("信管一班"));
		mlists.add(new MyClass("信管二班"));
		mlists.add(new MyClass("信管三班"));
		mlists.add(new MyClass("信管四班"));
		return mlists;
	}
}
