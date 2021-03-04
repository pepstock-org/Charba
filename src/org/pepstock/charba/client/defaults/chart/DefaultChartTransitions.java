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
package org.pepstock.charba.client.defaults.chart;

import org.pepstock.charba.client.defaults.IsDefaultAnimationTransition;
import org.pepstock.charba.client.defaults.IsDefaultTransitions;
import org.pepstock.charba.client.options.IsTransitionKey;

/**
 * Defaults for transitions option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartTransitions implements IsDefaultTransitions {

	private final IsDefaultTransitions transitions;

	/**
	 * Creates the object by transitions option element instance.
	 * 
	 * @param transitions transitions option element instance.
	 */
	public DefaultChartTransitions(IsDefaultTransitions transitions) {
		this.transitions = transitions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTransitions#has(org.pepstock.charba.client.options.IsTransitionKey)
	 */
	@Override
	public boolean has(IsTransitionKey transition) {
		return transitions.has(transition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTransitions#get(org.pepstock.charba.client.options.IsTransitionKey)
	 */
	@Override
	public IsDefaultAnimationTransition get(IsTransitionKey transition) {
		return transitions.get(transition);
	}

}
