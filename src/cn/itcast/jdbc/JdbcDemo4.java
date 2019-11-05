package cn.itcast.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDemo4 {

    /**
     * sys_user表 删除一条记录
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
            String sql = "delete from sys_user where name='阎婆惜'";
            //4.获取执行sql语句的对象 Statement
            stmt = conn.createStatement();
            //5.执行sql,接收返回结果
            int count = stmt.executeUpdate(sql);
            //6.处理结果
            System.out.println(count);
            if (count > 0) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //7.关闭资源
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
