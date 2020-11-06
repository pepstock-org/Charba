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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.dom.safehtml.SafeHtml;
import org.pepstock.charba.client.impl.plugins.HtmlLegend;
import org.pepstock.charba.client.options.LegendTitle;

/**
 * Interface to map object whoch contains a text to show inside a legend element.<br>
 * Used by {@link LegendItem} and {@link LegendTitle}.
 *
 * @author Andrea "Stock" Stocchero
 */
public interface HasLegendText {

	/**
	 * Returns a legend texter instance to use into default methods of this interface.
	 * 
	 * @return a legend texter instance
	 */
	LegendTexter getLegendTexter();

	/**
	 * Returns the text that will be displayed
	 * 
	 * @return the text that will be displayed.
	 */
	default String getText() {
		// checks if legend texter is consistent
		if (getLegendTexter() != null) {
			return getLegendTexter().getText();
		}
		// if here, legend texter not consistent
		// returns undefined
		return UndefinedValues.STRING;
	}

	/**
	 * Sets the text that will be displayed.
	 * 
	 * @param text the text that will be displayed
	 */
	default void setText(String text) {
		// checks if legend texter is consistent
		if (getLegendTexter() != null) {
			getLegendTexter().setText(text);
		}
	}

	/**
	 * Sets the label that will be displayed, as HTML.<br>
	 * This field is used ONLY by {@link HtmlLegend} plugin and not by CHART.js.
	 * 
	 * @param text the label that will be displayed, as HTML
	 */
	default void setText(SafeHtml text) {
		// checks if legend texter is consistent
		if (getLegendTexter() != null) {
			getLegendTexter().setText(text);
		}
	}

	/**
	 * Returns the label that will be displayed, as HTML.<br>
	 * If is not HTML, returns {@link UndefinedValues#STRING}. This field is used ONLY by {@link HtmlLegend} plugin and not by CHART.js.
	 * 
	 * @return the label that will be displayed, as HTML.
	 */
	default SafeHtml getTextAsHtml() {
		// checks if legend texter is consistent
		if (getLegendTexter() != null) {
			return getLegendTexter().getTextAsHtml();
		}
		// if here, legend texter not consistent
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
		// checks if legend texter is consistent
		if (getLegendTexter() != null) {
			return getLegendTexter().isHtmlText();
		}
		// if here, legend texter not consistent
		// returns default
		return LegendTexter.DEFAULT_HTML_TEXT;
	}

	/**
	 * Sets <code>true</code> if the text of legend item is HTML.<br>
	 * This field is used ONLY by {@link HtmlLegend} plugin and not by CHART.js.
	 * 
	 * @param htmlText <code>true</code> if the text of legend item is HTML
	 */
	default void setHtmlText(boolean htmlText) {
		// checks if legend texter is consistent
		if (getLegendTexter() != null) {
			getLegendTexter().setHtmlText(htmlText);
		}
	}

}