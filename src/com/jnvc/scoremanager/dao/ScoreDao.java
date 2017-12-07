package com.jnvc.scoremanager.dao;
import java.sql.SQLException;
import java.util.Vector;

import com.jnvc.scoremanager.model.Score;
//import java.util.List;
/**
 * 成绩业务接口
 * @author 程宇
 * @serialData 2016年4月25日:下午8:05:49
 * @version 1.0.0
 */
public interface ScoreDao {
	/** 
	 *  添加成绩信息
	 * @param sco
	 * @param num
	 * @return 新插入记录的id
	 * @author 程宇
	 * @serialData 2016年4月25日:下午9:33:49
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public int addScore(Score sco) throws SQLException;
	
	/** 
	 *  更新成绩信息
	 * @param sco
	 * @return boolean 返回是否更新成功
	 * @author 程宇
	 * @serialData 2016年4月25日:下午9:35:49
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public boolean updateScore(Score sco) throws SQLException;
	/** 
	 *  删除成绩信息
	 * @param sco
	 * @return boolean 返回是否更新成功
	 * @author 程宇
	 * @serialData 2016年4月25日:下午9:35:49
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public boolean deleteScore(int id) throws SQLException;
	/**成绩信息是否存在 15054174817
	 * @param number
	 * @param subject
	 * @param term
	 * @return
	 * @throws SQLException
	 */
	public boolean existScore(String number,String subject,String term) throws SQLException;
	/** 
	 *  筛选全部成绩信息
	 * @param num
	 * @return Vector 返回多行成绩集合
	 * @author 程宇
	 * @serialData 2016年4月25日:下午9:36:46
	 * @version 1.0.0
	 */ 
	public Vector selectScore() throws SQLException;
	/** 
	 *  按班级筛选成绩信息
	 * @param num
	 * @return Vector 返回多行成绩集合
	 * @author 程宇
	 * @serialData 2016年4月25日:下午9:36:46
	 * @version 1.0.0
	 */ 
	public Vector selectScoreBySubject(String subject) throws SQLException;
	/** 
	 *  按班级筛选成绩信息
	 * @param num
	 * @return Vector 返回多行成绩集合
	 * @author 程宇
	 * @serialData 2016年4月25日:下午9:36:46
	 * @version 1.0.0
	 */ 
	public Vector selectScoreByClasses(String name) throws SQLException;
	/** 
	 *  按学号筛选成绩信息
	 * @param num
	 * @return Vector 返回多行成绩集合
	 * @author 程宇
	 * @serialData 2016年4月25日:下午9:36:46
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public Vector selectScoreByNum(String num) throws SQLException;
	/** 
	 *  按姓名筛选成绩信息
	 * @param num
	 * @return Vector 返回多行成绩集合
	 * @author 程宇
	 * @serialData 2016年4月25日:下午9:36:46
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public Vector selectScoreByName(String name) throws SQLException;
	/** 
	 *  按学期筛选成绩信息
	 * @param num
	 * @return Vector 返回多行成绩集合
	 * @author 程宇
	 * @serialData 2016年4月25日:下午9:36:46
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public Vector selectScoreByTerm(String term) throws SQLException;
	/**获取下一条记录的id
	 * @return
	 * @throws SQLException
	 */
	public int getNextId() throws SQLException;
}
