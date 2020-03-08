package com.learning.springdao;

import com.learning.springdao.dao.JdbcDaoImpl;
import com.learning.springdao.model.Circle;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        JdbcDaoImpl dao = context.getBean("jdbcDaoImpl", JdbcDaoImpl.class);
        Circle circle = dao.getCircle(1);
        System.out.println("---> " + circle.getName());
        System.out.println("---> Count :: " + dao.getCircleCount());

        context.close();
    }
}
