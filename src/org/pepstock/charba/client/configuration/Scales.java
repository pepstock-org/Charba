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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.options.ExtendedOptions;
import org.pepstock.charba.client.options.Scale;

/**
 * The configuration element which contains all axes definitions.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class Scales extends ConfigurationContainer<ExtendedOptions> {

	// buffer to maintain axes
	private final List<Axis> yAxes = new LinkedList<>();
	// buffer to maintain axes
	private final List<Axis> xAxes = new LinkedList<>();

	/**
	 * Builds the object storing the chart instance and the root options element.
	 * 
	 * @param chart chart instance
	 * @param options root options element.
	 */
	Scales(AbstractChart<?, ?> chart, ExtendedOptions options) {
		super(chart, options);
	}

	/**
	 * Sets an array of X axes.
	 * 
	 * @param axes an array of axes.
	 */
	public void setXAxes(Axis... axes) {
		// checks consistency of arguments
		if (axes != null && axes.length > 0) {
			// clears the buffer
			xAxes.clear();
			// creates the array
			Scale[] scales = new Scale[axes.length];
			// scans all scale arguments
			for (int i = 0; i < axes.length; i++) {
				// adds to array
				scales[i] = axes[i].getScale();
				// adds to buffer
				xAxes.add(axes[i]);
			}
			// sets the array
			getConfiguration().getScales().setXAxes(scales);
		}
	}

	/**
	 * Returns the list of X axes.
	 * 
	 * @return the xAxes by a unmodifiable list
	 */
	public List<Axis> getXAxes() {
		return xAxes.isEmpty() ? null : Collections.unmodifiableList(xAxes);
	}

	/**
	 * Sets an array of Y axes.
	 * 
	 * @param axes an array of axes.
	 */
	public void setYAxes(Axis... axes) {
		// checks consistency of arguments
		if (axes != null && axes.length > 0) {
			// clears the buffer
			yAxes.clear();
			// creates the array
			Scale[] scales = new Scale[axes.length];
			// scans all scale arguments
			for (int i = 0; i < axes.length; i++) {
				// adds to array
				scales[i] = axes[i].getScale();
				// adds to buffer
				yAxes.add(axes[i]);
			}
			// sets the array
			getConfiguration().getScales().setYAxes(scales);
		}
	}

	/**
	 * Returns the list of Y axes.
	 * 
	 * @return the yAxes by a unmodifiable list
	 */
	public List<Axis> getYAxes() {
		return yAxes.isEmpty() ? null : Collections.unmodifiableList(yAxes);
	}

}