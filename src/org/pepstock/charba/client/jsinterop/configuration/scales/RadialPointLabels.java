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
package org.pepstock.charba.client.jsinterop.configuration.scales;

import org.pepstock.charba.client.callbacks.RadialPointLabelCallback;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.enums.FontStyle;

/**
 * It is used to configure the point labels that are shown on the perimeter of the scale.<br>
 * Note that these options only apply if display is true.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class RadialPointLabels {
	
	private final Axis axis;

	private RadialPointLabelCallback callback = null;

//	/**
//	 * Name of fields of JavaScript object.
//	 */
//	private enum Property implements Key
//	{
//		display,
//		callback,
//		fontSize,
//		fontStyle,
//		fontColor,
//		fontFamily,
//	}

	/**
	 * Builds the object storing the chart instance and the axis which this point labels belongs to.
	 * 
	 * @param chart chart instance.
	 * @param axis axis which this point labels belongs to.
	 */
	RadialPointLabels(Axis axis) {
		this.axis = axis;
//		registerNativePointLabelCallbacktHandler(getJavaScriptObject();
	}

	/**
	 * If true, labels are shown
	 * 
	 * @param display if true, labels are shown
	 */
	public void setDisplay(boolean display) {
		axis.getScale().getPointLabels().setDisplay(display);
	}

	/**
	 * If true, labels are shown
	 * 
	 * @return if true, labels are shown. 
	 */
	public boolean isDisplay() {
		return axis.getScale().getPointLabels().isDisplay();
	}

	/**
	 * Sets the font size for the tick labels.
	 * 
	 * @param fontSize font size for the tick labels.
	 */
	public void setFontSize(int fontSize) {
		axis.getScale().getPointLabels().setFontSize(fontSize);
	}

	/**
	 * Returns the font size for the tick labels.
	 * 
	 * @return font size for the tick labels. 
	 */
	public int getFontSize() {
		return axis.getScale().getPointLabels().getFontSize();
	}

	/**
	 * Sets the font style for the tick labels, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle font style for the tick labels, follows CSS font-style options (i.e. normal, italic, oblique, initial,
	 *            inherit).
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public void setFontStyle(FontStyle fontStyle) {
		axis.getScale().getPointLabels().setFontStyle(fontStyle);
	}

	/**
	 * Returns the font style for the tick labels, follows CSS font-style options (i.e. normal, italic, oblique, initial,
	 * inherit).
	 * 
	 * @return font style for the tick labels, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getFontStyle() {
		return axis.getScale().getPointLabels().getFontStyle();
	}

	/**
	 * Sets the font color for tick labels.
	 * 
	 * @param fontColor font color for tick labels.
	 */
	public void setFontColor(IsColor fontColor) {
		axis.getScale().getPointLabels().setFontColor(fontColor);
	}

	/**
	 * Sets the font color for tick labels.
	 * 
	 * @param fontColor font color for tick labels.
	 */
	public void setFontColor(String fontColor) {
		axis.getScale().getPointLabels().setFontColor(fontColor);
	}

	/**
	 * Returns the font color for tick labels.
	 * 
	 * @return font color for tick labels. 
	 */
	public String getFontColorAsString() {
		return axis.getScale().getPointLabels().getFontColorAsString();
	}

	/**
	 * Returns the font color for tick labels.
	 * 
	 * @return font color for tick labels. 
	 */
	public IsColor getFontColor() {
		return axis.getScale().getPointLabels().getFontColor();
	}

	/**
	 * Sets the font family for the tick labels, follows CSS font-family options.
	 * 
	 * @param fontFamily font family for the tick labels, follows CSS font-family options.
	 */
	public void setFontFamily(String fontFamily) {
		axis.getScale().getPointLabels().setFontFamily(fontFamily);
	}

	/**
	 * Returns the font family for the tick labels, follows CSS font-family options.
	 * 
	 * @return font family for the tick labels, follows CSS font-family options. 
	 */
	public String getFontFamily() {
		return axis.getScale().getPointLabels().getFontFamily();
	}

	/**
	 * @return the callback
	 */
	public RadialPointLabelCallback getCallback() {
		return callback;
	}

	/**
	 * @param callback the callback to set
	 */
	public void setCallback(RadialPointLabelCallback callback) {
//		// checks if property is already set
//		if (hasToBeRegistered(callback, Property.callback)) {
//			// registers the property
//			registerNativePointLabelCallbacktHandler(getJavaScriptObject();
//		}
//		this.callback = callback;
	}

	/**
	 * Returns the string representation of the tick value as it should be displayed on the chart.
	 * 
	 * @param item tick item to be shown
	 * @return string representation of the tick value
	 */
	protected String onCallback(String item) {
//		FIXME
//		// gets the chart instance
//		AbstractChart<?, ?> chart = getChart();
//		// checks if callback and chart are consistent
//		if (callback != null && chart != null) {
//			// invokes callback and returns new value
//			return callback.onCallback(chart, item);
//		}
//		// returns the item passed because no changed
//		return item;
		return null;
	}

	/**
	 * Sets the java script code to activate the call back, adding functions.
	 * 
	 * @param options
	 *            java script object where adding new functions definition.
	 */
    private native void registerNativePointLabelCallbacktHandler(GenericJavaScriptObject options)/*-{
		var self = this;
	    options.callback = function(item){
	    	return self.@org.pepstock.charba.client.options.scales.RadialPointLabels::onCallback(Ljava/lang/String;)(item);
	    }
	}-*/;
}