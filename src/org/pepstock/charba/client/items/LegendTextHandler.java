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

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.IsEnvelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.PropertyHandler;
import org.pepstock.charba.client.dom.safehtml.SafeHtml;
import org.pepstock.charba.client.dom.safehtml.SafeHtmlBuilder;
import org.pepstock.charba.client.impl.plugins.HtmlLegend;
import org.pepstock.charba.client.options.OptionsEnvelop;

/**
 * Manages the TEXT property of options in order to use the same logic between legend items and title.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LegendTextHandler extends PropertyHandler<Boolean> {

	// default of html text
	static final boolean DEFAULT_HTML_TEXT = false;

	/**
	 * Name of properties of native object.
	 */
	protected enum Property implements Key
	{
		TEXT("text"),
		// internal key to store a unique id
		CHARBA_HTML_TEXT("charbaHtmlText");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}

	}

	/**
	 * Creates a legend text handler with the envelop of the native object where TEXT property must be managed.
	 * 
	 * @param parent model which contains the legend text handler.
	 * @param envelop envelop of the native object where TEXT property must be managed
	 */
	public LegendTextHandler(AbstractNode parent, OptionsEnvelop<NativeObject> envelop) {
		this(parent, IsEnvelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates a legend text handler with the native object where TEXT property must be managed.
	 * 
	 * @param nativeObject native object where SPANGAPS property must be managed
	 */
	LegendTextHandler(NativeObject nativeObject) {
		this(null, nativeObject);
	}

	/**
	 * Creates a legend text handler with the native object where TEXT property must be managed.
	 * 
	 * @param parent model which contains the legend text handler.
	 * @param nativeObject native object where SPANGAPS property must be managed
	 */
	private LegendTextHandler(AbstractNode parent, NativeObject nativeObject) {
		super(parent, DEFAULT_HTML_TEXT, nativeObject);
	}

	/**
	 * Sets the label that will be displayed.
	 * 
	 * @param text the label that will be displayed
	 */
	void setText(String text) {
		setValueAndAddToParent(Property.TEXT, text);
	}

	/**
	 * Sets the label that will be displayed, as HTML.<br>
	 * This field is used ONLY by {@link HtmlLegend} plugin and not by CHART.js.
	 * 
	 * @param text the label that will be displayed, as HTML
	 */
	void setText(SafeHtml text) {
		// checks if argument is consistent
		if (text != null) {
			// not null then stores the HTML
			setText(text.asString());
			// and set the flag
			setHtmlText(true);
		} else {
			// null then resets the property
			remove(Property.TEXT);
			// and set the flag
			setHtmlText(false);
		}
	}

	/**
	 * Returns the label that will be displayed
	 * 
	 * @return the label that will be displayed.
	 */
	String getText() {
		return getValue(Property.TEXT, UndefinedValues.STRING);
	}

	/**
	 * Returns <code>true</code> if the text of legend item is HTML.<br>
	 * This field is used ONLY by {@link HtmlLegend} plugin and not by CHART.js.
	 * 
	 * @return <code>true</code> if the text of legend item is HTML
	 */
	boolean isHtmlText() {
		return getValue(Property.CHARBA_HTML_TEXT, DEFAULT_HTML_TEXT);
	}

	/**
	 * Returns the label that will be displayed, as HTML.<br>
	 * If is not HTML, returns {@link UndefinedValues#STRING}. This field is used ONLY by {@link HtmlLegend} plugin and not by CHART.js.
	 * 
	 * @return the label that will be displayed, as HTML.
	 */
	SafeHtml getTextAsHtml() {
		// checks if the text as HTML
		if (isHtmlText()) {
			// gets text
			String html = getText();
			// if html text is consistent
			if (html != null) {
				// creates safe html builder
				// creates and returns a safe html
				return SafeHtmlBuilder.create().appendHtmlConstant(html).toSafeHtml();
			}
		}
		// if here the text has not been stored as HTML
		// or text is missing
		return null;
	}

	/**
	 * Sets <code>true</code> if the text of legend item is HTML.<br>
	 * This field is used ONLY by {@link HtmlLegend} plugin and not by CHART.js.
	 * 
	 * @param htmlText <code>true</code> if the text of legend item is HTML
	 */
	void setHtmlText(boolean htmlText) {
		setValueAndAddToParent(Property.CHARBA_HTML_TEXT, htmlText);
	}

}
