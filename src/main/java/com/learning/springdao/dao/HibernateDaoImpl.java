package com.learning.springdao.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateDaoImpl {

    @Autowired
    private SessionFactory sessionFactory;

    public int getCircleCount() {
        String hql = "select count(*) from Circle";
        Query<Long> query = this.getSessionFactory().openSession().createQuery(hql);
        return query.uniqueResult().intValue();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}