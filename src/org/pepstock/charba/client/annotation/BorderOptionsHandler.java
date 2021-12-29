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

import java.util.List;

import org.pepstock.charba.client.callbacks.BorderDashCallback;
import org.pepstock.charba.client.callbacks.BorderDashOffsetCallback;
import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableDoubleChecker;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyArrayCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableIntegerChecker;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.callbacks.WidthCallback;
import org.pepstock.charba.client.commons.Array;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.commons.PropertyHandler;

/**
 * Base object to map the border options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class BorderOptionsHandler extends PropertyHandler<IsDefaultsBorderOptionsHandler> {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BORDER_COLOR("borderColor"),
		BORDER_WIDTH("borderWidth"),
		BORDER_DASH("borderDash"),
		BORDER_DASH_OFFSET("borderDashOffset");

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
	// callback proxy to invoke the border color function
	private final CallbackProxy<ProxyObjectCallback> borderColorCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border width function
	private final CallbackProxy<ProxyIntegerCallback> borderWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border dash function
	private final CallbackProxy<ProxyArrayCallback> borderDashCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border dash offset function
	private final CallbackProxy<ProxyDoubleCallback> borderDashOffsetCallbackProxy = JsHelper.get().newCallbackProxy();

	// callback instance to handle border color options
	private static final CallbackPropertyHandler<ColorCallback<AnnotationContext>> BORDER_COLOR_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BORDER_COLOR);
	// callback instance to handle border width options
	private static final CallbackPropertyHandler<WidthCallback<AnnotationContext>> BORDER_WIDTH_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BORDER_WIDTH);
	// callback instance to handle border dash options
	private static final CallbackPropertyHandler<BorderDashCallback<AnnotationContext>> BORDER_DASH_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BORDER_DASH);
	// callback instance to handle border dash offset options
	private static final CallbackPropertyHandler<BorderDashOffsetCallback<AnnotationContext>> BORDER_DASH_OFFSET_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.BORDER_DASH_OFFSET);

	/**
	 * Creates a border options handler with the native object where border options properties must be managed and the default value to use when the property does not exist.
	 * 
	 * @param parent model which contains the border options handler.
	 * @param defaultValues default value of border options to use when the properties do not exist
	 * @param nativeObject native object where border options handler properties must be managed
	 */
	BorderOptionsHandler(AbstractAnnotation parent, IsDefaultsBorderOptionsHandler defaultValues, NativeObject nativeObject) {
		super(parent, defaultValues, nativeObject);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.borderColorCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValueAsColor(new AnnotationContext(parent, context), getBorderColorCallback(), getDefaultValues().getBorderColorAsString(), false));
		// sets function to proxy callback in order to invoke the java interface
		this.borderWidthCallbackProxy
				.setCallback(context -> ScriptableUtils.getOptionValueAsNumber(new AnnotationContext(parent, context), getBorderWidthCallback(), getDefaultValues().getBorderWidth(), ScriptableIntegerChecker.POSITIVE_OR_DEFAULT).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.borderDashCallbackProxy.setCallback(context -> onBorderDash(new AnnotationContext(parent, context), getBorderDashCallback(), getDefaultValues().getBorderDash()));
		// sets function to proxy callback in order to invoke the java interface
		this.borderDashOffsetCallbackProxy
				.setCallback(context -> ScriptableUtils.getOptionValueAsNumber(new AnnotationContext(parent, context), getBorderDashOffsetCallback(), getDefaultValues().getBorderDashOffset(), ScriptableDoubleChecker.POSITIVE_OR_DEFAULT).doubleValue());
	}

	/**
	 * Sets the color of the border of annotation.
	 * 
	 * @param borderColor the color of the border of annotation
	 */
	void setBorderColor(String borderColor) {
		// resets callback
		setBorderColor((ColorCallback<AnnotationContext>) null);
		// stores value
		setValueAndAddToParent(Property.BORDER_COLOR, borderColor);
	}

	/**
	 * Returns the color of the border of annotation.
	 * 
	 * @return the color of the border of annotation
	 */
	String getBorderColorAsString() {
		return getValue(Property.BORDER_COLOR, getDefaultValues().getBorderColorAsString());
	}

	/**
	 * Sets the width of the border in pixels.
	 * 
	 * @param borderWidth the width of the border in pixels.
	 */
	void setBorderWidth(int borderWidth) {
		// resets callback
		setBorderWidth((WidthCallback<AnnotationContext>) null);
		// stores value
		setValueAndAddToParent(Property.BORDER_WIDTH, Checker.positiveOrZero(borderWidth));
	}

	/**
	 * Returns the width of the border in pixels.
	 * 
	 * @return the width of the border in pixels.
	 */
	int getBorderWidth() {
		return getValue(Property.BORDER_WIDTH, getDefaultValues().getBorderWidth());
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	void setBorderDash(int... borderDash) {
		// resets callback
		setBorderDash((BorderDashCallback<AnnotationContext>) null);
		// stores value
		setArrayValueAndAddToParent(Property.BORDER_DASH, ArrayInteger.fromOrNull(borderDash));
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 */
	List<Integer> getBorderDash() {
		// checks if there is the property
		if (isType(Property.BORDER_DASH, ObjectType.ARRAY)) {
			// gets the array
			ArrayInteger array = getArrayValue(Property.BORDER_DASH);
			// and transforms to a list
			return ArrayListHelper.list(array);
		}
		// if here, the property is missing
		return getDefaultValues().getBorderDash();
	}

	/**
	 * Sets the line dash pattern offset.
	 * 
	 * @param borderDashOffset the line dash pattern offset.
	 */
	void setBorderDashOffset(double borderDashOffset) {
		// resets callback
		setBorderDashOffset((BorderDashOffsetCallback<AnnotationContext>) null);
		// stores value
		setValueAndAddToParent(Property.BORDER_DASH_OFFSET, borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset.
	 * 
	 * @return the line dash pattern offset.
	 */
	double getBorderDashOffset() {
		return getValue(Property.BORDER_DASH_OFFSET, getDefaultValues().getBorderDashOffset());
	}

	// ---------------------
	// CALLBACKS
	// ---------------------

	/**
	 * Returns the callback called to set the color of the border of annotation.
	 * 
	 * @return the callback called to set the color of the border of annotation
	 */
	ColorCallback<AnnotationContext> getBorderColorCallback() {
		return BORDER_COLOR_PROPERTY_HANDLER.getCallback(this, getDefaultValues().getBorderColorCallback());
	}

	/**
	 * Sets the callback to set the color of the border of annotation.
	 * 
	 * @param borderColorCallback to set the color of the border of annotation
	 */
	void setBorderColor(ColorCallback<AnnotationContext> borderColorCallback) {
		BORDER_COLOR_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, borderColorCallback, borderColorCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the color of the border of annotation.
	 * 
	 * @param borderColorCallback to set the color of the border of annotation
	 */
	void setBorderColor(NativeCallback borderColorCallback) {
		// resets callback
		setBorderColor((ColorCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.BORDER_COLOR, borderColorCallback);
	}

	/**
	 * Returns the callback called to set the width of the border in pixels.
	 * 
	 * @return the callback called to set the width of the border in pixels
	 */
	WidthCallback<AnnotationContext> getBorderWidthCallback() {
		return BORDER_WIDTH_PROPERTY_HANDLER.getCallback(this, getDefaultValues().getBorderWidthCallback());
	}

	/**
	 * Sets the callback to set the color of the width of the border in pixels.
	 * 
	 * @param borderWidthCallback to set the width of the border in pixels
	 */
	void setBorderWidth(WidthCallback<AnnotationContext> borderWidthCallback) {
		BORDER_WIDTH_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, borderWidthCallback, borderWidthCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the color of the width of the border in pixels.
	 * 
	 * @param borderWidthCallback to set the width of the border in pixels
	 */
	void setBorderWidth(NativeCallback borderWidthCallback) {
		// resets callback
		setBorderWidth((WidthCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.BORDER_WIDTH, borderWidthCallback);
	}

	/**
	 * Returns the callback called to set the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which
	 * describe the pattern.
	 * 
	 * @return the callback called to set the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which
	 *         describe the pattern
	 */
	BorderDashCallback<AnnotationContext> getBorderDashCallback() {
		return BORDER_DASH_PROPERTY_HANDLER.getCallback(this, getDefaultValues().getBorderDashCallback());
	}

	/**
	 * Sets the callback to set the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the
	 * pattern.
	 * 
	 * @param borderDashCallback to set the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe
	 *            the pattern
	 */
	void setBorderDash(BorderDashCallback<AnnotationContext> borderDashCallback) {
		BORDER_DASH_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, borderDashCallback, borderDashCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the
	 * pattern.
	 * 
	 * @param borderDashCallback to set the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe
	 *            the pattern
	 */
	void setBorderDash(NativeCallback borderDashCallback) {
		// resets callback
		setBorderDash((BorderDashCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.BORDER_DASH, borderDashCallback);
	}

	/**
	 * Returns the callback called to set the line dash pattern offset.
	 * 
	 * @return the callback called to set the line dash pattern offset
	 */
	BorderDashOffsetCallback<AnnotationContext> getBorderDashOffsetCallback() {
		return BORDER_DASH_OFFSET_PROPERTY_HANDLER.getCallback(this, getDefaultValues().getBorderDashOffsetCallback());
	}

	/**
	 * Sets the callback to set the line dash pattern offset.
	 * 
	 * @param borderDashOffsetCallback to set the line dash pattern offset
	 */
	void setBorderDashOffset(BorderDashOffsetCallback<AnnotationContext> borderDashOffsetCallback) {
		BORDER_DASH_OFFSET_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, borderDashOffsetCallback, borderDashOffsetCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the line dash pattern offset.
	 * 
	 * @param borderDashOffsetCallback to set the line dash pattern offset
	 */
	void setBorderDashOffset(NativeCallback borderDashOffsetCallback) {
		// resets callback
		setBorderDashOffset((BorderDashOffsetCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.BORDER_DASH_OFFSET, borderDashOffsetCallback);
	}

	/**
	 * Returns an array of integer when the callback has been activated.
	 * 
	 * @param context annotation context instance
	 * @param borderDashCallback border dash callback instance
	 * @param defaultValue default value of options
	 * @return an array of integer
	 */
	Array onBorderDash(AnnotationContext context, BorderDashCallback<AnnotationContext> borderDashCallback, List<Integer> defaultValue) {
		// gets value
		List<Integer> result = ScriptableUtils.getOptionValue(context, borderDashCallback);
		// checks if consistent
		if (result != null) {
			// returns result of callback
			return ArrayInteger.fromOrEmpty(result);
		}
		// default result
		return ArrayInteger.fromOrEmpty(defaultValue);
	}
}