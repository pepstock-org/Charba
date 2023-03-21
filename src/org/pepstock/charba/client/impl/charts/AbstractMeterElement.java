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
package org.pepstock.charba.client.impl.charts;

import org.pepstock.charba.client.callbacks.ColorCallback;
import org.pepstock.charba.client.callbacks.FontCallback;
import org.pepstock.charba.client.colors.Color;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.items.FontItem;
import org.pepstock.charba.client.options.IsFont;

/**
 * Base class to map the meter charts labels, value and description.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class AbstractMeterElement {

	/**
	 * Default color of render, <b>rgb(128, 128, 128)</b>
	 */
	public static final IsColor DEFAULT_FONT_COLOR = new Color(128, 128, 128);

	// default color of render as string
	static final String DEFAULT_FONT_COLOR_AS_STRING = DEFAULT_FONT_COLOR.toRGBA();

	private final IsFont font;

	private boolean display = true;

	private boolean autoFontSize = true;

	private String color = null;

	private ColorCallback<MeterContext> colorCallback = null;

	private FontCallback<MeterContext> fontCallback = null;

	// -------------------------
	// instance used to cache the font item
	// without creating new font every craw cycle
	private FontItem fontItem = null;

	/**
	 * Creates the element getting the font defaults as argument.
	 * 
	 * @param defaultValues font defaults to use to initialize the font option
	 */
	AbstractMeterElement(IsDefaultFont defaultValues) {
		// checks and creates the font
		this.font = new FontItem(defaultValues);
	}

	/**
	 * Returns the font item.<br>
	 * It's called from {@link BaseMeterController}.
	 *
	 * @return the font item
	 */
	final FontItem getFontItem() {
		return fontItem;
	}

	/**
	 * Sets the font item for next computation.<br>
	 * It's called from {@link BaseMeterChart#applyConfiguration()}.
	 * 
	 * @param fontItem font item instance to be stored
	 */
	final void setFontItem(FontItem fontItem) {
		this.fontItem = fontItem;
	}

	/**
	 * Sets <code>true</code> whether the element label should be displayed.
	 * 
	 * @param display <code>true</code> whether the element label should be displayed
	 */
	public final void setDisplay(boolean display) {
		this.display = display;
	}

	/**
	 * Returns <code>true</code> whether the element label should be displayed.
	 * 
	 * @return <code>true</code> whether the element label should be displayed
	 */
	public final boolean isDisplay() {
		return display;
	}

	/**
	 * Returns the font of the label.
	 * 
	 * @return the font of the label
	 */
	public final IsFont getFont() {
		return font;
	}

	/**
	 * Returns <code>true</code> if the font size of the rendering will be automatically calculated, based on dimension of the area for rendering.
	 * 
	 * @return <code>true</code> if the font size of the rendering will be automatically calculated, based on dimension of the area for rendering
	 */
	public final boolean isAutoFontSize() {
		return autoFontSize;
	}

	/**
	 * Sets <code>true</code> if the font size of the rendering will be automatically calculated, based on dimension of the area for rendering.
	 * 
	 * @param autoFontSize <code>true</code> if the font size of the rendering will be automatically calculated, based on dimension of the area for rendering
	 */
	public final void setAutoFontSize(boolean autoFontSize) {
		this.autoFontSize = autoFontSize;
	}

	/**
	 * Sets the font color of the label.
	 * 
	 * @param color the font color of the label
	 */
	public final void setColor(String color) {
		// resets callback
		setColor((ColorCallback<MeterContext>) null);
		// stores value
		this.color = color;
	}

	/**
	 * Sets the font color of the label.
	 * 
	 * @param color the font color of the label
	 */
	public final void setColor(IsColor color) {
		// resets callback
		setColor((ColorCallback<MeterContext>) null);
		// stores value
		this.color = IsColor.checkAndGetValue(color);
	}

	/**
	 * Returns the font color of the label.
	 * 
	 * @return the font color of the label
	 */
	public final String getColorAsString() {
		return color;
	}

	/**
	 * Returns the font color of the label.
	 * 
	 * @return the font color of the label
	 */
	public final IsColor getColor() {
		// checks if color is consistent
		if (color != null) {
			return ColorBuilder.parse(getColorAsString());
		}
		// if here, the color is not set
		return null;
	}

	// --------------------------------------
	// CALLBACK
	// --------------------------------------

	/**
	 * Returns the callback to customize the font color for rendered label in the chart.
	 * 
	 * @return the callback to customize the font color for rendered label in the chart
	 */
	public final ColorCallback<MeterContext> getColorCallback() {
		return colorCallback;
	}

	/**
	 * Sets the callback to customize the font color for rendered label in the chart.
	 * 
	 * @param colorCallback the callback to customize the font color for rendered label in the chart
	 */
	public final void setColor(ColorCallback<MeterContext> colorCallback) {
		this.colorCallback = colorCallback;
	}

	/**
	 * Returns the callback to customize the font for rendered label in the chart.
	 * 
	 * @return the callback to customize the font for rendered label in the chart
	 */
	public final FontCallback<MeterContext> getFontCallback() {
		return fontCallback;
	}

	/**
	 * Sets the callback to customize the font for rendered label in the chart.
	 * 
	 * @param fontCallback the callback to customize the font for rendered label in the chart
	 */
	public final void setFont(FontCallback<MeterContext> fontCallback) {
		this.fontCallback = fontCallback;
	}
}