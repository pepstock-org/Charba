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
import org.pepstock.charba.client.defaults.IsDefaultMajor;
import org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions;
import org.pepstock.charba.client.defaults.IsDefaultTicks;
import org.pepstock.charba.client.enums.CrossAlign;
import org.pepstock.charba.client.enums.ElementAlign;
import org.pepstock.charba.client.enums.TickSource;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * CHART.JS default values for TICKS element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class DefaultTicks implements IsDefaultTicks {

	private static final boolean DEFAULT_DISPLAY = true;

	private static final boolean DEFAULT_AUTO_SKIP = true;

	private static final int DEFAULT_AUTO_SKIP_PADDING = 0;

	private static final int DEFAULT_LABEL_OFFSET = 0;

	private static final int DEFAULT_MAX_ROTATION = 50;

	private static final int DEFAULT_MIN_ROTATION = 0;

	private static final boolean DEFAULT_MIRROR = false;

	private static final int DEFAULT_PADDING = 0;

	private static final int DEFAULT_MAX_TICKS_LIMIT = 11;

	private static final double DEFAULT_STEP_SIZE = Double.MIN_VALUE;

	private static final String DEFAULT_BACKDROP_COLOR = "rgba(255,255,255,0.75)";

	private static final int DEFAULT_BACKDROP_PADDING_X = 2;

	private static final int DEFAULT_BACKDROP_PADDING_Y = 2;

	private static final boolean DEFAULT_SHOW_LABEL_BACKDROP = true;

	private static final int DEFAULT_PRECISION = 0;

	private static final int DEFAULT_Z = 0;

	private final DefaultMajor major = new DefaultMajor();

	private final DefaultFont font = new DefaultFont();

	/**
	 * To avoid any instantiation
	 */
	DefaultTicks() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getFont()
	 */
	@Override
	public IsDefaultFont getFont() {
		return font;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getMajor()
	 */
	@Override
	public IsDefaultMajor getMajor() {
		return major;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getNumberFormat()
	 */
	@Override
	public IsDefaultNumberFormatOptions getNumberFormat() {
		return DefaultNumberFormatOptions.INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return DEFAULT_DISPLAY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#isAutoSkip()
	 */
	@Override
	public boolean isAutoSkip() {
		return DEFAULT_AUTO_SKIP;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getAutoSkipPadding()
	 */
	@Override
	public int getAutoSkipPadding() {
		return DEFAULT_AUTO_SKIP_PADDING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getLabelOffset()
	 */
	@Override
	public int getLabelOffset() {
		return DEFAULT_LABEL_OFFSET;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getMaxRotation()
	 */
	@Override
	public int getMaxRotation() {
		return DEFAULT_MAX_ROTATION;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getMinRotation()
	 */
	@Override
	public int getMinRotation() {
		return DEFAULT_MIN_ROTATION;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#isMirror()
	 */
	@Override
	public boolean isMirror() {
		return DEFAULT_MIRROR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getPadding()
	 */
	@Override
	public int getPadding() {
		return DEFAULT_PADDING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getMaxTicksLimit()
	 */
	@Override
	public int getMaxTicksLimit() {
		return DEFAULT_MAX_TICKS_LIMIT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getStepSize()
	 */
	@Override
	public double getStepSize() {
		return DEFAULT_STEP_SIZE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getBackdropColorAsString()
	 */
	@Override
	public String getBackdropColorAsString() {
		return DEFAULT_BACKDROP_COLOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getBackdropPaddingX()
	 */
	@Override
	public int getBackdropPaddingX() {
		return DEFAULT_BACKDROP_PADDING_X;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getBackdropPaddingY()
	 */
	@Override
	public int getBackdropPaddingY() {
		return DEFAULT_BACKDROP_PADDING_Y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#isShowLabelBackdrop()
	 */
	@Override
	public boolean isShowLabelBackdrop() {
		return DEFAULT_SHOW_LABEL_BACKDROP;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getSource()
	 */
	@Override
	public TickSource getSource() {
		return TickSource.AUTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getPrecision()
	 */
	@Override
	public int getPrecision() {
		return DEFAULT_PRECISION;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getZ()
	 */
	@Override
	public int getZ() {
		return DEFAULT_Z;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getSampleSize()
	 */
	@Override
	public int getSampleSize() {
		return UndefinedValues.INTEGER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getAlign()
	 */
	@Override
	public ElementAlign getAlign() {
		return ElementAlign.CENTER;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.defaults.IsDefaultTicks#getCrossAlign()
	 */
	@Override
	public CrossAlign getCrossAlign() {
		return CrossAlign.NEAR;
	}

}
