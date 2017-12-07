package com.jnvc.scoremanager.ui.panel;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.jnvc.scoremanager.dao.LogDao;
import com.jnvc.scoremanager.other.Factory;
import com.jnvc.scoremanager.ui.MainWindow;
import com.jnvc.scoremanager.ui.MyTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelLog {
	public static MyTable table_log;
	private JTable jtable_log;
	private JPanel panel_log;
	public PanelLog() {

		// 系统管理页
		 panel_log = new JPanel();
		MainWindow.tabbedPane.addTab("系统日志", null, panel_log, null);
		panel_log.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 70, 620, 400);
		panel_log.add(scrollPane);

		table_log = new MyTable();
		Vector<String> columnname = new Vector();
		columnname.add("id");
		columnname.add("操作");
		columnname.add("目标");
		columnname.add("原值");
		columnname.add("新值");
		columnname.add("操作者");
		columnname.add("是否生效");
		columnname.add("时间");
		jtable_log = table_log.initialize(columnname);
		scrollPane.setViewportView(jtable_log);

		JButton button_apply = new JButton("应用操作");
		button_apply.setBounds(682, 126, 93, 23);
		panel_log.add(button_apply);

		JButton button_recover = new JButton("恢复操作");
		button_recover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(panel_log, "该操作会恢复该操作，新值将被替换为旧值，是否继续？", "提示：", JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
			}
		});
		button_recover.setBounds(682, 174, 93, 23);
		panel_log.add(button_recover);
		JButton button_clean = new JButton("清理日志");
		button_clean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(panel_log, "该操作会清理7天以前的日志，是否继续？", "提示：", JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
			}
		});
		button_clean.setBounds(680, 222, 93, 23);
		panel_log.add(button_clean);
		
		JButton button_refrush = new JButton("刷新");
		button_refrush.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LogDao logdao = Factory.getLogDao();
					table_log.setData(logdao.selectLog());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		button_refrush.setBounds(682, 70, 93, 23);
		panel_log.add(button_refrush);
		event();
	}
	public void event(){
		try {
			LogDao logdao = Factory.getLogDao();
			table_log.setData(logdao.selectLog());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
