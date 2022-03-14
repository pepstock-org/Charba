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
package org.pepstock.charba.client.impl.plugins;

import org.pepstock.charba.client.callbacks.CrosshairFormatterCallback;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyStringCallback;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.CallbackPropertyHandler;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.data.BarBorderRadius;
import org.pepstock.charba.client.options.IsFont;

/**
 * {@link Crosshair#ID} plugin configuration element in order to have in the chart the label on the axes where the mouse is positioned.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class CrosshairLabel extends NativeObjectContainer implements IsCrosshairDefaultLabel {

	/**
	 * Default font color for label, {@link HtmlColor#WHITE}.
	 */
	public static final IsColor DEFAULT_COLOR = HtmlColor.WHITE;

	/**
	 * Default font color for label, as string, {@link HtmlColor#WHITE}.
	 */
	public static final String DEFAULT_COLOR_AS_STRING = DEFAULT_COLOR.toRGBA();

	/**
	 * Default background color for label, <b>rgb(110, 112, 121)</b>.
	 */
	public static final IsColor DEFAULT_BACKGROUND_COLOR = ColorBuilder.build(110, 112, 121);

	/**
	 * Default background color for label, as string, <b>rgb(110, 112, 121)</b>.
	 */
	public static final String DEFAULT_BACKGROUND_COLOR_AS_STRING = DEFAULT_BACKGROUND_COLOR.toRGB();

	/**
	 * Default flag if crosshair label must be showed in the chart, value is <b>{@value DEFAULT_DISPLAY}</b>.
	 */
	public static final boolean DEFAULT_DISPLAY = true;

	/**
	 * Default padding for crosshair label, value is <b>{@value DEFAULT_PADDING}</b>.
	 */
	public static final int DEFAULT_PADDING = 6;

	/**
	 * Default border radius for crosshair label, value is <b>{@value DEFAULT_PADDING}</b>.
	 */
	public static final int DEFAULT_BORDER_RADIUS = 6;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BACKGROUND_COLOR("backgroundColor"),
		BORDER_RADIUS("borderRadius"),
		COLOR("color"),
		DISPLAY("display"),
		FONT("font"),
		PADDING("padding"),
		// internal property to set the callbacks
		CHARBA_FORMATTER_CALLBACK("formatter");

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

	// callback proxy to invoke the formatter function
	private final CallbackProxy<ProxyStringCallback> formatterCallbackProxy = JsHelper.get().newCallbackProxy();
	// progress callback
	private static final CallbackPropertyHandler<CrosshairFormatterCallback> FORMATTER_CALLBACK = new CallbackPropertyHandler<>(Property.CHARBA_FORMATTER_CALLBACK);

	// defaults global options instance
	private IsCrosshairDefaultLabel defaultOptions;
	// label font instance
	private final CrosshairLabelFont font;
	// bar border radius instance
	private BarBorderRadius borderRadiusObject = null;

	/**
	 * Creates new label element, using stored native object instance and the default values options.
	 * 
	 * @param nativeObject stored label values in the native object to read.
	 * @param defaultOptions default crosshair label options to returns the default when required.
	 */
	CrosshairLabel(NativeObject nativeObject, IsCrosshairDefaultLabel defaultOptions) {
		super(nativeObject);
		// checks the default values
		this.defaultOptions = checkDefaultValuesArgument(defaultOptions);
		// gets font
		this.font = new CrosshairLabelFont(this.defaultOptions.getFont(), getNativeObject());
		// stores border radius
		// getting value from object
		this.borderRadiusObject = new BarBorderRadius(getBorderRadius());
		// stores incremental ID
		setNewIncrementalId();
	}

	/**
	 * Returns the font object.
	 * 
	 * @return the font object.
	 */
	@Override
	public IsFont getFont() {
		return font;
	}

	/**
	 * Sets <code>true</code> if label will be applied in the chart, otherwise <code>false</code>.
	 * 
	 * @param display <code>true</code> if label will be applied in the chart, otherwise <code>false</code>
	 */
	public void setDisplay(boolean display) {
		setValue(Property.DISPLAY, display);
	}

	/**
	 * Returns <code>true</code> if label will be applied in the chart, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if label will be applied in the chart, otherwise <code>false</code>
	 */
	@Override
	public boolean isDisplay() {
		return getValue(Property.DISPLAY, defaultOptions.isDisplay());
	}

	/**
	 * Returns the crosshair label font color.
	 * 
	 * @return the crosshair label font color.
	 */
	@Override
	public String getColorAsString() {
		return getValue(Property.COLOR, defaultOptions.getColorAsString());
	}

	/**
	 * Returns the crosshair label font color.
	 * 
	 * @return the crosshair label font color.
	 */
	public IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

	/**
	 * Sets the crosshair label font color.
	 * 
	 * @param color the crosshair label font color.
	 */
	public void setColor(String color) {
		setValue(Property.COLOR, color);
	}

	/**
	 * Set the crosshair label font color.
	 * 
	 * @param color the crosshair label font color.
	 */
	public void setColor(IsColor color) {
		setColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the padding of crosshair label.
	 * 
	 * @param padding padding of crosshair label
	 */
	public void setPadding(int padding) {
		setValue(Property.PADDING, Checker.positiveOrZero(padding));
	}

	/**
	 * Returns the padding of crosshair label.
	 * 
	 * @return the padding of crosshair label
	 */
	@Override
	public int getPadding() {
		return getValue(Property.PADDING, defaultOptions.getPadding());
	}

	/**
	 * Sets the background color.
	 * 
	 * @param color the background color.
	 */
	public void setBackgroundColor(String color) {
		setValue(Property.BACKGROUND_COLOR, color);
	}

	/**
	 * Sets the background color.
	 * 
	 * @param color the background color.
	 */
	public void setBackgroundColor(IsColor color) {
		setBackgroundColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Returns the background color.
	 * 
	 * @return the background color.
	 */
	@Override
	public String getBackgroundColorAsString() {
		return getValue(Property.BACKGROUND_COLOR, defaultOptions.getBackgroundColorAsString());
	}

	/**
	 * Returns the background color.
	 * 
	 * @return the background color.
	 */
	public IsColor getBackgroundColor() {
		return ColorBuilder.parse(getBackgroundColorAsString());
	}

	/**
	 * Sets the label border radius (in pixels).
	 * 
	 * @param borderRadius the label border radius (in pixels).
	 */
	public void setBorderRadius(int borderRadius) {
		setValue(Property.BORDER_RADIUS, Checker.positiveOrZero(borderRadius));
		// stores border radius
		this.borderRadiusObject = new BarBorderRadius(getBorderRadius());
	}

	/**
	 * Returns the label border radius (in pixels).
	 * 
	 * @return the label border radius (in pixels).
	 */
	@Override
	public int getBorderRadius() {
		return getValue(Property.BORDER_RADIUS, defaultOptions.getBorderRadius());
	}

	// ------------------
	// CALLBACKS
	// ------------------

	/**
	 * Returns the callback which can be implemented to change the text of label.
	 * 
	 * @return the callback which can be implemented to change the text of label
	 */
	@Override
	public CrosshairFormatterCallback getCrosshairFormatterCallback() {
		return FORMATTER_CALLBACK.getCallback(this, defaultOptions.getCrosshairFormatterCallback());
	}

	/**
	 * Sets the callback which can be implemented to change the text of label,
	 * 
	 * @param formatterCallback the callback which can be implemented to change the text of label
	 */
	public void setCrosshairFormatterCallback(CrosshairFormatterCallback formatterCallback) {
		FORMATTER_CALLBACK.setCallback(this, getIncrementalId(), formatterCallback, formatterCallbackProxy.getProxy());
	}

	// ------------------
	// INTERNALS
	// ------------------

	/**
	 * Returns the {@link BarBorderRadius} instance.
	 * 
	 * @return the {@link BarBorderRadius} instance
	 */
	BarBorderRadius getBarBordeRadius() {
		return this.borderRadiusObject;
	}
}
