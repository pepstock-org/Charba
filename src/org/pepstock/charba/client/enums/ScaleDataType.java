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
package org.pepstock.charba.client.enums;

import org.pepstock.charba.client.configuration.CartesianCategoryAxis;
import org.pepstock.charba.client.configuration.CartesianLinearAxis;
import org.pepstock.charba.client.configuration.CartesianLogarithmicAxis;
import org.pepstock.charba.client.configuration.CartesianTimeAxis;
import org.pepstock.charba.client.configuration.CartesianTimeSeriesAxis;
import org.pepstock.charba.client.configuration.RadialAxis;

/**
 * Enumeration with all possible types of data set into scale.
 * 
 * @author Andrea "Stock" Stocchero
 * @see AxisType
 */
public enum ScaleDataType
{
	/**
	 * The data property is set as a double.<br>
	 * Used by {@link CartesianLinearAxis},{@link CartesianLogarithmicAxis} and {@link RadialAxis}.
	 */
	NUMBER,
	/**
	 * The data property is set as a date.<br>
	 * Used by {@link CartesianTimeAxis} and {@link CartesianTimeSeriesAxis}.
	 */
	DATE,
	/**
	 * The data property is set as a string.<br>
	 * Used by {@link CartesianCategoryAxis}.
	 */
	STRING;

}