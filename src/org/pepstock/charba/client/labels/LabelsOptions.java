/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.labels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;

/**
 * This is the object to map the {@link LabelsPlugin#ID} plugin options, both at chart and global level.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LabelsOptions extends AbstractPluginOptions implements IsDefaultOptions {

	// defaults global options instance
	private IsDefaultOptions defaultOptions;

	/**
	 * Creates new {@link LabelsPlugin#ID} plugin options.
	 */
	public LabelsOptions() {
		this(null, null);
	}

	/**
	 * Creates new {@link LabelsPlugin#ID} plugin options, relating to chart instance for default.
	 * 
	 * @param chart chart instance related to the plugin options
	 */
	public LabelsOptions(IsChart chart) {
		this(IsChart.isConsistent(chart) ? chart.getDefaultChartOptions().getPlugins().getOptions(LabelsPlugin.ID, LabelsPlugin.DEFAULTS_FACTORY) : null, null);
	}

	/**
	 * Creates new {@link LabelsPlugin#ID} plugin options.
	 * 
	 * @param defaultOptions default options stored in the defaults global
	 * @param nativeObject native object which represents the plugin options as native object
	 */
	LabelsOptions(IsDefaultOptions defaultOptions, NativeObject nativeObject) {
		// creates an empty object
		super(LabelsPlugin.ID, nativeObject);
		// checks if defaults options are consistent
		if (defaultOptions == null) {
			// reads the default default global options
			this.defaultOptions = loadGlobalsPluginOptions(LabelsPlugin.DEFAULTS_FACTORY);
		} else {
			// stores default options
			this.defaultOptions = defaultOptions;
		}
	}

	/**
	 * Creates new label (if not exist) using the label if passed as argument.
	 * 
	 * @param id label id to use
	 * @return new empty label object
	 */
	public Label createLabel(String id) {
		return createLabel(LabelId.create(id));
	}

	/**
	 * Creates new label (if not exist) using the label if passed as argument.
	 * 
	 * @param id label id to use
	 * @return new empty label object
	 */
	public Label createLabel(LabelId id) {
		// checks the consistency of ID
		Key.checkIfValid(id);
		// checks if already exists
		if (!has(id)) {
			// creates new label
			Label label = new Label(id, defaultOptions.getLabel(id), null);
			// stores the label
			setValue(id, label);
			// returns label
			return label;
		}
		// if here, the already exist
		return getLabel(id);
	}

	/**
	 * Returns <code>true</code> if the label with the id passed as argument exists.
	 * 
	 * @param id label id to check
	 * @return <code>true</code> if the label with the id passed as argument exists
	 */
	public boolean hasLabel(String id) {
		return hasLabel(LabelId.create(id));
	}

	/**
	 * Returns <code>true</code> if the label with the id passed as argument exists.
	 * 
	 * @param id label id to check
	 * @return <code>true</code> if the label with the id passed as argument exists
	 */
	@Override
	public boolean hasLabel(LabelId id) {
		// checks if the label id is consistent
		LabelId.checkIfValid(id);
		// checks if the label id exist
		return isType(id, ObjectType.OBJECT) || defaultOptions.hasLabel(id);
	}

	/**
	 * Removes the label by the id passed as argument, if exists.
	 * 
	 * @param id label id to check
	 */
	public void removeLabel(String id) {
		removeLabel(LabelId.create(id));
	}

	/**
	 * Removes the label by the id passed as argument, if exists.
	 * 
	 * @param id label id to check
	 */
	public void removeLabel(LabelId id) {
		// checks if the label id is consistent
		LabelId.checkIfValid(id);
		// checks if is an object
		// if is a string, the property is the ID
		if (isType(id, ObjectType.OBJECT)) {
			// removes from java script object if the label id exist
			remove(id);
		}
	}

	/**
	 * Returns the collection of labels.
	 * 
	 * @return the collection of labels
	 */
	@Override
	public List<Label> getLabels() {
		// gets an empty result
		List<Label> result = new ArrayList<>();
		// scan all keys
		for (Key key : keys()) {
			// checks if the type of the stored object
			// is an object
			if (isType(key, ObjectType.OBJECT)) {
				// gets label id
				LabelId id = LabelId.create(key.value());
				// gets and creates the label
				Label label = new Label(id, defaultOptions.getLabel(id), getValue(key));
				// adds to result
				result.add(label);
			}
		}
		// returns an unmodifiable list with the labels
		// in order it can not be updated
		return Collections.unmodifiableList(result);
	}

	/**
	 * Returns the label with the id passed as argument or <code>null</code> if not exist.
	 * 
	 * @param id label id to use to retrieve the label
	 * @return the label or <code>null</code> if not exist
	 */
	public Label getLabel(String id) {
		return getLabel(LabelId.create(id));
	}

	/**
	 * Returns the label with the id passed as argument or <code>null</code> if not exist.
	 * 
	 * @param id label id to check
	 * @return the label with the id passed as argument or <code>null</code> if not exist
	 */
	@Override
	public Label getLabel(LabelId id) {
		// checks if the label id is consistent
		LabelId.checkIfValid(id);
		// checks if the label id exist
		if (hasLabel(id) && isType(id, ObjectType.OBJECT)) {
			// gets from the cache
			return new Label(id, defaultOptions.getLabel(id), getValue(id));
		}
		// if here, the label id does not exist
		// then returns null
		return defaultOptions.getLabel(id);
	}

}