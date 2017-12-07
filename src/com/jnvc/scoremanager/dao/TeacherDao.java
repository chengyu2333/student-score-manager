package com.jnvc.scoremanager.dao;

import java.sql.SQLException;
import java.util.Vector;

import com.jnvc.scoremanager.model.Student;

/**
 * 教师业务接口
 * @author 程宇
 * @serialData 2016年4月25日:下午8:06:10
 * @version 1.0.0
 */
public interface TeacherDao extends PersonDao{
	/**
	 * 筛选全部学生
	 * @return Vector 学生信息多行集合
	 * @throws SQLException
	 */
	public Vector selectStudent() throws SQLException;
	/** 
	 * 按名字筛选学生
	 * @param name
	 * @return List<Studnet>
	 * @author 程宇
	 * @serialData May 9, 2016:2:38:04 PM
	 * @version 1.0.0
	 * @throws SQLException 
	 */
	public Vector selectStudentByName(String name) throws SQLException;
	/** 
	 * 按学号筛选学生
	 * @param Num
	 * @return Vector
	 * @author 程宇
	 * @serialData May 9, 2016:2:38:10 PM
	 * @version 1.0.0
	 * @throws SQLException 
	 */
	public Vector selectStudentByNum(String Num) throws SQLException;
	/** 
	 * 按班级筛选学生
	 * @param Num
	 * @return Vector
	 * @author 程宇
	 * @serialData May 9, 2016:2:38:10 PM
	 * @version 1.0.0
	 * @throws SQLException 
	 */
	public Vector selectStudentByClasses(String name) throws SQLException;
	/** 
	 * 添加一个学生
	 * @param stu
	 * @return 新插入记录的id
	 * @author 程宇
	 * @serialData May 9, 2016:2:39:59 PM
	 * @version 1.0.0
	 * @throws SQLException 
	 */
	public int addStudent(Student stu) throws SQLException;
	/** 
	 * 更新学生信息
	 * @param stu
	 * @return
	 * @author 程宇
	 * @serialData May 9, 2016:2:40:11 PM
	 * @version 1.0.0
	 * @throws SQLException 
	 */
	public boolean updateStudent(Student stu) throws SQLException;
	/** 
	 * 删除学生
	 * @param String
	 * @return
	 * @author 程宇
	 * @serialData May 9, 2016:2:40:21 PM
	 * @version 1.0.0
	 * @throws SQLException 
	 */
	public boolean deleteStudentByNumber(String number) throws SQLException;
	/**获取下一条记录的id
	 * @return
	 * @throws SQLException
	 */
	public int getNextId() throws SQLException;
}
