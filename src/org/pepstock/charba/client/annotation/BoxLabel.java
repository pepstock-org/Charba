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

import org.pepstock.charba.client.annotation.callbacks.LabelAlignPositionCallback;
import org.pepstock.charba.client.annotation.enums.LabelPosition;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyNativeObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.enums.TextAlign;
import org.pepstock.charba.client.enums.Weight;

/**
 * Implements a <b>LABEL</b> to apply on a LINE annotation.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class BoxLabel extends InnerLabel implements IsDefaultsBoxLabel {

	/**
	 * Default box label display, <b>{@value DEFAULT_DISPLAY}</b>.
	 */
	public static final boolean DEFAULT_DISPLAY = false;

	/**
	 * Default box label font weight, <b>{@link Weight#BOLD}</b>.
	 */
	public static final Weight DEFAULT_FONT_WEIGHT = Weight.BOLD;

	/**
	 * Default box label text color, <b>{@link HtmlColor#WHITE}</b>.
	 */
	public static final IsColor DEFAULT_COLOR = HtmlColor.BLACK;

	/**
	 * Default box label font color as string, <b>rgb(255, 255, 255)</b>.
	 */
	public static final String DEFAULT_FONT_COLOR_AS_STRING = DEFAULT_COLOR.toRGB();

	/**
	 * Default box label padding, <b>{@value DEFAULT_PADDING}</b>.
	 */
	public static final int DEFAULT_PADDING = 6;

	/**
	 * Default box label X adjust, <b>{@value DEFAULT_X_ADJUST}</b>.
	 */
	public static final double DEFAULT_X_ADJUST = 0D;

	/**
	 * Default box label Y adjust, <b>{@value DEFAULT_Y_ADJUST}</b>.
	 */
	public static final double DEFAULT_Y_ADJUST = 0D;

	/**
	 * Default text align for labels, <b>{@link TextAlign#CENTER}</b>.
	 */
	public static final TextAlign DEFAULT_TEXT_ALIGN = TextAlign.START;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		POSITION("position");

		// name value of property
		private final String value;

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
	// callback proxy to invoke the position function
	private final CallbackProxy<ProxyNativeObjectCallback> positionCallbackProxy = JsHelper.get().newCallbackProxy();

	// callback instance to handle position options
	private static final CallbackPropertyHandler<LabelAlignPositionCallback> POSITION_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.POSITION);

	// line annotation parent instance
	private final BoxAnnotation parent;
	// defaults options
	private final IsDefaultsBoxLabel defaultValues;
	// position instance
	private final AlignPosition position;

	/**
	 * To avoid any instantiation because is added in the all {@link LineAnnotation}.
	 * 
	 * @param parent {@link LineAnnotation} instance which contains the label
	 * @param defaultValues default options instance
	 */
	BoxLabel(BoxAnnotation parent, IsDefaultsBoxLabel defaultValues) {
		this(parent, null, defaultValues);
	}

	/**
	 * To avoid any instantiation because is added in the all {@link LineAnnotation}.
	 * 
	 * @param parent {@link LineAnnotation} instance which contains the label
	 * @param nativeObject native object to wrap, with all properties of a label
	 * @param defaultValues default options instance
	 */
	BoxLabel(BoxAnnotation parent, NativeObject nativeObject, IsDefaultsBoxLabel defaultValues) {
		super(parent, nativeObject, defaultValues);
		// stores line annotation parent
		this.parent = parent;
		// checks if default value is consistent
		// stores default options
		this.defaultValues = defaultValues;
		// loads position
		this.position = new AlignPosition(this, Property.POSITION, getValue(Property.POSITION), this.defaultValues.getPosition());
		// checks if already stored
		if (!has(Property.POSITION)) {
			// stores position
			setValue(Property.POSITION, this.position);
		}
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.positionCallbackProxy.setCallback(context -> onPosition(new AnnotationContext(this.parent, context)));
	}

	/**
	 * Returns the anchor position of label on line.
	 * 
	 * @return the anchor position of label on line
	 */
	@Override
	public AlignPosition getPosition() {
		return position;
	}

	// ---------------------
	// CALLBACKS
	// ---------------------

	/**
	 * Returns the callback called to set the anchor position of label on box.
	 * 
	 * @return the callback called to set the anchor position of label on box
	 */
	@Override
	public LabelAlignPositionCallback getPositionCallback() {
		return POSITION_PROPERTY_HANDLER.getCallback(this, defaultValues.getPositionCallback());
	}

	/**
	 * Sets the callback to set the anchor position of label on box.
	 * 
	 * @param positionCallback to set the anchor position of label on box
	 */
	public void setPosition(LabelAlignPositionCallback positionCallback) {
		POSITION_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, positionCallback, positionCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the anchor position of label on box.
	 * 
	 * @param positionCallback to set the anchor position of label on box
	 */
	public void setPosition(NativeCallback positionCallback) {
		// resets callback
		setPosition((LabelAlignPositionCallback) null);
		// stores values
		setValueAndAddToParent(Property.POSITION, positionCallback);
	}

	/**
	 * Returns a native object of a {@link AlignPosition} when the callback has been activated.
	 * 
	 * @param context annotation context instance.
	 * @return a native object of a {@link AlignPosition}
	 */
	private NativeObject onPosition(AnnotationContext context) {
		// prepares the result
		AlignPosition position = null;
		// gets value
		Object result = ScriptableUtils.getOptionValue(context, getPositionCallback());
		// checks if consistent
		if (result instanceof AlignPosition) {
			// casts
			position = (AlignPosition) result;
		} else if (result instanceof LabelPosition) {
			// casts
			LabelPosition labelPosition = (LabelPosition) result;
			// create align position
			position = new AlignPosition(labelPosition);
		} else if (result instanceof Number) {
			// is a percentage
			// casts
			Number number = (Number) result;
			// create align position
			position = new AlignPosition(number.doubleValue());
		} else {
			// if here the result is not consistent
			// then returns the default
			position = new AlignPosition();
		}
		// returns the object
		return position.nativeObject();
	}

}
