package com.jnvc.scoremanager.other;

import java.util.Properties;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Config {

	public static String getConfig(String key){
		Properties conf = new Properties();
		String dir = System.getProperty("user.dir");
		File config = new File(dir+"/config.properties");
		try{
			if(!config.exists()){
				//config.createNewFile();
			}else{
				FileInputStream fin = new FileInputStream(config);
				conf.load(fin);
				return conf.getProperty(key);
			}
		}catch(IOException e){
			System.out.println("创建文件出错");
		}catch(NullPointerException e){
			System.out.println("传入的参数不能为空");
		}
		return null;
	}

	public static boolean setConfig(String key, String value) {
		String dir = System.getProperty("user.dir");
		File config = new File(dir+"/config.properties");
		Properties conf = new Properties();
		try{
			if(!config.exists()){
				config.createNewFile();
			}
			FileInputStream fin = new FileInputStream(config);
			conf.load(fin);
			FileOutputStream fout = new FileOutputStream(config);
			conf.setProperty(key, value);
			conf.store(fout, "This is Score Manager System config file");
			fout.close();
			return true;
		}catch(IOException e){
			System.out.println("创建文件出错");
		}catch(NullPointerException e){
			System.out.println("传入的参数不能为空");
		}
		return false;
	}

}
