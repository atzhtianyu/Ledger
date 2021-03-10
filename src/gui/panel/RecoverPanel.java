package gui.panel;

import gui.listener.RecoverListener;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;

public class RecoverPanel extends WorkingPanel {
    public static RecoverPanel instance = new RecoverPanel();

    public JButton bRecover = new JButton("恢复");

    public RecoverPanel() {
        GUIUtil.setColor(ColorUtil.blueColor, bRecover);
        this.add(bRecover);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(RecoverPanel.instance);
    }

    @Override
    public void updateData() {

    }

    @Override
    public void addListener() {
        RecoverListener listener = new RecoverListener();
        bRecover.addActionListener(listener);
    }
}
