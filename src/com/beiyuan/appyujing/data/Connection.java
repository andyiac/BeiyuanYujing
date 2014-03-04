package com.beiyuan.appyujing.data;

import java.util.List;

public interface Connection {
	public List<Institute> getInstituteInfo(); 
	public List<Specialty> getSpecialtyInfo();
	public List<MyClass> getClassInfo();

}
