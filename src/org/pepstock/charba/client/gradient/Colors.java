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
package org.pepstock.charba.client.gradient;

import java.util.Date;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.Undefined;

/**
 * Is a map to apply the color stop of the gradient in relation with the data of the chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Colors extends AbstractNode {

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param nativeObject native object to map java script properties
	 */
	Colors(AbstractNode parent, Key childKey, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
	}

	/**
	 * Removes all keys.
	 */
	public void clear() {
		// scans all keys and removes them
		keys().forEach((key) -> remove(key));
	}

	// -----------
	// STRING
	// -----------

	/**
	 * Sets a color in the gradient, by a value of chart.
	 * 
	 * @param value the value of the chart where the color stop has been applied
	 * @param color the color assigned at passed chart value.
	 */
	public void setColor(String value, IsColor color) {
		setColor(value, IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets a color in the gradient, by a value of chart.
	 * 
	 * @param value the value of the chart where the color stop has been applied
	 * @param color the color assigned at passed chart value.
	 */
	public void setColor(String value, String color) {
		setColor(Key.create(value), color);
	}

	/**
	 * Returns the stored color in the gradient, by a value of chart.
	 * 
	 * @param value the value of the chart where the color stop has been applied
	 * @return the color assigned at passed chart value.
	 */
	public String getColorAsString(String value) {
		return getColorAsString(Key.create(value));
	}

	/**
	 * Returns the stored color in the gradient, by a value of chart.
	 * 
	 * @param value the value of the chart where the color stop has been applied
	 * @return the color assigned at passed chart value.
	 */
	public IsColor getColor(String value) {
		return getColor(Key.create(value));
	}

	/**
	 * Removes a color by keys.
	 * 
	 * @param values the values of the chart where the color stop has been applied
	 */
	public void remove(String... values) {
		// checks if argument is consistent
		if (values != null && values.length > 0) {
			// scans keys
			for (String value : values) {
				// checks if keys is consistent
				if (value != null) {
					// removes value
					removeKey(Key.create(value));
				}
			}
		}
	}

	// -----------
	// DOUBLE
	// -----------

	/**
	 * Sets a color in the gradient, by a value of chart.
	 * 
	 * @param value the value of the chart where the color stop has been applied
	 * @param color the color assigned at passed chart value.
	 */
	public void setColor(double value, IsColor color) {
		setColor(value, IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets a color in the gradient, by a value of chart.
	 * 
	 * @param value the value of the chart where the color stop has been applied
	 * @param color the color assigned at passed chart value.
	 */
	public void setColor(double value, String color) {
		// checks if value is consistent
		Checker.checkIfValid(value, "Value for color stop ");
		// stores value
		setColor(Key.create(String.valueOf(value)), color);
	}

	/**
	 * Returns the stored color in the gradient, by a value of chart.
	 * 
	 * @param value the value of the chart where the color stop has been applied
	 * @return the color assigned at passed chart value.
	 */
	public String getColorAsString(double value) {
		// checks if value is consistent
		Checker.checkIfValid(value, "Value for color stop ");
		// get value
		return getColorAsString(Key.create(String.valueOf(value)));
	}

	/**
	 * Returns the stored color in the gradient, by a value of chart.
	 * 
	 * @param value the value of the chart where the color stop has been applied
	 * @return the color assigned at passed chart value.
	 */
	public IsColor getColor(double value) {
		// checks if value is consistent
		Checker.checkIfValid(value, "Value for color stop ");
		// get value
		return getColor(Key.create(String.valueOf(value)));
	}

	/**
	 * Removes a color by keys.
	 * 
	 * @param values the values of the chart where the color stop has been applied
	 */
	public void remove(double... values) {
		// checks if argument is consistent
		if (values != null && values.length > 0) {
			// scans keys
			for (double value : values) {
				// checks if value is consistent
				if (Undefined.isNot(value)) {
					// removes value
					removeKey(Key.create(String.valueOf(value)));
				}
			}
		}
	}

	// -----------
	// DATE
	// -----------

	/**
	 * Sets a color in the gradient, by a value of chart.
	 * 
	 * @param value the value of the chart where the color stop has been applied
	 * @param color the color assigned at passed chart value.
	 */
	public void setColor(Date value, IsColor color) {
		setColor(value, IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets a color in the gradient, by a value of chart.
	 * 
	 * @param value the value of the chart where the color stop has been applied
	 * @param color the color assigned at passed chart value.
	 */
	public void setColor(Date value, String color) {
		// checks if value is consistent
		Checker.checkIfValid(value, "Value for color stop ");
		// stores value
		setColor(Key.create(String.valueOf(value.getTime())), color);
	}

	/**
	 * Returns the stored color in the gradient, by a value of chart.
	 * 
	 * @param value the value of the chart where the color stop has been applied
	 * @return the color assigned at passed chart value.
	 */
	public String getColorAsString(Date value) {
		// checks if value is consistent
		Checker.checkIfValid(value, "Value for color stop ");
		// get value
		return getColorAsString(Key.create(String.valueOf(value.getTime())));
	}

	/**
	 * Returns the stored color in the gradient, by a value of chart.
	 * 
	 * @param value the value of the chart where the color stop has been applied
	 * @return the color assigned at passed chart value.
	 */
	public IsColor getColor(Date value) {
		// checks if value is consistent
		Checker.checkIfValid(value, "Value for color stop ");
		// get value
		return getColor(Key.create(String.valueOf(value.getTime())));
	}

	/**
	 * Removes a color by keys.
	 * 
	 * @param values the values of the chart where the color stop has been applied
	 */
	public void remove(Date... values) {
		// checks if argument is consistent
		if (values != null && values.length > 0) {
			// scans keys
			for (Date value : values) {
				// checks if value is consistent
				if (value != null) {
					// removes value
					removeKey(Key.create(String.valueOf(value.getTime())));
				}
			}
		}
	}

	// -------------------
	// COMMON METHODS
	// -------------------

	/**
	 * Sets a color in the gradient, by a value of chart.
	 * 
	 * @param value the value of the chart where the color stop has been applied
	 * @param color the color assigned at passed chart value.
	 */
	private void setColor(Key value, String color) {
		setValueAndAddToParent(value, color);
	}

	/**
	 * Returns the stored color in the gradient, by a value of chart.
	 * 
	 * @param value the value of the chart where the color stop has been applied
	 * @return the color assigned at passed chart value.
	 */
	private String getColorAsString(Key value) {
		return getValue(value, Undefined.STRING);
	}

	/**
	 * Returns the stored color in the gradient, by a value of chart.
	 * 
	 * @param value the value of the chart where the color stop has been applied
	 * @return the color assigned at passed chart value.
	 */
	private IsColor getColor(Key value) {
		// gets color as string
		String color = getColorAsString(value);
		// checks if color is consistent
		if (color != null) {
			return ColorBuilder.parse(color);
		}
		// if here, color is not consistent
		return null;
	}

	/**
	 * Removes a color by key.
	 * 
	 * @param value the value of the chart where the color stop has been applied
	 */
	private void removeKey(Key value) {
		remove(value);
	}

}