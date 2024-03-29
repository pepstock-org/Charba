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
package org.pepstock.charba.client.defaults.chart;

import org.pepstock.charba.client.defaults.IsDefaultInteraction;
import org.pepstock.charba.client.enums.InteractionAxis;
import org.pepstock.charba.client.enums.IsInteractionMode;

/**
 * Defaults for interaction option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class DefaultChartInteraction implements IsDefaultInteraction {

	private final IsDefaultInteraction interaction;

	/**
	 * Creates the object by interaction option element instance.
	 * 
	 * @param interaction interaction option element instance.
	 */
	public DefaultChartInteraction(IsDefaultInteraction interaction) {
		this.interaction = interaction;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultInteraction#getMode()
	 */
	@Override
	public final IsInteractionMode getMode() {
		return interaction.getMode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultInteraction#isIntersect()
	 */
	@Override
	public final boolean isIntersect() {
		return interaction.isIntersect();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultInteraction#getAxis()
	 */
	@Override
	public final InteractionAxis getAxis() {
		return interaction.getAxis();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultInteraction#isIncludeInvisible()
	 */
	@Override
	public boolean isIncludeInvisible() {
		return interaction.isIncludeInvisible();
	}

}