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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;

/**
 * Abstract options for PIE chart. It contains all properties for this kind of chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class AbstractPieOptions extends ConfigurationOptions {

	/**
	 * Builds the object storing the chart instance and defaults.
	 * 
	 * @param chart chart instance
	 * @param defaultvalues defaults of chart
	 */
	protected AbstractPieOptions(IsChart chart, IsDefaultScaledOptions defaultvalues) {
		super(chart, defaultvalues);
	}

	/**
	 * Sets the portion of the chart that is cut out of the middle.<br>
	 * As {@link Double}, it is considered to be pixels.
	 * 
	 * @param cutout the portion of the chart that is cut out of the middle.<br>
	 *            As {@link Double}, it is considered to be pixels.
	 */
	public void setCutout(double cutout) {
		getConfiguration().setCutout(cutout);
	}

	/**
	 * Sets the portion of the chart that is cut out of the middle.<br>
	 * As {@link String} and ending with '%', percentage of the chart radius.
	 * 
	 * @param cutout the portion of the chart that is cut out of the middle.<br>
	 *            As {@link String} and ending with '%', percentage of the chart radius.
	 */
	public void setCutoutPercentage(String cutout) {
		getConfiguration().setCutoutPercentage(cutout);
	}

	/**
	 * Returns the portion of the chart that is cut out of the middle.<br>
	 * As {@link Double}, it is considered to be pixels.
	 * 
	 * @return the portion of the chart that is cut out of the middle.<br>
	 *         As {@link Double}, it is considered to be pixels.
	 */
	public double getCutout() {
		return getConfiguration().getCutout();
	}

	/**
	 * Returns the portion of the chart that is cut out of the middle.<br>
	 * As {@link String} and ending with '%', percentage of the chart radius.
	 * 
	 * @return the portion of the chart that is cut out of the middle.<br>
	 *         As {@link String} and ending with '%', percentage of the chart radius.
	 */
	public String getCutoutPercentage() {
		return getConfiguration().getCutoutPercentage();
	}

	/**
	 * Sets the outer radius of the chart.<br>
	 * As {@link Double}, it is considered to be pixels.
	 * 
	 * @param radius the outer radius of the chart.<br>
	 *            As {@link Double}, it is considered to be pixels.
	 */
	public void setRadius(double radius) {
		getConfiguration().setRadius(radius);
	}

	/**
	 * Returns the outer radius of the chart.<br>
	 * As {@link Double}, it is considered to be pixels.
	 * 
	 * @return the outer radius of the chart.<br>
	 *         As {@link Double}, it is considered to be pixels.
	 */
	public double getRadius() {
		return getConfiguration().getRadius();
	}

	/**
	 * Sets the outer radius of the chart.<br>
	 * As {@link String} and ending with '%', percentage of the maximum radius.
	 * 
	 * @param radius the outer radius of the chart.<br>
	 *            As {@link String} and ending with '%', percentage of the maximum radius.
	 */
	public void setRadiusPercentage(String radius) {
		getConfiguration().setRadiusPercentage(radius);
	}

	/**
	 * Returns the outer radius of the chart.<br>
	 * As {@link String} and ending with '%', percentage of the maximum radius.
	 * 
	 * @return the outer radius of the chart.<br>
	 *         As {@link String} and ending with '%', percentage of the maximum radius.
	 */
	public String getRadiusPercentage() {
		return getConfiguration().getRadiusPercentage();
	}

	/**
	 * Sets the starting angle to draw arcs from.
	 * 
	 * @param rotation starting angle to draw arcs from.
	 */
	public void setRotation(double rotation) {
		getConfiguration().setRotation(rotation);
	}

	/**
	 * Returns the starting angle to draw arcs from.
	 * 
	 * @return starting angle to draw arcs from.
	 */
	public double getRotation() {
		return getConfiguration().getRotation();
	}

	/**
	 * Sets the sweep to allow arcs to cover.
	 * 
	 * @param circumference the sweep to allow arcs to cover.
	 */
	public void setCircumference(double circumference) {
		getConfiguration().setCircumference(circumference);
	}

	/**
	 * Returns the sweep to allow arcs to cover.
	 * 
	 * @return the sweep to allow arcs to cover.
	 */
	public double getCircumference() {
		return getConfiguration().getCircumference();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.ConfigurationOptions#getAxisById(int)
	 */
	@Override
	protected final Axis getAxisById(int id) {
		// no axis
		return null;
	}
}