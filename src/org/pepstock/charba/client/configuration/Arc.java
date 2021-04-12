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

import org.pepstock.charba.client.defaults.IsDefaultArc;
import org.pepstock.charba.client.enums.BorderAlign;
import org.pepstock.charba.client.options.AbstractElement;

/**
 * Arcs are used in the polar area, doughnut and pie charts.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class Arc extends AbstractConfigurationElement<IsDefaultArc> {

	/**
	 * Builds the object setting the java script options object and defaults options for arc.
	 * 
	 * @param options root options of chart
	 */
	Arc(ConfigurationOptions options) {
		super(options);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractConfigurationElement#getElement()
	 */
	@Override
	protected AbstractElement<IsDefaultArc> getElement() {
		return getConfiguration().getElements().getArc();
	}

	/**
	 * Sets the property to set the border alignment on chart datasets.
	 * 
	 * @param align the property to set the border alignment on chart datasets
	 */
	public void setBorderAlign(BorderAlign align) {
		getConfiguration().getElements().getArc().setBorderAlign(align);
	}

	/**
	 * Returns the property to set the border alignment on chart datasets.
	 * 
	 * @return the property to set the border alignment on chart datasets.
	 */
	public BorderAlign getBorderAlign() {
		return getConfiguration().getElements().getArc().getBorderAlign();
	}

	/**
	 * Sets the relative thickness of the data set.<br>
	 * Providing a value for weight will cause the pie or doughnut data set to be drawn with a thickness relative to the sum of all the data set weight values.
	 * 
	 * @param weight the relative thickness of the data set
	 */
	public void setWeight(double weight) {
		getConfiguration().getElements().getArc().setWeight(weight);
	}

	/**
	 * Returns the relative thickness of the data set.<br>
	 * Providing a value for weight will cause the pie or doughnut data set to be drawn with a thickness relative to the sum of all the data set weight values.
	 * 
	 * @return the relative thickness of the data set
	 */
	public double getWeight() {
		return getConfiguration().getElements().getArc().getWeight();
	}

	/**
	 * Sets the arc angle to cover.
	 * 
	 * @param angle the arc angle to cover
	 */
	public void setAngle(double angle) {
		getConfiguration().getElements().getArc().setAngle(angle);
	}

	/**
	 * Returns the arc angle to cover.
	 * 
	 * @return the arc angle to cover
	 */
	public double getAngle() {
		return getConfiguration().getElements().getArc().getAngle();
	}

	/**
	 * Sets the arc offset (in pixels).
	 * 
	 * @param offset the arc offset
	 */
	public void setOffset(int offset) {
		getConfiguration().getElements().getArc().setOffset(offset);
	}

	/**
	 * Returns the arc offset (in pixels).
	 * 
	 * @return the arc offset
	 */
	public int getOffset() {
		return getConfiguration().getElements().getArc().getOffset();
	}

	/**
	 * Sets the arc border radius (in pixels).
	 * 
	 * @param borderRadius the arc border radius (in pixels).
	 */
	public void setBorderRadius(int borderRadius) {
		getConfiguration().getElements().getArc().setBorderRadius(borderRadius);
	}

	/**
	 * Returns the arc border radius (in pixels).
	 * 
	 * @return the arc border radius (in pixels).
	 */
	public int getBorderRadius() {
		return getConfiguration().getElements().getArc().getBorderRadius();
	}
	
	/**
	 * Sets the arc offset (in pixels) when hovered.
	 * 
	 * @param offset the arc offset when hovered
	 */
	public void setHoverOffset(int offset) {
		getConfiguration().getElements().getArc().setHoverOffset(offset);
	}

	/**
	 * Returns the arc offset (in pixels) when hovered.
	 * 
	 * @return the arc offset when hovered
	 */
	public int getHoverOffset() {
		return getConfiguration().getElements().getArc().getHoverOffset();
	}
}