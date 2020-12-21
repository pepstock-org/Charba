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

import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.defaults.IsDefaultPadding;
import org.pepstock.charba.client.defaults.IsDefaultScaleLabel;
import org.pepstock.charba.client.enums.ScaleLabelAlign;
import org.pepstock.charba.client.options.ScaleLabel;

/**
 * Defaults for scale label option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartScaleLabel implements IsDefaultScaleLabel {

	private final ScaleLabel scaleLabel;

	private final DefaultChartScaleLabelPadding padding;

	/**
	 * Creates the object by scale label option element instance.
	 * 
	 * @param scaleLabel scale label option element instance.
	 */
	DefaultChartScaleLabel(ScaleLabel scaleLabel) {
		this.scaleLabel = scaleLabel;
		// creates sub element
		this.padding = new DefaultChartScaleLabelPadding(scaleLabel.getPadding());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaleLabel#getFont()
	 */
	@Override
	public IsDefaultFont getFont() {
		return scaleLabel.getFont();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaleLabel#getColorAsString()
	 */
	@Override
	public String getColorAsString() {
		return scaleLabel.getColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaleLabel#getPadding()
	 */
	@Override
	public IsDefaultPadding getPadding() {
		return padding;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaleLabel#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return scaleLabel.isDisplay();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaleLabel#getLabelString()
	 */
	@Override
	public String getLabelString() {
		return scaleLabel.getLabelString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaleLabel#getAlign()
	 */
	@Override
	public ScaleLabelAlign getAlign() {
		return scaleLabel.getAlign();
	}

}
