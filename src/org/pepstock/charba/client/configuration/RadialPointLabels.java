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

import org.pepstock.charba.client.callbacks.CallbackFunctionContext;
import org.pepstock.charba.client.callbacks.RadialPointLabelCallback;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;

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
	 * Java script FUNCTION callback called to transform data labels to point labels. The default implementation simply returns the current string.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyPointLabelCallback {

		/**
		 * Method of function to be called to transform data labels to point labels. The default implementation simply returns the current string.
		 * 
		 * @param context context context value of <code>this</code> to the execution context of function.
		 * @param item label of the point
		 * @return new label to show.
		 */
		String call(CallbackFunctionContext context, String item);
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
		CALLBACK("callback");

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

	// font instance
	private final Font font;

	/**
	 * Builds the object storing the axis which this point labels belongs to.
	 * 
	 * @param axis axis which this point labels belongs to.
	 */
	RadialPointLabels(Axis axis) {
		super(axis);
		// gets embedded elements
		font = new Font(axis.getConfiguration().getPointLabels().getFont());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		pointLabelCallbackProxy.setCallback((context, item) -> {
			// checks if callback is consistent
			if (callback != null) {
				// invokes callback
				return callback.onCallback(getAxis(), item);
			}
			// returns passed item
			return item;
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
			getAxis().getConfiguration().setCallback(getAxis().getConfiguration().getPointLabels(), Property.CALLBACK, pointLabelCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getAxis().getConfiguration().setCallback(getAxis().getConfiguration().getPointLabels(), Property.CALLBACK, null);
		}
	}

}