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
package org.pepstock.charba.client.options;

import java.util.List;

import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultScaleLines;

/**
 * Abstract configuration defines border options for axis lines elements.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 * @param <D> defaults provider class
 *
 */
abstract class AbstractScaleLines<D> extends AbstractModel<AbstractScale, D> implements IsDefaultScaleLines {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BORDER_DASH("borderDash"),
		BORDER_DASH_OFFSET("borderDashOffset");

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
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param scale scale/axis of this object.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	AbstractScaleLines(AbstractScale scale, Key childKey, D defaultValues, NativeObject nativeObject) {
		super(scale, childKey, defaultValues, nativeObject);
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines
	 */
	public final void setBorderDash(int... borderDash) {
		setArrayValueAndAddToParent(Property.BORDER_DASH, ArrayInteger.fromOrNull(borderDash));
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines
	 */
	@Override
	public final List<Integer> getBorderDash() {
		// gets array
		ArrayInteger array = getArrayValue(Property.BORDER_DASH);
		// checks if consistent
		if (array != null) {
			// exists then returns the value
			return ArrayListHelper.list(array);
		}
		// if here, the options is missing
		// the returns the defaults.
		return getDefaultBorderDash();
	}

	/**
	 * Sets the line dash pattern offset.
	 * 
	 * @param borderDashOffset Offset for line dashes.
	 */
	public final void setBorderDashOffset(double borderDashOffset) {
		setValueAndAddToParent(Property.BORDER_DASH_OFFSET, borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset.
	 * 
	 * @return Offset for line dashes.
	 */
	@Override
	public final double getBorderDashOffset() {
		return getValue(Property.BORDER_DASH_OFFSET, getDefaultBorderDashOffset());
	}

	/**
	 * Provides the default value for border dash offset.
	 * 
	 * @return the default value for border dash offset.
	 */
	abstract double getDefaultBorderDashOffset();

	/**
	 * Provides the default value for border dash.
	 * 
	 * @return the default value for border dash.
	 */
	abstract List<Integer> getDefaultBorderDash();

}