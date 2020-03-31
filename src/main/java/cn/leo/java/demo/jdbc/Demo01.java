package cn.leo.java.demo.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Demo01 {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager
                .getConnection(
                        "jdbc:mysql://localhost:3306/orders?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&allowMultiQueries=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true",
                        "root", "123456");

        connection.setAutoCommit(false);

        String fileName = "D:\\_智慧\\老系统数据迁移\\lesseeContact_2.sql";
        FileReader fr = new FileReader(fileName);
        BufferedReader bf = new BufferedReader(fr);

        int count = 0;

        String line = null;
        boolean firstLine = true;
        String sql = null;
        String[] split = null;
        PreparedStatement ps = null;

        while ((line = bf.readLine()) != null) {
            if (line == null || "".equals(line.trim())) {
                continue;
            }
            count++;

            line = line.trim();
//            System.out.println(line);
            int valuesIndex = line.indexOf("VALUES");
//            System.out.println(valuesIndex);
            String aPart = line.substring(0, valuesIndex + 6);
//            System.out.println(aPart);
            String bPart = line.substring(valuesIndex + 6, line.length() - 1);
//            System.out.println(bPart);
            bPart = bPart.substring(1, bPart.length() - 1);
//            System.out.println(bPart);
            split = bPart.split(",");

            if (firstLine) {
                StringBuilder sb = new StringBuilder("(");
                for (int i = 0; i < split.length; i++) {
                    sb.append("?,");
                }
                String bPartNew = sb.substring(0, sb.length() - 1) + ")";
//                System.out.println(bPartNew);
                sql = aPart + bPartNew;
//                System.out.println(sql);

                ps = connection.prepareStatement(sql);
                firstLine = false;
            }

            for (int i = 0; i < split.length; i++) {
                String item = split[i];
//                System.out.println(item);
                if (i == 0) {
                    Integer value = Integer.valueOf(item.substring(1, item.length() - 1));
                    ps.setInt(i + 1, value);
//                    System.out.println(value);
                } else {
                    if ("null".equals(item)) {
                        ps.setString(i + 1, null);
//                        System.out.println("null");
                    } else {
                        String value = item.substring(1, item.length() - 1);
                        ps.setString(i + 1, value);
//                        System.out.println(value);
                    }
                }
            }

            ps.addBatch();
            if (count % 20000 == 0) {
                ps.executeBatch();
                System.out.println("batch");
            }

        }


        ps.executeBatch();
        connection.commit();

        ps.close();
        connection.close();

        System.out.println(System.currentTimeMillis() - start);

    }


}
