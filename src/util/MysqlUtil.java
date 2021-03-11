package util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class MysqlUtil {
    public static void backup(String mysqlPath, String backupPath) {
        Runtime runtime = Runtime.getRuntime();
        String command = "";
        Properties props = System.getProperties();
        if (props.getProperty("os.name").equals("Mac OS X")) {
            command = getMacExportCommand(mysqlPath, backupPath);
        }
        if (props.getProperty("os.name").equals("Windows")) {
            String commandFormat = "\"%s/bin/mysqldump.exe\" -u%s -p%s   -hlocalhost   -P%d %s -r \"%s\"";

            command = String.format(commandFormat, mysqlPath, DBUtil.loginName, DBUtil.password, DBUtil.port,
                    DBUtil.database, backupPath);
        }
        // 这里其实是在命令窗口中执行的 command 命令行
        try {
            Process exec = runtime.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 得到导出数据的命令行语句
    private static String getMacExportCommand(String mysqlPath, String backupPath) {
        String username = DBUtil.loginName;// 用户名
        String password = DBUtil.password;// 密码
        String host = "localhost";// 导入的目标数据库所在的主机
        String port = "3306";// 使用的端口号
        String exportDatabaseName = DBUtil.database;// 导入的目标数据库的名称
        // 注意哪些地方要空格，哪些不要空格
        return mysqlPath + "/bin/mysqldump -u" + username + " -p" + password +// 密码是用的小p，而端口是用的大P。
                " -h" + host + " -P" + port + " " + exportDatabaseName +
                " -r " + backupPath;
    }

    public static void recover(String mysqlPath, String recoverFile) {
        try {
            String commandFormat = "";
            Properties props = System.getProperties();
            if (props.getProperty("os.name").equals("Windows")) {
                commandFormat = "\"%s/bin/mysql.exe\" -u%s -p%s   %s ";
            }
            if (props.getProperty("os.name").equals("Mac OS X")) {
                commandFormat = "%s/bin/mysql -u%s -p%s %s ";
            }
            String command = String.format(commandFormat, mysqlPath, DBUtil.loginName, DBUtil.password,
                    DBUtil.database);

            Process p = Runtime.getRuntime().exec(command);
            OutputStream out = p.getOutputStream();
            String inStr;
            StringBuilder sb = new StringBuilder("");
            String outStr;
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(recoverFile), StandardCharsets.UTF_8));
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr).append("\r\n");
            }
            outStr = sb.toString();

            OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
            writer.write(outStr);
            writer.flush();
            out.close();
            br.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        String mysqlPath = "D:/tools/MYSQL/mysql-5.1.57-win32";
        String file = "C:/Documents and Settings/Administrator/My Documents/hutubill.sql";

        // backup(mysqlPath, file);
        // restore();
        // recover(mysqlPath, file);
        // recover(file);
        recover(mysqlPath, file);

    }

}
