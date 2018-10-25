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
package org.pepstock.charba.client.jsinterop.callbacks.handlers;

import org.pepstock.charba.client.jsinterop.commons.ArrayDouble;

/**
 * Interface to implement if wants to change the tick marks to include information about the data type.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.options.scales.BaseTick
 */
public interface TickHandler {

	/**
	 * Changes the tick marks to include information about the data type.
	 * 
	 */
	String onCallback(Object context, double value, int index, ArrayDouble values);

}