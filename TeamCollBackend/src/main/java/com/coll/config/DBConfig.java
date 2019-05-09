package com.coll.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.coll.model.Blog;

@Configuration
@ComponentScan("com.coll")
@EnableTransactionManagement
public class DBConfig 
{
	public DataSource getDataSource()
	{
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dataSource.setUsername("bharath");
		dataSource.setPassword("pass123");
		System.out.println("---DataSource Object Created");
		return dataSource;
	}
	
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory()
	{
		Properties prop=new Properties();
		prop.put("hibenate.hbmddl2.auto", "update");
		prop.put("hibernate.diaelect", "org.hibernate.diaelect.Oracle10gDiaelect");
		
		LocalSessionFactoryBuilder factory=new LocalSessionFactoryBuilder(this.getDataSource());
		factory.addProperties(prop);
		
		factory.addAnnotatedClass(Blog.class);
		
		SessionFactory sessionFactory=factory.buildSessionFactory();
		System.out.println("---SessionFactory Object Created---");
		return sessionFactory;
	}
	
	@Bean(name="txManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
	{
		System.out.println("---Transaction Object Created---");
		return new HibernateTransactionManager(sessionFactory);
	}

}
