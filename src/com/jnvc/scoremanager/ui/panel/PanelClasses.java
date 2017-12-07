package com.jnvc.scoremanager.ui.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.jnvc.scoremanager.dao.ClassesDao;
import com.jnvc.scoremanager.other.Factory;
import com.jnvc.scoremanager.ui.MainWindow;
import com.jnvc.scoremanager.ui.MyTable;
import com.jnvc.scoremanager.ui.dialog.DialogClasses;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuItem;
import java.awt.Font;

public class PanelClasses {

	public static JScrollPane scrollPane_classes;
	public static JPanel panel;
	private JPanel panel_classes;
	private JButton button_addClasses, button_removeClasses, button_saveClasses, button_cancelClasses,
			button_updateClasses, button_searchClasses;
	private JTable jtable_classes;
	public static MyTable table_classes;
	private JTextField textField_searchClasses;
	private JComboBox comboBox_classes;
	private JTabbedPane tabbedPane = MainWindow.tabbedPane;

	public PanelClasses() {
		panel_classes = new JPanel();
		tabbedPane.addTab("班级管理", null, panel_classes, null);
		panel_classes.setLayout(null);

		scrollPane_classes = new JScrollPane();
		scrollPane_classes.setBounds(10, 70, 620, 400);
		panel_classes.add(scrollPane_classes);
		// 初始化表格
		table_classes = new MyTable();
		Vector<String> colname_classes = new Vector<String>();
		colname_classes.add("id");
		colname_classes.add("班级");
		colname_classes.add("班主任");

		jtable_classes = table_classes.initialize(colname_classes);
		scrollPane_classes.setViewportView(jtable_classes);

		textField_searchClasses = new JTextField();
		textField_searchClasses.setBounds(105, 22, 100, 21);
		panel_classes.add(textField_searchClasses);
		textField_searchClasses.setColumns(10);

		button_searchClasses = new JButton("查找");
		button_searchClasses.setBounds(215, 21, 68, 23);
		button_searchClasses.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		panel_classes.add(button_searchClasses);

		comboBox_classes = new JComboBox();
		comboBox_classes.setFont(new Font("YaHei Consolas Hybrid", Font.PLAIN, 12));
		comboBox_classes
				.setModel(new DefaultComboBoxModel(new String[] { "\u73ED\u7EA7\u540D", "\u73ED\u4E3B\u4EFB" }));
		comboBox_classes.setBounds(21, 22, 74, 21);
		panel_classes.add(comboBox_classes);

		// 操作按钮
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(640, 70, 150, 400);
		panel_classes.add(panel);
		panel.setLayout(null);

		button_addClasses = new JButton("添加班级");
		button_addClasses.setBounds(28, 28, 93, 40);
		panel.add(button_addClasses);

		button_removeClasses = new JButton("删除班级");
		button_removeClasses.setBounds(28, 85, 93, 23);
		panel.add(button_removeClasses);

		button_saveClasses = new JButton("保存修改");
		button_saveClasses.setBounds(28, 169, 93, 23);
		panel.add(button_saveClasses);

		button_cancelClasses = new JButton("取消修改");
		button_cancelClasses.setBounds(28, 202, 93, 23);
		panel.add(button_cancelClasses);

		button_updateClasses = new JButton("修改班级");
		button_updateClasses.setBounds(28, 118, 93, 23);
		panel.add(button_updateClasses);

		
		// 弹出菜单
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(jtable_classes, popupMenu);
		scrollPane_classes.add(popupMenu);
		
		JMenuItem menuItem_updateClasses = new JMenuItem("修改该班级");
		popupMenu.add(menuItem_updateClasses);

		JMenuItem menuItem_removeClasses = new JMenuItem("删除该班级");
		popupMenu.add(menuItem_removeClasses);

		JMenuItem menuItem_classesSelectStudent = new JMenuItem("查看该班学生");
		popupMenu.add(menuItem_classesSelectStudent);
		
		JMenuItem menuItem_classesSelectCourse = new JMenuItem("查看该班课程");
		popupMenu.add(menuItem_classesSelectCourse);

		event();
	}

	private void event() {
		try {
			ClassesDao classesdao = Factory.getClassesDao();
			table_classes.setData(classesdao.selectClasses());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(MainWindow.frame, "初始化班级管理面板异常");
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		// 右击表格
		jtable_classes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					//System.out.println(jtable_classes.getSelectedRow());
				}
			}
		});
		// 双击表格、右键选中
		jtable_classes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = jtable_classes.rowAtPoint(e.getPoint());
				if (row >= 0)
				{
					jtable_classes.setRowSelectionInterval(row, row);
				}
				if (e.getClickCount() == 2) {
					new DialogClasses().initUpdate(table_classes.getRowVector(jtable_classes.getSelectedRow()));
				}
			}
		});
		// 搜索
		button_searchClasses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ClassesDao classesdao = Factory.getClassesDao();
					if ("".equals(textField_searchClasses.getText())) {
						table_classes.setData(classesdao.selectClasses());
						MainWindow.label_status.setText("显示全部班级");
					} else {
						if ("班级名".equals(comboBox_classes.getSelectedItem())) {
							table_classes.setData(classesdao.selectClassesByName(textField_searchClasses.getText()));
							MainWindow.label_status.setText("按班级名查找成功");
						} else if ("班主任".equals(comboBox_classes.getSelectedItem())) {
							table_classes.setData(classesdao.selectClassesByTeacher(textField_searchClasses.getText()));
							MainWindow.label_status.setText("按班主任查找成功");
						}
					}
					table_classes.refresh();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		// 删除
		button_removeClasses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					ClassesDao classesdao = Factory.getClassesDao();
					int[] rowid = jtable_classes.getSelectedRows();
					if(rowid.length<1){
						return;
					}
					if (JOptionPane.showConfirmDialog(MainWindow.frame, "选中了【" + rowid.length + "】条数据，真的要删除么", "提示：",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
						for (int i = rowid.length - 1; i >= 0; i--) {
							System.out.println(rowid[i]);
							if (classesdao.deleteClasses(
									Integer.parseInt(table_classes.getValueAt(rowid[i], 0).toString()))) {
								table_classes.removeRow(rowid[i]);
							}
						}
						MainWindow.label_status.setText("删除班级成功");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		// 添加
		button_addClasses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DialogClasses().initAdd();
			}
		});
		// 修改
		button_updateClasses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jtable_classes.getSelectedRow()==-1){
					return;
				}
				new DialogClasses().initUpdate(table_classes.getRowVector(jtable_classes.getSelectedRow()));
			}
		});
		// 取消修改
		button_cancelClasses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		// 保存修改
		button_saveClasses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
