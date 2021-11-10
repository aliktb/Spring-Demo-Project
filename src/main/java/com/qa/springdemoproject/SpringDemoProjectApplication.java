package com.qa.springdemoproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDemoProjectApplication {

  public static void main(String[] args) {


    SpringApplication.run(SpringDemoProjectApplication.class, args);

    // ApplicationContext context = SpringApplication.run(NewProjectApplication.class, args);
    //
    // Object byName = context.getBean("timeNow");
    // Object byType = context.getBean(LocalTime.class);
    // Object byNameAndType = context.getBean("timeNow", LocalTime.class);
    //
    //
    // System.out.println(context.getBean("timeNow"));
    // try {
    // Thread.sleep(2 * 1000);
    // } catch (InterruptedException e) {
    // Thread.currentThread().interrupt();
    // }
    //
    //
    // System.out.println(byName);
    // System.out.println(byType);
    // System.out.println(byNameAndType);

  }

}
