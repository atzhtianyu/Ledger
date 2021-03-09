package gui.panel;

import dao.CategoryDAO;
import entity.Category;
import gui.listener.CategoryListener;
import gui.model.CategoryTableModel;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class CategoryPanel extends WorkingPanel {
    public static CategoryPanel instance = new CategoryPanel();

    public JButton bAdd = new JButton("新增");
    public JButton bEdit = new JButton("编辑");
    public JButton bDelete = new JButton("删除");

    public CategoryTableModel ctm = new CategoryTableModel();

    public JTable t = new JTable(ctm);

    public CategoryPanel() {
        GUIUtil.setColor(ColorUtil.blueColor, bAdd, bEdit, bDelete);
        JScrollPane sp = new JScrollPane(t);
        JPanel submit = new JPanel();
        submit.add(bAdd);
        submit.add(bEdit);
        submit.add(bDelete);

        this.setLayout(new BorderLayout());
        this.add(sp, BorderLayout.CENTER);
        this.add(submit, BorderLayout.SOUTH);

        addListener();
    }

    public Category getSelectedCategory() {
        int index = t.getSelectedRow();
        return ctm.cs.get(index);
    }

    public void updateData() {
        ctm.cs = new CategoryDAO().list();
        t.updateUI();
        t.getSelectionModel().setSelectionInterval(0, 0);
        if (ctm.cs.size() == 0) {
            bDelete.setEnabled(false);
            bEdit.setEnabled(false);
        } else {
            bDelete.setEnabled(true);
            bEdit.setEnabled(true);
        }
    }

    public void addListener() {
        CategoryListener l = new CategoryListener();
        bAdd.addActionListener(l);
        bEdit.addActionListener(l);
        bDelete.addActionListener(l);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(CategoryPanel.instance);
    }

}
