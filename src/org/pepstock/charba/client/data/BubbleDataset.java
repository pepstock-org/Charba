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
package org.pepstock.charba.client.data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.PointStyleCallback;
import org.pepstock.charba.client.callbacks.RadiusCallback;
import org.pepstock.charba.client.callbacks.RotationCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.enums.PointStyle;

/**
 * The chart allows a number of properties to be specified for each dataset. These are used to set display properties for a specific dataset.<br>
 * The location of the bubble is determined by the first two dimensions and the corresponding horizontal and vertical axes.<br>
 * The third dimension is represented by the size of the individual bubbles.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class BubbleDataset extends HovingDataset implements HasDataPoints, HasOrder {

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
	// callback proxy to invoke the point style function
	private final CallbackProxy<ProxyObjectCallback> pointStyleCallbackProxy = JsHelper.get().newCallbackProxy();

	// radius callback instance
	private RadiusCallback<DatasetContext> radiusCallback = null;
	// hit radius callback instance
	private RadiusCallback<DatasetContext> hitRadiusCallback = null;
	// hover radius callback instance
	private RadiusCallback<DatasetContext> hoverRadiusCallback = null;
	// rotation callback instance
	private RotationCallback<DatasetContext> rotationCallback = null;
	// point style callback instance
	private PointStyleCallback pointStyleCallback = null;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
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

	// instance or order handler
	private final OrderHandler orderHandler;

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 */
	public BubbleDataset() {
		this(Dataset.DEFAULT_HIDDEN);
	}

	/**
	 * Creates a dataset.<br>
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
	 * Creates the data set using chart type related to the dataset.
	 * 
	 * @param type chart type related to the dataset
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	protected BubbleDataset(Type type, boolean hidden) {
		this(type, null, hidden);
	}

	/**
	 * Creates the data set using a default and chart type related to the dataset.
	 * 
	 * @param type chart type related to the dataset
	 * @param defaultValues default options
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	protected BubbleDataset(Type type, IsDefaultOptions defaultValues, boolean hidden) {
		super(type, defaultValues, hidden);
		// sets new order handler
		orderHandler = new OrderHandler(getNativeObject());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		radiusCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new DatasetContext(new DataEnvelop<>(context)), radiusCallback, getDefaultValues().getElements().getPoint().getRadius()).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		hitRadiusCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new DatasetContext(new DataEnvelop<>(context)), hitRadiusCallback, getDefaultValues().getElements().getPoint().getHitRadius()).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		hoverRadiusCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new DatasetContext(new DataEnvelop<>(context)), hoverRadiusCallback, getDefaultValues().getElements().getPoint().getHoverRadius()).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		rotationCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new DatasetContext(new DataEnvelop<>(context)), rotationCallback, getDefaultValues().getElements().getPoint().getRotation()).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		pointStyleCallbackProxy.setCallback((contextFunction, context) -> onPointStyle(new DatasetContext(new DataEnvelop<>(context))));
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
	public OrderHandler getOrderHandler() {
		return orderHandler;
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
	 * Sets the style of the point.
	 * 
	 * @param pointStyle array of the style of the point.
	 */
	public void setPointStyle(PointStyle... pointStyle) {
		// resets callback
		setPointStyle((PointStyleCallback) null);
		// stores value
		setValueOrArray(Property.POINT_STYLE, pointStyle);
	}

	/**
	 * Returns the style of the point.
	 * 
	 * @return list of the style of the point.
	 */
	public List<PointStyle> getPointStyle() {
		// checks if the callback has not been set
		if (getPointStyleCallback() == null) {
			// returns the array
			ArrayString array = getValueOrArray(Property.POINT_STYLE, getDefaultValues().getElements().getPoint().getPointStyle());
			return ArrayListHelper.list(PointStyle.values(), array);
		}
		// if here, is a callback
		// then returns a list with all values
		return Arrays.asList(PointStyle.values());
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
		// checks if the callback has not been set
		if (getHitRadiusCallback() == null) {
			// returns the array
			ArrayDouble array = getValueOrArray(Property.HIT_RADIUS, getDefaultValues().getElements().getPoint().getHitRadius());
			return ArrayListHelper.list(array);
		}
		// if here, is a callback
		// then returns an empty list
		return Collections.emptyList();
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
		// checks if the callback has not been set
		if (getHoverRadiusCallback() == null) {
			// returns the array
			ArrayDouble array = getValueOrArray(Property.HOVER_RADIUS, getDefaultValues().getElements().getPoint().getHoverRadius());
			return ArrayListHelper.list(array);
		}
		// if here, is a callback
		// then returns an empty list
		return Collections.emptyList();
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
		// checks if the callback has not been set
		if (getRadiusCallback() == null) {
			// returns the array
			ArrayDouble array = getValueOrArray(Property.RADIUS, getDefaultValues().getElements().getPoint().getRadius());
			return ArrayListHelper.list(array);
		}
		// if here, is a callback
		// then returns an empty list
		return Collections.emptyList();
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
		// checks if the callback has not been set
		if (getRotationCallback() == null) {
			// returns the array
			ArrayDouble array = getValueOrArray(Property.ROTATION, getDefaultValues().getElements().getPoint().getRotation());
			return ArrayListHelper.list(array);
		}
		// if here, is a callback
		// then returns an empty list
		return Collections.emptyList();
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
	 * Returns the point style callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the point style callback, if set, otherwise <code>null</code>.
	 */
	public PointStyleCallback getPointStyleCallback() {
		return pointStyleCallback;
	}

	/**
	 * Sets the point style callback.
	 * 
	 * @param pointStyleCallback the point style callback.
	 */
	public void setPointStyle(PointStyleCallback pointStyleCallback) {
		// sets the callback
		this.pointStyleCallback = pointStyleCallback;
		// checks if callback is consistent
		if (pointStyleCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.POINT_STYLE, pointStyleCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.POINT_STYLE);
		}
	}

	/**
	 * Returns a {@link PointStyle} when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @return a object property value, as {@link PointStyle}
	 */
	private String onPointStyle(DatasetContext context) {
		// gets value
		Object result = ScriptableUtils.getOptionValue(context, pointStyleCallback);
		// checks result
		if (result instanceof PointStyle) {
			// is point style instance
			PointStyle style = (PointStyle) result;
			return style.value();
		}
		// default result
		return getDefaultValues().getElements().getPoint().getPointStyle().value();
	}

}