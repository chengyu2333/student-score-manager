package com.jnvc.scoremanager.model;

/**
 * 学生模型类
 * @author 程宇
 * @serialData 2016年4月25日
 * @version 1.0.0
 */
public class Student{
	private int id;
	private String number;
	private String password;
	private String name;
	private String sex;
	private String classes;
	private String phone;
	private String address;
	private String idcard;
	
	public Student(){}
	/**
	 * @param num
	 * @param na
	 * @param s
	 * @param classes
	 */
	public Student(String num,String na,String s,String classes){
		this.number=num;
		this.name=na;
		this.sex=s;
		this.classes=classes;
	}

	public int getId(){
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getNumber(){
		return this.number;
	}
	public void setNumber(String nu)
	{
		this.number=nu;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName(){
		return this.name;
	}
	public void setName(String na){
		this.name=na;
	}
	
	public String getSex(){
		return this.sex;
	}
	public void setSex(String s){
		this.sex=s;
	}
	
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public String getPhone(){
		return this.phone;
	}
	public boolean setPhone(String ph){
		this.phone=ph;
		return true;
	}
	
	public String getAddress(){
		return this.address;
	}
	public void setAddress(String addr){
		this.address=addr;
	}
	
	public String getIdcard(){
		return this.idcard;
	}
	public void setIdcard(String ic){
		this.idcard=ic;
	}
}
