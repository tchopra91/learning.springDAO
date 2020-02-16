package com.learning.springdao;

import com.learning.springdao.dao.JdbcDaoImpl;
import com.learning.springdao.model.Circle;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        Circle circle = new JdbcDaoImpl().getCircle(1);
        System.out.println(circle.getName());
    }

}
