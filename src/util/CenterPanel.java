package util;

import gui.panel.WorkingPanel;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel {

    private double rate;
    private JComponent c;
    private boolean stretch;

    public CenterPanel(double rate, boolean stretch) {
        this.setLayout(null);
        this.rate = rate;
        this.stretch = stretch;
    }

    public CenterPanel(double rate) {
        this(rate, true);
    }

    public void repaint() {
        if (c != null) {
            Dimension containerSize = this.getSize();
            Dimension componentSize = c.getPreferredSize();

            if (stretch) {
                c.setSize((int) (containerSize.width * rate), (int) (containerSize.height * rate));
            } else {
                c.setSize(componentSize);
            }
            c.setLocation(containerSize.width / 2 - c.getSize().width / 2, containerSize.height / 2 - c.getSize().height / 2);
        }
        super.repaint();
    }

    public void show(JComponent p) {
        this.c = p;
        Component[] cs = getComponents();
        for (Component c : cs) {
            remove(c);
        }
        add(p);
        if (p instanceof WorkingPanel) {
            ((WorkingPanel) p).updateData();
        }

        this.updateUI();
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(200, 200);
        f.setLocationRelativeTo(null);
        CenterPanel cp = new CenterPanel(0.2, true);
        f.setContentPane(cp);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        JButton b = new JButton("abc");
        cp.show(b);
    }

}
