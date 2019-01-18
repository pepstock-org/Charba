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
package org.pepstock.charba.client.jsinterop.utils;

import jsinterop.annotations.JsType;

/**
 * This object is the wrapper to console java script object.<br>
 * The console object provides access to the browser's debugging console
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
@JsType(isNative = true)
public interface Console {

    /**
     * Outputs a message to the Web Console.
     *
     * @param objects A list of JavaScript objects to output. The string representations of each of these
     *            objects are appended together in the order listed and output.
     */
    void log(Object... objects);
    
}
