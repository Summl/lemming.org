package com.lemming.lemming.dao;

import com.lemming.lemming.DataBaseConnect;
import com.lemming.lemming.bean.Post;
import com.lemming.lemming.generic.PageNumber;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDao {
    public enum POST_ORDER {
        ORDER_BY_TIME,
        ORDER_BY_LIKE,
        ORDER_BY_READ,
    }

    /**
     * 发布新帖文
     * @param title 帖文标题
     * @param filename 帖文存储的名称
     * @param userId 发帖者的用户ID
     * @return 返回是否发帖成功
     */
    public static boolean addPost(String title,String filename,int userId){
        Connection connection = DataBaseConnect.getConnection();

        PreparedStatement preparedStatement = null;
        if (connection==null){
            return false;
        }

        String sql = "insert into post_info(title, post_filename, user_id, post_time) VALUES (?,?,?,NOW())";
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

    /**
     * 通过帖文ID获取帖文对象
     * @param id 帖文ID
     * @return 返回帖文对象
     */
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

    /**
     * 查找帖文
     * @param page 需要查找的页数（第几页）
     * @param pageSize 每页的最大容纳数
     * @param orderBy 按照哪一列排序
     * @return 返回查找到的结果列表
     */
    public static List<Post> queryPost(int page, int pageSize, POST_ORDER orderBy){
        List<Post> list = new ArrayList<>();
        PageNumber.setPageSize(pageSize);
        page = PageNumber.getValidPage(page,getCount());

        String StrOrderBy = "";
        switch (orderBy){
            case ORDER_BY_TIME:
                StrOrderBy = "id DESC";
                break;
            case ORDER_BY_LIKE:
                StrOrderBy = "like_num DESC";
                break;
            case ORDER_BY_READ:
                StrOrderBy = "read_num DESC";
                break;
        }
        String sql = "select * from post_info order by "+StrOrderBy+" limit "+PageNumber.getTop(page)+","+PageNumber.getEnd(page);
        Connection c = DataBaseConnect.getConnection();
        if (c==null){
            return list;
        }
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                Post post = new Post();
                post.parseFromResultSet(rs);
                list.add(post);
            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    /**
     * 获取帖文数量
     * @return 返回总数
     */
    public static Integer getCount(){
        String sql = "select COUNT(*) from post_info";
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

    public static void addReadNum(int id){
        Connection connection = DataBaseConnect.getConnection();

        PreparedStatement preparedStatement = null;
        if (connection==null){
            return;
        }

        String sql = "update post_info set post_info.read_num=post_info.like_num+1 where id=?;";
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
}
