package com.jnvc.scoremanager.other;

import java.sql.SQLException;

import com.jnvc.scoremanager.dao.LogDao;
import com.jnvc.scoremanager.model.Log;

/**
 * 测试类
 * @author 程宇
 * @serialData 2016年4月25日:下午8:35:47
 * @version 1.0.0
 */
public class Test {
	@SuppressWarnings("deprecation")
	public static void main(String[] args){
		/*
		AdminDao admin = Factory.getAdminDao();
		System.out.println("测试教师集合");
		List<Teacher> list = admin.selectTeacher();
		for(int i=0;i<list.size();i++){
			Teacher tea = list.get(i);
			System.out.println(tea.getUsername());
		}
		
		Iterator<Teacher> it = list.iterator();
		System.out.println("测试迭代器");
		while(it.hasNext()){
			Teacher tea = it.next();
			System.out.println(tea.getUsername());
		}
		*/
		//FileOperate f =  new FileOperate();
		//f.creatFile();
//		ConfigDao configdao = Factory.getConfigDao();
//		if(configdao.setConfig("aaa", "bbbb")){
//			System.out.println("写入配置成功");
//		}
//		System.out.println("读取配置："+configdao.getConfig("aaa"));
		//DBConnection dbconnection = Factory.getConnection();
		
		/*
		Person teac = new Person("0","0","123","0");
		teac.setPower(1);
		try {
			System.out.println(Factory.getAdminDao().check(teac));
			List<Person> list = Factory.getAdminDao().selectTeacher();
			Iterator<Person> it = list.iterator();
			while(it.hasNext()){
				Person tea = (Person) it.next();
				System.out.println(tea.getName()+"  \t  "+tea.getPower());
				
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
*/
		
//		
//		try {
//			AdminDao admindao = Factory.getAdminDao();
//			
//			Person per = new Person("00001","张三","123456","123@qq.com");
//			if(admindao.register(per)){
//				System.out.println("注册成功");
//			}
//			System.out.println("登录成功，用户权限为："+admindao.login(per));
//			Vector tea = new Vector();
//			tea = admindao.selectTeacher();
//			System.out.println(tea);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		//Date date = new Date(0, 0, 0);
		
		try {
			LogDao logdao = Factory.getLogDao();
			Log log = new Log();
			log.setNewvalue("90");
			log.setOldvalue("80");
			log.setOperate("修改");
			log.setTarget("成绩");
			log.setPerson("admin");
			logdao.addLog(log);
			System.out.println();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
