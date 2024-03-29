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

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.callbacks.CubicInterpolationModeCallback;
import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.callbacks.SteppedCallback;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.ArrayStringList;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.enums.CubicInterpolationMode;
import org.pepstock.charba.client.enums.DataType;
import org.pepstock.charba.client.enums.DefaultScaleId;
import org.pepstock.charba.client.enums.IndexAxis;
import org.pepstock.charba.client.enums.Stepped;
import org.pepstock.charba.client.options.ScaleId;

/**
 * The line chart allows a number of properties to be specified for each data set.<br>
 * These are used to set display properties for a specific data set.<br>
 * All point* properties can be specified as an array.<br>
 * If these are set to an array value, the first value applies to the first point, the second value to the second point, and so on.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class LineDataset extends LiningDataset implements HasDataPoints {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		X_AXIS_ID("xAxisID"),
		Y_AXIS_ID("yAxisID"),
		INDEX_AXIS("indexAxis"),
		CUBIC_INTERPOLATION_MODE("cubicInterpolationMode"),
		SHOW_LINE("showLine"),
		STEPPED("stepped");

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
	// callback proxy to invoke the cubic interpolation mode function
	private final CallbackProxy<ProxyStringCallback> cubicInterpolationModeCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the stepped function
	private final CallbackProxy<ProxyObjectCallback> steppedCallbackProxy = JsHelper.get().newCallbackProxy();

	// cubic interpolation mode callback instance
	private CubicInterpolationModeCallback cubicInterpolationModeCallback = null;
	// stepped callback instance
	private SteppedCallback steppedCallback = null;

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 */
	public LineDataset() {
		this(Dataset.DEFAULT_HIDDEN);
	}

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 * 
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	public LineDataset(boolean hidden) {
		this(ChartType.LINE, hidden);
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 */
	public LineDataset(IsDefaultOptions defaultValues) {
		this(defaultValues, Dataset.DEFAULT_HIDDEN);
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	public LineDataset(IsDefaultOptions defaultValues, boolean hidden) {
		this(ChartType.LINE, defaultValues, hidden);
	}

	/**
	 * Creates the dataset using chart type related to the dataset.
	 * 
	 * @param type chart type related to the dataset
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	protected LineDataset(Type type, boolean hidden) {
		this(type, null, hidden);
	}

	/**
	 * Creates the dataset using a default and chart type related to the dataset.
	 * 
	 * @param type chart type related to the dataset
	 * @param defaultValues default options
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	protected LineDataset(Type type, IsDefaultOptions defaultValues, boolean hidden) {
		super(type, defaultValues, hidden);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.cubicInterpolationModeCallbackProxy.setCallback(context -> onCubicInterpolationMode(createContext(context)));
		// sets function to proxy callback in order to invoke the java interface
		this.steppedCallbackProxy.setCallback(context -> onStepped(createContext(context)));
	}

	/**
	 * Sets the ID of the x axis to plot this dataset on.
	 * 
	 * @param xAxisID the ID of the x axis to plot this dataset on.<br>
	 * 
	 */
	public void setXAxisID(String xAxisID) {
		// checks if is valid scale id
		ScaleId.checkIfValid(xAxisID);
		// stores
		setValue(Property.X_AXIS_ID, xAxisID);
	}

	/**
	 * Sets the ID of the x axis to plot this data set on.
	 * 
	 * @param xAxisID the ID of the x axis to plot this data set on.
	 */
	public void setXAxisID(ScaleId xAxisID) {
		// checks if scale id is valid
		ScaleId.checkIfValid(xAxisID);
		// stores the object
		setValue(Property.X_AXIS_ID, xAxisID);
	}

	/**
	 * Returns the ID of the x axis to plot this data set on.<br>
	 * If not specified, this defaults to the ID of {@link DefaultScaleId#X}.
	 * 
	 * @return the ID of the x axis to plot this data set on.<br>
	 *         If not specified, this defaults to the ID of {@link DefaultScaleId#X}
	 */
	public ScaleId getXAxisID() {
		return getValue(Property.X_AXIS_ID, DefaultScaleId.X);
	}

	/**
	 * Sets the ID of the y axis to plot this data set on.
	 * 
	 * @param yAxisID the ID of the y axis to plot this data set on.
	 */
	public void setYAxisID(String yAxisID) {
		// checks if is valid scale id
		ScaleId.checkIfValid(yAxisID);
		// stores
		setValue(Property.Y_AXIS_ID, yAxisID);
	}

	/**
	 * Sets the ID of the y axis to plot this data set on.
	 * 
	 * @param yAxisID the ID of the y axis to plot this data set on.
	 */
	public void setYAxisID(ScaleId yAxisID) {
		// checks if scale id is valid
		ScaleId.checkIfValid(yAxisID);
		// stores the object
		setValue(Property.Y_AXIS_ID, yAxisID);
	}

	/**
	 * Returns the ID of the y axis to plot this data set on. <br>
	 * If not specified, this defaults to the ID of {@link DefaultScaleId#Y}.
	 * 
	 * @return the ID of the y axis to plot this data set on.<br>
	 *         If not specified, this defaults to the ID of {@link DefaultScaleId#Y}
	 */
	public ScaleId getYAxisID() {
		return getValue(Property.Y_AXIS_ID, DefaultScaleId.Y);
	}

	/**
	 * Sets the base axis for the data set. Use {@link IndexAxis#Y} for vertical line.
	 * 
	 * @param indexAxis the base axis for the data set
	 */
	public void setIndexAxis(IndexAxis indexAxis) {
		setValue(Property.INDEX_AXIS, indexAxis);
	}

	/**
	 * Returns the base axis for the data set.
	 * 
	 * @return the base axis for the data set
	 */
	public IndexAxis getIndexAxis() {
		return getValue(Property.INDEX_AXIS, IndexAxis.values(), getDefaultValues().getIndexAxis());
	}

	/**
	 * Sets algorithm used to interpolate a smooth curve from the discrete data points.<br>
	 * The following interpolation modes are supported:<br>
	 * <br>
	 * 
	 * <pre>
	 * 'default'
	 * 'monotone'
	 * </pre>
	 * 
	 * <br>
	 * The 'default' algorithm uses a custom weighted cubic interpolation, which produces pleasant curves for all types of datasets.<br>
	 * The 'monotone' algorithm is more suited to y = f(x) datasets : it preserves monotonicity (or piecewise monotonicity) of the dataset being interpolated, and ensures local
	 * extremums (if any) stay at input data points.
	 * 
	 * @param mode algorithm used to interpolate a smooth curve from the discrete data points
	 */
	public void setCubicInterpolationMode(CubicInterpolationMode mode) {
		// reset callback
		setCubicInterpolationMode((CubicInterpolationModeCallback) null);
		// stores value
		setValue(Property.CUBIC_INTERPOLATION_MODE, mode);
	}

	/**
	 * Returns algorithm used to interpolate a smooth curve from the discrete data points.
	 * 
	 * @return algorithm used to interpolate a smooth curve from the discrete data points.
	 */
	public CubicInterpolationMode getCubicInterpolationMode() {
		return getValue(Property.CUBIC_INTERPOLATION_MODE, CubicInterpolationMode.values(), getDefaultValues().getElements().getLine().getCubicInterpolationMode());
	}

	/**
	 * Sets if the line is not drawn for this dataset.
	 * 
	 * @param showLine <code>false</code> if the line is not drawn for this dataset.
	 */
	public void setShowLine(boolean showLine) {
		setValue(Property.SHOW_LINE, showLine);
	}

	/**
	 * Returns if the line is not drawn for this dataset.
	 * 
	 * @return <code>false</code> if the line is not drawn for this dataset.
	 */
	public boolean isShowLine() {
		return getValue(Property.SHOW_LINE, getTypedDataset().isShowLine());
	}

	/**
	 * Sets if the line is shown as a stepped line.<br>
	 * If the stepped value is set to anything other than false, tension will be ignored.
	 * 
	 * @param line if the line is shown as a stepped line. <code>false</code> is no step interpolation
	 */
	public void setStepped(boolean line) {
		// resets callback
		setStepped((SteppedCallback) null);
		// checks if no stepped line
		if (!line) {
			// sets boolean value instead of string one
			setValue(Property.STEPPED, false);
		} else {
			// sets value before, equals to true
			setValue(Property.STEPPED, Stepped.BEFORE);
		}
	}

	/**
	 * Sets if the line is shown as a stepped line.<br>
	 * If the stepped value is set to anything other than false, tension will be ignored.
	 * 
	 * @param line if the line is shown as a stepped line.
	 */
	public void setStepped(Stepped line) {
		// resets callback
		setStepped((SteppedCallback) null);
		// checks if no stepped line
		if (Stepped.FALSE.equals(line)) {
			// sets boolean value instead of string one
			setValue(Property.STEPPED, false);
		} else {
			// sets value
			setValue(Property.STEPPED, line);
		}
	}

	/**
	 * Returns if the line is shown as a stepped line.
	 * 
	 * @return If the line is shown as a stepped line.
	 */
	public Stepped getStepped() {
		// checks if value of stepped line is a boolean
		if (isType(Property.STEPPED, ObjectType.BOOLEAN)) {
			return Stepped.FALSE;
		} else {
			// otherwise returns the stepped
			return getValue(Property.STEPPED, Stepped.values(), getDefaultValues().getElements().getLine().getStepped());
		}
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of strings. Each point in the data array corresponds to the label at the same index on the x axis.
	 * 
	 * @param data an array of strings
	 */
	public void setDataString(String... data) {
		setArrayValue(Dataset.CommonProperty.DATA, ArrayString.fromOrNull(data));
		// sets data type checking if the key exists
		setValue(Dataset.InternalProperty.CHARBA_DATA_TYPE, has(Dataset.CommonProperty.DATA) ? DataType.STRINGS : DataType.UNKNOWN);
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of strings. Each point in the data array corresponds to the label at the same index on the x axis.
	 * 
	 * @param data a list of strings
	 */
	public void setDataString(List<String> data) {
		setArrayValue(Dataset.CommonProperty.DATA, ArrayString.fromOrNull(data));
		// sets data type checking if the key exists
		setValue(Dataset.InternalProperty.CHARBA_DATA_TYPE, has(Dataset.CommonProperty.DATA) ? DataType.STRINGS : DataType.UNKNOWN);
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of strings. Each point in the data array corresponds to the label at the same index on the x
	 * axis.
	 * 
	 * @return a list of strings or an empty list of strings if the data type is not {@link DataType#STRINGS}.
	 */
	public List<String> getDataString() {
		return getDataString(false);
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of strings. Each point in the data array corresponds to the label at the same index on the x
	 * axis.
	 * 
	 * @param binding if <code>true</code> binds the new array list in the container
	 * @return a list of strings or an empty list of strings if the data type is not {@link DataType#STRINGS}.
	 */
	public List<String> getDataString(boolean binding) {
		// checks if is a string data type
		if (has(Dataset.CommonProperty.DATA) && DataType.STRINGS.equals(getDataType())) {
			/// returns strings
			ArrayString array = getArrayValue(Dataset.CommonProperty.DATA);
			return ArrayListHelper.list(array);
		}
		// checks if wants to bind the array
		if (binding) {
			ArrayStringList result = new ArrayStringList();
			// set value
			setArrayValue(Dataset.CommonProperty.DATA, ArrayString.fromOrEmpty(result));
			// sets data type
			setValue(Dataset.InternalProperty.CHARBA_DATA_TYPE, DataType.STRINGS);
			// returns list
			return result;
		}
		// returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Returns the border join style callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border join style callback, if set, otherwise <code>null</code>.
	 */
	public CubicInterpolationModeCallback getCubicInterpolationModeCallback() {
		return cubicInterpolationModeCallback;
	}

	/**
	 * Sets the border join style callback.
	 * 
	 * @param cubicInterpolationModeCallback the border join style callback.
	 */
	public void setCubicInterpolationMode(CubicInterpolationModeCallback cubicInterpolationModeCallback) {
		// sets the callback
		this.cubicInterpolationModeCallback = cubicInterpolationModeCallback;
		// checks if callback is consistent
		if (cubicInterpolationModeCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.CUBIC_INTERPOLATION_MODE, cubicInterpolationModeCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.CUBIC_INTERPOLATION_MODE);
		}
	}

	/**
	 * Sets the border join style callback.
	 * 
	 * @param cubicInterpolationModeCallback the border join style callback.
	 */
	public void setCubicInterpolationMode(NativeCallback cubicInterpolationModeCallback) {
		// resets callback
		setCubicInterpolationMode((CubicInterpolationModeCallback) null);
		// stores value
		setValue(Property.CUBIC_INTERPOLATION_MODE, cubicInterpolationModeCallback);
	}

	/**
	 * Returns the stepped callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the stepped callback, if set, otherwise <code>null</code>.
	 */
	public SteppedCallback getSteppedCallback() {
		return steppedCallback;
	}

	/**
	 * Sets the stepped callback.
	 * 
	 * @param steppedCallback the stepped callback.
	 */
	public void setStepped(SteppedCallback steppedCallback) {
		// sets the callback
		this.steppedCallback = steppedCallback;
		// checks if callback is consistent
		if (steppedCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.STEPPED, steppedCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.STEPPED);
		}
	}

	/**
	 * Sets the stepped callback.
	 * 
	 * @param steppedCallback the stepped callback.
	 */
	public void setStepped(NativeCallback steppedCallback) {
		// resets callback
		setStepped((SteppedCallback) null);
		// stores and manages callback
		setValue(Property.STEPPED, steppedCallback);
	}

	/**
	 * Returns a {@link CubicInterpolationMode} when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @return a object property value, as {@link CubicInterpolationMode}
	 */
	private String onCubicInterpolationMode(DatasetContext context) {
		// gets value
		CubicInterpolationMode result = ScriptableUtil.getOptionValue(context, getCubicInterpolationModeCallback());
		// checks result
		if (result != null) {
			return result.value();
		}
		// default result
		return getDefaultValues().getElements().getLine().getCubicInterpolationMode().value();
	}

	/**
	 * Returns a {@link Stepped} when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @return a object property value, as {@link Stepped}
	 */
	private Object onStepped(DatasetContext context) {
		// gets value
		Stepped result = ScriptableUtil.getOptionValue(context, getSteppedCallback());
		// checks if consistent
		if (Stepped.FALSE.equals(result)) {
			// returns the result as boolean
			return false;
		} else if (result != null) {
			// returns the result as string
			return result.value();
		}
		// default result
		return getDefaultValues().getElements().getLine().getStepped().value();
	}
}