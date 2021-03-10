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

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultPadding;
import org.pepstock.charba.client.enums.Position;

/**
 * Maps the additional space to apply to the sides of elements (left, top, right, bottom), in pixels.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Padding extends AbstractNode implements IsPadding {
	
	// default font values
	private final IsDefaultPadding defaultValues;
	
	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent of the font.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Padding(AbstractNode parent, Key childKey, IsDefaultPadding defaultValues, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
		// checks if default value is consistent
		// stores defaults values
		this.defaultValues = checkDefaultValuesArgument(defaultValues);
	}

	/**
	 * Sets the padding left in pixel.
	 * 
	 * @param padding the padding left in pixel.
	 */
	@Override
	public void setLeft(int padding) {
		setValueAndAddToParent(Position.LEFT, padding);
	}

	/**
	 * Returns the padding left in pixel.
	 * 
	 * @return the padding left in pixel.
	 */
	@Override
	public int getLeft() {
		return getValue(Position.LEFT, defaultValues.getLeft());
	}

	/**
	 * Sets the padding right in pixel.
	 * 
	 * @param padding the padding right in pixel.
	 */
	@Override
	public void setRight(int padding) {
		setValueAndAddToParent(Position.RIGHT, padding);
	}

	/**
	 * Returns the padding right in pixel.
	 * 
	 * @return the padding right in pixel.
	 */
	@Override
	public int getRight() {
		return getValue(Position.RIGHT, defaultValues.getRight());
	}

	/**
	 * Sets the padding top in pixel.
	 * 
	 * @param padding the padding top in pixel.
	 */
	@Override
	public void setTop(int padding) {
		setValueAndAddToParent(Position.TOP, padding);
	}

	/**
	 * Returns the padding top in pixel.
	 * 
	 * @return the padding top in pixel.
	 */
	@Override
	public int getTop() {
		return getValue(Position.TOP, defaultValues.getTop());
	}

	/**
	 * Sets the padding bottom in pixel.
	 * 
	 * @param padding the padding bottom in pixel.
	 */
	@Override
	public void setBottom(int padding) {
		setValueAndAddToParent(Position.BOTTOM, padding);
	}

	/**
	 * Returns the padding bottom in pixel.
	 * 
	 * @return the padding bottom in pixel.
	 */
	@Override
	public int getBottom() {
		return getValue(Position.BOTTOM, defaultValues.getBottom());
	}

}