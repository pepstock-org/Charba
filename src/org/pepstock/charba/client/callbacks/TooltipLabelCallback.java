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

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.items.TooltipItem;
import org.pepstock.charba.client.items.TooltipLabelColor;

/**
 * The tooltip label configuration is nested below the tooltip configuration using the callbacks key.<br>
 * The tooltip has the following callbacks for providing text.<br>
 * All functions must return either a string or an array of strings. Arrays of strings are treated as multiple lines of text. This interface takes care about labels.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface TooltipLabelCallback {

	/**
	 * Returns text to render before an individual label.<br>
	 * This will be called for each item in the tooltip.<br>
	 * If returns <code>null</code>, it will be ignored.
	 * 
	 * @param chart chart instance
	 * @param item tooltip item
	 * @return label to be applied. 
	 */
	default String onBeforeLabel(IsChart chart, TooltipItem item) {
		return Defaults.get().invokeTooltipsBeforeLabel(chart, item);
	}

	/**
	 * Returns text to render for an individual item in the tooltip.<br>
	 * If returns <code>null</code>, it will be ignored.
	 * 
	 * @param chart chart instance
	 * @param item tooltip item
	 * @return label to be applied.
	 */
	default String onLabel(IsChart chart, TooltipItem item) {
		return Defaults.get().invokeTooltipsLabel(chart, item);
	}

	/**
	 * Returns the colors to render for the tooltip item.<br>
	 * If returns <code>null</code>, it will be ignored.
	 * 
	 * @param chart chart instance
	 * @param item tooltip item
	 * @return label color to be applied.
	 */
	default TooltipLabelColor onLabelColor(IsChart chart, TooltipItem item) {
		return Defaults.get().invokeTooltipsLabelColor(chart, item);
	}

	/**
	 * Returns the colors for the text of the label for the tooltip item.<br>
	 * If returns <code>null</code>, it will be ignored.
	 * 
	 * @param chart chart instance
	 * @param item tooltip item
	 * @return label text color to be applied.
	 */
	default IsColor onLabelTextColor(IsChart chart, TooltipItem item) {
		return Defaults.get().invokeTooltipsLabelTextColor(chart, item);
	}

	/**
	 * Returns text to render after an individual label.<br>
	 * If returns <code>null</code>, it will be ignored.
	 * 
	 * @param chart chart instance
	 * @param item tooltip item
	 * @return label to be applied. 
	 */
	default String onAfterLabel(IsChart chart, TooltipItem item) {
		return Defaults.get().invokeTooltipsAfterLabel(chart, item);
	}

}