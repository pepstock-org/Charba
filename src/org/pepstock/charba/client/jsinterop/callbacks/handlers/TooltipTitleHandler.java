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

import java.util.List;

import org.pepstock.charba.client.jsinterop.items.TooltipItem;

/**
 * Interface to be implemented from configuration item to be engaged when a tooltip title callback has been invoked.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public interface TooltipTitleHandler {

	/**
	 * Returns the text to render before the title.
	 * 
	 * @param items list of all tooltip items
	 * @return an array of labels to apply to the title. If returns <code>null</code>, it will be ignored.
	 */
	String[] onBeforeTitle(List<TooltipItem> items);

	/**
	 * Returns text to render as the title of the tooltip.
	 * 
	 * @param items list of all tooltip items
	 * @return an array of labels to apply to the title. If returns <code>null</code>, it will be ignored.
	 */
	String[] onTitle(List<TooltipItem> items);

	/**
	 * Returns text to render after the title.
	 * 
	 * @param items list of all tooltip items
	 * @return an array of labels to apply to the title. If returns <code>null</code>, it will be ignored.
	 */
	String[] onAfterTitle(List<TooltipItem> items);

}