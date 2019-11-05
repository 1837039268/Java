package cn.itcast.jdbc;

import java.sql.*;

public class JdbcDemo5 {

    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取数据库连接对象 Connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo1", "root", "root");
            //3.定义sql
            String sql = "select * from sys_user";
            //4.获取执行sql的对象 Statement
            stmt = conn.createStatement();
            //5.执行sql,接收返回结果
            rs = stmt.executeQuery(sql);
            //6.处理结果
            //6.1 循环判断是否有下一行
            while (rs.next()) {
                //6.2 获取数据
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String sex = rs.getString("sex");
                System.out.println(id + "--" + name + "--" + age + "--" + sex);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //7.释放资源
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
