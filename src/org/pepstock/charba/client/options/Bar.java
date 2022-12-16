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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultBar;
import org.pepstock.charba.client.defaults.globals.DefaultBar;
import org.pepstock.charba.client.enums.BorderSkipped;
import org.pepstock.charba.client.items.Undefined;

/**
 * Bar elements are used to represent the bars in a bar chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class Bar extends AbstractElement<IsDefaultBar> implements IsDefaultBar, HasPointStyle {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BASE("base"),
		BORDER_SKIPPED("borderSkipped"),
		BORDER_RADIUS("borderRadius"),
		ENABLE_BORDER_RADIUS("enableBorderRadius"),
		HOVER_BORDER_RADIUS("hoverBorderRadius"),
		INFLATE_AMOUNT("inflateAmount");

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

	// instance of style of points handler
	private final PointStyleHandler pointStyleHandler;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param elements parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	protected Bar(Elements elements, Key childKey, IsDefaultBar defaultValues, NativeObject nativeObject) {
		super(elements, childKey, defaultValues, nativeObject);
		// creates point style handler
		this.pointStyleHandler = new PointStyleHandler(this, getDefaultValues(), getNativeObject());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasPointStyle#getPointStyleHandler()
	 */
	@Override
	public PointStyleHandler getPointStyleHandler() {
		return pointStyleHandler;
	}

	/**
	 * If <code>true</code>, it only shows the borderRadius of a bar when the bar is at the end of the stack.
	 * 
	 * @param enableBorderRadius if <code>true</code>, it only shows the borderRadius of a bar when the bar is at the end of the stack
	 */
	public void setEnableBorderRadius(boolean enableBorderRadius) {
		setValueAndAddToParent(Property.ENABLE_BORDER_RADIUS, enableBorderRadius);
	}

	/**
	 * If <code>true</code>, it only shows the borderRadius of a bar when the bar is at the end of the stack.
	 * 
	 * @return if <code>true</code>, it only shows the borderRadius of a bar when the bar is at the end of the stack
	 */
	@Override
	public boolean isEnableBorderRadius() {
		return getValue(Property.ENABLE_BORDER_RADIUS, getDefaultValues().isEnableBorderRadius());
	}

	/**
	 * Sets the base value for the bar in data units along the value axis.<br>
	 * If not set, defaults to the value axis base value.
	 * 
	 * @param base base value for the bar in data units along the value axis.<br>
	 *            If not set, defaults to the value axis base value
	 */
	public void setBase(double base) {
		setValueAndAddToParent(Property.BASE, base);
	}

	/**
	 * Returns the base value for the bar in data units along the value axis.<br>
	 * If not set, defaults to the value axis base value.
	 * 
	 * @return base value for the bar in data units along the value axis.<br>
	 *         If not set, defaults to the value axis base value
	 */
	@Override
	public double getBase() {
		return getValue(Property.BASE, getDefaultValues().getBase());
	}

	/**
	 * Sets the edge to skip drawing the border for.
	 * 
	 * @param borderskip to set <code>false</code> as border skipped.
	 */
	public void setBorderSkipped(boolean borderskip) {
		setValueAndAddToParent(Property.BORDER_SKIPPED, borderskip);
	}

	/**
	 * Sets the edge to skip drawing the border for.
	 * 
	 * @param position the edge to skip drawing the border for.
	 */
	public void setBorderSkipped(BorderSkipped position) {
		// checks if setting a false value
		if (BorderSkipped.FALSE.equals(position)) {
			// stores boolean value
			setValueAndAddToParent(Property.BORDER_SKIPPED, false);
		} else if (BorderSkipped.TRUE.equals(position)) {
			// stores boolean value
			setValueAndAddToParent(Property.BORDER_SKIPPED, true);
		} else {
			// otherwise stores the key value
			setValueAndAddToParent(Property.BORDER_SKIPPED, position);
		}
	}

	/**
	 * Returns the edge to skip drawing the border for.
	 * 
	 * @return the edge to skip drawing the border for.
	 */
	@Override
	public BorderSkipped getBorderSkipped() {
		// checks if 'false' has been set
		if (isType(Property.BORDER_SKIPPED, ObjectType.BOOLEAN)) {
			// gets boolean value
			boolean value = getValue(Property.BORDER_SKIPPED, Undefined.BOOLEAN);
			// returns is false
			return value ? BorderSkipped.TRUE : BorderSkipped.FALSE;
		}
		// otherwise returns the enum value as string
		return getValue(Property.BORDER_SKIPPED, BorderSkipped.values(), getDefaultValues().getBorderSkipped());
	}

	/**
	 * Sets the bar border radius (in pixels).
	 * 
	 * @param borderRadius the bar border radius (in pixels).
	 */
	public void setBorderRadius(int borderRadius) {
		setValueAndAddToParent(Property.BORDER_RADIUS, Checker.positiveOrZero(borderRadius));
	}

	/**
	 * Returns the bar border radius (in pixels).
	 * 
	 * @return the bar border radius (in pixels).
	 */
	@Override
	public int getBorderRadius() {
		return getValue(Property.BORDER_RADIUS, getDefaultValues().getBorderRadius());
	}

	/**
	 * Sets the bar border radius (in pixels) when hovered.
	 * 
	 * @param borderRadius the bar border radius (in pixels) when hovered.
	 */
	public void setHoverBorderRadius(int borderRadius) {
		setValueAndAddToParent(Property.HOVER_BORDER_RADIUS, Checker.positiveOrZero(borderRadius));
	}

	/**
	 * Returns the bar border radius (in pixels) when hovered.
	 * 
	 * @return the bar border radius (in pixels) when hovered.
	 */
	@Override
	public int getHoverBorderRadius() {
		return getValue(Property.HOVER_BORDER_RADIUS, getDefaultValues().getHoverBorderRadius());
	}

	/**
	 * Sets <code>true</code> if the amount of pixels to inflate the bar rectangles, when drawing, is automatically calculated.
	 * 
	 * @param autoInflateAmount <code>true</code> if the amount of pixels to inflate the bar rectangles, when drawing, is automatically calculated
	 */
	public void setAutoInflateAmount(boolean autoInflateAmount) {
		// checks if setting
		if (autoInflateAmount) {
			setValueAndAddToParent(Property.INFLATE_AMOUNT, DefaultBar.AUTO_INFLATE_AMOUNT);
		} else {
			// removes key
			remove(Property.INFLATE_AMOUNT);
		}
	}

	/**
	 * Returns <code>true</code> if the amount of pixels to inflate the bar rectangles, when drawing, is automatically calculated.
	 * 
	 * @return <code>true</code> if the amount of pixels to inflate the bar rectangles, when drawing, is automatically calculated
	 */
	@Override
	public boolean isAutoInflateAmount() {
		// checks if the property is NOT set as number
		if (!isType(Property.INFLATE_AMOUNT, ObjectType.NUMBER)) {
			// gets value
			String value = getValue(Property.INFLATE_AMOUNT, Undefined.STRING);
			// checks if the value is consistent
			if (DefaultBar.AUTO_INFLATE_AMOUNT.equalsIgnoreCase(value) || value == null) {
				// if here, the value is consistent or not exists
				return true;
			}
			// if here, the stored value is not consistent
			// then returns default
			return getDefaultValues().isAutoInflateAmount();
		}
		// if here, the inflate is a number
		// then returns false
		return false;
	}

	/**
	 * Sets the amount of pixels to inflate the bar rectangles, when drawing.
	 * 
	 * @param inflateAmount the amount of pixels to inflate the bar rectangles, when drawing
	 */
	public void setInflateAmount(int inflateAmount) {
		setValueAndAddToParent(Property.INFLATE_AMOUNT, Checker.positiveOrZero(inflateAmount));
	}

	/**
	 * Returns the amount of pixels to inflate the bar rectangles, when drawing.
	 * 
	 * @return the amount of pixels to inflate the bar rectangles, when drawing
	 */
	@Override
	public int getInflateAmount() {
		// checks if the property is NOT set as number
		if (isType(Property.INFLATE_AMOUNT, ObjectType.NUMBER)) {
			return getValue(Property.INFLATE_AMOUNT, getDefaultValues().getInflateAmount());
		}
		// if here, the inflate is a number
		// then returns false
		return Undefined.INTEGER;
	}

}