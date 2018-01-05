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
package org.pepstock.charba.client.callbacks;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.items.TooltipModel;

/**
 * Custom tooltips allow you to hook into the tooltip rendering process so that you can render the tooltip in your own custom
 * way.<br>
 * Generally this is used to create an HTML tooltip instead of an on canvas one.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.options.Tooltips
 */
public interface TooltipCustomCallback {

	/**
	 * Custom tooltips allow you to hook into the tooltip rendering process so that you can render the tooltip in your own
	 * custom way.
	 * 
	 * @param chart chart instance
	 * @param model all info about tooltip to create own HTML tooltip.
	 * @see org.pepstock.charba.client.AbstractChart
	 * @see org.pepstock.charba.client.items.TooltipModel
	 */
	void onCustom(AbstractChart<?, ?> chart, TooltipModel model);

}