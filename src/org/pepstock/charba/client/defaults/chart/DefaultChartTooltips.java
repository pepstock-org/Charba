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
import org.pepstock.charba.client.defaults.IsDefaultTooltips;
import org.pepstock.charba.client.enums.InteractionMode;
import org.pepstock.charba.client.enums.IsTooltipPosition;
import org.pepstock.charba.client.enums.TextAlign;
import org.pepstock.charba.client.enums.TextDirection;
import org.pepstock.charba.client.options.Tooltips;

/**
 * Defaults for tooltips option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartTooltips implements IsDefaultTooltips {

	private final Tooltips tooltips;

	/**
	 * Creates the object by tooltips option element instance.
	 * 
	 * @param tooltips tooltips option element instance.
	 */
	DefaultChartTooltips(Tooltips tooltips) {
		this.tooltips = tooltips;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return tooltips.isEnabled();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getMode()
	 */
	@Override
	public InteractionMode getMode() {
		return tooltips.getMode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#isIntersect()
	 */
	@Override
	public boolean isIntersect() {
		return tooltips.isIntersect();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getPosition()
	 */
	@Override
	public IsTooltipPosition getPosition() {
		return tooltips.getPosition();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getBackgroundColorAsString()
	 */
	@Override
	public String getBackgroundColorAsString() {
		return tooltips.getBackgroundColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getTitleFont()
	 */
	@Override
	public IsDefaultFont getTitleFont() {
		return tooltips.getTitleFont();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getTitleAlign()
	 */
	@Override
	public TextAlign getTitleAlign() {
		return tooltips.getTitleAlign();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getTitleSpacing()
	 */
	@Override
	public int getTitleSpacing() {
		return tooltips.getTitleSpacing();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getTitleMarginBottom()
	 */
	@Override
	public int getTitleMarginBottom() {
		return tooltips.getTitleMarginBottom();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getBodyFont()
	 */
	@Override
	public IsDefaultFont getBodyFont() {
		return tooltips.getBodyFont();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getBodyAlign()
	 */
	@Override
	public TextAlign getBodyAlign() {
		return tooltips.getBodyAlign();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getBodySpacing()
	 */
	@Override
	public int getBodySpacing() {
		return tooltips.getBodySpacing();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getFooterFont()
	 */
	@Override
	public IsDefaultFont getFooterFont() {
		return tooltips.getFooterFont();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getFooterAlign()
	 */
	@Override
	public TextAlign getFooterAlign() {
		return tooltips.getFooterAlign();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getFooterSpacing()
	 */
	@Override
	public int getFooterSpacing() {
		return tooltips.getFooterSpacing();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getFooterMarginTop()
	 */
	@Override
	public int getFooterMarginTop() {
		return tooltips.getFooterMarginTop();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getXPadding()
	 */
	@Override
	public int getXPadding() {
		return tooltips.getXPadding();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getYPadding()
	 */
	@Override
	public int getYPadding() {
		return tooltips.getYPadding();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getCaretPadding()
	 */
	@Override
	public int getCaretPadding() {
		return tooltips.getCaretPadding();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getCaretSize()
	 */
	@Override
	public int getCaretSize() {
		return tooltips.getCaretSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getCornerRadius()
	 */
	@Override
	public int getCornerRadius() {
		return tooltips.getCornerRadius();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getMultiKeyBackgroundAsString()
	 */
	@Override
	public String getMultiKeyBackgroundAsString() {
		return tooltips.getMultiKeyBackgroundAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#isDisplayColors()
	 */
	@Override
	public boolean isDisplayColors() {
		return tooltips.isDisplayColors();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getBorderColorAsString()
	 */
	@Override
	public String getBorderColorAsString() {
		return tooltips.getBorderColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getBorderWidth()
	 */
	@Override
	public int getBorderWidth() {
		return tooltips.getBorderWidth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#isRtl()
	 */
	@Override
	public boolean isRtl() {
		return tooltips.isRtl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getTextDirection()
	 */
	@Override
	public TextDirection getTextDirection() {
		return tooltips.getTextDirection();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getBoxWidth()
	 */
	@Override
	public int getBoxWidth() {
		return tooltips.getBoxWidth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getBoxHeight()
	 */
	@Override
	public int getBoxHeight() {
		return tooltips.getBoxHeight();
	}

}
