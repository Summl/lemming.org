package com.lemming.lemming.dao;

import com.lemming.lemming.DataBaseConnect;
import com.lemming.lemming.bean.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    /**
     * 注册一个账户，将昵称设置为Email，并为其设置密码。
     * @param email 用户的电子邮箱
     * @param passwd 用户密码
     * @return 返回是否注册成功
     */
    public static boolean register(String userName, String email, String passwd){
        Connection connection = DataBaseConnect.getConnection();

        PreparedStatement preparedStatement = null;
        if (connection==null){
            return false;
        }

        String sql = "insert into user(user_name,email,user_password,registration_time) VALUES (?,?,?,NOW())";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,passwd);
            int res = preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
            return res > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过用户 ID 获取用户对象。
     * @param id 要获取的用户ID
     * @return 返回查找到的用户对象，若没有找到或查找失败则返回null
     */
    public static User getUserById(int id) {
        User user = new User();
        Connection connection = DataBaseConnect.getConnection();

        PreparedStatement preparedStatement = null;
        if (connection==null){
            return null;
        }

        String sql = "select * from user where id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet res = preparedStatement.executeQuery();
            if (res.next()){
                user.parseFromResultSet(res);
            }else {
                user = null;
            }
            connection.close();
            preparedStatement.close();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过用户邮箱获取用户
     * @param email 用户邮箱
     * @return 返回查找到的用户对象，若没有找到或查找失败则返回null
     */
    public static User getUserByEmail(String email) {
        User user = new User();
        Connection connection = DataBaseConnect.getConnection();

        PreparedStatement preparedStatement = null;
        if (connection==null){
            return null;
        }

        String sql = "select * from user where email=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,email);
            ResultSet res = preparedStatement.executeQuery();
            if (res.next()){
                user.parseFromResultSet(res);
            }else {
                user = null;
            }
            connection.close();
            preparedStatement.close();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过用户名获取用户
     * @param name 用户名
     * @return 返回查找到的用户对象，若没有找到或查找失败则返回null
     */
    public static User getUserByName(String name) {
        User user = new User();
        Connection connection = DataBaseConnect.getConnection();

        PreparedStatement preparedStatement = null;
        if (connection==null){
            return null;
        }

        String sql = "select * from user where user_name=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            ResultSet res = preparedStatement.executeQuery();
            if (res.next()){
                user.parseFromResultSet(res);
            }else {
                user = null;
            }
            connection.close();
            preparedStatement.close();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取排行榜数据
     * @param num 获取的人数
     * @return 返回排行榜数据
     */
    public static List<User> getLeaderboard(int num){
        List<User> res = new ArrayList<>();
        Connection connection = DataBaseConnect.getConnection();

        PreparedStatement preparedStatement = null;
        if (connection==null){
            return null;
        }

        String sql = "select user_id,user.user_name,count(post_info.id) as post_count from post_info,user where user.id=post_info.user_id group by user_id order by count(id) DESC limit 0,"+num+";";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt("user_id");
                res.add(getUserById(id));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return res;
        }
        return res;
    }

}
