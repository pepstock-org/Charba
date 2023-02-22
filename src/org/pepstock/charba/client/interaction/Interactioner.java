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
package org.pepstock.charba.client.interaction;

import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.IsInteractionMode;
import org.pepstock.charba.client.events.ChartEventContext;
import org.pepstock.charba.client.items.InteractionItem;
import org.pepstock.charba.client.items.InteractionOptions;

/**
 * Interface to be implemented to create a custom interaction mode where you can decide how and which elements will be managed interacting with chart events and tooltips.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface Interactioner {

	/**
	 * Returns <code>true</code> if interactioner passed as argument is not <code>null</code> and its mode is not <code>null</code> as well.
	 * 
	 * @param interactioner interactioner to be checked
	 * @return <code>true</code> if interactioner passed as argument is not <code>null</code> and its mode is not <code>null</code>
	 */
	static boolean isValid(Interactioner interactioner) {
		return interactioner != null && Key.isValid(interactioner.getMode());
	}

	/**
	 * Returns the name of interaction mode which must be used in chart options.
	 * 
	 * @return the name of interaction mode
	 */
	IsInteractionMode getMode();

	/**
	 * Returns items which must be managed by CHART.JS event or hovering handler and by tooltips.
	 * 
	 * @param chart the chart we are returning items from
	 * @param event the event we are find things at
	 * @param options options to use
	 * @param useFinalPosition use final element position (animation target)
	 * @return items that are found
	 */
	List<InteractionItem> invoke(IsChart chart, ChartEventContext event, InteractionOptions options, boolean useFinalPosition);

}