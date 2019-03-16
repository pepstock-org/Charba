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
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.enums.Position;

/**
 * DATALABELS plugin default options for PADDING element.<br>
 * It contains all default values for PADDING.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class DataLabelsDefaultsPadding extends NativeObjectContainer {

	// default padding
	private static final int DEFAULT_PADDING = 4;

	/**
	 * Creates the object with an empty native object instance.
	 */
	DataLabelsDefaultsPadding() {
		super();
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	DataLabelsDefaultsPadding(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the padding left in pixel.
	 * 
	 * @return the padding left in pixel. Default is 4.
	 */
	int getLeft() {
		return getValue(Position.left, DEFAULT_PADDING);
	}

	/**
	 * Returns the padding right in pixel.
	 * 
	 * @return the padding right in pixel. Default is 4.
	 */
	int getRight() {
		return getValue(Position.right, DEFAULT_PADDING);
	}

	/**
	 * Returns the padding top in pixel.
	 * 
	 * @return the padding top in pixel. Default is 4.
	 */
	int getTop() {
		return getValue(Position.top, DEFAULT_PADDING);
	}

	/**
	 * Returns the padding bottom in pixel.
	 * 
	 * @return the padding bottom in pixel. Default is 4.
	 */
	int getBottom() {
		return getValue(Position.bottom, DEFAULT_PADDING);
	}
}