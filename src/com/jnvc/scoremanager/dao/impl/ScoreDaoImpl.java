package com.jnvc.scoremanager.dao.impl;

import com.jnvc.scoremanager.model.Log;
import com.jnvc.scoremanager.model.Score;
import com.jnvc.scoremanager.other.Factory;
import com.jnvc.scoremanager.ui.Login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.jnvc.scoremanager.dao.LogDao;
import com.jnvc.scoremanager.dao.ScoreDao;
import com.jnvc.scoremanager.db.DBConnection;
/**
 * 成绩业务实现类
 * @author 程宇
 * @serialData 2016年4月25日
 * @version 1.0.0
 */
public class ScoreDaoImpl implements ScoreDao{
	protected Statement stm;
	private String sql;
	protected Connection con;
	private LogDao logdao;
	public ScoreDaoImpl() throws SQLException, ClassNotFoundException{
		con = DBConnection.getConnection();
		stm=(Statement) con.createStatement();
	}
	public int addScore(Score sco) throws SQLException {
		sql = "insert into score set number='"+sco.getNumber()+"',course='"+sco.getCourse()+"',score='"+sco.getScore()+"'";
		int i = stm.executeUpdate(sql);
		if(i>0){
			System.out.println("添加成绩成功");
			sql = "select LAST_INSERT_ID() as id";
			ResultSet rs=stm.executeQuery(sql);
			if(rs.next()){
				//写入日志
				try {
					logdao = Factory.getLogDao();
					Log log = new Log();
					log.setOperate("添加");
					log.setTarget("成绩");
					log.setNewvalue(String.valueOf(sco.getScore()));
					log.setOldvalue(String.valueOf(sco.getId()));
					log.setPerson(Login.person.getName());
					log.setEffect(true);
					logdao.addLog(log);
				} catch (ClassNotFoundException e) {
					System.out.println("添加日志出错");
					e.printStackTrace();
				}
				return rs.getInt("id");
			}
		}
		return 0;
	}
	public boolean updateScore(Score sco) throws SQLException{
		sql = "update score set score='"+sco.getScore()+"' where id="+sco.getId();
		int i = stm.executeUpdate(sql);
		if(i>0){
			//写入日志
			try {
				logdao = Factory.getLogDao();
				Log log = new Log();
				log.setOperate("更新");
				log.setTarget("成绩");
				log.setNewvalue(String.valueOf(sco.getScore()));
				log.setOldvalue(String.valueOf(sco.getId()));
				log.setPerson(Login.person.getName());
				log.setEffect(true);
				logdao.addLog(log);
			} catch (ClassNotFoundException e) {
				System.out.println("添加日志出错");
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	public boolean deleteScore(int id) throws SQLException{
		sql = "delete from score where id="+id;
		int i = stm.executeUpdate(sql);
		if(i>0){
			//写入日志
			try {
				logdao = Factory.getLogDao();
				Log log = new Log();
				log.setOperate("删除");
				log.setTarget("成绩");
				log.setNewvalue("");
				log.setOldvalue(String.valueOf(id));
				log.setPerson(Login.person.getName());
				log.setEffect(true);
				logdao.addLog(log);
			} catch (ClassNotFoundException e) {
				System.out.println("添加日志出错");
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	public Vector selectScore() throws SQLException{
		sql="select score.id,score.number,classes.name as classes,student.name,course.term,course.subject,score.score from score,student,classes,course where course.id=score.course and score.number=student.number and classes.id=student.classes";
		ResultSet rs = stm.executeQuery(sql);
		Vector data = new Vector();
		while(rs.next()){
			Vector row = new Vector();
			row.add(rs.getInt("id"));
			row.add(rs.getString("number"));
			row.add(rs.getString("classes"));
			row.add(rs.getString("name"));
			row.add(rs.getString("term"));
			row.add(rs.getString("subject"));
			row.add(rs.getFloat("score"));
			data.add(row);
		}
		return data;
	}
	public Vector selectScoreBySubject(String subject) throws SQLException{
		sql = "select score.id,score.number,classes.name as classes,student.name,course.term,course.subject,score.score from score,student,classes,course where course.id=score.course and score.number=student.number and classes.id=student.classes and course.subject='"+subject+"'";
		ResultSet rs = stm.executeQuery(sql);
		Vector data = new Vector();
		while(rs.next()){
			Vector row = new Vector();
			row.add(rs.getInt("id"));
			row.add(rs.getString("number"));
			row.add(rs.getString("classes"));
			row.add(rs.getString("name"));
			row.add(rs.getString("term"));
			row.add(rs.getString("subject"));
			row.add(rs.getFloat("score"));
			data.add(row);
		}
		return data;
	}
	public Vector selectScoreByClasses(String classes) throws SQLException{
		sql="select score.id,score.number,classes.name as classes,student.name,course.term,course.subject,score.score from score,student,classes,course where course.id=score.course and score.number=student.number and classes.id=student.classes and classes.name='"+classes+"'";
		ResultSet rs = stm.executeQuery(sql);
		Vector data = new Vector();
		while(rs.next()){
			Vector row = new Vector();
			row.add(rs.getInt("id"));
			row.add(rs.getString("number"));
			row.add(rs.getString("classes"));
			row.add(rs.getString("name"));
			row.add(rs.getString("term"));
			row.add(rs.getString("subject"));
			row.add(rs.getFloat("score"));
			data.add(row);
		}
		return data;
	}
	public Vector selectScoreByNum(String num) throws SQLException{
		sql="select score.id,score.number,classes.name as classes,student.name,course.term,course.subject,score.score from score,student,classes,course where course.id=score.course and score.number=student.number and classes.id=student.classes and student.number='"+num+"'";
		ResultSet rs = stm.executeQuery(sql);
		Vector data = new Vector();
		while(rs.next()){
			Vector row = new Vector();
			row.add(rs.getInt("id"));
			row.add(rs.getString("number"));
			row.add(rs.getString("classes"));
			row.add(rs.getString("name"));
			row.add(rs.getString("term"));
			row.add(rs.getString("subject"));
			row.add(rs.getFloat("score"));
			data.add(row);
		}
		return data;
	}
	public Vector selectScoreByName(String name) throws SQLException {
		sql="select score.id,score.number,classes.name as classes,student.name,course.term,course.subject,score.score from score,student,classes,course where course.id=score.course and score.number=student.number and classes.id=student.classes and student.name='"+name+"'";
		ResultSet rs = stm.executeQuery(sql);
		Vector data = new Vector<Score>();
		while(rs.next()){
			Vector row = new Vector();
			row.add(rs.getInt("id"));
			row.add(rs.getString("number"));
			row.add(rs.getString("classes"));
			row.add(rs.getString("name"));
			row.add(rs.getString("term"));
			row.add(rs.getString("subject"));
			row.add(rs.getFloat("score"));
			data.add(row);
		}
		return data;
	}
	public Vector selectScoreByTerm(String term) throws SQLException {
		sql = "select score.id,score.number,classes.name as classes,student.name,course.term,course.subject,score.score from score,student,classes,course where course.id=score.course and score.number=student.number and classes.id=student.classes and course.term='"+term+"'";
		ResultSet rs = stm.executeQuery(sql);
		Vector data = new Vector();
		while(rs.next()){
			Vector row = new Vector();
			row.add(rs.getInt("id"));
			row.add(rs.getString("number"));
			row.add(rs.getString("classes"));
			row.add(rs.getString("name"));
			row.add(rs.getString("term"));
			row.add(rs.getString("subject"));
			row.add(rs.getFloat("score"));
			data.add(row);
		}
		return data;
	}
	public int getNextId() throws SQLException{
		sql = "select MAX(id)+1 as id from score";
		ResultSet rs = stm.executeQuery(sql);
		if(rs.next()){
			return rs.getInt("id");
		}
		return 0;
	}
	public boolean existScore(String number, String subject, String term) throws SQLException {
		sql = "select * from score where number='"+number+"' and course=(select id from course where subject='"+subject+"' and term='"+term+"')";
		ResultSet rs = stm.executeQuery(sql);
		if(rs.next()){
			return true;
		}
		return false;
	}
}
