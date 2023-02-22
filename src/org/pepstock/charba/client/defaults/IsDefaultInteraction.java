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
package org.pepstock.charba.client.defaults;

import org.pepstock.charba.client.enums.InteractionAxis;
import org.pepstock.charba.client.enums.IsInteractionMode;
import org.pepstock.charba.client.items.InteractionOptions;

/**
 * Interface to define interaction object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultInteraction {

	/**
	 * Returns which elements appear in the tooltip.
	 * 
	 * @return which elements appear in the tooltip.
	 */
	IsInteractionMode getMode();

	/**
	 * If true, the hover mode only applies when the mouse position intersects an item on the chart.
	 * 
	 * @return if true, the hover mode only applies when the mouse position intersects an item on the chart.
	 */
	boolean isIntersect();

	/**
	 * Returns which directions are used in calculating distances.
	 * 
	 * @return define which directions are used in calculating distances.
	 */
	InteractionAxis getAxis();

	/**
	 * If true, the invisible points that are outside of the chart area will also be included when evaluating interactions.
	 * 
	 * @return if true, the invisible points that are outside of the chart area will also be included when evaluating interactions.
	 */
	boolean isIncludeInvisible();

	/**
	 * Creates an {@link InteractionOptions} using the configuration defined in this interaction.
	 * 
	 * @return an {@link InteractionOptions} using the configuration defined in this interaction
	 */
	default InteractionOptions create() {
		// creates options
		InteractionOptions options = new InteractionOptions(getMode(), isIntersect(), getAxis());
		options.setIncludeInvisible(isIncludeInvisible());
		return options;
	}

}