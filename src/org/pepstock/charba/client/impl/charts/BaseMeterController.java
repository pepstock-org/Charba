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
import org.pepstock.charba.client.enums.DefaultAnimationModeKey;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Weight;
import org.pepstock.charba.client.items.ChartAreaNode;
import org.pepstock.charba.client.items.DatasetMetaItem;
import org.pepstock.charba.client.options.IsAnimationModeKey;
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
	// min animation duration to apply the animation labels
	private static final double MINIMUM_ANIMATION_DURATION = 100D;
	// SQRT of 2 to calculate the square inside the doughnut
	private static final double SQRT_2 = Math.sqrt(2);
	// controller type
	private final ControllerType type;
	// flag to know if the draw has been invoked by an update
	// instead of animation when hovered
	private boolean fromUpdate = false;

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
			// gets chart node
			ChartNode node = context.getNode();
			// checks if the animation is enabled
			// if not, draws labels with easing 1
			if (!isAnimationConsistetForDrawingByEasing(chart) || !fromUpdate) {
				// draws labels
				drawLabels(chart, node, 1D);
			}
		} else {
			// if here, arguments are not consistent
			// exception
			throw new IllegalArgumentException("Draw method arguments are not consistent");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.controllers.AbstractController#update(org.pepstock.charba.client.controllers.ControllerContext, org.pepstock.charba.client.IsChart,
	 * org.pepstock.charba.client.options.IsAnimationModeKey)
	 */
	@Override
	public void update(ControllerContext context, IsChart chart, IsAnimationModeKey mode) {
		// sets the flag
		// needed into draw to know if the labels must be drawn or not
		// if resize, do not invoke the animation
		fromUpdate = DefaultAnimationModeKey.RESIZE.equals(mode) ? false : true;
		// update the doughnut chart
		super.update(context, chart, mode);
	}

	/**
	 * Returns <code>true</code> if the animation configuration on chart and dataset are enabled, checking also the duration of the animation options.
	 * 
	 * @param chart chart instance to check
	 * @return <code>true</code> if the animation configuration on chart and dataset are enabled, checking also the duration of the animation options
	 */
	boolean isAnimationConsistetForDrawingByEasing(IsChart chart) {
		// checks if naimation and its duration at configuration level are enabled.
		boolean isAnimated = chart.getOptions().isAnimationEnabled() && chart.getOptions().getAnimation().getDuration() >= MINIMUM_ANIMATION_DURATION;
		// checks if there is any dataset
		if (!chart.getData().getDatasets().isEmpty()) {
			// gets the dataset (should be only 1
			Dataset dataset = chart.getData().getDatasets().get(0);
			// checks the dataset animation
			isAnimated = isAnimated && dataset.isAnimationEnabled() && dataset.getAnimation().getDuration() >= MINIMUM_ANIMATION_DURATION;
		}
		// checks if options are meter ones
		if (chart.getOptions() instanceof MeterOptions) {
			// casts to meter options
			MeterOptions meterOptions = (MeterOptions)chart.getOptions();
			// checks the animated display options
			isAnimated = isAnimated && meterOptions.isAnimatedDisplay();
		}
		return isAnimated;
	}

	/**
	 * Draws the labels in the center of doughnut chart.
	 * 
	 * @param chart chart instance
	 * @param node native chart as chart node
	 * @param easing a value between 0 and 1 which indicates the animation progress
	 */
	void drawLabels(IsChart chart, ChartNode node, double easing) {
		// gets the list of datasets
		List<Dataset> datasets = chart.getData().getDatasets();
		// checks if not empty
		if (!datasets.isEmpty()) {
			// gets dataset index 0
			MeterDataset dataset = (MeterDataset) datasets.get(0);
			// checks if meter chart
			if (chart instanceof MeterChart) {
				MeterChart meterChart = (MeterChart) chart;
				MeterOptions options = meterChart.getOptions();
				// let's draw the value inside the doughnut
				execute(chart, node, dataset, options, easing);
			} else if (chart instanceof GaugeChart) {
				// checks if meter chart
				GaugeChart gaugeChart = (GaugeChart) chart;
				GaugeOptions options = gaugeChart.getOptions();
				// let's draw the value inside the doughnut
				execute(chart, node, dataset, options, easing);
			}
		}
		// checks if it must disable the update
		if (easing == 1D) {
			// reset flag from update
			fromUpdate = false;
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
		// clear the previous label
		ctx.clearRect(centerX - (sideOfSquare / 2D), centerY - (sideOfSquare / 2D), sideOfSquare, sideOfSquare);
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
