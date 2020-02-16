package com.learning.springdao.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.learning.springdao.model.Circle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JdbcDaoImpl {

    @Autowired
    private DataSource dataSource;

    public Circle getCircle(int circleId) {

        Circle circle = null;
        Connection conn = null;

        try {
            conn = this.dataSource.getConnection();

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

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}