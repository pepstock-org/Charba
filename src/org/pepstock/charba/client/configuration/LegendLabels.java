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
package org.pepstock.charba.client.configuration;

import java.util.List;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.callbacks.CallbackFunctionContext;
import org.pepstock.charba.client.callbacks.LegendFilterCallback;
import org.pepstock.charba.client.callbacks.LegendItemSortCallback;
import org.pepstock.charba.client.callbacks.LegendLabelsCallback;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.items.LegendLabelItem;

import jsinterop.annotations.JsFunction;

/**
 * This is the labels configuration of the legend.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class LegendLabels extends ConfigurationOptionsContainer {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback called to generate legend items for each thing in the legend. Default implementation returns the text + styling for the color box.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyGenerateLabelsCallback {

		/**
		 * Method of function to be called to generate legend items for each thing in the legend. Default implementation returns the text + styling for the color box.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param chart chart instance.
		 * @return array of legend items.
		 */
		ArrayObject call(CallbackFunctionContext context, Chart chart);
	}

	/**
	 * Java script FUNCTION callback called to filter legend items out of the legend. Receives 1 parameter, a Legend Item.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyFilterCallback {

		/**
		 * Method of function to be called to filter legend items out of the legend. Receives 1 parameter, a Legend Item.
		 * 
		 * @param context context value of <code>this</code> to the execution context of function.
		 * @param item legend item to check.
		 * @param chart chart instance.
		 * @return <code>true</code> to maintain the item, otherwise <code>false</code> to hide it.
		 */
		boolean call(CallbackFunctionContext context, NativeObject item, Chart chart);
	}

	/**
	 * Java script FUNCTION callback called to allow sorting of legend items.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyItemSortCallback {

		/**
		 * Method of function to be called to allow sorting of legend items.
		 * 
		 * @param context context context value of <code>this</code> to the execution context of function.
		 * @param item1 the first object to be compared.
		 * @param item2 the second object to be compared.
		 * @param chart chart instance.
		 * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
		 */
		int call(CallbackFunctionContext context, NativeObject item1, NativeObject item2, Chart chart);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the generate labels function
	private final CallbackProxy<ProxyGenerateLabelsCallback> labelsCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the filter function
	private final CallbackProxy<ProxyFilterCallback> filterCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the item sort function
	private final CallbackProxy<ProxyItemSortCallback> itemSortCallbackProxy = JsHelper.get().newCallbackProxy();

	// ---------------------------
	// -- USERS CALLBACKS ---
	// ---------------------------
	// user callbacks implementation for filtering legend labels
	private LegendFilterCallback filterCallback = null;
	// user callbacks implementation for generating labels
	private LegendLabelsCallback labelsCallback = null;
	// user callbacks implementation for item sort legend
	private LegendItemSortCallback itemSortCallback = null;

	// empty result
	private static final LegendLabelItem[] EMPTY_RESULT = new LegendLabelItem[0];
	// font instance
	private final Font font;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		GENERATE_LABELS("generateLabels"),
		SORT("sort"),
		FILTER("filter");

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
	 * Builds the object storing the chart instance and the root options element.
	 * 
	 * @param options root options element.
	 */
	LegendLabels(ConfigurationOptions options) {
		super(options);
		// get embedded elements
		this.font = new Font(() -> getConfiguration().getLegend().getLabels().getFont());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		filterCallbackProxy.setCallback((context, item, nativeChart) -> {
			// checks if callback is consistent
			if (filterCallback != null) {
				// calls callback
				return filterCallback.onFilter(getChart(), LegendLabelItem.FACTORY.create(item));
			}
			return true;
		});
		itemSortCallbackProxy.setCallback((context, item1, item2, nativeChart) -> {
			// checks if callback is consistent
			if (itemSortCallback != null) {
				// calls callback
				return itemSortCallback.onItemSort(getChart(), LegendLabelItem.FACTORY.create(item1), LegendLabelItem.FACTORY.create(item2));
			}
			// default is 0 - equals
			return 0;
		});
		labelsCallbackProxy.setCallback((context, nativeChart) -> {
			// checks if callback is consistent
			if (labelsCallback != null) {
				// calls callback
				// getting default labels
				List<LegendLabelItem> result = labelsCallback.generateLegendLabels(getChart(), Defaults.get().generateLabels(nativeChart));
				// transforms into a native array
				return ArrayObject.fromOrEmpty(result);
			}
			// empty array
			return ArrayObject.fromOrEmpty(EMPTY_RESULT);
		});
	}

	/**
	 * Returns the font element.
	 * 
	 * @return the font
	 */
	public Font getFont() {
		return font;
	}

	/**
	 * Sets the font color.
	 * 
	 * @param color font color.
	 */
	public void setColor(IsColor color) {
		setColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the font color.
	 * 
	 * @param color font color.
	 */
	public void setColor(String color) {
		getConfiguration().getLegend().getLabels().setColor(color);
	}

	/**
	 * Returns the font color as string.
	 * 
	 * @return font color as string
	 */
	public String getColorAsString() {
		return getConfiguration().getLegend().getLabels().getColorAsString();
	}

	/**
	 * Returns the font color.
	 * 
	 * @return font color
	 */
	public IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Sets if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case).
	 * 
	 * @param usePointStyle if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case).
	 */
	public void setUsePointStyle(boolean usePointStyle) {
		getConfiguration().getLegend().getLabels().setUsePointStyle(usePointStyle);
	}

	/**
	 * Returns if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case).
	 * 
	 * @return if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case).
	 */
	public boolean isUsePointStyle() {
		return getConfiguration().getLegend().getLabels().isUsePointStyle();
	}

	/**
	 * Sets the style of the legend, overriding point style from dataset.<br>
	 * Only applies if {@link LegendLabels#setUsePointStyle(boolean)} is set to <code>true</code>.
	 * 
	 * @param pointStyle the style of the legend, overriding point style from dataset.
	 */
	public void setPointStyle(PointStyle pointStyle) {
		getConfiguration().getLegend().getLabels().setPointStyle(pointStyle);
	}

	/**
	 * Sets the style of the legend, overriding point style from dataset, as image.<br>
	 * Only applies if {@link LegendLabels#setUsePointStyle(boolean)} is set to <code>true</code>.
	 * 
	 * @param pointStyle the style of the legend, overriding point style from dataset.
	 */
	public void setPointStyle(Img pointStyle) {
		getConfiguration().getLegend().getLabels().setPointStyle(pointStyle);
	}

	/**
	 * Returns <code>true</code> if the point style is set by an {@link Img}.
	 * 
	 * @return <code>true</code> if the point style is set by an {@link Img}
	 */
	public boolean isPointStyleAsImage() {
		return getConfiguration().getLegend().getLabels().isPointStyleAsImage();
	}

	/**
	 * Returns the style of the legend.
	 * 
	 * @return the style of the legend.
	 */
	public PointStyle getPointStyle() {
		return getConfiguration().getLegend().getLabels().getPointStyle();
	}

	/**
	 * Returns the style of the legend as image.
	 * 
	 * @return the style of the legend as image.
	 */
	public Img getPointStyleAsImage() {
		return getConfiguration().getLegend().getLabels().getPointStyleAsImage();
	}

	/**
	 * Sets the width of colored box.
	 * 
	 * @param boxWidth width of colored box.
	 */
	public void setBoxWidth(int boxWidth) {
		getConfiguration().getLegend().getLabels().setBoxWidth(boxWidth);
	}

	/**
	 * Returns the width of colored box.
	 * 
	 * @return width of colored box.
	 */
	public int getBoxWidth() {
		return getConfiguration().getLegend().getLabels().getBoxWidth();
	}

	/**
	 * Sets the height of colored box.
	 * 
	 * @param boxHeight height of colored box.
	 */
	public void setBoxHeight(int boxHeight) {
		getConfiguration().getLegend().getLabels().setBoxHeight(boxHeight);
	}

	/**
	 * Returns the height of colored box.
	 * 
	 * @return height of colored box.
	 */
	public int getBoxHeight() {
		return getConfiguration().getLegend().getLabels().getBoxHeight();
	}

	/**
	 * Sets the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @param padding Padding to apply around labels. Only top and bottom are implemented.
	 */
	public void setPadding(int padding) {
		getConfiguration().getLegend().getLabels().setPadding(padding);
	}

	/**
	 * Returns the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @return Padding to apply around labels. Only top and bottom are implemented.
	 */
	public int getPadding() {
		return getConfiguration().getLegend().getLabels().getPadding();
	}

	/**
	 * Returns the user filter callback instance.
	 * 
	 * @return the user filter callback instance
	 */
	public LegendFilterCallback getFilterCallback() {
		return filterCallback;
	}

	/**
	 * Sets the user filter callback instance.
	 * 
	 * @param filterCallback the user filter callback instance
	 */
	public void setFilterCallback(LegendFilterCallback filterCallback) {
		// sets the callback
		this.filterCallback = filterCallback;
		// checks if callback is consistent
		if (filterCallback != null) {
			// adds the callback proxy function to java script object
			getConfiguration().setCallback(getConfiguration().getLegend().getLabels(), Property.FILTER, filterCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(getConfiguration().getLegend().getLabels(), Property.FILTER, null);
		}
	}

	/**
	 * Returns the user callback instance to generate labels.
	 * 
	 * @return the user callback instance to generate labels
	 */
	public LegendLabelsCallback getLabelsCallback() {
		return labelsCallback;
	}

	/**
	 * Sets the user callback instance to generate labels.
	 * 
	 * @param labelsCallback the user callback instance to generate labels
	 */
	public void setLabelsCallback(LegendLabelsCallback labelsCallback) {
		// sets the callback
		this.labelsCallback = labelsCallback;
		// checks if callback is consistent
		if (labelsCallback != null) {
			// adds the callback proxy function to java script object
			getConfiguration().setCallback(getConfiguration().getLegend().getLabels(), Property.GENERATE_LABELS, labelsCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(getConfiguration().getLegend().getLabels(), Property.GENERATE_LABELS, null);
		}
	}

	/**
	 * Returns the user item sort callback instance.
	 * 
	 * @return the user item sort callback instance
	 */
	public LegendItemSortCallback getItemSortCallback() {
		return itemSortCallback;
	}

	/**
	 * Sets the user item sort callback instance.
	 * 
	 * @param itemSortCallback the user item sort callback instance
	 */
	public void setItemSortCallback(LegendItemSortCallback itemSortCallback) {
		// sets the callback
		this.itemSortCallback = itemSortCallback;
		// checks if callback is consistent
		if (itemSortCallback != null) {
			// adds the callback proxy function to java script object
			getConfiguration().setCallback(getConfiguration().getLegend().getLabels(), Property.SORT, itemSortCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getConfiguration().setCallback(getConfiguration().getLegend().getLabels(), Property.SORT, null);
		}
	}

}