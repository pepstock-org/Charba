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
package org.pepstock.charba.client.jsinterop.data;

import org.pepstock.charba.client.jsinterop.commons.ArrayMixedObject;
import org.pepstock.charba.client.jsinterop.commons.ArrayObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * The chart allows a number of properties to be specified for each dataset. These are used to set display properties for a specific dataset.<br>
 * This is the base implementation for all datasets with common fields.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = "Object", namespace = JsPackage.GLOBAL)
public final class NativeData extends NativeObject{

	/**
	 * Sets the labels of the data 
	 * @param labels array of labels
	 */
	@JsProperty
	native void setLabels(ArrayMixedObject labels);
	
	/**
	 * Returns the labels 
	 * @return the labels
	 */
	@JsProperty
	native ArrayMixedObject getLabels();

	/**
	 * Sets the labels for X axes of the data 
	 * @param labels array of labels
	 */
	@JsProperty
	native void setXLabels(ArrayMixedObject labels);

	/**
	 * Returns the labels for X axes
	 * @return the labels for X axes
	 * @see org.pepstock.charba.client.data.Labels
	 */
	@JsProperty
	native ArrayMixedObject getXLabels();

	/**
	 * Sets the labels for Y axes of the data
	 * @param labels array of labels
	 */
	@JsProperty
	native void setYLabels(ArrayMixedObject labels);

	/**
	 * Returns the labels for Y axes
	 * @return the labels for Y axes
	 * @see org.pepstock.charba.client.data.Labels
	 */
	@JsProperty
	native ArrayMixedObject getYLabels();

	/**
	 * Sets a set of datasets for chart
	 * @param datasets set of dataset
	 * @see org.pepstock.charba.client.data.Dataset
	 */
	@JsProperty
	native void setDatasets(ArrayObject<NativeDataset> datasets);

}