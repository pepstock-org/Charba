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
package org.pepstock.charba.client.data;

import java.util.List;

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.DrawActiveElementsOnTopCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.RadiusCallback;
import org.pepstock.charba.client.callbacks.RotationCallback;
import org.pepstock.charba.client.callbacks.ScriptableDoubleChecker;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyBooleanCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultOptions;

/**
 * The chart allows a number of properties to be specified for each data set. These are used to set display properties for a specific data set.<br>
 * The location of the bubble is determined by the first two dimensions and the corresponding horizontal and vertical axes.<br>
 * The third dimension is represented by the size of the individual bubbles.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class BubbleDataset extends HoverDataset implements HasDataPoints, HasOrder, HasDataPointStyle {

	// default for "drawActiveElementsOnTop" options
	private static final boolean DEFAULT_DRAW_ACTIVE_ELEMENTS_ON_TOP = true;

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the radius function
	private final CallbackProxy<ProxyDoubleCallback> radiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hit radius function
	private final CallbackProxy<ProxyDoubleCallback> hitRadiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover radius function
	private final CallbackProxy<ProxyDoubleCallback> hoverRadiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the rotation function
	private final CallbackProxy<ProxyDoubleCallback> rotationCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the "drawActiveElementsOnTop" function
	private final CallbackProxy<ProxyBooleanCallback> drawActiveElementsOnTopCallbackProxy = JsHelper.get().newCallbackProxy();

	// radius callback instance
	private RadiusCallback<DatasetContext> radiusCallback = null;
	// hit radius callback instance
	private RadiusCallback<DatasetContext> hitRadiusCallback = null;
	// hover radius callback instance
	private RadiusCallback<DatasetContext> hoverRadiusCallback = null;
	// rotation callback instance
	private RotationCallback<DatasetContext> rotationCallback = null;
	// "drawActiveElementsOnTop" callback instance
	private DrawActiveElementsOnTopCallback drawActiveElementsOnTopCallback = null;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DRAW_ACTIVE_ELEMENTS_ON_TOP("drawActiveElementsOnTop"),
		HOVER_RADIUS("hoverRadius"),
		HIT_RADIUS("hitRadius"),
		POINT_STYLE("pointStyle"),
		RADIUS("radius"),
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

	// instance order handler
	private final OrderHandler orderHandler;
	// instance point style handler
	private final DataPointStyleHandler pointStyleHandler;

	/**
	 * Creates a data set.<br>
	 * It uses the global options has default.
	 */
	public BubbleDataset() {
		this(Dataset.DEFAULT_HIDDEN);
	}

	/**
	 * Creates a data set.<br>
	 * It uses the global options has default.
	 * 
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	public BubbleDataset(boolean hidden) {
		this(ChartType.BUBBLE, hidden);
	}

	/**
	 * Creates the data set using a default.
	 * 
	 * @param defaultValues default options
	 */
	public BubbleDataset(IsDefaultOptions defaultValues) {
		this(defaultValues, Dataset.DEFAULT_HIDDEN);
	}

	/**
	 * Creates the data set using a default.
	 * 
	 * @param defaultValues default options
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	public BubbleDataset(IsDefaultOptions defaultValues, boolean hidden) {
		this(ChartType.BUBBLE, defaultValues, hidden);
	}

	/**
	 * Creates the data set using chart type related to the data set.
	 * 
	 * @param type chart type related to the data set
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	protected BubbleDataset(Type type, boolean hidden) {
		this(type, null, hidden);
	}

	/**
	 * Creates the data set using a default and chart type related to the data set.
	 * 
	 * @param type chart type related to the data set
	 * @param defaultValues default options
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	protected BubbleDataset(Type type, IsDefaultOptions defaultValues, boolean hidden) {
		super(type, defaultValues, hidden);
		// sets new order handler
		this.orderHandler = new OrderHandler(getNativeObject());
		// sets new point style handler
		this.pointStyleHandler = new DataPointStyleHandler(this, getNativeObject(), getDefaultValues().getElements().getPoint());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.radiusCallbackProxy
				.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(createContext(context), getRadiusCallback(), getDefaultValues().getElements().getPoint().getRadius(), ScriptableDoubleChecker.POSITIVE_OR_DEFAULT).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.hitRadiusCallbackProxy
				.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(createContext(context), getHitRadiusCallback(), getDefaultValues().getElements().getPoint().getHitRadius(), ScriptableDoubleChecker.POSITIVE_OR_DEFAULT).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.hoverRadiusCallbackProxy
				.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(createContext(context), getHoverRadiusCallback(), getDefaultValues().getElements().getPoint().getHoverRadius(), ScriptableDoubleChecker.POSITIVE_OR_DEFAULT).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.rotationCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(createContext(context), getRotationCallback(), getDefaultValues().getElements().getPoint().getRotation()).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.drawActiveElementsOnTopCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValue(createContext(context), getDrawActiveElementsOnTopCallback(), DEFAULT_DRAW_ACTIVE_ELEMENTS_ON_TOP));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#mustUseDataPoints()
	 */
	@Override
	final boolean mustUseDataPoints() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HasOrder#getOrderHandler()
	 */
	@Override
	public final OrderHandler getOrderHandler() {
		return orderHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HasDataPointStyle#getPointStyleHandler()
	 */
	@Override
	public final DataPointStyleHandler getPointStyleHandler() {
		return pointStyleHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getDefaultBackgroundColorAsString()
	 */
	@Override
	protected String getDefaultBackgroundColorAsString() {
		return getDefaultValues().getElements().getPoint().getBackgroundColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getDefaultBorderColorAsString()
	 */
	@Override
	protected String getDefaultBorderColorAsString() {
		return getDefaultValues().getElements().getPoint().getBorderColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getDefaultBorderWidth()
	 */
	@Override
	protected int getDefaultBorderWidth() {
		return getDefaultValues().getElements().getPoint().getBorderWidth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getDefaultHoverBackgroundColorAsString()
	 */
	@Override
	protected String getDefaultHoverBackgroundColorAsString() {
		return getDefaultValues().getElements().getPoint().getHoverBackgroundColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getDefaultHoverBorderColorAsString()
	 */
	@Override
	protected String getDefaultHoverBorderColorAsString() {
		return getDefaultValues().getElements().getPoint().getHoverBorderColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getDefaultHoverBorderWidth()
	 */
	@Override
	protected int getDefaultHoverBorderWidth() {
		return getDefaultValues().getElements().getPoint().getHoverBorderWidth();
	}

	/**
	 * Sets if draws the active points of a dataset over the other points of the dataset.
	 * 
	 * @param drawActiveElementsOnTop if <code>true</code>, draws the active points of a dataset over the other points of the dataset
	 */
	public void setDrawActiveElementsOnTop(boolean drawActiveElementsOnTop) {
		// resets callback
		setDrawActiveElementsOnTop((DrawActiveElementsOnTopCallback) null);
		// stores value
		setValue(Property.DRAW_ACTIVE_ELEMENTS_ON_TOP, drawActiveElementsOnTop);
	}

	/**
	 * Returns if draws the active points of a dataset over the other points of the dataset.
	 * 
	 * @return if draws the active points of a dataset over the other points of the dataset.
	 */
	public boolean isDrawActiveElementsOnTop() {
		return getValue(Property.DRAW_ACTIVE_ELEMENTS_ON_TOP, DEFAULT_DRAW_ACTIVE_ELEMENTS_ON_TOP);
	}

	/**
	 * Returns the callback, if draws the active points of a dataset over the other points of the dataset.
	 * 
	 * @return the callback, if draws the active points of a dataset over the other points of the dataset
	 */
	public DrawActiveElementsOnTopCallback getDrawActiveElementsOnTopCallback() {
		return drawActiveElementsOnTopCallback;
	}

	/**
	 * Sets the callback, if draws the active points of a dataset over the other points of the dataset.
	 * 
	 * @param drawActiveElementsOnTopCallback the callback, if draws the active points of a dataset over the other points of the dataset.
	 */
	public void setDrawActiveElementsOnTop(NativeCallback drawActiveElementsOnTopCallback) {
		// resets callback
		setDrawActiveElementsOnTop((DrawActiveElementsOnTopCallback) null);
		// stores value
		setValue(Property.DRAW_ACTIVE_ELEMENTS_ON_TOP, drawActiveElementsOnTopCallback);
	}

	/**
	 * Sets the callback, if draws the active points of a dataset over the other points of the dataset.
	 * 
	 * @param drawActiveElementsOnTopCallback the callback, if draws the active points of a dataset over the other points of the dataset.
	 */
	public void setDrawActiveElementsOnTop(DrawActiveElementsOnTopCallback drawActiveElementsOnTopCallback) {
		// sets the callback
		this.drawActiveElementsOnTopCallback = drawActiveElementsOnTopCallback;
		// checks if callback is consistent
		if (drawActiveElementsOnTopCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.DRAW_ACTIVE_ELEMENTS_ON_TOP, drawActiveElementsOnTopCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.DRAW_ACTIVE_ELEMENTS_ON_TOP);
		}
	}

	/**
	 * Sets the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @param hitRadius array of the pixel size of the non-displayed point.
	 */
	public void setHitRadius(double... hitRadius) {
		// resets callback
		setHitRadius((RadiusCallback<DatasetContext>) null);
		// stores value
		setValueOrArray(Property.HIT_RADIUS, hitRadius);
	}

	/**
	 * Returns the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @return list of the pixel size of the non-displayed point.
	 */
	public List<Double> getHitRadius() {
		ArrayDouble array = getValueOrArray(Property.HIT_RADIUS, getDefaultValues().getElements().getPoint().getHitRadius());
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the radius of the point when hovered.
	 * 
	 * @param hoverRadius array of the radius of the point when hovered.
	 */
	public void setHoverRadius(double... hoverRadius) {
		// resets callback
		setHoverRadius((RadiusCallback<DatasetContext>) null);
		// stores value
		setValueOrArray(Property.HOVER_RADIUS, hoverRadius);
	}

	/**
	 * Returns the radius of the point when hovered.
	 * 
	 * @return list of the radius of the point when hovered.
	 */
	public List<Double> getHoverRadius() {
		ArrayDouble array = getValueOrArray(Property.HOVER_RADIUS, getDefaultValues().getElements().getPoint().getHoverRadius());
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the radius of the point shape.<br>
	 * If set to 0, the point is not rendered.
	 * 
	 * @param radius array of the radius of the point shape.
	 */
	public void setRadius(double... radius) {
		// resets callback
		setRadius((RadiusCallback<DatasetContext>) null);
		// stores value
		setValueOrArray(Property.RADIUS, radius);
	}

	/**
	 * Returns the radius of the point shape.
	 * 
	 * @return list of the radius of the point shape.
	 */
	public List<Double> getRadius() {
		ArrayDouble array = getValueOrArray(Property.RADIUS, getDefaultValues().getElements().getPoint().getRadius());
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the rotation of the point in degrees.
	 * 
	 * @param rotation array of the rotation of the point in degrees.
	 */
	public void setRotation(double... rotation) {
		// resets callback
		setRotation((RotationCallback<DatasetContext>) null);
		// stores value
		setValueOrArray(Property.ROTATION, rotation);
	}

	/**
	 * Returns the rotation of the point in degrees.
	 * 
	 * @return list of the rotation of the point in degrees.
	 */
	public List<Double> getRotation() {
		ArrayDouble array = getValueOrArray(Property.ROTATION, getDefaultValues().getElements().getPoint().getRotation());
		return ArrayListHelper.list(array);
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
	 * @param radiusCallback the radius callback to set
	 */
	public void setRadius(RadiusCallback<DatasetContext> radiusCallback) {
		// sets the callback
		this.radiusCallback = radiusCallback;
		// checks if callback is consistent
		if (radiusCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.RADIUS, radiusCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.RADIUS);
		}
	}

	/**
	 * Sets the radius callback.
	 * 
	 * @param radiusCallback the radius callback to set
	 */
	public void setRadius(NativeCallback radiusCallback) {
		// resets callback
		setRadius((RadiusCallback<DatasetContext>) null);
		// stores value
		setValue(Property.RADIUS, radiusCallback);
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
	 * @param hitRadiusCallback the hit radius callback to set
	 */
	public void setHitRadius(RadiusCallback<DatasetContext> hitRadiusCallback) {
		// sets the callback
		this.hitRadiusCallback = hitRadiusCallback;
		// checks if callback is consistent
		if (hitRadiusCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.HIT_RADIUS, hitRadiusCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.HIT_RADIUS);
		}
	}

	/**
	 * Sets the hit radius callback.
	 * 
	 * @param hitRadiusCallback the hit radius callback to set
	 */
	public void setHitRadius(NativeCallback hitRadiusCallback) {
		// resets callback
		setHitRadius((RadiusCallback<DatasetContext>) null);
		// stores value
		setValue(Property.HIT_RADIUS, hitRadiusCallback);
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
	 * @param hoverRadiusCallback the hover radius callback to set
	 */
	public void setHoverRadius(RadiusCallback<DatasetContext> hoverRadiusCallback) {
		// sets the callback
		this.hoverRadiusCallback = hoverRadiusCallback;
		// checks if callback is consistent
		if (hoverRadiusCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.HOVER_RADIUS, hoverRadiusCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.HOVER_RADIUS);
		}
	}

	/**
	 * Sets the hover radius callback.
	 * 
	 * @param hoverRadiusCallback the hover radius callback to set
	 */
	public void setHoverRadius(NativeCallback hoverRadiusCallback) {
		// resets callback
		setHoverRadius((RadiusCallback<DatasetContext>) null);
		// stores value
		setValue(Property.HOVER_RADIUS, hoverRadiusCallback);
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
	 * @param rotationCallback the rotation callback to set
	 */
	public void setRotation(RotationCallback<DatasetContext> rotationCallback) {
		// sets the callback
		this.rotationCallback = rotationCallback;
		// checks if callback is consistent
		if (rotationCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.ROTATION, rotationCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.ROTATION);
		}
	}

	/**
	 * Sets the rotation callback.
	 * 
	 * @param rotationCallback the rotation callback to set
	 */
	public void setRotation(NativeCallback rotationCallback) {
		// resets callback
		setRotation((RotationCallback<DatasetContext>) null);
		// stores value
		setValue(Property.ROTATION, rotationCallback);
	}

}