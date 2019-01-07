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
package org.pepstock.charba.client.jsinterop.defaults.chart;

import org.pepstock.charba.client.ScaleType;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.jsinterop.ChartOptions;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultAnimation;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultElements;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultHover;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultLayout;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultLegend;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultScales;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultTitle;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips;
import org.pepstock.charba.client.jsinterop.defaults.globals.DefaultsBuilder;

/**
 * Defaults for options element, based on chart type. THIS IS THE ROOT OF ALL ELEMENTS DEFAULTS.
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 */
public final class DefaultChartOptions implements IsDefaultScaledOptions {

	private final ChartOptions chartOptions;

	private final IsDefaultAnimation animation;

	private final IsDefaultScale scale;
	
	private final IsDefaultScales scales;

	private final IsDefaultHover hover;

	private final IsDefaultTitle title;

	private final IsDefaultLegend legend;

	private final IsDefaultTooltips tooltips;

	private final IsDefaultLayout layout;

	private final IsDefaultElements elements;

	/**
	 * Creates the object by options element instance.
	 * 
	 * @param chartOptions chart options instance.
	 */
	public DefaultChartOptions(ChartOptions chartOptions) {
		this.chartOptions = chartOptions;
		// creates sub elements
		animation = new DefaultChartAnimation(chartOptions.getAnimation());
		hover = new DefaultChartHover(chartOptions.getHover());
		title = new DefaultChartTitle(chartOptions.getTitle());
		legend = new DefaultChartLegend(chartOptions.getLegend());
		tooltips = new DefaultChartTooltips(chartOptions.getTooltips());
		layout = new DefaultChartLayout(chartOptions.getLayout());
		elements = new DefaultChartElements(chartOptions.getElements());
		// checks if the chart options is related to axes
		// checks if single scale
		if (ScaleType.single.equals(chartOptions.getType().scaleType())){
			// gets scale
			scale = new DefaultChartScale(chartOptions.getScale());
		} else {
			// uses the default scale
			scale = DefaultsBuilder.get().getScaledOptions().getScale();
		}
		// checks if multi scale
		if (ScaleType.multi.equals(chartOptions.getType().scaleType())){
			// gets scale
			scales = new DefaultChartScales(chartOptions.getScales());
		} else {
			// uses the default scales
			scales = DefaultsBuilder.get().getScaledOptions().getScales();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getLayout()
	 */
	@Override
	public IsDefaultLayout getLayout() {
		return layout;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getElements()
	 */
	@Override
	public IsDefaultElements getElements() {
		return elements;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getScale()
	 */
	@Override
	public IsDefaultScale getScale() {
		return scale;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScaledOptions#getScales()
	 */
	@Override
	public IsDefaultScales getScales() {
		return scales;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getAnimation()
	 */
	@Override
	public IsDefaultAnimation getAnimation() {
		return animation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getHover()
	 */
	@Override
	public IsDefaultHover getHover() {
		return hover;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getTitle()
	 */
	@Override
	public IsDefaultTitle getTitle() {
		return title;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getLegend()
	 */
	@Override
	public IsDefaultLegend getLegend() {
		return legend;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getTooltips()
	 */
	@Override
	public IsDefaultTooltips getTooltips() {
		return tooltips;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#isResponsive()
	 */
	@Override
	public boolean isResponsive() {
		return chartOptions.isResponsive();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getResponsiveAnimationDuration()
	 */
	@Override
	public int getResponsiveAnimationDuration() {
		return chartOptions.getResponsiveAnimationDuration();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#isMaintainAspectRatio()
	 */
	@Override
	public boolean isMaintainAspectRatio() {
		return chartOptions.isMaintainAspectRatio();
	}
	

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getAspectRatio()
	 */
	@Override
	public double getAspectRatio() {
		return chartOptions.getAspectRatio();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getDevicePixelRatio()
	 */
	@Override
	public double getDevicePixelRatio() {
		return chartOptions.getDevicePixelRatio();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getDefaultColorAsString()
	 */
	@Override
	public String getDefaultColorAsString() {
		return chartOptions.getDefaultColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getDefaultFontColorAsString()
	 */
	@Override
	public String getDefaultFontColorAsString() {
		return chartOptions.getDefaultFontColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getDefaultFontSize()
	 */
	@Override
	public int getDefaultFontSize() {
		return chartOptions.getDefaultFontSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getDefaultFontStyle()
	 */
	@Override
	public FontStyle getDefaultFontStyle() {
		return chartOptions.getDefaultFontStyle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getDefaultFontFamily()
	 */
	@Override
	public String getDefaultFontFamily() {
		return chartOptions.getDefaultFontFamily();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#isShowLines()
	 */
	@Override
	public boolean isShowLines() {
		return chartOptions.isShowLines();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#isSpanGaps()
	 */
	@Override
	public boolean isSpanGaps() {
		return chartOptions.isSpanGaps();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getCutoutPercentage()
	 */
	@Override
	public double getCutoutPercentage() {
		return chartOptions.getCutoutPercentage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getRotation()
	 */
	@Override
	public double getRotation() {
		return chartOptions.getRotation();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getCircumference()
	 */
	@Override
	public double getCircumference() {
		return chartOptions.getCircumference();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getStartAngle()
	 */
	@Override
	public double getStartAngle() {
		return chartOptions.getStartAngle();
	}

}
