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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Code generator (batch) which reads all java script files needed in Charba and creates the classes which will wrap the java
 * script content of script in order to be able to inject the java script code inside a &lt;script&gt; element.<br>
 * Uses a java class template to create the classes and a properties file with all java script files and their configuration to
 * create classes.<br>
 * The template has got the following variables:<br>
 * <ul>
 * <li>packageName: name of package of class
 * <li>className: name of class
 * <li>resourceName: name of injectable resource
 * <li>javaScriptFile: file name of java script
 * <li>javaScriptContent: content of java script file
 * </ul>
 * <br>
 * The properties have got the following format:<br>
 * <code>javascriptFile=javascriptSourceFolder,packageName,className,resourceName</code>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class InjectableResourceGenerator {

	// Represents the end-of-file (or stream).
	private static final int EOF = -1;
	// charset must be UTF-8
	private static final Charset UTF8 = Charset.forName("UTF-8");
	// project folder where storing sources
	private static final File SRC_FOLDER = new File("./src");
	// java class file extension
	private static final String JAVA_EXTENSION = ".java";
	// file where the properties of this code generator are stored
	private static final File PROPERTIES_FILE = new File("./codegen/org/pepstock/charba/codegen/JavaScriptFiles.properties");
	// file where the java class template is stored
	private static final File TEMPLATE_FILE = new File("./codegen/org/pepstock/charba/codegen/InjectableResource.template");
	// threshold of amount of chars for any string array item
	private static final int CHARS_PER_ARRAY_ITEM = 1000;
	// defines the line separator
	private static final byte LINE_SEPARATOR = '\n';
	// defines the comma
	private static final String COMMA_STRING = ",";
	// defines the dot
	private static final String DOT_STRING = ".";
	// defines the file separator
	private static final String FILE_SEPARATOR_STRING = "/";
	// defines the line separator as string
	private static final String LINE_SEPARATOR_STRING = "\n";
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
	// defines the pattern to apply on replace all on the java class template
	// for package name
	private static final String PACKAGE_NAME_VARIABLE = Pattern.quote("${packageName}");
	// defines the pattern to apply on replace all on the java class template
	// for class name
	private static final String CLASS_NAME_VARIABLE = Pattern.quote("${className}");
	// defines the pattern to apply on replace all on the java class template
	// for resource name of injected resource is creating
	private static final String RESOURCE_NAME_VARIABLE = Pattern.quote("${resourceName}");
	// defines the pattern to apply on replace all on the java class template
	// for java script file name
	private static final String JAVASCRIPT_FILE_VARIABLE = Pattern.quote("${javaScriptFile}");
	// defines the pattern to apply on replace all on the java class template
	// for java script file content
	private static final String JAVASCRIPT_CONTENT_VARIABLE = Pattern.quote("${javaScriptContent}");

	/**
	 * Main program of code generator.<br>
	 * It does not need any argument.
	 * 
	 * @param args no argument are need
	 * @throws IOException occurs if any error occurs reading or writing the files
	 * @throws FileNotFoundException occurs if the template, properties or java script files don't exist
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// reads the template of java classes to create
		StringBuilder templateSource = readTemplate();
		// reads the properties with items that the code gen must manage
		Properties properties = readProperties();
		// checks if properties are consistent
		if (properties.isEmpty()) {
			// if not exception
			throw new IOException("Properties are empty");
		}
		// scans all properties
		for (Entry<Object, Object> entry : properties.entrySet()) {
			// the key is the java script file name
			final String javaScriptFileName = entry.getKey().toString();
			// parses the value of the key
			String[] values = entry.getValue().toString().split(COMMA_STRING);
			// checks if the value has got the right format
			// must have 4 items, comma separated
			if (values.length == 4) {
				// initialized the instances using the split values
				final String javaScriptSourceFolder = values[0];
				final String packageName = values[1];
				final String className = values[2];
				final String resourceName = values[3];
				// start reading the java script file
				File javaScriptFile = new File(javaScriptSourceFolder, javaScriptFileName);
				// gets the content of java script file in bytes
				byte[] buffer = readJavaScriptFile(javaScriptFile);
				// escapes the java script content in order to be able to assign it
				// to a java string
				StringBuilder builder = escapeJavaScriptContent(buffer);
				// gets new string from template
				String templateInstance = templateSource.toString();
				// replaces the package name into template
				String changedTemplate = templateInstance.replaceAll(PACKAGE_NAME_VARIABLE, Matcher.quoteReplacement(packageName));
				// replaces the class name into template
				changedTemplate = changedTemplate.replaceAll(CLASS_NAME_VARIABLE, Matcher.quoteReplacement(className));
				// replaces the resource name into template
				changedTemplate = changedTemplate.replaceAll(RESOURCE_NAME_VARIABLE, Matcher.quoteReplacement(resourceName));
				// replaces the java script file name into template
				changedTemplate = changedTemplate.replaceAll(JAVASCRIPT_FILE_VARIABLE, Matcher.quoteReplacement(javaScriptFileName));
				// replaces the java script content into template
				changedTemplate = changedTemplate.replaceAll(JAVASCRIPT_CONTENT_VARIABLE, Matcher.quoteReplacement(builder.toString()));
				// writes the java class
				writeJavaClass(packageName, className, changedTemplate);
			}
		}
	}

	/**
	 * Reads the java class template, returning the content as a string builder.
	 * 
	 * @return the java class template
	 * @throws FileNotFoundException occurs if the template file not exists
	 */
	private static StringBuilder readTemplate() throws FileNotFoundException {
		// creates a scanner to read the template
		Scanner scanner = null;
		try {
			scanner = new Scanner(TEMPLATE_FILE, UTF8.name());
			// creates a builder to maintain the template
			StringBuilder templateSource = new StringBuilder();
			// reads the content of template file
			while (scanner.hasNextLine()) {
				// adds to builder, adding always a line separator at the end
				templateSource.append(scanner.nextLine()).append(LINE_SEPARATOR_STRING);
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
	 * Reads the properties of this code generator, returning the content as a properties instance.
	 * 
	 * @return the properties of this code generator
	 * @throws IOException occurs if the properties file not exists or any errors occurred reading the file
	 */
	private static Properties readProperties() throws IOException {
		// creates reference file reader for properties
		InputStreamReader propertiesFileReader = null;
		try {
			// creates instance file reader for properties
			propertiesFileReader = new InputStreamReader(new FileInputStream(PROPERTIES_FILE), UTF8);
			// creates properties instance
			Properties properties = new Properties();
			// loads properties
			properties.load(propertiesFileReader);
			// returns properties
			return properties;
		} finally {
			// checks if file reader has been initialized
			if (propertiesFileReader != null) {
				// then closes the file reader
				propertiesFileReader.close();
			}
		}
	}

	/**
	 * Reads the java script file to wrap into the java class.
	 * 
	 * @param javaScriptFile java script file locator
	 * @return an array of bytes which represents the content of java script file
	 * @throws IOException occurs if the java script file not exists or any errors occurred reading the file
	 */
	private static byte[] readJavaScriptFile(File javaScriptFile) throws IOException {
		// creates reference java script file stream
		FileInputStream javaScriptFileInputStream = null;
		try {
			// creates instance java script file stream
			javaScriptFileInputStream = new FileInputStream(javaScriptFile);
			// gets the length of file
			int fileSize = (int) javaScriptFile.length();
			// creates an array of bytes with the file size dimension
			byte[] buffer = new byte[fileSize];
			// reads the java script file
			read(javaScriptFileInputStream, buffer, 0, fileSize);
			// returns the buffer
			return buffer;
		} finally {
			// checks if java script file input stream has been initialized
			if (javaScriptFileInputStream != null) {
				// then closes the java script file input stream
				javaScriptFileInputStream.close();
			}
		}
	}

	/**
	 * Writes the java class file which wraps the content of java script file.
	 * 
	 * @param packageName package name of java class
	 * @param className class name of java class
	 * @param changedTemplate the content of the java class
	 * @throws IOException occurs if any errors writing the file
	 */
	private static final void writeJavaClass(String packageName, String className, String changedTemplate) throws IOException {
		// gets the folder where the java class must be stored
		// SRC plus package name
		File targetJavaPackageFolder = new File(SRC_FOLDER, packageName.replace(DOT_STRING, FILE_SEPARATOR_STRING));
		// gets the whole file where the java class must be stored
		// SRC plus package name plus class name plus java extension
		File targetJavaClassFile = new File(targetJavaPackageFolder, className + JAVA_EXTENSION);
		// creates a print stream reference
		PrintStream targetJavaClassFileOutputStream = null;
		try {
			// creates a print stream reference
			targetJavaClassFileOutputStream = new PrintStream(targetJavaClassFile, UTF8.name());
			// writes the java class
			targetJavaClassFileOutputStream.print(changedTemplate);
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
	 * Reads the content of java script file escaping the character in order to be able to assign the content to a java string
	 * instance.
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
				builder.append(QUOTE_STRING).append(COMMA_STRING).append(LINE_SEPARATOR_STRING).append(TAB_INDENT_STRING).append(QUOTE_STRING);
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
				builder.append(QUOTE_STRING);
			} else if (charCounter > CHARS_PER_ARRAY_ITEM) {
				// if here, the current amount of chars is greater than fixed
				// then creates an array item string
				builder.append(QUOTE_STRING).append(COMMA_STRING).append(LINE_SEPARATOR_STRING).append(TAB_INDENT_STRING).append(QUOTE_STRING);
				// resets the counter
				charCounter = 0;
			}
		}
		// returns the string builder
		return builder;
	}

	/**
	 * Reads physically the java script file returning an array of bytes.
	 * 
	 * @param input input stream referenced to the java script file
	 * @param buffer the buffer of bytes to fill
	 * @param offset the offset for reading, always 0
	 * @param length the amount of bytes to read
	 * @return an array of bytes which are the content of java script file
	 * @throws IOException occurs if any errors occurred reading the stream
	 */
	private static int read(final InputStream input, final byte[] buffer, final int offset, final int length) throws IOException {
		// checks if length is consistent
		if (length < 0) {
			// if not exception
			throw new IllegalArgumentException("Length must not be negative: " + length);
		}
		// sets a remaining bytes
		int remaining = length;
		// scans till remaining is positive
		while (remaining > 0) {
			// calculates the location of current read
			final int location = length - remaining;
			// reads the bytes
			final int count = input.read(buffer, offset + location, remaining);
			// checks if is the end of file
			if (EOF == count) {
				// if yes, exit from cycle
				break;
			}
			// decrements the remaining byte using the amount
			// previously read
			remaining -= count;
		}
		// returns if all bytes are read
		return length - remaining;
	}

}
