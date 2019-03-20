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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultBaseTick;

/**
 * Configures the ticks of an axis.<br>
 * Common base class for ticks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @param <P> parent node class
 * @param <D> defaults provider class
 */
public abstract class AbstractTick<P extends AbstractModel<?, ?>, D extends IsDefaultBaseTick> extends FontItem<P, D> {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		lineHeight
	}

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script
	 * properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	AbstractTick(P ticks, Key childKey, D defaultValues, NativeObject nativeObject) {
		super(ticks, childKey, defaultValues, nativeObject);
	}

	/**
	 * Sets the height of an individual line of text.
	 * 
	 * @param lineHeight height of an individual line of text.
	 */
	public void setLineHeight(double lineHeight) {
		setValue(Property.lineHeight, lineHeight);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Sets the height of an individual line of text.
	 * 
	 * @param lineHeight height of an individual line of text.
	 */
	public void setLineHeight(String lineHeight) {
		setValue(Property.lineHeight, lineHeight);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	public double getLineHeight() {
		// creates default
		double defaultValue = getDefaultValues().getLineHeight();
		// checks type if number
		if (ObjectType.Number.equals(type(Property.lineHeight))) {
			// reads and returns as double
			return getValue(Property.lineHeight, defaultValue);
		}
		// if here, is not a number
		// then returns the default
		return defaultValue;
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	public String getLineHeightAsString() {
		// creates default
		String defaultValue = String.valueOf(getDefaultValues().getLineHeight());
		// checks type if string
		if (ObjectType.String.equals(type(Property.lineHeight))) {
			// reads and returns as string
			return getValue(Property.lineHeight, defaultValue);
		}
		// if here, is not a number
		// then returns the default
		return defaultValue;
	}

}