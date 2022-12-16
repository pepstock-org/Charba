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
import org.pepstock.charba.client.callbacks.CircularCallback;
import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.JoinStyleCallback;
import org.pepstock.charba.client.callbacks.NativeCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyBooleanCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.callbacks.ScriptableUtil;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.enums.JoinStyle;

/**
 * The polar area chart allows a number of properties to be specified for each dataset. These are used to set display properties for a specific dataset.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class PolarAreaDataset extends HoverDataset implements HasBorderAlign {

	// callback proxy to invoke the border join style function
	private final CallbackProxy<ProxyStringCallback> borderJoinStyleCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover border join style function
	private final CallbackProxy<ProxyStringCallback> hoverBorderJoinStyleCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the circular function
	private final CallbackProxy<ProxyBooleanCallback> circularCallbackProxy = JsHelper.get().newCallbackProxy();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BORDER_JOIN_STYLE("borderJoinStyle"),
		CIRCULAR("circular"),
		HOVER_BORDER_JOIN_STYLE("hoverBorderJoinStyle");

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
	// border join style callback instance
	private JoinStyleCallback<DatasetContext> borderJoinStyleCallback = null;
	// hover border join style callback instance
	private JoinStyleCallback<DatasetContext> hoverBorderJoinStyleCallback = null;
	// circular callback instance
	private CircularCallback circularCallback = null;

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 */
	public PolarAreaDataset() {
		this(Dataset.DEFAULT_HIDDEN);
	}

	/**
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 * 
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	public PolarAreaDataset(boolean hidden) {
		this(ChartType.POLAR_AREA, hidden);
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 */
	public PolarAreaDataset(IsDefaultOptions defaultValues) {
		this(defaultValues, Dataset.DEFAULT_HIDDEN);
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	public PolarAreaDataset(IsDefaultOptions defaultValues, boolean hidden) {
		this(ChartType.POLAR_AREA, defaultValues, hidden);
	}

	/**
	 * Creates the dataset using chart type related to the dataset.
	 * 
	 * @param type chart type related to the dataset
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	protected PolarAreaDataset(Type type, boolean hidden) {
		this(type, null, hidden);
	}

	/**
	 * Creates the dataset using a default and chart type related to the dataset.
	 * 
	 * @param type chart type related to the dataset
	 * @param defaultValues default options
	 * @param hidden if <code>true</code>, it will be initially hidden.
	 */
	protected PolarAreaDataset(Type type, IsDefaultOptions defaultValues, boolean hidden) {
		super(type, defaultValues, hidden);
		// creates border align handler instance
		this.borderAlignHandler = new BorderAlignHandler(getNativeObject(), getDefaultValues());
		// sets function to proxy callback in order to invoke the java interface
		this.borderJoinStyleCallbackProxy.setCallback(context -> onBorderJoinStyle(createContext(context), getBorderJoinStyleCallback(), getDefaultValues().getElements().getArc().getBorderJoinStyle()));
		// sets function to proxy callback in order to invoke the java interface
		this.hoverBorderJoinStyleCallbackProxy.setCallback(context -> onBorderJoinStyle(createContext(context), getHoverBorderJoinStyleCallback(), getDefaultValues().getElements().getArc().getBorderJoinStyle()));
		// sets function to proxy callback in order to invoke the java interface
		this.circularCallbackProxy.setCallback(context -> ScriptableUtil.getOptionValue(new DatasetContext(context), getCircularCallback(), getDefaultValues().getElements().getArc().isCircular()));
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
	 * Sets <code>true</code> if the arc is curved.
	 * 
	 * @param circular <code>true</code> if the arc is curved
	 */
	public void setCircular(int circular) {
		// resets callback
		setCircular((CircularCallback) null);
		// stores value
		setValue(Property.CIRCULAR, circular);
	}

	/**
	 * Returns <code>true</code> if the arc is curved.
	 * 
	 * @return <code>true</code> if the arc is curved
	 */
	public boolean isCircular() {
		return getValue(Property.CIRCULAR, getDefaultValues().getElements().getArc().isCircular());
	}

	// --------------------------
	// CALLBACK
	// --------------------------

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

	/**
	 * Sets <code>true</code> if the arc is curved
	 * 
	 * @param circularCallback <code>true</code> if the arc is curved
	 */
	public void setCircular(NativeCallback circularCallback) {
		// resets callback
		setCircular((CircularCallback) null);
		// stores value
		setValue(Property.HOVER_BORDER_JOIN_STYLE, circularCallback);
	}

	/**
	 * Returns <code>true</code> if the arc is curved.
	 * 
	 * @return <code>true</code> if the arc is curved.
	 */
	public CircularCallback getCircularCallback() {
		return circularCallback;
	}

	/**
	 * Sets <code>true</code> if the arc is curved
	 * 
	 * @param circularCallback <code>true</code> if the arc is curved
	 */
	public void setCircular(CircularCallback circularCallback) {
		// sets the callback
		this.circularCallback = circularCallback;
		// checks if callback is consistent
		if (circularCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.CIRCULAR, circularCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.CIRCULAR);
		}
	}
}