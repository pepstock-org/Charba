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

/**
 * Maps the methods for LABELS elements to get defaults value.
 * 
 * @author Andrea "Stock" Stocchero
 */
interface IsDefaultLabels {

	/**
	 * Returns the stored options for specific key.
	 * 
	 * @param key key of the options
	 * @return the stored option or <code>null</code> if no options are stored for that key
	 */
	default LabelItem getLabel(DataLabelId key) {
		return null;
	}

	/**
	 * Returns <code>true</code> if there is a stored options for specific key.
	 * 
	 * @param key key of the options
	 * @return <code>true</code> if there is a stored options for specific key
	 */
	default boolean hasLabel(DataLabelId key) {
		return false;
	}

}