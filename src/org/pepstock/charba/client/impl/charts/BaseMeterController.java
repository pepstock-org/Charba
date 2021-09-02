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
import org.pepstock.charba.client.Helpers;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.controllers.AbstractController;
import org.pepstock.charba.client.controllers.ControllerContext;
import org.pepstock.charba.client.controllers.ControllerProvider;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.dom.elements.Context2dItem;
import org.pepstock.charba.client.dom.elements.ImageData;
import org.pepstock.charba.client.dom.elements.TextMetricsItem;
import org.pepstock.charba.client.dom.enums.TextBaseline;
import org.pepstock.charba.client.enums.Render;
import org.pepstock.charba.client.enums.TextAlign;
import org.pepstock.charba.client.items.ChartAreaNode;
import org.pepstock.charba.client.items.DatasetItem;
import org.pepstock.charba.client.items.FontItem;
import org.pepstock.charba.client.options.IsImmutableFont;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Controller implementation to create charts like meter of gauges, extending doughnut chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class BaseMeterController extends AbstractController {

	// static reference of controller provider for meter charts
	static final ControllerProvider PROVIDER = new BaseMeterControllerProvier();
	// default font increment
	private static final int FONT_SIZE_DECREMENT = 4;
	// minimum font size
	private static final int MINIMUM_FONT_SIZE = 8;
	// default padding
	private static final double PADDING = 4D;
	// max percentage
	private static final double MAX_PERCENTAGE = 100D;
	// SQRT of 2 to calculate the square inside the doughnut
	private static final double SQRT_2 = Math.sqrt(2);

	/**
	 * Creates the controller using the type passed as argument.
	 * 
	 * @param type controller type, could be meter or gauge.
	 */
	private BaseMeterController(ControllerType type) {
		super(type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Controller#onBeforeInitialize(org.pepstock.charba.client.controllers.ControllerContext, org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onBeforeInitialize(ControllerContext context, IsChart chart) {
		// checks if arguments are consistent
		Checker.assertCheck(Controller.isConsistent(this, context, chart), "Initialize method arguments are not consistent");
		// gets the data set at index
		Dataset dataset = chart.getData().getDatasets().get(context.getIndex());
		// checks if is a meter data set (or gauge)
		Checker.assertCheck(dataset instanceof MeterDataset, "Dataset at index " + context.getIndex() + " is not a MeterDataset");
		// casts to meter data set
		MeterDataset meterDataset = (MeterDataset) dataset;
		// meter or gauge charts must have only 1 data set
		// checks if there is more than 1
		if (context.getIndex() > 0) {
			// if more than 1
			// forces hidden data set
			meterDataset.hide();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Controller#onBeforeDraw(org.pepstock.charba.client.controllers.ControllerContext, org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onBeforeDraw(ControllerContext context, IsChart chart) {
		// checks if arguments are consistent
		// checks if the index is 0 because
		// only the data set 0 contains my value
		Checker.assertCheck(Controller.isConsistent(this, context, chart) && context.getIndex() == 0, "Draw method arguments are not consistent");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Controller#onAfterDraw(org.pepstock.charba.client.controllers.ControllerContext, org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onAfterDraw(ControllerContext context, IsChart chart) {
		// gets chart node
		ChartNode node = context.getNode();
		// draws labels
		drawLabels(chart, node);
	}

	/**
	 * Draws the labels in the center of doughnut chart.
	 * 
	 * @param chart chart instance
	 * @param node native chart as chart node
	 */
	void drawLabels(IsChart chart, ChartNode node) {
		// gets the list of data sets
		List<Dataset> datasets = chart.getData().getDatasets();
		// checks if not empty
		if (!datasets.isEmpty()) {
			// gets data set index 0
			MeterDataset dataset = (MeterDataset) datasets.get(0);
			// checks if meter chart
			if (chart instanceof MeterChart) {
				MeterChart meterChart = (MeterChart) chart;
				MeterOptions options = meterChart.getOptions();
				// let's draw the value inside the doughnut
				execute(meterChart, node, dataset, options, calculateEase(meterChart, options, dataset));
			} else if (chart instanceof GaugeChart) {
				// checks if meter chart
				GaugeChart gaugeChart = (GaugeChart) chart;
				GaugeOptions options = gaugeChart.getOptions();
				// let's draw the value inside the doughnut
				execute(gaugeChart, node, dataset, options, calculateEase(gaugeChart, options, dataset));
			}
		}
	}

	/**
	 * Calculates the easing based on circumference of the data set element.
	 * 
	 * @param chart chart instance to use for calculation
	 * @param options the chart options
	 * @param dataset meter data set to get the ratio between value and maximum
	 * @return easing of drawing (between 0 and 1) for animation
	 */
	private double calculateEase(IsChart chart, MeterOptions options, MeterDataset dataset) {
		// checks if animation is required
		if (options.isAnimated()) {
			// calculate the max circumference for the data set
			// transforming the value in degrees
			// in order to compare with circumference of the element
			double maxCircumference = dataset.getValueMaximumRatio() * 360D;
			// gets the data set item of data set 0
			DatasetItem item = chart.getDatasetItem(0);
			// calculates the circumference in degree of element
			double elemCircumference = item.getElements().get(0).getCircumference() * 180 / Math.PI;
			// calculate the dividend
			double dividend = Math.min(maxCircumference, elemCircumference);
			double divider = Math.max(maxCircumference, elemCircumference);
			// returns easing
			return Math.min(dividend / divider, 1D);
		}
		// if here, no animation is requested
		// then returns no easing
		return 1D;
	}

	/**
	 * Draws the value inside the inner radius of doughnut.
	 * 
	 * @param chart chart instance
	 * @param item chart item with CHART.JS properties needed to calculate the area where to drawn the value
	 * @param dataset the data set instance
	 * @param options the chart options
	 * @param ease easing of drawing (between 0 and 1) for animation
	 */
	private void execute(BaseMeterChart<?> chart, ChartNode item, MeterDataset dataset, MeterOptions options, double ease) {
		// checks if the rendering of the label is disable
		if (Render.NONE.equals(options.getRender())) {
			// do nothing
			return;
		}
		// gets data set item at index 0
		// it can not be null
		DatasetItem datasetMetaItem = chart.getDatasetItem(0);
		// calculate the side of the square where to draw the value
		// minus 1 because of the image data to put and the round management
		final double sideOfSquare = ((datasetMetaItem.getController().getInnerRadius() * 2) / SQRT_2) - 1;
		// gets canvas context 2d
		Context2dItem ctx = chart.getCanvas().getContext2d();
		// gets the chart area of CHART.JS
		ChartAreaNode area = item.getChartArea();
		// calculate the center point of the square
		final double centerX = (area.getRight() - area.getLeft()) / 2D + area.getLeft();
		final double centerY = (area.getBottom() - area.getTop()) / 2D + area.getTop();
		final double x = centerX - (sideOfSquare / 2D);
		final double y = centerY - (sideOfSquare / 2D);
		// gets max value
		final double maxValue = Render.PERCENTAGE.equals(options.getRender()) || Render.PERCENTAGE_AND_LABEL.equals(options.getRender()) ? MAX_PERCENTAGE : dataset.getMax();
		// gets value
		final double valueToCalculate = Render.PERCENTAGE.equals(options.getRender()) || Render.PERCENTAGE_AND_LABEL.equals(options.getRender()) ? dataset.getValue() / dataset.getMax() : dataset.getValue();
		// here is calculating the value to showed
		// based on easing of drawing
		final double value = options.isAnimated() ? valueToCalculate * ease : valueToCalculate;
		// gets context
		MeterContext context = options.getContext();
		// sets value and ease for context
		// to execute the formatted value for max
		context.setEasing(1D);
		context.setValue(maxValue);
		// gets max value in the string to check font size
		final String maxValueToShow = getFormattedValue(options, context);
		// sets to the right values
		context.setEasing(ease);
		context.setValue(value);
		// value to show with format required
		final String valueToShow = getFormattedValue(options, context);
		// gets font
		final FontItem font = options.getFontItem();
		// gets font color
		final String fontColor = getFontColor(options, context);
		// gets the label
		final String label = dataset.getLabel();
		// saves context
		ctx.save();
		// begins path and clip the area
		ctx.beginPath();
		ctx.rect(x, y, sideOfSquare, sideOfSquare);
		// clip area
		ctx.clip();
		// checks if image data must be get
		if (chart.getImageData() == null) {
			// gets image data in the center, 1 pixel more
			// because of rounded of dimensions
			ImageData data = ctx.getImageData(x, y, sideOfSquare + 1, sideOfSquare + 1);
			// stores the image data
			chart.setImageData(data);
		}
		// clears the previous label
		ctx.clearRect(x, y, sideOfSquare, sideOfSquare);
		// sets stored image data to canvas
		ctx.putImageData(chart.getImageData(), x, y);
		// checks if auto font size is set
		if (options.isAutoFontSize()) {
			// calculates the font size
			calculateFontSize(ctx, sideOfSquare, maxValueToShow, font);
		}
		// sets color to canvas
		ctx.setFillColor(fontColor);
		// sets alignment
		ctx.setTextAlign(TextAlign.CENTER);
		// gets immutable font
		IsImmutableFont immutableFont = Helpers.get().toFont(font);
		// checks if it must draw also the label
		if ((Render.VALUE_AND_LABEL.equals(options.getRender()) || Render.PERCENTAGE_AND_LABEL.equals(options.getRender())) && label != null) {
			// sets font
			ctx.setFont(immutableFont.toCSSString());
			// sets alignment from center point
			ctx.setTextBaseline(TextBaseline.BOTTOM);
			// draws text
			ctx.fillText(valueToShow, centerX, centerY - PADDING);
			// checks if auto font size is set
			if (options.isAutoFontSize()) {
				// re-calculates the font size for label
				calculateFontSize(ctx, sideOfSquare, label, font);
			}
			ctx.setFont(immutableFont.toCSSString());
			// sets alignment from center point
			ctx.setTextBaseline(TextBaseline.TOP);
			// draws text
			ctx.fillText(dataset.getLabel(), centerX, centerY + PADDING);
		} else {
			// if here it must draw ONLY the value
			// sets font
			ctx.setFont(immutableFont.toCSSString());
			// sets alignment from center point
			ctx.setTextBaseline(TextBaseline.MIDDLE);
			// draws text
			ctx.fillText(valueToShow, centerX, centerY);
		}
		// restores context
		ctx.restore();
	}

	/**
	 * Calculates the font size based on available space in the square in the doughnut inner radius.
	 * 
	 * @param ctx canvas context
	 * @param sideOfSquare side of square
	 * @param value value to display
	 * @param font font instance
	 */
	private void calculateFontSize(Context2dItem ctx, double sideOfSquare, String value, FontItem font) {
		// half of side of square
		int fontSize = (int) (sideOfSquare / 2);
		boolean check = true;
		// loop to calculate the size
		while (check) {
			// stores size
			font.setSize(fontSize);
			// sets font
			ctx.setFont(Helpers.get().toFontString(font));
			// gets metrics
			TextMetricsItem metrics = ctx.measureText(value);
			// if the width is inside of side (and padding) or
			// is the minimum size of font
			// exit
			if ((metrics.getWidth() + PADDING * 2D) < sideOfSquare || fontSize <= MINIMUM_FONT_SIZE) {
				check = false;
			} else {
				// decrements the font size
				fontSize = fontSize - FONT_SIZE_DECREMENT;
			}
		}
	}

	/**
	 * Returns the color to apply to rendered label, invoking the callback if set.
	 * 
	 * @param options options of the chart
	 * @param context scriptable context of meter
	 * @return the font color to apply to rendered label
	 */
	private String getFontColor(MeterOptions options, MeterContext context) {
		// checks if the options font color is set as callback
		if (options.getFontColorCallback() != null) {
			Object result = ScriptableUtils.getOptionValueAsColor(context, options.getFontColorCallback(), MeterOptions.DEFAULT_FONT_COLOR_AS_STRING, false);
			// checks the result
			if (result instanceof IsColor) {
				// casts to color
				IsColor color = (IsColor) result;
				// returns as string
				return color.toRGBA();
			} else if (result instanceof String) {
				// returns as string
				return (String) result;
			}
			// if here, invalid returns object
			// then goes to the end of this method
			// to return the default
		} else if (options.getFontColor() != null) {
			// checks if the color has been as color
			return options.getFontColor().toRGBA();
		}
		// if here, invalid returns object from callback
		// or font color is not set
		// then returns the default
		return MeterOptions.DEFAULT_FONT_COLOR_AS_STRING;
	}

	/**
	 * Returns a formatted value of the chart applying the precision or invoking the value callback.
	 * 
	 * @param options options of the chart
	 * @param context scriptable context of meter
	 * @return a formatted value of the chart
	 */
	private String getFormattedValue(MeterOptions options, MeterContext context) {
		// checks if options has got a callback
		if (options.getFormatCallback() != null) {
			// invokes callback
			String result = options.getFormatCallback().invoke(context);
			// checks if result is consistent
			if (result != null) {
				// return this value
				return result;
			}
		}
		// if here, it sues the precision set in the options
		return Utilities.applyPrecision(context.getValue(), options.getPrecision());
	}

	/**
	 * Inner class which is implementing a {@link ControllerProvider} to create a base meter controller.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class BaseMeterControllerProvier implements ControllerProvider {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.controllers.ControllerProvider#provide(org.pepstock.charba.client.controllers.ControllerType)
		 */
		@Override
		public Controller provide(ControllerType controllerType) {
			return new BaseMeterController(controllerType);
		}

	}

}
