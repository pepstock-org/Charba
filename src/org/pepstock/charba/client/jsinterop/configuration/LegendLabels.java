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
package org.pepstock.charba.client.jsinterop.configuration;

import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.jsinterop.AbstractChart;
import org.pepstock.charba.client.jsinterop.Chart;
import org.pepstock.charba.client.jsinterop.callbacks.LegendFilterCallback;
import org.pepstock.charba.client.jsinterop.callbacks.LegendLabelsCallback;
import org.pepstock.charba.client.jsinterop.commons.ArrayObject;
import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.commons.JsHelper;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.items.LegendItem;
import org.pepstock.charba.client.jsinterop.items.LegendLabelItem;
import org.pepstock.charba.client.jsinterop.options.ExtendedOptions;

import jsinterop.annotations.JsFunction;

/**
 * This is the labels configuration of the legend.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LegendLabels extends ConfigurationContainer<ExtendedOptions> {
	
	/**
	 * Called to generate legend items for each thing in the legend. Default implementation returns the text + styling for the color box.
	 * 
	 * @return array of legend items.
	 */
	@JsFunction
	interface ProxyGenerateLabelsCallback {
		ArrayObject call(Object context, Chart chart);
	}

	/**
	 * Called to filter legend items out of the legend. Receives 1 parameter, a Legend Item.
	 * 
	 * @param item legend item to check.
	 * @return <code>true</code> to maintain the item, otherwise <code>false</code> to hide it.
	 */
	@JsFunction
	interface ProxyFilterCallback {
		boolean call(Object context, NativeObject item);
	}
	
	private final CallbackProxy<ProxyGenerateLabelsCallback> labelsCallbackProxy = JsHelper.get().newCallbackProxy();

	private final CallbackProxy<ProxyFilterCallback> filterCallbackProxy = JsHelper.get().newCallbackProxy();
	
	private LegendFilterCallback filterCallback = null;
	
	private LegendLabelsCallback labelsCallback = null;
	
	/**
	 * Name of fields of JavaScript object.
	 */
	enum Property implements Key
	{
		// CALLBACKS
		generateLabels,
		filter
	}

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	LegendLabels(AbstractChart<?, ?> chart, ExtendedOptions options) {
		super(chart, options);
		filterCallbackProxy.setCallback(new ProxyFilterCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.LegendLabels.ProxyFilterCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.items.LegendItem)
			 */
			@Override
			public boolean call(Object context, NativeObject item) {
				// checks if callback is consistent
				if (filterCallback != null) {
					// calls callback
					return filterCallback.onFilter(getChart(), new LegendItem(item));
				}
				return true;
			}
			
		});
		
		labelsCallbackProxy.setCallback(new ProxyGenerateLabelsCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.LegendLabels.ProxyGenerateLabelsCallback#call(java.lang.Object, java.lang.Object)
			 */
			@Override
			public ArrayObject call(Object context, Chart chart) {
				// checks if callback is consistent
				if (labelsCallback != null) {
					// calls callback
					LegendLabelItem[] result = labelsCallback.generateLegendLabels(getChart());
					return ArrayObject.of(result);
				}
				return ArrayObject.of();
			}
			
		});
	}
	
	/**
	 * Sets the font size for label.
	 * 
	 * @param fontSize Font size for label.
	 */
	public void setFontSize(int fontSize) {
		getConfiguration().getLegend().getLabels().setFontSize(fontSize);
	}

	/**
	 * Returns the font size for label.
	 * 
	 * @return Font size for label. For default see {@link org.pepstock.charba.client.GlobalOptions#getLegend()}.
	 */
	public int getFontSize() {
		return getConfiguration().getLegend().getLabels().getFontSize();
	}

	/**
	 * Sets the font style for the label, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style for the label, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public void setFontStyle(FontStyle fontStyle) {
		getConfiguration().getLegend().getLabels().setFontStyle(fontStyle);
	}

	/**
	 * Returns the font style for the label, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style for the label, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit). For default see {@link org.pepstock.charba.client.GlobalOptions#getLegend()}.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getFontStyle() {
		return getConfiguration().getLegend().getLabels().getFontStyle();
	}

	/**
	 * Sets the font color for label
	 * 
	 * @param fontColor Font color for label
	 */
	public void setFontColor(IsColor fontColor) {
		getConfiguration().getLegend().getLabels().setFontColor(fontColor);
	}

	/**
	 * Sets the font color for label
	 * 
	 * @param fontColor Font color for label
	 */
	public void setFontColor(String fontColor) {
		getConfiguration().getLegend().getLabels().setFontColor(fontColor);
	}

	/**
	 * Returns the font color for label
	 * 
	 * @return Font color for label. For default see {@link org.pepstock.charba.client.GlobalOptions#getLegend()}.
	 */
	public String getFontColorAsString() {
		return getConfiguration().getLegend().getLabels().getFontColorAsString();
	}

	/**
	 * Returns the font color for label
	 * 
	 * @return Font color for label. For default see {@link org.pepstock.charba.client.GlobalOptions#getLegend()}.
	 */
	public IsColor getFontColor() {
		return getConfiguration().getLegend().getLabels().getFontColor();
	}

	/**
	 * Sets the font family for the label, follows CSS font-family options.
	 * 
	 * @param fontFamily Font family for the label, follows CSS font-family options.
	 */
	public void setFontFamily(String fontFamily) {
		getConfiguration().getLegend().getLabels().setFontFamily(fontFamily);
	}

	/**
	 * Returns the font family for the label, follows CSS font-family options.
	 * 
	 * @return Font family for the label, follows CSS font-family options. For default see {@link org.pepstock.charba.client.GlobalOptions#getLegend()}.
	 */
	public String getFontFamily() {
		return getConfiguration().getLegend().getLabels().getFontFamily();
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
	 * @return if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case). For default see {@link org.pepstock.charba.client.GlobalOptions#getLegend()}.
	 */
	public boolean isUsePointStyle() {
		return getConfiguration().getLegend().getLabels().isUsePointStyle();
	}

	/**
	 * Sets the width of coloured box.
	 * 
	 * @param boxWidth width of coloured box.
	 */
	public void setBoxWidth(int boxWidth) {
		getConfiguration().getLegend().getLabels().setBoxWidth(boxWidth);
	}

	/**
	 * Returns the width of coloured box.
	 * 
	 * @return width of coloured box. For default see {@link org.pepstock.charba.client.GlobalOptions#getLegend()}.
	 */
	public int getBoxWidth() {
		return getConfiguration().getLegend().getLabels().getBoxWidth();
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
	 * @return Padding to apply around labels. Only top and bottom are implemented. For default see {@link org.pepstock.charba.client.GlobalOptions#getLegend()}.
	 */
	public int getPadding() {
		return getConfiguration().getLegend().getLabels().getPadding();
	}

	// --------------
	// callbacks
	// ---------------

	/**
	 * @return the filterCallback
	 */
	public LegendFilterCallback getFilterCallback() {
		return filterCallback;
	}

	/**
	 * @param filterCallback the filterCallback to set
	 */
	public void setFilterCallback(LegendFilterCallback filterCallback) {
		this.filterCallback = filterCallback;
		if (filterCallback != null) {
			getConfiguration().setCallback(getConfiguration().getLegend().getLabels(), Property.filter, filterCallbackProxy.getProxy());
		} else {
			getConfiguration().setCallback(getConfiguration().getLegend().getLabels(), Property.filter, null);
		}
	}

	/**
	 * @return the labelsCallback
	 */
	public LegendLabelsCallback getLabelsCallback() {
		return labelsCallback;
	}

	/**
	 * @param labelsCallback the labelsCallback to set
	 */
	public void setLabelsCallback(LegendLabelsCallback labelsCallback) {
		this.labelsCallback = labelsCallback;
		if (labelsCallback != null) {
			getConfiguration().setCallback(getConfiguration().getLegend().getLabels(), Property.generateLabels, labelsCallbackProxy.getProxy());
		} else {
			getConfiguration().setCallback(getConfiguration().getLegend().getLabels(), Property.generateLabels, null);
		}
	}
}