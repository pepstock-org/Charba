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
* @return builder instance */
package org.pepstock.charba.client.labels;

import java.util.HashMap;
import java.util.Map;

import org.pepstock.charba.client.IsChart;

/**
 * Comfortable object to create {@link LabelsPlugin#ID} plugin options by a builder.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LabelsOptionsBuilder {

	// maps with all labels builders
	// K = label id, V = label builder
	private final Map<String, LabelBuilder> labelBuilders = new HashMap<>();
	// plugin options instance
	private LabelsOptions options;

	/**
	 * To avoid any instantiation
	 * 
	 * @param chart chart instance related to the plugin options
	 */
	private LabelsOptionsBuilder(IsChart chart) {
		this.options = new LabelsOptions(chart);
	}

	/**
	 * Returns new builder instance.
	 * 
	 * @return new builder instance
	 */
	public static LabelsOptionsBuilder create() {
		return create(null);
	}

	/**
	 * Returns new builder instance using the chart global options.
	 * 
	 * @param chart chart instance related to the plugin options
	 * @return new builder instance
	 */
	public static LabelsOptionsBuilder create(IsChart chart) {
		return new LabelsOptionsBuilder(chart);
	}

	/**
	 * Returns a configured labels options.
	 * 
	 * @return a configured labels options.
	 */
	public LabelsOptions build() {
		// returns options
		return options;
	}

	/**
	 * Returns new options builder for new label identified by id.
	 * 
	 * @param id id of the new label
	 * @return new options builder for new label identified by id
	 */
	public LabelBuilder createLabel(String id) {
		return createLabel(IsLabelId.create(id));
	}

	/**
	 * Returns new options builder for new label identified by id.
	 * 
	 * @param id id of the new label
	 * @return new options builder for new label identified by id
	 */
	public LabelBuilder createLabel(IsLabelId id) {
		// checks if id is consistent
		IsLabelId.checkIfValid(id);
		// creates new label and new labels builder
		LabelBuilder builder = new LabelBuilder(this, options.createLabel(id));
		// stores the options builder
		builder.setOptionsBuilder(this);
		// stores into map
		labelBuilders.put(id.value(), builder);
		// returns the builder
		return builder;
	}

	/**
	 * Returns the options builder for label identified by id.
	 * 
	 * @param id id of the options
	 * @return the options builder for label identified by id
	 */
	public LabelBuilder getLabel(String id) {
		return getLabel(IsLabelId.create(id));
	}

	/**
	 * Returns the options builder for label identified by id
	 * 
	 * @param id id of the options
	 * @return the options builder for label identified by id
	 */
	public LabelBuilder getLabel(IsLabelId id) {
		// checks if id is consistent
		IsLabelId.checkIfValid(id);
		// checks if labels does not exist
		if (labelBuilders.containsKey(id.value())) {
			// it creates new one
			return createLabel(id);
		}
		// returns labels builder
		return labelBuilders.get(id.value());
	}
}
