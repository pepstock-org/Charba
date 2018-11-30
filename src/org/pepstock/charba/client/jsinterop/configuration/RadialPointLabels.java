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
import org.pepstock.charba.client.jsinterop.callbacks.RadialPointLabelCallback;
import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.commons.JsHelper;

import jsinterop.annotations.JsFunction;

/**
 * It is used to configure the point labels that are shown on the perimeter of the scale.<br>
 * Note that these options only apply if display is true.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class RadialPointLabels extends AxisContainer {
	
	@JsFunction
	interface ProxyPointLabelCallback {
		// FIXME context
		String call(Object context, String item);
	}
	
	private final CallbackProxy<ProxyPointLabelCallback> pointLabelCallbackProxy = JsHelper.get().newCallbackProxy();
	
	private RadialPointLabelCallback callback = null;
	
	/**
	 * Name of fields of JavaScript object.
	 */
	enum Property implements Key
	{
		callback
	}
	
	/**
	 * Builds the object storing the chart instance and the axis which this point labels belongs to.
	 * 
	 * @param chart chart instance.
	 * @param axis axis which this point labels belongs to.
	 */
	RadialPointLabels(Axis axis) {
		super(axis);
		pointLabelCallbackProxy.setCallback(new ProxyPointLabelCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.PointLabels.ProxyPointLabelCallback#call(java.lang.Object, java.lang.String)
			 */
			@Override
			public String call(Object context, String item) {
				if (callback != null) {
					return callback.onCallback(getAxis(), item);
				}
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
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public void setFontStyle(FontStyle fontStyle) {
		getAxis().getScale().getPointLabels().setFontStyle(fontStyle);
	}

	/**
	 * Returns the font style for the tick labels, follows CSS font-style options (i.e. normal, italic, oblique, initial,
	 * inherit).
	 * 
	 * @return font style for the tick labels, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * @see org.pepstock.charba.client.enums.FontStyle
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
	 * @return the callback
	 */
	public RadialPointLabelCallback getCallback() {
		return callback;
	}
	/**
	 * @param callback the callback to set
	 */
	public void setCallback(RadialPointLabelCallback callback) {
		this.callback = callback;
		if (callback != null) {
			getAxis().getConfiguration().setCallback(getAxis().getConfiguration().getPointLabels(), Property.callback, pointLabelCallbackProxy.getProxy());
		} else {
			getAxis().getConfiguration().setCallback(getAxis().getConfiguration().getPointLabels(), Property.callback, null);
		}
	}

//	/**
//	 * @return the callback
//	 */
//	public RadialPointLabelCallback getCallback() {
//		return getAxis().getConfiguration().getPointLabels().getCallback();
//	}
//	
//	/**
//	 * set the callback
//	 */
//	public void getCallback(RadialPointLabelCallback callback) {
//		getAxis().getConfiguration().getPointLabels().setCallback(callback);
//		if (!hasGlobalCallback()) {
//			if (callback == null) {
//				getAxis().getChart().getOptions().getConfiguration().setRadialPointLabelCallbackHandler(getAxis().getScale(), null);
//			} else {
//				getAxis().getChart().getOptions().getConfiguration().setRadialPointLabelCallbackHandler(getAxis().getScale(), this);
//			}
//		}
//	}
//
//	/* (non-Javadoc)
//	 * @see org.pepstock.charba.client.jsinterop.callbacks.handlers.RadialPointLabelHandler#onCallback(java.lang.String)
//	 */
//	@Override
//	public String onCallback(String item) {
//		if (getCallback() != null) {
//			return getCallback().onCallback(getAxis(), item);
//		} else if (hasGlobalCallback()) {
//			getAxis().getDefaultScale().getPointLabels().getCallback().onCallback(getAxis(), item);
//		}
//		return item;
//	}
}