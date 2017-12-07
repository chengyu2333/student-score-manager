package com.jnvc.scoremanager.model;

/**
 * 成绩模型类
 * @author 程宇
 * @serialData 2016年4月25日
 * @version 1.0.0
 */
public class Score extends Student{
	private int id;
	private String number;
	private int course;
	private float score;
	
	public Score(){}
	
	/**
	 * @param num
	 * @param cou
	 * @param sc
	 */
	public Score(String num,int cou,float sc){
		this.number=num;
		this.course=cou;
		this.score=sc;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getCourse() {
		return course;
	}

	public void setCourse(int course) {
		this.course = course;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	
}
