package com.jnvc.scoremanager.dao;
import com.jnvc.scoremanager.model.Log;

import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

/**
 * 日志业务接口 
 * @author 程宇
 * @serialData 2016年4月25日:下午8:05:27
 * @version 1.0.0
 */
public interface LogDao {
	/** 
	 *  添加日志信息
	 * @return boolean 返回是否添加成功
	 * @author 程宇
	 * @serialData 2016年4月25日:下午9:24:53
	 * @version 1.0.0
	 */ 
	public boolean addLog(Log log) throws SQLException;
	
	/** 
	 *  删除某时间以前的日志 
	 * @param log
	 * @return boolean 返回是否删除成功
	 * @author 程宇
	 * @serialData 2016年4月25日:下午9:26:43
	 * @version 1.0.0
	 */ 
	public boolean deleteLogBytime(Date time) throws SQLException;
	
	/**查询全部日志
	 * @return
	 */
	public Vector selectLog() throws SQLException;
	/** 
	 *  按时间筛选日志
	 * @param datetime 日期
	 * @return Vector 返回日志集合
	 * @author 程宇
	 * @serialData 2016年4月25日:下午9:25:14
	 * @version 1.0.0
	 */ 
	public Vector selectLogByTime(String datetime) throws SQLException;
	/** 
	 *  按ID筛选日志
	 * @param id
	 * @return Vector 返回日志集合
	 * @author 程宇
	 * @serialData 2016年4月25日:下午9:25:50
	 * @version 1.0.0
	 */ 
	public Vector selectLogById(int id) throws SQLException;
	
	/**按是否生效查询
	 * @param effect
	 * @return Vector 返回日志集合
	 */
	public Vector selectLogByEffect(boolean effect) throws SQLException;
}
