package com.jnvc.scoremanager.other;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

//解决自动排序的问题
class C extends JFrame {
    C() {
        final JTable jt = new JTable(new Object[][]{{5,1},{3,8},{9,4}}, new Object[]{'A','B'});
        jt.setAutoCreateRowSorter(true);

        add(new JScrollPane(jt));
        add(new JButton(new AbstractAction("显示选定行最左列的值") {
            public void actionPerformed(ActionEvent ae) {
                int rowModel = jt.convertRowIndexToModel(jt.getSelectedRow()),
                    columnModel = jt.convertColumnIndexToModel(0);
                
                JOptionPane.showMessageDialog(null,
                        "<HTML>" +
                        "用 JTable.getValueAt() 获取的值是 " +
                        jt.getValueAt(jt.getSelectedRow(), 0) +
                        "<P>" +
                        "用 JTable.getModel().getValueAt() 和转换过的行列号获取的值是 " +
                        jt.getModel().getValueAt(rowModel, columnModel));

            }
        }), BorderLayout.SOUTH);

        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) { new C(); }
}