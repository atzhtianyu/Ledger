package util;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GUIUtil {

    private static final String imageFolder = "./src/img";

    public static void setImageIcon(JButton b, String fileName, String tip) {
        ImageIcon i = new ImageIcon(new File(imageFolder, fileName).getAbsolutePath());
        b.setIcon(i);
        b.setToolTipText(tip);
        b.setPreferredSize(new Dimension(60, 80));
        b.setVerticalTextPosition(JButton.BOTTOM);
        b.setHorizontalTextPosition(JButton.CENTER);
        b.setText(tip);
    }

    public static void setColor(Color color, Component... cs) {
        for (Component c : cs) {
            c.setForeground(color);
        }
    }

    public static void showPanel(JPanel p, double stretchRate) {
        JFrame f = new JFrame();
        f.setSize(500, 500);
        f.setLocationRelativeTo(null);
        CenterPanel cp = new CenterPanel(stretchRate);
        f.setContentPane(cp);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        cp.show(p);
    }

    public static void showPanel(JPanel p) {
        showPanel(p, 0.85);
    }

    public static boolean checkEmpty(JTextField tf, String input) {
        String text = tf.getText().trim();
        if (text.length() == 0) {
            JOptionPane.showMessageDialog(null, input + "不能为空");
            tf.grabFocus();
            return false;
        }
        return true;
    }

    public static boolean checkNumber(JTextField tf, String input) {
        if (!checkEmpty(tf, input)) {
            return false;
        }
        String text = tf.getText().trim();
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, input + "需要是整数");
            tf.grabFocus();
            return false;
        }
    }

    public static boolean checkZero(JTextField tf, String input) {
        if (!checkNumber(tf, input)) {
            return false;
        }
        String text = tf.getText().trim();
        if (Integer.parseInt(text) == 0) {
            JOptionPane.showMessageDialog(null, input + "不能为零");
            tf.grabFocus();
            return false;
        }
        return true;
    }

    public static int getInt(JTextField tf) {
        return Integer.parseInt(tf.getText());
    }

}
