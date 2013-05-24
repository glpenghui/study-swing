package com.todo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher {

	public void launcher(){
		String [] contextPath = new String[]{"app-config.xml"};
		new ClassPathXmlApplicationContext(contextPath);
	}
}
