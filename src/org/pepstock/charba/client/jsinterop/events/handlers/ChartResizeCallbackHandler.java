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
package org.pepstock.charba.client.jsinterop.events.handlers;

import org.pepstock.charba.client.jsinterop.Chart;
import org.pepstock.charba.client.jsinterop.items.SizeItem;
import org.pepstock.charba.client.jsinterop.options.NativeOptions;

/**
 * Event handler for chart resize event managing.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface ChartResizeCallbackHandler {

	/**
	 * Invoked when the chart has been resized.
	 * 
	 * @param event chart resize event
	 */
	void onResize(NativeOptions context, Chart chart, SizeItem size);

}