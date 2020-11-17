/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License";
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.labels;

import java.util.Collections;
import java.util.List;

/**
 * Maps the default global options if there are and provides all default values for {@link LabelsPlugin#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
interface IsDefaultOptions {

	/**
	 * Returns <code>true</code> if the label with the id passed as argument exists.
	 * 
	 * @param id label id to check
	 * @return <code>true</code> if the label with the id passed as argument exists
	 */
	default boolean hasLabel(IsLabelId id) {
		return false;
	}

	/**
	 * Returns the list of labels.
	 * 
	 * @return the list of labels
	 */
	default List<Label> getLabels() {
		return Collections.emptyList();
	}

	/**
	 * Returns the label with the id passed as argument or <code>null</code> if not exist.
	 * 
	 * @param id label id to use to retrieve the annotation
	 * @return the label or <code>null</code> if not exist
	 */
	default Label getLabel(IsLabelId id) {
		return null;
	}
}
