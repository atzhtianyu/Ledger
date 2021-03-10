package gui.listener;

import dao.CategoryDAO;
import entity.Category;
import gui.frame.MainFrame;
import gui.model.CategoryComboBoxModel;
import gui.panel.CategoryPanel;
import gui.panel.MainPanel;
import gui.panel.RecordPanel;
import gui.panel.SpendPanel;
import service.RecordService;
import util.GUIUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class RecordListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        RecordPanel p = RecordPanel.instance;
        if (p.cbModel.cs.size() == 0) {
            JOptionPane.showMessageDialog(p, "暂无消费分类，无法添加，请先增加消费分类");
            MainPanel.instance.workingPanel.show(CategoryPanel.instance);
            return;
        }
        if (!GUIUtil.checkNumber(p.tfSpend, "消费金额")) {
            return;
        }
        int spend = Integer.parseInt(p.tfSpend.getText());
        Category c = p.getSelectedCategory();
        String comment = p.tfComment.getText();
        Date d = p.datePicker.getDate();

        new RecordService().add(spend, c, comment, d);
        JOptionPane.showMessageDialog(p, "添加成功");

        MainPanel.instance.workingPanel.show(SpendPanel.instance);
    }
}
