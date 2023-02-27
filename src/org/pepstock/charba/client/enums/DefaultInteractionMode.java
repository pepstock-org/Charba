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
package org.pepstock.charba.client.enums;

import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.events.ChartEventContext;
import org.pepstock.charba.client.interaction.Interactioner;
import org.pepstock.charba.client.interaction.Interactions;
import org.pepstock.charba.client.items.InteractionItem;
import org.pepstock.charba.client.items.InteractionOptions;

/**
 * When configuring interaction with the graph via hover or tooltips, a number of different modes are available to set which elements appear via tooltip or hover.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum DefaultInteractionMode implements IsInteractionMode, Interactioner
{

	/**
	 * Finds all of the items that intersect the point.
	 */
	POINT("point"),
	/**
	 * Gets the item that is nearest to the point.<br>
	 * The nearest item is determined based on the distance to the center of the chart item (point, bar).<br>
	 * If 2 or more items are at the same distance, the one with the smallest area is used.
	 */
	NEAREST("nearest"),
	/**
	 * Finds all items at the same index. If the intersect setting is true, the first intersecting item is used to determine the index in the data.<br>
	 * If intersect false the nearest item, in the x direction, is used to determine the index.
	 */
	INDEX("index"),
	/**
	 * Finds all items in the same dataset. If the intersect setting is true, the first intersecting item is used to determine the index in the data.<br>
	 * If intersect false the nearest item is used to determine the index.
	 */
	DATASET("dataset"),
	/**
	 * Returns all items that would intersect based on the X coordinate of the position only.<br>
	 * Note that this only applies to cartesian charts
	 */
	X("x"),
	/**
	 * Returns all items that would intersect based on the Y coordinate of the position.<br>
	 * Note that this only applies to cartesian charts.
	 */
	Y("y");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private DefaultInteractionMode(String value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.interaction.Interactioner#getMode()
	 */
	@Override
	public IsInteractionMode getMode() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.interaction.Interactioner#invoke(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.events.ChartEventContext,
	 * org.pepstock.charba.client.items.InteractionOptions, boolean)
	 */
	@Override
	public List<InteractionItem> invoke(IsChart chart, ChartEventContext event, InteractionOptions options, boolean useFinalPosition) {
		return Interactions.get().getInteractioner(this).invoke(chart, event, options, useFinalPosition);
	}

}