package org.pepstock.charba.client.jsinterop;

import org.pepstock.charba.client.jsinterop.commons.Checker;
import org.pepstock.charba.client.jsinterop.defaults.DefaultOptions;
import org.pepstock.charba.client.jsinterop.items.ChartAreaItem;
import org.pepstock.charba.client.jsinterop.items.LegendNode;
import org.pepstock.charba.client.jsinterop.items.OptionsNode;
import org.pepstock.charba.client.jsinterop.items.ScalesNode;
import org.pepstock.charba.client.jsinterop.items.TitleNode;
import org.pepstock.charba.client.jsinterop.items.TooltipNode;
import org.pepstock.charba.client.jsinterop.items.UndefinedValues;

public class ChartNode {
	
	private final Chart chart;
	
	private final OptionsNode options;
	
	/**
	 * @param chart
	 */
	protected ChartNode(Chart chart) {
		this.chart = chart;
		if (chart != null) {
			options = new OptionsNode(DefaultOptions.get(), chart.getOptions());
		} else {
			options = null;
		}
	}
	
	/**
	 * Returns the options item.
	 * 
	 * @return the options item.
	 */
	public OptionsNode getOptions() {
		return options;
	}

	/**
	 * Returns the legend item.
	 * 
	 * @return the legend item.
	 */
	public LegendNode getLegend() {
		return chart.getLegend();
	}

	/**
	 * Returns the scales item.
	 * 
	 * @return the scales item.
	 */
	public ScalesNode getScales() {
		return chart.getScales();
	}

	/**
	 * Returns the chart area item.
	 * 
	 * @return the chart area item.
	 */
	public ChartAreaItem getChartArea() {
		return chart.getChartArea();
	}

	/**
	 * Returns the title item.
	 * 
	 * @return the title item.
	 */
	public TitleNode getTitle() {
		return chart.getTitleBlock();
	}

	/**
	 * Returns the tooltip item.
	 * 
	 * @return the tooltip item.
	 */
	public TooltipNode getTooltip() {
		return chart.getTooltip();
	}

	/**
	 * Returns the CHART JS chart ID.
	 * 
	 * @return the CHART JS chart ID. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getId() {
		return Checker.check(chart.getId(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the width in pixel.
	 * 
	 * @return the width in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getWidth() {
		return Checker.check(chart.getWidth(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the height in pixel.
	 * 
	 * @return the height in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getHeight() {
		return Checker.check(chart.getHeight(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the aspect ratio.
	 * 
	 * @return the aspect ratio. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getAspectRatio() {
		return Checker.check(chart.getAspectRatio(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the current device pixel ratio.
	 * 
	 * @return the current device pixel ratio. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getCurrentDevicePixelRatio() {
		return Checker.check(chart.getCurrentDevicePixelRatio(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns if the chart is animating or not.
	 * 
	 * @return if the chart is animating or not. Default is {@link org.pepstock.charba.client.items.UndefinedValues#BOOLEAN}.
	 */
	public boolean isAnimating() {
		return Checker.check(chart.isAnimating(), UndefinedValues.BOOLEAN);
	}

	/**
	 * Returns the border width value.
	 * 
	 * @return the border width value. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getBorderWidth() {
		return Checker.check(chart.getBorderWidth(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the outer radius value.
	 * 
	 * @return the outer radius value. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getOuterRadius() {
		return Checker.check(chart.getOuterRadius(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the inner radius value.
	 * 
	 * @return the inner radius value. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getInnerRadius() {
		return Checker.check(chart.getInnerRadius(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the radius length value.
	 * 
	 * @return the radius length value. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getRadiusLength() {
		return Checker.check(chart.getRadiusLength(), UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the offset X value.
	 * 
	 * @return the offset X value. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getOffsetX() {
		return Checker.check(chart.getOffsetX(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the offset Y value.
	 * 
	 * @return the offset Y value. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getOffsetY() {
		return Checker.check(chart.getOffsetY(), UndefinedValues.INTEGER);
	}

}
