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

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.callbacks.OffsetCallback;
import org.pepstock.charba.client.callbacks.ScriptableContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultOptions;

/**
 * The pie chart allows a number of properties to be specified for each dataset. These are used to set display properties for a specific dataset.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class PieDataset extends HovingDataset implements HasBorderAlign {

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the offset function
	private final CallbackProxy<ScriptableFunctions.ProxyIntegerCallback> offsetCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover offset function
	private final CallbackProxy<ScriptableFunctions.ProxyIntegerCallback> hoverOffsetCallbackProxy = JsHelper.get().newCallbackProxy();

	// border offset callback instance
	private OffsetCallback<ScriptableContext> offsetCallback = null;
	// hover offset callback instance
	private OffsetCallback<ScriptableContext> hoverOffsetCallback = null;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		WEIGHT("weight"),
		OFFSET("offset"),
		HOVER_OFFSET("hoverOffset");

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

	// instance of border align handler
	private final BorderAlignHandler borderAlignHandler;

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 */
	public PieDataset() {
		this(Dataset.DEFAULT_HIDDEN);
	}

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 * 
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	public PieDataset(boolean hidden) {
		this(ChartType.PIE, hidden);
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 */
	public PieDataset(IsDefaultOptions defaultValues) {
		this(defaultValues, Dataset.DEFAULT_HIDDEN);
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	public PieDataset(IsDefaultOptions defaultValues, boolean hidden) {
		this(ChartType.PIE, defaultValues, hidden);
	}

	/**
	 * Creates the dataset using chart type related to the dataset.
	 * 
	 * @param type chart type related to the dataset
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	protected PieDataset(Type type, boolean hidden) {
		this(type, null, hidden);
	}

	/**
	 * Creates the dataset using a default and chart type related to the dataset.
	 * 
	 * @param type chart type related to the dataset
	 * @param defaultValues default options
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	protected PieDataset(Type type, IsDefaultOptions defaultValues, boolean hidden) {
		super(type, defaultValues, hidden);
		// creates border align handler instance
		this.borderAlignHandler = new BorderAlignHandler(getNativeObject(), getDefaultValues());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// gets value calling callback
		offsetCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new ScriptableContext(new DataEnvelop<>(context)), offsetCallback, getDefaultValues().getElements().getArc().getOffset()).intValue());
		// gets value calling callback
		hoverOffsetCallbackProxy.setCallback((contextFunction, context) -> ScriptableUtils.getOptionValue(new ScriptableContext(new DataEnvelop<>(context)), hoverOffsetCallback, getDefaultValues().getElements().getArc().getOffset()).intValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.HasBorderAlign#getBorderAlignHandler()
	 */
	@Override
	public final BorderAlignHandler getBorderAlignHandler() {
		return borderAlignHandler;
	}

	/**
	 * Sets the relative thickness of the dataset.<br>
	 * Providing a value for weight will cause the pie or doughnut dataset to be drawn with a thickness relative to the sum of all the dataset weight values.
	 * 
	 * @param weight the relative thickness of the dataset
	 */
	public void setWeight(double weight) {
		setValue(Property.WEIGHT, weight);
	}

	/**
	 * Returns the relative thickness of the dataset.<br>
	 * Providing a value for weight will cause the pie or doughnut dataset to be drawn with a thickness relative to the sum of all the dataset weight values.
	 * 
	 * @return the relative thickness of the dataset
	 */
	public double getWeight() {
		return getValue(Property.WEIGHT, getDefaultValues().getElements().getArc().getWeight());
	}

	/**
	 * Sets the arc offset (in pixels).
	 * 
	 * @param offset the arc offset
	 */
	public void setOffset(int... offset) {
		setValueOrArray(Property.OFFSET, offset);
	}

	/**
	 * Returns the arc offset (in pixels).
	 * 
	 * @return the arc offset
	 */
	public List<Integer> getOffset() {
		// checks if the callback has not been
		if (getOffsetCallback() == null) {
			// returns the array
			ArrayInteger array = getValueOrArray(Property.OFFSET, getDefaultValues().getElements().getArc().getOffset());
			return ArrayListHelper.list(array);
		}
		// if here, is a callback
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Sets the arc offset (in pixels), when dataset if hovered.
	 * 
	 * @param offset the arc offset, when dataset if hovered
	 */
	public void setHoverOffset(int... offset) {
		setValueOrArray(Property.HOVER_OFFSET, offset);
	}

	/**
	 * Returns the arc offset (in pixels), when dataset if hovered.
	 * 
	 * @return the arc offset, when dataset if hovered
	 */
	public List<Integer> getHoverOffset() {
		// checks if the callback has not been
		if (getHoverOffsetCallback() == null) {
			// returns the array
			ArrayInteger array = getValueOrArray(Property.HOVER_OFFSET, getDefaultValues().getElements().getArc().getOffset());
			return ArrayListHelper.list(array);
		}
		// if here, is a callback
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Returns the offset callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the offset callback, if set, otherwise <code>null</code>.
	 */
	public OffsetCallback<ScriptableContext> getOffsetCallback() {
		return offsetCallback;
	}

	/**
	 * Sets the offset callback.
	 * 
	 * @param offsetCallback the offset callback.
	 */
	public void setOffset(OffsetCallback<ScriptableContext> offsetCallback) {
		// sets the callback
		this.offsetCallback = offsetCallback;
		// checks if callback is consistent
		if (offsetCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.OFFSET, offsetCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.OFFSET);
		}
	}

	/**
	 * Returns the offset callback, when dataset is hovered, if set, otherwise <code>null</code>.
	 * 
	 * @return the offset callback, when dataset is hovered, if set, otherwise <code>null</code>.
	 */
	public OffsetCallback<ScriptableContext> getHoverOffsetCallback() {
		return hoverOffsetCallback;
	}

	/**
	 * Sets the offset callback, when dataset is hovered.
	 * 
	 * @param hoverOffsetCallback the offset callback, when dataset is hovered.
	 */
	public void setHoverOffset(OffsetCallback<ScriptableContext> hoverOffsetCallback) {
		// sets the callback
		this.hoverOffsetCallback = hoverOffsetCallback;
		// checks if callback is consistent
		if (hoverOffsetCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.HOVER_OFFSET, hoverOffsetCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.HOVER_OFFSET);
		}
	}

}