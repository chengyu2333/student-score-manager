package com.jnvc.scoremanager.dao.impl;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.jnvc.scoremanager.dao.ClassesDao;
import com.jnvc.scoremanager.dao.LogDao;
import com.jnvc.scoremanager.db.DBConnection;
import com.jnvc.scoremanager.model.Classes;
import com.jnvc.scoremanager.model.Log;
import com.jnvc.scoremanager.other.Factory;
import com.jnvc.scoremanager.ui.Login;

/**
 * 班级业务实现类
 * @author 程宇
 * @serialData 2016年4月22日:下午8:35:17
 * @version 1.0.0
 */
public class ClassesDaoImpl implements ClassesDao{
	protected Statement stm;
	private String sql;
	protected Connection con;
	private LogDao logdao;
	
	public ClassesDaoImpl() throws SQLException, ClassNotFoundException{
		con = DBConnection.getConnection();
		stm=(Statement) con.createStatement();
	}
	public int addClasses(Classes cla) throws SQLException{
		sql = "insert into classes(name,teacher) values('"+cla.getName()+"',(select number from admin where name='"+cla.getTeacher()+"'))";
		int i = stm.executeUpdate(sql);
		if(i>0){
			//写入日志
			try {
				logdao = Factory.getLogDao();
				Log log = new Log();
				log.setOperate("添加");
				log.setTarget("班级");
				log.setNewvalue(cla.getName());
				log.setOldvalue("");
				log.setPerson(Login.person.getName());
				log.setEffect(true);
				logdao.addLog(log);
			} catch (ClassNotFoundException e) {
				System.out.println("添加日志出错");
				e.printStackTrace();
			}
			sql = "select LAST_INSERT_ID() as id";
			ResultSet rs=stm.executeQuery(sql);
			if(rs.next()){
				return rs.getInt("id");
			}
		}
		return 0;
	}
	public boolean deleteClasses(int id) throws SQLException{
		sql = "delete from classes where id='"+id+"'";
		int i = stm.executeUpdate(sql);
		if(i>0){
			System.out.println("删除班级成功");
			//写入日志
			try {
				logdao = Factory.getLogDao();
				Log log = new Log();
				log.setOperate("删除");
				log.setTarget("班级");
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
	public boolean updateClasses(Classes cla) throws SQLException{
		sql = "update classes set name='"+cla.getName()+"',teacher=(select number from admin where name='"+cla.getTeacher()+"') where id='"+cla.getId()+"'";
		int i = stm.executeUpdate(sql);
		if(i>0){
			System.out.println("更新班级信息成功");
			//写入日志
			try {
				logdao = Factory.getLogDao();
				Log log = new Log();
				log.setOperate("更新");
				log.setTarget("班级");
				log.setNewvalue(cla.getName());
				log.setOldvalue(String.valueOf(cla.getId()));
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
	
	public Vector selectClassesName() throws SQLException{
		sql = "select name from classes";
		ResultSet rs = stm.executeQuery(sql);
		Vector data = new Vector();
		while(rs.next()){
			data.add(rs.getString("name"));
		}
		return data;
	}
	
	public Vector selectClasses() throws SQLException{
		sql = "select classes.id,classes.name,admin.name as teacher from classes,admin where admin.number=classes.teacher";
		ResultSet rs = stm.executeQuery(sql);
		Vector data = new Vector();
		while(rs.next()){
			Vector row = new Vector();
			row.add(rs.getInt("id"));
			row.add(rs.getString("name"));
			row.add(rs.getString("teacher"));
			data.add(row);
		}
		return data;
	}
	public Vector selectClassesByTeacher(String name) throws SQLException{
		sql = "select classes.id,classes.name,admin.name as teacher from classes,admin where admin.number=classes.teacher and classes.teacher=(select number from admin where name='"+name+"')";
		ResultSet rs = stm.executeQuery(sql);
		Vector data = new Vector();
		while(rs.next()){
			Vector row = new Vector();
			row.add(rs.getInt("id"));
			row.add(rs.getString("name"));
			row.add(rs.getString("teacher"));
			data.add(row);
		}
		return data;
	}
	public Vector selectClassesByName(String name) throws SQLException{
		sql = "select classes.id,classes.name,admin.name as teacher from classes,admin where admin.number=classes.teacher and classes.name='"+name+"'";
		ResultSet rs = stm.executeQuery(sql);
		Vector data = new Vector();
		while(rs.next()){
			Vector row = new Vector();
			row.add(rs.getInt("id"));
			row.add(rs.getString("name"));
			row.add(rs.getString("teacher"));
			data.add(row);
		}
		return data;
	}
	public Vector selectClassesById(int id) throws SQLException{
		sql = "select classes.id,classes.name,admin.name as teacher from classes,admin where admin.number=classes.teacher and classes.teacher=(select number from admin where id='"+id+"')";
		ResultSet rs = stm.executeQuery(sql);
		Vector data = new Vector();
		while(rs.next()){
			Vector row = new Vector();
			row.add(rs.getInt("id"));
			row.add(rs.getString("name"));
			row.add(rs.getString("teacher"));
			data.add(row);
		}
		return data;
	}
	public int getNextId() throws SQLException{
		sql = "select MAX(id)+1 as id from classes";
		ResultSet rs = stm.executeQuery(sql);
		if(rs.next()){
			return rs.getInt("id");
		}
		return 0;
	}
}
