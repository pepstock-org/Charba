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
import org.pepstock.charba.client.defaults.IsDefaultPadding;
import org.pepstock.charba.client.enums.Position;

/**
 * This is abstract padidng object element of the chart options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class AbstractPadding<P extends AbstractModel<?, ?>> extends AbstractModel<P, IsDefaultPadding> implements IsDefaultPadding {

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param model options model of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	AbstractPadding(P model, Key childKey, IsDefaultPadding defaultValues, NativeObject nativeObject) {
		super(model, childKey, defaultValues, nativeObject);
	}

	/**
	 * Sets the padding size to all dimensions.
	 * 
	 * @param padding padding size to apply to all dimensions.
	 */
	public final void set(int padding) {
		setTop(padding);
		setBottom(padding);
		setLeft(padding);
		setRight(padding);
	}

	/**
	 * Sets the padding left in pixel.
	 * 
	 * @param padding the padding left in pixel.
	 */
	public final void setLeft(int padding) {
		setValue(Position.LEFT, padding);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the padding left in pixel.
	 * 
	 * @return the padding left in pixel.
	 */
	public final int getLeft() {
		return getValue(Position.LEFT, getDefaultValues().getLeft());
	}

	/**
	 * Sets the padding right in pixel.
	 * 
	 * @param padding the padding right in pixel.
	 */
	public final void setRight(int padding) {
		setValue(Position.RIGHT, padding);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the padding right in pixel.
	 * 
	 * @return the padding right in pixel.
	 */
	public final int getRight() {
		return getValue(Position.RIGHT, getDefaultValues().getRight());
	}

	/**
	 * Sets the padding top in pixel.
	 * 
	 * @param padding the padding top in pixel.
	 */
	public final void setTop(int padding) {
		setValue(Position.TOP, padding);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the padding top in pixel.
	 * 
	 * @return the padding top in pixel.
	 */
	public final int getTop() {
		return getValue(Position.TOP, getDefaultValues().getTop());
	}

	/**
	 * Sets the padding bottom in pixel.
	 * 
	 * @param padding the padding bottom in pixel.
	 */
	public final void setBottom(int padding) {
		setValue(Position.BOTTOM, padding);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the padding bottom in pixel.
	 * 
	 * @return the padding bottom in pixel.
	 */
	public final int getBottom() {
		return getValue(Position.BOTTOM, getDefaultValues().getBottom());
	}
}