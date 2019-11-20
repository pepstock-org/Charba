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

import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.charba.client.callbacks.LegendTextCallback;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.impl.plugins.HtmlLegendOptionsFactory.HtmlLegendBuilderDefaultsOptionsFactory;
import org.pepstock.charba.client.items.UndefinedValues;

import com.google.gwt.dom.client.Style.Cursor;

/**
 * Configuration options of {@link ChartPointer#ID} plugin.<br>
 * It manages the cursor to adopt when the cursor is over the dataset item and when not.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class HtmlLegendOptions extends AbstractCursorPointerOptions {

	/**
	 * Default cursor type when the cursor is over the legend, {@link Cursor#POINTER}.
	 */
	public static final Cursor DEFAULT_CURSOR_POINTER = Cursor.POINTER;
	/**
	 * Default maximum legends columns to show.
	 */
	public static final int DEFAULT_MAXIMUM_LEGEND_COLUMNS = Integer.MAX_VALUE;
	/**
	 * Default display if legend must be showed.
	 */
	public static final boolean DEFAULT_DISPLAY = true;
	// internal count
	private static final AtomicInteger COUNTER = new AtomicInteger(0);
	// defaults global options instance
	private HtmlLegendDefaultsOptions defaultsOptions;
	// defaults global options factory
	private final HtmlLegendBuilderDefaultsOptionsFactory defaultsFactory = new HtmlLegendBuilderDefaultsOptionsFactory();
	// legend text callback instance
	private LegendTextCallback legendTextCallback = null;

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		MAXIMUM_LEGEND_COLUMNS("maxLegendColumns"),
		DISPLAY("display"),
		// internal key to store a unique id
		CHARBA_ID("_charbaId");

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
	 * Builds the object with new java script object setting the default value of plugin.
	 */
	public HtmlLegendOptions() {
		// creates an empty object
		super(HtmlLegend.ID);
		// reads the default default global options
		defaultsOptions = loadGlobalsPluginOptions(defaultsFactory);
		// stores the id based on a counter
		setValue(Property.CHARBA_ID, COUNTER.getAndIncrement());
	}

	/**
	 * Builds the object with a java script object stored into options and the default global ones
	 * 
	 * @param nativeObject native object into options
	 * @param defaultsOptions default options stored into defaults global
	 */
	HtmlLegendOptions(NativeObject nativeObject, HtmlLegendDefaultsOptions defaultsOptions) {
		super(HtmlLegend.ID, nativeObject);
		this.defaultsOptions = defaultsOptions;
	}

	/**
	 * Returns the chart id in order to retrieve the legend text callback for the options.
	 * 
	 * @return identifier of the options or {@link UndefinedValues#INTEGER} if does not exist
	 */
	int getCharbaId() {
		return getValue(Property.CHARBA_ID, UndefinedValues.INTEGER);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.impl.plugins.AbstractCursorPointerOptions#getCursorPointerAsString()
	 */
	@Override
	final String getCursorPointerAsString() {
		return defaultsOptions.getCursorPointerAsString();
	}
	
	/**
	 * Sets if the legend is shown.
	 * 
	 * @param display if the legend is shown.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.DISPLAY, display);
	}

	/**
	 * Returns if the legend is shown.
	 * 
	 * @return <code>true</code> if the legend is shown.
	 */
	public boolean isDisplay() {
		return getValue(Property.DISPLAY, defaultsOptions.isDisplay());
	}

	/**
	 * Returns the callback which can be implemented to change the text of legend for a specific item, as HTML.
	 * 
	 * @return the callback which can be implemented to change the text of legend for a specific item, as HTML
	 */
	public LegendTextCallback getLegendTextCallback() {
		return legendTextCallback != null ? legendTextCallback : defaultsOptions.getLegendTextCallback();
	}

	/**
	 * Sets the callback which can be implemented to change the text of legend for a specific item, as HTML.
	 * 
	 * @param legendTextCallback the callback which can be implemented to change the text of legend for a specific item, as HTML
	 */
	public void setLegendTextCallback(LegendTextCallback legendTextCallback) {
		internalSetLegendTextCallback(legendTextCallback);
		// stores legend callback into fatory as cache
		HtmlLegend.FACTORY.store(getCharbaId(), legendTextCallback);
	}

	/**
	 * Sets the callback which can be implemented to change the text of legend for a specific item, as HTML.
	 * 
	 * @param legendTextCallback the callback which can be implemented to change the text of legend for a specific item, as HTML
	 */
	void internalSetLegendTextCallback(LegendTextCallback legendTextCallback) {
		this.legendTextCallback = legendTextCallback;
	}

	/**
	 * Sets the maximum amount of columns of legend.
	 * 
	 * @param maxColumns the maximum amount of columns of legend
	 */
	public void setMaximumLegendColumns(int maxColumns) {
		// checks if max columns is consistent
		setValue(Property.MAXIMUM_LEGEND_COLUMNS, maxColumns < 1 ? DEFAULT_MAXIMUM_LEGEND_COLUMNS : maxColumns);
	}

	/**
	 * Returns the maximum amount of columns of legend.
	 * 
	 * @return he maximum amount of columns of legend
	 */
	public int getMaximumLegendColumns() {
		return getValue(Property.MAXIMUM_LEGEND_COLUMNS, defaultsOptions.getMaximumLegendColumns());
	}

}
