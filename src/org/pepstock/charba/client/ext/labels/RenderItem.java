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
package org.pepstock.charba.client.ext.labels;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.items.UndefinedValues;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * This object is wrapping the native java script object provided by labels plugin when the RENDER function is called.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
public class RenderItem {

	/**
	 * To avoid any instantiation
	 */
	RenderItem() {
	}

	/**
	 * Returns the <code>chart</code> property by native object.
	 * 
	 * @return the <code>chart</code> property by native object.
	 */
	@JsProperty(name = "chart")
	native final Chart getNativeChart();
	
	/**
	 * Returns the <code>datasetIndex</code> property by native object.
	 * 
	 * @return the <code>datasetIndex</code> property by native object.
	 */
	@JsProperty(name = "datasetIndex")
	native final int getNativeDatasetIndex();

	/**
	 * Returns the <code>index</code> property by native object.
	 * 
	 * @return the <code>index</code> property by native object.
	 */
	@JsProperty(name = "index")
	native final int getNativeIndex();

	/**
	 * Returns the <code>label</code> property by native object.
	 * 
	 * @return the <code>label</code> property by native object.
	 */
	@JsProperty(name = "label")
	native final String getNativeLabel();

	/**
	 * Returns the <code>value</code> property by native object.
	 * 
	 * @return the <code>value</code> property by native object.
	 */
	@JsProperty(name = "value")
	native final double getNativeValue();

	/**
	 * Returns the <code>percentage</code> property by native object.
	 * 
	 * @return the <code>percentage</code> property by native object.
	 */
	@JsProperty(name = "percentage")
	native final double getNativePercentage();

	/**
	 * Returns the index of the data inside the dataset.
	 * 
	 * @return the index of the data inside the dataset. Default is
	 *         {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public final int getIndex() {
		// checks if is defined
		if (ObjectType.Undefined.equals(JsHelper.get().typeOf(this, "index"))) {
			return UndefinedValues.INTEGER;
		} 
		// returns property value
		return getNativeIndex();
	}
	
	/**
	 * Returns the dataset index of the data inside the dataset.
	 * 
	 * @return the dataset index of the data inside the dataset. Default is
	 *         {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public final int getDatasetIndex() {
		// checks if is defined
		if (ObjectType.Undefined.equals(JsHelper.get().typeOf(this, "datasetIndex"))) {
			return UndefinedValues.INTEGER;
		} 
		// returns property value
		return getNativeDatasetIndex();
	}

	/**
	 * Returns the label for the dataset.
	 * 
	 * @return the label for the dataset. Default is {@link org.pepstock.charba.client.items.UndefinedValues#STRING}.
	 */
	@JsOverlay
	public final String getLabel() {
		// checks if is defined
		if (ObjectType.Undefined.equals(JsHelper.get().typeOf(this, "label"))) {
			return UndefinedValues.STRING;
		} 
		// returns property value
		return getNativeLabel();
	}

	/**
	 * Returns the percentage for the dataset.
	 * 
	 * @return the percentage for the dataset. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public final double getPercentage() {
		// checks if is defined
		if (ObjectType.Undefined.equals(JsHelper.get().typeOf(this, "percentage"))) {
			return UndefinedValues.DOUBLE;
		} 
		// returns property value
		return getNativePercentage();
	}

	/**
	 * Returns the value for the dataset.
	 * 
	 * @return the value for the dataset. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	@JsOverlay
	public final double getValue() {
		// checks if is defined
		if (ObjectType.Undefined.equals(JsHelper.get().typeOf(this, "value"))) {
			return UndefinedValues.DOUBLE;
		} 
		// returns property value
		return getNativeValue();
	}
}