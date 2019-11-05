package cn.itcast.jdbc;

import java.sql.*;

/**
 * sys_user表 添加一条记录 insert 语句
 */
public class JdbcDemo2 {

    public static void main(String[] args) {

        Statement stmt = null;
        Connection conn = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.定义sql
            String sql = "insert into sys_user values(null,'武先生',35,'1','1974-11-10 15:20:33',0)";
            //3.获取Connection对象
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo1", "root", "root");
            //4.获取执行sql的对象 Statement
            stmt = conn.createStatement();
            //5.执行sql,接收返回结果
            int count = stmt.executeUpdate(sql);//影响的行数
            //6.处理结果
            System.out.println(count);
            if (count > 0) {
                System.out.println("添加成功");
            } else {
                System.out.println("添加失败");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                //避免空指针异常
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
