package com.lemming.lemming.dao;

import com.lemming.lemming.DataBaseConnect;
import com.lemming.lemming.bean.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostDao {
    public static boolean addPost(String title,String filename,int userId){
        Connection connection = DataBaseConnect.getConnection();

        PreparedStatement preparedStatement = null;
        if (connection==null){
            return false;
        }

        String sql = "insert into post_info(title, post, user_id, post_time) VALUES (?,?,?,NOW())";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,title);
            preparedStatement.setString(2,filename);
            preparedStatement.setInt(3,userId);
            int res = preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
            return res > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Post getPostById(int id){
        Post post = new Post();
        Connection connection = DataBaseConnect.getConnection();

        PreparedStatement preparedStatement = null;
        if (connection==null){
            return null;
        }

        String sql = "select * from post_info where id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet res = preparedStatement.executeQuery();
            if (res.next()){
                post.parseFromResultSet(res);
            }else {
                post = null;
            }
            connection.close();
            preparedStatement.close();
            return post;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
