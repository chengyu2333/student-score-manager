package com.jnvc.scoremanager.model;

/**
 * 课程模型类
 * @author 程宇
 * @serialData 2016年4月25日
 * @version 1.0.0
 */
public class Course {
	private int id;
	private String subject;//科目
	private int credit;//学分
	private String term;//学年
	private String teacher;//任课教师
	
	public Course(){
		
	}
	/**
	 * @param sub
	 * @param cre
	 * @param term
	 * @param teacher
	 */
	public Course(String sub,int cre,String term,String teacher){
		this.subject=sub;
		this.credit=cre;
		this.term=term;
		this.teacher=teacher;
	}
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
