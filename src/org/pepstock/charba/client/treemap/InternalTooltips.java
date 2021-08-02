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
package org.pepstock.charba.client.treemap;

import org.pepstock.charba.client.configuration.ConfigurationOptions;
import org.pepstock.charba.client.configuration.Tooltips;
import org.pepstock.charba.client.enums.IsTooltipPosition;

/**
 * Specific tooltip options for treemap chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class InternalTooltips extends Tooltips {

	// positioner name provided out of the box by controller
	private static final String POSITIONER_VALUE = "treemap";
	// positioner name provided out of the box by controller
	private static final Positioner POSITIONER = new Positioner();

	/**
	 * Builds the object storing the chart instance and the root options element.
	 * 
	 * @param options root options element.
	 */
	InternalTooltips(ConfigurationOptions options) {
		super(options);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.Tooltips#setPosition(org.pepstock.charba.client.enums.IsTooltipPosition)
	 */
	@Override
	public void setPosition(IsTooltipPosition position) {
		// ignore position
		// to maintain the treemap positioner
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.Tooltips#getPosition()
	 */
	@Override
	public IsTooltipPosition getPosition() {
		return POSITIONER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.Tooltips#setIntersect(boolean)
	 */
	@Override
	public void setIntersect(boolean intersect) {
		// ignore the argument
		// to use always the configuration of the controller
		super.setIntersect(true);
	}

	/**
	 * Internal class to return the positioner provided out of the box by controller.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class Positioner implements IsTooltipPosition {

		/**
		 * To avoid any instantiation
		 */
		private Positioner() {
			// do nothing
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return POSITIONER_VALUE;
		}

	}

}