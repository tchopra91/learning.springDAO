package com.learning.springdao.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.learning.springdao.model.Circle;

public class JdbcDaoImpl {

    public Circle getCircle(int circleId) {

        Circle circle = null;
        Connection conn = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=springDAO", "sa", "Ionuser@123");

            PreparedStatement ps = conn.prepareStatement("select * from CIRCLE where id = ?");
                    
            ps.setInt(1, circleId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                circle = new Circle(circleId, rs.getString("name"));
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return circle;
    }
}