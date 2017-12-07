package com.jnvc.scoremanager.ui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jnvc.scoremanager.dao.AdminDao;
import com.jnvc.scoremanager.model.Person;
import com.jnvc.scoremanager.other.Config;
import com.jnvc.scoremanager.other.Factory;
import com.jnvc.scoremanager.ui.MainWindow;
import com.jnvc.scoremanager.ui.Tools;
import com.jnvc.scoremanager.ui.panel.PanelTeacher;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import javax.swing.DefaultComboBoxModel;

public class DialogTeacher {
	private final JPanel contentPanel = new JPanel();
	public Vector row;
	private JDialog dialog;
	private JButton okButton;
	private JTextField textField_name;
	private JTextField textField_id;
	private JComboBox comboBox_power;
	private JTextField textField_number;
	private JTextField textField_password;
	private JTextField textField_email;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void initAdd(){
		dialog= new JDialog();
		dialog.setTitle("添加教师");
		initUI();
		eventAdd();
	}
	public void initUpdate(Vector newrow){
		dialog= new JDialog();
		dialog.setTitle("修改教师");
		row = new Vector();
		row=newrow;
		initUI();
		if("1".equals(row.get(0).toString())){
			comboBox_power.setEnabled(false);
		}
		eventUpdate();
	}

	public void initUI() {
		if("true".equals(Config.getConfig("sort"))){
			JOptionPane.showMessageDialog(MainWindow.frame, "开启排序后不能修改数据！");
			return;
		}
		dialog.setIconImage(Toolkit.getDefaultToolkit().getImage("res\\icon.png"));
		dialog.setVisible(true);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setResizable(false);
		dialog.setBounds(0,0,214,300);
		dialog.setBounds(Tools.getScreenWidth()/2-dialog.getWidth()/2, Tools.getScreenHeight()/2-dialog.getHeight()/2, 210, 300);
		dialog.setAlwaysOnTop(true);
		dialog.getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialog.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textField_name = new JTextField();
			textField_name.setBounds(67, 90, 127, 21);
			contentPanel.add(textField_name);
			textField_name.setColumns(10);
		}
		{
			JLabel label_name = new JLabel("姓名：");
			label_name.setBounds(10, 93, 54, 15);
			contentPanel.add(label_name);
		}
		{
			JLabel label_power = new JLabel("权限：");
			label_power.setBounds(10, 165, 54, 15);
			contentPanel.add(label_power);
		}
		{
			JLabel label_id = new JLabel("id：");
			label_id.setBounds(10, 21, 54, 15);
			contentPanel.add(label_id);
		}
		{
			textField_id = new JTextField();
			textField_id.setEnabled(false);
			textField_id.setBounds(67, 16, 127, 21);
			contentPanel.add(textField_id);
			textField_id.setColumns(10);
		}
		
		{
			comboBox_power = new JComboBox();
			comboBox_power.setModel(new DefaultComboBoxModel(new String[] {"\u672A\u5BA1\u6838", "\u6559\u5E08", "\u7BA1\u7406\u5458"}));
			comboBox_power.setFont(new Font("YaHei Consolas Hybrid", Font.PLAIN, 12));
			comboBox_power.setBounds(67, 164, 127, 21);
		}
		
		contentPanel.add(comboBox_power);
		JLabel label_number = new JLabel("工号：");
		label_number.setBounds(10, 57, 54, 15);
		contentPanel.add(label_number);
		
		textField_number = new JTextField();
		textField_number.setBounds(67, 53, 127, 21);
		contentPanel.add(textField_number);
		textField_number.setColumns(10);
		
		JLabel label_password = new JLabel("密码：");
		label_password.setBounds(10, 129, 54, 15);
		contentPanel.add(label_password);
		
		textField_password = new JTextField();
		textField_password.setText("123456");
		textField_password.setBounds(67, 127, 127, 21);
		contentPanel.add(textField_password);
		textField_password.setColumns(10);
		
		JLabel label_email = new JLabel("邮箱：");
		label_email.setBounds(10, 201, 54, 15);
		contentPanel.add(label_email);
		
		textField_email = new JTextField();
		textField_email.setBounds(67, 201, 127, 21);
		contentPanel.add(textField_email);
		textField_email.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			dialog.getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			{
				okButton = new JButton("保存");
				buttonPane.add(okButton);
				dialog.getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("取消");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dialog.dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void eventAdd(){
		try {
			AdminDao admindao = Factory.getAdminDao();
			int nextId = admindao.getNextId();
			textField_id.setText(nextId+"");
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//判断信息是否完整
				if("".equals(textField_name.getText())||"".equals(textField_name.getText())||"".equals(textField_password.getText())){
					JOptionPane.showMessageDialog(dialog, "请将信息填写完整", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				row = new Vector();
				//保存数据
				try{
					AdminDao admindao = Factory.getAdminDao();
					if("true".equals(Config.getConfig("autosave"))){
						Person tea = new Person();
						tea.setNumber(textField_number.getText());
						tea.setName(textField_name.getText());
						tea.setPassword(textField_password.getText());
						tea.setPower(comboBox_power.getSelectedIndex());
						tea.setEmail(textField_email.getText());
						admindao.addTeacher(tea);
					}
					row.add(admindao.getNextId()-1);
					row.add(textField_number.getText());
					row.add(textField_name.getText());
					row.add(comboBox_power.getSelectedIndex());
					row.add(textField_email.getText());
					PanelTeacher.table_teacher.setRowVector(row);
					PanelTeacher.table_teacher.refresh();
					MainWindow.label_status.setText("添加教师成功");
				}catch(MySQLIntegrityConstraintViolationException keyerr){
					JOptionPane.showMessageDialog(dialog, "此教师已存在", "提示：", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}finally{
					dialog.dispose();
				}
			}
		});
	}
	
	public void eventUpdate()
	{
		textField_id.setText(row.get(0).toString());
		textField_number.setText(row.get(1).toString());
		textField_name.setText(row.get(2).toString());
		comboBox_power.setSelectedIndex(Integer.parseInt(row.get(3).toString()));
		textField_email.setText(row.get(4).toString());
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//判断信息是否完整
				if("".equals(textField_name.getText())||"".equals(textField_name.getText())||"".equals(textField_password.getText())){
					JOptionPane.showMessageDialog(dialog, "请将信息填写完整", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
					try {
						AdminDao admindao = Factory.getAdminDao();
						if("true".equals(Config.getConfig("autosave"))){
							Person tea = new Person();
							tea.setNumber(textField_number.getText());
							tea.setName(textField_name.getText());
							tea.setPassword(textField_password.getText());
							tea.setPower(comboBox_power.getSelectedIndex());
							tea.setEmail(textField_email.getText());
							admindao.updateTeacher(tea);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					row.set(0,Integer.parseInt( textField_id.getText()));
					row.set(1,textField_number.getText());
					row.set(2, textField_name.getText());
					row.set(3, comboBox_power.getSelectedIndex());
					row.set(4, textField_email.getText());
					int rowNumber=PanelTeacher.table_teacher.getRowNumber(0, Integer.parseInt(textField_id.getText()));
					PanelTeacher.table_teacher.updateRowVector(rowNumber, row);
					PanelTeacher.table_teacher.refresh();
					MainWindow.label_status.setText("修改教师成功");
				dialog.dispose();
			}
		});
	}
}
