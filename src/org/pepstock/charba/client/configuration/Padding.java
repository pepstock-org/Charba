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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.options.IsPadding;

/**
 * Maps the additional space to apply to the sides of elements (left, top, right, bottom), in pixels.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Padding extends AbstractDynamicConfiguration<IsPadding> implements IsPadding {

	/**
	 * Builds the object by a padding provider used to get the padding element for storing properties.
	 * 
	 * @param provider padding provider used to get the padding element for storing properties.
	 */
	Padding(IsProvider<IsPadding> provider) {
		super(provider);
	}

	/**
	 * Sets the padding size to all dimensions.
	 * 
	 * @param padding padding size to apply to all dimensions.
	 */
	@Override
	public void set(int padding) {
		checkAndGet().set(padding);
	}

	/**
	 * Sets the padding left in pixel.
	 * 
	 * @param padding the padding left in pixel.
	 */
	@Override
	public void setLeft(int padding) {
		checkAndGet().setLeft(padding);
	}

	/**
	 * Returns the padding left in pixel.
	 * 
	 * @return the padding left in pixel.
	 */
	@Override
	public int getLeft() {
		return checkAndGet().getLeft();
	}

	/**
	 * Sets the padding right in pixel.
	 * 
	 * @param padding the padding right in pixel.
	 */
	@Override
	public void setRight(int padding) {
		checkAndGet().setRight(padding);
	}

	/**
	 * Returns the padding right in pixel.
	 * 
	 * @return the padding right in pixel.
	 */
	@Override
	public int getRight() {
		return checkAndGet().getRight();
	}

	/**
	 * Sets the padding top in pixel.
	 * 
	 * @param padding the padding top in pixel.
	 */
	@Override
	public void setTop(int padding) {
		checkAndGet().setTop(padding);
	}

	/**
	 * Returns the padding top in pixel.
	 * 
	 * @return the padding top in pixel.
	 */
	@Override
	public int getTop() {
		return checkAndGet().getTop();
	}

	/**
	 * Sets the padding bottom in pixel.
	 * 
	 * @param padding the padding bottom in pixel.
	 */
	@Override
	public void setBottom(int padding) {
		checkAndGet().setBottom(padding);
	}

	/**
	 * Returns the padding bottom in pixel.
	 * 
	 * @return the padding bottom in pixel.
	 */
	@Override
	public int getBottom() {
		return checkAndGet().getBottom();
	}

}