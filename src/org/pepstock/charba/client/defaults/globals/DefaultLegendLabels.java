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
package org.pepstock.charba.client.defaults.globals;

import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.defaults.IsDefaultLegendLabels;
import org.pepstock.charba.client.enums.PointStyle;

/**
 * CHART.JS default values for LEGENDLABELS element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultLegendLabels implements IsDefaultLegendLabels {

	private static final int DEFAULT_PADDING = 10;

	private static final int DEFAULT_BOX_WIDTH = 40;

	private static final boolean DEFAULT_USE_POINT_STYLE = false;

	private final DefaultFont font = new DefaultFont();

	/**
	 * To avoid any instantiation
	 */
	DefaultLegendLabels() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegendLabels#getFont()
	 */
	@Override
	public IsDefaultFont getFont() {
		return font;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.legend.labels.IsDefaultLegendLabels#isUsePointStyle()
	 */
	@Override
	public boolean isUsePointStyle() {
		return DEFAULT_USE_POINT_STYLE;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPointStyler#getPointStyle()
	 */
	@Override
	public PointStyle getPointStyle() {
		return PointStyle.CIRCLE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.legend.labels.IsDefaultLegendLabels#getBoxWidth()
	 */
	@Override
	public int getBoxWidth() {
		return DEFAULT_BOX_WIDTH;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegendLabels#getBoxHeight()
	 */
	@Override
	public int getBoxHeight() {
		return font.getSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.legend.labels.IsDefaultLegendLabels#getPadding()
	 */
	@Override
	public int getPadding() {
		return DEFAULT_PADDING;
	}
	
}
