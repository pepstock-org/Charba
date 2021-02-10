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
package org.pepstock.charba.client.data;

import java.util.List;

/**
 * Manages the LABELS properties for datasets and options which this property is required.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface HasLabels {

	/**
	 * Returns an labels option handler instance.
	 * 
	 * @return an labels option handler instance
	 */
	LabelsHandler getLabelsHandler();

	/**
	 * Sets the labels of the data.
	 * 
	 * @param labels array of labels
	 */
	default void setLabels(String... labels) {
		// creates a label object
		Labels internalLabels = Labels.build();
		// loads
		internalLabels.load(labels);
		// sets labels
		setLabels(internalLabels);
	}

	/**
	 * Sets the labels of the data.
	 * 
	 * @param labels list of labels
	 */
	default void setLabels(List<String> labels) {
		// creates a label object
		Labels internalLabels = Labels.build();
		// loads
		internalLabels.load(labels);
		// sets labels
		setLabels(internalLabels);
	}

	/**
	 * Sets the labels of the data.
	 * 
	 * @param labels labels object to manage also multi-line labels
	 */
	default void setLabels(Labels labels) {
		// checks if labels handler is consistent
		if (getLabelsHandler() != null) {
			getLabelsHandler().setLabels(labels);
		}
	}

	/**
	 * Returns the labels.
	 * 
	 * @return the labels
	 */
	default Labels getLabels() {
		return getLabels(false);
	}

	/**
	 * Returns the labels for axes.
	 * 
	 * @param binding if <code>true</code> binds the new labels into container
	 * @return the labels for axes
	 */
	default Labels getLabels(boolean binding) {
		// checks if labels handler is consistent
		if (getLabelsHandler() != null) {
			return getLabelsHandler().getLabels(binding);
		}
		// if here, labels option manager is not consistent
		// then returns an empty labels
		return Labels.build();
	}

}