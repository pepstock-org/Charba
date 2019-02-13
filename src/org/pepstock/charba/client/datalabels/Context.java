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

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.NativeExtendedObject;
import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.items.UndefinedValues;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * The option context is used to give contextual information when resolving options. It mainly applies to scriptable options but
 * also to some function options such as formatter.<br>
 * The context object contains the following properties:<br>
 * <ul>
 * <li><b>active</b> (boolean): whether the associated element is hovered
 * <li><b>chart</b> (Chart): the associated chart
 * <li><b>dataIndex</b>(int): index of the associated data
 * <li><b>dataset</b>(object): the dataset at index datasetIndex
 * <li><b>datasetIndex</b>(int): index of the associated dataset
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
public final class Context extends NativeExtendedObject {

	/**
	 * To avoid any instantiation
	 */
	Context() {
	}

	/**
	 * Returns the <code>chart</code> property by native object.
	 * 
	 * @return the <code>chart</code> property by native object.
	 */
	@JsProperty(name = "chart")
	native Chart getNativeChart();

	/**
	 * Returns the <code>dataIndex</code> property by native object.
	 * 
	 * @return the <code>dataIndex</code> property by native object.
	 */
	@JsProperty(name = "dataIndex")
	native int getNativeDataIndex();

	/**
	 * Returns the <code>datasetIndex</code> property by native object.
	 * 
	 * @return the <code>datasetIndex</code> property by native object.
	 */
	@JsProperty(name = "datasetIndex")
	native int getNativeDatasetIndex();

	/**
	 * Returns the <code>active</code> property by native object.
	 * 
	 * @return the <code>active</code> property by native object.
	 */
	@JsProperty(name = "active")
	native boolean isNativeActive();

	/**
	 * Returns the index of the data inside the dataset.
	 * 
	 * @return the index of the data inside the dataset. Default is
	 *         {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public int getIndex() {
		// checks if is defined
		if (ObjectType.Undefined.equals(JsHelper.get().typeOf(this, "dataIndex"))) {
			return UndefinedValues.INTEGER;
		}
		// returns property value
		return getNativeDataIndex();
	}

	/**
	 * Returns the dataset index of the data inside the dataset.
	 * 
	 * @return the dataset index of the data inside the dataset. Default is
	 *         {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public int getDatasetIndex() {
		// checks if is defined
		if (ObjectType.Undefined.equals(JsHelper.get().typeOf(this, "datasetIndex"))) {
			return UndefinedValues.INTEGER;
		}
		// returns property value
		return getNativeDatasetIndex();
	}

	/**
	 * Returns the dataset index of the data inside the dataset.
	 * 
	 * @return the dataset index of the data inside the dataset. Default is true.
	 */
	@JsOverlay
	public boolean isActive() {
		// checks if is defined
		if (ObjectType.Undefined.equals(JsHelper.get().typeOf(this, "active"))) {
			return true;
		}
		// returns property value
		return isNativeActive();
	}

	/**
	 * Returns the CHARBA ID, set to the chart.
	 * 
	 * @return the CHARBA ID
	 */
	@JsOverlay
	String getCharbaId() {
		return getNativeChart().getCharbaId();
	}

}