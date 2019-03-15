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

import org.pepstock.charba.client.callbacks.RadialPointLabelCallback;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.FontStyle;

import jsinterop.annotations.JsFunction;

/**
 * It is used to configure the point labels that are shown on the perimeter of the scale.<br>
 * Note that these options only apply if display is true.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class RadialPointLabels extends AxisContainer {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback called to transform data labels to point labels. The default implementation simply returns
	 * the current string.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyPointLabelCallback {

		/**
		 * Method of function to be called to transform data labels to point labels. The default implementation simply returns
		 * the current string.
		 * 
		 * @param context context context Value of <code>this</code> to the execution context of function.
		 * @param item label of the point
		 * @return new label to show.
		 */
		String call(Object context, String item);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------

	// callback proxy to invoke the point labels function
	private final CallbackProxy<ProxyPointLabelCallback> pointLabelCallbackProxy = JsHelper.get().newCallbackProxy();

	// ---------------------------
	// -- USERS CALLBACKS ---
	// ---------------------------
	// user callbacks implementation for point labels
	private RadialPointLabelCallback callback = null;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		callback
	}

	/**
	 * Builds the object storing the axis which this point labels belongs to.
	 * 
	 * @param axis axis which this point labels belongs to.
	 */
	RadialPointLabels(Axis axis) {
		super(axis);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		pointLabelCallbackProxy.setCallback(new ProxyPointLabelCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.options.PointLabels.ProxyPointLabelCallback#call(java.lang.Object,
			 * java.lang.String)
			 */
			@Override
			public String call(Object context, String item) {
				// checks if callback is consistent
				if (callback != null) {
					// invokes callback
					return callback.onCallback(getAxis(), item);
				}
				// returns passed item
				return item;
			}
		});
	}

	/**
	 * If true, labels are shown
	 * 
	 * @param display if true, labels are shown
	 */
	public void setDisplay(boolean display) {
		getAxis().getScale().getPointLabels().setDisplay(display);
	}

	/**
	 * If true, labels are shown
	 * 
	 * @return if true, labels are shown.
	 */
	public boolean isDisplay() {
		return getAxis().getScale().getPointLabels().isDisplay();
	}

	/**
	 * Sets the font size for the tick labels.
	 * 
	 * @param fontSize font size for the tick labels.
	 */
	public void setFontSize(int fontSize) {
		getAxis().getScale().getPointLabels().setFontSize(fontSize);
	}

	/**
	 * Returns the font size for the tick labels.
	 * 
	 * @return font size for the tick labels.
	 */
	public int getFontSize() {
		return getAxis().getScale().getPointLabels().getFontSize();
	}

	/**
	 * Sets the font style for the tick labels, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle font style for the tick labels, follows CSS font-style options (i.e. normal, italic, oblique, initial,
	 *            inherit).
	 */
	public void setFontStyle(FontStyle fontStyle) {
		getAxis().getScale().getPointLabels().setFontStyle(fontStyle);
	}

	/**
	 * Returns the font style for the tick labels, follows CSS font-style options (i.e. normal, italic, oblique, initial,
	 * inherit).
	 * 
	 * @return font style for the tick labels, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 */
	public FontStyle getFontStyle() {
		return getAxis().getScale().getPointLabels().getFontStyle();
	}

	/**
	 * Sets the font color for tick labels.
	 * 
	 * @param fontColor font color for tick labels.
	 */
	public void setFontColor(IsColor fontColor) {
		getAxis().getScale().getPointLabels().setFontColor(fontColor);
	}

	/**
	 * Sets the font color for tick labels.
	 * 
	 * @param fontColor font color for tick labels.
	 */
	public void setFontColor(String fontColor) {
		getAxis().getScale().getPointLabels().setFontColor(fontColor);
	}

	/**
	 * Returns the font color for tick labels.
	 * 
	 * @return font color for tick labels.
	 */
	public String getFontColorAsString() {
		return getAxis().getScale().getPointLabels().getFontColorAsString();
	}

	/**
	 * Returns the font color for tick labels.
	 * 
	 * @return font color for tick labels.
	 */
	public IsColor getFontColor() {
		return getAxis().getScale().getPointLabels().getFontColor();
	}

	/**
	 * Sets the font family for the tick labels, follows CSS font-family options.
	 * 
	 * @param fontFamily font family for the tick labels, follows CSS font-family options.
	 */
	public void setFontFamily(String fontFamily) {
		getAxis().getScale().getPointLabels().setFontFamily(fontFamily);
	}

	/**
	 * Returns the font family for the tick labels, follows CSS font-family options.
	 * 
	 * @return font family for the tick labels, follows CSS font-family options.
	 */
	public String getFontFamily() {
		return getAxis().getScale().getPointLabels().getFontFamily();
	}
	
	/**
	 * Sets the height of an individual line of text.
	 * 
	 * @param lineHeight height of an individual line of text.
	 */
	public void setLineHeight(double lineHeight) {
		getAxis().getScale().getPointLabels().setLineHeight(lineHeight);
	}

	/**
	 * Sets the height of an individual line of text.
	 * 
	 * @param lineHeight height of an individual line of text.
	 */
	public void setLineHeight(String lineHeight) {
		getAxis().getScale().getPointLabels().setLineHeight(lineHeight);
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	public double getLineHeight() {
		return getAxis().getScale().getPointLabels().getLineHeight();
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	public String getLineHeightAsString() {
		return getAxis().getScale().getPointLabels().getLineHeightAsString();
	}

	/**
	 * Returns the user callback to change point labels.
	 * 
	 * @return the callback
	 */
	public RadialPointLabelCallback getCallback() {
		return callback;
	}

	/**
	 * Sets the user callback to change point labels.
	 * 
	 * @param callback the callback to set
	 */
	public void setCallback(RadialPointLabelCallback callback) {
		// sets the callback
		this.callback = callback;
		// checks if callback is consistent
		if (callback != null) {
			// adds the callback proxy function to java script object
			getAxis().getConfiguration().setCallback(getAxis().getConfiguration().getPointLabels(), Property.callback, pointLabelCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getAxis().getConfiguration().setCallback(getAxis().getConfiguration().getPointLabels(), Property.callback, null);
		}
	}

}