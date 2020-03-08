package com.learning.springdao.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

public class NamedParamJdbcDaoImpl extends NamedParameterJdbcDaoSupport {

    public int getCircleCount() {
        String sql = "select count(*) from CIRCLE";
        return this.getJdbcTemplate().queryForObject(sql, Integer.class);
    }
}