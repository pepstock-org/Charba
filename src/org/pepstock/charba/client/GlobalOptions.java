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
package org.pepstock.charba.client;

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.defaults.global.Options;

/**
 * Contains the GLOBAL options for a specific chart instance.<br>
 * Loaded when a new chart instance is created.<br>
 * It's the result of merging of different global options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class GlobalOptions extends Options {


	/**
	 * Creates the object using the java script object with the defaults provided by merging global options.
	 * 
	 * @param javaScriptObject the java script object with the merged defaults.
	 */
	GlobalOptions(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
	}

}
