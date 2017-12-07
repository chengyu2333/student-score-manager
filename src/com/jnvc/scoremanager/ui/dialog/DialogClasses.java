package com.jnvc.scoremanager.ui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jnvc.scoremanager.dao.AdminDao;
import com.jnvc.scoremanager.dao.ClassesDao;
import com.jnvc.scoremanager.model.Classes;
import com.jnvc.scoremanager.other.Config;
import com.jnvc.scoremanager.other.Factory;
import com.jnvc.scoremanager.ui.MainWindow;
import com.jnvc.scoremanager.ui.Tools;
import com.jnvc.scoremanager.ui.panel.PanelClasses;
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

public class DialogClasses {

	private final JPanel contentPanel = new JPanel();
	public Vector row;
	private JDialog dialog;
	private JButton okButton;
	private JTextField textField_name;
	private JTextField textField_id;
	private JComboBox comboBox_teacher;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void initAdd() {
		dialog = new JDialog();
		dialog.setTitle("添加班级");
		initUI();
		eventAdd();
	}

	public void initUpdate(Vector newrow) {
		dialog = new JDialog();
		dialog.setTitle("修改班级");
		row = new Vector();
		row = newrow;
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
		dialog.setBounds(0, 0, 210, 260);
		dialog.setBounds(Tools.getScreenWidth() / 2 - dialog.getWidth() / 2,
				Tools.getScreenHeight() / 2 - dialog.getHeight() / 2, 210, 230);
		dialog.setAlwaysOnTop(true);
		dialog.getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialog.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textField_name = new JTextField();
			textField_name.setBounds(74, 73, 120, 21);
			contentPanel.add(textField_name);
			textField_name.setColumns(10);
		}
		{
			JLabel label_name = new JLabel("\u73ED\u7EA7\uFF1A");
			label_name.setBounds(10, 77, 54, 15);
			contentPanel.add(label_name);
		}
		{
			JLabel label_teacher = new JLabel("班主任:");
			label_teacher.setBounds(10, 123, 54, 15);
			contentPanel.add(label_teacher);
		}
		{
			JLabel label_id = new JLabel("\u7F16\u53F7\uFF1A");
			label_id.setBounds(10, 31, 54, 15);
			contentPanel.add(label_id);
		}
		{
			textField_id = new JTextField();
			textField_id.setEnabled(false);
			textField_id.setBounds(74, 26, 120, 21);
			contentPanel.add(textField_id);
			textField_id.setColumns(10);
		}

		{
			comboBox_teacher = new JComboBox();
			comboBox_teacher.setFont(new Font("YaHei Consolas Hybrid", Font.PLAIN, 12));
			comboBox_teacher.setEditable(true);
			try {
				AdminDao admindao = Factory.getAdminDao();
				Vector data = admindao.selectTeacher();

				Vector tea = new Vector();
				for (int i = 0; i < data.size(); i++) {
					Vector row = new Vector();
					row = (Vector) data.get(i);
					comboBox_teacher.addItem(row.get(2));
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			comboBox_teacher.setBounds(74, 120, 120, 21);
		}

		contentPanel.add(comboBox_teacher);
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
		ClassesDao classesdao;
		try {
			classesdao = Factory.getClassesDao();
			int nextId = classesdao.getNextId();
			textField_id.setText(nextId + "");
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				row = new Vector();

				// 判断组合框输入的数据是否存在
				if (!comboBoxTextIsExist(comboBox_teacher)) {
					JOptionPane.showMessageDialog(dialog, "教师不存在", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// 保存数据
				try {
					ClassesDao classesdao = Factory.getClassesDao();
					if ("true".equals(Config.getConfig("autosave"))) {
						Classes cla = new Classes();
						cla.setName(textField_name.getText());
						cla.setTeacher(comboBox_teacher.getSelectedItem().toString());
						System.out.println(classesdao.addClasses(cla));
					}
					row.add(classesdao.getNextId() - 1);
					row.add(textField_name.getText());
					row.add(comboBox_teacher.getSelectedItem().toString());
					PanelClasses.table_classes.setRowVector(row);
					PanelClasses.table_classes.refresh();
					
					MainWindow.label_status.setText("添加班级成功");
				}catch(MySQLIntegrityConstraintViolationException keyerr){
					JOptionPane.showMessageDialog(dialog, "此班级已存在", "提示：", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					e1.printStackTrace();
				} finally {
					dialog.dispose();
				}
			}
		});
	}

	public void eventUpdate() {
		textField_name.setText(row.get(1).toString());
		textField_id.setText(row.get(0).toString());

		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 判断组合框输入的数据是否存在
				if (!comboBoxTextIsExist(comboBox_teacher)) {
					JOptionPane.showMessageDialog(dialog, "教师不存在", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					if ("true".equals(Config.getConfig("autosave"))) {
						ClassesDao classesdao = Factory.getClassesDao();
						Classes cla = new Classes();
						cla.setId(Integer.parseInt(textField_id.getText()));
						cla.setName(textField_name.getText());
						cla.setTeacher((String) comboBox_teacher.getSelectedItem());
						classesdao.updateClasses(cla);
					}
					row.set(0, Integer.parseInt(textField_id.getText()));
					row.set(1, textField_name.getText());
					row.set(2, (String) comboBox_teacher.getSelectedItem());
					int rowNumber = PanelClasses.table_classes.getRowNumber(0, Integer.parseInt(textField_id.getText()));
					PanelClasses.table_classes.updateRowVector(rowNumber, row);
					PanelClasses.table_classes.refresh();
					MainWindow.label_status.setText("删除班级成功");
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
