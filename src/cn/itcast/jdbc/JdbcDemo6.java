package cn.itcast.jdbc;

import cn.itcast.domain.User;
import cn.itcast.util.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 定义一个方法,查询sys_user表的数据将其封装为对象,然后装载集合,返回
 */

public class JdbcDemo6 {

    public static void main(String[] args) {

        List<User> list = new JdbcDemo6().findAll2();
        System.out.println(list);
        System.out.println(list.size());
    }

    /**
     * 查询所有sys_user对象
     *
     * @return
     */
    public List<User> findAll() {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<User> list = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo1", "root", "root");
            //3.定义sql
            String sql = "select * from sys_user";
            //4.获取执行sql对象
            stmt = conn.createStatement();
            //5.执行sql
            rs = stmt.executeQuery(sql);
            //6.遍历结果集,封装对象,装载集合
            User user = null;
            list = new ArrayList<>();
            while (rs.next()) {
                //获取数据
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String sex = rs.getString("sex");
                String createTime = rs.getString("create_time");
                String delFlag = rs.getString("del_flag");

                //创建user对象
                user = new User();
                user.setId(id);
                user.setName(name);
                user.setAge(age);
                user.setSex(sex);
                user.setCreateTime(createTime);
                user.setDelFlag(delFlag);

                //装载集合
                list.add(user);

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

        return list;
    }


    /**
     * 演示JDBC工具类,查询所有sys_user对象
     *
     * @return
     */
    public List<User> findAll2() {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<User> list = null;
        try {
            /*//1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo1", "root", "root");*/
            conn = JDBCUtils.getConnection();
            //3.定义sql
            String sql = "select * from sys_user";
            //4.获取执行sql对象
            stmt = conn.createStatement();
            //5.执行sql
            rs = stmt.executeQuery(sql);
            //6.遍历结果集,封装对象,装载集合
            User user = null;
            list = new ArrayList<>();
            while (rs.next()) {
                //获取数据
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String sex = rs.getString("sex");
                String createTime = rs.getString("create_time");
                String delFlag = rs.getString("del_flag");

                //创建user对象
                user = new User();
                user.setId(id);
                user.setName(name);
                user.setAge(age);
                user.setSex(sex);
                user.setCreateTime(createTime);
                user.setDelFlag(delFlag);

                //装载集合
                list.add(user);

            }
        }  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            /*try {
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
            }*/

            JDBCUtils.close(rs,stmt,conn);
        }
        return list;
    }

}
