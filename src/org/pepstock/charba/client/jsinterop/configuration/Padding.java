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
package org.pepstock.charba.client.jsinterop.configuration;

import org.pepstock.charba.client.jsinterop.options.ExtendedOptions;

/**
 * It is applied to all sides of the chart (left, top, right, bottom).
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 *
 */
public class Padding {
	
	private final ExtendedOptions options;

	/**
	 * Builds the object storing the root options element.
	 * 
	 * @param options root options element.
	 */
	Padding(ExtendedOptions options) {
		this.options = options;
	}
	
	/**
	 * Sets the padding left in pixel.
	 * 
	 * @param padding the padding left in pixel.
	 */
	public void setLeft(int padding) {
		options.getLayout().getPadding().setLeft(padding);
	}

	/**
	 * Returns the padding left in pixel.
	 * 
	 * @return the padding left in pixel.
	 */
	public int getLeft() {
		return options.getLayout().getPadding().getLeft();
	}

	/**
	 * Sets the padding right in pixel.
	 * 
	 * @param padding the padding right in pixel.
	 */
	public void setRight(int padding) {
		options.getLayout().getPadding().setRight(padding);
	}

	/**
	 * Returns the padding right in pixel.
	 * 
	 * @return the padding right in pixel.
	 */
	public int getRight() {
		return options.getLayout().getPadding().getRight();
	}

	/**
	 * Sets the padding top in pixel.
	 * 
	 * @param padding the padding top in pixel.
	 */
	public void setTop(int padding) {
		options.getLayout().getPadding().setTop(padding);
	}

	/**
	 * Returns the padding top in pixel.
	 * 
	 * @return the padding top in pixel.
	 */
	public int getTop() {
		return options.getLayout().getPadding().getTop();
	}

	/**
	 * Sets the padding bottom in pixel.
	 * 
	 * @param padding the padding bottom in pixel.
	 */
	public void setBottom(int padding) {
		options.getLayout().getPadding().setBottom(padding);
	}

	/**
	 * Returns the padding bottom in pixel.
	 * 
	 * @return the padding bottom in pixel. 
	 */
	public int getBottom() {
		return options.getLayout().getPadding().getBottom();
	}

}