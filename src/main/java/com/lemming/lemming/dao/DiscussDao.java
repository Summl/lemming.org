package com.lemming.lemming.dao;

import com.lemming.lemming.DataBaseConnect;
import com.lemming.lemming.bean.Discuss;
import com.lemming.lemming.bean.Post;
import com.lemming.lemming.generic.PageNumber;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiscussDao {
    public static boolean addDiscuss(int postId, int userId, String discuss){
        Connection connection = DataBaseConnect.getConnection();

        PreparedStatement preparedStatement = null;
        if (connection==null){
            return false;
        }

        String sql = "insert into discuss_info(post_id,user_id,discuss,reg_time) VALUES (?,?,?,NOW())";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,postId);
            preparedStatement.setInt(2,userId);
            preparedStatement.setString(3,discuss);
            int res = preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
            return res > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addLikeNum(int id){
        Connection connection = DataBaseConnect.getConnection();

        PreparedStatement preparedStatement = null;
        if (connection==null){
            return;
        }

        String sql = "update discuss_info set like_num=like_num+1 where id=?;";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Discuss getDiscussById(int id){
        Discuss discuss = new Discuss();
        Connection connection = DataBaseConnect.getConnection();

        PreparedStatement preparedStatement = null;
        if (connection==null){
            return null;
        }

        String sql = "select * from discuss_info where id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet res = preparedStatement.executeQuery();
            if (res.next()){
                discuss.parseFromResultSet(res);
            }else {
                discuss = null;
            }
            connection.close();
            preparedStatement.close();
            return discuss;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Integer getCount(){
        String sql = "select COUNT(*) from discuss_info";
        Connection c = DataBaseConnect.getConnection();
        Integer count = null;
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            count = rs.getInt(1);

            c.close();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public static List<Discuss> queryDiscuss(int postId, int pageNum){
        List<Discuss> list = new ArrayList<>();
        PageNumber.setPageSize(10);
        pageNum = PageNumber.getValidPage(pageNum,getCount());

        String sql = "select * from discuss_info where post_id="+postId+" order by id DESC limit "+PageNumber.getTop(pageNum)+","+PageNumber.getEnd(pageNum);
        Connection c = DataBaseConnect.getConnection();
        if (c==null){
            return list;
        }
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                Discuss discuss = new Discuss();
                discuss.parseFromResultSet(rs);
                list.add(discuss);
            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public static boolean deleteDiscuss(int discussId){
        Connection connection = DataBaseConnect.getConnection();

        PreparedStatement preparedStatement = null;
        if (connection==null){
            return false;
        }

        String sql = "delete from discuss_info where id=?;";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,discussId);
            int res = preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
            return res > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
