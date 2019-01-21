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

import org.pepstock.charba.client.defaults.IsDefaultFontItem;
import org.pepstock.charba.client.defaults.IsDefaultTicks;
import org.pepstock.charba.client.enums.TickSource;

/**
 * CHART.JS default values for TICKS element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class DefaultTicks extends AbstractDefaultFontItem implements IsDefaultTicks {

	private static final boolean DEFAULT_DISPLAY = true;

	private static final boolean DEFAULT_BEGIN_AT_ZERO = false;

	private static final boolean DEFAULT_REVERSE = false;

	private static final boolean DEFAULT_AUTO_SKIP = true;

	private static final int DEFAULT_AUTO_SKIP_PADDING = 0;

	private static final int DEFAULT_LABEL_OFFSET = 0;

	private static final int DEFAULT_MAX_ROTATION = 50;

	private static final int DEFAULT_MIN_ROTATION = 0;

	private static final boolean DEFAULT_MIRROR = false;

	private static final int DEFAULT_PADDING = 0;

	private static final double DEFAULT_MIN = Double.MIN_VALUE;

	private static final double DEFAULT_MAX = Double.MAX_VALUE;

	private static final int DEFAULT_MAX_TICKS_LIMIT = 11;

	private static final double DEFAULT_STEP_SIZE = Double.MIN_VALUE;

	private static final double DEFAULT_SUGGESTED_MAX = Double.MAX_VALUE;

	private static final double DEFAULT_SUGGESTED_MIN = Double.MIN_VALUE;

	private static final String DEFAULT_BACKDROP_COLOR = "rgba(255,255,255,0.75)";

	private static final int DEFAULT_BACKDROP_PADDING_X = 2;

	private static final int DEFAULT_BACKDROP_PADDING_Y = 2;

	private static final boolean DEFAULT_SHOW_LABEL_BACKDROP = true;

	private static final int DEFAULT_PRECISION = 0;

	private final DefaultTickItem minor = new DefaultTickItem();

	private final DefaultTickItem major = new DefaultTickItem();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTicks#getMinor()
	 */
	@Override
	public IsDefaultFontItem getMinor() {
		return minor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTicks#getMajor()
	 */
	@Override
	public IsDefaultFontItem getMajor() {
		return major;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTicks#isBeginAtZero()
	 */
	@Override
	public boolean isBeginAtZero() {
		return DEFAULT_BEGIN_AT_ZERO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTicks#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return DEFAULT_DISPLAY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTicks#isReverse()
	 */
	@Override
	public boolean isReverse() {
		return DEFAULT_REVERSE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTicks#isAutoSkip()
	 */
	@Override
	public boolean isAutoSkip() {
		return DEFAULT_AUTO_SKIP;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTicks#getAutoSkipPadding()
	 */
	@Override
	public int getAutoSkipPadding() {
		return DEFAULT_AUTO_SKIP_PADDING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTicks#getLabelOffset()
	 */
	@Override
	public int getLabelOffset() {
		return DEFAULT_LABEL_OFFSET;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTicks#getMaxRotation()
	 */
	@Override
	public int getMaxRotation() {
		return DEFAULT_MAX_ROTATION;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTicks#getMinRotation()
	 */
	@Override
	public int getMinRotation() {
		return DEFAULT_MIN_ROTATION;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTicks#isMirror()
	 */
	@Override
	public boolean isMirror() {
		return DEFAULT_MIRROR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTicks#getPadding()
	 */
	@Override
	public int getPadding() {
		return DEFAULT_PADDING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTicks#getMin()
	 */
	@Override
	public double getMin() {
		return DEFAULT_MIN;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTicks#getMax()
	 */
	@Override
	public double getMax() {
		return DEFAULT_MAX;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTicks#getMaxTicksLimit()
	 */
	@Override
	public int getMaxTicksLimit() {
		return DEFAULT_MAX_TICKS_LIMIT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTicks#getStepSize()
	 */
	@Override
	public double getStepSize() {
		return DEFAULT_STEP_SIZE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTicks#getSuggestedMax()
	 */
	@Override
	public double getSuggestedMax() {
		return DEFAULT_SUGGESTED_MAX;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTicks#getSuggestedMin()
	 */
	@Override
	public double getSuggestedMin() {
		return DEFAULT_SUGGESTED_MIN;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTicks#getBackdropColorAsString()
	 */
	@Override
	public String getBackdropColorAsString() {
		return DEFAULT_BACKDROP_COLOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTicks#getBackdropPaddingX()
	 */
	@Override
	public int getBackdropPaddingX() {
		return DEFAULT_BACKDROP_PADDING_X;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTicks#getBackdropPaddingY()
	 */
	@Override
	public int getBackdropPaddingY() {
		return DEFAULT_BACKDROP_PADDING_Y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTicks#isShowLabelBackdrop()
	 */
	@Override
	public boolean isShowLabelBackdrop() {
		return DEFAULT_SHOW_LABEL_BACKDROP;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTicks#getSource()
	 */
	@Override
	public TickSource getSource() {
		return TickSource.auto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultTicks#getPrecision()
	 */
	@Override
	public int getPrecision() {
		return DEFAULT_PRECISION;
	}

}
