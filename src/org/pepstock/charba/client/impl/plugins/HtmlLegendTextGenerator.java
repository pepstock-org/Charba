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
package org.pepstock.charba.client.impl.plugins;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.HtmlLegendCallback;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.dom.DOMBuilder;
import org.pepstock.charba.client.dom.elements.Div;
import org.pepstock.charba.client.dom.elements.TableCell;
import org.pepstock.charba.client.dom.enums.Display;
import org.pepstock.charba.client.dom.safehtml.SafeHtml;
import org.pepstock.charba.client.items.HasLegendText;

/**
 * Builds the DIV html element which contains a text. It's used to show the title and all single legend items.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of legend element which contains the text
 * @param <C> type of legend element callback
 */
final class HtmlLegendTextGenerator<T extends HasLegendText, C extends HtmlLegendCallback<T>> {

	/**
	 * Builds a {@link TableCell} which should contains and represents the a text of legend element.
	 * 
	 * @param chart chart instance related to legend to build
	 * @param item legend item or legend title to show in the label cell element
	 * @param callback callback instance which can be implemented to change the text of legend for a specific item or legend's title, as HTML
	 * @return a {@link TableCell} which should contains and represents the text
	 */
	Div createTextElement(IsChart chart, T item, C callback) {
		// result text label cell
		final Div element = DOMBuilder.get().createDivElement();
		// gets text of legend item ot title
		String currentText = item.getText();
		// checks if the text of legend item or title is consistent
		if (currentText != null && currentText.length() > 0) {
			// checks if the text has stored as HTML
			if (item.isHtmlText()) {
				// gets HTML
				SafeHtml html = item.getTextAsHtml();
				// sets HTML in the label cell
				element.setInnerHTML(html.asString());
			} else {
				// invokes the method to manage the text
				// passed as plain text
				managePlainText(chart, item, element, currentText, callback);
			}
		} else {
			// set not visible
			element.getStyle().setDisplay(Display.NONE);
		}
		return element;
	}

	/**
	 * Manages the plain text of legend item or legend title, checking if to invoke a callback or split by a break point.
	 * 
	 * @param chart chart instance related to legend to build
	 * @param item legend item or legend title instance to represent the label
	 * @param element HTML element where the legend text must be stored
	 * @param currentText normalized text to apply
	 * @param callback callback instance which can be implemented to change the text of legend for a specific item or legend title, as HTML
	 */
	private void managePlainText(IsChart chart, T item, Div element, String currentText, C callback) {
		// checks if callback is consistent
		if (callback != null) {
			// if here there is a callback to invoke
			SafeHtml textFromCallback = callback.generateText(chart, item, currentText);
			// checks result
			if (textFromCallback != null) {
				// if here, it sets the result of callback
				// as HTML
				element.setInnerHTML(textFromCallback.asString());
			} else {
				// if here, the text from callback is not consistent
				// and is not breakable
				// then the text is set as text of HTML element
				element.setInnerText(currentText);
			}
		} else {
			// if here, no callbacks then it manages the text
			// of item as plain
			manageText(element, currentText);
		}
	}

	/**
	 * Manages the plain text of legend element (item or title), checking if to split by a break point.
	 * 
	 * @param element HTML element where the legend text must be stored
	 * @param text normalized text to apply
	 */
	private void manageText(Div element, String text) {
		// checks if the text contains a carriage return
		if (text.contains(Constants.LINE_SEPARATOR)) {
			// splits the text
			String[] splitText = text.split(Constants.LINE_SEPARATOR);
			// scans all split text
			for (String singleText : splitText) {
				// if elements has got more than 0 children
				// means that a text node has been already added
				// then BR element will be added
				if (element.hasChildNodes()) {
					element.appendChild(DOMBuilder.get().createLineBreakElement());
				}
				// adds the split text as text element
				element.appendChild(DOMBuilder.get().createTextNode(singleText));
			}
		} else {
			// if here, the text has not any HTML element
			// and is not breakable
			// then the text is set as text of HTML element
			element.setInnerText(text);
		}
	}

}
