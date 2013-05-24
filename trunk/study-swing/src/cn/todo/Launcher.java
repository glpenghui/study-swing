package cn.todo;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import cn.tool.RFIDReader;

public class Launcher {

	public static JdbcTemplate baby_db;
	public static PlatformTransactionManager txManager;  

	public void launcher(){
		String [] contextPath = new String[]{"app-config.xml"};
		ApplicationContext context = new ClassPathXmlApplicationContext(contextPath);
		BeanFactory factory = (BeanFactory) context; 
		baby_db =(JdbcTemplate)factory.getBean("baby_db"); 
		txManager = (PlatformTransactionManager) factory.getBean("transactionManager");
		RFIDReader.init();
	}
	
}
