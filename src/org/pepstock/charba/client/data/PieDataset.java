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

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.callbacks.BorderRadiusCallback;
import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.JoinStyleCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.OffsetCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyIntegerCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyNativeObjectCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.enums.JoinStyle;

/**
 * The pie chart allows a number of properties to be specified for each dataset. These are used to set display properties for a specific dataset.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class PieDataset extends HoverDataset implements HasBorderAlign {

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
	// callback proxy to invoke the border join style function
	private final CallbackProxy<ProxyStringCallback> borderJoinStyleCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover border join style function
	private final CallbackProxy<ProxyStringCallback> hoverBorderJoinStyleCallbackProxy = JsHelper.get().newCallbackProxy();

	// offset callback instance
	private OffsetCallback<DatasetContext> offsetCallback = null;
	// hover offset callback instance
	private OffsetCallback<DatasetContext> hoverOffsetCallback = null;
	// border radius callback instance
	private BorderRadiusCallback<DatasetContext> borderRadiusCallback = null;
	// border join style callback instance
	private JoinStyleCallback<DatasetContext> borderJoinStyleCallback = null;
	// hover border join style callback instance
	private JoinStyleCallback<DatasetContext> hoverBorderJoinStyleCallback = null;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BORDER_JOIN_STYLE("borderJoinStyle"),
		BORDER_RADIUS("borderRadius"),
		CIRCUMFERENCE("circumference"),
		HOVER_BORDER_JOIN_STYLE("hoverBorderJoinStyle"),
		HOVER_OFFSET("hoverOffset"),
		OFFSET("offset"),
		ROTATION("rotation"),
		SPACING("spacing"),
		WEIGHT("weight"),
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
		this.offsetCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(createContext(context), getOffsetCallback(), getDefaultValues().getElements().getArc().getOffset()).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.hoverOffsetCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValueAsNumber(createContext(context), getHoverOffsetCallback(), getDefaultValues().getElements().getArc().getOffset()).intValue());
		// sets function to proxy callback in order to invoke the java interface
		this.borderRadiusCallbackProxy.setCallback(context -> borderItemsHandler.onBorderItem(createContext(context), getBorderRadiusCallback(), ArcBorderRadius.FACTORY, getDefaultValues().getElements().getArc().getBorderRadius()));
		// sets function to proxy callback in order to invoke the java interface
		this.borderJoinStyleCallbackProxy.setCallback(context -> onBorderJoinStyle(createContext(context), getBorderJoinStyleCallback(), getDefaultValues().getElements().getArc().getBorderJoinStyle()));
		// sets function to proxy callback in order to invoke the java interface
		this.hoverBorderJoinStyleCallbackProxy.setCallback(context -> onBorderJoinStyle(createContext(context), getHoverBorderJoinStyleCallback(), getDefaultValues().getElements().getArc().getBorderJoinStyle()));
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
	 * Sets the fixed arc offset (in pixels).<br>
	 * Similar to <code>offset</code> but applies to all arcs.
	 * 
	 * @param spacing the fixed arc offset (in pixels)
	 */
	public void setSpacing(int spacing) {
		setValue(Property.SPACING, spacing);
	}

	/**
	 * Returns the fixed arc offset (in pixels).<br>
	 * Similar to <code>offset</code> but applies to all arcs.
	 * 
	 * @return the fixed arc offset (in pixels)
	 */
	public int getSpacing() {
		return getValue(Property.SPACING, getDefaultValues().getElements().getArc().getSpacing());
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
	 * Returns the sweep to allow arcs to cover.
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
		ArrayInteger array = getValueOrArray(Property.OFFSET, getDefaultValues().getElements().getArc().getOffset());
		return ArrayListHelper.list(array);
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
		ArrayInteger array = getValueOrArray(Property.HOVER_OFFSET, getDefaultValues().getElements().getArc().getHoverOffset());
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the arc border radius (in pixels).
	 * 
	 * @param borderRadius the arc border radius (in pixels).
	 */
	public void setBorderRadius(int... borderRadius) {
		// resets callback
		setBorderRadius((BorderRadiusCallback<DatasetContext>) null);
		// stores the value
		borderItemsHandler.setBorderItem(Property.BORDER_RADIUS, Property.CHARBA_BORDER_RADIUS_TYPE, borderRadius);
	}

	/**
	 * Sets the arc border radius objects.
	 * 
	 * @param borderRadius the arc border radius objects.
	 */
	public void setBorderRadius(ArcBorderRadius... borderRadius) {
		// resets callback
		setBorderRadius((BorderRadiusCallback<DatasetContext>) null);
		// stores the value
		borderItemsHandler.setBorderItem(Property.BORDER_RADIUS, Property.CHARBA_BORDER_RADIUS_TYPE, borderRadius);
	}

	/**
	 * Sets the arc border radius objects.
	 * 
	 * @param borderRadius the arc border radius objects.
	 */
	public void setBorderRadius(List<ArcBorderRadius> borderRadius) {
		// resets callback
		setBorderRadius((BorderRadiusCallback<DatasetContext>) null);
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
	 * Returns the list of arc border radius objects.<br>
	 * If a callback or an array have been set, returns an empty object.
	 * 
	 * @return the list of arc border radius objects.<br>
	 *         If a callback or an array have been set, returns an empty object
	 */
	public List<ArcBorderRadius> getBorderRadiusAsObjects() {
		return borderItemsHandler.getBorderItemAsObjects(Property.BORDER_RADIUS, Property.CHARBA_BORDER_RADIUS_TYPE, ArcBorderRadius.FACTORY, getDefaultValues().getElements().getArc().getBorderRadius());
	}

	/**
	 * Sets how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified end
	 * points and control points are exactly at the same position, are skipped).
	 * 
	 * @param borderJoinStyle how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 */
	public void setBorderJoinStyle(JoinStyle... borderJoinStyle) {
		// resets callback
		setBorderJoinStyle((JoinStyleCallback<DatasetContext>) null);
		// stores value
		setValueOrArray(Property.BORDER_JOIN_STYLE, borderJoinStyle);
	}

	/**
	 * Returns how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified
	 * end points and control points are exactly at the same position, are skipped).
	 * 
	 * @return how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together
	 */
	public List<JoinStyle> getBorderJoinStyle() {
		ArrayString array = getValueOrArray(Property.BORDER_JOIN_STYLE, getDefaultValues().getElements().getArc().getBorderJoinStyle());
		return ArrayListHelper.list(JoinStyle.values(), array);
	}

	/**
	 * Sets how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified end
	 * points and control points are exactly at the same position, are skipped), when element is hovered.<br>
	 * There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * 
	 * @param borderJoinStyle There are three possible values for this property: round, bevel and miter.
	 */
	public void setHoverBorderJoinStyle(JoinStyle... borderJoinStyle) {
		// resets callback
		setHoverBorderJoinStyle((JoinStyleCallback<DatasetContext>) null);
		// stores value
		setValueOrArray(Property.HOVER_BORDER_JOIN_STYLE, borderJoinStyle);
	}

	/**
	 * Returns how two connecting segments (of lines, arcs or curves) with non-zero lengths in a shape are joined together (degenerate segments with zero lengths, whose specified
	 * end points and control points are exactly at the same position, are skipped), when element is hovered.<br>
	 * There are three possible values for this property: round, bevel and miter. By default this property is set to miter.
	 * 
	 * @return There are three possible values for this property: round, bevel and miter.
	 */
	public List<JoinStyle> getHoverBorderJoinStyle() {
		ArrayString array = getValueOrArray(Property.HOVER_BORDER_JOIN_STYLE, getDefaultValues().getElements().getArc().getBorderJoinStyle());
		return ArrayListHelper.list(JoinStyle.values(), array);
	}

	// --------------------------
	// CALLBACK
	// --------------------------

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
	 * Sets the offset callback.
	 * 
	 * @param offsetCallback the offset callback.
	 */
	public void setOffset(NativeCallback offsetCallback) {
		// resets callback
		setOffset((OffsetCallback<DatasetContext>) null);
		// stores value
		setValue(Property.OFFSET, offsetCallback);
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
	 * Sets the offset callback, when dataset is hovered.
	 * 
	 * @param hoverOffsetCallback the offset callback, when dataset is hovered.
	 */
	public void setHoverOffset(NativeCallback hoverOffsetCallback) {
		// resets callback
		setHoverOffset((OffsetCallback<DatasetContext>) null);
		// stores value
		setValue(Property.HOVER_OFFSET, hoverOffsetCallback);
	}

	/**
	 * Returns the border radius callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border radius callback, if set, otherwise <code>null</code>.
	 */
	public BorderRadiusCallback<DatasetContext> getBorderRadiusCallback() {
		return borderRadiusCallback;
	}

	/**
	 * Sets the border radius callback.
	 * 
	 * @param borderRadiusCallback the border radius callback.
	 */
	public void setBorderRadius(BorderRadiusCallback<DatasetContext> borderRadiusCallback) {
		// sets the callback
		this.borderRadiusCallback = borderRadiusCallback;
		// checks if callback is consistent
		borderItemsHandler.setBorderItemCallback(Property.BORDER_RADIUS, Property.CHARBA_BORDER_RADIUS_TYPE, borderRadiusCallback, borderRadiusCallbackProxy.getProxy());
	}

	/**
	 * Sets the border radius callback.
	 * 
	 * @param borderRadiusCallback the border radius callback.
	 */
	public void setBorderRadius(NativeCallback borderRadiusCallback) {
		// resets callback
		setBorderRadius((BorderRadiusCallback<DatasetContext>) null);
		// stores value
		setValue(Property.BORDER_RADIUS, borderRadiusCallback);
	}

	/**
	 * Returns the border join style callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border join style callback, if set, otherwise <code>null</code>.
	 */
	public JoinStyleCallback<DatasetContext> getBorderJoinStyleCallback() {
		return borderJoinStyleCallback;
	}

	/**
	 * Sets the border join style callback.
	 * 
	 * @param borderJoinStyleCallback the border join style callback.
	 */
	public void setBorderJoinStyle(NativeCallback borderJoinStyleCallback) {
		// resets callback
		setBorderJoinStyle((JoinStyleCallback<DatasetContext>) null);
		// stores value
		setValue(Property.BORDER_JOIN_STYLE, borderJoinStyleCallback);
	}

	/**
	 * Sets the border join style callback.
	 * 
	 * @param borderJoinStyleCallback the border join style callback.
	 */
	public void setBorderJoinStyle(JoinStyleCallback<DatasetContext> borderJoinStyleCallback) {
		// sets the callback
		this.borderJoinStyleCallback = borderJoinStyleCallback;
		// checks if callback is consistent
		if (borderJoinStyleCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.BORDER_JOIN_STYLE, borderJoinStyleCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.BORDER_JOIN_STYLE);
		}
	}

	/**
	 * Returns the border join style callback when element is hovered, if set, otherwise <code>null</code>.
	 * 
	 * @return the border join style callback when element is hovered, if set, otherwise <code>null</code>.
	 */
	public JoinStyleCallback<DatasetContext> getHoverBorderJoinStyleCallback() {
		return hoverBorderJoinStyleCallback;
	}

	/**
	 * Sets the border join style callback when element is hovered.
	 * 
	 * @param borderJoinStyleCallback the border join style callback when element is hovered.
	 */
	public void setHoverBorderJoinStyle(NativeCallback borderJoinStyleCallback) {
		// resets callback
		setHoverBorderJoinStyle((JoinStyleCallback<DatasetContext>) null);
		// stores value
		setValue(Property.HOVER_BORDER_JOIN_STYLE, borderJoinStyleCallback);
	}

	/**
	 * Sets the border join style callback when element is hovered.
	 * 
	 * @param borderJoinStyleCallback the border join style callback when element is hovered.
	 */
	public void setHoverBorderJoinStyle(JoinStyleCallback<DatasetContext> borderJoinStyleCallback) {
		// sets the callback
		this.hoverBorderJoinStyleCallback = borderJoinStyleCallback;
		// checks if callback is consistent
		if (borderJoinStyleCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.HOVER_BORDER_JOIN_STYLE, hoverBorderJoinStyleCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.HOVER_BORDER_JOIN_STYLE);
		}
	}

}