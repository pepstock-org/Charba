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

/**
 * Extends a JavaScript object container for all entities which needs the chart instance.<br>
 * This class is used for all entities which will trigger events or callbacks to pass the chart instance as parameter of implemented interface.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.commons.JavaScriptObjectContainer
 */
abstract class AxisContainer{

	// axis instance
	private final Axis axis;
	
	/**
	 * Creates the chart configuration object with the chart instance
	 * @param chart chart instance
	 * @see org.pepstock.charba.client.jsinterop.AbstractChart
	 */
	AxisContainer(Axis axis) {
		this.axis = axis;
	}

	/**
	 * @return the axis
	 */
	final Axis getAxis() {
		return axis;
	}
	
}