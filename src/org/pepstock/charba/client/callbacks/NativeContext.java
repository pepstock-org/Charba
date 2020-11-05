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
package org.pepstock.charba.client.callbacks;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.NativeObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * The option context is used to give contextual information when resolving options.<br>
 * It mainly applies to scriptable options.
 * 
 * @author Andrea "Stock" Stocchero
 */
// 
@JsType(isNative = true, name = NativeName.CHART_SCRIPTABLE_OPTIONS_CONTEXT, namespace = JsPackage.GLOBAL)
final class NativeContext {

	/**
	 * Returns the chart native instance.
	 * 
	 * @return the chart native instance
	 */
	@JsProperty
	native Chart getChart();

	/**
	 * Returns true if element is active (hovered).
	 * 
	 * @return true if element is active (hovered)
	 */
	@JsProperty
	native boolean isActive();

	/**
	 * Returns the index of the current dataset.
	 * 
	 * @return the index of the current dataset.
	 */
	@JsProperty
	native int getDatasetIndex();
	
	/**
	 * Returns the index of the current data.
	 * 
	 * @return the index of the current data.
	 */
	@JsProperty
	native int getDataIndex();

	/**
	 * Returns the parsed data values for the given dataIndex and datasetIndex.
	 * 
	 * @return the parsed data values for the given dataIndex and datasetIndex
	 */
	@JsProperty
	native NativeObject getDataPoint();

	/**
	 * Returns the element (point, arc, bar, etc.) for this data.
	 * 
	 * @return the element (point, arc, bar, etc.) for this data
	 */
	@JsProperty
	native NativeObject getElement();

	/**
	 * Returns the associated scale.
	 * 
	 * @return the associated scale
	 */
	@JsProperty
	native NativeObject getScale();

	/**
	 * Returns the associated tick.
	 * 
	 * @return the associated tick
	 */
	@JsProperty
	native NativeObject getTick();
	/**
	 * Returns the index of the tick.
	 * 
	 * @return the index of the tick
	 */
	@JsProperty
	native int getIndex();

	/**
	 * Returns the additional options, only for <b>DataLabel</b> plugin.
	 * 
	 * @return the additional options
	 */
	@JsProperty
	native NativeObject getOptions();

	/**
	 * Sets the additional options, only for <b>DataLabel</b> plugin.
	 * 
	 * @param options the additional options
	 */
	@JsProperty
	native void setOptions(NativeObject options);

}
