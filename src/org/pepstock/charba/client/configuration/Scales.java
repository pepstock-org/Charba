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
package org.pepstock.charba.client.configuration;

import java.util.ArrayList;
import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.options.ExtendedOptions;
import org.pepstock.charba.client.options.Scale;
import org.pepstock.charba.client.options.ScaleIdChecker;

/**
 * The configuration element which contains all axes definitions.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class Scales extends ConfigurationContainer<ExtendedOptions> {

	// buffer to maintain axes
	//private final Map<String, Axis> storedAxes = new HashMap<>();

	/**
	 * Builds the object storing the chart instance and the root options element.
	 * 
	 * @param chart chart instance
	 * @param options root options element.
	 */
	Scales(IsChart chart, ExtendedOptions options) {
		super(chart, options);
	}

	/**
	 * Sets an array of X axes.
	 * 
	 * @param axes an array of axes.
	 */
	public void setAxes(Axis... axes) {
		// checks consistency of arguments
		if (axes != null && axes.length > 0) {
			// clears the buffer
//			storedAxes.clear();
			// creates the array
			Scale[] scales = new Scale[axes.length];
			// scans all scale arguments
			for (int i = 0; i < axes.length; i++) {
				// adds to array
				scales[i] = axes[i].getScale();
				// adds to buffer
//				storedAxes.put(axes[i].getId(), axes[i]);
			}
			// sets the array
			getConfiguration().getScales().setAxes(scales);
		}
	}

	/**
	 * Returns the scale with the id passed as argument or <code>null</code> if not exist.
	 * 
	 * @param scaleId scale id to check
	 * @return the scale with the id passed as argument or <code>null</code> if not exist
	 */
	public Axis getAxisById(String scaleId) {
//		return storedAxes.get(scaleId);
		return null;
	}

	/**
	 * Returns the scale with the id passed as argument or <code>null</code> if not exist.
	 * 
	 * @param scaleId scale id to check
	 * @return the scale with the id passed as argument or <code>null</code> if not exist
	 */
	public Axis getAxisById(Key scaleId) {
		// checks if the scale id is consistent
		ScaleIdChecker.check(scaleId);
		// returns the object if exist
//		return storedAxes.get(scaleId.value());
		return null;
	}

	/**
	 * Returns the list of X axes.
	 * 
	 * @return the xAxes by a unmodifiable list
	 */
	public List<Axis> getAxes() {
		// sets reference
//		List<Axis> result = storedAxes.isEmpty() ? Collections.emptyList() : new LinkedList<Axis>(storedAxes.values());
		// returns the unmodifiable list
//		return Collections.unmodifiableList(result);
		return new ArrayList<>();
	}

}