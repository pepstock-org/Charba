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

import org.pepstock.charba.client.configuration.Axis;

/**
 * Interface to map all callback of scriptable options for SCALEs, available for CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of result of invocation method.
 */
public interface ScaleScriptable<T> {

	/**
	 * Returns the value of property at runtime for scales.
	 * 
	 * @param axis axis instance
	 * @param context context instance
	 * @return value of property to be applied
	 */
	T invoke(Axis axis, ScaleScriptableContext context);

}
