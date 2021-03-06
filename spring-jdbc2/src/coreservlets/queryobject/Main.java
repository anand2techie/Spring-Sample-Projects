package coreservlets.queryobject;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import coreservlets.Customer;
import coreservlets.CustomerQuery;

public class Main {

  public static void main(String[] args) throws Exception {
    
    BeanFactory beanFactory =
      new ClassPathXmlApplicationContext(new String[]{
        "/coreservlets/queryobject/applicationContext.xml",
        "/coreservlets/util/dataSourceContext.xml"});
    
    CustomerQuery query = (CustomerQuery) beanFactory.getBean("customerQuery");
    
    Customer result = query.getCustomerByName("Java Joe");
    
    System.out.println(result);
    
  }
}
