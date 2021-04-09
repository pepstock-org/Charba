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
import org.pepstock.charba.client.zoom.AbstractConfigurationItem;
import org.pepstock.charba.client.zoom.ZoomPlugin;

/**
 * Callback interface of {@link ZoomPlugin#ID} plugin that is called once zooming or panning is rejected.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface RejectedCallback {

	/**
	 * Method called once zooming or panning is rejected.
	 * 
	 * @param chart chart instance
	 * @param configurationItem configuration item of {@link ZoomPlugin} which generated the event
	 */
	void onRejected(IsChart chart, AbstractConfigurationItem<?> configurationItem);

}
