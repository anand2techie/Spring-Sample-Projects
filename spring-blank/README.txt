= Building with Ant =

* Description

   Spring/Hibernate project configuration supporting ANT or
   Maven 2 build processes.

*  Quick Start

   - Place Java code into src/main/java

   - Place configuration files, if any, such as XML files in src/main/resources

   - Place test Java (JUNIT4) code into src/test/java

   - Place configuration files, if any, into src/tes/resources

   - Compile: "ant compile"

   - Compile and test: "ant test"

   - Compile, test, and package (jar): "ant package"

   - Cleanup: "ant clean"

*  Objectives

   - Clean build artifacts

   - Compile source

   - Execute (JUNIT v4) tests
      - Test source/resources always maintained separately

   - Package tested source into a jar package

   - Conduct build processes in a manner consistent with maven 2 

* Directories

   - src/main/java
      - Java production source

   - src/main/resources
      - Production resources such as configuration files

   - src/test/java
      - Test Java source
      - Not distributed with production package

   - src/test/resources
      - Test configuration files
      - Not distributed with production package

   - lib
      - Third-party jar libraries used for compiling production source

   
*  Ant targets

   Execute any one or combination of the following targets as command-line
   arguments to an ant command.

   Some targets will automatically execute others based on dependencies.
   For instance "ant test" will also execute the compile target. Therefore,
   executing "ant compile test" will compile twice and then test. So, please
   take note of prerequisite targets.

   - clean

   - compile

   - test
      runs "compile" before "test"

   - package
      runs "compile" and "test" before "package"

