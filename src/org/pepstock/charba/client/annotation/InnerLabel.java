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

import org.pepstock.charba.client.annotation.callbacks.AdjustSizeCallback;
import org.pepstock.charba.client.annotation.enums.DrawTime;
import org.pepstock.charba.client.callbacks.SimpleDisplayCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyBooleanCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;

/**
 * base object to map all label options for instances which belongs to another container.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class InnerLabel extends AbstractNode implements IsDefaultsInnerLabel, HasLabel, HasTextStrokeOptions {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		// even if in the JS plugin the options is called "enabled"
		// we think that "display" is more coherent with the scope of the option
		// and then Charba use "display" in the method
		ENABLED("enabled"),
		X_ADJUST("xAdjust"),
		Y_ADJUST("yAdjust");

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
	// callback proxy to invoke the xAdjust function
	private final CallbackProxy<ProxyDoubleCallback> xAdjustCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the yAdjust function
	private final CallbackProxy<ProxyDoubleCallback> yAdjustCallbackProxy = JsHelper.get().newCallbackProxy();

	// callback instance to handle display options
	private static final CallbackPropertyHandler<SimpleDisplayCallback<AnnotationContext>> DISPLAY_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.ENABLED);
	// callback instance to handle xAdjust options
	private static final CallbackPropertyHandler<AdjustSizeCallback> X_ADJUST_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.X_ADJUST);
	// callback instance to handle yAdjustg options
	private static final CallbackPropertyHandler<AdjustSizeCallback> Y_ADJUST_PROPERTY_HANDLER = new CallbackPropertyHandler<>(Property.Y_ADJUST);

	// line annotation parent instance
	private final AbstractAnnotation parent;
	// defaults options
	private final IsDefaultsInnerLabel defaultValues;
	// label handler
	private final LabelHandler labelHandler;
	// text stroke handler
	private final TextStrokeOptionsHandler textStrokeHandler;

	/**
	 * To avoid any instantiation because is added in the all {@link LineAnnotation}.
	 * 
	 * @param parent {@link LineAnnotation} instance which contains the label
	 * @param nativeObject native object to wrap, with all properties of a label
	 * @param defaultValues default options instance
	 */
	InnerLabel(AbstractAnnotation parent, NativeObject nativeObject, IsDefaultsInnerLabel defaultValues) {
		super(nativeObject);
		// stores line annotation parent
		this.parent = parent;
		// checks if default value is consistent
		// stores default options
		this.defaultValues = checkDefaultValuesArgument(defaultValues);
		// stores incremental ID
		setNewIncrementalId();
		// creates label handler
		this.labelHandler = new LabelHandler(this.parent, this, this.defaultValues, getNativeObject());
		// creates text stroke handler
		this.textStrokeHandler = new TextStrokeOptionsHandler(this.parent, this.defaultValues, getNativeObject());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.displayCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValue(new AnnotationContext(this.parent, context), getDisplayCallback(), defaultValues.isDisplay()));
		// sets function to proxy callback in order to invoke the java interface
		this.xAdjustCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(new AnnotationContext(this.parent, context), getXAdjustCallback(), defaultValues.getXAdjust()).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.yAdjustCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(new AnnotationContext(this.parent, context), getYAdjustCallback(), defaultValues.getYAdjust()).doubleValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.HasLabel#getLabelHandler()
	 */
	@Override
	public final LabelHandler getLabelHandler() {
		return labelHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.HasTextStrokeOptions#getTextStrokeOptionsHandler()
	 */
	@Override
	public final TextStrokeOptionsHandler getTextStrokeOptionsHandler() {
		return textStrokeHandler;
	}

	/**
	 * Sets <code>true</code> whether the label should be displayed.
	 * 
	 * @param display <code>true</code> whether the label should be displayed
	 */
	public final void setDisplay(boolean display) {
		// resets callback
		setDisplay((SimpleDisplayCallback<AnnotationContext>) null);
		// stores value
		setValue(Property.ENABLED, display);
	}

	/**
	 * Returns <code>true</code> whether the label should be displayed.
	 * 
	 * @return <code>true</code> whether the label should be displayed
	 */
	@Override
	public final boolean isDisplay() {
		return getValue(Property.ENABLED, defaultValues.isDisplay());
	}

	/**
	 * Sets the adjustment along x-axis (left-right) of label relative to above number (can be negative).<br>
	 * For horizontal lines positioned left or right, negative values move the label toward the edge, and positive values toward the center.
	 * 
	 * @param xAdjust the adjustment along x-axis (left-right) of label
	 */
	public final void setXAdjust(double xAdjust) {
		// resets callback
		setXAdjust((AdjustSizeCallback) null);
		// stores value
		setValue(Property.X_ADJUST, xAdjust);
	}

	/**
	 * Returns the adjustment along x-axis (left-right) of label relative to above number (can be negative).<br>
	 * For horizontal lines positioned left or right, negative values move the label toward the edge, and positive values toward the center.
	 * 
	 * @return the adjustment along x-axis (left-right) of label
	 */
	@Override
	public final double getXAdjust() {
		return getValue(Property.X_ADJUST, defaultValues.getXAdjust());
	}

	/**
	 * Sets the adjustment along y-axis (top-bottom) of label relative to above number (can be negative).<br>
	 * For vertical lines positioned top or bottom, negative values move the label toward the edge, and positive values toward the center.
	 * 
	 * @param yAdjust the adjustment along y-axis (top-bottom) of label
	 */
	public final void setYAdjust(double yAdjust) {
		// resets callback
		setYAdjust((AdjustSizeCallback) null);
		// stores value
		setValue(Property.Y_ADJUST, yAdjust);
	}

	/**
	 * Returns the adjustment along y-axis (top-bottom) of label relative to above number (can be negative).<br>
	 * For vertical lines positioned top or bottom, negative values move the label toward the edge, and positive values toward the center.
	 * 
	 * @return the adjustment along y-axis (top-bottom) of label
	 */
	@Override
	public final double getYAdjust() {
		return getValue(Property.Y_ADJUST, defaultValues.getYAdjust());
	}

	/**
	 * Sets the draw time which defines when the annotations are drawn.
	 * 
	 * @param drawTime the draw time which defines when the annotations are drawn
	 */
	public final void setDrawTime(DrawTime drawTime) {
		// stores value
		setValue(AnnotationOptions.Property.DRAW_TIME, drawTime);
	}

	/**
	 * Returns the draw time which defines when the annotations are drawn.
	 * 
	 * @return the draw time which defines when the annotations are drawn
	 */
	@Override
	public final DrawTime getDrawTime() {
		return getValue(AnnotationOptions.Property.DRAW_TIME, DrawTime.values(), defaultValues.getDrawTime());
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
	public final SimpleDisplayCallback<AnnotationContext> getDisplayCallback() {
		return DISPLAY_PROPERTY_HANDLER.getCallback(this, defaultValues.getDisplayCallback());
	}

	/**
	 * Sets the callback to set whether the label should be displayed.
	 * 
	 * @param displayCallback to set whether the label should be displayed
	 */
	public final void setDisplay(SimpleDisplayCallback<AnnotationContext> displayCallback) {
		DISPLAY_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, displayCallback, displayCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set whether the label should be displayed.
	 * 
	 * @param displayCallback to set whether the label should be displayed
	 */
	public final void setDisplay(NativeCallback displayCallback) {
		// resets callback
		setDisplay((SimpleDisplayCallback<AnnotationContext>) null);
		// stores values
		setValueAndAddToParent(Property.ENABLED, displayCallback);
	}

	/**
	 * Returns the callback called to set the adjustment along x-axis (left-right) of label relative to above number (can be negative).
	 * 
	 * @return the callback called to set the adjustment along x-axis (left-right) of label relative to above number (can be negative)
	 */
	@Override
	public final AdjustSizeCallback getXAdjustCallback() {
		return X_ADJUST_PROPERTY_HANDLER.getCallback(this, defaultValues.getXAdjustCallback());
	}

	/**
	 * Sets the callback to set the adjustment along y-axis (top-bottom) of label relative to above number (can be negative).
	 * 
	 * @param adjustCallback to set the adjustment along y-axis (top-bottom) of label relative to above number (can be negative)
	 */
	public final void setXAdjust(AdjustSizeCallback adjustCallback) {
		X_ADJUST_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, adjustCallback, xAdjustCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the adjustment along y-axis (top-bottom) of label relative to above number (can be negative).
	 * 
	 * @param adjustCallback to set the adjustment along y-axis (top-bottom) of label relative to above number (can be negative)
	 */
	public final void setXAdjust(NativeCallback adjustCallback) {
		// resets callback
		setXAdjust((AdjustSizeCallback) null);
		// stores values
		setValueAndAddToParent(Property.X_ADJUST, adjustCallback);
	}

	/**
	 * Returns the callback called to set the adjustment along y-axis (top-bottom) of label relative to above number (can be negative).
	 * 
	 * @return the callback called to set the adjustment along y-axis (top-bottom) of label relative to above number (can be negative)
	 */
	@Override
	public final AdjustSizeCallback getYAdjustCallback() {
		return Y_ADJUST_PROPERTY_HANDLER.getCallback(this, defaultValues.getYAdjustCallback());
	}

	/**
	 * Sets the callback to set the adjustment along x-axis (left-right) of label relative to above number (can be negative).
	 * 
	 * @param adjustCallback to set the adjustment along x-axis (left-right) of label relative to above number (can be negative)
	 */
	public final void setYAdjust(AdjustSizeCallback adjustCallback) {
		Y_ADJUST_PROPERTY_HANDLER.setCallback(this, AnnotationPlugin.ID, adjustCallback, yAdjustCallbackProxy.getProxy());
	}

	/**
	 * Sets the callback to set the adjustment along x-axis (left-right) of label relative to above number (can be negative).
	 * 
	 * @param adjustCallback to set the adjustment along x-axis (left-right) of label relative to above number (can be negative)
	 */
	public final void setYAdjust(NativeCallback adjustCallback) {
		// resets callback
		setYAdjust((AdjustSizeCallback) null);
		// stores values
		setValueAndAddToParent(Property.Y_ADJUST, adjustCallback);
	}

}
