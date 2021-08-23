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
package org.pepstock.charba.client.matrix;

import java.util.List;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyDoubleCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.controllers.ControllerType;
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
	 * Default border width, <b>{@value}</b>.
	 */
	public static final int DEFAULT_BORDER_WIDTH = 0;
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
	// factory to create data points
	private static final DataPointFactory DATAPOINTS_FACTORY = new DataPointFactory();

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the width function
	private final CallbackProxy<ProxyDoubleCallback> widthCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the height function
	private final CallbackProxy<ProxyDoubleCallback> heightCallbackProxy = JsHelper.get().newCallbackProxy();

	// ---------------------------
	// -- USERS CALLBACKS ---
	// ---------------------------
	// user callback implementation for width
	private SizeCallback widthCallback = null;
	// user callback implementation for height
	private SizeCallback heightCallback = null;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ANCHOR_X("anchorX"),
		ANCHOR_Y("anchorY"),
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
		this.widthCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(createContext(context), getWidthCallback(), DEFAULT_WIDTH).doubleValue());
		// sets function to proxy callback in order to invoke the java interface
		this.heightCallbackProxy.setCallback(context -> ScriptableUtils.getOptionValue(createContext(context), getHeightCallback(), DEFAULT_HEIGHT).doubleValue());
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
		// gets array
		ArrayObject array = getArrayValue(CommonProperty.DATA);
		// returns points
		return ArrayListHelper.list(array, DATAPOINTS_FACTORY);
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