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
package org.pepstock.charba.client.resources;

/**
 * Exception created when it's able to load CHART.JS source in deferred mode.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ResourceLoadException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates the exception by a description and the cause.
	 * 
	 * @param message description of exception
	 * @param cause cause of the exception
	 */
	ResourceLoadException(String message, Throwable cause) {
		super(message, cause);
	}
}
