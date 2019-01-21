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
package org.pepstock.charba.client.controllers;

/**
 * Exception created when the controller type is not valid.<br>
 * A controller type <br>
 * <ul>
 * <li>can not start with a dot or an underscore
 * <li>can not contain any non-URL-safe characters
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class InvalidControllerTypeException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Builds the exception using the message explaining why the type is not valid.
	 * 
	 * @param message explanation why the type is not valid.
	 */
	InvalidControllerTypeException(String message) {
		super(message);
	}
}
