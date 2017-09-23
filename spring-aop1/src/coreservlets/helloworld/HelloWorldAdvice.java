package coreservlets.helloworld;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import coreservlets.Customer;

public class HelloWorldAdvice implements MethodInterceptor {

  public Object invoke(MethodInvocation invocation) throws Throwable {
    return new Customer(){
      public String toString(){
        return "Hello World!";
      }
    };
  }
}
