/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 */

package com.osbcp.junitcheckstyletestwrapper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.xml.sax.InputSource;

import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.ConfigurationLoader;
import com.puppycrawl.tools.checkstyle.DefaultLogger;
import com.puppycrawl.tools.checkstyle.PropertiesExpander;
import com.puppycrawl.tools.checkstyle.api.AuditListener;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.Configuration;

/**
 * Contains the logic for the JUnit Checkstyle test wrapper
 *
 * @author Christoffer Pettersson, christoffer@christoffer.me
 */

public abstract class JUnitCheckstyleTestWrapper {

	/**
	 * Runs the JUnit Checkstyle test wrapper on a specific folder based on a given rule set file in the same folder
	 * 
	 * @param testClassInstance The JUnit test instance
	 * @param folderToCheck The folder that should be tested
	 * @param ruleFileName Name of the Checkstyle rule set file that should be located in the same folder
	 * @throws FileNotFoundException If any errors occur
	 * @throws CheckstyleException  If any error occur
	 */

	public static void run(final Object testClassInstance, final String folderToCheck, final String ruleFileName) throws FileNotFoundException, CheckstyleException {

		File fileFolderToCheck = new File(folderToCheck);

		System.out.println("Starting Checkstyle on folder '" + fileFolderToCheck.getAbsolutePath() + "'..");

		/*
		 * Validations
		 */

		if (!fileFolderToCheck.exists()) {
			throw new FileNotFoundException("The folder to check '" + fileFolderToCheck.getAbsolutePath() + "' does not exist.");
		}

		if (!fileFolderToCheck.isDirectory()) {
			throw new FileNotFoundException("The folder to check '" + fileFolderToCheck.getAbsolutePath() + "' is not a directory.");
		}

		if (testClassInstance.getClass().getResource(ruleFileName) == null) {
			throw new FileNotFoundException("The rule set file '" + ruleFileName + "' does not exist in the same folder as '" + testClassInstance.getClass().getSimpleName() + "'.");
		}

		/*
		 * Files
		 */

		List<File> files = new ArrayList<File>();
		listFiles(files, fileFolderToCheck, "java");
		System.out.println("Found " + files.size() + " Java source files.");

		if (files.isEmpty()) {
			Assert.fail("Found no Java source files. Configuration error?");
		}

		/*
		 * Listener
		 */

		ByteArrayOutputStream sos = new ByteArrayOutputStream();
		AuditListener listener = new DefaultLogger(sos, false);

		/*
		 * Configuration
		 */

		InputSource inputSource = new InputSource(testClassInstance.getClass().getResourceAsStream(ruleFileName));
		Configuration configuration = ConfigurationLoader.loadConfiguration(inputSource, new PropertiesExpander(System.getProperties()), false);

		/*
		 * Create checker
		 */

		Checker checker = new Checker();
		checker.setModuleClassLoader(Checker.class.getClassLoader());
		checker.configure(configuration);
		checker.addListener(listener);

		/*
		 * Process
		 */

		int errors = checker.process(files);
		System.out.println("Found " + errors + " check style errors.");
		System.out.println(sos.toString());
		Assert.assertTrue(errors + " check style errors found. " + sos.toString(), errors == 0);

		/*
		 * Clean up
		 */

		checker.destroy();

	}

	/**
	 * Lists all files in a given folder
	 */

	private static void listFiles(final List<File> files, final File folder, final String extension) {
		if (folder.canRead()) {
			if (folder.isDirectory()) {
				for (File f : folder.listFiles()) {
					listFiles(files, f, extension);
				}
			} else if (folder.toString().endsWith("." + extension)) {
				files.add(folder);
			}
		}
	}

}