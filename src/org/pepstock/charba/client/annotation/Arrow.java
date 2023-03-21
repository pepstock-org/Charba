/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.annotation;

import org.pepstock.charba.client.annotation.callbacks.FillCallback;
import org.pepstock.charba.client.annotation.callbacks.LengthCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyBooleanCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback;
import org.pepstock.charba.client.callbacks.ScriptableIntegerChecker;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.callbacks.SimpleDisplayCallback;
import org.pepstock.charba.client.callbacks.WidthCallback;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;

/**
 * Implements a <b>ARROWHEADS</b>, and its start and end nodes, to apply on a LINE annotation.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Arrow extends AbstractNode implements IsDefaultsArrow, HasBorderOptions, HasShadowOptions, HasExtendedShadowOptions, HasBackgroundColor {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DISPLAY("display"),
		FILL("fill"),
		LENGTH("length"),
		WIDTH("width");

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
	// callback proxy to invoke the length function
	private final CallbackProxy<ProxyIntegerCallback> lengthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the width function
	private final CallbackProxy<ProxyIntegerCallback> widthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the fill function
	private final CallbackProxy<ProxyBooleanCallback> fillCallbackProxy = JsHelper.get().newCallbackProxy();

	// callback instance to handle display options
	private static final CallbackPropertyHandler<SimpleDisplayCallback<AnnotationContext>> DISPLAY_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.DISPLAY);
	// callback instance to handle length options
	private static final CallbackPropertyHandler<LengthCallback> LENGTH_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.LENGTH);
	// callback instance to handle width options
	private static final CallbackPropertyHandler<WidthCallback<AnnotationContext>> WIDTH_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.WIDTH);
	// callback instance to handle fill options
	private static final CallbackPropertyHandler<FillCallback> FILL_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.FILL);

	// line annotation instance
	private final LineAnnotation annotation;
	// defaults options
	private final IsDefaultsArrow defaultValues;
	// background color handler
	private final BackgroundColorHandler backgroundColorHandler;
	// extended border options handler
	private final BorderOptionsHandler borderOptionsHandler;
	// shadow options handler
	private final ShadowOptionsHandler shadowOptionsHandler;
	// extended shadow options handler
	private final ExtendedShadowOptionsHandler extendedShadowOptionsHandler;

	/**
	 * To avoid any instantiation because is added in the all {@link LineAnnotation}.
	 * 
	 * @param annotation {@link LineAnnotation} which the arrow heads node belongs to
	 * @param nativeObject native object to wrap, with all properties of a arrow
	 * @param defaultValues default options instance
	 */
	Arrow(LineAnnotation annotation, NativeObject nativeObject, IsDefaultsArrow defaultValues) {
		super(nativeObject);
		// stores line annotation parent
		this.annotation = annotation;
		// checks if default value is consistent
		// stores default options
		this.defaultValues = checkDefaultValuesArgument(defaultValues);
		// stores incremental ID
		setNewIncrementalId();
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.displayCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValue(new AnnotationContext(this.annotation, context), getDisplayCallback(), this.defaultValues.isDisplay()));
		// sets function to proxy callback in order to invoke the java interface
		this.lengthCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(new AnnotationContext(this.annotation, context), getLengthCallback(), this.defaultValues.getLength(), ScriptableIntegerChecker.POSITIVE_OR_DEFAULT).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.widthCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(new AnnotationContext(this.annotation, context), getWidthCallback(), this.defaultValues.getWidth(), ScriptableIntegerChecker.POSITIVE_OR_DEFAULT).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.fillCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValue(new AnnotationContext(this.annotation, context), getFillCallback(), this.defaultValues.isFill()));

		// creates background color handler
		this.backgroundColorHandler = new BackgroundColorHandler(this.annotation, this.defaultValues, getNativeObject());
		// loads border handler
		this.borderOptionsHandler = new BorderOptionsHandler(this.annotation, this.defaultValues, getNativeObject());
		// loads shadow handler
		this.shadowOptionsHandler = new ShadowOptionsHandler(this.annotation, this.defaultValues, getNativeObject());
		// creates shadow options handler
		this.extendedShadowOptionsHandler = new ExtendedShadowOptionsHandler(this.annotation, this.defaultValues, getNativeObject());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.HasBackgroundColor#getBackgroundColorHandler()
	 */
	@Override
	public final BackgroundColorHandler getBackgroundColorHandler() {
		return backgroundColorHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.HasBorderOptions#getBorderOptionsHandler()
	 */
	@Override
	public final BorderOptionsHandler getBorderOptionsHandler() {
		return borderOptionsHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.HasShadowOptions#getShadowOptionsHandler()
	 */
	@Override
	public final ShadowOptionsHandler getShadowOptionsHandler() {
		return shadowOptionsHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.HasExtendedShadowOptions#getExtendedShadowOptionsHandler()
	 */
	@Override
	public final ExtendedShadowOptionsHandler getExtendedShadowOptionsHandler() {
		return extendedShadowOptionsHandler;
	}

	/**
	 * Sets <code>true</code> whether the arrow head should be displayed.
	 * 
	 * @param display <code>true</code> whether the arrow head should be displayed
	 */
	public final void setDisplay(boolean display) {
		// resets callback
		setDisplay((SimpleDisplayCallback<AnnotationContext>) null);
		// stores value
		setValue(Property.DISPLAY, display);
	}

	/**
	 * Returns <code>true</code> whether the arrow head should be displayed.
	 * 
	 * @return <code>true</code> whether the arrow head should be displayed
	 */
	@Override
	public final boolean isDisplay() {
		return getValue(Property.DISPLAY, defaultValues.isDisplay());
	}

	/**
	 * Sets <code>true</code> whether the arrow head must be closed and filled.
	 * 
	 * @param fill <code>true</code> whether the arrow head must be closed and filled.
	 */
	public final void setFill(boolean fill) {
		// resets callback
		setFill((FillCallback) null);
		// stores value
		setValue(Property.FILL, fill);
	}

	/**
	 * Returns <code>true</code> whether the arrow head must be closed and filled.
	 * 
	 * @return <code>true</code> whether the arrow head must be closed and filled.
	 */
	@Override
	public final boolean isFill() {
		return getValue(Property.FILL, defaultValues.isFill());
	}

	/**
	 * Sets the amount of pixels of the length of the arrow head.
	 * 
	 * @param length the amount of pixels of the length of the arrow head.
	 */
	public final void setLength(int length) {
		// resets callback
		setLength((LengthCallback) null);
		// stores value
		setValue(Property.LENGTH, Checker.positiveOrZero(length));
	}

	/**
	 * Returns the amount of pixels of the length of the arrow head.
	 * 
	 * @return the amount of pixels of the length of the arrow head.
	 */
	@Override
	public final int getLength() {
		return getValue(Property.LENGTH, defaultValues.getLength());
	}

	/**
	 * Sets the amount of pixels of the width of the arrow head.
	 * 
	 * @param width the amount of pixels of the width of the arrow head
	 */
	public final void setWidth(int width) {
		// resets callback
		setWidth((WidthCallback<AnnotationContext>) null);
		// stores value
		setValue(Property.WIDTH, Checker.positiveOrZero(width));
	}

	/**
	 * Returns the width of the arrow head.
	 * 
	 * @return the width of the arrow head
	 */
	@Override
	public final int getWidth() {
		return getValue(Property.WIDTH, defaultValues.getWidth());
	}

	// ---------------------
	// CALLBACKS
	// ---------------------

	/**
	 * Returns the callback called to set whether the arrow head should be displayed.
	 * 
	 * @return the callback called to set whether the arrow head should be displayed
	 */
	@Override
	public final SimpleDisplayCallback<AnnotationContext> getDisplayCallback() {
		return DISPLAY_PROPERTY_HANDLER.getCallback(this, defaultValues.getDisplayCallback());
	}

	/**
	 * Sets the callback to set whether the arrow head should be displayed.
	 * 
	 * @param displayCallback to set whether the arrow head should be displayed
	 */
	public final void setDisplay(SimpleDisplayCallback<AnnotationContext> displayCallback) {
		DISPLAY_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, displayCallback, displayCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set whether the arrow head should be displayed.
	 * 
	 * @param displayCallback to set whether the arrow head should be displayed
	 */
	public final void setDisplay(NativeCallback displayCallback) {
		// resets callback
		setDisplay((SimpleDisplayCallback<AnnotationContext>) null);
		// stores values
		setValue(Property.DISPLAY, displayCallback);
	}

	/**
	 * Returns the callback called to set the amount of pixels of the length of the arrow head.
	 * 
	 * @return the callback called to set the amount of pixels of the length of the arrow head.
	 */
	@Override
	public final LengthCallback getLengthCallback() {
		return LENGTH_PROPERTY_HANDLER.getCallback(this, defaultValues.getLengthCallback());
	}

	/**
	 * Sets the callback to set the amount of pixels of the length of the arrow head.
	 * 
	 * @param lengthCallback to set the amount of pixels of the length of the arrow head.
	 */
	public final void setLength(LengthCallback lengthCallback) {
		LENGTH_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, lengthCallback, lengthCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the amount of pixels of the length of the arrow head.
	 * 
	 * @param lengthCallback to set the amount of pixels of the length of the arrow head.
	 */
	public final void setLength(NativeCallback lengthCallback) {
		// resets callback
		setLength((LengthCallback) null);
		// stores values
		setValue(Property.LENGTH, lengthCallback);
	}

	/**
	 * Returns the callback called to set the amount of pixels of the width of the arrow head.
	 * 
	 * @return the callback called to set the amount of pixels of the width of the arrow head.
	 */
	@Override
	public final WidthCallback<AnnotationContext> getWidthCallback() {
		return WIDTH_PROPERTY_HANDLER.getCallback(this, defaultValues.getWidthCallback());
	}

	/**
	 * Sets the callback to set the amount of pixels of the width of the arrow head.
	 * 
	 * @param widthCallback to set the amount of pixels of the width of the arrow head.
	 */
	public final void setWidth(WidthCallback<AnnotationContext> widthCallback) {
		WIDTH_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, widthCallback, widthCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the amount of pixels of the width of the arrow head.
	 * 
	 * @param widthCallback to set the amount of pixels of the width of the arrow head.
	 */
	public final void setWidth(NativeCallback widthCallback) {
		// resets callback
		setWidth((WidthCallback<AnnotationContext>) null);
		// stores values
		setValue(Property.WIDTH, widthCallback);
	}

	/**
	 * Returns the callback called to set <code>true</code> whether the arrow head must be closed and filled.
	 * 
	 * @return the callback called to set <code>true</code> whether the arrow head must be closed and filled.
	 */
	@Override
	public final FillCallback getFillCallback() {
		return FILL_PROPERTY_HANDLER.getCallback(this, defaultValues.getFillCallback());
	}

	/**
	 * Sets the callback to set whether <code>true</code> whether the arrow head must be closed and filled.
	 * 
	 * @param fillCallback to set whether <code>true</code> whether the arrow head must be closed and filled.
	 */
	public final void setFill(FillCallback fillCallback) {
		FILL_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, fillCallback, fillCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set whether <code>true</code> whether the arrow head must be closed and filled.
	 * 
	 * @param fillCallback to set whether <code>true</code> whether the arrow head must be closed and filled.
	 */
	public final void setFill(NativeCallback fillCallback) {
		// resets callback
		setFill((FillCallback) null);
		// stores values
		setValue(Property.FILL, fillCallback);
	}

}