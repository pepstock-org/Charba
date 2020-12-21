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
import org.pepstock.charba.client.defaults.IsDefaultMajor;
import org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions;
import org.pepstock.charba.client.defaults.IsDefaultTicks;
import org.pepstock.charba.client.enums.CrossAlign;
import org.pepstock.charba.client.enums.ElementAlign;
import org.pepstock.charba.client.enums.TickSource;
import org.pepstock.charba.client.options.Ticks;

/**
 * Defaults for ticks option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartTicks implements IsDefaultTicks {

	private final Ticks ticks;

	/**
	 * Creates the object by ticks option element instance.
	 * 
	 * @param ticks ticks option element instance.
	 */
	DefaultChartTicks(Ticks ticks) {
		this.ticks = ticks;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getFont()
	 */
	@Override
	public IsDefaultFont getFont() {
		return ticks.getFont();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getMajor()
	 */
	@Override
	public IsDefaultMajor getMajor() {
		return ticks.getMajor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getNumberFormat()
	 */
	@Override
	public IsDefaultNumberFormatOptions getNumberFormat() {
		return ticks.getNumberFormat();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getColorAsString()
	 */
	@Override
	public String getColorAsString() {
		return ticks.getColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return ticks.isDisplay();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#isAutoSkip()
	 */
	@Override
	public boolean isAutoSkip() {
		return ticks.isAutoSkip();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getAutoSkipPadding()
	 */
	@Override
	public int getAutoSkipPadding() {
		return ticks.getAutoSkipPadding();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getLabelOffset()
	 */
	@Override
	public int getLabelOffset() {
		return ticks.getLabelOffset();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getMaxRotation()
	 */
	@Override
	public int getMaxRotation() {
		return ticks.getMaxRotation();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getMinRotation()
	 */
	@Override
	public int getMinRotation() {
		return ticks.getMinRotation();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#isMirror()
	 */
	@Override
	public boolean isMirror() {
		return ticks.isMirror();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getPadding()
	 */
	@Override
	public int getPadding() {
		return ticks.getPadding();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getMaxTicksLimit()
	 */
	@Override
	public int getMaxTicksLimit() {
		return ticks.getMaxTicksLimit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getStepSize()
	 */
	@Override
	public double getStepSize() {
		return ticks.getStepSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getBackdropColorAsString()
	 */
	@Override
	public String getBackdropColorAsString() {
		return ticks.getBackdropColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getBackdropPaddingX()
	 */
	@Override
	public int getBackdropPaddingX() {
		return ticks.getBackdropPaddingX();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getBackdropPaddingY()
	 */
	@Override
	public int getBackdropPaddingY() {
		return ticks.getBackdropPaddingY();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#isShowLabelBackdrop()
	 */
	@Override
	public boolean isShowLabelBackdrop() {
		return ticks.isShowLabelBackdrop();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getSource()
	 */
	@Override
	public TickSource getSource() {
		return ticks.getSource();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getPrecision()
	 */
	@Override
	public int getPrecision() {
		return ticks.getPrecision();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getZ()
	 */
	@Override
	public int getZ() {
		return ticks.getZ();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getSampleSize()
	 */
	@Override
	public int getSampleSize() {
		return ticks.getSampleSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getAlign()
	 */
	@Override
	public ElementAlign getAlign() {
		return ticks.getAlign();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getCrossAlign()
	 */
	@Override
	public CrossAlign getCrossAlign() {
		return ticks.getCrossAlign();
	}

}
