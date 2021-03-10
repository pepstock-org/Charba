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

import java.util.List;

import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.defaults.globals.AbstractDefaultOptions;
import org.pepstock.charba.client.enums.Event;
import org.pepstock.charba.client.enums.IndexAxis;
import org.pepstock.charba.client.intl.CLocale;

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
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getEvents()
	 */
	@Override
	public List<Event> getEvents() {
		return options.getEvents();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getLocale()
	 */
	@Override
	public CLocale getLocale() {
		return options.getLocale();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getScope()
	 */
	@Override
	public String getScope() {
		return options.getScope();
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
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getResizeDelay()
	 */
	@Override
	public double getResizeDelay() {
		return options.getResizeDelay();
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
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getColorAsString()
	 */
	@Override
	public final String getColorAsString() {
		return options.getColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getBackgroundColorAsString()
	 */
	@Override
	public String getBackgroundColorAsString() {
		return options.getBackgroundColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getBorderColorAsString()
	 */
	@Override
	public String getBorderColorAsString() {
		return options.getBorderColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#isShowLine()
	 */
	@Override
	public final boolean isShowLine() {
		return options.isShowLine();
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
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getCutout()
	 */
	@Override
	public double getCutout() {
		return options.getCutout();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getCutoutPercentage()
	 */
	@Override
	public String getCutoutPercentage() {
		return options.getCutoutPercentage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getRadius()
	 */
	@Override
	public double getRadius() {
		return options.getRadius();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getRadiusPercentage()
	 */
	@Override
	public String getRadiusPercentage() {
		return options.getRadiusPercentage();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#isSkipNull()
	 */
	@Override
	public final boolean isSkipNull() {
		return options.isSkipNull();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptions#getIndexAxis()
	 */
	@Override
	public final IndexAxis getIndexAxis() {
		return options.getIndexAxis();
	}

}
