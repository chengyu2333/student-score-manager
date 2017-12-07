package com.jnvc.scoremanager.dao.impl;
import com.jnvc.scoremanager.model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.jnvc.scoremanager.dao.PersonDao;
import com.jnvc.scoremanager.db.DBConnection;

/**
 * 实现PersonDao类
 * @author 程宇
 * @serialData 2016年4月22日
 * @version 1.0.0
 */
public class PersonDaoImpl implements PersonDao{
	protected Statement stm;
	private String sql;
	protected Connection con;
	public PersonDaoImpl() throws Exception{
		con = DBConnection.getConnection();
		stm=(Statement) con.createStatement();
	}
	
	public Person login(Person per) throws Exception{
		String sql="select * from admin where number='"+per.getNumber()+"' and password='"+per.getPassword()+"'";
		ResultSet rs=stm.executeQuery(sql);

		if(rs.next()){
			per.setPower(rs.getInt("power"));
			per.setName(rs.getString("name"));
			per.setEmail(rs.getString("email"));
			return per;
		}
		return null;
	}
	
	public boolean register(Person per) throws Exception{
		if(!this.userExist("")){
			//注册为管理员
			if(!this.userExist(per.getName())){
				sql="insert into admin(number,name,password,power,email) values('"+per.getNumber()+"','"+per.getName()+"','"+per.getPassword()+"','2','"+per.getEmail()+"')";
				stm.executeUpdate(sql);
//				if(stm!=null){
//					stm.close();
//					con.close();
//				}
				return true;
			}else{
				return false;
			}
			
		}else{
			//注册为教师
			if(this.userExist(per.getNumber())){
				System.out.println("用户名已存在");
			}else{
				//Teacher tea = new Teacher(per.getNumber(),per.getName(),per.getPassword(),per.getEmail());
				if(!this.userExist(per.getName())){
					sql="insert into admin(number,name,password,power,email) values('"+per.getNumber()+"','"+per.getName()+"','"+per.getPassword()+"','0','"+per.getEmail()+"')";
					stm.executeUpdate(sql);
//					if(stm!=null){
//						stm.close();
//						con.close();
//					}
					return true;
				}else{
					JOptionPane.showMessageDialog(null, "该工号已存在");
					return false;
				}
			}
		}
		return false;
	}

	public boolean ChangePassword(Person per) throws SQLException{
		sql="update admin set password='"+per.getPassword()+"' where number='"+per.getNumber()+"'";
		int i = stm.executeUpdate(sql);
		if(i>0){
			return true;
		}
		return false;
	}
	
	public boolean userExist(String number) throws SQLException {
		if(number.equals("")){
			sql = "select id from admin";
		}else{
			sql = "select id from admin where number='"+number+"'";
		}
		
		ResultSet rs = stm.executeQuery(sql);
		if(rs.next()){
			return true;
		}else{
			return false;
		}
	}
}
