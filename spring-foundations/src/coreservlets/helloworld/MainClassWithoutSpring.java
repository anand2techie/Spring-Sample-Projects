package coreservlets.helloworld;

public class MainClassWithoutSpring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//object creation
		HelloWorld helloWorld=new HelloWorldImpl();
		helloWorld.execute();
	}

}
