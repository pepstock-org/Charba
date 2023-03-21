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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

/**
 * This utility is minifying CSS files.<br>
 * The source has been changed following the <code>cssmin</code> utility, <a href="https://github.com/soldair/cssmin">https://github.com/soldair/cssminf</a>.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class CssMinifier {

	// log instance
	private static final Logger LOGGER = Logger.getLogger("cssMinifier");
	// Represents the end-of-file (or stream).
	private static final int EOF = -1;
	// static state
	private static final RunningState RUNNING_STATE = new RunningState();
	// defines the carriage return
	private static final int CARRIAGE_RETURN = '\r';
	// defines the line separator
	private static final int LINE_SEPARATOR = '\n';
	// defines the comma
	private static final int BLANK = ' ';
	// defines the asterisk
	private static final int ASTERISK = '*';
	// defines the slash
	private static final int SLASH = '/';
	// defines the semicolon
	private static final int SEMICOLON = ';';
	// defines the OPEN round bracket
	private static final int OPEN_ROUND_BRACKET = '(';
	// defines the CLOSE round bracket
	private static final int CLOSE_ROUND_BRACKET = ')';
	// defines the OPEN brace
	private static final int OPEN_BRACE = '{';
	// defines the CLOSE brace
	private static final int CLOSE_BRACE = '}';
	// defines the at sign
	private static final int AT_SIGN = '@';

	// enumerates the state of the CSS parsing
	private static enum State
	{
		STATE_FREE,
		STATE_ATRULE,
		STATE_SELECTOR,
		STATE_BLOCK,
		STATE_DECLARATION,
		STATE_COMMENT;
	}

	/**
	 * Entry point of the utility.<br>
	 * 2 arguments must be passed:<br>
	 * <ul>
	 * <li>CSS source file, to minify
	 * <li>CSS target file, to store the minified source
	 * </ul>
	 * 
	 * @param args arguments must be 2: source and target CSS files.
	 * @throws IOException if any exception occurs
	 */
	public static void main(String[] args) throws IOException {
		// checks if arguments are consistent
		if (args.length != 2) {
			// the arguments must be 2:
			// 1. source CSS file
			// 2. target CSS file
			throw new IllegalArgumentException("Inavalid arguments. Arguments must be 2: source and target CSS files.");
		}
		// gets the files
		File sourceFile = new File(args[0]);
		// checks if source file exist
		if (!sourceFile.exists()) {
			throw new IllegalArgumentException("Invalid argument. Source CSS file does not exist: " + args[0]);
		}
		File targetFile = new File(args[1]);
		// creates streams
		InputStream sourceStream = new FileInputStream(sourceFile);
		OutputStream targetStream = new FileOutputStream(targetFile);
		// minify!!
		try {
			LOGGER.info("Minify file " + sourceFile.getName() + ", length: " + sourceFile.length());
			minify(sourceStream, targetStream);
			LOGGER.info("Created file " + targetFile.getAbsolutePath() + ", targetFile: " + targetFile.length());
			LOGGER.info("Compression percentage: " + (100 - (targetFile.length() * 100 / sourceFile.length())) + "%");
		} finally {
			// checks if target is consistent
			if (targetStream != null) {
				// if yes, flush and close
				targetStream.flush();
				targetStream.close();
			}
			// checks if source is consistent
			if (sourceStream != null) {
				// if yes, close
				sourceStream.close();
			}
		}
	}

	/**
	 * Minifies the CSS file.<br>
	 * Removes comments removes newlines and line feeds keeping removes last semicolon from last property.
	 * 
	 * @param source CSS source file to minify
	 * @param target CSS target file to store the minified source
	 * @throws IOException if any exception occurs
	 */
	private static void minify(InputStream source, OutputStream target) throws IOException {
		// reads the source stream
		int c;
		// while end of file
		// reads the char
		while ((c = get(source)) != EOF) {
			// manages char by state machine
			c = machine(c, source);
			// if after machine
			// the char is consistent
			// it will store in the target
			if (c != 0) {
				target.write(c);
			}
		}
	}

	/**
	 * Returns the next character from source stream.<br>
	 * Watch out for lookahead. If the character is a control character, translate it to a space or line feed.
	 * 
	 * @param source CSS source file to minify
	 * @return the next character from source stream
	 * @throws IOException if any exception occurs
	 */
	private static int get(InputStream source) throws IOException {
		// saves next char
		int c = RUNNING_STATE.nextCharacter;
		RUNNING_STATE.nextCharacter = EOF;
		// if char is EOF, a read from source is needed
		if (c == EOF) {
			c = source.read();
		}
		// if is a char, returns it
		if (c >= BLANK || c == LINE_SEPARATOR || c == EOF) {
			return c;
		}
		// transforms CR to line separator
		// and returns it
		if (c == CARRIAGE_RETURN) {
			return LINE_SEPARATOR;
		}
		// if here, returns a blank
		return BLANK;
	}

	/**
	 * Returns the next character without getting it.
	 * 
	 * @param source CSS source file to minify
	 * @return the next character without getting it
	 * @throws IOException if any exception occurs
	 */
	private static int peek(InputStream source) throws IOException {
		// gets next char
		// storing in the state
		RUNNING_STATE.nextCharacter = get(source);
		return RUNNING_STATE.nextCharacter;
	}

	/**
	 * State machine implementation, parsing CSS source stream.
	 * 
	 * @param c character to be checked
	 * @param source CSS source file to minify
	 * @return the character to be managed
	 * @throws IOException if any exception occurs
	 */
	private static int machine(int c, InputStream source) throws IOException {
		// checks if there is a comment
		// and not already inside
		if (RUNNING_STATE.state != State.STATE_COMMENT) {
			// checks the start of the comment
			if (c == SLASH && peek(source) == ASTERISK) {
				// stores in the state
				RUNNING_STATE.temporaryState = RUNNING_STATE.state;
				RUNNING_STATE.state = State.STATE_COMMENT;
			}
		}
		// state machine checks
		switch (RUNNING_STATE.state) {
		// ----------------------------------
		// FREE STATE, ready for new state
		// ----------------------------------
		case STATE_FREE:
			if (c == BLANK && c == LINE_SEPARATOR) {
				c = 0;
			} else if (c == AT_SIGN) {
				RUNNING_STATE.state = State.STATE_ATRULE;
				break;
			} else if (c > 0) {
				RUNNING_STATE.state = State.STATE_SELECTOR;
			}
			// ----------------------------------
			// SELECTOR STATE, is reading
			// it comes after FREE
			// ----------------------------------
		case STATE_SELECTOR:
			if (c == OPEN_BRACE) {
				RUNNING_STATE.state = State.STATE_BLOCK;
			} else if (c == LINE_SEPARATOR) {
				c = 0;
			} else if (c == AT_SIGN) {
				RUNNING_STATE.state = State.STATE_ATRULE;
			} else if (c == BLANK && peek(source) == OPEN_BRACE) {
				c = 0;
			}
			break;
		// ----------------------------------
		// RULE STATE, to support CSS rules
		// like "@import", "@font-face{" etc.
		// ----------------------------------
		case STATE_ATRULE:
			if ((c == LINE_SEPARATOR && peek(source) == LINE_SEPARATOR) || c == SEMICOLON) {
				c = SEMICOLON;
				RUNNING_STATE.state = State.STATE_FREE;
			} else if (c == LINE_SEPARATOR) {
				c = BLANK;
				RUNNING_STATE.state = State.STATE_FREE;
			} else if (c == OPEN_BRACE) {
				RUNNING_STATE.state = State.STATE_BLOCK;
			}
			break;
		// --------------------------------------------
		// BLOCK STATE, to manages blocks "{...}"
		// --------------------------------------------
		case STATE_BLOCK:
			if (c == BLANK || c == LINE_SEPARATOR) {
				c = 0;
				break;
			} else if (c == CLOSE_BRACE) {
				RUNNING_STATE.state = State.STATE_FREE;
				break;
			} else {
				RUNNING_STATE.state = State.STATE_DECLARATION;
			}
			// --------------------------------------------
			// DECLARATION STATE, to manages blocks "(...)"
			// --------------------------------------------
		case STATE_DECLARATION:
			// support in brackets
			if (c == OPEN_ROUND_BRACKET) {
				RUNNING_STATE.insideBrackets = true;
			}
			if (!RUNNING_STATE.insideBrackets) {
				if (c == SEMICOLON) {
					RUNNING_STATE.state = State.STATE_BLOCK;
					// could continue peeking through white space..
					if (peek(source) == CLOSE_BRACE) {
						c = 0;
					}
				} else if (c == CLOSE_BRACE) {
					// handle unterminated declaration
					RUNNING_STATE.state = State.STATE_FREE;
				} else if (c == LINE_SEPARATOR) {
					// skip new lines
					c = 0;
				} else if (c == BLANK) {
					// skip multiple spaces after each other
					if (peek(source) == c) {
						c = 0;
					}
				}
			} else if (c == CLOSE_ROUND_BRACKET) {
				RUNNING_STATE.insideBrackets = false;
			}
			break;
		// --------------------------------------------
		// COMMENT STATE, to manages comment "/*.. */"
		// --------------------------------------------
		case STATE_COMMENT:
			if (c == ASTERISK && peek(source) == SLASH) {
				RUNNING_STATE.nextCharacter = EOF;
				RUNNING_STATE.state = RUNNING_STATE.temporaryState;
			}
			c = 0;
			break;
		}
		return c;
	}

	/**
	 * This object is managing the state of the machine.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class RunningState {
		// next char
		private int nextCharacter = EOF;
		// temporary state
		private State temporaryState;
		// current real state
		private State state = State.STATE_FREE;
		// flag if the parser is inside brackets
		private boolean insideBrackets = false;
	}

}