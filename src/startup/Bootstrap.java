package startup;

import gui.frame.MainFrame;
import gui.panel.MainPanel;
import gui.panel.SpendPanel;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class Bootstrap {
    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(() -> {
            MainFrame.instance.setVisible(true);
            MainPanel.instance.workingPanel.show(SpendPanel.instance);
        });
    }
}
