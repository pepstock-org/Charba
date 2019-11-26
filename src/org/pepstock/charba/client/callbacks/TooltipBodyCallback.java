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

import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.items.TooltipItem;

/**
 * The tooltip label configuration is nested below the tooltip configuration using the callbacks key.<br>
 * The tooltip has the following callbacks for providing text.<br>
 * All functions must return either a string or an array of strings. Arrays of strings are treated as multiple lines of
 * text.<br>
 * This interface takes care about labels to apply to the body.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface TooltipBodyCallback {

	/**
	 * Returns text to render before the body section.
	 * 
	 * @param chart chart instance
	 * @param items list of all tooltip items
	 * @return a list of labels to apply to the body. If returns <code>null</code> or empty list, it will be ignored.
	 */
	List<String> onBeforeBody(IsChart chart, List<TooltipItem> items);

	/**
	 * Returns text to render after the body section.
	 * 
	 * @param chart chart instance
	 * @param items list of all tooltips items
	 * @return a list of labels to apply to the body. If returns <code>null</code> or empty list, it will be ignored.
	 */
	List<String> onAfterBody(IsChart chart, List<TooltipItem> items);

}