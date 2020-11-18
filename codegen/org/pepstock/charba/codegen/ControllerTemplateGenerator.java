/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.codegen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Code generator (batch) which gets an javascript file as controller template and creates a java class in order to wrap the template.<br>
 * The javascript file can be passed as argument or it will use the default, if missing.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class ControllerTemplateGenerator {

	// log instance
	private static final Logger LOGGER = Logger.getLogger("codegen");
	// charset must be UTF-8
	private static final Charset UTF8 = Charset.forName("UTF-8");
	// project folder where storing sources
	private static final File TARGET_CLASS = new File("./src/org/pepstock/charba/client/controllers/ControllerTemplate.java");
	// file where the java class template is stored, default
	private static final File DEFAULT_JAVASCRIPT_TEMPLATE_FILE = new File("./codegen/org/pepstock/charba/codegen/ExtendedController.template");
	// file where the java class template is stored
	private static final File JAVA_TEMPLATE_FILE = new File("./codegen/org/pepstock/charba/codegen/ExtendedControllerClass.template");
	// threshold of amount of chars for any string array item
	private static final int CHARS_PER_ARRAY_ITEM = 1000;
	// defines the comma
	private static final String COMMENT_PREFIX = "//";
	// defines the line separator
	private static final byte LINE_SEPARATOR = '\n';
	// defines the comma
	private static final String COMMA_STRING = ",";
	// defines the line separator as string
	private static final String LINE_SEPARATOR_STRING = "\n";
	// defines the line separator for code as string
	private static final String LINE_SEPARATOR_STRING_FOR_CODE = "\\n";
	// defines the tab indent as string
	private static final String TAB_INDENT_STRING = "\t\t";
	// defines the quote as string
	private static final String QUOTE_STRING = "\"";
	// defines the backslash UTF char
	private static final byte BACKSLASH = 0x5C;
	// defines the replacement for backslash
	private static final String BACKSLASH_REPLACEMENT = "\\\\";
	// defines the quote UTF char
	private static final byte QUOTE = '"';
	// defines the replacement for quote
	private static final String QUOTE_REPLACEMENT = "\\\"";
	// defines the code when a javascript method is started
	private static final String METHOD_START = "Charba_ControllerType.prototype.";
	// defines the code when a javascript method is ended
	private static final String METHOD_END = "};";
	// defines the pattern to apply on replace all on the java class template
	// for controller type content
	private static final String CONTROLLER_TYPE_VARIABLE = Pattern.quote("_ControllerType");
	// defines the pattern to apply on replace all on the java class template
	// for chart type content
	private static final String CHART_TYPE_VARIABLE = Pattern.quote("_ChartType");
	// defines the pattern to apply on replace all on the java class template
	// for the clone default flag
	private static final String CONTROLLER_CLONE_DEFAULTS_VARIABLE = Pattern.quote("_ControllerCloneDefaults");
	// defines the place holder to apply on replace all on the java class template
	// for controller type content
	private static final String CONTROLLER_TYPE_PLACEHOLDER = Matcher.quoteReplacement("{0}");
	// defines the place holder to apply on replace all on the java class template
	// for chart type content
	private static final String CHART_TYPE_PLACEHOLDER = Matcher.quoteReplacement("{1}");
	// defines the place holder to apply on replace all on the java class template
	// for the clone default flag
	private static final String CONTROLLER_CLONE_DEFAULTS_PLACEHOLDER = Matcher.quoteReplacement("{2}");

	// defines the pattern to apply on replace all on the java class template
	// for java script file content
	private static final String JAVASCRIPT_CONTENT_VARIABLE = Pattern.quote("${javaScriptContent}");

	/**
	 * Main program of code generator.<br>
	 * It does not need any argument.
	 * 
	 * @param args only 1 arguments, which is the java script (minify) of java script controller template
	 * @throws IOException occurs if any error occurs reading or writing the files
	 * @throws FileNotFoundException occurs if the template or java script files don't exist
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		LOGGER.info("Started code generation code for controller template");
		// gets the references for java script template
		final File javaScriptTemplateFile;
		// checks if arguments are correctly passed
		if (args != null && args.length == 1) {
			// gets the argument
			String javaScriptTemplateFileAsString = args[0];
			// creates the file
			javaScriptTemplateFile = new File(javaScriptTemplateFileAsString);
			LOGGER.info("Use controller template: " + javaScriptTemplateFileAsString);
		} else {
			LOGGER.info("No controller template has been passed. Use the default: " + DEFAULT_JAVASCRIPT_TEMPLATE_FILE);
			// sets the default controller template file
			javaScriptTemplateFile = DEFAULT_JAVASCRIPT_TEMPLATE_FILE;
		}
		// reads the template of javascript template
		StringBuilder templateJavaScriptSource = readTemplate(javaScriptTemplateFile);
		String templateJsInstance = templateJavaScriptSource.toString();
		// replaces the chart type into template
		String changedTemplate = templateJsInstance.replaceAll(CHART_TYPE_VARIABLE, CHART_TYPE_PLACEHOLDER);
		// replaces the controller into template
		changedTemplate = changedTemplate.replaceAll(CONTROLLER_TYPE_VARIABLE, CONTROLLER_TYPE_PLACEHOLDER);
		// replaces the controller clone defaults flag into template
		changedTemplate = changedTemplate.replaceAll(CONTROLLER_CLONE_DEFAULTS_VARIABLE, CONTROLLER_CLONE_DEFAULTS_PLACEHOLDER);
		// escapes the java script content in order to be able to assign it
		// to a java string
		StringBuilder builder = escapeJavaScriptContent(changedTemplate.getBytes(UTF8));
		// reads the template of java template
		StringBuilder templateJavaSource = readTemplate(JAVA_TEMPLATE_FILE);
		String templateJavaInstance = templateJavaSource.toString();
		// replaces the java script content into template
		String javaSource = templateJavaInstance.replaceAll(JAVASCRIPT_CONTENT_VARIABLE, Matcher.quoteReplacement(builder.toString()));
		// writes the java class
		writeJavaClass(javaSource);
		LOGGER.info("Code generation for controller template is completed");
	}

	/**
	 * Reads the file template, returning the content as a string builder.
	 * 
	 * @param template template file to read
	 * @return the java class template
	 * @throws FileNotFoundException occurs if the template file not exists
	 */
	private static StringBuilder readTemplate(File template) throws FileNotFoundException {
		boolean flagInMethod = false;
		// creates a scanner to read the template
		Scanner scanner = null;
		try {
			scanner = new Scanner(template, UTF8.name());
			// creates a builder to maintain the template
			StringBuilder templateSource = new StringBuilder();
			// reads the content of template file
			while (scanner.hasNextLine()) {
				// reads the line
				String line = scanner.nextLine().trim();
				// checks if is a method
				if (line.startsWith(METHOD_START) && !flagInMethod) {
					// sets the flag
					flagInMethod = true;
				} else if (line.startsWith(METHOD_END) && flagInMethod) {
					// sets the flag
					flagInMethod = false;
				}
				// checks if comment, if yes, it is removed
				if (!line.startsWith(COMMENT_PREFIX)) {
					// adds to builder
					templateSource.append(line);
					// checks if it has to add the CR
					// only for methods
					if (!flagInMethod) {
						// adds a line separator at the end
						templateSource.append(LINE_SEPARATOR_STRING);
					}
				}
			}
			// returns the template as string builder
			return templateSource;
		} finally {
			// checks if scanner has been initialized
			if (scanner != null) {
				// then closes the scanner
				scanner.close();
			}
		}
	}

	/**
	 * Writes the java class file which wraps the content of java script file.
	 * 
	 * @param content the content of the java class
	 * @throws IOException occurs if any errors writing the file
	 */
	private static final void writeJavaClass(String content) throws IOException {
		// creates a print stream reference
		PrintStream targetJavaClassFileOutputStream = null;
		try {
			// creates a print stream reference
			targetJavaClassFileOutputStream = new PrintStream(TARGET_CLASS, UTF8.name());
			// writes the java class
			targetJavaClassFileOutputStream.print(content);
		} catch (UnsupportedEncodingException e) {
			// charset not supported
			throw new IOException(e);
		} finally {
			// checks if java script file output stream has been initialized
			if (targetJavaClassFileOutputStream != null) {
				// then closes the java script file output stream
				targetJavaClassFileOutputStream.close();
			}
		}
	}

	/**
	 * Reads the content of java script file escaping the character in order to be able to assign the content to a java string instance.
	 * 
	 * @param buffer array of bytes which are the content of java script file
	 * @return a string builder with all escaped content of java script file
	 */
	private static StringBuilder escapeJavaScriptContent(byte[] buffer) {
		// gets a instance with total amount of bytes
		int bufferSize = buffer.length;
		// creates a builder to be returned
		final StringBuilder builder = new StringBuilder();
		// adds quote because the result will be an array of strings
		builder.append(QUOTE_STRING);
		// gets a counter of chars managed
		// in order to split when greater than the fixed value
		int charCounter = 0;
		// scans all buffer
		for (byte b : buffer) {
			if (b == QUOTE) {
				// if here the byte is a quote
				builder.append(QUOTE_REPLACEMENT);
				// increments the chars managed with length
				// of replacement
				charCounter += QUOTE_REPLACEMENT.length();
			} else if (b == BACKSLASH) {
				// if here the byte is a backslash
				builder.append(BACKSLASH_REPLACEMENT);
				// increments the chars managed with length
				// of replacement
				charCounter += BACKSLASH_REPLACEMENT.length();
			} else if (b == LINE_SEPARATOR && bufferSize > 1) {
				// if here, there is a line separator into java script
				// but it's not at the end of the file
				// then creates an array item string
				builder.append(LINE_SEPARATOR_STRING_FOR_CODE).append(QUOTE_STRING).append(COMMA_STRING).append(LINE_SEPARATOR_STRING).append(TAB_INDENT_STRING).append(QUOTE_STRING);
				// resets the counter
				charCounter = 0;
			} else if (b != LINE_SEPARATOR) {
				// if here, is not the end of the file
				builder.append((char) b);
				// increments the chars by 1 because
				// here no replacement
				charCounter++;
			}
			// decrements buffer size
			// because a byte has been managed
			bufferSize--;
			// checks if is at the end of byte scanning
			if (bufferSize == 0) {
				// if yes, adds the quote to close the string
				builder.append(LINE_SEPARATOR_STRING_FOR_CODE).append(QUOTE_STRING);
			} else if (charCounter > CHARS_PER_ARRAY_ITEM) {
				// if here, the current amount of chars is greater than fixed
				// then creates an array item string
				builder.append(LINE_SEPARATOR_STRING_FOR_CODE).append(QUOTE_STRING).append(COMMA_STRING).append(LINE_SEPARATOR_STRING).append(TAB_INDENT_STRING).append(QUOTE_STRING);
				// resets the counter
				charCounter = 0;
			}
		}
		// returns the string builder
		return builder;
	}

}
