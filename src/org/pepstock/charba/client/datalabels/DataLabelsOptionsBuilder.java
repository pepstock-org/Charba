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
package org.pepstock.charba.client.datalabels;

import java.util.HashMap;
import java.util.Map;

import org.pepstock.charba.client.IsChart;

/**
 * Comfortable object to create {@link DataLabelsPlugin#ID} plugin options by a builder.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DataLabelsOptionsBuilder extends AbstractBuilder<DataLabelsOptions> {

	// maps with all labels builders
	// K = label item id, V = label item builder
	private final Map<String, LabelItemBuilder> labelItemBuilders = new HashMap<>();

	/**
	 * To avoid any instantiation
	 * 
	 * @param chart chart instance related to the plugin options
	 */
	private DataLabelsOptionsBuilder(IsChart chart) {
		super(new DataLabelsOptions(chart));
		// stores itself as builder
		setOptionsBuilder(this);
	}

	/**
	 * Returns new builder instance.
	 * 
	 * @return new builder instance
	 */
	public static DataLabelsOptionsBuilder create() {
		return create(null);
	}

	/**
	 * Returns new builder instance using the chart global options.
	 * 
	 * @param chart chart instance related to the plugin options
	 * @return new builder instance
	 */
	public static DataLabelsOptionsBuilder create(IsChart chart) {
		return new DataLabelsOptionsBuilder(chart);
	}

	/**
	 * Returns a configured labels options.
	 * 
	 * @return a configured labels options.
	 */
	public DataLabelsOptions build() {
		// returns options
		return getLabel();
	}

	/**
	 * Returns new options builder for new label identified by id.
	 * 
	 * @param id id of the new label item
	 * @return new options builder for new label identified by id
	 */
	public LabelItemBuilder createLabel(String id) {
		return createLabel(IsDataLabelId.create(id));
	}

	/**
	 * Returns new options builder for new label identified by id.
	 * 
	 * @param id id of the new label item
	 * @return new options builder for new label identified by id
	 */
	public LabelItemBuilder createLabel(IsDataLabelId id) {
		// checks if id is consistent
		IsDataLabelId.checkIfValid(id);
		// creates new label and new labels builder
		LabelItemBuilder builder = new LabelItemBuilder(getLabel().getLabels().createLabel(id));
		// stores the options builder
		builder.setOptionsBuilder(this);
		// stores into map
		labelItemBuilders.put(id.value(), builder);
		// returns the builder
		return builder;
	}

	/**
	 * Returns the options builder for label identified by id.
	 * 
	 * @param id id of the options
	 * @return the options builder for label identified by id
	 */
	public LabelItemBuilder getLabel(String id) {
		return getLabel(IsDataLabelId.create(id));
	}

	/**
	 * Returns the options builder for label identified by id
	 * 
	 * @param id id of the options
	 * @return the options builder for label identified by id
	 */
	public LabelItemBuilder getLabel(IsDataLabelId id) {
		// checks if id is consistent
		IsDataLabelId.checkIfValid(id);
		// checks if labels does not exist
		if (labelItemBuilders.containsKey(id.value())) {
			// it creates new one
			return createLabel(id);
		}
		// returns labels builder
		return labelItemBuilders.get(id.value());
	}
}
