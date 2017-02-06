package com.epam.login.configs;
//imports login configuration
/**
 * package includes class DatabaseConfigs.
 */

import java.io.Serializable;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * DatabaseConfigs.
 * @author gsawhney
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfigs implements Serializable {
	
	    /**
	 * Serial Id.
	 */
	private static final long serialVersionUID = -6043992772240429024L;
		/**
	     * This is dbDriver.
	     */
	    @Value("${db.driver}")
	    /*
	     * This is a public class DatabaseConfigs
	     */
	    /*
	     *  value driver
	     */
    private String dbDriver;
	    /**
	     * This is dbDriver.
	     */
    @Value("${db.password}")
    /*
     * value Password
     */
    private  String dbPassword;
    /**
     * This is dbDriver.
     */
    @Value("${db.url}")
    /*
     * value URL
     */
    private  String dbUrl;
    /**
     * This is dbUsername.
     */
    @Value("${db.username}")
    /*
     * value username
     */
    private  String dbUsername;
    /**
     * This is hbdialect.
     */
    @Value("${hibernate.dialect}")
    /*
     *hibernate value dialect
     */
    private  String hibernateDialect;
    /**
     * This is to hibernateShowSql.
     */
    @Value("${hibernate.show_sql}")
    /*
     *hibernate value show_sql
     */
    private  String hibernateShowSql;
    /**
     * This is for entitymanagerPackagesToScan.
     */
    @Value("${entitymanager.packagesToScan}")
    /*
     * value packagesToScan
     */
    private  String entitymanagerPackagesToScan;

    /**
     * dataSource.
     * @return dataSource
     */
    @Bean
    public DataSource dataSource() {
    	/*
    	 * This is a method
    	 */
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dbDriver);
        /*
         * sets database driver
         */
        dataSource.setUrl(dbUrl);
        /*
         * sets database Url
         */
        dataSource.setUsername(dbUsername);
        /*
         * sets database Username
         */
        dataSource.setPassword(dbPassword);
        /*
         * sets database PAssword
         */
        return dataSource;
        //returns datasource
    }

    /**
     * DatabaseConfigs.
     * Default constructor
     */
    public DatabaseConfigs() {
        super();
        //calls main class
    }

    /**
     * sessionFactory.
     * @return sessionFactoryBean
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        /*
         * LocalSessionFactoryBean
         */
        sessionFactoryBean.setDataSource(dataSource());
        //sets Data source
        sessionFactoryBean.setPackagesToScan(entitymanagerPackagesToScan);
        Properties hibernateProperties = new Properties();
        //sets hibernate properties
        hibernateProperties.put("hibernate.dialect", hibernateDialect);
        //putss hibernate dialect
        hibernateProperties.put("hibernate.show_sql", hibernateShowSql);
        //puts hibernate show_sql
        
        sessionFactoryBean.setHibernateProperties(hibernateProperties);
      //sets hibernate properties

        return sessionFactoryBean;
        //returns sessionFactoryBean
    }

    /**
     * HibernateTransactionManager.
     * @return transactionManager
     */
    @Bean
    public HibernateTransactionManager transactionManager() {
    	/*
    	 * transactionManager
    	 */
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
        //return transactionManager
    }

}
