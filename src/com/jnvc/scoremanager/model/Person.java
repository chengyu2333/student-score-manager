package com.jnvc.scoremanager.model;

/**
 * 用户模型类(父类)
 * @author 程宇
 * @serialData 2016年4月22日
 * @version 1.0.0
 */
public class Person {
	private int id;
	protected String name;
	protected String password;
	private int power;
	private String email;
	private String number;
	
	public Person(){}
	
	public Person(String number,String password){
		this.number=number;
		this.password=password;
	}
	
	public Person(String number,String name,String password,String email){
		this.email=email;
		this.number=number;
		this.name=name;
		this.password=password;
	}
	
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
