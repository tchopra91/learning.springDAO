package com.learning.springdao;

import com.learning.springdao.dao.HibernateDaoImpl;
import com.learning.springdao.dao.JdbcDaoImpl;
import com.learning.springdao.dao.NamedParamJdbcDaoImpl;
import com.learning.springdao.model.Circle;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        JdbcDaoImpl dao = context.getBean("jdbcDaoImpl", JdbcDaoImpl.class);
        NamedParamJdbcDaoImpl namedParamDao = context.getBean("namedParamjdbcDaoImpl", NamedParamJdbcDaoImpl.class);
        HibernateDaoImpl hibernateDao = context.getBean("hibernateDaoImpl", HibernateDaoImpl.class);

        Circle circle = dao.getCircle(1);
        System.out.println("---> " + circle.getName());
        // System.out.println("---> Count :: " + dao.getCircleCount());
        // System.out.println("---> Count :: " + namedParamDao.getCircleCount());
        System.out.println("---> Count :: " + hibernateDao.getCircleCount());

        System.out.println("---> Name of circle with id '1' :: " + dao.getCircleName(1));
        System.out.println("---> JSON of circle witg id '1' :: " + dao.getCircleForId(1).toString());

        // dao.saveCircle(new Circle(2, "Test circle 2"));
        // dao.saveCircle(new Circle(3, "Test circle 3"));
        System.out.println("---> Get all circles :: " + dao.getAllCircles());

        context.close();
    }
}
