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

import org.pepstock.charba.client.annotation.callbacks.CalloutPositionCallback;
import org.pepstock.charba.client.annotation.callbacks.MarginCallback;
import org.pepstock.charba.client.annotation.callbacks.SideCallback;
import org.pepstock.charba.client.annotation.callbacks.StartCallback;
import org.pepstock.charba.client.annotation.enums.CalloutPosition;
import org.pepstock.charba.client.callbacks.DisplayCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyBooleanCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableIntegerChecker;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Implements a <b>CALLOUT</b> to apply on a LABEL annotation.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Callout extends AbstractNode implements IsDefaultsCallout, HasBorderOptions, HasExtendedBorderOptions {

	/**
	 * Default callout display, <b>{@value DEFAULT_DISPLAY}</b>.
	 */
	public static final boolean DEFAULT_DISPLAY = false;

	/**
	 * Default callout border width, <b>{@value DEFAULT_BORDER_WIDTH}</b>.
	 */
	public static final int DEFAULT_BORDER_WIDTH = 1;

	/**
	 * Default callout border cap style, <b>{@link CapStyle#BUTT}</b>.
	 */
	public static final CapStyle DEFAULT_BORDER_CAP_STYLE = CapStyle.BUTT;

	/**
	 * Default callout border join style, <b>{@link JoinStyle#MITER}</b>.
	 */
	public static final JoinStyle DEFAULT_BORDER_JOIN_STYLE = JoinStyle.MITER;

	/**
	 * Default callout border dash offset, <b>{@value DEFAULT_BORDER_DASH_OFFSET}</b>.
	 */
	public static final int DEFAULT_BORDER_DASH_OFFSET = 0;

	/**
	 * Default amount of pixels between the label and the callout separator, <b>{@value DEFAULT_MARGIN}</b>.
	 */
	public static final int DEFAULT_MARGIN = 5;

	/**
	 * Default width of the starter line of callout pointer, <b>{@value DEFAULT_SIDE}</b>.
	 */
	public static final int DEFAULT_SIDE = 5;

	/**
	 * Default the separator dimension in pixels to use as starting point for callout pointer, <b>{@link Undefined#INTEGER}</b>.
	 */
	public static final int DEFAULT_START = Undefined.INTEGER;

	/**
	 * Default the percentage of the separator dimension to use as starting point for callout pointer, <b>{@value DEFAULT_START_AS_PERCENTAGE}</b>.
	 */
	public static final double DEFAULT_START_AS_PERCENTAGE = 0.5;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		// even if in the JS plugin the options is called "enabled"
		// we think that "display" is more coherent with the scope of the option
		// and then Charba use "display" in the method
		ENABLED("enabled"),
		MARGIN("margin"),
		POSITION("position"),
		SIDE("side"),
		START("start");

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
	// callback proxy to invoke the display function
	private final CallbackProxy<ProxyBooleanCallback> displayCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the margin function
	private final CallbackProxy<ProxyIntegerCallback> marginCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the side function
	private final CallbackProxy<ProxyIntegerCallback> sideCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the start function
	private final CallbackProxy<ProxyObjectCallback> startCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the position function
	private final CallbackProxy<ProxyStringCallback> positionCallbackProxy = JsHelper.get().newCallbackProxy();

	// callback instance to handle display options
	private static final CallbackPropertyHandler<DisplayCallback<AnnotationContext>> DISPLAY_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ENABLED);
	// callback instance to handle margin options
	private static final CallbackPropertyHandler<MarginCallback> MARGIN_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.MARGIN);
	// callback instance to handle side options
	private static final CallbackPropertyHandler<SideCallback> SIDE_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.SIDE);
	// callback instance to handle start options
	private static final CallbackPropertyHandler<StartCallback> START_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.START);
	// callback instance to handle position options
	private static final CallbackPropertyHandler<CalloutPositionCallback> POSITION_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.POSITION);

	// line annotation parent instance
	private final AbstractAnnotation parent;
	// defaults options
	private final IsDefaultsCallout defaultValues;
	// border options handler
	private final BorderOptionsHandler borderOptionsHandler;
	// extended border options handler
	private final ExtendedBorderOptionsHandler extendedBorderOptionsHandler;

	/**
	 * To avoid any instantiation because is added in the all {@link LineAnnotation}.
	 * 
	 * @param parent {@link LineAnnotation} instance which contains the label
	 * @param nativeObject native object to wrap, with all properties of a label
	 * @param defaultValues default options instance
	 */
	Callout(AbstractAnnotation parent, NativeObject nativeObject, IsDefaultsCallout defaultValues) {
		super(nativeObject);
		// stores line annotation parent
		this.parent = parent;
		// checks if default value is consistent
		// stores default options
		this.defaultValues = checkDefaultValuesArgument(defaultValues);
		// stores incremental ID
		setNewIncrementalId();
		// creates border options handler
		this.borderOptionsHandler = new BorderOptionsHandler(this.parent, this.defaultValues, getNativeObject());
		// creates border options handler
		this.extendedBorderOptionsHandler = new ExtendedBorderOptionsHandler(this.parent, this.defaultValues, getNativeObject());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.displayCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(new AnnotationContext(this.parent, context), getDisplayCallback(), this.defaultValues.isDisplay()));
		// sets function to proxy callback in order to invoke the java interface
		this.marginCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValueAsNumber(new AnnotationContext(this.parent, context), getMarginCallback(), this.defaultValues.getMargin(), ScriptableIntegerChecker.POSITIVE_OR_DEFAULT).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.sideCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValueAsNumber(new AnnotationContext(this.parent, context), getSideCallback(), this.defaultValues.getSide(), ScriptableIntegerChecker.POSITIVE_OR_DEFAULT).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.startCallbackProxy.setCallback(context -> onStart(new AnnotationContext(this.parent, context), this.defaultValues.getStart()));
		// sets function to proxy callback in order to invoke the java interface
		this.positionCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(new AnnotationContext(this.parent, context), getPositionCallback(), this.defaultValues.getPosition()).value());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.HasBorderOptions#getBorderOptionsHandler()
	 */
	@Override
	public BorderOptionsHandler getBorderOptionsHandler() {
		return borderOptionsHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.HasExtendedBorderOptions#getExtendedBorderOptionsHandler()
	 */
	@Override
	public ExtendedBorderOptionsHandler getExtendedBorderOptionsHandler() {
		return extendedBorderOptionsHandler;
	}

	/**
	 * Sets <code>true</code> whether the label should be displayed.
	 * 
	 * @param display <code>true</code> whether the label should be displayed
	 */
	public void setDisplay(boolean display) {
		// resets callback
		setDisplay((DisplayCallback<AnnotationContext>) null);
		// stores value
		setValue(Property.ENABLED, display);
	}

	/**
	 * Returns <code>true</code> whether the label should be displayed.
	 * 
	 * @return <code>true</code> whether the label should be displayed
	 */
	@Override
	public boolean isDisplay() {
		return getValue(Property.ENABLED, defaultValues.isDisplay());
	}

	/**
	 * Sets the amount of pixels between the label and the callout separator.
	 * 
	 * @param margin the amount of pixels between the label and the callout separator
	 */
	public void setMargin(int margin) {
		// resets callback
		setMargin((MarginCallback) null);
		// stores value
		setValue(Property.MARGIN, Checker.positiveOrZero(margin));
	}

	/**
	 * Returns the amount of pixels between the label and the callout separator.
	 * 
	 * @return the amount of pixels between the label and the callout separator.
	 */
	@Override
	public int getMargin() {
		return getValue(Property.MARGIN, defaultValues.getMargin());
	}

	/**
	 * Sets the width of the starter line of callout pointer.
	 * 
	 * @param side the width of the starter line of callout pointer
	 */
	public void setSide(int side) {
		// resets callback
		setSide((SideCallback) null);
		// stores value
		setValue(Property.SIDE, Checker.positiveOrZero(side));
	}

	/**
	 * Returns the width of the starter line of callout pointer.
	 * 
	 * @return the width of the starter line of callout pointer
	 */
	@Override
	public int getSide() {
		return getValue(Property.SIDE, defaultValues.getSide());
	}

	/**
	 * Sets the separator dimension in pixels to use as starting point for callout pointer.
	 * 
	 * @param start the separator dimension in pixels to use as starting point for callout pointer
	 */
	public void setStart(int start) {
		// resets callback
		setStart((StartCallback) null);
		// stores value
		setValue(Property.START, Checker.positiveOrZero(start));
	}

	/**
	 * Returns the separator dimension in pixels to use as starting point for callout pointer.
	 * 
	 * @return the separator dimension in pixels to use as starting point for callout pointer
	 */
	@Override
	public int getStart() {
		return getValue(Property.START, defaultValues.getStart());
	}

	/**
	 * Sets the percentage of the separator dimension to use as starting point for callout pointer.
	 * 
	 * @param start the percentage of the separator dimension to use as starting point for callout pointer
	 */
	public void setStartAsPercentage(double start) {
		// resets callback
		setStart((StartCallback) null);
		// stores value
		setValue(Property.START, Utilities.getAsPercentage(start));
	}

	/**
	 * Returns the percentage of the separator dimension to use as starting point for callout pointer.
	 * 
	 * @return the percentage of the separator dimension to use as starting point for callout pointer
	 */
	@Override
	public double getStartAsPercentage() {
		return Utilities.getAsPercentage(getValue(Property.START, Undefined.STRING), defaultValues.getStartAsPercentage());
	}

	/**
	 * Sets the position of callout, with respect to the label.
	 * 
	 * @param position the position of callout, with respect to the label
	 */
	public void setPosition(CalloutPosition position) {
		// resets callback
		setPosition((CalloutPositionCallback) null);
		// stores value
		setValue(Property.POSITION, position);
	}

	/**
	 * Returns the position of callout, with respect to the label.
	 * 
	 * @return the position of callout, with respect to the label.
	 */
	@Override
	public CalloutPosition getPosition() {
		return getValue(Property.POSITION, CalloutPosition.values(), defaultValues.getPosition());
	}

	// ---------------------
	// CALLBACKS
	// ---------------------

	/**
	 * Returns the callback called to set whether the label should be displayed.
	 * 
	 * @return the callback called to set whether the label should be displayed
	 */
	@Override
	public DisplayCallback<AnnotationContext> getDisplayCallback() {
		return DISPLAY_PROPERTY_HANDLER.getCallback(this, defaultValues.getDisplayCallback());
	}

	/**
	 * Sets the callback to set whether the label should be displayed.
	 * 
	 * @param displayCallback to set whether the label should be displayed
	 */
	public void setDisplay(DisplayCallback<AnnotationContext> displayCallback) {
		DISPLAY_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, displayCallback, displayCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set whether the label should be displayed.
	 * 
	 * @param displayCallback to set whether the label should be displayed
	 */
	public void setDisplay(NativeCallback displayCallback) {
		// resets callback
		setDisplay((DisplayCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.ENABLED, displayCallback);
	}

	/**
	 * Returns the callback to set the amount of pixels between the label and the callout separator.
	 * 
	 * @return the callback to set the amount of pixels between the label and the callout separator
	 */
	@Override
	public MarginCallback getMarginCallback() {
		return MARGIN_PROPERTY_HANDLER.getCallback(this, defaultValues.getMarginCallback());
	}

	/**
	 * Sets the callback to set the amount of pixels between the label and the callout separator.
	 * 
	 * @param marginCallback the callback to set the amount of pixels between the label and the callout separator
	 */
	public void setMargin(MarginCallback marginCallback) {
		MARGIN_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, marginCallback, marginCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the amount of pixels between the label and the callout separator.
	 * 
	 * @param marginCallback the callback to set the amount of pixels between the label and the callout separator
	 */
	public void setMargin(NativeCallback marginCallback) {
		// resets callback
		setMargin((MarginCallback) null);
		// stores values
		setValueAndAddToParent(Property.MARGIN, marginCallback);
	}

	/**
	 * Returns the callback to set the width of the starter line of callout pointer.
	 * 
	 * @return the callback to set the width of the starter line of callout pointer
	 */
	@Override
	public SideCallback getSideCallback() {
		return SIDE_PROPERTY_HANDLER.getCallback(this, defaultValues.getSideCallback());
	}

	/**
	 * Sets the callback to set the width of the starter line of callout pointer.
	 * 
	 * @param sideCallback the callback to set the width of the starter line of callout pointer
	 */
	public void setSide(SideCallback sideCallback) {
		SIDE_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, sideCallback, sideCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the width of the starter line of callout pointer.
	 * 
	 * @param sideCallback the callback to set the width of the starter line of callout pointer
	 */
	public void setSide(NativeCallback sideCallback) {
		// resets callback
		setSide((SideCallback) null);
		// stores values
		setValueAndAddToParent(Property.SIDE, sideCallback);
	}

	/**
	 * Returns the callback to set the separator dimension to use as starting point for callout pointer.
	 * 
	 * @return the callback to set the separator dimension to use as starting point for callout pointer
	 */
	@Override
	public StartCallback getStartCallback() {
		return START_PROPERTY_HANDLER.getCallback(this, defaultValues.getStartCallback());
	}

	/**
	 * Sets the callback to set the separator dimension to use as starting point for callout pointer.
	 * 
	 * @param startCallback the callback to set the separator dimension to use as starting point for callout pointer
	 */
	public void setStart(StartCallback startCallback) {
		START_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, startCallback, startCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the separator dimension to use as starting point for callout pointer.
	 * 
	 * @param startCallback the callback to set the separator dimension to use as starting point for callout pointer
	 */
	public void setStart(NativeCallback startCallback) {
		// resets callback
		setStart((StartCallback) null);
		// stores values
		setValueAndAddToParent(Property.START, startCallback);
	}

	/**
	 * Returns the callback to set the position of callout, with respect to the label.
	 * 
	 * @return the callback to set the position of callout, with respect to the label
	 */
	@Override
	public CalloutPositionCallback getPositionCallback() {
		return POSITION_PROPERTY_HANDLER.getCallback(this, defaultValues.getPositionCallback());
	}

	/**
	 * Sets the callback to set the position of callout, with respect to the label.
	 * 
	 * @param positionCallback the callback to set the position of callout, with respect to the label
	 */
	public void setPosition(CalloutPositionCallback positionCallback) {
		POSITION_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, positionCallback, positionCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the position of callout, with respect to the label.
	 * 
	 * @param positionCallback the callback to set the position of callout, with respect to the label
	 */
	public void setPosition(NativeCallback positionCallback) {
		// resets callback
		setPosition((CalloutPositionCallback) null);
		// stores values
		setValueAndAddToParent(Property.POSITION, positionCallback);
	}

	// -----------------------
	// INTERNALS for CALLBACKS
	// -----------------------

	/**
	 * Returns an object as string when the callback has been activated.
	 * 
	 * @param context annotation context instance.
	 * @param defaultValue default value to apply if callback returns an inconsistent value
	 * @return an object as string
	 */
	private Object onStart(AnnotationContext context, double defaultValue) {
		// gets value
		Number result = ScriptableUtils.getOptionValue(context, getStartCallback(), defaultValue);
		// checks if consistent
		if (result instanceof Integer) {
			// returns as integer
			return result.intValue();
		} else if (result instanceof Double) {
			// is a percentage
			// returns the double value
			return Utilities.getAsPercentage(result.doubleValue());
		}
		// if here the result is null
		// then returns the default
		return Utilities.getAsPercentage(defaultValue);
	}

}
