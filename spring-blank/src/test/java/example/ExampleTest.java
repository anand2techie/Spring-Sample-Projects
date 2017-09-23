package example;

import junit.framework.JUnit4TestAdapter;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Demonstrates the test configuration for this project.
 */
public class ExampleTest {

   private static final Logger log = Logger.getLogger(ExampleTest.class);

   @Test
   public void helloWorld() {

      log.debug("Hello World!");

   }

   /**
    * Unnecessary when using maven with surefire.
    */
   public static junit.framework.Test suite() { 

      return new JUnit4TestAdapter(ExampleTest.class); 

   }
}
