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
package org.pepstock.charba.client.annotation;

import org.pepstock.charba.client.callbacks.CapStyleCallback;
import org.pepstock.charba.client.callbacks.JoinStyleCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.PropertyHandler;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.JoinStyle;

/**
 * Base object to map the extended border options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class ExtendedBorderOptionsHandler extends PropertyHandler<IsDefaultsExtendedBorderOptionsHandler> {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BORDER_CAP_STYLE("borderCapStyle"),
		BORDER_JOIN_STYLE("borderJoinStyle");

		// name value of property
		private String value;

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

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the border cap style function
	private final CallbackProxy<ProxyStringCallback> borderCapStyleCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border join style function
	private final CallbackProxy<ProxyStringCallback> borderJoinStyleCallbackProxy = JsHelper.get().newCallbackProxy();

	// callback instance to handle border cap style options
	private static final CallbackPropertyHandler<CapStyleCallback<AnnotationContext>> BORDER_CAP_STYLE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BORDER_CAP_STYLE);
	// callback instance to handle border join style options
	private static final CallbackPropertyHandler<JoinStyleCallback<AnnotationContext>> BORDER_JOIN_STYLE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BORDER_JOIN_STYLE);

	/**
	 * Creates a extended border options handler with the native object where extended border options properties must be managed and the default value to use when the property does
	 * not exist.
	 * 
	 * @param parent model which contains the extended border options handler.
	 * @param defaultValues default value of extended border options to use when the properties do not exist
	 * @param nativeObject native object where extended border options handler properties must be managed
	 */
	ExtendedBorderOptionsHandler(AbstractAnnotation parent, IsDefaultsExtendedBorderOptionsHandler defaultValues, NativeObject nativeObject) {
		super(parent, defaultValues, nativeObject);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.borderCapStyleCallbackProxy.setCallback(context -> onBorderCapStyle(new AnnotationContext(parent, context), getDefaultValues().getBorderCapStyle()));
		// sets function to proxy callback in order to invoke the java interface
		this.borderJoinStyleCallbackProxy.setCallback(context -> onBorderJoinStyle(new AnnotationContext(parent, context), getDefaultValues().getBorderJoinStyle()));
	}

	/**
	 * Sets how the end points of every line are drawn.
	 * 
	 * @param borderCapStyle how the end points of every line are drawn.
	 */
	void setBorderCapStyle(CapStyle borderCapStyle) {
		// resets callback
		setBorderCapStyle((CapStyleCallback<AnnotationContext>) null);
		// stores value
		setValueAndAddToParent(Property.BORDER_CAP_STYLE, borderCapStyle);
	}

	/**
	 * Returns how the end points of every line are drawn.
	 * 
	 * @return how the end points of every line are drawn.
	 */
	CapStyle getBorderCapStyle() {
		return getValue(Property.BORDER_CAP_STYLE, CapStyle.values(), getDefaultValues().getBorderCapStyle());
	}

	/**
	 * Sets how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified end
	 * points and control points are exactly at the same position, are skipped).
	 * 
	 * @param borderJoinStyle how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 */
	void setBorderJoinStyle(JoinStyle borderJoinStyle) {
		// resets callback
		setBorderJoinStyle((JoinStyleCallback<AnnotationContext>) null);
		// stores value
		setValueAndAddToParent(Property.BORDER_JOIN_STYLE, borderJoinStyle);
	}

	/**
	 * Returns how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified
	 * end points and control points are exactly at the same position, are skipped).
	 * 
	 * @return how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 */
	JoinStyle getBorderJoinStyle() {
		return getValue(Property.BORDER_JOIN_STYLE, JoinStyle.values(), getDefaultValues().getBorderJoinStyle());
	}

	// ---------------------
	// CALLBACKS
	// ---------------------

	/**
	 * Returns the border capstyle callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border capstyle callback, if set, otherwise <code>null</code>.
	 */
	CapStyleCallback<AnnotationContext> getBorderCapStyleCallback() {
		return BORDER_CAP_STYLE_PROPERTY_HANDLER.getCallback(this, getDefaultValues().getBorderCapStyleCallback());
	}

	/**
	 * Sets the border capstyle callback.
	 * 
	 * @param borderCapStyleCallback the border capstyle callback.
	 */
	void setBorderCapStyle(CapStyleCallback<AnnotationContext> borderCapStyleCallback) {
		BORDER_CAP_STYLE_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, borderCapStyleCallback, borderCapStyleCallbackProxy.getProxy());
	}

	/**
	 * Sets the border capstyle callback.
	 * 
	 * @param borderCapStyleCallback the border capstyle callback.
	 */
	void setBorderCapStyle(NativeCallback borderCapStyleCallback) {
		// resets callback
		setBorderCapStyle((CapStyleCallback<AnnotationContext>) null);
		// stores and manages callback
		setValueAndAddToParent(Property.BORDER_CAP_STYLE, borderCapStyleCallback);
	}

	/**
	 * Returns the border join style callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border join style callback, if set, otherwise <code>null</code>.
	 */
	JoinStyleCallback<AnnotationContext> getBorderJoinStyleCallback() {
		return BORDER_JOIN_STYLE_PROPERTY_HANDLER.getCallback(this, getDefaultValues().getBorderJoinStyleCallback());
	}

	/**
	 * Sets the border join style callback.
	 * 
	 * @param borderJoinStyleCallback the border join style callback.
	 */
	void setBorderJoinStyle(JoinStyleCallback<AnnotationContext> borderJoinStyleCallback) {
		BORDER_JOIN_STYLE_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, borderJoinStyleCallback, borderJoinStyleCallbackProxy.getProxy());
	}

	/**
	 * Sets the border join style callback.
	 * 
	 * @param borderJoinStyleCallback the border join style callback.
	 */
	void setBorderJoinStyle(NativeCallback borderJoinStyleCallback) {
		// resets callback
		setBorderJoinStyle((JoinStyleCallback<AnnotationContext>) null);
		// stores and manages callback
		setValueAndAddToParent(Property.BORDER_JOIN_STYLE, borderJoinStyleCallback);
	}

	// -----------------------
	// INTERNALS for CALLBACKS
	// -----------------------

	/**
	 * Returns a {@link CapStyle} when the callback has been activated.
	 * 
	 * @param context annotation context instance.
	 * @param defaultValue default value of cap style
	 * @return a object property value, as {@link CapStyle}
	 */
	private String onBorderCapStyle(AnnotationContext context, CapStyle defaultValue) {
		return checkCallbackResult(ScriptableUtil.getOptionValue(context, getBorderCapStyleCallback()), defaultValue);
	}

	/**
	 * Returns a {@link JoinStyle} when the callback has been activated.
	 * 
	 * @param context annotation context instance.
	 * @param defaultValue default value of join style
	 * @return a object property value, as {@link JoinStyle}
	 */
	private String onBorderJoinStyle(AnnotationContext context, JoinStyle defaultValue) {
		return checkCallbackResult(ScriptableUtil.getOptionValue(context, getBorderJoinStyleCallback()), defaultValue);
	}

	/**
	 * Checks if the result is consistent, returning the value or default.
	 * 
	 * @param result result of callback to be checked and returned if consistent
	 * @param defaultValue default value for the callback invocation, used only if the result is <code>null</code>
	 * @return the value of key or the default.
	 */
	private String checkCallbackResult(Key result, Key defaultValue) {
		// checks result
		if (result != null) {
			return result.value();
		}
		// checks defaults
		Checker.checkIfValid(defaultValue, "Default value argument");
		// default result
		return defaultValue.value();
	}

}