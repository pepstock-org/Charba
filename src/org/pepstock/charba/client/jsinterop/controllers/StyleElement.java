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
package org.pepstock.charba.client.jsinterop.controllers;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.items.DatasetItem;
import org.pepstock.charba.client.jsinterop.items.ScaleItem;

/**
 * Element used by controller by <code>removeHoverStyle</code> and <code>setHoverStyle</code> methods. It contrians information
 * about the dataset, chart and scales to use.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 *
 */
public final class StyleElement extends DatasetItem {

	private final InternalScaleItem xScale;

	private final InternalScaleItem yScale;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		_xScale,
		_yScale
	}

	/**
	 * Wraps the CHART.JS java script object.
	 * 
	 * @param nativeObject CHART.JS java script object
	 */
	StyleElement(NativeObject nativeObject) {
		super(nativeObject);
		// checks and set the X scale if exists
		if (has(Property._xScale)) {
			xScale = new InternalScaleItem(getValue(Property._xScale));
		} else {
			xScale = null;
		}
		// checks and set the Y scale if exists
		if (has(Property._yScale)) {
			yScale = new InternalScaleItem(getValue(Property._yScale));
		} else {
			yScale = null;
		}
	}

	/**
	 * Returns the X scale instance.
	 * 
	 * @return the X scale instance otherwise <code>null</code> if not exists.
	 */
	public ScaleItem getXScale() {
		return xScale;
	}

	/**
	 * Returns the Y scale instance.
	 * 
	 * @return the Y scale instance otherwise <code>null</code> if not exists.
	 */
	public ScaleItem getYScale() {
		return yScale;
	}

	/**
	 * Returns the native object of this element.
	 * 
	 * @return the native object.
	 */
	NativeObject getObject() {
		return super.getNativeObject();
	}

	/**
	 * Internal class to extend scale items.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	protected static class InternalScaleItem extends ScaleItem {

		/**
		 * Wraps the CHART.JS java script object.
		 * 
		 * @param nativeObject CHART.JS java script object
		 */
		protected InternalScaleItem(NativeObject javaScriptObject) {
			super(javaScriptObject);
		}

	}

}
