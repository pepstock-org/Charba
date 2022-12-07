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

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.defaults.IsDefaultLegendLabels;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.enums.PointStyleType;
import org.pepstock.charba.client.enums.TextAlign;
import org.pepstock.charba.client.items.Undefined;

/**
 * CHART.JS default values for LEGENDLABELS element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultLegendLabels implements IsDefaultLegendLabels {

	private static final int DEFAULT_PADDING = 10;

	private static final int DEFAULT_BOX_WIDTH = 40;

	private static final boolean DEFAULT_USE_POINT_STYLE = false;

	private static final double DEFAULT_POINT_STYLE_WIDTH = Undefined.DOUBLE;

	private static final boolean DEFAULT_USE_BORDER_RADIUS = false;

	private static final int DEFAULT_BORDER_RADIUS = 0;

	private final DefaultRoutedFont font = new DefaultRoutedFont();

	/**
	 * To avoid any instantiation
	 */
	DefaultLegendLabels() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegendLabels#getColorAsString()
	 */
	@Override
	public String getColorAsString() {
		return Defaults.get().getGlobal().getColorAsString();
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
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegendLabels#getPointStyleWidth()
	 */
	@Override
	public double getPointStyleWidth() {
		return DEFAULT_POINT_STYLE_WIDTH;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPointStyleHandler#getPointStyleType()
	 */
	@Override
	public PointStyleType getPointStyleType() {
		return PointStyleType.STRING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPointStyleHandler#getPointStyleAsCanvas()
	 */
	@Override
	public Canvas getPointStyleAsCanvas() {
		return Undefined.CANVAS_ELEMENT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPointStyler#getPointStyleAsImage()
	 */
	@Override
	public Img getPointStyleAsImage() {
		return Undefined.IMAGE_ELEMENT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegendLabels#getTextAlign()
	 */
	@Override
	public TextAlign getTextAlign() {
		return TextAlign.CENTER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegendLabels#isUseBorderRadius()
	 */
	@Override
	public boolean isUseBorderRadius() {
		return DEFAULT_USE_BORDER_RADIUS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLegendLabels#getBorderRadius()
	 */
	@Override
	public int getBorderRadius() {
		return DEFAULT_BORDER_RADIUS;
	}

}
