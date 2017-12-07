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

import com.jnvc.scoremanager.dao.ClassesDao;
import com.jnvc.scoremanager.dao.CourseDao;
import com.jnvc.scoremanager.dao.ScoreDao;
import com.jnvc.scoremanager.dao.TeacherDao;
import com.jnvc.scoremanager.model.Score;
import com.jnvc.scoremanager.other.Config;
import com.jnvc.scoremanager.other.Factory;
import com.jnvc.scoremanager.ui.MainWindow;
import com.jnvc.scoremanager.ui.Tools;
import com.jnvc.scoremanager.ui.panel.PanelScore;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;

public class DialogScore {
	private final JPanel contentPanel = new JPanel();
	public Vector row;
	private JDialog dialog;
	private JButton okButton,cancelButton;
	private JTextField textField_number,textField_id,textField_score,textField_name;
	private JComboBox comboBox_subject, comboBox_term;
	public ScoreDao scoredao;
	public CourseDao coursedao;
	public TeacherDao teacherdao;
	public ClassesDao classesdao;
	private JTextField textField_classes;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void initAdd() {
		dialog = new JDialog();
		dialog.setTitle("录入成绩");
		try {
			scoredao = Factory.getScoreDao();
			coursedao = Factory.getCourseDao();
			teacherdao = Factory.getTeacherDao();
			classesdao = Factory.getClassesDao();
		} catch (Exception e) {
			System.out.println("获取业务对象异常");
			e.printStackTrace();
		}

		initUI();
		eventAdd();
	}

