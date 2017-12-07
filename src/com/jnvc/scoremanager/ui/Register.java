package com.jnvc.scoremanager.ui;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI.NormalColor;

import com.jnvc.scoremanager.dao.PersonDao;
import com.jnvc.scoremanager.model.Person;
import com.jnvc.scoremanager.other.Factory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Register extends JFrame {

	private JPanel panel;
	private JTextField textFieldUsername;
	private JPasswordField passwordFieldPassword;
	private JPasswordField passwordFieldConfirmPassword;
	private JTextField textFieldEmail;
	private JTextField textFieldNumber;
	private JButton buttonRegister;
	private Image image = new ImageIcon("res\\bgreg.jpg").getImage();  
	
	public Register() {
		initialize();
		buttonRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if("".equals(textFieldUsername.getText())&&"".equals(passwordFieldConfirmPassword.getText())&&"".equals(passwordFieldPassword.getText())){
					JOptionPane.showMessageDialog(Register.this.panel, "请把信息填写完整");
				}else if(!passwordFieldPassword.getText().equals(passwordFieldConfirmPassword.getText())){
					JOptionPane.showMessageDialog(panel, "密码不一致");
				}else{
					
					try {
						PersonDao perdao = Factory.getPersonDao();
						Person per = new Person(textFieldNumber.getText(),textFieldUsername.getText(),passwordFieldConfirmPassword.getText(),textFieldEmail.getText());
						if(perdao.register(per)){
							JOptionPane.showMessageDialog(panel, "注册成功");
							Register.this.setVisible(false);
						}
		
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(panel, "系统错误");
						e1.printStackTrace();
					}
				}
				
			}
		});
	}
	
	private void initialize() {
		setTitle("注册");
		setIconImage(Toolkit.getDefaultToolkit().getImage("res\\icon.png"));
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(0, 0, 292, 313);
		setBounds(Tools.getScreenWidth()/2-getWidth()/2, Tools.getScreenHeight()/2-getHeight()/2, 292, 313);
		
		this.setVisible(true);		
		panel = new JPanel(){
			  protected void paintComponent(Graphics g) { 
			      g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);  
			  }  
		};
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("姓名");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(10, 64, 54, 15);
		panel.add(label);
		
		JLabel label_1 = new JLabel("密码");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(10, 102, 54, 15);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("邮箱");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(10, 172, 54, 15);
		panel.add(label_2);
		
		JLabel lblNewLabel = new JLabel("确认密码");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 137, 54, 15);
		panel.add(lblNewLabel);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(74, 61, 171, 25);
		panel.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		passwordFieldPassword = new JPasswordField();
		passwordFieldPassword.setBounds(74, 99, 171, 25);
		panel.add(passwordFieldPassword);
		passwordFieldPassword.setColumns(10);
		
		passwordFieldConfirmPassword = new JPasswordField();
		passwordFieldConfirmPassword.setBounds(74, 134, 171, 25);
		panel.add(passwordFieldConfirmPassword);
		passwordFieldConfirmPassword.setColumns(10);
		
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(74, 169, 171, 25);
		panel.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("工号");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(10, 26, 54, 15);
		panel.add(lblNewLabel_1);
		
		textFieldNumber = new JTextField();
		textFieldNumber.setBounds(74, 23, 171, 25);
		panel.add(textFieldNumber);
		textFieldNumber.setColumns(10);	
		
		buttonRegister = new JButton("注册");
		buttonRegister.setFont(new Font("YaHei Consolas Hybrid", Font.PLAIN, 16));
		buttonRegister.setUI(new BEButtonUI().setNormalColor(NormalColor.green));
		buttonRegister.setBounds(74, 219, 136, 36);
		panel.add(buttonRegister);
	
	}
}
