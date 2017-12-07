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
import com.jnvc.scoremanager.dao.CourseDao;
import com.jnvc.scoremanager.model.Course;
import com.jnvc.scoremanager.other.Config;
import com.jnvc.scoremanager.other.Factory;
import com.jnvc.scoremanager.ui.MainWindow;
import com.jnvc.scoremanager.ui.Tools;
import com.jnvc.scoremanager.ui.panel.PanelCourse;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class DialogCourse {
	private final JPanel contentPanel = new JPanel();
	public Vector row;
	private JDialog dialog;
	private JButton okButton;
	private JTextField textField_subject;
	private JTextField textField_id;
	private JComboBox comboBox_teacher,comboBox_term;
	private JTextField textField_credit;
	private JTextField textField_teacherNumber;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void initAdd(){
		dialog= new JDialog();
		dialog.setTitle("添加课程");
		initUI();
		eventAdd();
	}
	public void initUpdate(Vector newrow){
		dialog= new JDialog();
		dialog.setTitle("修改课程");
		row = new Vector();
		row=newrow;
		initUI();
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
		dialog.setBounds(0,0,210,320);
		dialog.setBounds(Tools.getScreenWidth()/2-dialog.getWidth()/2, Tools.getScreenHeight()/2-dialog.getHeight()/2, 210, 320);
		dialog.setAlwaysOnTop(true);
		dialog.getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialog.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textField_subject = new JTextField();
			textField_subject.setBounds(74, 99, 120, 21);
			contentPanel.add(textField_subject);
			textField_subject.setColumns(10);
		}
		{
			JLabel label_subject = new JLabel("科目：");
			label_subject.setBounds(10, 102, 54, 15);
			contentPanel.add(label_subject);
		}
		{
			JLabel label_teacher = new JLabel("教师：");
			label_teacher.setBounds(10, 180, 54, 15);
			contentPanel.add(label_teacher);
		}
		{
			JLabel label_id = new JLabel("id：");
			label_id.setBounds(10, 24, 54, 15);
			contentPanel.add(label_id);
		}
		{
			textField_id = new JTextField();
			textField_id.setEnabled(false);
			textField_id.setBounds(74, 19, 120, 21);
			contentPanel.add(textField_id);
			textField_id.setColumns(10);
		}
		
		{//组合框 教师
			comboBox_teacher = new JComboBox();
			comboBox_teacher.setFont(new Font("YaHei Consolas Hybrid", Font.PLAIN, 12));
			comboBox_teacher.setEditable(true);
			try {
				AdminDao admindao = Factory.getAdminDao();
				Vector data = admindao.selectTeacher();
				
				Vector tea = new Vector();
				for(int i=0;i<data.size();i++){
					Vector row = new Vector();
					row=(Vector) data.get(i);
					comboBox_teacher.addItem(row.get(2));
				}
				comboBox_teacher.setSelectedIndex(1);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			comboBox_teacher.setBounds(74, 179, 120, 21);
		}
		
		contentPanel.add(comboBox_teacher);
		{
			JLabel label_credit = new JLabel("学分");
			label_credit.setBounds(10, 141, 54, 15);
			contentPanel.add(label_credit);
		}
		{
			textField_credit = new JTextField();
			textField_credit.setBounds(74, 139, 120, 21);
			contentPanel.add(textField_credit);
			textField_credit.setColumns(10);
		}
		{
			JLabel label_term = new JLabel("学期");
			label_term.setBounds(10, 63, 54, 15);
			contentPanel.add(label_term);
		}
		{//组合框 学期
			comboBox_term = new JComboBox();
			comboBox_term.setFont(new Font("YaHei Consolas Hybrid", Font.PLAIN, 12));
			comboBox_term.setEditable(true);
			comboBox_term.setBounds(74, 59, 120, 21);
			try {
				CourseDao coursedao = Factory.getCourseDao();
				Vector data = coursedao.termList("");
				for (int i = 0; i < data.size(); i++) {
					comboBox_term.addItem(data.get(i));
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		contentPanel.add(comboBox_term);
		{
			JLabel label_teacherNumber = new JLabel("\u6559\u5E08\u5DE5\u53F7\uFF1A");
			label_teacherNumber.setBounds(10, 222, 66, 15);
			contentPanel.add(label_teacherNumber);
		}
		{
			textField_teacherNumber = new JTextField();
			textField_teacherNumber.setBounds(74, 219, 120, 21);
			contentPanel.add(textField_teacherNumber);
			textField_teacherNumber.setColumns(10);
		}
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
		event();
	}
	
	public void eventAdd(){
		try {
			CourseDao coursedao = Factory.getCourseDao();
			int nextId = coursedao.getNextId();
			textField_id.setText(nextId+"");
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//判断信息是否输入完整
				if("".equals(textField_subject.getText())||"".equals(textField_credit.getText())||"".equals(textField_teacherNumber.getText())||"".equals(comboBox_teacher.getSelectedItem().toString())||"".equals(comboBox_term.getSelectedItem().toString())){
					JOptionPane.showMessageDialog(dialog, "请将信息添加完整", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//判断组合框输入的数据是否存在
				if(!comboBoxTextIsExist(comboBox_teacher)){
					JOptionPane.showMessageDialog(dialog, "教师不存在", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//保存数据
				row = new Vector();
				try{
					CourseDao coursedao = Factory.getCourseDao();
					if("true".equals(Config.getConfig("autosave"))){
						Course cou = new Course();
						cou.setSubject(textField_subject.getText());
						cou.setTerm(comboBox_term.getSelectedItem().toString());
						cou.setCredit(Integer.parseInt(textField_credit.getText()));
						cou.setTeacher(textField_teacherNumber.getText());
						coursedao.addCourse(cou);
					}
					row.add(coursedao.getNextId()-1);
					row.add(comboBox_term.getSelectedItem().toString());
					row.add(textField_subject.getText());
					row.add(textField_credit.getText());
					row.add(textField_teacherNumber.getText());
					row.add(comboBox_teacher.getSelectedItem().toString());
					PanelCourse.table_course.setRowVector(row);
					PanelCourse.table_course.refresh();
					MainWindow.label_status.setText("添加课程成功");
				}catch(MySQLIntegrityConstraintViolationException keyerr){
					JOptionPane.showMessageDialog(dialog, "此课程已存在", "提示：", JOptionPane.ERROR_MESSAGE);
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
		comboBox_term.setSelectedItem(row.get(1).toString());
		textField_subject.setText(row.get(2).toString());
		textField_credit.setText(row.get(3).toString());
		textField_teacherNumber.setText(row.get(4).toString());
		comboBox_teacher.setSelectedItem(row.get(5).toString());
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//判断信息是否输入完整
				if("".equals(textField_subject.getText())||"".equals(textField_credit.getText())||"".equals(textField_teacherNumber.getText())||"".equals(comboBox_teacher.getSelectedItem().toString())||"".equals(comboBox_term.getSelectedItem().toString())){
					JOptionPane.showMessageDialog(dialog, "请将信息添加完整", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//判断组合框输入的数据是否存在
				if(!comboBoxTextIsExist(comboBox_teacher)){
					JOptionPane.showMessageDialog(dialog, "教师不存在", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
					try {
						CourseDao coursedao = Factory.getCourseDao();
						if("true".equals(Config.getConfig("autosave"))){
							Course cou = new Course();
							cou.setId(Integer.parseInt(textField_id.getText()));
							cou.setSubject(textField_subject.getText());
							cou.setTerm(comboBox_term.getSelectedItem().toString());
							cou.setCredit(Integer.parseInt(textField_credit.getText()));
							cou.setTeacher(textField_teacherNumber.getText());
							coursedao.updateCourse(cou);
						}
						row.set(0,coursedao.getNextId()-1);
						row.set(1,comboBox_term.getSelectedItem().toString());
						row.set(2,textField_subject.getText());
						row.set(3,textField_credit.getText());
						row.set(4,textField_teacherNumber.getText());
						row.set(5,comboBox_teacher.getSelectedItem().toString());
						int rowNumber=PanelCourse.table_course.getRowNumber(0, Integer.parseInt(textField_id.getText()));
						PanelCourse.table_course.updateRowVector(rowNumber, row);
						PanelCourse.table_course.refresh();
						MainWindow.label_status.setText("修改课程成功");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				
				dialog.dispose();
			}
		});
	}
	private void event(){
		//组合框选择项被改变
		comboBox_teacher.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				AdminDao admindao = null;
				Vector tea = new Vector();
				try {
					 admindao = Factory.getAdminDao();
					 tea=admindao.selectTeacherByName(comboBox_teacher.getSelectedItem().toString());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				 if(!tea.isEmpty()){
					 tea=(Vector) tea.get(0);
					 textField_teacherNumber.setText(tea.get(1).toString());
				 }else{
					 //comboBox_teacher.setSelectedItem("");
				 }
			}
		});
		comboBox_teacher.setSelectedIndex(0);
		//教师编号编辑框失去焦点
		textField_teacherNumber.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				AdminDao admindao = null;
				Vector tea = new Vector();
				try {
					 admindao = Factory.getAdminDao();
					 tea=admindao.selectTeacherByNumber(textField_teacherNumber.getText());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				 if(!tea.isEmpty()){
					 tea=(Vector) tea.get(0);
					 comboBox_teacher.setSelectedItem(tea.get(2));
				 }else{
					 JOptionPane.showMessageDialog(dialog, "没有这个教师", "提示", JOptionPane.ERROR_MESSAGE);
					 textField_teacherNumber.setText("");
				 }
			}
		});
	}
	/**判断组合框输入的数据是否存在
	 * @param comboBox
	 * @return
	 */
	public boolean comboBoxTextIsExist(JComboBox comboBox){
		for(int i=0;i<comboBox.getItemCount();i++){
			if(comboBox.getItemAt(i).equals(comboBox.getSelectedItem())){
				return true;
			}
			if(i==comboBox.getItemCount()-1){
				return false;
			}
		}
		return false;
	}
}
