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

import org.pepstock.charba.client.defaults.IsDefaultPadding;

/**
 * Interface to map a font element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsPadding extends IsDefaultPadding {

	/**
	 * Sets the padding size to all dimensions.
	 * 
	 * @param padding padding size to apply to all dimensions.
	 */
	default void set(int padding) {
		setTop(padding);
		setBottom(padding);
		setLeft(padding);
		setRight(padding);
	}

	/**
	 * Sets the padding size to X dimensions (left and right).
	 * 
	 * @param padding padding size to X dimensions (left and right).
	 */
	default void setX(int padding) {
		setLeft(padding);
		setRight(padding);
	}

	/**
	 * Sets the padding size to Y dimensions (top and bottom).
	 * 
	 * @param padding padding size to Y dimensions (top and bottom).
	 */
	default void setY(int padding) {
		setTop(padding);
		setBottom(padding);
	}

	/**
	 * Sets the padding left in pixel.
	 * 
	 * @param padding the padding left in pixel.
	 */
	void setLeft(int padding);

	/**
	 * Sets the padding right in pixel.
	 * 
	 * @param padding the padding right in pixel.
	 */
	void setRight(int padding);

	/**
	 * Sets the padding top in pixel.
	 * 
	 * @param padding the padding top in pixel.
	 */
	void setTop(int padding);

	/**
	 * Sets the padding bottom in pixel.
	 * 
	 * @param padding the padding bottom in pixel.
	 */
	void setBottom(int padding);

}
