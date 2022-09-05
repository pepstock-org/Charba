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

import org.pepstock.charba.client.defaults.IsDefaultBar;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.enums.BorderSkipped;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.enums.PointStyleType;
import org.pepstock.charba.client.items.Undefined;

/**
 * CHART.JS default values for BAR element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultBar extends AbstractDefaultOptionsElement implements IsDefaultBar {

	/**
	 * Value to store in the CHART.JS configuration when the amount of pixels to inflate the bar rectangles, when drawing, is automatically calculated,
	 * <b>{@value AUTO_INFLATE_AMOUNT}</b>.
	 */
	public static final String AUTO_INFLATE_AMOUNT = "auto";

	private static final boolean DEFAULT_ENABLE_BORDER_RADIUS = true;

	private static final int DEFAULT_BORDER_WIDTH = 0;

	private static final int DEFAULT_BORDER_RADIUS = 0;

	private static final boolean DEFAULT_AUTO_INFLATE_AMOUNT = true;

	private static final double DEFAULT_BASE = Undefined.DOUBLE;

	/**
	 * To avoid any instantiation
	 */
	DefaultBar() {
		super(DEFAULT_BORDER_WIDTH);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBar#getBorderSkipped()
	 */
	@Override
	public BorderSkipped getBorderSkipped() {
		return BorderSkipped.START;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBar#getBorderRadius()
	 */
	@Override
	public int getBorderRadius() {
		return DEFAULT_BORDER_RADIUS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultPointStyler#getPointStyle()
	 */
	@Override
	public PointStyle getPointStyle() {
		return null;
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
	 * @see org.pepstock.charba.client.defaults.IsDefaultPointStyler#getPointStyleAsImage()
	 */
	@Override
	public Img getPointStyleAsImage() {
		return Undefined.IMAGE_ELEMENT;
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
	 * @see org.pepstock.charba.client.defaults.IsDefaultBar#getHoverBorderRadius()
	 */
	@Override
	public int getHoverBorderRadius() {
		return getBorderRadius();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBar#isEnableBorderRadius()
	 */
	@Override
	public boolean isEnableBorderRadius() {
		return DEFAULT_ENABLE_BORDER_RADIUS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBar#isAutoInflateAmount()
	 */
	@Override
	public boolean isAutoInflateAmount() {
		return DEFAULT_AUTO_INFLATE_AMOUNT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBar#getInflateAmount()
	 */
	@Override
	public int getInflateAmount() {
		return Undefined.INTEGER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultBar#getBase()
	 */
	@Override
	public double getBase() {
		return DEFAULT_BASE;
	}

}
