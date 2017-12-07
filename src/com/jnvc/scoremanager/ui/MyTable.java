package com.jnvc.scoremanager.ui;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import com.jnvc.scoremanager.other.Config;

/**
 * 方便的的公共表格类
 */

public class MyTable {
	private Vector data;
	private Vector columnname;
	private Vector temp;
	private DefaultTableModel dtm;
	private JTable jtable;

	/**
	 * 初始化一个空表格对象
	 * 
	 * @return JTable 表格对象
	 */
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	public JTable initialize() {
		data = new Vector();
		dtm = new DefaultTableModel(data, columnname);
		jtable = new JTable(dtm) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		if("true".equals(Config.getConfig("sort"))){
			jtable.setAutoCreateRowSorter(true);
		}
		TableColumn firsetColumn = jtable.getColumnModel().getColumn(0);
		firsetColumn.setMaxWidth(30);
		return jtable;
	}

	/**
	 * 初始化一个带表头的表格对象
	 * 
	 * @param columnname
	 *            表头Vector集合
	 * @return JTable 表格对象
	 */
	public JTable initialize(Vector columnname) {
		this.columnname = columnname;
		data = new Vector();
		dtm = new DefaultTableModel(data, columnname);

		jtable = new JTable(dtm) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		if("true".equals(Config.getConfig("sort"))){
			jtable.setAutoCreateRowSorter(true);
		}

		TableColumn firsetColumn = jtable.getColumnModel().getColumn(0);
		firsetColumn.setMinWidth(30);
		firsetColumn.setMaxWidth(50);
		return jtable;
	}

	/**
	 * 初始化一个带数据的表格对象
	 * 
	 * @param columnname
	 *            表头Vector集合
	 * @param data
	 *            数据Vector集合
	 * @return JTable 表格对象
	 */
	public JTable initialize(Vector columnname, Vector data) {
		this.columnname = columnname;
		this.data = data;
		dtm = new DefaultTableModel(data, columnname);
		jtable = new JTable(dtm) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		if("true".equals(Config.getConfig("sort"))){
			jtable.setAutoCreateRowSorter(true);
		}
		TableColumn firsetColumn = jtable.getColumnModel().getColumn(0);
		firsetColumn.setMaxWidth(30);
		return jtable;
	}

	/**
	 * 获取TableModel
	 * 
	 * @return DefaultTableModel
	 */
	public DefaultTableModel getDefaultTableModel() {
		return dtm;
	}

	public void refresh() {
		dtm.fireTableStructureChanged();
		dtm.fireTableDataChanged();
	}

	/**
	 * 替换表格数据
	 * 
	 * @param Vector
	 *            data
	 * @return 新数据的行数
	 */
	public int setData(Vector newdata) {
		data.clear();
		int i = 0;
		for (i = 0; i < newdata.size(); i++) {
			data.add(newdata.get(i));
		}
		return i;
	}

	/**
	 * 追加数据
	 * 
	 * @param data
	 * @return int 追加数据的行数
	 */
	public int appendData(Vector data) {
		int i;
		for (i = 0; i < data.size(); i++) {
			this.data.add(data.get(i));
		}
		return i + 1;
	}

	/**
	 * 获取整个表格的数据
	 * 
	 * @return Vector
	 */
	public Vector getData() {
		return this.data;
	}

	/**
	 * 清空表格数据
	 */
	public void clear() {
		dtm.getDataVector().clear();
	}

	/**
	 * 插入一行数据
	 * 
	 * @param row
	 *            Vector集合
	 */
	public void setRowVector(Vector row) {
		data.add(row);
	}

	/**
	 * 获取一行数据
	 * 
	 * @param row
	 * @return Vector集合
	 */
	public Vector getRowVector(int row) {
		temp = new Vector();
		for (int i = 0; i < dtm.getColumnCount(); i++) {
			
			row = jtable.convertRowIndexToModel(row);
			i = jtable.convertColumnIndexToModel(i);
			
			 temp.add(dtm.getValueAt(row, i));
		}
		return temp;
	}

	/**
	 * 更新一行数据
	 * 
	 * @param row
	 * @param newrow
	 */
	public void updateRowVector(int row, Vector rowdata) {
		temp = new Vector();
		for (int i = 0; i < dtm.getColumnCount(); i++) {
			dtm.setValueAt(rowdata.get(i), row, i);
		}
	}

	/**
	 * 删除一行数据
	 * 
	 * @param row
	 */
	public void removeRow(int row) {
		dtm.removeRow(row);
	}

	/**
	 * 获取单元格数据
	 * 
	 * @param row
	 *            行
	 * @param column
	 *            列
	 * @return Object
	 */
	public Object getValueAt(int row, int column) {
		
		row = jtable.convertRowIndexToModel(row);
		column = jtable.convertColumnIndexToModel(column);
		
		return dtm.getValueAt(row, column);
	}

	/**
	 * 设置单元格数据
	 * 
	 * @param row
	 *            行
	 * @param column
	 *            列
	 * @return Object
	 */
	public void setValueAt(Object obj, int row, int column) {
		dtm.setValueAt(obj, row, column);
	}

	/**
	 * 获取列数
	 * 
	 * @return
	 */
	public int getColumnCount() {
		return dtm.getColumnCount();
	}

	/**
	 * 获取行数
	 * 
	 * @return
	 */
	public int getRowCount() {
		return dtm.getRowCount();
	}

	/**
	 * 获取某值所在行
	 * 
	 * @param column
	 *            列
	 * @param value
	 *            值
	 * @return 所在行，-1未找到
	 */
	public int getRowNumber(int column, Object value) {
		int i;
		for (i = 0; i < jtable.getRowCount(); i++) {
			if (dtm.getValueAt(i, column) == value) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 插入对象至头部
	 * 
	 * @param vec
	 *            vector集合
	 * @param obj
	 *            待插入对象
	 * @return vector对象
	 */
	public Vector insertHead(Vector vec, Object obj) {
		Vector temp = new Vector();
		temp.add(obj);
		for (int i = 0; i < vec.size(); i++) {
			temp.add(vec.get(i));
		}
		return temp;
	}

	/**
	 * 为表格显示自动编号的id
	 */
	public void showId() {
		int rowcount = dtm.getRowCount();
		Vector rowid = new Vector();
		for (int i = 1; i <= rowcount; i++) {
			rowid.add(i);
		}
		dtm.addColumn("id", rowid);
	}
}
