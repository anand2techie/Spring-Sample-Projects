package coreservlets.helloworld;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanFactoryObj {
	
	static BeanFactory beanFactory;
	
	static{
		beanFactory =
			      new ClassPathXmlApplicationContext(
			        "/coreservlets/helloworld/applicationContext.xml");
	}

}
