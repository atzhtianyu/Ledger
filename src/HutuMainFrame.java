import javax.swing.*;
import java.awt.*;

class HutuMainFrame {
    public static void main(String[] args) {

        JFrame f = new JFrame();
        f.setSize(500, 450);
        f.setTitle("一本糊涂账");
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JToolBar tb = new JToolBar();
        JButton bSpend = new JButton("消费一览");
        JButton bRecord = new JButton("记一笔");
        JButton bCategory = new JButton("消费分类");
        JButton bReport = new JButton("月消费报表");
        JButton bConfig = new JButton("设置");
        JButton bBackup = new JButton("备份");
        JButton bRecover = new JButton("恢复");

        tb.add(bSpend);
        tb.add(bRecord);
        tb.add(bCategory);
        tb.add(bReport);
        tb.add(bConfig);
        tb.add(bBackup);
        tb.add(bRecover);

        f.setLayout(new BorderLayout());
        f.add(tb, BorderLayout.NORTH);
        f.add(new JPanel(), BorderLayout.CENTER);

        f.setVisible(true);

        bSpend.addActionListener(e -> {

        });
        bRecord.addActionListener(e -> {

        });
        bCategory.addActionListener(e -> {

        });
        bReport.addActionListener(e -> {

        });
        bConfig.addActionListener(e -> {

        });
        bBackup.addActionListener(e -> {

        });
        bRecover.addActionListener(e -> {

        });
    }
}
