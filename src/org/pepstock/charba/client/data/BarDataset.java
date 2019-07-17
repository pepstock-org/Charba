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

import org.pepstock.charba.client.callbacks.BorderSkippedCallback;
import org.pepstock.charba.client.callbacks.ScriptableContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.enums.BorderSkipped;
import org.pepstock.charba.client.options.Scales;
import org.pepstock.charba.client.utils.Utilities;

/**
 * The bar chart allows a number of properties to be specified for each dataset. These are used to set display properties for a
 * specific dataset.<br>
 * Some properties can be specified as an array. If these are set to an array value, the first value applies to the first bar,
 * the second value to the second bar, and so on.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class BarDataset extends HovingFlexDataset implements HasDataPoints {
	// default label
	private static final String DEFAULT_LABEL = Utilities.EMPTY_STRING;

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the border skipped function
	private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> borderSkippedCallbackProxy = JsHelper.get().newCallbackProxy();

	// border skipped callback instance
	private BorderSkippedCallback borderSkippedCallback = null;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		X_AXIS_ID("xAxisID"),
		Y_AXIS_ID("yAxisID"),
		BORDER_SKIPPED("borderSkipped"),
		BORDER_WIDTH("borderWidth");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
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
		borderSkippedCallbackProxy.setCallback((contextFunction, context) -> onBorderSkipped(context));
	}

	/**
	 * Returns the label for the dataset which appears in the legend and tooltips.
	 * 
	 * @return the label for the dataset which appears in the legend and tooltips.
	 */
	@Override
	public String getLabel() {
		return getValue(Dataset.Property.LABEL, DEFAULT_LABEL);
	}

	/**
	 * Sets the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x axis.
	 * 
	 * @param xAxisID the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found
	 *            x axis.
	 */
	public void setXAxisID(String xAxisID) {
		setValue(Property.X_AXIS_ID, xAxisID);
	}

	/**
	 * Returns the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x
	 * axis.
	 * 
	 * @return the ID of the x axis to plot this dataset on. If not specified, this defaults to the ID of the first found x
	 *         axis.
	 */
	public String getXAxisID() {
		return getValue(Property.X_AXIS_ID, Scales.DEFAULT_X_AXIS_ID);
	}

	/**
	 * Sets the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y axis.
	 * 
	 * @param yAxisID the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found
	 *            y axis.
	 */
	public void setYAxisID(String yAxisID) {
		setValue(Property.Y_AXIS_ID, yAxisID);
	}

	/**
	 * Returns the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y
	 * axis.
	 * 
	 * @return the ID of the y axis to plot this dataset on. If not specified, this defaults to the ID of the first found y
	 *         axis.
	 */
	public String getYAxisID() {
		return getValue(Property.Y_AXIS_ID, Scales.DEFAULT_Y_AXIS_ID);
	}

	/**
	 * Sets the stroke width of the bar in pixels.
	 * 
	 * @param borderWidth the stroke width of the bar in pixels.
	 */
	public void setBorderWidth(BarBorderWidth borderWidth) {
		// stores value
		setValue(Property.BORDER_WIDTH, borderWidth);
	}

	/**
	 * Returns the stroke width of the bar in pixels. If a callback or an array have been set, returns an empty object.
	 * 
	 * @return list of the stroke width of the bar in pixels. If a callback or an array have been set, returns an empty object
	 */
	public BarBorderWidth getBorderWidthAsItem() {
		// gets object type
		ObjectType type = type(Property.BORDER_WIDTH);
		// checks if borer width has been set by an object
		if (ObjectType.OBJECT.equals(type)) {
			// returns the array
			return new BarBorderWidth(getValue(Property.BORDER_WIDTH));
		}
		// if here, is not a bar border width object
		// then creates new border width
		BarBorderWidth borderWidth = new BarBorderWidth();
		// checks if borer width has been set by an object
		if (ObjectType.NUMBER.equals(type)) {
			// reads number and set to object
			borderWidth.set(getValue(Property.BORDER_WIDTH, getDefaultBorderWidth()));
		}
		// returns the border width object
		return borderWidth;
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
			setValue(Property.BORDER_SKIPPED, BorderSkipped.FALSE);
		}
	}

	/**
	 * Sets the edge to skip drawing the border for.
	 * 
	 * @param borderskip the edge to skip drawing the border for.
	 */
	public void setBorderSkipped(BorderSkipped borderskip) {
		// resets callbacks
		setBorderSkipped((BorderSkippedCallback) null);
		// checks if setting a false value
		if (BorderSkipped.FALSE.equals(borderskip)) {
			// stores boolean value
			setValue(Property.BORDER_SKIPPED, false);
		} else {
			// otherwise stores the key value
			setValue(Property.BORDER_SKIPPED, borderskip);
		}
	}

	/**
	 * Returns the edge to skip drawing the border for.
	 * 
	 * @return the edge to skip drawing the border for.
	 */
	public BorderSkipped getBorderSkipped() {
		// checks if 'false' has been set
		if (ObjectType.BOOLEAN.equals(type(Property.BORDER_SKIPPED))) {
			// returns is false
			return BorderSkipped.FALSE;
		} else if (ObjectType.FUNCTION.equals(type(Property.BORDER_SKIPPED))) {
			// checks if a callback has been set
			// returns defaults
			return getDefaultValues().getElements().getRectangle().getBorderSkipped();
		}
		// otherwise returns the enum value as string
		return getValue(Property.BORDER_SKIPPED, BorderSkipped.class, getDefaultValues().getElements().getRectangle().getBorderSkipped());
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
			setValue(Property.BORDER_SKIPPED, borderSkippedCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.BORDER_SKIPPED);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getDefaultBackgroundColorAsString()
	 */
	@Override
	protected String getDefaultBackgroundColorAsString() {
		return getDefaultValues().getElements().getRectangle().getBackgroundColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getDefaultBorderColorAsString()
	 */
	@Override
	protected String getDefaultBorderColorAsString() {
		return getDefaultValues().getElements().getRectangle().getBorderColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.data.Dataset#getDefaultBorderWidth()
	 */
	@Override
	protected int getDefaultBorderWidth() {
		return getDefaultValues().getElements().getRectangle().getBorderWidth();
	}

	/**
	 * Returns an object (boolean or {@link BorderSkipped}) when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @return a object property value, as boolean or {@link BorderSkipped}.
	 */
	private Object onBorderSkipped(ScriptableContext context) {
		// gets value
		BorderSkipped value = ScriptableUtils.getOptionValueAsString(context, borderSkippedCallback);
		BorderSkipped result = value == null ? getDefaultValues().getElements().getRectangle().getBorderSkipped() : value;
		// checks if is boolean
		if (BorderSkipped.FALSE.equals(result)) {
			return false;
		} else {
			// returns the string value
			return result.value();
		}
	}
}