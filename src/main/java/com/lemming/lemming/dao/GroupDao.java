package com.lemming.lemming.dao;

import com.lemming.lemming.DataBaseConnect;
import com.lemming.lemming.bean.User;
import com.lemming.lemming.bean.UserGroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupDao {
    public static UserGroup getGroupInfoByUserId(int id){

        User user = UserDao.getUserById(id);

        if (user==null){
            return null;
        }

        UserGroup group = new UserGroup();
        Connection connection = DataBaseConnect.getConnection();

        PreparedStatement preparedStatement = null;
        if (connection==null){
            return null;
        }

        String sql = "select * from user_group where id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,user.getGroup());
            ResultSet res = preparedStatement.executeQuery();
            if (res.next()){
                group.parseFromResultSet(res);
            }else {
                group = null;
            }
            connection.close();
            preparedStatement.close();
            return group;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
