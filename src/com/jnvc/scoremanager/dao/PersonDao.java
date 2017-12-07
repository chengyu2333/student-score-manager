package com.jnvc.scoremanager.dao;

import java.sql.SQLException;

import com.jnvc.scoremanager.model.Person;

//import java.sql.SQLException;

public interface PersonDao {
	/** 
	 * 用户注册
	 * @param oPerson
	 * @return boolean
	 * @author 程宇
	 * @serialData May 9, 2016:2:41:13 PM
	 * @version 1.0.0
	 */
	public abstract boolean register(Person per) throws Exception;
	
	/** 
	 * 用户登录
	 * @param Person
	 * @return Person
	 * @author 程宇
	 * @serialData May 9, 2016:2:41:03 PM
	 * @version 1.0.0
	 */
	public abstract Person login(Person per) throws Exception;
	
	/** 
	 * 更改密码
	 * @param Person
	 * @return boolean
	 * @author 程宇
	 * @serialData May 9, 2016:2:41:03 PM
	 * @version 1.0.0
	 */
	public abstract boolean ChangePassword(Person per) throws SQLException;
	/**
	 * 用户是否存在
	 * @param username 为空字符串检查全部用户
	 * @return
	 * @throws Exception
	 */
	public abstract boolean userExist(String username) throws Exception;
}
