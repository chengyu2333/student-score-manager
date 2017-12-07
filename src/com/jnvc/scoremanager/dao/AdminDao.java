package com.jnvc.scoremanager.dao;
import com.jnvc.scoremanager.model.Person;

import java.sql.SQLException;
import java.util.Vector;

/**
 * 用户业务接口
 * @author 程宇
 * @serialData 2016年4月22日:下午8:04:38
 * @version 1.0.0
 */
public interface AdminDao extends PersonDao{
	/** 
	 * 添加教师
	 * @param Person per
	 * @return 新添加记录的id
	 * @author 程宇
	 * @serialData 2016年4月22日:下午8:45:20
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public int addTeacher(Person per) throws SQLException;
	/** 
	 * 删除教师
	 * @param String 教师编号
	 * @return boolean 返回是否删除成功
	 * @author 程宇
	 * @serialData 2016年4月22日:下午8:45:20
	 * @version 1.0.0
	 * @throws Exception 
	 */ 
	public boolean deleteTeacherByNumber(String number) throws SQLException;
	/**修改教师信息
	 * @param per
	 * @return
	 * @throws SQLException
	 */
	public boolean updateTeacher(Person per) throws SQLException;
	/** 
	 * 对教师进行授权
	 * @param teacher
	 * @return boolean 返回是否审核成功
	 * @author 程宇
	 * @serialData 2016年4月22日:下午8:45:20
	 * @version 1.0.0
	 * @throws Exception 
	 */ 
	public boolean permit(String number,int power) throws Exception;
	
	/** 
	 *  筛选教师
	 * @return List<Person> 返回教师集合
	 * @author 程宇
	 * @serialData 2016年4月22日:下午8:38:18
	 * @version 1.0.0
	 * @throws Exception 
	 */ 
	public Vector selectTeacher() throws Exception;
	
	/** 
	 *  按工号筛选教师
	 * @return Person 返回教师对象
	 * @author 程宇
	 * @serialData 2016年4月22日:下午8:38:18
	 * @version 1.0.0
	 * @throws Exception 
	 */ 
	public Vector selectTeacherByName(String name) throws Exception;

	/** 
	 *  按工号筛选教师
	 * @return Person 返回教师对象
	 * @author 程宇
	 * @serialData 2016年4月22日:下午8:38:18
	 * @version 1.0.0
	 * @throws Exception 
	 */ 
	public Vector selectTeacherByNumber(String number) throws Exception;
	/**获取下一条记录的id
	 * @return
	 * @throws SQLException
	 */
	public int getNextId() throws SQLException;
}
