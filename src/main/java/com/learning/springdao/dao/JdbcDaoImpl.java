package com.learning.springdao.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import com.learning.springdao.model.Circle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class JdbcDaoImpl {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public Circle getCircle(int circleId) {
        Circle circle = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = this.dataSource.getConnection();

            ps = conn.prepareStatement("select * from CIRCLE where id = ?");

            ps.setInt(1, circleId);

            rs = ps.executeQuery();

            if (rs.next()) {
                circle = new Circle(circleId, rs.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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

    public int getCircleCount() {
        String sql = "select count(*) from CIRCLE";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public String getCircleName(int circleId) {
        String sql = "select name from CIRCLE where id = ?";
        return this.jdbcTemplate.queryForObject(sql, new Object[] { circleId }, String.class);
    }

    public Circle getCircleForId(int circleId) {
        String sql = "select * from CIRCLE where id = ?";
        return this.jdbcTemplate.queryForObject(sql, new Object[] { circleId }, new CircleRowMapper());
    }

    public List<Circle> getAllCircles() {
        String sql = "select * from CIRCLE";
        return this.jdbcTemplate.query(sql, new CircleRowMapper());
    }

    private static final class CircleRowMapper implements RowMapper<Circle> {

        @Override
        public Circle mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Circle(rs.getInt("id"), rs.getString("name"));
        }

    }

    public void saveCircle(Circle circle) {
        String sql = "insert into CIRCLE(id, name) values(?, ?)";
        this.jdbcTemplate.update(sql, new Object[] { circle.getId(), circle.getName() });
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}