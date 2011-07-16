Wraps the static code analyze tool [Checkstyle](http://checkstyle.sourceforge.net/) into a simple JUnit test. 

![alt text](https://github.com/corgrath/JUnit-Checkstyle-Test-Wrapper/raw/master/doc/wiki/results.png "The JUnit results in Eclipse")

Download
========================================
Download the latest version here:
https://github.com/corgrath/JUnit-Checkstyle-Test-Wrapper/downloads/


How to use it in an Eclipse project
========================================

Download JUnit Checkstyle Test wrapper
----------------------------------------

Download the latest version of JUnit Checkstyle Test Wrapper and add it to the project. 

![alt text](https://github.com/corgrath/JUnit-Checkstyle-Test-Wrapper/raw/master/doc/wiki/install_checkstyle_wrapper.png "Download JUnit-Checkstyle-Test-Wrapper and add it to Eclipse")

Download Checkstyle
----------------------------------------

Download the latest version of Checkstyle and add it to the project.

Download Checkstyle from: https://sourceforge.net/projects/checkstyle/files/ 

![alt text](https://github.com/corgrath/JUnit-Checkstyle-Test-Wrapper/raw/master/doc/wiki/install_checkstyle.png "Download Checkstyle and add it to Eclipse")

Create a JUnit test case
----------------------------------------

Write a JUnit test case using the wrapper.

	import org.junit.Test;
	
	import com.osbcp.junitcheckstyletestwrapper.JUnitCheckstyleTestWrapper;
	
	public class TestCheckstyle {
	
		@Test
		public void testSrc() throws Exception {
	
			JUnitCheckstyleTestWrapper.run(this, "./src/", "checkstyle.xml");
	
		}
	
	}
		
![alt text](https://github.com/corgrath/JUnit-Checkstyle-Test-Wrapper/raw/master/doc/wiki/eclipse_testcheckstyle.png "Create the JUnit test in Eclipse")

Create a Checkstyle rule set file
----------------------------------------

Create a Checkstyle rule set file and add it in the same folder as the test.

Guide: http://checkstyle.sourceforge.net/availablechecks.html

Or download our sample file: https://github.com/corgrath/JUnit-Checkstyle-Test-Wrapper/raw/master/doc/wiki/checkstyle.xml

![alt text](https://github.com/corgrath/JUnit-Checkstyle-Test-Wrapper/raw/master/doc/wiki/eclipse_checkstyle_xml.png "Create the rule files for Checkstyle")

Run and view the results
----------------------------------------

![alt text](https://github.com/corgrath/JUnit-Checkstyle-Test-Wrapper/raw/master/doc/wiki/results.png "The JUnit results in Eclipse")

JavaDoc
========================================

http://dl.dropbox.com/u/8183146/persistent/projects/java_junit_checkstyle_wrapper/javadoc/index.html


License
========================================

JUnit Checkstyle Test Wrapper - Copyright 2011 Christoffer Pettersson, christoffer@christoffer.me

Licensed under the Apache License, Version 2.0