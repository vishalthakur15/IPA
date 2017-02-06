package com.epam.login;
/**
 * package includes class Application.
 */

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * class that represents main class.
 * Application
 * @return this is a class
 */
@EnableJpaRepositories(basePackageClasses = com.epam.login.configs.DatabaseConfigs.class)
//@EnableAutoConfiguration(exclude={com.epam.login.configs.DatabaseConfigs.class})
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	
    /**
     * class that represents implementation of application.
     * main method
     * @return this is a class      
     */
	/*
	 *This is an Application class 
	 */
	
	/**
	 * Main method.
	 * @param args args
	 */
	public static void main(String[] args) {
		/*
		 * This is a main class
		 */

        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        /*
         * ctx is the object of class
         * 
         */

        System.out.println("Let's inspect the beans provided by Spring Boot:");
        /*
         * Following statement printing output on console
         */
        /*
         * It checks the output on console
         */

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        /*
         * This is a function to sort array
         */
        for (String beanName : beanNames) {
        	/*
        	 * This is a for each loop
        	 */
            System.out.println(beanName);
            /*
             * Following statement prints the beanName on console
             */
        } //end of for each loop

    }  //end of main loop
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

} //end of class loop
