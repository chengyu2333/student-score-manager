package com.jnvc.scoremanager.ui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jnvc.scoremanager.dao.ClassesDao;
import com.jnvc.scoremanager.dao.CourseDao;
import com.jnvc.scoremanager.dao.ScoreDao;
import com.jnvc.scoremanager.dao.TeacherDao;
import com.jnvc.scoremanager.model.Student;
import com.jnvc.scoremanager.other.Config;
import com.jnvc.scoremanager.other.Factory;
import com.jnvc.scoremanager.ui.MainWindow;
import com.jnvc.scoremanager.ui.Tools;
import com.jnvc.scoremanager.ui.panel.PanelStudent;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class DialogStudent {
	private final JPanel contentPanel = new JPanel();
	public Vector row;
	private JDialog dialog;
	private JButton okButton;
	private JTextField textField_name;
	private JTextField textField_id;
	private JComboBox comboBox_classes;
	private JTextField textField_number;
	private JTextField textField_password;
	private JTextField textField_idcard;
	private JTextField textField_phone;
	private JTextArea textArea_address;
	private ButtonGroup buttongroup;
	private JRadioButton radioButton_girl, radioButton_boy;
	public ScoreDao scoredao;
	public CourseDao coursedao;
	public TeacherDao teacherdao;
	public ClassesDao classesdao;
	private String sex = null;
	/**
	 * @wbp.parser.entryPoint
	 */
	public void initAdd() {
		dialog = new JDialog();
		dialog.setTitle("添加学生");
		try {
			teacherdao = Factory.getTeacherDao();
			classesdao = Factory.getClassesDao();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		initUI();
		eventAdd();
	}

	public void initUpdate(Vector newrow) {
		dialog = new JDialog();
		dialog.setTitle("修改学生");
		row = new Vector();
		row = newrow;
		try {
			teacherdao = Factory.getTeacherDao();
			classesdao = Factory.getClassesDao();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		initUI();
		textField_number.setEnabled(false);
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
		dialog.setBounds(0, 0, 246, 400);
		dialog.setBounds(Tools.getScreenWidth() / 2 - dialog.getWidth() / 2,
				Tools.getScreenHeight() / 2 - dialog.getHeight() / 2, 250, 400);
		dialog.setAlwaysOnTop(true);
		dialog.getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialog.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textField_name = new JTextField();
			textField_name.setBounds(76, 84, 147, 21);
			contentPanel.add(textField_name);
			textField_name.setColumns(10);
		}
		{
			JLabel label_classes = new JLabel("\u73ED\u7EA7\uFF1A");
			label_classes.setBounds(10, 157, 54, 15);
			contentPanel.add(label_classes);
		}
		{
			JLabel label_id = new JLabel("\u7F16\u53F7\uFF1A");
			label_id.setBounds(10, 17, 54, 15);
			contentPanel.add(label_id);
		}
		{
			textField_id = new JTextField();
			textField_id.setEnabled(false);
			textField_id.setBounds(76, 14, 147, 21);
			contentPanel.add(textField_id);
			textField_id.setColumns(10);
		}

		{// 组合框 班级
			comboBox_classes = new JComboBox();
			comboBox_classes.setFont(new Font("YaHei Consolas Hybrid", Font.PLAIN, 12));
			comboBox_classes.setEditable(true);
			try {

				ClassesDao classesdao = Factory.getClassesDao();
				Vector data = classesdao.selectClassesName();
				for (int i = 0; i < data.size(); i++) {
					comboBox_classes.addItem(data.get(i));
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			comboBox_classes.setBounds(76, 154, 147, 21);
		}

		contentPanel.add(comboBox_classes);

		JLabel label_name = new JLabel("\u59D3\u540D\uFF1A");
		label_name.setBounds(10, 87, 54, 15);
		contentPanel.add(label_name);

		JLabel label_number = new JLabel("\u5B66\u53F7\uFF1A");
		label_number.setBounds(10, 52, 54, 15);
		contentPanel.add(label_number);

		textField_number = new JTextField();
		textField_number.setBounds(76, 50, 147, 21);
		contentPanel.add(textField_number);
		textField_number.setColumns(10);

		JLabel label_sex = new JLabel("\u6027\u522B\uFF1A");
		label_sex.setBounds(10, 122, 54, 15);
		contentPanel.add(label_sex);

		radioButton_boy = new JRadioButton("\u7537");
		radioButton_boy.setBounds(74, 116, 56, 23);
		contentPanel.add(radioButton_boy);

		radioButton_girl = new JRadioButton("\u5973");
		radioButton_girl.setBounds(132, 116, 54, 23);
		contentPanel.add(radioButton_girl);

		buttongroup = new ButtonGroup();
		buttongroup.add(radioButton_boy);
		buttongroup.add(radioButton_girl);
		// bg.getSelection();

		{
			JLabel label_password = new JLabel("\u5BC6\u7801\uFF1A");
			label_password.setBounds(10, 192, 54, 15);
			contentPanel.add(label_password);
		}
		{
			textField_password = new JTextField();
			textField_password.setText("123456");
			textField_password.setBounds(76, 189, 147, 21);
			contentPanel.add(textField_password);
			textField_password.setColumns(10);
		}
		{
			JLabel label_idcard = new JLabel("\u8EAB\u4EFD\u8BC1\uFF1A");
			label_idcard.setBounds(10, 227, 54, 15);
			contentPanel.add(label_idcard);
		}
		{
			textField_idcard = new JTextField();
			textField_idcard.setBounds(76, 225, 147, 21);
			contentPanel.add(textField_idcard);
			textField_idcard.setColumns(10);
		}
		{
			JLabel label_phone = new JLabel("\u624B\u673A\uFF1A");
			label_phone.setBounds(10, 262, 54, 15);
			contentPanel.add(label_phone);
		}
		{
			textField_phone = new JTextField();
			textField_phone.setBounds(76, 261, 147, 21);
			contentPanel.add(textField_phone);
			textField_phone.setColumns(10);
		}
		{
			JLabel label_address = new JLabel("\u5BB6\u5EAD\u5730\u5740\uFF1A");
			label_address.setBounds(10, 297, 62, 15);
			contentPanel.add(label_address);
		}

		textArea_address = new JTextArea();
		textArea_address.setLineWrap(true);
		textArea_address.setBorder(UIManager.getBorder("TextField.border"));
		textArea_address.setBounds(76, 293, 147, 39);
		contentPanel.add(textArea_address);
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

	public void eventAdd() {
		try {
			int nextId = teacherdao.getNextId();
			textField_id.setText(nextId + "");
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				row = new Vector();
				//String sex = null;
				if (radioButton_boy.isSelected()) {
					sex = radioButton_boy.getText();
				} else if (radioButton_girl.isSelected()) {
					sex = radioButton_girl.getText();
				}
				// 判断组合框输入的数据是否存在
				if (!comboBoxTextIsExist(comboBox_classes)) {
					JOptionPane.showMessageDialog(dialog, "班级不存在", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// 保存数据
				try {
					if ("true".equals(Config.getConfig("autosave"))) {
						Student stu = new Student();
						stu.setId(Integer.parseInt(textField_id.getText()));
						stu.setName(textField_name.getText());
						stu.setNumber(textField_number.getText());
						stu.setAddress(textArea_address.getText());
						stu.setIdcard(textField_idcard.getText());
						stu.setPhone(textField_phone.getText());
						stu.setSex(sex);
						stu.setPassword(textField_password.getText());
						stu.setClasses(comboBox_classes.getSelectedItem().toString());
						teacherdao.addStudent(stu);
					}
					row.add(teacherdao.getNextId() - 1);
					row.add(textField_number.getText());
					row.add(textField_password.getText());
					row.add(comboBox_classes.getSelectedItem().toString());
					row.add(textField_name.getText());
					row.add(sex);
					row.add(textField_phone.getText());
					row.add(textField_idcard.getText());
					row.add(textArea_address.getText());
					PanelStudent.table_student.setRowVector(row);
					PanelStudent.table_student.refresh();
					MainWindow.label_status.setText("添加学生成功");
				}catch(MySQLIntegrityConstraintViolationException keyerr){
					JOptionPane.showMessageDialog(dialog, "此学生已存在", "提示：", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					e1.printStackTrace();
				} finally {
					dialog.dispose();
				}
			}
		});
	}

	public void eventUpdate() {
		
		textField_id.setText(row.get(0).toString());
		textField_number.setText(row.get(1).toString());
		textField_password.setText(row.get(2).toString());
		comboBox_classes.setSelectedItem(row.get(3));
		textField_name.setText(row.get(4).toString());
		if ("男".equals(row.get(5).toString())) {
			radioButton_boy.setSelected(true);
			radioButton_girl.setSelected(false);
		} else if ("女".equals(row.get(5).toString())) {
			radioButton_girl.setSelected(true);
			radioButton_boy.setSelected(false);
		}
		if (row.get(6) != null)
			textField_phone.setText(row.get(6).toString());
		if (row.get(7) != null)
			textField_idcard.setText(row.get(7).toString());
		if (row.get(8) != null)
			textArea_address.setText(row.get(8).toString());

		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 判断组合框输入的数据是否存在
				if (!comboBoxTextIsExist(comboBox_classes)) {
					JOptionPane.showMessageDialog(dialog, "班级不存在", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (radioButton_boy.isSelected()) {
					sex="男";
				} else if (radioButton_girl.isSelected()) {
					sex="女";
				}
				try {
					if ("true".equals(Config.getConfig("autosave"))) {
						//更新数据
						Student stu = new Student();
						stu.setId(Integer.parseInt(textField_id.getText()));
						stu.setName(textField_name.getText());
						stu.setNumber(textField_number.getText());
						stu.setAddress(textArea_address.getText());
						stu.setIdcard(textField_idcard.getText());
						stu.setPhone(textField_phone.getText());
						stu.setSex(sex);
						stu.setPassword(textField_password.getText());
						stu.setClasses(comboBox_classes.getSelectedItem().toString());
						teacherdao.updateStudent(stu);
					}
					row.set(0, Integer.parseInt(textField_id.getText()));
					row.set(1, textField_number.getText());
					row.set(2, textField_password.getText());
					row.set(3, comboBox_classes.getSelectedItem().toString());
					row.set(4, textField_name.getText());
					row.set(5, sex);
					row.set(6, textField_phone.getText());
					row.set(7, textField_idcard.getText());
					row.set(8, textArea_address.getText());
					int rowNumber = PanelStudent.table_student.getRowNumber(0, Integer.parseInt(textField_id.getText()));
					PanelStudent.table_student.updateRowVector(rowNumber, row);
					PanelStudent.table_student.refresh();
					MainWindow.label_status.setText("修改学生成功");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				dialog.dispose();
			}
		});
	}

	/**
	 * 判断组合框输入的数据是否存在
	 * 
	 * @param comboBox
	 * @return
	 */
	public boolean comboBoxTextIsExist(JComboBox comboBox) {
		for (int i = 0; i < comboBox.getItemCount(); i++) {
			if (comboBox.getItemAt(i).equals(comboBox.getSelectedItem())) {
				return true;
			}
			if (i == comboBox.getItemCount() - 1) {
				return false;
			}
		}
		return false;
	}
}
