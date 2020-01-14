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

import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.defaults.globals.AbstractDefaultOptions;
import org.pepstock.charba.client.enums.FontStyle;

/**
 * Abstract defaults for options element, based on chart type. THIS IS MUST BE EXTENDED AS ROOT OF ALL ELEMENTS CHART DEFAULTS.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class AbstractDefaultChartOptions extends AbstractDefaultOptions {

	private final IsDefaultOptions options;

	/**
	 * Creates the object by a default options.
	 * 
	 * @param defaultOptions default options instance used as default.
	 */
	AbstractDefaultChartOptions(IsDefaultOptions defaultOptions) {
		super(defaultOptions);
		// stores defaults
		this.options = defaultOptions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#isResponsive()
	 */
	@Override
	public final boolean isResponsive() {
		return options.isResponsive();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getResponsiveAnimationDuration()
	 */
	@Override
	public final int getResponsiveAnimationDuration() {
		return options.getResponsiveAnimationDuration();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#isMaintainAspectRatio()
	 */
	@Override
	public final boolean isMaintainAspectRatio() {
		return options.isMaintainAspectRatio();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getAspectRatio()
	 */
	@Override
	public final double getAspectRatio() {
		return options.getAspectRatio();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getDevicePixelRatio()
	 */
	@Override
	public final double getDevicePixelRatio() {
		return options.getDevicePixelRatio();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getDefaultColorAsString()
	 */
	@Override
	public final String getDefaultColorAsString() {
		return options.getDefaultColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getDefaultFontColorAsString()
	 */
	@Override
	public final String getDefaultFontColorAsString() {
		return options.getDefaultFontColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getDefaultFontSize()
	 */
	@Override
	public final int getDefaultFontSize() {
		return options.getDefaultFontSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getDefaultFontStyle()
	 */
	@Override
	public final FontStyle getDefaultFontStyle() {
		return options.getDefaultFontStyle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getDefaultFontFamily()
	 */
	@Override
	public final String getDefaultFontFamily() {
		return options.getDefaultFontFamily();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getDefaultLineHeight()
	 */
	@Override
	public final double getDefaultLineHeight() {
		return options.getDefaultLineHeight();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#isShowLines()
	 */
	@Override
	public final boolean isShowLines() {
		return options.isShowLines();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#isSpanGaps()
	 */
	@Override
	public final boolean isSpanGaps() {
		return options.isSpanGaps();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getCutoutPercentage()
	 */
	@Override
	public final double getCutoutPercentage() {
		return options.getCutoutPercentage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getRotation()
	 */
	@Override
	public final double getRotation() {
		return options.getRotation();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getCircumference()
	 */
	@Override
	public final double getCircumference() {
		return options.getCircumference();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getStartAngle()
	 */
	@Override
	public final double getStartAngle() {
		return options.getStartAngle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#isDrawOnAttach()
	 */
	@Override
	public final boolean isDrawOnAttach() {
		return options.isDrawOnAttach();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#isDestroyOnDetach()
	 */
	@Override
	public final boolean isDestroyOnDetach() {
		return options.isDestroyOnDetach();
	}

}
