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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.PointStyleCallback;
import org.pepstock.charba.client.callbacks.RadiusCallback;
import org.pepstock.charba.client.callbacks.RotationCallback;
import org.pepstock.charba.client.callbacks.ScriptableDoubleChecker;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultPoint;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.options.AbstractElement;

/**
 * Point elements are used to represent the points in a line chart or a bubble chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class Point extends AbstractConfigurationElement<IsDefaultPoint> {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		RADIUS("radius"),
		HIT_RADIUS("hitRadius"),
		HOVER_RADIUS("hoverRadius"),
		POINT_STYLE("pointStyle"),
		ROTATION("rotation");

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

	// callback proxy to invoke the radius function
	private final CallbackProxy<ProxyDoubleCallback> radiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hit radius function
	private final CallbackProxy<ProxyDoubleCallback> hitRadiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover radius function
	private final CallbackProxy<ProxyDoubleCallback> hoverRadiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the rotation function
	private final CallbackProxy<ProxyDoubleCallback> rotationCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point style function
	private final CallbackProxy<ProxyObjectCallback> pointStyleCallbackProxy = JsHelper.get().newCallbackProxy();

	// point style callback instance
	private PointStyleCallback<DatasetContext> pointStyleCallback = null;
	// rotation callback instance
	private RotationCallback<DatasetContext> rotationCallback = null;
	// hover radius callback instance
	private RadiusCallback<DatasetContext> hoverRadiusCallback = null;
	// hit radius callback instance
	private RadiusCallback<DatasetContext> hitRadiusCallback = null;
	// radius callback instance
	private RadiusCallback<DatasetContext> radiusCallback = null;

	/**
	 * Builds the object storing the root options element.
	 * 
	 * @param options root options element.
	 */
	Point(ConfigurationOptions options) {
		super(options);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.radiusCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(createContext(context), getRadiusCallback(), getDefaultElement().getRadius(), ScriptableDoubleChecker.POSITIVE_OR_DEFAULT).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.hitRadiusCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(createContext(context), getHitRadiusCallback(), getDefaultElement().getHitRadius(), ScriptableDoubleChecker.POSITIVE_OR_DEFAULT).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.hoverRadiusCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(createContext(context), getHoverRadiusCallback(), getDefaultElement().getHoverRadius(), ScriptableDoubleChecker.POSITIVE_OR_DEFAULT).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.rotationCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(createContext(context), getRotationCallback(), getDefaultElement().getRotation()).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.pointStyleCallbackProxy.setCallback(context -> onPointStyle(createContext(context), getPointStyleCallback(), getDefaultElement().getPointStyle()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractConfigurationElement#getElement()
	 */
	@Override
	protected AbstractElement<IsDefaultPoint> getElement() {
		return getConfiguration().getElements().getPoint();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractConfigurationElement#getDefaultElement()
	 */
	@Override
	protected IsDefaultPoint getDefaultElement() {
		return getOptions().getDefaultValues().getElements().getPoint();
	}

	/**
	 * Sets the radius of the point shape.<br>
	 * If set to 0, the point is not rendered.
	 * 
	 * @param radius array of the radius of the point shape.
	 */
	public void setRadius(double radius) {
		// resets callback
		setRadius((RadiusCallback<DatasetContext>) null);
		// stores value
		getConfiguration().getElements().getPoint().setRadius(radius);
	}

	/**
	 * Returns the radius of the point.
	 * 
	 * @return the radius of the point.
	 */
	public double getRadius() {
		return getConfiguration().getElements().getPoint().getRadius();
	}

	/**
	 * Sets the style of the point.
	 * 
	 * @param pointStyle the style of the point.
	 */
	public void setPointStyle(boolean pointStyle) {
		// resets callback
		setPointStyle((PointStyleCallback<DatasetContext>) null);
		// stores value
		getConfiguration().getElements().getBar().setPointStyle(pointStyle);
	}

	/**
	 * Sets the style of the point.
	 * 
	 * @param pointStyle the style of the point.
	 */
	public void setPointStyle(PointStyle pointStyle) {
		// resets callback
		setPointStyle((PointStyleCallback<DatasetContext>) null);
		// stores value
		getConfiguration().getElements().getPoint().setPointStyle(pointStyle);
	}

	/**
	 * Sets the style of the point as canvas.
	 * 
	 * @param pointStyle the style of the point.
	 */
	public void setPointStyle(Canvas pointStyle) {
		// resets callback
		setPointStyle((PointStyleCallback<DatasetContext>) null);
		// stores value
		getConfiguration().getElements().getPoint().setPointStyle(pointStyle);
	}

	/**
	 * Sets the style of the point as image.
	 * 
	 * @param pointStyle the style of the point.
	 */
	public void setPointStyle(Img pointStyle) {
		// resets callback
		setPointStyle((PointStyleCallback<DatasetContext>) null);
		// stores value
		getConfiguration().getElements().getPoint().setPointStyle(pointStyle);
	}

	/**
	 * Returns the style of the point as canvas.
	 * 
	 * @return the style of the point as canvas.
	 */
	public Canvas getPointStyleAsCanvas() {
		return getConfiguration().getElements().getPoint().getPointStyleAsCanvas();
	}

	/**
	 * Returns the style of the legend.
	 * 
	 * @return the style of the legend.
	 */
	public PointStyle getPointStyle() {
		return getConfiguration().getElements().getPoint().getPointStyle();
	}

	/**
	 * Returns the style of the point as image.
	 * 
	 * @return the style of the point as image.
	 */
	public Img getPointStyleAsImage() {
		return getConfiguration().getElements().getPoint().getPointStyleAsImage();
	}

	/**
	 * Sets the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @param hitRadius the pixel size of the non-displayed point.
	 */
	public void setHitRadius(double hitRadius) {
		// resets callback
		setHitRadius((RadiusCallback<DatasetContext>) null);
		// stores value
		getConfiguration().getElements().getPoint().setHitRadius(hitRadius);
	}

	/**
	 * Returns the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @return the pixel size of the non-displayed point.
	 */
	public double getHitRadius() {
		return getConfiguration().getElements().getPoint().getHitRadius();
	}

	/**
	 * Sets the radius of the point when hovered.
	 * 
	 * @param hoverRadius the radius of the point when hovered.
	 */
	public void setHoverRadius(double hoverRadius) {
		// resets callback
		setHoverRadius((RadiusCallback<DatasetContext>) null);
		// stores value
		getConfiguration().getElements().getPoint().setHoverRadius(hoverRadius);
	}

	/**
	 * Returns the radius of the point when hovered.
	 * 
	 * @return the radius of the point when hovered.
	 */
	public double getHoverRadius() {
		return getConfiguration().getElements().getPoint().getHoverRadius();
	}

	/**
	 * Sets the point rotation (in degrees).
	 * 
	 * @param rotation the point rotation (in degrees).
	 */
	public void setRotation(double rotation) {
		// resets callback
		setRotation((RotationCallback<DatasetContext>) null);
		// stores value
		getConfiguration().getElements().getPoint().setRotation(rotation);
	}

	/**
	 * Returns the point rotation (in degrees).
	 * 
	 * @return the point rotation (in degrees).
	 */
	public double getRotation() {
		return getConfiguration().getElements().getPoint().getRotation();
	}

	// ---------------
	// CALLBACKS
	// ---------------

	/**
	 * Returns the point style callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the point style callback, if set, otherwise <code>null</code>.
	 */
	public PointStyleCallback<DatasetContext> getPointStyleCallback() {
		return pointStyleCallback;
	}

	/**
	 * Sets the point style callback.
	 * 
	 * @param pointStyleCallback the point style callback.
	 */
	public void setPointStyle(PointStyleCallback<DatasetContext> pointStyleCallback) {
		// sets the callback
		this.pointStyleCallback = pointStyleCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.POINT_STYLE, pointStyleCallback, pointStyleCallbackProxy);
	}

	/**
	 * Sets the point style callback.
	 * 
	 * @param pointStyleCallback the point style callback.
	 */
	public void setPointStyle(NativeCallback pointStyleCallback) {
		// resets callback
		setPointStyle((PointStyleCallback<DatasetContext>) null);
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.POINT_STYLE, pointStyleCallback);
	}

	/**
	 * Returns the radius callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the radius callback, if set, otherwise <code>null</code>.
	 */
	public RadiusCallback<DatasetContext> getRadiusCallback() {
		return radiusCallback;
	}

	/**
	 * Sets the radius callback.
	 * 
	 * @param radiusCallback the radius callback.
	 */
	public void setRadius(RadiusCallback<DatasetContext> radiusCallback) {
		// sets the callback
		this.radiusCallback = radiusCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.RADIUS, radiusCallback, radiusCallbackProxy);
	}

	/**
	 * Sets the radius callback.
	 * 
	 * @param radiusCallback the radius callback.
	 */
	public void setRadius(NativeCallback radiusCallback) {
		// resets callback
		setRadius((RadiusCallback<DatasetContext>) null);
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.RADIUS, radiusCallback);
	}

	/**
	 * Returns the hit radius callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the hit radius callback, if set, otherwise <code>null</code>.
	 */
	public RadiusCallback<DatasetContext> getHitRadiusCallback() {
		return hitRadiusCallback;
	}

	/**
	 * Sets the hit radius callback.
	 * 
	 * @param hitRadiusCallback the hit radius callback.
	 */
	public void setHitRadius(RadiusCallback<DatasetContext> hitRadiusCallback) {
		// sets the callback
		this.hitRadiusCallback = hitRadiusCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.HIT_RADIUS, hitRadiusCallback, hitRadiusCallbackProxy);
	}

	/**
	 * Sets the hit radius callback.
	 * 
	 * @param hitRadiusCallback the hit radius callback.
	 */
	public void setHitRadius(NativeCallback hitRadiusCallback) {
		// resets callback
		setHitRadius((RadiusCallback<DatasetContext>) null);
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.HIT_RADIUS, hitRadiusCallback);
	}

	/**
	 * Returns the hover radius callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the hover radius callback, if set, otherwise <code>null</code>.
	 */
	public RadiusCallback<DatasetContext> getHoverRadiusCallback() {
		return hoverRadiusCallback;
	}

	/**
	 * Sets the hover radius callback.
	 * 
	 * @param hoverRadiusCallback the hover radius callback.
	 */
	public void setHoverRadius(RadiusCallback<DatasetContext> hoverRadiusCallback) {
		// sets the callback
		this.hoverRadiusCallback = hoverRadiusCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.HOVER_RADIUS, hoverRadiusCallback, hoverRadiusCallbackProxy);
	}

	/**
	 * Sets the hover radius callback.
	 * 
	 * @param hoverRadiusCallback the hover radius callback.
	 */
	public void setHoverRadius(NativeCallback hoverRadiusCallback) {
		// resets callback
		setHoverRadius((RadiusCallback<DatasetContext>) null);
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.HOVER_RADIUS, hoverRadiusCallback);
	}

	/**
	 * Returns the rotation callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the rotation callback, if set, otherwise <code>null</code>.
	 */
	public RotationCallback<DatasetContext> getRotationCallback() {
		return rotationCallback;
	}

	/**
	 * Sets the rotation callback.
	 * 
	 * @param rotationCallback the rotation callback.
	 */
	public void setRotation(RotationCallback<DatasetContext> rotationCallback) {
		// sets the callback
		this.rotationCallback = rotationCallback;
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.ROTATION, rotationCallback, rotationCallbackProxy);
	}

	/**
	 * Sets the rotation callback.
	 * 
	 * @param rotationCallback the rotation callback.
	 */
	public void setRotation(NativeCallback rotationCallback) {
		// resets callback
		setRotation((RotationCallback<DatasetContext>) null);
		// stores and manages callback
		getChart().getOptions().setCallback(getElement(), Property.ROTATION, rotationCallback);
	}

}