/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
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
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pepstock.charba.client.utils.Hasher;

/**
 * Code generator (batch) which reads all java script files needed in Charba and creates the classes which will wrap the java script content of script in order to be able to inject
 * the java script code inside a &lt;script&gt; element.<br>
 * Uses a java class template to create the classes and a properties file with all java script files and their configuration to create classes.<br>
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

	// log instance
	private static final Logger LOGGER = Logger.getLogger("codegen");
	// Represents the end-of-file (or stream).
	private static final int EOF = -1;
	// charset must be UTF-8
	private static final Charset UTF8 = Charset.forName("UTF-8");
	// project folder where storing sources
	private static final File SRC_FOLDER = new File("./src");
	// java class file extension
	private static final String JAVA_EXTENSION = ".java";
	// file where the properties of this code generator are stored
	private static final File PROPERTIES_FILE = new File("./codegen/org/pepstock/charba/codegen/InjectableResourceGenerator.properties");
	// file where the java class template is stored
	private static final File TEMPLATE_FILE = new File("./codegen/org/pepstock/charba/codegen/InjectableResource.template");
	// file where the java class template for reosurce hash is stored
	private static final File HASH_TEMPLATE_FILE = new File("./codegen/org/pepstock/charba/codegen/ResourceHash.template");
	// file where the dependencies are stored
	private static final File DEPENDENCIES_FILE = new File("ivymoduleconfiguration.properties");
	// file where the Apache Software license template is stored
	private static final File ASL_TEMPLATE_FILE = new File("./codegen/org/pepstock/charba/codegen/ApacheSoftwareLicense.template");
	// file where the Apache Software license file is stored
	private static final File ASL_FILE = new File("LICENSE");
	// file where the Apache Software license notice file is stored
	private static final File ASL_NOTICE_FILE = new File("NOTICE");
	// resource hash java class name
	private static final String HASH_CLASS_NAME = "ResourceHash";
	// resource hash java class name
	private static final String HASH_PACKAGE_NAME = "org.pepstock.charba.client.resources";
	// threshold of amount of chars for any string array item
	private static final int CHARS_PER_ARRAY_ITEM = 1000;
	// defines the carriage return
	private static final byte CARRIAGE_RETURN = '\r';
	// defines the line separator
	private static final byte LINE_SEPARATOR = '\n';
	// defines the blank
	private static final String BLANK_STRING = " ";
	// defines the comma
	private static final String COMMA_STRING = ",";
	// defines the dot
	private static final String DOT_STRING = ".";
	// defines the semicolon
	private static final String SEMICOLON_STRING = ";";
	// defines the underscore
	private static final String UNDERSCORE_STRING = "_";
	// Constant for OPEN round bracket
	private static final String OPEN_ROUND_BRACKET = "(";
	// Constant for CLOSE round bracket
	private static final String CLOSE_ROUND_BRACKET = ")";
	// defines the file separator
	private static final String FILE_SEPARATOR_STRING = "/";
	// defines the line separator as string
	private static final String LINE_SEPARATOR_STRING = "\n";
	// defines the tab indent as string
	private static final String TAB_INDENT_STRING = "\t\t";
	// defines the single tab indent as string
	private static final String SINGLE_TAB_INDENT_STRING = "\t";
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
	// defines the pattern to apply on replace all on the java class template
	// for java script file content
	private static final String ADDITIONAL_PACKAGES_VARIABLE = Pattern.quote("${additionalPackages}");
	// defines the pattern to apply on replace all on the java class template
	// for java script file content
	private static final String HASH_ITEMS_VARIABLE = Pattern.quote("${hashItems}");
	// defines the pattern to apply on replace all on the java class template
	// for package name
	private static final String HASH_PACKAGE_NAME_VARIABLE = Pattern.quote("${hashPackageName}");
	// defines the pattern to apply on replace all on the java class template
	// for class name
	private static final String HASH_CLASS_NAME_VARIABLE = Pattern.quote("${hashClassName}");
	// the package where the common classes for resources are stored
	// it will use to check if additional imports must be performed
	private static final String RESOURCES_PACKAGE = "org.pepstock.charba.client.resources";
	// additional import for classes not in resource package
	private static final String ADDITIONAL_PACKAGE = "import org.pepstock.charba.client.resources.AbstractInjectableResource;";
	// prefix of resource name enumeration, needed to know if the resource name enum must be imported
	private static final String RESOURCE_NAME_PREFIX = "ResourceName.";
	// additional import for classes not in resource package and they are also using the resource name enumeration
	private static final String ADDITIONAL_RESOURCE_NAME_PACKAGE = "import org.pepstock.charba.client.resources.ResourceName;";
	// collection of escape result
	private static final Map<String, EscapeResult> ESCAPE_RESULTS = new HashMap<>();
	// template for bundled libraries for Apache Software license
	private static final String ASL_BUNDLED = "   This product bundles ${javaScriptFile} version ${javaScriptFileVersion}, which is available under a \"${javaScriptFileLicense}\" license.\n   For details, see ${javaScriptURLLicense}\n";
	// defines the pattern to apply on replace all on the java class template
	// for java script file version
	private static final String JAVASCRIPT_FILE_VERSION_VARIABLE = Pattern.quote("${javaScriptFileVersion}");
	// defines the pattern to apply on replace all on the java class template
	// for java script file license
	private static final String JAVASCRIPT_FILE_LICENSE_VARIABLE = Pattern.quote("${javaScriptFileLicense}");
	// defines the pattern to apply on replace all on the java class template
	// for java script URL license
	private static final String JAVASCRIPT_URL_LICENSE_VARIABLE = Pattern.quote("${javaScriptURLLicense}");
	// defines the prefix for dependencies property
	private static final String DEPENDENCY_PREFIX = "depver.";
	// template for bundled libraries for Apache Software license
	private static final String ASL_NOTICE_CONTENT = "Charba\nCopyright 2017-" + Calendar.getInstance(Locale.ITALY).get(Calendar.YEAR) + " Andrea Stocchero\n\n";

	// digest algorithm
	private static final String ALGORITHM = "SHA-256";
	// message diget instance
	private static final MessageDigest DIGEST;
	// instantiate the digest with a static code
	static {
		// gets the temporary assignment
		MessageDigest temporaryAssignment = null;
		try {
			// creates message digest
			temporaryAssignment = MessageDigest.getInstance(ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			// unable to create the digest
			LOGGER.severe(e.getMessage());
			// closes process
			throw new RuntimeException(e);
		}
		// stores the digest
		DIGEST = temporaryAssignment;
	}

	/**
	 * Main program of code generator.<br>
	 * It does not need any argument.
	 * 
	 * @param args no argument are need
	 * @throws IOException occurs if any error occurs reading or writing the files
	 * @throws FileNotFoundException occurs if the template, properties or java script files don't exist
	 * @throws NoSuchAlgorithmException
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, NoSuchAlgorithmException {
		// reads the template of java classes to create
		StringBuilder templateSource = readTemplate(TEMPLATE_FILE);
		// reads the template of hash enum java class to create
		StringBuilder templateHashSource = readTemplate(HASH_TEMPLATE_FILE);
		// reads the template of Apache Software License
		StringBuilder templateASLSource = readTemplate(ASL_TEMPLATE_FILE);
		// reads the properties with items that the code gen must manage
		Properties properties = readProperties(PROPERTIES_FILE);
		// checks if properties are consistent
		if (properties.isEmpty()) {
			// if not exception
			throw new IOException("Properties are empty");
		}
		// reads the dependencies
		Properties dependencies = readProperties(DEPENDENCIES_FILE);
		// checks if properties are consistent
		if (dependencies.isEmpty()) {
			// if not exception
			throw new IOException("Dependencies file is empty");
		}
		// scans all properties
		for (Entry<Object, Object> entry : properties.entrySet()) {
			// the key is the java script file name
			final String javaScriptFileName = entry.getKey().toString();
			LOGGER.info("Started code generation code for '" + javaScriptFileName + "'");
			// parses the value of the key
			String[] values = entry.getValue().toString().split(COMMA_STRING);
			// checks if the value has got the right format
			// must have 4 items, comma separated
			if (values.length >= 4) {
				// initialized the instances using the split values
				final String javaScriptSourceFolder = values[0];
				final String packageName = values[1];
				final String className = values[2];
				final String resourceName = values[3];
				// start reading the java script file
				File javaScriptFile = new File(javaScriptSourceFolder, javaScriptFileName);
				// gets the content of java script file in bytes
				byte[] buffer = readFileToBuffer(javaScriptFile);
				// escapes the java script content in order to be able to assign it
				// to a java string
				EscapeResult escapeResult = escapeJavaScriptContent(resourceName, buffer);
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
				changedTemplate = changedTemplate.replaceAll(JAVASCRIPT_CONTENT_VARIABLE, Matcher.quoteReplacement(escapeResult.getBuilder().toString()));
				// creates the import string
				StringBuilder importBuilder = new StringBuilder();
				// checks if the imports must be added
				if (!RESOURCES_PACKAGE.equalsIgnoreCase(packageName)) {
					// adds injectable resource package
					importBuilder.append(LINE_SEPARATOR_STRING).append(ADDITIONAL_PACKAGE);
					// creates a string to import
					// checks also if the resource name is related to enumeration
					// in order to add also that import
					if (resourceName.startsWith(RESOURCE_NAME_PREFIX)) {
						// adds resource name package
						importBuilder.append(LINE_SEPARATOR_STRING).append(ADDITIONAL_RESOURCE_NAME_PACKAGE);
					}
					// adds new line
					importBuilder.append(LINE_SEPARATOR_STRING);
				} else {
					// adds only a blank
					importBuilder.append(BLANK_STRING);
				}
				// replaces the java script content into template
				changedTemplate = changedTemplate.replaceAll(ADDITIONAL_PACKAGES_VARIABLE, Matcher.quoteReplacement(importBuilder.toString()));
				// writes the java class if changed
				if (writeJavaClass(packageName, className, changedTemplate)) {
					LOGGER.info("Code generation for '" + javaScriptFileName + "' is completed");
				} else {
					// if here, no changes
					LOGGER.warning("Code generation for '" + javaScriptFileName + "' is skipped because no changes to apply");
				}
				// stores escape result
				ESCAPE_RESULTS.put(className, escapeResult);
				// ---------------------
				// LICENSE update
				// ---------------------
				// checks if injectable must be added to apache license
				if (values.length == 7) {
					// gets license and url
					String depver = values[4];
					String version = dependencies.getProperty(DEPENDENCY_PREFIX + depver);
					String license = values[5];
					String url = values[6].replaceAll(JAVASCRIPT_FILE_VERSION_VARIABLE, Matcher.quoteReplacement(version));
					String asl = ASL_BUNDLED.replaceAll(JAVASCRIPT_FILE_VARIABLE, Matcher.quoteReplacement(javaScriptFileName));
					asl = asl.replaceAll(JAVASCRIPT_FILE_VERSION_VARIABLE, Matcher.quoteReplacement(version));
					asl = asl.replaceAll(JAVASCRIPT_FILE_LICENSE_VARIABLE, Matcher.quoteReplacement(license));
					asl = asl.replaceAll(JAVASCRIPT_URL_LICENSE_VARIABLE, Matcher.quoteReplacement(url));
					templateASLSource.append(asl).append(LINE_SEPARATOR_STRING).append(LINE_SEPARATOR_STRING);
				}
			} else {
				// if here the source properties are wrong
				LOGGER.warning("Property format for '" + javaScriptFileName + "' is not correct");
			}
		}
		// --------------------------
		// RESOURCE HASH ENUMERATION
		// --------------------------
		// checks if escape result map is consistent
		if (!ESCAPE_RESULTS.isEmpty()) {
			LOGGER.info("Started code generation code for '" + HASH_CLASS_NAME + JAVA_EXTENSION + "'");
			// creates the resource hash enumeration if change
			if (createResourceHash(templateHashSource)) {
				LOGGER.info("Code generation for '" + HASH_CLASS_NAME + JAVA_EXTENSION + "' is completed");
			} else {
				// if here, no changes
				LOGGER.warning("Code generation for '" + HASH_CLASS_NAME + JAVA_EXTENSION + "' is skipped because no changes to apply");
			}
		} else {
			// if here the escape result map is empty
			LOGGER.warning("Escape result map is empty and the " + HASH_CLASS_NAME + " is not created");
		}
		// writes the Apache Software License
		LOGGER.info("Started Apache Software license generation");
		writeLicense(ASL_FILE, templateASLSource.toString());
		writeLicense(ASL_NOTICE_FILE, ASL_NOTICE_CONTENT);
		LOGGER.info("Apache Software license generation is completed");
	}

	/**
	 * Reads the java class template, returning the content as a string builder.
	 * 
	 * @param template file template to read
	 * @return the java class template
	 * @throws FileNotFoundException occurs if the template file not exists
	 */
	private static StringBuilder readTemplate(File template) throws FileNotFoundException {
		// creates a scanner to read the template
		Scanner scanner = null;
		try {
			scanner = new Scanner(template, UTF8.name());
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
	 * @param file file properties to read
	 * @return the properties of this code generator
	 * @throws IOException occurs if the properties file not exists or any errors occurred reading the file
	 */
	private static Properties readProperties(File file) throws IOException {
		// creates reference file reader for properties
		InputStreamReader propertiesFileReader = null;
		try {
			// creates instance file reader for properties
			propertiesFileReader = new InputStreamReader(new FileInputStream(file), UTF8);
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
	 * Reads the file to a buffer of bytes.
	 * 
	 * @param file java script file locator
	 * @return an array of bytes which represents the content of file
	 * @throws IOException occurs if the file not exists or any errors occurred reading the file
	 */
	private static byte[] readFileToBuffer(File file) throws IOException {
		// creates reference java script file stream
		FileInputStream javaScriptFileInputStream = null;
		try {
			// creates instance java script file stream
			javaScriptFileInputStream = new FileInputStream(file);
			// gets the length of file
			int fileSize = (int) file.length();
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
	 * @return <code>true</code> if the file has been changed and replaced
	 * @throws IOException occurs if any errors writing the file
	 */
	private static final boolean writeJavaClass(String packageName, String className, String changedTemplate) throws IOException {
		// gets the folder where the java class must be stored
		// SRC plus package name
		File targetJavaPackageFolder = new File(SRC_FOLDER, packageName.replace(DOT_STRING, FILE_SEPARATOR_STRING));
		// gets the whole file where the java class must be stored
		// SRC plus package name plus class name plus java extension
		File targetJavaClassFile = new File(targetJavaPackageFolder, className + JAVA_EXTENSION);
		// reads existing file
		if (targetJavaClassFile.exists()) {
			// reads java file and encodes it
			byte[] encodedHashOfExistingFile = DIGEST.digest(readFileToBuffer(targetJavaClassFile));
			// reads the byte of template and encodes it
			byte[] encodedhashOfNewFile = DIGEST.digest(changedTemplate.getBytes(StandardCharsets.UTF_8));
			// checks if the bytes are equals
			if (Arrays.equals(encodedHashOfExistingFile, encodedhashOfNewFile)) {
				// files are equals
				// skips it
				return false;
			}
		}
		// creates a print stream reference
		PrintStream targetJavaClassFileOutputStream = null;
		try {
			// creates a print stream reference
			targetJavaClassFileOutputStream = new PrintStream(targetJavaClassFile, UTF8.name());
			// writes the java class
			targetJavaClassFileOutputStream.print(changedTemplate);
			// returns true
			return true;
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
	 * Writes the Apache Software license in the project
	 * 
	 * @param licenseFile the file needed for Apahce Software license to write
	 * @param changedTemplate the content of the Apache Software license
	 * @throws IOException occurs if any errors writing the file
	 */
	private static final void writeLicense(File licenseFile, String changedTemplate) throws IOException {
		// creates a print stream reference
		PrintStream targetLicenseFileOutputStream = null;
		try {
			// creates a print stream reference
			targetLicenseFileOutputStream = new PrintStream(licenseFile, UTF8.name());
			// writes the java class
			targetLicenseFileOutputStream.print(changedTemplate);
		} catch (UnsupportedEncodingException e) {
			// charset not supported
			throw new IOException(e);
		} finally {
			// checks if java script file output stream has been initialized
			if (targetLicenseFileOutputStream != null) {
				// then closes the java script file output stream
				targetLicenseFileOutputStream.close();
			}
		}
	}

	/**
	 * Reads the content of java script file escaping the character in order to be able to assign the content to a java string instance.
	 * 
	 * @param resourceName resource name of java script content
	 * @param buffer array of bytes which are the content of java script file
	 * @return a string builder with all escaped content of java script file
	 */
	private static EscapeResult escapeJavaScriptContent(String resourceName, byte[] buffer) {
		// gets a instance with total amount of bytes
		int bufferSize = buffer.length;
		// creates a builder to be returned
		final StringBuilder builder = new StringBuilder();
		// adds quote because the result will be an array of strings
		builder.append(QUOTE_STRING);
		// gets a counter of chars managed
		// in order to split when greater than the fixed value
		int charCounter = 0;
		// hash instance
		int hash = 0;
		// scans all buffer
		for (byte b : buffer) {
			if (b == QUOTE) {
				// if here the byte is a quote
				builder.append(QUOTE_REPLACEMENT);
				// increments the chars managed with length
				// of replacement
				charCounter += QUOTE_REPLACEMENT.length();
				// calculates hash for byte
				hash += Hasher.hash((char) QUOTE);
			} else if (b == BACKSLASH) {
				// if here the byte is a backslash
				builder.append(BACKSLASH_REPLACEMENT);
				// increments the chars managed with length
				// of replacement
				charCounter += BACKSLASH_REPLACEMENT.length();
				// calculates hash for byte
				hash += Hasher.hash((char) BACKSLASH);
			} else if (b == LINE_SEPARATOR && bufferSize > 1) {
				// if here, there is a line separator into java script
				// but it's not at the end of the file
				// then creates an array item string
				builder.append(QUOTE_STRING).append(COMMA_STRING).append(LINE_SEPARATOR_STRING).append(TAB_INDENT_STRING).append(QUOTE_STRING);
				// resets the counter
				charCounter = 0;
			} else if (b != LINE_SEPARATOR && b != CARRIAGE_RETURN) {
				// if here, is not the end of the file
				builder.append((char) b);
				// increments the chars by 1 because
				// here no replacement
				charCounter++;
				// calculates hash for byte
				hash += Hasher.hash((char) b);
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
		return new EscapeResult(resourceName, builder, hash);
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

	/**
	 * Creates and writes the java class with all hash for each java script resource.
	 * 
	 * @param templateHashSource java class template for the enumeration
	 * @return <code>true</code> if the file has been changed and replaced
	 * @throws IOException occurs if any errors occurred writing the stream
	 */
	private static boolean createResourceHash(StringBuilder templateHashSource) throws IOException {
		// creates items buffer
		final StringBuilder hashEnum = new StringBuilder();
		// sorts the key
		final SortedSet<String> keys = new TreeSet<>(ESCAPE_RESULTS.keySet());
		// scans all escape results
		for (String key : keys) {
			// gets the escape result instance
			EscapeResult result = ESCAPE_RESULTS.get(key);
			// if the buffer is not empty
			// it must close the java statement with comma
			if (hashEnum.length() > 0) {
				hashEnum.append(COMMA_STRING).append(LINE_SEPARATOR_STRING);
			}
			// comment of the item
			hashEnum.append(SINGLE_TAB_INDENT_STRING).append("/**").append(LINE_SEPARATOR_STRING);
			hashEnum.append(SINGLE_TAB_INDENT_STRING).append(" * ").append("Hash item for '" + key + "' class.").append(LINE_SEPARATOR_STRING);
			hashEnum.append(SINGLE_TAB_INDENT_STRING).append(" */").append(LINE_SEPARATOR_STRING);
			// appends the enumeration item definition
			hashEnum.append(SINGLE_TAB_INDENT_STRING).append(capitalize(key)).append(OPEN_ROUND_BRACKET).append(result.getResourceName()).append(COMMA_STRING).append(BLANK_STRING).append(result.getHash()).append(CLOSE_ROUND_BRACKET);
		}
		// closes the items list with a semicolon as requested by java
		hashEnum.append(SEMICOLON_STRING);
		// gets new string from template
		String templateHashInstance = templateHashSource.toString();
		// replaces the package name into template
		String changedTemplate = templateHashInstance.replaceAll(HASH_PACKAGE_NAME_VARIABLE, Matcher.quoteReplacement(HASH_PACKAGE_NAME));
		// replaces the class name into template
		changedTemplate = changedTemplate.replaceAll(HASH_CLASS_NAME_VARIABLE, Matcher.quoteReplacement(HASH_CLASS_NAME));
		// replaces the enum items into template
		changedTemplate = changedTemplate.replaceAll(HASH_ITEMS_VARIABLE, Matcher.quoteReplacement(hashEnum.toString()));
		// writes the java class
		return writeJavaClass(HASH_PACKAGE_NAME, HASH_CLASS_NAME, changedTemplate);
	}

	/**
	 * Normalizes the name of the resource hash enumeration item, putting everything in upper-case.
	 * 
	 * @param value resource hash enumeration item
	 * @return resource hash enumeration item to upper-case
	 */
	private static String capitalize(String value) {
		// creates the buffer
		final StringBuilder builder = new StringBuilder();
		// checks if the argument is consistent
		if (value != null && value.length() > 0) {
			// gets all chars of the string
			char[] chars = value.toCharArray();
			// scans all char
			for (char c : chars) {
				// if the char is already upper-case nad the buffer is not empty
				// because the first chart should be already upper-case
				if (Character.isUpperCase(c) && builder.length() > 0) {
					// adds underscore before the original upper-case
					builder.append(UNDERSCORE_STRING);
				}
				// adds char in upper-case
				builder.append(Character.toUpperCase(c));
			}
		}
		// returns result
		return builder.toString();
	}

	/**
	 * Result of escaping of the resource content.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static final class EscapeResult {

		private final String resourceName;

		private final StringBuilder builder;

		private final int hash;

		/**
		 * Creates the object with the content and its hash.
		 * 
		 * @param resourceName resource name of java script content
		 * @param builder content of the java script resource
		 * @param hash hash of the content
		 */
		EscapeResult(String resourceName, StringBuilder builder, int hash) {
			this.resourceName = resourceName;
			this.builder = builder;
			this.hash = hash;
		}

		/**
		 * Returns the resource name of the java script resource.
		 * 
		 * @return the resource name of the java script resource
		 */
		String getResourceName() {
			return resourceName;
		}

		/**
		 * Returns the content of the java script resource.
		 * 
		 * @return the content of the java script resource
		 */
		StringBuilder getBuilder() {
			return builder;
		}

		/**
		 * Returns the hash of the content.
		 * 
		 * @return the hash of the content
		 */
		int getHash() {
			return hash;
		}

	}

}