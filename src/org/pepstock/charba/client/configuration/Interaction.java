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

import org.pepstock.charba.client.enums.InteractionAxis;
import org.pepstock.charba.client.enums.InteractionMode;
import org.pepstock.charba.client.items.InteractionItem;

/**
 * Definitions about how the user can interact with chart elements.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Interaction extends ConfigurationOptionsContainer {

	/**
	 * Builds the object storing the root options element.
	 * 
	 * @param options root options element.
	 */
	protected Interaction(ConfigurationOptions options) {
		super(options);
	}

	/**
	 * Sets which elements appear in the interaction.
	 * 
	 * @param mode which elements appear in the interaction.
	 */
	public void setMode(InteractionMode mode) {
		getConfiguration().getInteraction().setMode(mode);
	}

	/**
	 * Returns which elements appear in the interaction.
	 * 
	 * @return which elements appear in the interaction.
	 */
	public InteractionMode getMode() {
		return getConfiguration().getInteraction().getMode();
	}

	/**
	 * if true, the mode only applies when the mouse position intersects an item on the chart.
	 * 
	 * @param intersect if true, the mode only applies when the mouse position intersects an item on the chart.
	 */
	public void setIntersect(boolean intersect) {
		getConfiguration().getInteraction().setIntersect(intersect);
	}

	/**
	 * if true, the mode only applies when the mouse position intersects an item on the chart.
	 * 
	 * @return if true, the mode only applies when the mouse position intersects an item on the chart.
	 */
	public boolean isIntersect() {
		return getConfiguration().getInteraction().isIntersect();
	}

	/**
	 * Sets which directions are used in calculating distances.
	 * 
	 * @param axis define which directions are used in calculating distances.
	 */
	public void setAxis(InteractionAxis axis) {
		getConfiguration().getInteraction().setAxis(axis);
	}

	/**
	 * Returns which directions are used in calculating distances.
	 * 
	 * @return define which directions are used in calculating distances.
	 */
	public InteractionAxis getAxis() {
		return getConfiguration().getInteraction().getAxis();
	}

	/**
	 * If true, the invisible points that are outside of the chart area will also be included when evaluating interactions.
	 * 
	 * @param includeInvisible if true, the invisible points that are outside of the chart area will also be included when evaluating interactions.
	 */
	public void setIncludeInvisible(boolean includeInvisible) {
		getConfiguration().getInteraction().setIncludeInvisible(includeInvisible);
	}

	/**
	 * If true, the invisible points that are outside of the chart area will also be included when evaluating interactions.
	 * 
	 * @return if true, the invisible points that are outside of the chart area will also be included when evaluating interactions.
	 */
	public boolean isIncludeInvisible() {
		return getConfiguration().getInteraction().isIncludeInvisible();
	}

	/**
	 * Creates an {@link InteractionItem} using the configuration defined in this interaction.
	 * 
	 * @return an {@link InteractionItem} using the configuration defined in this interaction
	 */
	public final InteractionItem createItem() {
		return getConfiguration().getInteraction().create();
	}
}