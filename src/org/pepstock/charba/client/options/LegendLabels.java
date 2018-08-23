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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.callbacks.LegendFilterHandler;
import org.pepstock.charba.client.callbacks.LegendLabelsCallback;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ChartContainer;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.items.LegendItem;
import org.pepstock.charba.client.items.LegendLabelItem;

/**
 * This is the labels configuration of the legend.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LegendLabels extends ChartContainer {

	private LegendLabelsCallback labelsCallBack = null;

	private LegendFilterHandler filterHandler = null;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		fontSize,
		fontStyle,
		fontColor,
		fontFamily,
		boxWidth,
		padding,
		usePointStyle,
		generateLabels,
		filter
	}

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	LegendLabels(AbstractChart<?, ?> chart) {
		super(chart);
	}

	/**
	 * @return the legendCallBack
	 */
	public LegendLabelsCallback getLabelsCallBack() {
		return labelsCallBack;
	}

	/**
	 * @param legendLabelsCallBack the legendCallBack to set
	 */
	public void setLabelsCallBack(LegendLabelsCallback legendLabelsCallBack) {
		// checks if the callback is already set into java script object
		if (hasToBeRegistered(legendLabelsCallBack, Property.generateLabels)) {
			// registers the handler into java script object
			registerNativeLegendLabelsHandler(getJavaScriptObject());
		}
		this.labelsCallBack = legendLabelsCallBack;
	}

	/**
	 * @return the legendFilterHandler
	 */
	public LegendFilterHandler getFilterHandler() {
		return filterHandler;
	}

	/**
	 * @param filterHandler the legendFilterHandler to set
	 */
	public void setLegendFilterHandler(LegendFilterHandler filterHandler) {
		// checks if the callback is already set into java script object
		if (hasToBeRegistered(filterHandler, Property.filter)) {
			// registers the handler into java script object
			registerNativeFilterLabelsHandler(getJavaScriptObject());
		}
		this.filterHandler = filterHandler;
	}
	
	/**
	 * Sets the font size for label.
	 * 
	 * @param fontSize Font size for label.
	 */
	public void setFontSize(int fontSize) {
		setValue(Property.fontSize, fontSize);
	}

	/**
	 * Returns the font size for label.
	 * 
	 * @return Font size for label. For default see {@link org.pepstock.charba.client.GlobalOptions#getLegend()}.
	 */
	public int getFontSize() {
		return getValue(Property.fontSize, getChart().getGlobal().getLegend().getLabels().getFontSize());
	}

	/**
	 * Sets the font style for the label, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style for the label, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public void setFontStyle(FontStyle fontStyle) {
		setValue(Property.fontStyle, fontStyle);
	}

	/**
	 * Returns the font style for the label, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style for the label, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit). For default see {@link org.pepstock.charba.client.GlobalOptions#getLegend()}.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getFontStyle() {
		return getValue(Property.fontStyle, FontStyle.class, getChart().getGlobal().getLegend().getLabels().getFontStyle());
	}

	/**
	 * Sets the font color for label
	 * 
	 * @param fontColor Font color for label
	 */
	public void setFontColor(IsColor fontColor) {
		setFontColor(fontColor.toRGBA());
	}

	/**
	 * Sets the font color for label
	 * 
	 * @param fontColor Font color for label
	 */
	public void setFontColor(String fontColor) {
		setValue(Property.fontColor, fontColor);
	}

	/**
	 * Returns the font color for label
	 * 
	 * @return Font color for label. For default see {@link org.pepstock.charba.client.GlobalOptions#getLegend()}.
	 */
	public String getFontColorAsString() {
		return getValue(Property.fontColor, getChart().getGlobal().getLegend().getLabels().getFontColorAsString());
	}

	/**
	 * Returns the font color for label
	 * 
	 * @return Font color for label. For default see {@link org.pepstock.charba.client.GlobalOptions#getLegend()}.
	 */
	public IsColor getFontColor() {
		return ColorBuilder.parse(getFontColorAsString());
	}

	/**
	 * Sets the font family for the label, follows CSS font-family options.
	 * 
	 * @param fontFamily Font family for the label, follows CSS font-family options.
	 */
	public void setFontFamily(String fontFamily) {
		setValue(Property.fontFamily, fontFamily);
	}

	/**
	 * Returns the font family for the label, follows CSS font-family options.
	 * 
	 * @return Font family for the label, follows CSS font-family options. For default see {@link org.pepstock.charba.client.GlobalOptions#getLegend()}.
	 */
	public String getFontFamily() {
		return getValue(Property.fontFamily, getChart().getGlobal().getLegend().getLabels().getFontFamily());
	}

	/**
	 * Sets if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case).
	 * 
	 * @param usePointStyle if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case).
	 */
	public void setUsePointStyle(boolean usePointStyle) {
		setValue(Property.usePointStyle, usePointStyle);
	}

	/**
	 * Returns if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case).
	 * 
	 * @return if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case). For default see {@link org.pepstock.charba.client.GlobalOptions#getLegend()}.
	 */
	public boolean isUsePointStyle() {
		return getValue(Property.usePointStyle, getChart().getGlobal().getLegend().getLabels().isUsePointStyle());
	}

	/**
	 * Sets the width of coloured box.
	 * 
	 * @param boxWidth width of coloured box.
	 */
	public void setBoxWidth(int boxWidth) {
		setValue(Property.boxWidth, boxWidth);
	}

	/**
	 * Returns the width of coloured box.
	 * 
	 * @return width of coloured box. For default see {@link org.pepstock.charba.client.GlobalOptions#getLegend()}.
	 */
	public int getBoxWidth() {
		return getValue(Property.boxWidth, getChart().getGlobal().getLegend().getLabels().getBoxWidth());
	}
	
	/**
	 * Sets the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @param padding Padding to apply around labels. Only top and bottom are implemented.
	 */
	public void setPadding(int padding) {
		setValue(Property.padding, padding);
	}

	/**
	 * Returns the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @return Padding to apply around labels. Only top and bottom are implemented. For default see {@link org.pepstock.charba.client.GlobalOptions#getLegend()}.
	 */
	public int getPadding() {
		return getValue(Property.padding, getChart().getGlobal().getLegend().getLabels().getPadding());
	}

	/**
	 * Called to generate legend items for each thing in the legend. Default implementation returns the text + styling for the color box.
	 * 
	 * @return array of legend items.
	 */
	protected LegendLabelItem[] generateLegendLabels() {
		// checks if callback is consistent
		if (labelsCallBack != null) {
			// calls callback
			LegendLabelItem[] result = labelsCallBack.generateLegendLabels(getChart());
			// if result is null..
			if (result == null) {
				// .. returns a empty array.
				return new LegendLabelItem[0];
			}
			// returns the generated array of legend items.
			return result;
		}
		// returns a empty array
		return new LegendLabelItem[0];
	}

	/**
	 * Called to filter legend items out of the legend. Receives 1 parameter, a Legend Item.
	 * 
	 * @param item legend item to check.
	 * @return <code>true</code> to maintain the item, otherwise <code>false</code> to hide it.
	 */
	protected boolean onFilter(GenericJavaScriptObject item) {
		// checks if callback is consistent
		if (filterHandler != null) {
			// calls callback
			return filterHandler.onFilter(getChart(), new LegendItem(item));
		}
		return true;
	}
	
	/**
	 * Sets the java script code to activate the call back, adding functions.
	 * 
	 * @param options
	 *            java script object where adding new functions definition.
	 */
    private native void registerNativeLegendLabelsHandler(GenericJavaScriptObject options)/*-{
    	var self = this;
	    options.generateLabels = function(chart){
	    	// calls the default generateLabels function
	    	// to get the default.
	    	var labels = $wnd.Chart.defaults.global.legend.labels.generateLabels(chart);
	        var newLabels = self.@org.pepstock.charba.client.options.LegendLabels::generateLegendLabels()();
	        // checke if array is empty
	        // if empty, returns the default labels.
	        if (newLabels.length == 0){
	        	return labels;
	        } else {
	        	return newLabels;
	        }
	    }
	}-*/;

	/**
	 * Sets the java script code to activate the call back, adding functions.
	 * 
	 * @param options
	 *            java script object where adding new functions definition.
	 */
    private native void registerNativeFilterLabelsHandler(GenericJavaScriptObject options)/*-{
		var self = this;
	    options.filter = function(legendItem){
	    	return self.@org.pepstock.charba.client.options.LegendLabels::onFilter(Lorg/pepstock/charba/client/commons/GenericJavaScriptObject;)(legendItem);
	    }
	}-*/;

}