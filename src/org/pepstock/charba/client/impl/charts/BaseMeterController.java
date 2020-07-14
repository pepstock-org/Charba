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
package org.pepstock.charba.client.impl.charts;

import java.util.List;

import org.pepstock.charba.client.ChartNode;
import org.pepstock.charba.client.Controller;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.controllers.AbstractController;
import org.pepstock.charba.client.controllers.ControllerContext;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.dom.elements.Context2dItem;
import org.pepstock.charba.client.dom.elements.TextMetricsItem;
import org.pepstock.charba.client.dom.enums.TextAlign;
import org.pepstock.charba.client.dom.enums.TextBaseline;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Weight;
import org.pepstock.charba.client.items.ChartAreaNode;
import org.pepstock.charba.client.items.DatasetMetaItem;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Controller implementation to create charts like meter of gauges, extending doughnut chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class BaseMeterController extends AbstractController {

	// default font increment
	private static final int FONT_SIZE_INCREMENT = 4;
	// minimum font size
	private static final int MINIMUM_FONT_SIZE = 12;
	// default padding
	private static final double PADDING = 4D;
	// max percentage
	private static final double MAX_PERCENTAGE = 100D;
	// SQRT of 2 to calculate the sqare inside the doughnut
	private static final double SQRT_2 = Math.sqrt(2);
	// controller type
	private final ControllerType type;

	/**
	 * Creates the controller using the type passed as argument.
	 * 
	 * @param type controller type, could be meter or gauge.
	 */
	BaseMeterController(ControllerType type) {
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Controller#getType()
	 */
	@Override
	public ControllerType getType() {
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.controllers.AbstractController#initialize(org.pepstock.charba.client.controllers.ControllerContext, org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void initialize(ControllerContext context, IsChart chart) {
		// checks if arguments are consistent
		if (Controller.isConsistent(this, context, chart)) {
			// gets the dataset at index
			Dataset dataset = chart.getData().getDatasets().get(context.getIndex());
			// checks if is a meter dataset (or gauge)
			if (dataset instanceof MeterDataset) {
				// casts to meter dataset
				MeterDataset meterDataset = (MeterDataset) dataset;
				// meter or gauge charts must have only 1 dataset
				// checks if there is more than 1
				if (context.getIndex() > 0) {
					// if more than 1
					// forces hidden dataset
					meterDataset.hide();
				}
				// invokes the initialization
				super.initialize(context, chart);
			} else {
				// if not meter dataset
				// exception
				throw new IllegalArgumentException("Dataset at index " + context.getIndex() + " is not a MeterDataset");
			}
		} else {
			// if here, arguments are not consistent
			// exception
			throw new IllegalArgumentException("Initialize method arguments are not consistent");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.controllers.AbstractController#draw(org.pepstock.charba.client.controllers.ControllerContext, org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void draw(ControllerContext context, IsChart chart) {
		// checks if arguments are consistent
		if (Controller.isConsistent(this, context, chart)) {
			// draw the doughnut chart
			super.draw(context, chart);
			// gets the list of datasets
			List<Dataset> datasets = chart.getData().getDatasets();
			// checks if not empty
			if (!datasets.isEmpty()) {
				// gets chart item
				ChartNode item = context.getNode();
				// gets dataset index 0
				MeterDataset dataset = (MeterDataset) datasets.get(0);
				// checks if meter chart
				if (chart instanceof MeterChart) {
					MeterChart meterChart = (MeterChart) chart;
					MeterOptions options = meterChart.getOptions();
					// let's draw the value inside the doughnut
					// FIXME ease value
					execute(chart, item, dataset, options, 1D);
				} else if (chart instanceof GaugeChart) {
					// checks if meter chart
					GaugeChart gaugeChart = (GaugeChart) chart;
					GaugeOptions options = gaugeChart.getOptions();
					// let's draw the value inside the doughnut
					// FIXME ease value
					execute(chart, item, dataset, options, 1D);
				}
			}
		} else {
			// if here, arguments are not consistent
			// exception
			throw new IllegalArgumentException("Draw method arguments are not consistent");
		}
	}

	/**
	 * Draws the value inside the inner radius of doughnut.
	 * 
	 * @param chart chart instance
	 * @param item chart item with CHART.JS properties needed to calculate the area where to drawn the value
	 * @param dataset the dataset instance
	 * @param options the chart options
	 * @param ease easing of drawing (between 0 and 1) for animation
	 */
	private void execute(IsChart chart, ChartNode item, MeterDataset dataset, MeterOptions options, double ease) {
		// gets dataset item at index 0
		// it can not be null
		DatasetMetaItem datasetMetaItem = chart.getDatasetMeta(0);
		// calculate the side of the square where to draw the value
		final int sideOfSquare = (int) ((datasetMetaItem.getController().getInnerRadius() * 2) / SQRT_2);
		// gets canvas context 2d
		Context2dItem ctx = chart.getCanvas().getContext2d();
		// gets the chart area of CHART.JS
		ChartAreaNode area = item.getChartArea();
		// calculate the center point of the square
		final double centerX = (area.getRight() - area.getLeft()) / 2D + area.getLeft();
		final double centerY = (area.getBottom() - area.getTop()) / 2D + area.getTop();
		// gets max value
		final double maxValue = MeterDisplay.PERCENTAGE.equals(options.getDisplay()) || MeterDisplay.PERCENTAGE_AND_LABEL.equals(options.getDisplay()) ? MAX_PERCENTAGE : dataset.getMax();
		// gets value
		final double valueToCalculate = MeterDisplay.PERCENTAGE.equals(options.getDisplay()) || MeterDisplay.PERCENTAGE_AND_LABEL.equals(options.getDisplay()) ? dataset.getValue() / dataset.getMax() : dataset.getValue();
		// here is calculating the value to showed
		// based on easing of drawing
		final double value = options.isAnimatedDisplay() ? valueToCalculate * ease : valueToCalculate;
		// gets max value into string to check font size
		final String maxValueToShow = getFormattedValue(chart, options, maxValue, 1D);
		// value to show with format required
		final String valueToShow = getFormattedValue(chart, options, value, ease);
		// gets font style
		final FontStyle style = options.getFontStyle() == null ? FontStyle.NORMAL : options.getFontStyle();
		// gets font family
		final String fontFamily = options.getFontFamily() == null ? Defaults.get().getGlobal().getFont().getFamily() : options.getFontFamily();
		// gets font color
		final String fontColor = options.getDisplayFontColor() == null ? MeterOptions.DEFAULT_DISPLAY_COLOR.toRGBA() : options.getDisplayFontColor().toRGBA();
		// gets the label
		final String label = dataset.getLabel();
		// saves context
		ctx.save();
		// calculates the font size
		int fontSize = calculateFontSize(ctx, sideOfSquare, maxValueToShow, style, fontFamily);
		// sets color to canvas
		ctx.setFillColor(fontColor);
		// sets alignment
		ctx.setTextAlign(TextAlign.CENTER);
		// checks if it must draw also the label
		if ((MeterDisplay.VALUE_AND_LABEL.equals(options.getDisplay()) || MeterDisplay.PERCENTAGE_AND_LABEL.equals(options.getDisplay())) && label != null) {
			// sets font
			ctx.setFont(Utilities.toCSSFontProperty(style, Weight.NORMAL, fontSize, fontFamily));
			// sets alignment from center point
			ctx.setTextBaseline(TextBaseline.BOTTOM);
			// draws text
			ctx.fillText(valueToShow, centerX, centerY - PADDING);
			// re-calculates the font size for label
			fontSize = calculateFontSize(ctx, sideOfSquare, label, style, fontFamily);
			ctx.setFont(Utilities.toCSSFontProperty(style, Weight.NORMAL, fontSize, fontFamily));
			// sets alignment from center point
			ctx.setTextBaseline(TextBaseline.TOP);
			// draws text
			ctx.fillText(dataset.getLabel(), centerX, centerY + PADDING);
		} else {
			// if here it must draw ONLY the value
			// sets font
			ctx.setFont(Utilities.toCSSFontProperty(style, Weight.NORMAL, fontSize, fontFamily));
			// sets alignment from center point
			ctx.setTextBaseline(TextBaseline.MIDDLE);
			// draws text
			ctx.fillText(valueToShow, centerX, centerY);
		}
		// restores context
		ctx.restore();
	}

	/**
	 * Calculates the font size based on available space into square into doughnut inner radius.
	 * 
	 * @param ctx canvas context
	 * @param sideOfSquare side of square
	 * @param value value to display
	 * @param style font style
	 * @param fontFamily font family
	 * @return the font size to use
	 */
	private int calculateFontSize(Context2dItem ctx, int sideOfSquare, String value, FontStyle style, String fontFamily) {
		// half of side of square
		int fontSize = sideOfSquare / 2;
		boolean check = true;
		// loop to calculate the size
		while (check) {
			// sets font
			ctx.setFont(Utilities.toCSSFontProperty(style, Weight.NORMAL, fontSize, fontFamily));
			// gets metrics
			TextMetricsItem metrics = ctx.measureText(value);
			// if the width is inside of side (and padding) or
			// is the minimum size of font
			// exit
			if ((metrics.getWidth() + PADDING * 2D) < sideOfSquare || fontSize <= MINIMUM_FONT_SIZE) {
				check = false;
			} else {
				// decrements the font size
				fontSize = fontSize - FONT_SIZE_INCREMENT;
			}
		}
		// returns font size
		return fontSize;
	}

	/**
	 * Returns a formatted value of the chart applying the precision or invoking the value callback.
	 * 
	 * @param chart chart instance
	 * @param options chart options instance
	 * @param value value of the chart
	 * @param easing the current animation status
	 * @return a formatted value of the chart
	 */
	private String getFormattedValue(IsChart chart, MeterOptions options, double value, double easing) {
		// checks if options has got a callback
		if (options.getValueCallback() != null) {
			// invokes callback
			String result = options.getValueCallback().onFormat(chart, value, easing);
			// checks if result is consistent
			if (result != null) {
				// return this value
				return result;
			}
		}
		// if here, it sues the precision set into options
		return Utilities.applyPrecision(value, options.getPrecision());
	}

}
