package com.beiyuan.appyujing.data;

import java.util.List;

public interface Connection {
	public List<College> getInstituteInfo(); 
	public List<Grade> getGradeInfo(); 
	public List<Profession> getSpecialtyInfo();
	public List<MyClass> getClassInfo();

}
