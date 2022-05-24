package com.lemming.lemming.dao;

import com.lemming.lemming.DataBaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserInfoDao {

    public static boolean updateUserBaseInfo(String name,String sex,String phone, String email,Integer id) {
        Connection connection = DataBaseConnect.getConnection();
        PreparedStatement preparedStatement = null;
        if (connection==null) {
            return false;
        }
        String sql = "update user set user_name=?,sex=?,phone=?,email=? where id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,sex);
            preparedStatement.setString(3,phone);
            preparedStatement.setString(4,email);
            preparedStatement.setInt(5,id);
            int res=preparedStatement.executeUpdate();
            return res == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
