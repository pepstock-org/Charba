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

import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.jsinterop.items.TooltipItem;
import org.pepstock.charba.client.jsinterop.items.TooltipLabelColor;

/**
 * Interface to be implemented from configuration item to be engaged when a tooltip label callback has been invoked.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public interface TooltipLabelHandler {

	/**
	 * Returns text to render before an individual label. This will be called for each item in the tooltip.
	 * 
	 * @param item tooltip item
	 * @return label to be applied. If returns <code>null</code>, it will be ignored.
	 */
	String onBeforeLabel(TooltipItem item);

	/**
	 * Returns text to render for an individual item in the tooltip.
	 * 
	 * @param item tooltip item
	 * @return label to be applied. If returns <code>null</code>, it will be ignored.
	 */
	String onLabel(TooltipItem item);

	/**
	 * Returns the colors to render for the tooltip item.
	 * 
	 * @param item tooltip item
	 * @return label color to be applied. If returns <code>null</code>, it will be ignored.
	 */
	TooltipLabelColor onLabelColor(TooltipItem item);

	/**
	 * Returns the colors for the text of the label for the tooltip item.
	 * 
	 * @param item tooltip item
	 * @return label text color to be applied. If returns <code>null</code>, it will be ignored.
	 */
	IsColor onLabelTextColor(TooltipItem item);

	/**
	 * Returns text to render after an individual label.
	 * 
	 * @param item tooltip item
	 * @return label to be applied. If returns <code>null</code>, it will be ignored.
	 */
	String onAfterLabel(TooltipItem item);

}