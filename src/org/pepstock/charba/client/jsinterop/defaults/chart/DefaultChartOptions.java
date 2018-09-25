package org.pepstock.charba.client.jsinterop.defaults.chart;

import org.pepstock.charba.client.jsinterop.ChartOptions;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultAnimation;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultArc;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultHover;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultLegend;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultLine;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultPadding;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultPoint;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultRectangle;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultTitle;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips;

public final class DefaultChartOptions implements IsDefaultOptions{
	
	private final ChartOptions chartOptions;

	/**
	 * @param chartOptions
	 */
	public DefaultChartOptions(ChartOptions chartOptions) {
		this.chartOptions = chartOptions;
	}
	

	/**
	 * @return the chartOptions
	 */
	final ChartOptions getChartOptions() {
		return chartOptions;
	}

	// FIXME
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getScale()
	 */
	@Override
	public IsDefaultScale getScale() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getAnimation()
	 */
	@Override
	public IsDefaultAnimation getAnimation() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getHover()
	 */
	@Override
	public IsDefaultHover getHover() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getTitle()
	 */
	@Override
	public IsDefaultTitle getTitle() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getLegend()
	 */
	@Override
	public IsDefaultLegend getLegend() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getTooltips()
	 */
	@Override
	public IsDefaultTooltips getTooltips() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getPadding()
	 */
	@Override
	public IsDefaultPadding getPadding() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getArc()
	 */
	@Override
	public IsDefaultArc getArc() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getLine()
	 */
	@Override
	public IsDefaultLine getLine() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getPoint()
	 */
	@Override
	public IsDefaultPoint getPoint() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getRectangle()
	 */
	@Override
	public IsDefaultRectangle getRectangle() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#isResponsive()
	 */
	@Override
	public boolean isResponsive() {
		return chartOptions.isResponsive();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getResponsiveAnimationDuration()
	 */
	@Override
	public int getResponsiveAnimationDuration() {
		return chartOptions.getResponsiveAnimationDuration();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#isMaintainAspectRatio()
	 */
	@Override
	public boolean isMaintainAspectRatio() {
		return chartOptions.isMaintainAspectRatio();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getDevicePixelRatio()
	 */
	@Override
	public double getDevicePixelRatio() {
		return chartOptions.getDevicePixelRatio();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getDefaultColor()
	 */
	@Override
	public String getDefaultColor() {
		return chartOptions.getDefaultColorAsString();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getDefaultFontColor()
	 */
	@Override
	public String getDefaultFontColor() {
		return chartOptions.getDefaultFontColorAsString();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getDefaultFontSize()
	 */
	@Override
	public int getDefaultFontSize() {
		return chartOptions.getDefaultFontSize();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getDefaultFontStyle()
	 */
	@Override
	public String getDefaultFontStyle() {
		return chartOptions.getDefaultFontStyle().name();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getDefaultFontFamily()
	 */
	@Override
	public String getDefaultFontFamily() {
		return chartOptions.getDefaultFontFamily();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#isShowLines()
	 */
	@Override
	public boolean isShowLines() {
		return chartOptions.isShowLines();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#isSpanGaps()
	 */
	@Override
	public boolean isSpanGaps() {
		return chartOptions.isSpanGaps();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getCutoutPercentage()
	 */
	@Override
	public double getCutoutPercentage() {
		return chartOptions.getCutoutPercentage();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getRotation()
	 */
	@Override
	public double getRotation() {
		return chartOptions.getRotation();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getCircumference()
	 */
	@Override
	public double getCircumference() {
		return chartOptions.getCircumference();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getStartAngle()
	 */
	@Override
	public double getStartAngle() {
		return chartOptions.getStartAngle();
	}

}
