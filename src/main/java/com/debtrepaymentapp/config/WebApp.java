package com.debtrepaymentapp.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.debtrepaymentapp.dao.DebtDAO;
import com.debtrepaymentapp.dao.DebtDAOImpl;
import com.debtrepaymentapp.dao.UserDAO;
import com.debtrepaymentapp.dao.UserDAOImpl;
import com.debtrepaymentapp.model.User;

 

public class WebApp extends AbstractAnnotationConfigDispatcherServletInitializer {

   @Override
    protected Class<?>[] getRootConfigClasses() {

        return new Class<?>[0];

    }

    @Override
    protected Class<?>[] getServletConfigClasses() {

        return new Class<?>[]{ WebAppConfig.class };

    }
 
    @Override
    protected String[] getServletMappings() {

        return new String[]{ "/" };

    }

    @Configuration
    @EnableWebMvc
    @ComponentScan("com.debtrepaymentapp.controller")
    public static class WebAppConfig extends WebMvcConfigurerAdapter {

        @Bean
        public ViewResolver getViewResolver(){
            InternalResourceViewResolver resolver = new InternalResourceViewResolver();
            resolver.setPrefix("/WEB-INF/view/");
            resolver.setSuffix(".jsp");
            return resolver;
        }
         
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        }
     
        @Bean
        public DataSource getDataSource() {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/debtappdb");
            dataSource.setUsername("tmnsql");
            dataSource.setPassword("tmnsqlpw");
             
            return dataSource;
        }
         
        @Bean
        public DebtDAO getDebtDAO() {
            return new DebtDAOImpl(getDataSource());
        }
        
        @Bean
        public UserDAO getUserDAO() {
        	return new UserDAOImpl(getDataSource());
        }
        
        @Bean
        public User getUser() {
        	return new User();
        }
        
     }
}

