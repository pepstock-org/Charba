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
package org.pepstock.charba.client.jsinterop.options.legend;

import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.jsinterop.commons.AssignHelper;
import org.pepstock.charba.client.jsinterop.commons.IsDelegated;
import org.pepstock.charba.client.jsinterop.options.legend.labels.LegendLabels;
import org.pepstock.charba.client.jsinterop.options.legend.labels.NativeLegendLabels;

/**
 * The chart legend displays data about the datasets that area appearing on the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Legend implements IsDelegated<NativeLegend> {
	
	private final NativeLegend delegated;
	
	private final IsDefaultLegend defaultValues;

	private LegendLabels labels;
	
	public Legend(IsDefaultLegend defaultValues) {
		this(new NativeLegend(), defaultValues);
	}

	public Legend(NativeLegend delegated, IsDefaultLegend defaultValues) {
		this.delegated = delegated;
		this.defaultValues = defaultValues;
		NativeLegendLabels labelsObject = delegated.getLabels();
		if (labelsObject != null) {
			labels = new LegendLabels(labelsObject, defaultValues.getLabels());
		} else {
			setLabels(new LegendLabels(defaultValues.getLabels()));
		}
	}

	/**
	 * @return the labels
	 */
	public LegendLabels getLabels() {
		return labels;
	}
	
	public void setLabels(LegendLabels labels) {
		this.labels = labels;
		delegated.setLabels(labels.getDelegated());
	}


	/**
	 * Sets if the legend is shown.
	 * 
	 * @param display if the legend is shown.
	 */
	public void setDisplay(boolean display) {
		delegated.setDisplay(display);
	}

	/**
	 * Returns if the legend is shown.
	 * 
	 * @return if the legend is shown. Default is true.
	 */
	public boolean isDisplay() {
		return AssignHelper.check(delegated.isDisplay(), defaultValues.isDisplay());
	}

	/**
	 * Marks that this box should take the full width of the canvas (pushing down other boxes).
	 * 
	 * @param fullWidth Marks that this box should take the full width of the canvas (pushing down other boxes)
	 */
	public void setFullWidth(boolean fullWidth) {
		delegated.setFullWidth(fullWidth);
	}

	/**
	 * Returns if marks that this box should take the full width of the canvas (pushing down other boxes)
	 * 
	 * @return Marks that this box should take the full width of the canvas (pushing down other boxes). Default is true.
	 */
	public boolean isFullWidth() {
		return AssignHelper.check(delegated.isFullWidth(), defaultValues.isFullWidth());
	}

	/**
	 * Sets the legend will show datasets in reverse order.
	 * 
	 * @param reverse legend will show datasets in reverse order.
	 */
	public void setReverse(boolean reverse) {
		delegated.setReverse(reverse);
	}

	/**
	 * Returns if the legend will show datasets in reverse order.
	 * 
	 * @return Legend will show datasets in reverse order. Default is false.
	 */
	public boolean isReverse() {
		return AssignHelper.check(delegated.isReverse(), defaultValues.isReverse());
	}

	/**
	 * Sets the position of the legend.
	 * 
	 * @param position Position of the legend.
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public void setPosition(Position position) {
		delegated.setPosition(position.name());
	}

	/**
	 * Returns the position of the legend.
	 * 
	 * @return Position of the legend. Default is {@link org.pepstock.charba.client.enums.Position#top}.
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public Position getPosition() {
		return AssignHelper.deserialize(AssignHelper.check(delegated.getPosition(), defaultValues.getPosition()), Position.class, Position.top);
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.commons.IsDelegated#getDelegated()
	 */
	@Override
	public NativeLegend getDelegated() {
		return delegated;
	}
	
}