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
package org.pepstock.charba.client.jsinterop.items;

import org.pepstock.charba.client.jsinterop.commons.Checker;
import org.pepstock.charba.client.jsinterop.commons.NativeName;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Calling some methods on your chart instance passing an argument of an event, will return the elements at the event
 * position.<br>
 * The elements are mapped by this object.<br>
 * This is the CHART.JS item with all needed info about a selected dataset. This object has been created and passed to event
 * handler or callbacks to apply own logic.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name=NativeName.OBJECT)
public final class DatasetItem extends NativeObject{
	
	/**
	 * Returns all view information about the dataset.
	 * 
	 * @return all view information about the dataset.
	 * @see org.pepstock.charba.client.items.DatasetViewItem
	 */
	@JsProperty(name = "_view")
	public native DatasetViewItem getView();

	@JsProperty(name = "_datasetIndex")
	native int getNativeDatasetIndex();
	
	@JsProperty(name = "_index")
	native int getNativeIndex();
	
	@JsProperty(name = "hidden")
	native boolean isNativeHidden();
	
	@JsProperty(name = "hidden")
	native void setNativeHidden(boolean hidden);

	/**
	 * Returns the dataset index of the chart
	 * 
	 * @return the dataset index of the chart
	 * @see org.pepstock.charba.client.data.Data#getDatasets()
	 */
	@JsOverlay
	public final int getDatasetIndex() {
		return Checker.check(getNativeDatasetIndex(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the index of the data inside the dataset.
	 * 
	 * @return the index of the data inside the dataset.
	 * @see org.pepstock.charba.client.data.Dataset#getData()
	 * @see org.pepstock.charba.client.data.Data#getLabels()
	 */
	@JsOverlay
	public final int getIndex() {
		return Checker.check(getNativeIndex(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns if the dataset is hidden.
	 * 
	 * @return <code>true</code> if the dataset is hidden, otherwise <code>false</code>.
	 */
	@JsOverlay
	public final boolean isHidden() {
		return Checker.check(isNativeHidden(), UndefinedValues.BOOLEAN);
	}

	/**
	 * Sets if the dataset must be hidden.
	 * 
	 * @param hidden <code>true</code> if the dataset must be hidden, otherwise <code>false</code>.
	 */
	@JsOverlay
	public final void setHidden(boolean hidden) {
		setNativeHidden(hidden);
	}

}