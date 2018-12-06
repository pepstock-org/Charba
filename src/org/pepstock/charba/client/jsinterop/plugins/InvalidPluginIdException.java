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
package org.pepstock.charba.client.jsinterop.plugins;

/**
 * Exception created when the plugin ID is not valid.<br>
 * A plugin id <br>
 * <ul>
 * <li>can not start with a dot or an underscore
 * <li>can not contain any non-URL-safe characters
 * <li>cannot contain upper-case letters
 * <li>should be something short, but also reasonably descriptive
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 *
 */
public class InvalidPluginIdException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Builds the exception using the message explaining why the id is not valid.
	 * 
	 * @param message explanation why the id is not valid.
	 */
	public InvalidPluginIdException(String message) {
		super(message);
	}
}
