package com.jnvc.scoremanager.ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPasswordField;

import com.jnvc.scoremanager.other.Factory;
import com.mysql.jdbc.Connection;
import com.jnvc.scoremanager.dao.PersonDao;
import com.jnvc.scoremanager.db.DBConnection;
import com.jnvc.scoremanager.model.Person;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class Login {

	private JFrame frame;
	private JPanel panel;
	private JTextField textFieldNumber;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JLabel lblRegister;
	private JLabel lblForget;
	public static Person person=null;
	private Image image = new ImageIcon("res\\bg.jpg").getImage();  

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.put("RootPane.setupButtonVisible", false);
					BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
					//BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
					org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
					new Login();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "系统初始化错误！");
					e.printStackTrace();
				}
			}
		});
	} 

	public Login() {        
		initialize();	
		event();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("res\\icon.png"));
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.setTitle("登录");	
		frame.setBounds(0, 0, 411, 293);
		frame.setBounds(Tools.getScreenWidth()/2-frame.getWidth()/2, Tools.getScreenHeight()/2-frame.getHeight()/2, 411, 293);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		panel = new JPanel(){
			  protected void paintComponent(Graphics g) { 
			      g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);  
			  }  
		};
		
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("工号：");
		lblUsername.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblUsername.setBounds(74, 85, 54, 31);
		panel.add(lblUsername);
		
		btnLogin = new JButton("登录");
		btnLogin.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnLogin.setBounds(110, 186, 179, 34);
		panel.add(btnLogin);
		frame.getRootPane().setDefaultButton(btnLogin);
		
		JLabel lblPassword = new JLabel("密码：");
		lblPassword.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblPassword.setBounds(74, 133, 54, 31);
		panel.add(lblPassword);
		
		textFieldNumber = new JTextField();
		textFieldNumber.setBounds(122, 85, 160, 30);
		panel.add(textFieldNumber);
	
		
		lblRegister = new JLabel("注册帐号");
		lblRegister.setBounds(292, 94, 54, 15);
		lblRegister.setForeground(Color.GRAY);
		panel.add(lblRegister);
		
		lblForget = new JLabel("找回密码");
		lblForget.setBounds(292, 142, 54, 15);
		lblForget.setForeground(Color.GRAY);
		panel.add(lblForget);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(122, 135, 160, 30);
		panel.add(passwordField);
		
		JButton btnNewButton = new JButton("*");
		btnNewButton.setVisible(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Person per = new Person();
				per.setPower(2);
				new MainWindow(per);
				frame.setVisible(false);
			}
		});
		btnNewButton.setBounds(356, 232, 39, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("成绩管理系统");
		lblNewLabel.setVisible(false);
		lblNewLabel.setForeground(new Color(0, 102, 255));
		lblNewLabel.setFont(new Font("YaHei Consolas Hybrid", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(26, 10, 352, 51);
		panel.add(lblNewLabel);
		
		ImageIcon ic = new ImageIcon("res\\icon.png");
		JLabel icon = new JLabel();
		icon.setVisible(false);
		icon.setLocation(10, 85);
		icon.setSize(54, 79);
		icon.setIcon(ic);
		panel.add(icon);
	
	}

	private void event(){
		
		//如果配置文件不存在载入设置窗口
		String dir = System.getProperty("user.dir");
		File config = new File(dir+"/config.properties");
		if(!config.exists()){
			new Setting();
		}
			
		//登录按钮事件
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if("".equals(textFieldNumber.getText())||"".equals(passwordField.getText())){
					JOptionPane.showMessageDialog(frame, "请将信息填写完整");
					return;
				}
				Connection con;
				try {
					con = DBConnection.getConnection();
					try {
						btnLogin.setEnabled(false);
						Person per = new Person(textFieldNumber.getText(),passwordField.getText());
						PersonDao perdao=Factory.getPersonDao();
						per = perdao.login(per);
						if(per!=null){
							person=per;
							if(per.getPower()==2){
								JOptionPane.showMessageDialog(Login.this.frame, "欢迎回来，管理员:"+per.getName());
								new MainWindow(per);
								Login.this.frame.setVisible(false);
							}else if(per.getPower()==1){
								JOptionPane.showMessageDialog(Login.this.frame, "欢迎回来，教师："+per.getName());
								new MainWindow(per);
								Login.this.frame.setVisible(false);
								
							}else if(per.getPower()==0){
								JOptionPane.showMessageDialog(Login.this.frame, "帐号暂未通过授权");
							}
						}else{
							JOptionPane.showMessageDialog(Login.this.frame, "用户名或密码错误");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(Login.this.frame, "系统错误！");
						return;
					} finally{
						btnLogin.setEnabled(true);
					}
				
				} catch (Exception e2) {
					if(JOptionPane.showConfirmDialog(frame, "数据库连接失败，是否查看数据库配置？\n错误："+e2.getMessage(), "提示：", JOptionPane.OK_CANCEL_OPTION)==0){
						new Setting();
					}
					return;
				}
			}
		});
		
		//注册帐号
		lblRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new Register();	
			}
		});
		//忘记密码
		lblForget.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e){
					JOptionPane.showMessageDialog(frame, "请联系管理员", "提示：", JOptionPane.INFORMATION_MESSAGE);
				}
		});
	}
}
