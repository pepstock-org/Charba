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
package org.pepstock.charba.client.jsinterop.callbacks;

import org.pepstock.charba.client.jsinterop.AbstractChart;

/**
 * Filters items out of the item container. Receives 2 parameters, an item and the chart.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of item to be filtered
 * @since 2.0
 */
interface FilterCallback<T> {

	/**
	 * Callback that filter items on chart components.
	 * 
	 * @param chart chart instance
	 * @param item item to be filtered
	 * @return <code>false</code> to remove the item from the container, otherwise <code>true</code>.
	 */
	boolean onFilter(AbstractChart<?, ?> chart, T item);

}