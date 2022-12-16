/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.impl.plugins;

import org.pepstock.charba.client.callbacks.HtmlLegendItemCallback;
import org.pepstock.charba.client.callbacks.HtmlLegendTitleCallback;

/**
 * Maps the default configuration options of {@link HtmlLegend#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
interface IsHtmlLegendDefaultOptions extends IsDefaultCursorPointerOptions {

	/**
	 * Returns if the legend is shown.
	 * 
	 * @return <code>true</code> if the legend is shown.
	 */
	default boolean isDisplay() {
		return HtmlLegendOptions.DEFAULT_DISPLAY;
	}

	/**
	 * Returns the maximum amount of columns of legend.
	 * 
	 * @return he maximum amount of columns of legend
	 */
	default int getMaximumLegendColumns() {
		return HtmlLegendOptions.DEFAULT_MAXIMUM_LEGEND_COLUMNS;
	}

	/**
	 * Returns the callback which can be implemented to change the text of legend for a specific item, as HTML.
	 * 
	 * @return the callback which can be implemented to change the text of legend for a specific item, as HTML
	 */
	default HtmlLegendItemCallback getLegendTextCallback() {
		return null;
	}

	/**
	 * Returns the callback which can be implemented to change the text of legend' title, as HTML.
	 * 
	 * @return the callback which can be implemented to change the text of legend' title, as HTML
	 */
	default HtmlLegendTitleCallback getLegendTitleCallback() {
		return null;
	}
}