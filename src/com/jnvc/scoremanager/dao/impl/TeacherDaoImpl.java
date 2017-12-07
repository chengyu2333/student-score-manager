package com.jnvc.scoremanager.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.jnvc.scoremanager.dao.LogDao;
import com.jnvc.scoremanager.dao.TeacherDao;
import com.jnvc.scoremanager.model.Log;
import com.jnvc.scoremanager.model.Student;
import com.jnvc.scoremanager.other.Factory;
import com.jnvc.scoremanager.ui.Login;

/**
 * 教师业务实现类
 * @author 程宇
 * @serialData 2016年4月25日
 * @version 1.0.0
 */
public class TeacherDaoImpl extends PersonDaoImpl implements TeacherDao{
	private String sql;
	private LogDao logdao;
	public TeacherDaoImpl() throws Exception {
		super();
	}
	
	public Vector selectStudent() throws SQLException{
		sql="select student.*,classes.name as class from student,classes where classes.id=student.classes";
		Vector data = new Vector<Student>();
		ResultSet rs = stm.executeQuery(sql);
		
		while(rs.next()){
			Vector row = new Vector();
			row.add(rs.getInt("id"));
			row.add(rs.getString("number"));
			row.add(rs.getString("password"));
			row.add(rs.getString("class"));
			row.add(rs.getString("name"));
			row.add(rs.getString("sex"));
			row.add(rs.getString("phone"));
			row.add(rs.getString("idcard"));
			row.add(rs.getString("address"));
			data.add(row);
		}
		return data;
	}
	public Vector selectStudentByName(String name) throws SQLException{
		sql="select student.*,classes.name as class from student,classes where classes.id=student.classes and student.name='"+name+"'";
		Vector data = new Vector();
		ResultSet rs = stm.executeQuery(sql);
		
		while(rs.next()){
			Vector row = new Vector();
			row.add(rs.getInt("id"));
			row.add(rs.getString("number"));
			row.add(rs.getString("password"));
			row.add(rs.getString("class"));
			row.add(rs.getString("name"));
			row.add(rs.getString("sex"));
			row.add(rs.getString("phone"));
			row.add(rs.getString("idcard"));
			row.add(rs.getString("address"));
			data.add(row);
		}
		return data;
	}
	public Vector selectStudentByClasses(String name) throws SQLException{
		sql="select student.*,classes.name as class from student,classes where classes.id=student.classes and student.classes=(select id from classes where name='"+name+"')";
		Vector data = new Vector();
		ResultSet rs = stm.executeQuery(sql);
		
		while(rs.next()){
			Vector row = new Vector();
			row.add(rs.getInt("id"));
			row.add(rs.getString("number"));
			row.add(rs.getString("password"));
			row.add(rs.getString("class"));
			row.add(rs.getString("name"));
			row.add(rs.getString("sex"));
			row.add(rs.getString("phone"));
			row.add(rs.getString("idcard"));
			row.add(rs.getString("address"));
			data.add(row);
		}
		return data;
	}
	public Vector selectStudentByNum(String num) throws SQLException{
		sql="select student.*,classes.name as class from student,classes where classes.id=student.classes and number='"+num+"'";
		ResultSet rs = stm.executeQuery(sql);
		Vector data = new Vector();
		Vector row = new Vector();
		if(rs.next()){
			row.add(rs.getInt("id"));
			row.add(rs.getString("number"));
			row.add(rs.getString("password"));
			row.add(rs.getString("class"));
			row.add(rs.getString("name"));
			row.add(rs.getString("sex"));
			row.add(rs.getString("phone"));
			row.add(rs.getString("idcard"));
			row.add(rs.getString("address"));
			data.add(row);
		}
		return data;
	}
	
	public int addStudent(Student stu) throws SQLException{
		sql="insert into student(number,name,password,classes,sex,address,idcard,phone) values('"+stu.getNumber()+"','"+stu.getName()+"','"+stu.getPassword()+"',(select id from classes where name='"+stu.getClasses()+"'),'"+stu.getSex()+"','"+stu.getAddress()+"','"+stu.getIdcard()+"','"+stu.getPhone()+"')";
		int i=stm.executeUpdate(sql);
		if(i==1){
			sql = "select LAST_INSERT_ID() as id";
			ResultSet rs=stm.executeQuery(sql);
			if(rs.next()){
				//写入日志
				try {
					logdao = Factory.getLogDao();
					Log log = new Log();
					log.setOperate("添加");
					log.setTarget("学生");
					log.setNewvalue(stu.getName());
					log.setOldvalue("");
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
	public boolean updateStudent(Student stu) throws SQLException{
		sql="update student set name='"+stu.getName()+"',password='"+stu.getPassword()+"',classes=(select id from classes where name='"+stu.getClasses()+"'),sex='"+stu.getSex()+"',address='"+stu.getAddress()+"',idcard='"+stu.getIdcard()+"',phone='"+stu.getPhone()+"' where number='"+stu.getNumber()+"'";
		int i=stm.executeUpdate(sql);
		if(i>0){
			//写入日志
			try {
				logdao = Factory.getLogDao();
				Log log = new Log();
				log.setOperate("更新");
				log.setTarget("学生");
				log.setNewvalue(stu.getName());
				log.setOldvalue(String.valueOf(stu.getId()));
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
	public boolean deleteStudentByNumber(String number) throws SQLException{
		sql="delect from student where number='"+number+"'";
		int i = stm.executeUpdate(sql);
		if(i>0){
			//写入日志
			try {
				logdao = Factory.getLogDao();
				Log log = new Log();
				log.setOperate("删除");
				log.setTarget("学生");
				log.setNewvalue("");
				log.setOldvalue(number);
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
	public int getNextId() throws SQLException{
		sql = "select MAX(id)+1 as id from student";
		ResultSet rs = stm.executeQuery(sql);
		if(rs.next()){
			return rs.getInt("id");
		}
		return 0;
	}
}
