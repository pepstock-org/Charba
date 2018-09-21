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
package org.pepstock.charba.client.jsinterop.configuration.scales;

import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.jsinterop.configuration.ChartContainer;
import org.pepstock.charba.client.jsinterop.options.Options;
import org.pepstock.charba.client.jsinterop.options.Scale;

/**
 * The configuration element which contains all axes definitions.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Scales extends ChartContainer<Options> {

	// buffer to maintain axes
	private final List<Axis> yAxes = new LinkedList<>();
	// buffer to maintain axes
	private final List<Axis> xAxes = new LinkedList<>();

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	public Scales(AbstractChart<?, ?> chart, Options options) {
		super(chart, options);
	}

	/**
	 * Sets an array of X axes.
	 * 
	 * @param axes an array of axes.
	 */
	public void setXAxes(Axis... axes) {
		if (axes != null && axes.length > 0) {
			xAxes.clear();
			Scale[] scales = new Scale[axes.length];
			for (int i=0; i<axes.length; i++) {
				scales[i] = axes[i].getScale();
				xAxes.add(axes[i]);
			}
			getConfiguration().getScales().setXAxes(scales);
		}
	}

	/**
	 * @return the xAxes
	 */
	public List<Axis> getXAxes() {
		return xAxes.isEmpty() ? null : xAxes;
	}

	/**
	 * Sets an array of Y axes.
	 * 
	 * @param axes an array of axes.
	 */
	public void setYAxes(Axis... axes) {
		if (axes != null && axes.length > 0) {
			yAxes.clear();
			Scale[] scales = new Scale[axes.length];
			for (int i=0; i<axes.length; i++) {
				scales[i] = axes[i].getScale();
				yAxes.add(axes[i]);
			}
			getConfiguration().getScales().setYAxes(scales);
		}
	}

	/**
	 * @return the yAxes
	 */
	public List<Axis> getYAxes() {
		return yAxes.isEmpty() ? null : yAxes;
	}

}