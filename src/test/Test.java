package test;

import util.CircleProgressBar;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class Test {
    public static void main(String[] args) {
        Properties props = System.getProperties();
        System.out.println(props.getProperty("os.name"));
    }
}
