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
package org.pepstock.charba.client.data;

import java.util.Arrays;
import java.util.List;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.callbacks.BorderAlignCallback;
import org.pepstock.charba.client.enums.BorderAlign;

/**
 * Manages the border align properties for datasets which this property is required.
 * 
 * @author Andrea "Stock" Stocchero
 */
interface HasBorderAlign {

	/**
	 * Returns a border align handler instance.
	 * 
	 * @return a border align handler instance
	 */
	BorderAlignHandler getBorderAlignHandler();

	/**
	 * Sets the property to set the border alignment on chart datasets.
	 * 
	 * @param align the property to set the border alignment on chart datasets
	 */
	default void setBorderAlign(BorderAlign... align) {
		// checks if border align handler is consistent
		if (getBorderAlignHandler() != null) {
			getBorderAlignHandler().setBorderAlign(align);
		}
	}

	/**
	 * Sets the property to set the border alignment on chart datasets.
	 * 
	 * @param align the property to set the border alignment on chart datasets
	 */
	default void setBorderAlign(List<BorderAlign> align) {
		// checks if border align handler is consistent
		if (getBorderAlignHandler() != null) {
			getBorderAlignHandler().setBorderAlign(align);
		}
	}

	/**
	 * Returns the property to set the border alignment on chart datasets.
	 * 
	 * @return the property to set the border alignment on chart datasets.
	 */
	default List<BorderAlign> getBorderAlign() {
		// checks if border align handler is consistent
		if (getBorderAlignHandler() != null) {
			return getBorderAlignHandler().getBorderAlign();
		}
		// if here, border align handler is not consistent
		// then returns the default
		return Arrays.asList(Defaults.get().getGlobal().getElements().getArc().getBorderAlign());
	}

	/**
	 * Returns the border align callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border align callback, if set, otherwise <code>null</code>.
	 */
	default BorderAlignCallback getBorderAlignCallback() {
		// checks if border align handler is consistent
		if (getBorderAlignHandler() != null) {
			return getBorderAlignHandler().getBorderAlignCallback();
		}
		// if here, border align handler is not consistent
		// then returns null
		return null;
	}

	/**
	 * Sets the border align callback.
	 * 
	 * @param borderAlignCallback the border align callback to set
	 */
	default void setBorderAlign(BorderAlignCallback borderAlignCallback) {
		// checks if border align handler is consistent
		if (getBorderAlignHandler() != null) {
			getBorderAlignHandler().setBorderAlign(borderAlignCallback);
		}
	}
}