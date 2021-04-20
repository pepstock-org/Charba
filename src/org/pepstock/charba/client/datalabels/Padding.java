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
package org.pepstock.charba.client.datalabels;

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.datalabels.callbacks.PaddingCallback;
import org.pepstock.charba.client.defaults.IsDefaultPadding;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.options.IsPadding;

/**
 * Base object to map font options for {@link DataLabelsPlugin#ID} plugin configuration.<br>
 * It is applied to all sides of the chart (left, top, right, bottom).<br>
 * It can be used also in the callback for padding generation at runtime.
 * 
 * @author Andrea "Stock" Stocchero
 * @see PaddingCallback
 */
public final class Padding extends AbstractElement implements IsPadding {

	/**
	 * Default padding, <b>{@value DEFAULT_PADDING}</b>.
	 */
	public static final int DEFAULT_PADDING = 4;

	// defaults global options instance
	private IsDefaultPadding defaultOptions;

	/**
	 * Creates new padding element, using the default values options.
	 */
	public Padding() {
		this(DefaultOptions.INSTANCE.getPadding(), null);
	}

	/**
	 * Creates new padding element, using stored native object instance and the default values options.
	 * 
	 * @param nativeObject stored padding values in the native object to read.
	 * @param defaultOptions default PADDING options to returns the default when required.
	 */
	Padding(IsDefaultPadding defaultOptions, NativeObject nativeObject) {
		super(nativeObject);
		// checks if default value is consistent
		// stores default
		this.defaultOptions = checkDefaultValuesArgument(defaultOptions);
	}

	/**
	 * Sets the padding left in pixel.
	 * 
	 * @param padding the padding left in pixel.
	 */
	@Override
	public void setLeft(int padding) {
		setValue(Position.LEFT, padding);
	}

	/**
	 * Returns the padding left in pixel.
	 * 
	 * @return the padding left in pixel.
	 */
	@Override
	public int getLeft() {
		return getValue(Position.LEFT, defaultOptions.getLeft());
	}

	/**
	 * Sets the padding right in pixel.
	 * 
	 * @param padding the padding right in pixel.
	 */
	@Override
	public void setRight(int padding) {
		setValue(Position.RIGHT, padding);
	}

	/**
	 * Returns the padding right in pixel.
	 * 
	 * @return the padding right in pixel.
	 */
	@Override
	public int getRight() {
		return getValue(Position.RIGHT, defaultOptions.getRight());
	}

	/**
	 * Sets the padding top in pixel.
	 * 
	 * @param padding the padding top in pixel.
	 */
	@Override
	public void setTop(int padding) {
		setValue(Position.TOP, padding);
	}

	/**
	 * Returns the padding top in pixel.
	 * 
	 * @return the padding top in pixel.
	 */
	@Override
	public int getTop() {
		return getValue(Position.TOP, defaultOptions.getTop());
	}

	/**
	 * Sets the padding bottom in pixel.
	 * 
	 * @param padding the padding bottom in pixel.
	 */
	@Override
	public void setBottom(int padding) {
		setValue(Position.BOTTOM, padding);
	}

	/**
	 * Returns the padding bottom in pixel.
	 * 
	 * @return the padding bottom in pixel.
	 */
	@Override
	public int getBottom() {
		return getValue(Position.BOTTOM, defaultOptions.getBottom());
	}
}