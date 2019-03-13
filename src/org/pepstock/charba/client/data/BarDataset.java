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
import org.pepstock.charba.client.callbacks.BorderSkippedCallback;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.enums.BorderSkipped;
import org.pepstock.charba.client.enums.DataType;
import org.pepstock.charba.client.options.Scales;

import jsinterop.annotations.JsFunction;

/**
 * The bar chart allows a number of properties to be specified for each dataset. These are used to set display properties for a
 * specific dataset.<br>
 * Some properties can be specified as an array. If these are set to an array value, the first value applies to the first bar,
 * the second value to the second bar, and so on.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class BarDataset extends HovingFlexDataset implements HasDataPoints {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION callback called to provide the border skipped.<br>
	 * Must be an interface with only 1 method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ProxyBorderSkippedCallback {

		/**
		 * Method of function to be called to provide the border skipped.
		 * 
		 * @param contextFunction context Value of <code>this</code> to the execution context of function.
		 * @param context native object as context.
		 * @return border skipped property value. Could be a {@link BorderSkipped} or a {@link Boolean}
		 */
		Object call(Object contextFunction, Context context);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the border skipped function
	private final CallbackProxy<ProxyBorderSkippedCallback> borderSkippedCallbackProxy = JsHelper.get().newCallbackProxy();

	// border skipped callback instance
	private BorderSkippedCallback borderSkippedCallback = null;

	// data point factory
	private final DataPointListFactory factory = new DataPointListFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		xAxisID,
		yAxisID,
		borderSkipped
	}

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 */
	public BarDataset() {
		this(null);
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 */
	public BarDataset(IsDefaultOptions defaultValues) {
		super(defaultValues);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		borderSkippedCallbackProxy.setCallback(new ProxyBorderSkippedCallback() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.pepstock.charba.client.data.BarDataset.ProxyBorderSkippedCallback#call(java.lang.Object,
			 * org.pepstock.charba.client.data.Context)
			 */
			@Override
			public Object call(Object contextFunction, Context context) {
				// gets chart instance
				String id = context.getNativeChart().getCharbaId();
				AbstractChart<?, ?> chart = Charts.get(id);
				// checks if the callback is set
				if (chart != null && borderSkippedCallback != null) {
					// calls callback
					BorderSkipped result = borderSkippedCallback.borderSkipped(chart, context);
					// checks result
					if (result != null) {
						// checks if is boolean
						if (BorderSkipped.False.equals(result)) {
							return false;
						} else {
							// returns the string value
							return result.name();
						}
					}
				}
				// default result
				BorderSkipped defaults = getDefaultValues().getElements().getRectangle().getBorderSkipped();
				// checks if is boolean
				if (BorderSkipped.False.equals(defaults)) {
					return false;
				} else {
					// returns the string value
					return defaults.name();
				}
			}
		});
	}

	/**
	 * Sets the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 * 
	 * @param xAxisID the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found
	 *            x axis.
	 */
	public void setXAxisID(String xAxisID) {
		setValue(Property.xAxisID, xAxisID);
	}

	/**
	 * Returns the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x
	 * axis.
	 * 
	 * @return the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x
	 *         axis.
	 */
	public String getXAxisID() {
		return getValue(Property.xAxisID, Scales.DEFAULT_X_AXIS_ID);
	}

	/**
	 * Sets the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y axis.
	 * 
	 * @param yAxisID the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found
	 *            y axis.
	 */
	public void setYAxisID(String yAxisID) {
		setValue(Property.yAxisID, yAxisID);
	}

	/**
	 * Returns the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y
	 * axis.
	 * 
	 * @return the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y
	 *         axis.
	 */
	public String getYAxisID() {
		return getValue(Property.yAxisID, Scales.DEFAULT_Y_AXIS_ID);
	}

	/**
	 * Sets the edge to skip drawing the border for.
	 * 
	 * @param borderskip to set <code>false</code> as border skipped. If set <code>true</code>, is ignored
	 */
	public void setBorderSkipped(boolean borderskip) {
		// checks value for border skipped
		// if not false, otherwise ignore it
		if (!borderskip) {
			// stores boolean value
			setValue(Property.borderSkipped, BorderSkipped.False);
		}
	}

	/**
	 * Sets the edge to skip drawing the border for.
	 * 
	 * @param position the edge to skip drawing the border for.
	 */
	public void setBorderSkipped(BorderSkipped position) {
		// resets callbacks
		setBorderSkipped((BorderSkippedCallback)null);
		// checks if setting a false value
		if (BorderSkipped.False.equals(position)) {
			// stores boolean value
			setValue(Property.borderSkipped, false);
		} else {
			// otherwise stores the key value
			setValue(Property.borderSkipped, position);
		}
	}

	/**
	 * Returns the edge to skip drawing the border for.
	 * 
	 * @return the edge to skip drawing the border for.
	 */
	public BorderSkipped getBorderSkipped() {
		// checks if 'false' has been set
		if (ObjectType.Boolean.equals(type(Property.borderSkipped))) {
			// returns is false
			return BorderSkipped.False;
		} else if (ObjectType.Function.equals(type(Property.borderSkipped))) {
			// checks if a callback has been set
			// returns defaults
			return getDefaultValues().getElements().getRectangle().getBorderSkipped();
		}
		// otherwise returns the enum value as string
		return getValue(Property.borderSkipped, BorderSkipped.class, getDefaultValues().getElements().getRectangle().getBorderSkipped());
	}
	
	/**
	 * Returns the border skipped callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border skipped callback, if set, otherwise <code>null</code>.
	 */
	public BorderSkippedCallback getBorderSkippedCallback() {
		return borderSkippedCallback;
	}

	/**
	 * Sets the border skipped callback.
	 * 
	 * @param borderSkippedCallback the border skipped callback to set
	 */
	public void setBorderSkipped(BorderSkippedCallback borderSkippedCallback) {
		// sets the callback
		this.borderSkippedCallback = borderSkippedCallback;
		// checks if callback is consistent
		if (borderSkippedCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.borderSkipped, borderSkippedCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.borderSkipped);
		}
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
	 * @see org.pepstock.charba.client.data.HovingFlexDataset#getDefaultBackgroundColorAsString()
	 */
	@Override
	String getDefaultBackgroundColorAsString() {
		return getDefaultValues().getElements().getRectangle().getBackgroundColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HovingFlexDataset#getDefaultBorderColorAsString()
	 */
	@Override
	String getDefaultBorderColorAsString() {
		return getDefaultValues().getElements().getRectangle().getBorderColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HovingFlexDataset#getDefaultBorderWidth()
	 */
	@Override
	int getDefaultBorderWidth() {
		return getDefaultValues().getElements().getRectangle().getBorderWidth();
	}

}