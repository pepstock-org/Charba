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

import java.util.Set;

import org.pepstock.charba.client.defaults.IsDefaultAnimation;
import org.pepstock.charba.client.defaults.IsDefaultAnimations;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.defaults.IsDefaultPadding;
import org.pepstock.charba.client.defaults.IsDefaultTooltips;
import org.pepstock.charba.client.defaults.IsDefaultTransitions;
import org.pepstock.charba.client.enums.Event;
import org.pepstock.charba.client.enums.InteractionMode;
import org.pepstock.charba.client.enums.IsTooltipPosition;
import org.pepstock.charba.client.enums.TextAlign;
import org.pepstock.charba.client.enums.TextDirection;
import org.pepstock.charba.client.enums.TooltipAlign;

/**
 * Defaults for tooltips option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartTooltips implements IsDefaultTooltips {

	private final IsDefaultTooltips tooltip;

	/**
	 * Creates the object by tooltip option element instance.
	 * 
	 * @param tooltip tooltip option element instance.
	 */
	public DefaultChartTooltips(IsDefaultTooltips tooltip) {
		this.tooltip = tooltip;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationContainer#getTransitions()
	 */
	@Override
	public IsDefaultTransitions getTransitions() {
		return tooltip.getTransitions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationTransition#getAnimation()
	 */
	@Override
	public IsDefaultAnimation getAnimation() {
		return tooltip.getAnimation();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimationTransition#getAnimations()
	 */
	@Override
	public IsDefaultAnimations getAnimations() {
		return tooltip.getAnimations();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getPadding()
	 */
	@Override
	public IsDefaultPadding getPadding() {
		return tooltip.getPadding();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return tooltip.isEnabled();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getEvents()
	 */
	@Override
	public Set<Event> getEvents() {
		return tooltip.getEvents();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getMode()
	 */
	@Override
	public InteractionMode getMode() {
		return tooltip.getMode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#isIntersect()
	 */
	@Override
	public boolean isIntersect() {
		return tooltip.isIntersect();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getPosition()
	 */
	@Override
	public IsTooltipPosition getPosition() {
		return tooltip.getPosition();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getBackgroundColorAsString()
	 */
	@Override
	public String getBackgroundColorAsString() {
		return tooltip.getBackgroundColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getTitleFont()
	 */
	@Override
	public IsDefaultFont getTitleFont() {
		return tooltip.getTitleFont();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getTitleColorAsString()
	 */
	@Override
	public String getTitleColorAsString() {
		return tooltip.getTitleColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getTitleAlign()
	 */
	@Override
	public TextAlign getTitleAlign() {
		return tooltip.getTitleAlign();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getTitleSpacing()
	 */
	@Override
	public int getTitleSpacing() {
		return tooltip.getTitleSpacing();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getTitleMarginBottom()
	 */
	@Override
	public int getTitleMarginBottom() {
		return tooltip.getTitleMarginBottom();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getBodyFont()
	 */
	@Override
	public IsDefaultFont getBodyFont() {
		return tooltip.getBodyFont();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getBodyColorAsString()
	 */
	@Override
	public String getBodyColorAsString() {
		return tooltip.getBodyColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getBodyAlign()
	 */
	@Override
	public TextAlign getBodyAlign() {
		return tooltip.getBodyAlign();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getBodySpacing()
	 */
	@Override
	public int getBodySpacing() {
		return tooltip.getBodySpacing();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getFooterFont()
	 */
	@Override
	public IsDefaultFont getFooterFont() {
		return tooltip.getFooterFont();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getFooterColorAsString()
	 */
	@Override
	public String getFooterColorAsString() {
		return tooltip.getFooterColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getFooterAlign()
	 */
	@Override
	public TextAlign getFooterAlign() {
		return tooltip.getFooterAlign();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getFooterSpacing()
	 */
	@Override
	public int getFooterSpacing() {
		return tooltip.getFooterSpacing();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getFooterMarginTop()
	 */
	@Override
	public int getFooterMarginTop() {
		return tooltip.getFooterMarginTop();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getCaretPadding()
	 */
	@Override
	public int getCaretPadding() {
		return tooltip.getCaretPadding();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getCaretSize()
	 */
	@Override
	public int getCaretSize() {
		return tooltip.getCaretSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getCornerRadius()
	 */
	@Override
	public int getCornerRadius() {
		return tooltip.getCornerRadius();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getMultiKeyBackgroundAsString()
	 */
	@Override
	public String getMultiKeyBackgroundAsString() {
		return tooltip.getMultiKeyBackgroundAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#isDisplayColors()
	 */
	@Override
	public boolean isDisplayColors() {
		return tooltip.isDisplayColors();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getBorderColorAsString()
	 */
	@Override
	public String getBorderColorAsString() {
		return tooltip.getBorderColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getBorderWidth()
	 */
	@Override
	public int getBorderWidth() {
		return tooltip.getBorderWidth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#isRtl()
	 */
	@Override
	public boolean isRtl() {
		return tooltip.isRtl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getTextDirection()
	 */
	@Override
	public TextDirection getTextDirection() {
		return tooltip.getTextDirection();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getBoxWidth()
	 */
	@Override
	public int getBoxWidth() {
		return tooltip.getBoxWidth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getBoxHeight()
	 */
	@Override
	public int getBoxHeight() {
		return tooltip.getBoxHeight();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#isUsePointStyle()
	 */
	@Override
	public boolean isUsePointStyle() {
		return tooltip.isUsePointStyle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getXAlign()
	 */
	@Override
	public TooltipAlign getXAlign() {
		return tooltip.getXAlign();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getYAlign()
	 */
	@Override
	public TooltipAlign getYAlign() {
		return tooltip.getYAlign();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTooltips#getBoxPadding()
	 */
	@Override
	public int getBoxPadding() {
		return tooltip.getBoxPadding();
	}

}
