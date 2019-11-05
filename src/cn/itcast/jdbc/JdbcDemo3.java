package cn.itcast.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDemo3 {

    /**
     * sys_user表 修改记录
     */
    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取数据库连接对象 Connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo1", "root", "root");
            //3.定义sql
            String sql = "update sys_user set name='二娘' where name='孙二娘'";
            //4.获取执行sql的对象 Statement
            stmt = conn.createStatement();
            //5.执行sql,接收返回结果
            int count = stmt.executeUpdate(sql);
            //6.处理结果
            System.out.println(count);
            if (count > 0) {
                System.out.println("修改成功");
            } else {
                System.out.println("修改失败");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //7.释放资源
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
