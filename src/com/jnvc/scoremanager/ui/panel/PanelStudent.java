package com.jnvc.scoremanager.ui.panel;

import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicTabbedPaneUI.TabbedPaneLayout;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.jnvc.scoremanager.dao.ClassesDao;
import com.jnvc.scoremanager.dao.TeacherDao;
import com.jnvc.scoremanager.other.Factory;
import com.jnvc.scoremanager.ui.MainWindow;
import com.jnvc.scoremanager.ui.MyTable;
import com.jnvc.scoremanager.ui.dialog.DialogStudent;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

public class PanelStudent {
	public static JScrollPane scrollPane_student;
	public static JPanel panel;
	private JTextField textField_searchStudent;
	private JButton button_addStudent, button_removeStudent, button_updateStudent, button_saveStudent,
			button_cancelStudent, button_searchStudent;
	private JComboBox comboBox_student;
	public static MyTable table_student;
	private JTable jtable_student;
	private JMenuItem menuItem_deleteStudent;

	public PanelStudent() {
		// 学生管理页
		JPanel panel_student = new JPanel();
		MainWindow.tabbedPane.addTab("学生管理", null, panel_student, null);
		panel_student.setLayout(null);

		scrollPane_student = new JScrollPane();
		scrollPane_student.setBounds(10, 70, 620, 400);
		panel_student.add(scrollPane_student);

		table_student = new MyTable();
		Vector<String> column_student = new Vector<String>();
		column_student.add("id");
		column_student.add("学号");
		column_student.add("密码");
		column_student.add("班级");
		column_student.add("姓名");
		column_student.add("性别");
		column_student.add("手机");
		column_student.add("身份证");
		column_student.add("地址");
		jtable_student = table_student.initialize(column_student);
		jtable_student.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane_student.setViewportView(jtable_student);

		//弹出菜单
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(jtable_student, popupMenu);
		scrollPane_student.add(popupMenu);
		
		menuItem_deleteStudent = new JMenuItem("删除学生");
		popupMenu.add(menuItem_deleteStudent);
		
		JMenuItem menuItem_updateStudent = new JMenuItem("修改学生");
		popupMenu.add(menuItem_updateStudent);
		
		JMenuItem menuItem_selectScore = new JMenuItem("查看成绩");
		popupMenu.add(menuItem_selectScore);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(640, 70, 150, 400);
		panel_student.add(panel);
		panel.setLayout(null);

		button_addStudent = new JButton("添加学生");
		button_addStudent.setBounds(28, 28, 93, 40);
		panel.add(button_addStudent);

		button_removeStudent = new JButton("删除学生");
		button_removeStudent.setBounds(28, 85, 93, 23);
		panel.add(button_removeStudent);

		button_updateStudent = new JButton("修改学生");
		button_updateStudent.setBounds(28, 118, 93, 23);
		panel.add(button_updateStudent);

		button_saveStudent = new JButton("保存修改");
		button_saveStudent.setBounds(28, 169, 93, 23);
		panel.add(button_saveStudent);

		button_cancelStudent = new JButton("取消修改");
		button_cancelStudent.setBounds(28, 202, 93, 23);
		panel.add(button_cancelStudent);

		comboBox_student = new JComboBox();
		comboBox_student
				.setModel(new DefaultComboBoxModel(new String[] { "\u5B66\u53F7", "\u73ED\u7EA7", "\u59D3\u540D" }));
		comboBox_student.setBounds(21, 22, 74, 21);
		panel_student.add(comboBox_student);

		textField_searchStudent = new JTextField();
		textField_searchStudent.setBounds(105, 22, 100, 21);
		panel_student.add(textField_searchStudent);
		textField_searchStudent.setColumns(10);

		button_searchStudent = new JButton("查找");
		button_searchStudent.setBounds(215, 21, 68, 23);
		button_searchStudent.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		panel_student.add(button_searchStudent);


		event();
	}

	private void event() {
		if(MainWindow.loginer.getPower()==1){
			button_searchStudent.setEnabled(false);
		}
		
		try {
			TeacherDao teacherdao = Factory.getTeacherDao();
			table_student.setData(teacherdao.selectStudent());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(MainWindow.frame, "初始化学生管理面板异常");
			e.printStackTrace();
		}
		
		// 添加学生
		button_addStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DialogStudent().initAdd();
			}
		});
		button_updateStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jtable_student.getSelectedRow()>0){
					new DialogStudent().initUpdate(table_student.getRowVector(jtable_student.getSelectedRow()));
				}
			}
		});
		// 双击表格
		jtable_student.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = jtable_student.rowAtPoint(e.getPoint());
				if (row >= 0)
				{
					jtable_student.setRowSelectionInterval(row, row);
				}
				if (e.getClickCount() == 2) {
					new DialogStudent().initUpdate(table_student.getRowVector(jtable_student.getSelectedRow()));
				}
			}
		});
		// 搜索
		button_searchStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TeacherDao teacherdao = Factory.getTeacherDao();
					if ("".equals(textField_searchStudent.getText())) {
						table_student.setData(teacherdao.selectStudent());
						MainWindow.label_status.setText("显示全部学生");
					} else {
						if ("学号".equals(comboBox_student.getSelectedItem())) {
							table_student.setData(teacherdao.selectStudentByNum(textField_searchStudent.getText()));
							MainWindow.label_status.setText("按学号查找成功");							
						} else if ("班级".equals(comboBox_student.getSelectedItem())) {
							table_student.setData(teacherdao.selectStudentByClasses(textField_searchStudent.getText()));
							MainWindow.label_status.setText("按班级查找成功");
						} else if ("姓名".equals(comboBox_student.getSelectedItem())) {
							table_student.setData(teacherdao.selectStudentByName(textField_searchStudent.getText()));
							MainWindow.label_status.setText("按姓名查找成功");
						}
					}
					table_student.refresh();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		// 删除
		button_removeStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					ClassesDao classesdao = Factory.getClassesDao();
					int[] rowid = jtable_student.getSelectedRows();
					if(rowid.length<1){
						return;
					}
					if (JOptionPane.showConfirmDialog(MainWindow.frame, "选中了【" + rowid.length + "】条数据，真的要删除么", "提示：",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
						for (int i = rowid.length - 1; i >= 0; i--) {
							System.out.println(rowid[i]);

							if (classesdao.deleteClasses(
									Integer.parseInt(table_student.getValueAt(rowid[i], 0).toString()))) {
								table_student.removeRow(rowid[i]);
							}
						}
						MainWindow.label_status.setText("删除学生成功");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
	}
	// 右键菜单
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
