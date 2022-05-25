package com.lemming.lemming.dao;

import com.lemming.lemming.DataBaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserInfoDao {


    public static boolean changePassword(Integer id,String newpassword){
        Connection connection = DataBaseConnect.getConnection();
        PreparedStatement preparedStatement = null;
        if (connection==null){
            return false;
        }
        String sql = "update user set user_password=? where id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,newpassword);
            preparedStatement.setInt(2,id);
            int res = preparedStatement.executeUpdate();
            return res == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

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
    public static boolean revokeUserAccountDao(Integer id){
        Connection connection=DataBaseConnect.getConnection();
        PreparedStatement preparedStatement=null;
        if(connection==null){
            return false;
        }
        String sql="update user set user_condition=0 where id=?";
        try{
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            int res=preparedStatement.executeUpdate();
            return res ==1;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean changeImg(Integer id,String fname) {
        Connection connection = DataBaseConnect.getConnection();
        PreparedStatement preparedStatement = null;
        if (connection==null) {
            return false;
        }
        String sql = "update user set img_filename=? where id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,fname);
            preparedStatement.setInt(2,id);
            int res=preparedStatement.executeUpdate();
            return res == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean setGroupById(int userId, int groupId){
        Connection connection = DataBaseConnect.getConnection();
        PreparedStatement preparedStatement = null;
        if (connection==null) {
            return false;
        }
        String sql = "update user set group_id=? where id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,groupId);
            preparedStatement.setInt(2,userId);
            int res=preparedStatement.executeUpdate();
            return res == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
