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

import org.pepstock.charba.client.enums.InteractionAxis;
import org.pepstock.charba.client.enums.IsInteractionMode;

/**
 * Definitions about how elements appear in the chart, hovering the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Hover extends ConfigurationOptionsContainer {

	/**
	 * Builds the object storing the root options element.
	 * 
	 * @param options root options element.
	 */
	Hover(ConfigurationOptions options) {
		super(options);
	}

	/**
	 * Sets which elements appear in the chart, hovering the chart.
	 * 
	 * @param mode which elements appear in the chart, hovering the chart.
	 */
	public void setMode(IsInteractionMode mode) {
		getConfiguration().getHover().setMode(mode);
	}

	/**
	 * Sets which elements appear in the chart, hovering the chart.
	 * 
	 * @param mode which elements appear in the chart, hovering the chart.
	 */
	public void setMode(String mode) {
		getConfiguration().getHover().setMode(mode);
	}

	/**
	 * Returns which elements appear in the chart, hovering the chart.
	 * 
	 * @return which elements appear in the chart, hovering the chart.
	 */
	public IsInteractionMode getMode() {
		return getConfiguration().getHover().getMode();
	}

	/**
	 * if true, the hover mode only applies when the mouse position intersects an item on the chart.
	 * 
	 * @param intersect if true, the hover mode only applies when the mouse position intersects an item on the chart.
	 */
	public void setIntersect(boolean intersect) {
		getConfiguration().getHover().setIntersect(intersect);
	}

	/**
	 * if true, the hover mode only applies when the mouse position intersects an item on the chart.
	 * 
	 * @return if true, the hover mode only applies when the mouse position intersects an item on the chart.
	 */
	public boolean isIntersect() {
		return getConfiguration().getHover().isIntersect();
	}

	/**
	 * Sets which directions are used in calculating distances.
	 * 
	 * @param axis define which directions are used in calculating distances.
	 */
	public void setAxis(InteractionAxis axis) {
		getConfiguration().getHover().setAxis(axis);
	}

	/**
	 * Returns which directions are used in calculating distances.
	 * 
	 * @return define which directions are used in calculating distances.
	 */
	public InteractionAxis getAxis() {
		return getConfiguration().getHover().getAxis();
	}
}