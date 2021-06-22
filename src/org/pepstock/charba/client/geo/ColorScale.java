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

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.geo.enums.Interpolate;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ColorScale extends LegendScale {
	
	/**
	 * Default missing color options, <b>{@value DEFAULT_MISSING_COLOR}</b>.
	 */
	public static final String DEFAULT_MISSING_COLOR = HtmlColor.TRANSPARENT.toRGBA();

	/**
	 * Default quantize options, <b>{@value DEFAULT_QUANTIZE}</b>.
	 */
	public static final int DEFAULT_QUANTIZE = 0;
	
	/**
	 * Name of properties of native object for projection scale.
	 */
	private enum Property implements Key
	{
		MISSING("missing"),
		QUANTIZE("quantize"),
		INTERPOLATE("interpolate");

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

	ColorScale(AbstractNode parent, Key childKey, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
	}

	/**
	 * Sets the missing color.
	 * 
	 * @param missingColor the missing color.
	 */
	public void setMissingColor(IsColor missingColor) {
		setMissingColor(IsColor.checkAndGetValue(missingColor));
	}

	/**
	 * Sets the missing color.
	 * 
	 * @param missingColor the missing color.
	 */
	public void setMissingColor(String missingColor) {
		setValueAndAddToParent(Property.MISSING, missingColor);
	}

	/**
	 * Returns the missing color.
	 * 
	 * @return the missing color.
	 */
	public String getMissingColorAsString() {
		return getValue(Property.MISSING, DEFAULT_MISSING_COLOR);
	}

	/**
	 * Returns the missing color.
	 * 
	 * @return the missing color.
	 */
	public IsColor getMissingColor() {
		return ColorBuilder.parse(getMissingColorAsString());
	}

	/**
	 * Sets the amount of pieces to allow to split the color scale in N quantized equal bins.
	 * 
	 * @param quantize the amount of pieces to allow to split the color scale in N quantized equal bins
	 */
	public void setQuantize(int quantize) {
		setValueAndAddToParent(Property.QUANTIZE, quantize);
	}

	/**
	 * Returns the amount of pieces to allow to split the color scale in N quantized equal bins.
	 * 
	 * @return the amount of pieces to allow to split the color scale in N quantized equal bins
	 */
	public int getQuantize() {
		return getValue(Property.QUANTIZE, DEFAULT_QUANTIZE);
	}
	
	/**
	 * Sets the color interpolation of the scale.
	 * 
	 * @param interpolate the color interpolation of the scale
	 */
	public void setInterpolate(Interpolate interpolate) {
		setValueAndAddToParent(Property.INTERPOLATE, interpolate);
	}

	/**
	 * Returns the color interpolation of the scale.
	 * 
	 * @return the color interpolation of the scale
	 */
	public Interpolate getInterpolate() {
		return getValue(Property.INTERPOLATE, Interpolate.values(), Interpolate.BLUES);
	}

}
