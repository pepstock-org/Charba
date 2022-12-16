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
package org.pepstock.charba.client.matrix;

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.callbacks.BarBorderWidthCallback;
import org.pepstock.charba.client.callbacks.BorderRadiusCallback;
import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableDoubleChecker;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyNativeObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayObjectContainerList;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.data.BarBorderRadius;
import org.pepstock.charba.client.data.BarBorderWidth;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.data.HoverFlexDataset;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.matrix.callbacks.SizeCallback;
import org.pepstock.charba.client.matrix.enums.Anchor;

/**
 * The matrix data set allows to specify the values for showing magnitude of a phenomenon as color in two dimensions.<br>
 * The variation in color may be by hue or intensity, giving obvious visual cues to the reader about how the phenomenon is clustered or varies over space.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class MatrixDataset extends HoverFlexDataset {

	/**
	 * Factory to create {@link MatrixDataPoint}s.
	 */
	public static final DataPointFactory DATAPOINTS_FACTORY = new DataPointFactory();
	/**
	 * Default border width, <b>{@value}</b>.
	 */
	public static final int DEFAULT_BORDER_WIDTH = 0;
	/**
	 * Default border radius, <b>{@value}</b>.
	 */
	public static final int DEFAULT_BORDER_RADIUS = 0;
	/**
	 * Default width, in pixels, <b>{@value}</b>.
	 */
	public static final double DEFAULT_WIDTH = 20;
	/**
	 * Default height, in pixels, <b>{@value}</b>.
	 */
	public static final double DEFAULT_HEIGHT = 20;

	// exception string message for setting data
	private static final String INVALID_SET_DATA_CALL = "'setData' method is not invokable by a matrix chart. Use 'setDataPoints' method";
	// exception string message for getting data
	private static final String INVALID_GET_DATA_CALL = "'getData' method is not invokable by a matrix chart. Use 'getDataPoints' method";

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the width function
	private final CallbackProxy<ProxyDoubleCallback> widthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the height function
	private final CallbackProxy<ProxyDoubleCallback> heightCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border radius function
	private final CallbackProxy<ProxyNativeObjectCallback> borderRadiusCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border width function
	private final CallbackProxy<ProxyNativeObjectCallback> borderWidthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover border width function
	private final CallbackProxy<ProxyNativeObjectCallback> hoverBorderWidthCallbackProxy = JsHelper.get().newCallbackProxy();

	// ---------------------------
	// -- USERS CALLBACKS ---
	// ---------------------------
	// user callback implementation for width
	private SizeCallback widthCallback = null;
	// user callback implementation for height
	private SizeCallback heightCallback = null;
	// user callback implementation for border radius
	private BorderRadiusCallback<DatasetContext> borderRadiusCallback = null;
	// user callback implementation for border width
	private BarBorderWidthCallback borderWidthCallback = null;
	// user callback implementation for hover border width
	private BarBorderWidthCallback hoverBorderWidthCallback = null;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ANCHOR_X("anchorX"),
		ANCHOR_Y("anchorY"),
		BORDER_RADIUS("borderRadius"),
		WIDTH("width"),
		HEIGHT("height");

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

	/**
	 * Creates a data set.<br>
	 * It uses the global options has default.
	 */
	public MatrixDataset() {
		this(Defaults.get().getGlobal());
	}

	/**
	 * Creates a data set setting the defaults value.
	 * 
	 * @param defaultValues default options
	 */
	public MatrixDataset(IsDefaultOptions defaultValues) {
		this(MatrixChart.CONTROLLER_TYPE, defaultValues);
	}

	/**
	 * Creates a data set by defaults value and the controller type.
	 * 
	 * @param type controller type related to the data set
	 * @param defaultValues default options
	 */
	MatrixDataset(ControllerType type, IsDefaultOptions defaultValues) {
		super(type, defaultValues, Dataset.DEFAULT_HIDDEN);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.widthCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(createContext(context), getWidthCallback(), DEFAULT_WIDTH, ScriptableDoubleChecker.POSITIVE_OR_ZERO).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.heightCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(createContext(context), getHeightCallback(), DEFAULT_HEIGHT, ScriptableDoubleChecker.POSITIVE_OR_ZERO).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.borderRadiusCallbackProxy.setCallback(context -> onBorderRadius(createContext(context)));
		// sets function to proxy callback in order to invoke the java interface
		this.borderWidthCallbackProxy.setCallback(context -> onBorderWidth(createContext(context), getBorderWidthCallback(), DEFAULT_BORDER_WIDTH));
		// sets function to proxy callback in order to invoke the java interface
		this.hoverBorderWidthCallbackProxy.setCallback(context -> onBorderWidth(createContext(context), getHoverBorderWidthCallback(), DEFAULT_BORDER_WIDTH));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getDefaultBorderWidth()
	 */
	@Override
	protected int getDefaultBorderWidth() {
		return DEFAULT_BORDER_WIDTH;
	}

	/**
	 * Sets the data property of a data set for a chart is specified as an array of data points.
	 * 
	 * @param datapoints an array of data points
	 */
	public void setDataPoints(MatrixDataPoint... datapoints) {
		setArrayValue(CommonProperty.DATA, ArrayObject.fromOrNull(datapoints));
	}

	/**
	 * Sets the data property of a data set for a chart is specified as an array of data points.
	 * 
	 * @param datapoints a list of data points
	 */
	public void setDataPoints(List<MatrixDataPoint> datapoints) {
		setArrayValue(CommonProperty.DATA, ArrayObject.fromOrNull(datapoints));
	}

	/**
	 * Returns the matrix data property of a dataset for a chart is specified as an array of matrix data points
	 * 
	 * @return a list of matrix data points or an empty list of data points.
	 */
	public List<MatrixDataPoint> getDataPoints() {
		return getDataPoints(false);
	}

	/**
	 * Returns the matrix data property of a dataset for a chart is specified as an array of matrix data points
	 * 
	 * @param binding if <code>true</code> binds the new array list in the container
	 * @return a list of matrix data points or an empty list of data points
	 */
	public List<MatrixDataPoint> getDataPoints(boolean binding) {
		// checks if is a numbers data type
		if (has(CommonProperty.DATA)) {
			// gets array
			ArrayObject array = getArrayValue(CommonProperty.DATA);
			// returns points
			return ArrayListHelper.list(array, DATAPOINTS_FACTORY);
		}
		// checks if wants to bind the array
		if (binding) {
			ArrayObjectContainerList<MatrixDataPoint> result = new ArrayObjectContainerList<>();
			// set value
			setArrayValue(CommonProperty.DATA, ArrayObject.fromOrEmpty(result));
			// returns list
			return result;
		}
		// returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Sets the height of matrix element in pixels.
	 * 
	 * @param height the height of matrix element in pixels
	 */
	public void setHeight(double height) {
		// resets callback
		setHeight((SizeCallback) null);
		// stores the value
		setValue(Property.HEIGHT, Checker.positiveOrZero(height));
	}

	/**
	 * Returns the height of matrix element in pixels.
	 * 
	 * @return the height of matrix element in pixels
	 */
	public double getHeight() {
		return getValue(Property.HEIGHT, DEFAULT_HEIGHT);
	}

	/**
	 * Sets the width of matrix element in pixels.
	 * 
	 * @param width the width of matrix element in pixels
	 */
	public void setWidth(double width) {
		// resets callback
		setWidth((SizeCallback) null);
		// stores the value
		setValue(Property.WIDTH, Checker.positiveOrZero(width));
	}

	/**
	 * Returns the width of matrix element in pixels.
	 * 
	 * @return the width of matrix element in pixels.
	 */
	public double getWidth() {
		return getValue(Property.WIDTH, DEFAULT_WIDTH);
	}

	/**
	 * Sets the anchor point on X orientation of matrix element.
	 * 
	 * @param anchor the anchor point on X orientation of matrix element
	 */
	public void setXAnchor(Anchor anchor) {
		// checks if the values of anchor are correct for x orientation
		if (!Anchor.BOTTOM.equals(anchor) && !Anchor.TOP.equals(anchor)) {
			setValue(Property.ANCHOR_X, anchor);
		}
	}

	/**
	 * Returns the anchor point on X orientation of matrix element.
	 * 
	 * @return the anchor point on X orientation of matrix element
	 */
	public Anchor getXAnchor() {
		return getValue(Property.ANCHOR_X, Anchor.values(), Anchor.CENTER);
	}

	/**
	 * Sets the anchor point on Y orientation of matrix element.
	 * 
	 * @param anchor the anchor point on Y orientation of matrix element
	 */
	public void setYAnchor(Anchor anchor) {
		// checks if the values of anchor are correct for x orientation
		if (!Anchor.LEFT.equals(anchor) && !Anchor.RIGHT.equals(anchor)) {
			setValue(Property.ANCHOR_Y, anchor);
		}
	}

	/**
	 * Returns the anchor point on Y orientation of matrix element.
	 * 
	 * @return the anchor point on Y orientation of matrix element
	 */
	public Anchor getYAnchor() {
		return getValue(Property.ANCHOR_Y, Anchor.values(), Anchor.CENTER);
	}

	/**
	 * Sets the border radius (in pixels).
	 * 
	 * @param borderRadius the border radius (in pixels).
	 */
	public void setBorderRadius(int borderRadius) {
		// resets callback
		setBorderRadius((BorderRadiusCallback<DatasetContext>) null);
		// stores the value
		setValue(Property.BORDER_RADIUS, Checker.positiveOrZero(borderRadius));
	}

	/**
	 * Sets the border radius (in pixels).
	 * 
	 * @param borderRadius the border radius (in pixels).
	 */
	public void setBorderRadius(BarBorderRadius borderRadius) {
		// resets callback
		setBorderRadius((BorderRadiusCallback<DatasetContext>) null);
		// stores the value
		setValue(Property.BORDER_RADIUS, borderRadius);
	}

	/**
	 * Returns the border radius (in pixels).
	 * 
	 * @return the border radius (in pixels).
	 */
	public int getBorderRadius() {
		// checks if was stored as number
		if (isType(Property.BORDER_RADIUS, ObjectType.NUMBER)) {
			return getValue(Property.BORDER_RADIUS, DEFAULT_BORDER_RADIUS);
		} else if (isType(Property.BORDER_RADIUS, ObjectType.OBJECT)) {
			// if here, the property is a object
			BarBorderRadius object = getBorderRadiusAsObject();
			// checks if there is the same value
			if (object != null && object.areValuesEquals()) {
				// the returns the same value
				// in whatever property
				return object.getTopLeft();
			}
		}
		// if here, the property is missing
		// then returns default
		return DEFAULT_BORDER_RADIUS;
	}

	/**
	 * Returns the border radius (in pixels).
	 * 
	 * @return the border radius (in pixels).
	 */
	public BarBorderRadius getBorderRadiusAsObject() {
		// checks if was stored as object
		if (isType(Property.BORDER_RADIUS, ObjectType.OBJECT)) {
			return BarBorderRadius.FACTORY.create(getValue(Property.BORDER_RADIUS));
		} else if (isType(Property.BORDER_RADIUS, ObjectType.NUMBER)) {
			// if here, the property is a number
			// then returns new border radius object
			return new BarBorderRadius(getBorderRadius());
		}
		// if here, the property is missing
		// then returns null
		return null;
	}

	/**
	 * Sets the border width (in pixels).
	 * 
	 * @param borderWidth the border width (in pixels).
	 */
	public void setBorderWidth(BarBorderWidth borderWidth) {
		// resets callback
		setBorderWidth((BarBorderWidthCallback) null);
		// stores the value
		setValue(Dataset.CommonProperty.BORDER_WIDTH, borderWidth);
	}

	/**
	 * Returns the border width (in pixels).
	 * 
	 * @return the border width (in pixels).
	 */
	public BarBorderWidth getBorderWidthAsObject() {
		// checks if was stored as object
		if (isType(Dataset.CommonProperty.BORDER_WIDTH, ObjectType.OBJECT)) {
			return BarBorderWidth.FACTORY.create(getValue(Dataset.CommonProperty.BORDER_WIDTH));
		} else if (isType(Dataset.CommonProperty.BORDER_WIDTH, ObjectType.NUMBER)) {
			// if here, the property is a number
			// then returns new border width object
			return new BarBorderWidth(getValue(Dataset.CommonProperty.BORDER_WIDTH, DEFAULT_BORDER_WIDTH));
		}
		// if here, the property is missing
		// then returns null
		return null;
	}

	/**
	 * Sets the border width (in pixels), when hovered.
	 * 
	 * @param hoverBorderWidth the border width (in pixels), when hovered.
	 */
	public void setHoverBorderWidth(BarBorderWidth hoverBorderWidth) {
		// resets callback
		setHoverBorderWidth((BarBorderWidthCallback) null);
		// stores the value
		setValue(Dataset.CommonProperty.HOVER_BORDER_WIDTH, hoverBorderWidth);
	}

	/**
	 * Returns the border width (in pixels), when hovered.
	 * 
	 * @return the border width (in pixels), when hovered.
	 */
	public BarBorderWidth getHoverBorderWidthAsObject() {
		// checks if was stored as object
		if (isType(Dataset.CommonProperty.HOVER_BORDER_WIDTH, ObjectType.OBJECT)) {
			return BarBorderWidth.FACTORY.create(getValue(Dataset.CommonProperty.HOVER_BORDER_WIDTH));
		} else if (isType(Dataset.CommonProperty.HOVER_BORDER_WIDTH, ObjectType.NUMBER)) {
			// if here, the property is a number
			// then returns new border width object
			return new BarBorderWidth(getValue(Dataset.CommonProperty.HOVER_BORDER_WIDTH, DEFAULT_BORDER_WIDTH));
		}
		// if here, the property is missing
		// then returns null
		return null;
	}

	// ---------------------------
	// CALLBACKS
	// ---------------------------

	/**
	 * Sets the width callback to set the width of matrix element in pixels.
	 * 
	 * @param widthCallback the width callback
	 */
	public void setWidth(SizeCallback widthCallback) {
		// sets the callback
		this.widthCallback = widthCallback;
		// checks if consistent
		if (widthCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.WIDTH, widthCallbackProxy.getProxy());
		} else {
			// otherwise removes the properties from java script object
			remove(Property.WIDTH);
		}
	}

	/**
	 * Returns the width callback to set the width of matrix element in pixels.
	 * 
	 * @return the width callback
	 */
	public SizeCallback getWidthCallback() {
		return widthCallback;
	}

	/**
	 * Sets the width callback to set the width of matrix element in pixels.
	 * 
	 * @param widthCallback the width callback
	 */
	public void setWidth(NativeCallback widthCallback) {
		// resets callback
		setWidth((SizeCallback) null);
		// stores value
		setValue(Property.WIDTH, widthCallback);
	}

	/**
	 * Sets the height callback to set the height of matrix element in pixels.
	 * 
	 * @param heightCallback the height callback
	 */
	public void setHeight(SizeCallback heightCallback) {
		// sets the callback
		this.heightCallback = heightCallback;
		// checks if consistent
		if (heightCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.HEIGHT, heightCallbackProxy.getProxy());
		} else {
			// otherwise removes the properties from java script object
			remove(Property.HEIGHT);
		}
	}

	/**
	 * Returns the height callback to set the height of matrix element in pixels.
	 * 
	 * @return the height callback
	 */
	public SizeCallback getHeightCallback() {
		return heightCallback;
	}

	/**
	 * Sets the height callback to set the height of matrix element in pixels.
	 * 
	 * @param heightCallback the height callback
	 */
	public void setHeight(NativeCallback heightCallback) {
		// resets callback
		setHeight((SizeCallback) null);
		// stores value
		setValue(Property.HEIGHT, heightCallback);
	}

	/**
	 * Sets the callback to set the border radius (in pixels).
	 * 
	 * @param borderRadiusCallback the border radius callback
	 */
	public void setBorderRadius(BorderRadiusCallback<DatasetContext> borderRadiusCallback) {
		// sets the callback
		this.borderRadiusCallback = borderRadiusCallback;
		// checks if consistent
		if (borderRadiusCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.BORDER_RADIUS, borderRadiusCallbackProxy.getProxy());
		} else {
			// otherwise removes the properties from java script object
			remove(Property.BORDER_RADIUS);
		}
	}

	/**
	 * Returns the callback to set the border radius (in pixels).
	 * 
	 * @return the border radius callback
	 */
	public BorderRadiusCallback<DatasetContext> getBorderRadiusCallback() {
		return borderRadiusCallback;
	}

	/**
	 * Sets the callback to set the border radius (in pixels).
	 * 
	 * @param borderRadiusCallback the border radius callback
	 */
	public void setBorderRadius(NativeCallback borderRadiusCallback) {
		// resets callback
		setBorderRadius((BorderRadiusCallback<DatasetContext>) null);
		// stores value
		setValue(Property.BORDER_RADIUS, borderRadiusCallback);
	}

	/**
	 * Sets the callback to set the border width (in pixels).
	 * 
	 * @param borderWidthCallback the border width callback
	 */
	public void setBorderWidth(BarBorderWidthCallback borderWidthCallback) {
		// sets the callback
		this.borderWidthCallback = borderWidthCallback;
		// checks if consistent
		if (borderWidthCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Dataset.CommonProperty.BORDER_WIDTH, borderWidthCallbackProxy.getProxy());
		} else {
			// otherwise removes the properties from java script object
			remove(Dataset.CommonProperty.BORDER_WIDTH);
		}
	}

	/**
	 * Returns the callback to set the border width (in pixels).
	 * 
	 * @return the border width callback
	 */
	public BarBorderWidthCallback getBorderWidthCallback() {
		return borderWidthCallback;
	}

	/**
	 * Sets the callback to set the border width (in pixels).
	 * 
	 * @param borderWidthCallback the border width callback
	 */
	public void setBorderWidth(NativeCallback borderWidthCallback) {
		// resets callback
		setBorderWidth((BarBorderWidth) null);
		// stores value
		setValue(Dataset.CommonProperty.BORDER_WIDTH, borderWidthCallback);
	}

	/**
	 * Sets the callback to set the border width (in pixels), when hovered.
	 * 
	 * @param hoverBorderWidthCallback the hover border width callback
	 */
	public void setHoverBorderWidth(BarBorderWidthCallback hoverBorderWidthCallback) {
		// sets the callback
		this.hoverBorderWidthCallback = hoverBorderWidthCallback;
		// checks if consistent
		if (hoverBorderWidthCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Dataset.CommonProperty.HOVER_BORDER_WIDTH, hoverBorderWidthCallbackProxy.getProxy());
		} else {
			// otherwise removes the properties from java script object
			remove(Dataset.CommonProperty.HOVER_BORDER_WIDTH);
		}
	}

	/**
	 * Returns the callback to set the border width (in pixels), when hovered.
	 * 
	 * @return the hover border width callback
	 */
	public BarBorderWidthCallback getHoverBorderWidthCallback() {
		return hoverBorderWidthCallback;
	}

	/**
	 * Sets the callback to set the border width (in pixels), when hovered.
	 * 
	 * @param hoverBorderWidthCallback the hover border width callback
	 */
	public void setHoverBorderWidth(NativeCallback hoverBorderWidthCallback) {
		// resets callback
		setHoverBorderWidth((BarBorderWidthCallback) null);
		// stores value
		setValue(Dataset.CommonProperty.HOVER_BORDER_WIDTH, hoverBorderWidthCallback);
	}

	// ---------------------------
	// OVERRIDE METHODS
	// ---------------------------

	/**
	 * Throws an exception because not available.
	 * 
	 * @param values ignored because will throw an exception
	 */
	@Override
	public void setData(double... values) {
		throw new UnsupportedOperationException(INVALID_SET_DATA_CALL);
	}

	/**
	 * Throws an exception because not available.
	 * 
	 * @param values ignored because will throw an exception
	 */
	@Override
	public void setData(List<Double> values) {
		throw new UnsupportedOperationException(INVALID_SET_DATA_CALL);
	}

	/**
	 * Throws an exception because not available.
	 * 
	 * @param binding ignored because will throw an exception
	 * @return nothing because will throw an exception
	 */
	@Override
	public List<Double> getData(boolean binding) {
		throw new UnsupportedOperationException(INVALID_GET_DATA_CALL);
	}

	// ---------------------------
	// INTERNALS METHODS
	// ---------------------------

	/**
	 * Returns an integer or {@link BarBorderRadius} when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @return a object property value, as integer or {@link BarBorderRadius}
	 */
	final NativeObject onBorderRadius(DatasetContext context) {
		int valueToReturn = DEFAULT_BORDER_RADIUS;
		// gets value
		Object value = ScriptableUtil.getOptionValue(context, getBorderRadiusCallback());
		// checks if is an object
		if (value instanceof BarBorderRadius) {
			// casts to border radius object
			BarBorderRadius object = (BarBorderRadius) value;
			// returns the native object
			return object.nativeObject();
		} else if (value instanceof Number) {
			// checks if is an number
			// casts to number
			Number number = (Number) value;
			// stores to result
			valueToReturn = Checker.positiveOrZero(number.intValue());
		}
		// cats to a object
		BarBorderRadius object = new BarBorderRadius(valueToReturn);
		// returns the native object
		return object.nativeObject();
	}

	/**
	 * Returns an integer or {@link BarBorderWidth} when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @param callback callback instance for the specific option.
	 * @param defaultValue default value of the option
	 * @return a object property value, as integer or {@link BarBorderWidth}
	 */
	final NativeObject onBorderWidth(DatasetContext context, BarBorderWidthCallback callback, int defaultValue) {
		int valueToReturn = defaultValue;
		// gets value
		Object value = ScriptableUtil.getOptionValue(context, callback);
		// checks if is an object
		if (value instanceof BarBorderWidth) {
			// casts to border width object
			BarBorderWidth object = (BarBorderWidth) value;
			// returns the native object
			return object.nativeObject();
		} else if (value instanceof Number) {
			// checks if is an number
			// casts to number
			Number number = (Number) value;
			// stores to result
			valueToReturn = Checker.positiveOrZero(number.intValue());
		}
		// cats to a object
		BarBorderWidth object = new BarBorderWidth(valueToReturn);
		// returns the native object
		return object.nativeObject();
	}

	/**
	 * Factory to create a matrix data point from a native object, used for array container lists.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static class DataPointFactory implements NativeObjectContainerFactory<MatrixDataPoint> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client. commons.NativeObject)
		 */
		@Override
		public MatrixDataPoint create(NativeObject nativeObject) {
			return new MatrixDataPoint(nativeObject);
		}

	}
}