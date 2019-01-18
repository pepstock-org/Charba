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
package org.pepstock.charba.client.jsinterop.callbacks;

import org.pepstock.charba.client.jsinterop.configuration.Axis;

/**
 * Callback function to transform data labels to point labels.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public interface RadialPointLabelCallback {

	/**
	 * Callback function to transform data labels to point labels. The default implementation simply returns the current string.
	 * 
	 * @param axis axis instance where this callback as been defined
	 * @param item label of current label
	 * @return new label to apply to point label
	 */
	String onCallback(Axis axis, String item);

}