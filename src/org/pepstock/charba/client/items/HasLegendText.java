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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.dom.safehtml.SafeHtml;
import org.pepstock.charba.client.impl.plugins.HtmlLegend;
import org.pepstock.charba.client.options.LegendTitle;

/**
 * Interface to map object which contains a text to show inside a legend element.<br>
 * Used by {@link LegendItem} and {@link LegendTitle}.
 *
 * @author Andrea "Stock" Stocchero
 */
public interface HasLegendText {

	/**
	 * Returns a legend text handler instance to use in the default methods of this interface.
	 * 
	 * @return a legend text handler instance
	 */
	LegendTextHandler getLegendTextHandler();

	/**
	 * Returns the text that will be displayed
	 * 
	 * @return the text that will be displayed.
	 */
	default String getText() {
		// checks if legend text handler is consistent
		if (getLegendTextHandler() != null) {
			return getLegendTextHandler().getText();
		}
		// if here, legend text handler not consistent
		// returns undefined
		return Undefined.STRING;
	}

	/**
	 * Sets the text that will be displayed.
	 * 
	 * @param text the text that will be displayed
	 */
	default void setText(String text) {
		// checks if legend text handler is consistent
		if (getLegendTextHandler() != null) {
			getLegendTextHandler().setText(text);
		}
	}

	/**
	 * Sets the label that will be displayed, as HTML.<br>
	 * This field is used ONLY by {@link HtmlLegend} plugin and not by CHART.js.
	 * 
	 * @param text the label that will be displayed, as HTML
	 */
	default void setText(SafeHtml text) {
		// checks if legend text handler is consistent
		if (getLegendTextHandler() != null) {
			getLegendTextHandler().setText(text);
		}
	}

	/**
	 * Returns the label that will be displayed, as HTML.<br>
	 * If is not HTML, returns {@link Undefined#STRING}. This field is used ONLY by {@link HtmlLegend} plugin and not by CHART.js.
	 * 
	 * @return the label that will be displayed, as HTML.
	 */
	default SafeHtml getTextAsHtml() {
		// checks if legend text handler is consistent
		if (getLegendTextHandler() != null) {
			return getLegendTextHandler().getTextAsHtml();
		}
		// if here, legend text handler not consistent
		// returns default
		return null;
	}

	/**
	 * Returns <code>true</code> if the text of legend item is HTML.<br>
	 * This field is used ONLY by {@link HtmlLegend} plugin and not by CHART.js.
	 * 
	 * @return <code>true</code> if the text of legend item is HTML
	 */
	default boolean isHtmlText() {
		// checks if legend text handler is consistent
		if (getLegendTextHandler() != null) {
			return getLegendTextHandler().isHtmlText();
		}
		// if here, legend text handler not consistent
		// returns default
		return LegendTextHandler.DEFAULT_HTML_TEXT;
	}

	/**
	 * Sets <code>true</code> if the text of legend item is HTML.<br>
	 * This field is used ONLY by {@link HtmlLegend} plugin and not by CHART.js.
	 * 
	 * @param htmlText <code>true</code> if the text of legend item is HTML
	 */
	default void setHtmlText(boolean htmlText) {
		// checks if legend text handler is consistent
		if (getLegendTextHandler() != null) {
			getLegendTextHandler().setHtmlText(htmlText);
		}
	}

}