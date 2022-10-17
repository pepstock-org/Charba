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
package org.pepstock.charba.client.geo;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.data.BarBorderWidth;
import org.pepstock.charba.client.items.BarElementOptions;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.options.AbstractElementFactory;
import org.pepstock.charba.client.options.Bar;
import org.pepstock.charba.client.options.ElementFactory;

/**
 * The styling of the new element {@link GeoFeatureElementOptions} is based on {@link Bar} element with some additional options for the outline and graticule.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class GeoFeatureElementOptions extends BarElementOptions {

	/**
	 * Element factory to get "{@value GeoFeatureElement#TYPE}" element.
	 */
	public static final ElementFactory<GeoFeatureElementOptions> FACTORY = new GeoFeatureElementOptionsFactory(GeoFeatureElement.TYPE);

	/**
	 * Default outline border width options, <b>{@value DEFAULT_OUTLINE_BORDER_WIDTH}</b>.
	 */
	static final int DEFAULT_OUTLINE_BORDER_WIDTH = 0;

	/**
	 * Default graticule border color options, <b>{@value DEFAULT_GRATICULE_BORDER_COLOR}</b>.
	 */
	static final String DEFAULT_GRATICULE_BORDER_COLOR = "#CCCCCC";

	/**
	 * Default graticule border width options, <b>{@value DEFAULT_OUTLINE_BORDER_WIDTH}</b>.
	 */
	static final int DEFAULT_GRATICULE_BORDER_WIDTH = 0;

	/**
	 * Default border width options, <b>{@value DEFAULT_BORDER_WIDTH}</b>.
	 */
	static final int DEFAULT_BORDER_WIDTH = 0;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		OUTLINE_BACKGROUND_COLOR("outlineBackgroundColor"),
		OUTLINE_BORDER_COLOR("outlineBorderColor"),
		OUTLINE_BORDER_WIDTH("outlineBorderWidth"),
		GRATICULE_BORDER_COLOR("graticuleBorderColor"),
		GRATICULE_BORDER_WIDTH("graticuleBorderWidth");

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

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	GeoFeatureElementOptions(NativeObject nativeObject) {
		super(nativeObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.items.ChartElementOptions#getDefaultBorderWidth()
	 */
	@Override
	protected int getDefaultBorderWidth() {
		return DEFAULT_BORDER_WIDTH;
	}

	/**
	 * It doesn't do anything because you can set the border width only by a number.
	 * 
	 * @param borderWidth the border width.
	 */
	@Override
	public void setBorderWidth(BarBorderWidth borderWidth) {
		// do nothing
	}

	/**
	 * Sets the outline background color.
	 * 
	 * @param backgroundColor the outline background color.
	 */
	public void setOutlineBackgroundColor(IsColor backgroundColor) {
		setOutlineBackgroundColor(IsColor.checkAndGetValue(backgroundColor));
	}

	/**
	 * Sets the outline background color.
	 * 
	 * @param backgroundColor the outline background color.
	 */
	public void setOutlineBackgroundColor(String backgroundColor) {
		setValueAndAddToParent(Property.OUTLINE_BACKGROUND_COLOR, backgroundColor);
	}

	/**
	 * Returns the outline background color.
	 * 
	 * @return the outline background color.
	 */
	public String getOutlineBackgroundColorAsString() {
		return getValue(Property.OUTLINE_BACKGROUND_COLOR, Undefined.STRING);
	}

	/**
	 * Returns the outline background color.
	 * 
	 * @return the outline background color.
	 */
	public IsColor getOutlineBackgroundColor() {
		// gets color
		String color = getOutlineBackgroundColorAsString();
		// if color is consistent, parses to a color object
		return color != null ? ColorBuilder.parse(color) : null;
	}

	/**
	 * Sets the outline border width.
	 * 
	 * @param borderWidth the outline border width.
	 */
	public void setOutlineBorderWidth(int borderWidth) {
		setValueAndAddToParent(Property.OUTLINE_BORDER_WIDTH, Checker.positiveOrZero(borderWidth));
	}

	/**
	 * Returns the outline border width.
	 * 
	 * @return the outline border width.
	 */
	public int getOutlineBorderWidth() {
		return getValue(Property.OUTLINE_BORDER_WIDTH, DEFAULT_OUTLINE_BORDER_WIDTH);
	}

	/**
	 * Sets the outline border color.
	 * 
	 * @param borderColor the outline border color.
	 */
	public void setOutlineBorderColor(IsColor borderColor) {
		setOutlineBorderColor(IsColor.checkAndGetValue(borderColor));
	}

	/**
	 * Sets the outline border color.
	 * 
	 * @param borderColor the outline border color.
	 */
	public void setOutlineBorderColor(String borderColor) {
		setValueAndAddToParent(Property.OUTLINE_BORDER_COLOR, borderColor);
	}

	/**
	 * Returns the outline border color.
	 * 
	 * @return the outline border color.
	 */
	public String getOutlineBorderColorAsString() {
		return getValue(Property.OUTLINE_BORDER_COLOR, Defaults.get().getGlobal().getBorderColorAsString());
	}

	/**
	 * Returns the outline border color.
	 * 
	 * @return the outline border color.
	 */
	public IsColor getOutlineBorderColor() {
		return ColorBuilder.parse(getOutlineBorderColorAsString());
	}

	// ------------------------------------------------------
	// GRATICULE
	// ------------------------------------------------------

	/**
	 * Sets the graticule border width.
	 * 
	 * @param borderWidth the graticule border width.
	 */
	public void setGraticuleBorderWidth(int borderWidth) {
		setValueAndAddToParent(Property.GRATICULE_BORDER_WIDTH, Checker.positiveOrZero(borderWidth));
	}

	/**
	 * Returns the graticule border width.
	 * 
	 * @return the graticule border width.
	 */
	public int getGraticuleBorderWidth() {
		return getValue(Property.GRATICULE_BORDER_WIDTH, DEFAULT_GRATICULE_BORDER_WIDTH);
	}

	/**
	 * Sets the graticule border color.
	 * 
	 * @param borderColor the graticule border color.
	 */
	public void setGraticuleBorderColor(String borderColor) {
		setValueAndAddToParent(Property.GRATICULE_BORDER_COLOR, borderColor);
	}

	/**
	 * Sets the graticule border color.
	 * 
	 * @param borderColor the graticule border color.
	 */
	public void setGraticuleBorderColor(IsColor borderColor) {
		setGraticuleBorderColor(IsColor.checkAndGetValue(borderColor));
	}

	/**
	 * Returns the graticule border color.
	 * 
	 * @return the graticule border color.
	 */
	public String getGraticuleBorderColorAsString() {
		return getValue(Property.GRATICULE_BORDER_COLOR, DEFAULT_GRATICULE_BORDER_COLOR);
	}

	/**
	 * Returns the graticule border color.
	 * 
	 * @return the graticule border color.
	 */
	public IsColor getGraticuleBorderColor() {
		return ColorBuilder.parse(getGraticuleBorderColorAsString());
	}

	/**
	 * Specific element factory for matrix element options.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class GeoFeatureElementOptionsFactory extends AbstractElementFactory<GeoFeatureElementOptions> {

		/**
		 * Creates the factory by the key of object, as string.
		 * 
		 * @param elementKeyAsString the key of object, as string.
		 */
		private GeoFeatureElementOptionsFactory(String elementKeyAsString) {
			super(elementKeyAsString);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public GeoFeatureElementOptions create(NativeObject nativeObject) {
			return new GeoFeatureElementOptions(nativeObject);
		}

	}
}
