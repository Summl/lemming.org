package com.lemming.lemming.dao;

import com.lemming.lemming.DataBaseConnect;
import com.lemming.lemming.bean.Exam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExamDao {
    public static List<Exam> getExamData(){
        List<Exam> res = new ArrayList<>();
        Exam exam = new Exam();
        Connection conn = DataBaseConnect.getConnection();
//        PreparedStatement preparedStatement = null;

        if(conn == null){
            return null;
        }

        String sql="select * from exam_info where active = 1";
        try {
            Statement st = conn.createStatement();
//            preparedStatement.setInt(1,num);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                exam.parseFromResultSet(rs);
                res.add(exam);
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
}
