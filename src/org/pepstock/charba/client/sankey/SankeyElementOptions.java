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
package org.pepstock.charba.client.sankey;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.ChartElementOptions;
import org.pepstock.charba.client.options.AbstractElementFactory;
import org.pepstock.charba.client.options.ElementFactory;
import org.pepstock.charba.client.sankey.enums.ColorMode;

/**
 * Maps the out-of-the-box CHART.JS element options used to represents flows on the charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class SankeyElementOptions extends ChartElementOptions {

	/**
	 * Element factory to get "{@value SankeyElement#TYPE}" element.
	 */
	public static final ElementFactory<SankeyElementOptions> FACTORY = new SankeyElementOptionsFactory(SankeyElement.TYPE);

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		COLOR_FROM("colorFrom"),
		COLOR_MODE("colorMode"),
		COLOR_TO("colorTo"),
		HOVER_COLOR_FROM("hoverColorFrom"),
		HOVER_COLOR_TO("hoverColorTo");

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
	SankeyElementOptions(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the color mode between sankey elements.
	 * 
	 * @return the color mode between sankey elements
	 */
	public ColorMode getColorMode() {
		return getValue(Property.COLOR_MODE, ColorMode.values(), ColorMode.GRADIENT);
	}

	/**
	 * Sets the color mode between sankey elements.
	 * 
	 * @param colorMode the color mode between sankey elements
	 */
	public void setColorMode(ColorMode colorMode) {
		setValue(Property.COLOR_MODE, colorMode);
	}

	/**
	 * Returns the starting color of the flow between sankey elements.
	 * 
	 * @return the starting color of the flow between sankey elements
	 */
	public String getColorFromAsString() {
		return getValue(Property.COLOR_FROM, SankeyDataset.DEFAULT_COLOR_FROM);
	}

	/**
	 * Returns the starting color of the flow between sankey elements.
	 * 
	 * @return the starting color of the flow between sankey elements
	 */
	public IsColor getColorFrom() {
		return ColorBuilder.parse(getColorFromAsString());
	}

	/**
	 * Sets the starting color of the flow between sankey elements.
	 * 
	 * @param color the starting color of the flow between sankey elements
	 */
	public void setColorFrom(IsColor color) {
		setColorFrom(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the starting color of the flow between sankey elements.
	 * 
	 * @param color the starting color of the flow between sankey elements
	 */
	public void setColorFrom(String color) {
		setValue(Property.COLOR_FROM, color);
	}

	/**
	 * Returns the ending color of the flow between sankey elements.
	 * 
	 * @return the ending color of the flow between sankey elements
	 */
	public String getColorToAsString() {
		return getValue(Property.COLOR_TO, SankeyDataset.DEFAULT_COLOR_TO);
	}

	/**
	 * Returns the ending color of the flow between sankey elements.
	 * 
	 * @return the ending color of the flow between sankey elements
	 */
	public IsColor getColorTo() {
		return ColorBuilder.parse(getColorToAsString());
	}

	/**
	 * Sets the ending color of the flow between sankey elements.
	 * 
	 * @param color the ending color of the flow between sankey elements
	 */
	public void setColorTo(IsColor color) {
		setColorTo(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the ending color of the flow between sankey elements.
	 * 
	 * @param color the ending color of the flow between sankey elements
	 */
	public void setColorTo(String color) {
		setValue(Property.COLOR_TO, color);
	}

	/**
	 * Returns the starting color of the flow between sankey elements, when hovered.
	 * 
	 * @return the starting color of the flow between sankey elements, when hovered
	 */
	public String getHoverColorFromAsString() {
		return getValue(Property.HOVER_COLOR_FROM, getColorFromAsString());
	}

	/**
	 * Returns the starting color of the flow between sankey elements, when hovered.
	 * 
	 * @return the starting color of the flow between sankey elements, when hovered
	 */
	public IsColor getHoverColorFrom() {
		return ColorBuilder.parse(getHoverColorFromAsString());
	}

	/**
	 * Sets the starting color of the flow between sankey elements, when hovered.
	 * 
	 * @param color the starting color of the flow between sankey elements, when hovered
	 */
	public void setHoverColorFrom(IsColor color) {
		setHoverColorFrom(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the starting color of the flow between sankey elements, when hovered.
	 * 
	 * @param color the starting color of the flow between sankey elements, when hovered
	 */
	public void setHoverColorFrom(String color) {
		setValue(Property.HOVER_COLOR_FROM, color);
	}

	/**
	 * Returns the ending color of the flow between sankey elements, when hovered.
	 * 
	 * @return the ending color of the flow between sankey elements, when hovered
	 */
	public String getHoverColorToAsString() {
		return getValue(Property.HOVER_COLOR_TO, getColorToAsString());
	}

	/**
	 * Returns the ending color of the flow between sankey elements, when hovered.
	 * 
	 * @return the ending color of the flow between sankey elements, when hovered
	 */
	public IsColor getHoverColorTo() {
		return ColorBuilder.parse(getHoverColorToAsString());
	}

	/**
	 * Sets the ending color of the flow between sankey elements, when hovered.
	 * 
	 * @param color the ending color of the flow between sankey elements, when hovered
	 */
	public void setHoverColorTo(IsColor color) {
		setHoverColorTo(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the ending color of the flow between sankey elements, when hovered.
	 * 
	 * @param color the ending color of the flow between sankey elements, when hovered
	 */
	public void setHoverColorTo(String color) {
		setValue(Property.HOVER_COLOR_TO, color);
	}

	/**
	 * Specific element factory for sankey element options.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class SankeyElementOptionsFactory extends AbstractElementFactory<SankeyElementOptions> {

		/**
		 * Creates the factory by the key of object, as string.
		 * 
		 * @param elementKeyAsString the key of object, as string.
		 */
		private SankeyElementOptionsFactory(String elementKeyAsString) {
			super(elementKeyAsString);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public SankeyElementOptions create(NativeObject nativeObject) {
			return new SankeyElementOptions(nativeObject);
		}

	}
}