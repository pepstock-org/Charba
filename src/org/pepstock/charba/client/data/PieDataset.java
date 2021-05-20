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
import org.pepstock.charba.client.callbacks.BorderRadiusCallback;
import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.OffsetCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyNativeObjectCallback;
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

	// border radius array constant for set border radius from a list
	private static final ArcBorderRadius[] BORDER_RADIUS_EMPTY_ARRAY = new ArcBorderRadius[0];

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the offset function
	private final CallbackProxy<ProxyIntegerCallback> offsetCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover offset function
	private final CallbackProxy<ProxyIntegerCallback> hoverOffsetCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the border radius function
	private final CallbackProxy<ProxyNativeObjectCallback> borderRadiusCallbackProxy = JsHelper.get().newCallbackProxy();

	// border offset callback instance
	private OffsetCallback<DatasetContext> offsetCallback = null;
	// hover offset callback instance
	private OffsetCallback<DatasetContext> hoverOffsetCallback = null;
	// border skipped callback instance
	private BorderRadiusCallback borderRadiusCallback = null;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		WEIGHT("weight"),
		OFFSET("offset"),
		HOVER_OFFSET("hoverOffset"),
		BORDER_RADIUS("borderRadius"),
		ROTATION("rotation"),
		CIRCUMFERENCE("circumference"),
		// internal to map the border radius type
		CHARBA_BORDER_RADIUS_TYPE("charbaBorderRadiusType");

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
	// border items handler instance
	private final BorderItemsHandler borderItemsHandler;

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
		// creates the border items handler
		this.borderItemsHandler = new BorderItemsHandler(getNativeObject());
		// creates border align handler instance
		this.borderAlignHandler = new BorderAlignHandler(getNativeObject(), getDefaultValues());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.offsetCallbackProxy.setCallback((context) -> ScriptableUtils.getOptionValue(createContext(context), getOffsetCallback(), getDefaultValues().getElements().getArc().getOffset()).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.hoverOffsetCallbackProxy.setCallback((context) -> ScriptableUtils.getOptionValue(createContext(context), getHoverOffsetCallback(), getDefaultValues().getElements().getArc().getOffset()).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.borderRadiusCallbackProxy.setCallback((context) -> borderItemsHandler.onBorderItem(createContext(context), getBorderRadiusCallback(), ArcBorderRadius.FACTORY, getDefaultValues().getElements().getArc().getBorderRadius()));
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
	 * Sets the starting angle to draw arcs from.
	 * 
	 * @param rotation starting angle to draw arcs from.
	 */
	public void setRotation(double rotation) {
		setValue(Property.ROTATION, rotation);
	}

	/**
	 * Returns the starting angle to draw arcs from.
	 * 
	 * @return starting angle to draw arcs from.
	 */
	public double getRotation() {
		return getValue(Property.ROTATION, getDefaultValues().getRotation());
	}

	/**
	 * Sets the sweep to allow arcs to cover.
	 * 
	 * @param circumference the sweep to allow arcs to cover.
	 */
	public void setCircumference(double circumference) {
		setValue(Property.CIRCUMFERENCE, circumference);
	}

	/**
	 * Returns the the sweep to allow arcs to cover.
	 * 
	 * @return the sweep to allow arcs to cover.
	 */
	public double getCircumference() {
		return getValue(Property.CIRCUMFERENCE, getDefaultValues().getCircumference());
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
		// resets callback
		setOffset((OffsetCallback<DatasetContext>) null);
		// stores the value
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
		// resets callback
		setHoverOffset((OffsetCallback<DatasetContext>) null);
		// stores the value
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
			ArrayInteger array = getValueOrArray(Property.HOVER_OFFSET, getDefaultValues().getElements().getArc().getHoverOffset());
			return ArrayListHelper.list(array);
		}
		// if here, is a callback
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Sets the arc border radius (in pixels).
	 * 
	 * @param borderRadius the arc border radius (in pixels).
	 */
	public void setBorderRadius(int... borderRadius) {
		// resets callback
		setBorderRadius((BorderRadiusCallback) null);
		// stores the value
		borderItemsHandler.setBorderItem(Property.BORDER_RADIUS, Property.CHARBA_BORDER_RADIUS_TYPE, borderRadius);
	}

	/**
	 * Sets the arc border radius (in pixels).
	 * 
	 * @param borderRadius the arc border radius (in pixels).
	 */
	public void setBorderRadius(ArcBorderRadius... borderRadius) {
		// resets callback
		setBorderRadius((BorderRadiusCallback) null);
		// stores the value
		borderItemsHandler.setBorderItem(Property.BORDER_RADIUS, Property.CHARBA_BORDER_RADIUS_TYPE, borderRadius);
	}

	/**
	 * Sets the arc border radius (in pixels).
	 * 
	 * @param borderRadius the arc border radius (in pixels).
	 */
	public void setBorderRadius(List<ArcBorderRadius> borderRadius) {
		// resets callback
		setBorderRadius((BorderRadiusCallback) null);
		// stores the value
		borderItemsHandler.setBorderItem(Property.BORDER_RADIUS, Property.CHARBA_BORDER_RADIUS_TYPE, borderRadius, BORDER_RADIUS_EMPTY_ARRAY);
	}

	/**
	 * Returns the list of arc border radius (in pixels).<br>
	 * If a callback has been set, returns an empty list.
	 * 
	 * @return the list of arc border radius (in pixels).<br>
	 *         If a callback has been set, returns an empty list
	 */
	public List<Integer> getBorderRadius() {
		return borderItemsHandler.getBorderItem(Property.BORDER_RADIUS, Property.CHARBA_BORDER_RADIUS_TYPE, getDefaultValues().getElements().getArc().getBorderRadius());
	}

	/**
	 * Returns the list of bar border radius (in pixels).<br>
	 * If a callback or an array have been set, returns an empty object.
	 * 
	 * @return the list of bar border radius (in pixels).<br>
	 *         If a callback or an array have been set, returns an empty object
	 */
	public List<ArcBorderRadius> getBorderRadiusAsObjects() {
		return borderItemsHandler.getBorderItemAsObjects(Property.BORDER_RADIUS, Property.CHARBA_BORDER_RADIUS_TYPE, ArcBorderRadius.FACTORY, getDefaultValues().getElements().getArc().getBorderRadius());
	}

	/**
	 * Returns the offset callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the offset callback, if set, otherwise <code>null</code>.
	 */
	public OffsetCallback<DatasetContext> getOffsetCallback() {
		return offsetCallback;
	}

	/**
	 * Sets the offset callback.
	 * 
	 * @param offsetCallback the offset callback.
	 */
	public void setOffset(OffsetCallback<DatasetContext> offsetCallback) {
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
	public OffsetCallback<DatasetContext> getHoverOffsetCallback() {
		return hoverOffsetCallback;
	}

	/**
	 * Sets the offset callback, when dataset is hovered.
	 * 
	 * @param hoverOffsetCallback the offset callback, when dataset is hovered.
	 */
	public void setHoverOffset(OffsetCallback<DatasetContext> hoverOffsetCallback) {
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

	/**
	 * Returns the border radius callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border radius callback, if set, otherwise <code>null</code>.
	 */
	public BorderRadiusCallback getBorderRadiusCallback() {
		return borderRadiusCallback;
	}

	/**
	 * Sets the border radius callback.
	 * 
	 * @param borderRadiusCallback the border radius callback.
	 */
	public void setBorderRadius(BorderRadiusCallback borderRadiusCallback) {
		// sets the callback
		this.borderRadiusCallback = borderRadiusCallback;
		// checks if callback is consistent
		borderItemsHandler.setBorderItemCallback(Property.BORDER_RADIUS, Property.CHARBA_BORDER_RADIUS_TYPE, borderRadiusCallback, borderRadiusCallbackProxy.getProxy());
	}

}