package com.jnvc.scoremanager.dao;
import com.jnvc.scoremanager.model.Course;
import java.sql.SQLException;
import java.util.Vector;

/**
 * 课程业务接口
 * @author 程宇
 * @serialData 2016年4月25日:下午8:05:13
 * @version 1.0.0
 */
public interface CourseDao {
	/** 
	 *  添加课程信息
	 * @param cou
	 * @return 新插入记录的id
	 * @author 程宇
	 * @serialData 2016年4月25日:下午9:22:58
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public int addCourse(Course cou) throws SQLException;
	/** 
	 *  删除课程信息
	 * @param cou
	 * @return boolean 返回是否删除成功
	 * @author 程宇
	 * @serialData 2016年4月25日:下午9:23:16
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public boolean deleteCourse(int id) throws SQLException;
	/** 
	 *  更新课程信息
	 * @param cou
	 * @return boolean 返回是否更新成功
	 * @author 程宇
	 * @serialData 2016年4月25日:下午9:23:45
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public boolean updateCourse(Course cou) throws SQLException;
	/** 
	 *  学期列表
	 * @param 班级名
	 * @return Vector 返回多行课程集合
	 * @author 程宇
	 * @serialData 2016年4月25日:下午9:24:05
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public Vector termList(String cla) throws SQLException;
	/** 
	 *  筛选全部课程
	 * @param term
	 * @return Vector 返回多行课程集合
	 * @author 程宇
	 * @serialData 2016年4月25日:下午9:24:05
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public Vector selectCourse() throws SQLException;
	/**根据条件查找课程编号
	 * @param subject
	 * @param term
	 * @return
	 * @throws SQLException
	 */
	public int selectCourseId(String subject,String term) throws SQLException;
	/** 
	 *  按学年筛选课程
	 * @param term
	 * @return Vector 返回多行课程集合
	 * @author 程宇
	 * @serialData 2016年4月25日:下午9:24:05
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public Vector selectCourseByTerm(String term) throws SQLException;
	/** 
	 *  按班级名筛选课程
	 * @param term
	 * @return Vector 返回多行课程集合
	 * @author 程宇
	 * @serialData 2016年4月25日:下午9:24:05
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public Vector selectCourseByClasses(String cla) throws SQLException;
	/** 
	 *  按科目筛选课程
	 * @param sub
	 * @return Vector 返回多行课程集合
	 * @author 程宇
	 * @serialData 2016年4月25日:下午9:24:30
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public Vector selectCourseBySubject(String sub) throws SQLException;
	/**获取下一条记录的id
	 * @return
	 * @throws SQLException
	 */
	public int getNextId() throws SQLException;
}
