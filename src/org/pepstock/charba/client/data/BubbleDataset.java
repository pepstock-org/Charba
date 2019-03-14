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

import java.util.List;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.callbacks.PointStyleCallback;
import org.pepstock.charba.client.callbacks.RadiusCallback;
import org.pepstock.charba.client.callbacks.RotationCallback;
import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.ArrayDoubleList;
import org.pepstock.charba.client.commons.ArrayEnumList;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.enums.DataType;
import org.pepstock.charba.client.enums.PointStyle;

import jsinterop.annotations.JsFunction;

/**
 * The chart allows a number of properties to be specified for each dataset. These are used to set display properties for a
 * specific dataset.<br>
 * The location of the bubble is determined by the first two dimensions and the corresponding horizontal and vertical axes.<br>
 * The third dimension is represented by the size of the individual bubbles.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class BubbleDataset extends HovingDataset implements HasDataPoints {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback called to provide the radius property.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyRadiusCallback {

		/**
		 * Method of function to be called to provide the radius property.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return radius property value.
		 */
		double call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the hit radius property.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyHitRadiusCallback {

		/**
		 * Method of function to be called to provide the hit radius property.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return hit radius property value.
		 */
		double call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the hover radius property.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyHoverRadiusCallback {

		/**
		 * Method of function to be called to provide the hover radius property.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return hover radius property value.
		 */
		double call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the rotation property.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyRotationCallback {

		/**
		 * Method of function to be called to provide the rotation property.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return rotation property value.
		 */
		double call(Object contextFunction, Context context);
	}

	/**
	 * Java script FUNCTION callback called to provide the point style.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyPointStyleCallback {

		/**
		 * Method of function to be called to provide the point style.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return point style property value.
		 */
		Object call(Object contextFunction, Context context);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the radius function
	private final CallbackProxy<ProxyRadiusCallback> radiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hit radius function
	private final CallbackProxy<ProxyHitRadiusCallback> hitRadiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover radius function
	private final CallbackProxy<ProxyHoverRadiusCallback> hoverRadiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the rotation function
	private final CallbackProxy<ProxyRotationCallback> rotationCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the point style function
	private final CallbackProxy<ProxyPointStyleCallback> pointStyleCallbackProxy = JsHelper.get().newCallbackProxy();

	// radius callback instance
	private RadiusCallback radiusCallback = null;
	// hit radius callback instance
	private RadiusCallback hitRadiusCallback = null;
	// hover radius callback instance
	private RadiusCallback hoverRadiusCallback = null;
	// rotation callback instance
	private RotationCallback rotationCallback = null;
	// point style callback instance
	private PointStyleCallback<PointStyle> pointStyleCallback = null;

	// exception message when it's not using data points
	private static final String DATA_USAGE_MESSAGE = "Use datapoints instead of data for bubble chart";
	// data point factory
	private final DataPointListFactory factory = new DataPointListFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		hoverRadius,
		hitRadius,
		pointStyle,
		radius,
		rotation
	}

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 */
	public BubbleDataset() {
		this(null);
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 */
	public BubbleDataset(IsDefaultOptions defaultValues) {
		super(defaultValues);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		radiusCallbackProxy.setCallback(new ProxyRadiusCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.BubbleDataset.ProxyRadiusCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.data.Context)
			 */
			@Override
			public double call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && radiusCallback != null) {
					// calls callback
					return radiusCallback.radius(chart, context);
				}
				// default result
				return getDefaultValues().getElements().getPoint().getRadius();
			}
		});
		hitRadiusCallbackProxy.setCallback(new ProxyHitRadiusCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.BubbleDataset.ProxyHitRadiusCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.data.Context)
			 */
			@Override
			public double call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && hitRadiusCallback != null) {
					// calls callback
					return hitRadiusCallback.radius(chart, context);
				}
				// default result
				return getDefaultValues().getElements().getPoint().getHitRadius();
			}
		});
		hoverRadiusCallbackProxy.setCallback(new ProxyHoverRadiusCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.BubbleDataset.ProxyHoverRadiusCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.data.Context)
			 */
			@Override
			public double call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && hoverRadiusCallback != null) {
					// calls callback
					return hoverRadiusCallback.radius(chart, context);
				}
				// default result
				return getDefaultValues().getElements().getPoint().getHoverRadius();
			}
		});
		rotationCallbackProxy.setCallback(new ProxyRotationCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.BubbleDataset.ProxyRotationCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.data.Context)
			 */
			@Override
			public double call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && rotationCallback != null) {
					// calls callback
					return rotationCallback.rotation(chart, context);
				}
				// default result
				return getDefaultValues().getElements().getPoint().getRotation();
			}
		});
		pointStyleCallbackProxy.setCallback(new ProxyPointStyleCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.BubbleDataset.ProxyPointStyleCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.data.Context)
			 */
			@Override
			public Object call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && pointStyleCallback != null) {
					// calls callback
					PointStyle result = pointStyleCallback.style(chart, context);
					// checks result
					if (result != null) {
						return result.name();
					}
				}
				// default result
				return getDefaultValues().getElements().getPoint().getPointStyle().name();
			}
		});
	}

	/**
	 * Sets the style of the point.
	 * 
	 * @param pointStyle array of the style of the point.
	 */
	public void setPointStyle(PointStyle... pointStyle) {
		setValueOrArray(Property.pointStyle, pointStyle);
	}

	/**
	 * Returns the style of the point.
	 * 
	 * @return list of the style of the point.
	 */
	public List<PointStyle> getPointStyle() {
		// checks if the callback has not been set
		if (!ObjectType.Function.equals(type(Property.pointStyle))) {
			// returns the array
			ArrayString array = getValueOrArray(Property.pointStyle, getDefaultValues().getElements().getPoint().getPointStyle());
			return ArrayListHelper.list(PointStyle.class, array);
		} 
		// if here, is a callback
		// then returns an empty list
		return new ArrayEnumList<PointStyle>(PointStyle.class);
	}

	/**
	 * Sets the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @param hitRadius array of the pixel size of the non-displayed point.
	 */
	public void setHitRadius(double... hitRadius) {
		setValueOrArray(Property.hitRadius, hitRadius);
	}

	/**
	 * Returns the pixel size of the non-displayed point that reacts to mouse events.
	 * 
	 * @return list of the pixel size of the non-displayed point.
	 */
	public List<Double> getHitRadius() {
		// checks if the callback has not been set
		if (!ObjectType.Function.equals(type(Property.hitRadius))) {
			// returns the array
			ArrayDouble array = getValueOrArray(Property.hitRadius, getDefaultValues().getElements().getPoint().getHitRadius());
			return ArrayListHelper.list(array);
		} 
		// if here, is a callback
		// then returns an empty list
		return new ArrayDoubleList();
	}

	/**
	 * Sets the radius of the point when hovered.
	 * 
	 * @param hoverRadius array of the radius of the point when hovered.
	 */
	public void setHoverRadius(double... hoverRadius) {
		setValueOrArray(Property.hoverRadius, hoverRadius);
	}

	/**
	 * Returns the radius of the point when hovered.
	 * 
	 * @return list of the radius of the point when hovered.
	 */
	public List<Double> getHoverRadius() {
		// checks if the callback has not been set
		if (!ObjectType.Function.equals(type(Property.hoverRadius))) {
			// returns the array
			ArrayDouble array = getValueOrArray(Property.hoverRadius, getDefaultValues().getElements().getPoint().getHoverRadius());
			return ArrayListHelper.list(array);
		} 
		// if here, is a callback
		// then returns an empty list
		return new ArrayDoubleList();
	}

	/**
	 * Sets the radius of the point shape. If set to 0, the point is not rendered.
	 * 
	 * @param radius array of the radius of the point shape.
	 */
	public void setRadius(double... radius) {
		setValueOrArray(Property.radius, radius);
	}

	/**
	 * Returns the radius of the point shape.
	 * 
	 * @return list of the radius of the point shape.
	 */
	public List<Double> getRadius() {
		// checks if the callback has not been set
		if (!ObjectType.Function.equals(type(Property.radius))) {
			// returns the array
			ArrayDouble array = getValueOrArray(Property.radius, getDefaultValues().getElements().getPoint().getRadius());
			return ArrayListHelper.list(array);
		} 
		// if here, is a callback
		// then returns an empty list
		return new ArrayDoubleList();
	}

	/**
	 * Sets the rotation of the point in degrees.
	 * 
	 * @param rotation array of the rotation of the point in degrees.
	 */
	public void setRotation(double... rotation) {
		setValueOrArray(Property.rotation, rotation);
	}

	/**
	 * Returns the rotation of the point in degrees.
	 * 
	 * @return list of the rotation of the point in degrees.
	 */
	public List<Double> getRotation() {
		// checks if the callback has not been set
		if (!ObjectType.Function.equals(type(Property.rotation))) {
			// returns the array
			ArrayDouble array = getValueOrArray(Property.rotation, getDefaultValues().getElements().getPoint().getRotation());
			return ArrayListHelper.list(array);
		} 
		// if here, is a callback
		// then returns an empty list
		return new ArrayDoubleList();
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of data points.
	 * 
	 * @param datapoints an array of data points
	 */
	@Override
	public void setDataPoints(DataPoint... datapoints) {
		setArrayValue(Dataset.Property.data, ArrayObject.fromOrNull(datapoints));
		// sets data type checking if the key exists
		setValue(Dataset.Property._charbaDataType, has(Dataset.Property.data) ? DataType.points : DataType.unknown);
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of data points.
	 * 
	 * @param datapoints a list of data points
	 */
	@Override
	public void setDataPoints(List<DataPoint> datapoints) {
		setArrayValue(Dataset.Property.data, ArrayObject.fromOrNull(datapoints));
		// sets data type checking if the key exists
		setValue(Dataset.Property._charbaDataType, has(Dataset.Property.data) ? DataType.points : DataType.unknown);
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of data points
	 * 
	 * @return a list of data points or an empty list of data points if the data type is not {@link DataType#points}.
	 */
	@Override
	public List<DataPoint> getDataPoints() {
		return getDataPoints(false);
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of data points
	 * 
	 * @param binding if <code>true</code> binds the new array list into container
	 * @return a list of data points or an empty list of data points if the data type is not {@link DataType#points}.
	 */
	@Override
	public List<DataPoint> getDataPoints(boolean binding) {
		return getDataPoints(factory, binding);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#setData(double[])
	 */
	@Override
	public void setData(double... values) {
		throw new UnsupportedOperationException(DATA_USAGE_MESSAGE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#setData(java.util.List)
	 */
	@Override
	public void setData(List<Double> values) {
		throw new UnsupportedOperationException(DATA_USAGE_MESSAGE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getData()
	 */
	@Override
	public List<Double> getData() {
		throw new UnsupportedOperationException(DATA_USAGE_MESSAGE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getData(boolean)
	 */
	@Override
	public List<Double> getData(boolean binding) {
		throw new UnsupportedOperationException(DATA_USAGE_MESSAGE);
	}
	
	/**
	 * Returns the radius callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the radius callback, if set, otherwise <code>null</code>.
	 */
	public RadiusCallback getRadiusCallback() {
		return radiusCallback;
	}

	/**
	 * Sets the radius callback.
	 * 
	 * @param radiusCallback the radius callback to set
	 */
	public void setRadius(RadiusCallback radiusCallback) {
		// sets the callback
		this.radiusCallback = radiusCallback;
		// checks if callback is consistent
		if (radiusCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.radius, radiusCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.radius);
		}
	}

	/**
	 * Returns the hit radius callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the hit radius callback, if set, otherwise <code>null</code>.
	 */
	public RadiusCallback getHitRadiusCallback() {
		return hitRadiusCallback;
	}

	/**
	 * Sets the hit radius callback.
	 * 
	 * @param hitRadiusCallback the hit radius callback to set
	 */
	public void setHitRadius(RadiusCallback hitRadiusCallback) {
		// sets the callback
		this.hitRadiusCallback = hitRadiusCallback;
		// checks if callback is consistent
		if (hitRadiusCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.hitRadius, hitRadiusCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.hitRadius);
		}
	}

	/**
	 * Returns the hover radius callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the hover radius callback, if set, otherwise <code>null</code>.
	 */
	public RadiusCallback getHoverRadiusCallback() {
		return hoverRadiusCallback;
	}

	/**
	 * Sets the hover radius callback.
	 * 
	 * @param hoverRadiusCallback the hover radius callback to set
	 */
	public void setHoverRadius(RadiusCallback hoverRadiusCallback) {
		// sets the callback
		this.hoverRadiusCallback = hoverRadiusCallback;
		// checks if callback is consistent
		if (hoverRadiusCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.hoverRadius, hoverRadiusCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.hoverRadius);
		}
	}

	/**
	 * Returns the rotation callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the rotation callback, if set, otherwise <code>null</code>.
	 */
	public RotationCallback getRotationCallback() {
		return rotationCallback;
	}

	/**
	 * Sets the rotation callback.
	 * 
	 * @param rotationCallback the rotation callback to set
	 */
	public void setRotation(RotationCallback rotationCallback) {
		// sets the callback
		this.rotationCallback = rotationCallback;
		// checks if callback is consistent
		if (rotationCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.rotation, rotationCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.rotation);
		}
	}

	/**
	 * Returns the point style callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the point style callback, if set, otherwise <code>null</code>.
	 */
	public PointStyleCallback<?> getPointStyleCallback() {
		return pointStyleCallback;
	}

	/**
	 * Sets the point style callback.
	 * 
	 * @param pointStyleCallback the point style callback.
	 */
	public void setPointStyle(PointStyleCallback<PointStyle> pointStyleCallback) {
		// sets the callback
		this.pointStyleCallback = pointStyleCallback;
		// checks if callback is consistent
		if (pointStyleCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.pointStyle, pointStyleCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.pointStyle);
		}
	}

}