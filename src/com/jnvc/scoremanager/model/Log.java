package com.jnvc.scoremanager.model;

/**
 * 日志模型类
 * @author 程宇
 * @serialData 2016年4月25日
 * @version 1.0.0
 */
public class Log {
	private int id;
	private String operate;
	private String target;
	private String oldvalue;
	private String newvalue;
	private String time;
	private String person;
	private boolean effect;
	
	public boolean isEffect() {
		return effect;
	}
	public void setEffect(boolean effect) {
		this.effect = effect;
	}
	public Log(){
		
	}
	/**
	 * @param op
	 * @param target
	 * @param before
	 * @param newvalue
	 * @param datetime
	 */
	public Log(String op,String target,String oldvalue,String newvalue,String person){
		this.operate=op;
		this.target=target;
		this.oldvalue=oldvalue;
		this.newvalue=newvalue;
		this.person=person;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getOldvalue() {
		return oldvalue;
	}
	public void setOldvalue(String oldvalue) {
		this.oldvalue = oldvalue;
	}
	public String getNewvalue() {
		return newvalue;
	}
	public void setNewvalue(String newvalue) {
		this.newvalue = newvalue;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	
}
