package com.jnvc.scoremanager.other;
import java.sql.SQLException;

import com.jnvc.scoremanager.dao.*;
import com.jnvc.scoremanager.dao.impl.*;

/**
 *  工厂类
 * @author 程宇
 * @serialData 2016年4月22日
 * @version 1.0.0
 */
public class Factory {
	private static AdminDao admindao = null;
	private static PersonDao persondao = null;
	private static ClassesDao classesdao = null;
	private static CourseDao coursedao = null;
	private static ScoreDao scoredao = null;
	private static LogDao logdao = null;
	private static TeacherDao teacherdao = null;
	//private static ConfigDao configdao = null;
	
	
	public static PersonDao getPersonDao() throws Exception{
		if(persondao==null){
			persondao = new PersonDaoImpl();
		}	
		return persondao;
	}
	/** 
	 *  返回管理员业务对象
	 * @return AdminDao
	 * @author 程宇
	 * @serialData 2016年4月22日
	 * @version 1.0.0
	 * @throws Exception 
	 */ 
	public static AdminDao getAdminDao() throws Exception{
		if(admindao==null){
			admindao = new AdminDaoImpl();
		}	
		return admindao;
	}

	/** 
	 *  返回教室业务对象
	 * @return TeacherDao
	 * @author 程宇
	 * @serialData 2016年4月22日
	 * @version 1.0.0
	 * @throws Exception 
	 */ 
	public static TeacherDao getTeacherDao() throws Exception{
		if(teacherdao==null){
			teacherdao =new TeacherDaoImpl();
		}
		return teacherdao;
	}
	
	/** 
	 *  返回班级业务对象
	 * @return ClassesDao
	 * @author 程宇
	 * @serialData 2016年4月22日
	 * @version 1.0.0
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */ 
	public static ClassesDao getClassesDao() throws SQLException, ClassNotFoundException{
		if(classesdao==null){
			classesdao = new ClassesDaoImpl();
		}
		return classesdao;
	}
	
	/** 
	 *  返回课程业务对象
	 * @return CourseDao
	 * @author 程宇
	 * @serialData 2016年4月22日
	 * @version 1.0.0
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */ 
	public static CourseDao getCourseDao() throws SQLException, ClassNotFoundException{
		if(coursedao==null){
			coursedao = new CourseDaoImpl();
		}
		return coursedao;
	}
	
	/** 
	 *  返回成绩业务对象
	 * @return ScoreDao
	 * @author 程宇
	 * @serialData 2016年4月22日
	 * @version 1.0.0
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */ 
	public static ScoreDao getScoreDao() throws SQLException, ClassNotFoundException{
		if(scoredao==null){
			scoredao = new ScoreDaoImpl();
		}
		return scoredao;
	}
	
	
	/**
	 * 返回日志业务对象
	 * @return LogDao
	 * @author 程宇
	 */
	public static LogDao getLogDao()throws SQLException, ClassNotFoundException{
		if(logdao==null){
			logdao = new LogDaoImpl();
		}
		return logdao;
		
	}
	
}
