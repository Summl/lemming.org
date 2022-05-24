package com.lemming.lemming.dao;

import com.lemming.lemming.DataBaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserInfoDao {

    public static boolean updateUserBaseInfo(String name) {
        Connection connection = DataBaseConnect.getConnection();
        PreparedStatement preparedStatement = null;
        if (connection==null) {
            return false;
        }

        String sql = "update User set ";
        return false;
    }
}
