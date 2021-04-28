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

import org.pepstock.charba.client.defaults.IsDefaultBar;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.BorderSkipped;
import org.pepstock.charba.client.enums.PointStyle;

/**
 * Defaults for bar option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartBar implements IsDefaultBar {

	private final IsDefaultBar bar;

	/**
	 * Creates the object by bar option element instance.
	 * 
	 * @param bar bar option element instance.
	 */
	DefaultChartBar(IsDefaultBar bar) {
		this.bar = bar;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultArc#getBackgroundColorAsString()
	 */
	@Override
	public String getBackgroundColorAsString() {
		return bar.getBackgroundColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultArc#getBorderWidth()
	 */
	@Override
	public int getBorderWidth() {
		return bar.getBorderWidth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultArc#getBorderColorAsString()
	 */
	@Override
	public String getBorderColorAsString() {
		return bar.getBorderColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBar#getBorderSkipped()
	 */
	@Override
	public BorderSkipped getBorderSkipped() {
		return bar.getBorderSkipped();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBar#getBorderRadius()
	 */
	@Override
	public int getBorderRadius() {
		return bar.getBorderRadius();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPointStyler#getPointStyle()
	 */
	@Override
	public PointStyle getPointStyle() {
		return bar.getPointStyle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPointStyler#isPointStyleAsImage()
	 */
	@Override
	public boolean isPointStyleAsImage() {
		return bar.isPointStyleAsImage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPointStyler#getPointStyleAsImage()
	 */
	@Override
	public Img getPointStyleAsImage() {
		return bar.getPointStyleAsImage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptionsElement#getHoverBackgroundColorAsString()
	 */
	@Override
	public String getHoverBackgroundColorAsString() {
		return bar.getHoverBackgroundColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptionsElement#getHoverBorderWidth()
	 */
	@Override
	public int getHoverBorderWidth() {
		return bar.getHoverBorderWidth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptionsElement#getHoverBorderColorAsString()
	 */
	@Override
	public String getHoverBorderColorAsString() {
		return bar.getHoverBorderColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBar#getHoverBorderRadius()
	 */
	@Override
	public int getHoverBorderRadius() {
		return bar.getHoverBorderRadius();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBar#isEnableBorderRadius()
	 */
	@Override
	public boolean isEnableBorderRadius() {
		return bar.isEnableBorderRadius();
	}

}
