package com.beiyuan.appyujing.data;

import java.util.ArrayList;
import java.util.List;


public class VirtualData {

	public List<College> getCollegeInfo() {
		// TODO Auto-generated method stub
		List<College> mlists = new ArrayList<College>();
		mlists.add(new College("信工学院"));
		mlists.add(new College("农林学院"));
		mlists.add(new College("经管学院"));
		mlists.add(new College("外国语学院"));
		mlists.add(new College("医学院"));
		return mlists;
 }
	
	public List<Grade> getGradeInfo() {
		// TODO Auto-generated method stub
		List<Grade> mlists = new ArrayList<Grade>();
		mlists.add(new Grade("2011"));
		mlists.add(new Grade("2012"));
		mlists.add(new Grade("2013"));
		mlists.add(new Grade("2014"));
		mlists.add(new Grade("2015"));
		return mlists;
 }


	public List<Profession> getProfessionInfo() {
		// TODO Auto-generated method stub
		List<Profession> mlists = new ArrayList<Profession>();
		mlists.add(new Profession("信工"));
		mlists.add(new Profession("电子"));
		mlists.add(new Profession("信息管理与信息系统"));
		mlists.add(new Profession("计算机科学与技术"));
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
