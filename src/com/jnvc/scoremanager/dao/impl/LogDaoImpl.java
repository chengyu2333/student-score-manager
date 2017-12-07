package com.jnvc.scoremanager.dao.impl;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;

import com.jnvc.scoremanager.model.Log;
import com.jnvc.scoremanager.model.Student;
import com.jnvc.scoremanager.dao.LogDao;
import com.jnvc.scoremanager.db.DBConnection;

/**
 * 日志业务实现类
 * @author 程宇
 * @serialData 2016年4月25日
 * @version 1.0.0
 */
public class LogDaoImpl implements LogDao{
	protected Statement stm;
	private String sql;
	protected Connection con;
	public LogDaoImpl() throws SQLException, ClassNotFoundException{
		con = DBConnection.getConnection();
		stm=(Statement) con.createStatement();
	}
	public boolean addLog(Log log) throws SQLException{
		Date date = new Date();
		int effect;
		if(log.isEffect()==true){
			effect=1;
		}else{
			effect=0;
		}
			
		sql="insert into log(effect,newvalue,oldvalue,opreate,target,person,time) values('"+effect+"','"+log.getNewvalue()+"','"+log.getOldvalue()+"','"+log.getOperate()+"','"+log.getTarget()+"','"+log.getPerson()+"','"+(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"')";
		int i = stm.executeUpdate(sql);
		if(i>0){
			return true;
		}
		return false;
	}
	public Vector selectLogByEffect(boolean effect)  throws SQLException{
		return null;
	}
	public Vector selectLogByTime(String datetime) throws SQLException{
		System.out.println("按时间查找日志");
		return null;
	}
	public Vector selectLogById(int id) throws SQLException{
		System.out.println("按id查找日志");
		return null;
	}
	public boolean deleteLogBytime(Date date)  throws SQLException{
		return false;
	}

	public Vector selectLog() throws SQLException{
		sql="select * from log";
		Vector data = new Vector<Student>();
		ResultSet rs = stm.executeQuery(sql);
		
		while(rs.next()){
			Vector row = new Vector();
			row.add(rs.getInt("id"));
			row.add(rs.getString("opreate"));
			row.add(rs.getString("target"));
			row.add(rs.getString("oldvalue"));
			row.add(rs.getString("newvalue"));
			row.add(rs.getString("person"));
			row.add(rs.getBoolean("effect"));
			row.add(rs.getDate("time")+" "+rs.getTime("time"));
			data.add(row);
		}
		return data;
	}
}
