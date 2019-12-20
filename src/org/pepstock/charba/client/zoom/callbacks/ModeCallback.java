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
package org.pepstock.charba.client.zoom.callbacks;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.enums.InteractionAxis;
import org.pepstock.charba.client.zoom.ZoomPlugin;

/**
 * Callback interface of {@link ZoomPlugin#ID} plugin to set the mode (pan and zoom) directions at runtime.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface ModeCallback {

	/**
	 * Called to set the mode (pan and zoom) directions at runtime.
	 * 
	 * @param chart chart instance
	 * @return the mode (pan and zoom) directions
	 */
	InteractionAxis mode(IsChart chart);

}
