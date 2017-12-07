package com.jnvc.scoremanager.db;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.jnvc.scoremanager.other.Config;
import com.mysql.jdbc.Connection;

public class DBConnection {
	
	static Connection con = null;

	public static Connection getConnection() throws SQLException, ClassNotFoundException{
			Config config = new Config();
			
			String host = "jdbc:mysql://"+config.getConfig("host")+":"+config.getConfig("port")+"/"+config.getConfig("dbname");
			String user = config.getConfig("dbuser");
			String password = config.getConfig("dbpassword");
			Class.forName("com.mysql.jdbc.Driver");
			con=(Connection) DriverManager.getConnection(host,user,password);
			return con;
	}
	public static boolean closeDB() throws SQLException{
		if(con!=null){
				con.close();
				return true;
		}
		return false;
	}
}
