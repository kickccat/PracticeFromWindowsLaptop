package io.sample.springdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConstructorArgsApp {
    
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-cp.xml");
    
        Organization organization = (Organization) context.getBean("myorg");
        
        organization.corporateSlogan();
    
        System.out.println(organization);
        
        ((ClassPathXmlApplicationContext) context).close();
    }
}
