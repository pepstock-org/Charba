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
package org.pepstock.charba.client.configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.pepstock.charba.client.commons.ArrayUtil;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.options.Scale;
import org.pepstock.charba.client.options.ScaleId;

/**
 * The configuration element which contains all axes definitions.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class Scales extends ConfigurationOptionsContainer {

	// buffer to maintain axes
	private final Map<String, Axis> storedAxes = new HashMap<>();

	/**
	 * Builds the object storing the root options element.
	 * 
	 * @param options root options element.
	 */
	protected Scales(ScalesOptions options) {
		super(options);
	}

	/**
	 * Sets an array of X axes.
	 * 
	 * @param axes an array of axes.
	 */
	public void setAxes(Axis... axes) {
		// checks consistency of arguments
		if (ArrayUtil.isNotEmpty(axes)) {
			// checks consistency of scales
			super.getOptions().getChart().checkAxes(axes);
			// gets charts id
			String chartId = getOptions().getChart().getId();
			// clears the buffer
			storedAxes.clear();
			// creates the array
			Scale[] scales = new Scale[axes.length];
			// scans all scale arguments
			for (int i = 0; i < axes.length; i++) {
				// gets axis id
				String axisChartId = axes[i].getChart().getId();
				// checks if the axis is related to the same chart
				Checker.assertCheck(chartId.equalsIgnoreCase(axisChartId), "Chart id " + chartId + "' of axis '" + axes[i].getId().value() + "' is not the same of the options: " + chartId);
				// adds to array
				scales[i] = axes[i].getScale();
				// adds to buffer
				storedAxes.put(axes[i].getId().value(), axes[i]);
			}
			// sets the array
			getConfiguration().getScales().setAxes(scales);
		} else {
			// removes all current one
			// sets an empty array
			getConfiguration().getScales().setAxes();
		}
	}

	/**
	 * Returns the scale with the id passed as argument or <code>null</code> if not exist.
	 * 
	 * @param scaleId scale id to check
	 * @return the scale with the id passed as argument or <code>null</code> if not exist
	 */
	public Axis getAxisById(String scaleId) {
		return storedAxes.get(scaleId);
	}

	/**
	 * Returns the scale with the id passed as argument or <code>null</code> if not exist.
	 * 
	 * @param scaleId scale id to check
	 * @return the scale with the id passed as argument or <code>null</code> if not exist
	 */
	public Axis getAxisById(ScaleId scaleId) {
		// checks if the scale id is consistent
		ScaleId.checkIfValid(scaleId);
		// returns the object if exist
		return storedAxes.get(scaleId.value());
	}

	/**
	 * Returns the list of X axes.
	 * 
	 * @return the xAxes by a unmodifiable list
	 */
	public List<Axis> getAxes() {
		// sets reference
		List<Axis> result = storedAxes.isEmpty() ? Collections.emptyList() : new LinkedList<>(storedAxes.values());
		// returns the unmodifiable list
		return Collections.unmodifiableList(result);
	}

}