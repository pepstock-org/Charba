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
import org.pepstock.charba.client.defaults.IsDefaultLegendLabels;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.enums.TextAlign;

/**
 * Defaults for legend labels option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartLegendLabels implements IsDefaultLegendLabels {

	private final IsDefaultLegendLabels labels;

	/**
	 * Creates the object by legend labels option element instance.
	 * 
	 * @param labels legend labels option element instance.
	 */
	DefaultChartLegendLabels(IsDefaultLegendLabels labels) {
		this.labels = labels;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegendLabels#getColorAsString()
	 */
	@Override
	public String getColorAsString() {
		return labels.getColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegendLabels#getFont()
	 */
	@Override
	public IsDefaultFont getFont() {
		return labels.getFont();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegendLabels#isUsePointStyle()
	 */
	@Override
	public boolean isUsePointStyle() {
		return labels.isUsePointStyle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPointStyler#getPointStyle()
	 */
	@Override
	public PointStyle getPointStyle() {
		return labels.getPointStyle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegendLabels#getBoxWidth()
	 */
	@Override
	public int getBoxWidth() {
		return labels.getBoxWidth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegendLabels#getBoxHeight()
	 */
	@Override
	public int getBoxHeight() {
		return labels.getBoxHeight();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegendLabels#getPadding()
	 */
	@Override
	public int getPadding() {
		return labels.getPadding();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPointStyler#isPointStyleAsImage()
	 */
	@Override
	public boolean isPointStyleAsImage() {
		return labels.isPointStyleAsImage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPointStyler#getPointStyleAsImage()
	 */
	@Override
	public Img getPointStyleAsImage() {
		return labels.getPointStyleAsImage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegendLabels#getTextAlign()
	 */
	@Override
	public TextAlign getTextAlign() {
		return labels.getTextAlign();
	}

}
