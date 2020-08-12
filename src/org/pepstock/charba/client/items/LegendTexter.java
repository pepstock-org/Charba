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
import org.pepstock.charba.client.commons.NativeObjectContainer;
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
public final class LegendTexter extends NativeObjectContainer {

	// default of html text
	static final boolean DEFAULT_HTML_TEXT = false;
	// model which contains the legend texter
	private final AbstractNode model;

	/**
	 * Name of properties of native object.
	 */
	protected enum Property implements Key
	{
		TEXT("text"),
		// internal key to store a unique id
		CHARBA_HTML_TEXT("_htmlText");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
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
	 * Creates a legend texter with the envelop of the native object where TEXT property must be managed.
	 * 
	 * @param envelop envelop of the native object where TEXT property must be managed
	 * @param model model which contains the legend texter.
	 */
	public LegendTexter(OptionsEnvelop<NativeObject> envelop, AbstractNode model) {
		this(IsEnvelop.checkAndGetIfValid(envelop).getContent(), model);
		// checks if model is consistent
		if (model == null) {
			throw new IllegalArgumentException("Model argument is null");
		}
	}

	/**
	 * Creates a legend texter with the native object where TEXT property must be managed.
	 * 
	 * @param nativeObject native object where SPANGAPS property must be managed
	 */
	LegendTexter(NativeObject nativeObject) {
		this(nativeObject, null);
	}

	
	/**
	 * Creates a legend texter with the native object where TEXT property must be managed.
	 * 
	 * @param nativeObject native object where SPANGAPS property must be managed
	 * @param model model which contains the legend texter.
	 */
	private LegendTexter(NativeObject nativeObject, AbstractNode model) {
		super(nativeObject);
		// stores model
		this.model = model;
	}

	/**
	 * Sets the label that will be displayed.
	 * 
	 * @param text the label that will be displayed
	 */
	void setText(String text) {
		setValue(Property.TEXT, text);
		// checks and adds to parent if model is consistent
		checkAndAddToParent();
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
	 * @return the label that will be displayed. Default is {@link UndefinedValues#STRING}.
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
	 * @return the label that will be displayed, as HTML. Default is <code>null</code>.
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
		setValue(Property.CHARBA_HTML_TEXT, htmlText);
		// checks and adds to parent if model is consistent
		checkAndAddToParent();
	}

	/**
	 * Called recursively when a property has been set in the item.<br>
	 * This is mandatory because it could happen that the parent item is not present, therefore it must be added.
	 */
	private void checkAndAddToParent() {
		// checks if model is consistent
		if (model != null) {
			// checks if the node is already added to parent
			model.checkAndAddToParent();
		}
	}
}