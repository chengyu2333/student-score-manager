package com.jnvc.scoremanager.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import com.jnvc.scoremanager.other.Config;

public class Setting extends JFrame {
	private JTextField textField_host;
	private JTextField textField_username;
	private JTextField textField_password;
	private JPanel panel;
	private JCheckBox checkBox_autoSave,checkBox_sort;
	private JTextField textField_port;
	private JTextField textField_dbname;

	/**
	 * Create the frame.
	 */
	public Setting() {
		setTitle("设置");
		setBounds(100, 100, 342, 305);
		setBounds(Tools.getScreenWidth()/2-getWidth()/2, Tools.getScreenHeight()/2-getHeight()/2, 342, 305);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("res\\icon.png"));
		setResizable(false);
		setAlwaysOnTop(true);
		setVisible(true);
		
		 panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_db = new JPanel();
		panel_db.setBorder(new TitledBorder(null, "\u6570\u636E\u5E93\u914D\u7F6E", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_db.setBounds(10, 10, 147, 214);
		panel.add(panel_db);
		
		JLabel label_host = new JLabel("\u5730\u5740\uFF1A");
		
		textField_host = new JTextField();
		textField_host.setText("127.0.0.1");
		textField_host.setColumns(10);
		panel_db.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_db.add(label_host);
		panel_db.add(textField_host);
		
		JLabel label_port = new JLabel("\u7AEF\u53E3\uFF1A");
		panel_db.add(label_port);
		
		textField_port = new JTextField();
		textField_port.setText("3306");
		panel_db.add(textField_port);
		textField_port.setColumns(10);
		
		JLabel label_dbname = new JLabel("\u5E93\u540D\uFF1A");
		panel_db.add(label_dbname);
		
		textField_dbname = new JTextField();
		textField_dbname.setText("scoremanager");
		panel_db.add(textField_dbname);
		textField_dbname.setColumns(10);
		
		JLabel label = new JLabel("\u5E10\u53F7\uFF1A");
		panel_db.add(label);
		
		textField_username = new JTextField();
		panel_db.add(textField_username);
		textField_username.setColumns(10);
		
		JLabel label_password = new JLabel("\u5BC6\u7801\uFF1A");
		panel_db.add(label_password);
		
		textField_password = new JTextField();
		panel_db.add(textField_password);
		textField_password.setColumns(10);
		
		JPanel panel_system = new JPanel();
		panel_system.setBorder(new TitledBorder(null, "\u7CFB\u7EDF\u914D\u7F6E", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_system.setBounds(167, 10, 159, 214);
		panel.add(panel_system);
		
		 checkBox_autoSave = new JCheckBox("\u4FEE\u6539\u540E\u7ACB\u5373\u4FDD\u5B58\u6570\u636E");
		 checkBox_autoSave.setSelected(true);
		 checkBox_autoSave.setEnabled(false);
		panel_system.add(checkBox_autoSave);
		checkBox_autoSave.setActionCommand("");
		
		 checkBox_sort = new JCheckBox("\u5F00\u542F\u8868\u683C\u6392\u5E8F      ");
		checkBox_sort.setToolTipText("\u63D0\u793A\uFF1A\u5F00\u542F\u540E\u53EA\u80FD\u67E5\u770B\u4E0D\u80FD\u4FEE\u6539\uFF01");
		panel_system.add(checkBox_sort);
		
		//读取配置
		String dir = System.getProperty("user.dir");
		File config = new File(dir+"/config.properties");
		if(config.exists()){
			textField_host.setText(Config.getConfig("host"));
			textField_dbname.setText(Config.getConfig("dbname"));
			textField_port.setText(Config.getConfig("port"));
			textField_password.setText(Config.getConfig("dbpassword"));
			textField_username.setText(Config.getConfig("dbuser"));
			if("true".equals(Config.getConfig("autosave"))){
				checkBox_autoSave.setSelected(true);
			}else{
				checkBox_autoSave.setSelected(false);
			}
			if("true".equals(Config.getConfig("sort"))){
				checkBox_sort.setSelected(true);
			}else{
				checkBox_sort.setSelected(false);
			}
		}

		//测试连接按钮
		JButton button_connection = new JButton("测试连接");
		button_connection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String host = "jdbc:mysql://"+textField_host.getText()+":"+textField_port.getText()+"/"+textField_dbname.getText();
				String user = textField_username.getText();
				String password = textField_password.getText();
				try {
					Class.forName("com.mysql.jdbc.Driver");
					DriverManager.getConnection(host,user,password);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException esql){
					JOptionPane.showMessageDialog(panel, "数据库连接失败\n"+esql.getMessage(), "提示", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				JOptionPane.showMessageDialog(panel, "数据库连接成功", "提示", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		panel_db.add(button_connection);
		

		//保存按钮
		JButton button_save = new JButton("保存");
		button_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if("".equals(textField_dbname.getText())||"".equals(textField_port.getText())||"".equals(textField_host.getText())||"".equals(textField_password.getText())||"".equals(textField_username.getText())){
					JOptionPane.showMessageDialog(panel, "请将信息填写完整", "提示", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				Config.setConfig("host", textField_host.getText());
				Config.setConfig("port", textField_port.getText());
				Config.setConfig("dbname", textField_dbname.getText());
				Config.setConfig("dbuser", textField_username.getText());
				Config.setConfig("dbpassword", textField_password.getText());
				if(checkBox_autoSave.isSelected()){
					Config.setConfig("autosave", "true");
				}else{
					Config.setConfig("autosave", "false");
				}
				
				if(checkBox_sort.isSelected()){
					Config.setConfig("sort", "true");
				}else{
					Config.setConfig("sort", "false");
				}
				
				JOptionPane.showMessageDialog(panel, "保存成功,重启生效。", "提示", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
		button_save.setBounds(41, 234, 93, 23);
		panel.add(button_save);
		
		//重置按钮
		JButton button_restart = new JButton("重置");
		button_restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_host.setText(Config.getConfig("host"));
				textField_dbname.setText(Config.getConfig("dbname"));
				textField_port.setText(Config.getConfig("port"));
				textField_password.setText(Config.getConfig("dbpassword"));
				textField_username.setText(Config.getConfig("dbuser"));
				if("true".equals(Config.getConfig("autosave"))){
					checkBox_autoSave.setSelected(true);
				}else{
					checkBox_autoSave.setSelected(false);
				}
				
				if("true".equals(Config.getConfig("sort"))){
					checkBox_sort.setSelected(true);
				}else{
					checkBox_sort.setSelected(false);
				}
			}
		});
		button_restart.setBounds(192, 234, 93, 23);
		panel.add(button_restart);

	}
}