	public void initUpdate(Vector newrow) {
		if("true".equals(Config.getConfig("sort"))){
			JOptionPane.showMessageDialog(MainWindow.frame, "开启排序后不能修改数据！");
			return;
		}
		dialog = new JDialog();
		dialog.setTitle("修改成绩");
		row = new Vector();
		row = newrow;
		try {
			scoredao = Factory.getScoreDao();
			coursedao = Factory.getCourseDao();
			teacherdao = Factory.getTeacherDao();
			classesdao = Factory.getClassesDao();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		initUI();
		textField_number.setEnabled(false);
		comboBox_subject.setEnabled(false);
		comboBox_term.setEnabled(false);
		eventUpdate();
	}

	public void initUI() {
		dialog.setIconImage(Toolkit.getDefaultToolkit().getImage("res\\icon.png"));
		dialog.setVisible(true);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setResizable(false);
		dialog.setBounds(0, 0, 210, 342);
		dialog.setBounds(Tools.getScreenWidth() / 2 - dialog.getWidth() / 2,
				Tools.getScreenHeight() / 2 - dialog.getHeight() / 2, 210, 310);
		dialog.setAlwaysOnTop(true);
		dialog.getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialog.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textField_number = new JTextField();
			textField_number.setBounds(68, 127, 126, 21);
			contentPanel.add(textField_number);
			textField_number.setColumns(10);
		}
		{
			JLabel label_number = new JLabel("学号：");
			label_number.setBounds(10, 133, 54, 15);
			contentPanel.add(label_number);
		}
		{
			JLabel label_subject = new JLabel("科目：");
			label_subject.setBounds(10, 170, 54, 15);
			contentPanel.add(label_subject);
		}
		{
			JLabel label_id = new JLabel("id：");
			label_id.setBounds(10, 22, 54, 15);
			contentPanel.add(label_id);
		}
		{
			textField_id = new JTextField();
			textField_id.setEnabled(false);
			textField_id.setBounds(68, 16, 126, 21);
			contentPanel.add(textField_id);
			textField_id.setColumns(10);
		}

		{// 组合框 科目
			comboBox_subject = new JComboBox();
			comboBox_subject.setEditable(true);
			comboBox_subject.setFont(new Font("YaHei Consolas Hybrid", Font.PLAIN, 12));
			comboBox_subject.setBounds(68, 164, 126, 21);
			
			try {
				CourseDao coursedao = Factory.getCourseDao();
				Vector data = coursedao.selectCourse();
				for (int i = 0; i < data.size(); i++) {
					Vector row = (Vector) data.get(i);
					
					comboBox_subject.addItem(row.get(2).toString());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		contentPanel.add(comboBox_subject);

		{// 组合框 学期
			comboBox_term = new JComboBox();
			comboBox_term.setEditable(true);
			comboBox_term.setFont(new Font("YaHei Consolas Hybrid", Font.PLAIN, 12));
			comboBox_term.setBounds(68, 201, 126, 21);
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
			JLabel label_term = new JLabel("\u5B66\u671F\uFF1A");
			label_term.setBounds(10, 207, 54, 15);
			contentPanel.add(label_term);
		}
		{
			JLabel label_score = new JLabel("\u6210\u7EE9\uFF1A");
			label_score.setBounds(10, 244, 54, 15);
			contentPanel.add(label_score);
		}

		textField_score = new JTextField();
		textField_score.setBounds(68, 238, 66, 21);
		contentPanel.add(textField_score);
		textField_score.setColumns(10);
		{
			JLabel label_name = new JLabel("\u59D3\u540D\uFF1A");
			label_name.setBounds(10, 59, 54, 15);
			contentPanel.add(label_name);
		}
		{
			textField_name = new JTextField();
			textField_name.setEnabled(false);
			textField_name.setBounds(68, 53, 124, 21);
			contentPanel.add(textField_name);
			textField_name.setColumns(10);
		}
		
		JLabel label_classes = new JLabel("\u73ED\u7EA7\uFF1A");
		label_classes.setBounds(10, 96, 54, 15);
		contentPanel.add(label_classes);
		
		textField_classes = new JTextField();
		textField_classes.setEnabled(false);
		textField_classes.setBounds(68, 90, 126, 21);
		contentPanel.add(textField_classes);
		textField_classes.setColumns(10);
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
				 cancelButton = new JButton("取消");
				
				buttonPane.add(cancelButton);
			}
		}
		event();
	}
	public void event(){
		//取消按钮
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		
		// 学号编辑框失去焦点
		textField_number.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				Vector stu = new Vector();
				try {
					stu = teacherdao.selectStudentByNum(textField_number.getText());
					if(!stu.isEmpty()){
						stu = (Vector) stu.get(0);
						textField_classes.setText(stu.get(3).toString());
						textField_name.setText(stu.get(4).toString());
						
						Vector dataCourse = coursedao.selectCourseByClasses(textField_classes.getText());
						Vector tea = new Vector();
						for (int i = 0; i < dataCourse.size(); i++) {
							Vector row = new Vector();
							row = (Vector) dataCourse.get(i);
							comboBox_subject.addItem(row.get(2));
						}
						
						Vector dataTerm = coursedao.termList(textField_classes.getText());
						for (int i = 0; i < dataTerm.size(); i++) {
							comboBox_term.addItem(dataTerm.get(i));
						}
					}else{
						JOptionPane.showMessageDialog(dialog, "该学号不存在", "提示：", JOptionPane.WARNING_MESSAGE);
						textField_number.setText("");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	public void eventAdd() {
		// id编辑框
		try {
			int nextId = scoredao.getNextId();
			textField_id.setText(nextId + "");
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		// 确定按钮被单击
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				row = new Vector();
				//属否输入完全
				if ("".equals(textField_score.getText()) || "".equals(textField_number.getText())
						|| "".equals(comboBox_subject.getSelectedItem().toString())) {
					JOptionPane.showMessageDialog(dialog, "请将信息填写完整", "提示：", JOptionPane.WARNING_MESSAGE);
					return;
				}
				// 判断组合框输入的数据是否存在
				if (!comboBoxTextIsExist(comboBox_subject)) {
					JOptionPane.showMessageDialog(dialog, "科目不存在", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// 尝试保存成绩信息
				try {
					//成绩是否存在
					if(scoredao.existScore(textField_number.getText(), comboBox_subject.getSelectedItem().toString(), comboBox_term.getSelectedItem().toString() )){
						JOptionPane.showMessageDialog(dialog, "该科目成绩已经存在", "提示：", JOptionPane.ERROR_MESSAGE);
						return;
					}
					Vector stu = new Vector();
					stu = teacherdao.selectStudentByNum(textField_number.getText());
					stu = (Vector) stu.get(0);
					Vector cla = new Vector();
					cla = classesdao.selectClassesByName(stu.get(3).toString());
					cla = (Vector) cla.get(0);
					if ("true".equals(Config.getConfig("autosave"))) {
						//自动保存数据
						Score sco = new Score();
						int courseid=coursedao.selectCourseId(comboBox_subject.getSelectedItem().toString(),
								comboBox_term.getSelectedItem().toString());
						sco.setScore(Float.parseFloat(textField_score.getText()));
						sco.setNumber(textField_number.getText());
						sco.setCourse(courseid);
						scoredao.addScore(sco);
					}
					row.add(scoredao.getNextId() - 1);
					row.add(textField_number.getText());
					row.add(cla.get(1));// 班级
					row.add(stu.get(4));// 姓名
					row.add(comboBox_term.getSelectedItem());
					row.add(comboBox_subject.getSelectedItem());//
					row.add(textField_score.getText());
					PanelScore.table_score.setRowVector(row);
					PanelScore.table_score.refresh();
					MainWindow.label_status.setText("添加成绩成功");
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
		textField_classes.setText(row.get(2).toString());
		textField_name.setText(row.get(3).toString());
		comboBox_term.setSelectedItem(row.get(4).toString());
		comboBox_subject.setSelectedItem(row.get(5).toString());
		textField_score.setText(row.get(6).toString());
		// 确定按钮被单击
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//属否输入完全
				if ("".equals(textField_score.getText()) || "".equals(textField_number.getText())
						|| "".equals(comboBox_subject.getSelectedItem().toString())) {
					JOptionPane.showMessageDialog(dialog, "请将信息填写完整", "提示：", JOptionPane.WARNING_MESSAGE);
					return;
				}
				// 判断组合框输入的数据是否存在
				if (!comboBoxTextIsExist(comboBox_subject)) {
					JOptionPane.showMessageDialog(dialog, "科目不存在", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// 保存数据
				try {
					Vector stu = new Vector();
					stu = teacherdao.selectStudentByNum(textField_number.getText());
					stu = (Vector) stu.get(0);
					Vector cla = new Vector();
					cla = classesdao.selectClassesByName(stu.get(3).toString());
					cla = (Vector) cla.get(0);
					if ("true".equals(Config.getConfig("autosave"))) {
						//自动保存数据
						Score sco = new Score();
						sco.setId(Integer.parseInt(textField_id.getText()));
						sco.setScore(Float.parseFloat(textField_score.getText()));
						sco.setNumber(textField_number.getText());
						sco.setCourse(coursedao.selectCourseId(comboBox_subject.getSelectedItem().toString(),
								comboBox_term.getSelectedItem().toString()));
						scoredao.updateScore(sco);
					}
					row.set(0, textField_id.getText());
					row.set(1, textField_number.getText());
					row.set(2, cla.get(1));// 班级
					row.set(3, stu.get(4));// 姓名
					row.set(4, comboBox_term.getSelectedItem());
					row.set(5, comboBox_subject.getSelectedItem());//
					row.set(6, textField_score.getText());
					int rowNumber = PanelScore.table_score.getRowNumber(0, Integer.parseInt(textField_id.getText()));
					PanelScore.table_score.updateRowVector(rowNumber, row);
					PanelScore.table_score.refresh();
					MainWindow.label_status.setText("修改成绩成功");
				} catch (Exception e1) {
					e1.printStackTrace();
				} finally {
					dialog.dispose();
				}
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
