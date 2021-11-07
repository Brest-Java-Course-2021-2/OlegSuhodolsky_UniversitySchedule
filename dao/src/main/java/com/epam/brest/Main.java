package com.epam.brest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String [] args){
        ApplicationContext context = new AnnotationConfigApplicationContext(
                "SpringConfig.java");

       //context.getDisplayName();
       System.out.println(context.getClass().getName());
        String [] beans = context.getBeanDefinitionNames();
          for (String b:beans){
              System.out.println("BEAN -> " + b);
          }


    }
}

