package org.webjars.demo.service;

import java.util.List;

public interface BaseService <T>{
	
	
	//这个方法只限于插入的表的列于model中的属性一一对应才能使用
	public void addForNotMath(Object[] fileName, Object[] fileValues);
	
	
	public int add(T t);

	public T selectOne(int id);
	
	public List<T> getAll();
	
	
	
	public int update(T t);	
	
	public int delete(int id);
}
