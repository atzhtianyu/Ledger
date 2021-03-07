package gui.panel;

import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;

public class BackupPanel extends JPanel {
    public static BackupPanel instance = new BackupPanel();

    public JButton bBackup = new JButton("备份");

    public BackupPanel() {
        GUIUtil.setColor(ColorUtil.blueColor, bBackup);
        this.add(bBackup);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(BackupPanel.instance);
    }
}