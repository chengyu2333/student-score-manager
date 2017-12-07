package com.jnvc.scoremanager.dao;
import com.jnvc.scoremanager.model.Classes;

import java.sql.SQLException;
import java.util.Vector;

/**
 * @Description: 班级业务接口
 * @author 程宇
 * @serialData 2016年4月25日:下午8:05:03
 * @version 1.0.0
 */
	public interface ClassesDao {
	/** 
	 *  添加班级信息
	 * @param cla
	 * @return boolean 新添加记录的id
	 * @author 程宇
	 * @serialData 2016年4月25日:下午9:19:55
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public int addClasses(Classes cla) throws SQLException;
	/** 
	 *  删除班级信息
	 * @param id
	 * @return boolean 是否删除成功
	 * @author 程宇
	 * @serialData 2016年4月25日:下午9:20:28
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public boolean deleteClasses(int id) throws SQLException;
	/** 
	 *  更新班级信息
	 * @param cla
	 * @return boolean 更新是否成功
	 * @author 程宇
	 * @serialData 2016年4月25日:下午9:20:49
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public boolean updateClasses(Classes cla) throws SQLException;
	
	/**获取班级名称列表
	 * @return
	 * @throws SQLException
	 */
	public Vector selectClassesName() throws SQLException;
	/** 
	 * 筛选全部班级
	 * @param name
	 * @return Vector 多行班级数据集合
	 * @author 程宇
	 * @serialData 2016年4月25日:下午9:21:12
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public Vector selectClasses() throws SQLException;
	/** 
	 *  按班主任筛选班级
	 * @param name
	 * @return Vector 多行班级数据集合
	 * @author 程宇
	 * @serialData 2016年4月25日:下午9:21:12
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public Vector selectClassesByTeacher(String name) throws SQLException;
	/** 
	 *  按班级名筛选班级
	 * @param name
	 * @return Vector 多行班级数据集合
	 * @author 程宇
	 * @serialData 2016年4月25日:下午9:21:37
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public Vector selectClassesByName(String name) throws SQLException;
	/**按id筛选班级
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Vector selectClassesById(int id) throws SQLException;
	/**获取下次添加的id
	 * @return int id
	 */
	public int getNextId() throws SQLException;
}
