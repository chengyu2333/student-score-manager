package com.jnvc.scoremanager.model;

/**
 * 班级模型类
 * @author 程宇
 * @serialData 2016年4月25日
 * @version 1.0.0
 */
public class Classes {
	private int id;
	private String name;//班级名
	private String teacher;//班主任
	
	public Classes(){
		
	}
	/**
	 * @param n
	 */
	public Classes(String n)
	{
		this.name=n;
	}
	/**
	 * @param n
	 * @param t
	 */
	public Classes(String n,String t){
		this.name=n;
		this.teacher=t;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	public void setName(String n){
		this.name=n;
	}
	
	public String getTeacher(){
		return this.teacher;
	}
	public void setTeacher(String t){
		this.teacher=t;
	}
	
	
}
