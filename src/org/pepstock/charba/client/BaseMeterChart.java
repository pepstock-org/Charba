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
package org.pepstock.charba.client;

import java.util.List;

import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.data.MeterDataset;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.MeterDisplay;
import org.pepstock.charba.client.items.ChartAreaItem;
import org.pepstock.charba.client.items.ChartItem;
import org.pepstock.charba.client.options.MeterOptions;
import org.pepstock.charba.client.plugins.AbstractPlugin;
import org.pepstock.charba.client.plugins.InvalidPluginIdException;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.Context2d.TextAlign;
import com.google.gwt.canvas.dom.client.Context2d.TextBaseline;
import com.google.gwt.canvas.dom.client.TextMetrics;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.i18n.client.NumberFormat;

/**
 * This is an abstract meter chart, inherited by a meter and gauge charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class BaseMeterChart<O extends MeterOptions, D extends MeterDataset> extends AbstractChart<O, D> {
	
	// exception message if not able to add internal plugin
	private static final String UNABLE_TO_ADD_PLUGIN = "Unable to add plugin. ";

	/**
	 * Default of maximum of dataset
	 */
	protected static final double DEFAULT_MAX = 100D;
	
	private final ChartLabel<O,D> plugin;
	
	/**
	 * Builds the object.
	 */
	protected BaseMeterChart() {
		try {
			// creates teh plugin
			plugin = new ChartLabel<O,D>(this);
			// adds it
			getPlugins().add(plugin);
		} catch (InvalidPluginIdException e) {
			throw new UnsupportedOperationException(UNABLE_TO_ADD_PLUGIN + e.getMessage(), e);
		}
	}
	
	/**
	 * Returns a dataset with a maximum value.
	 * @param max maximum value of dataset
	 * @return dataset instance
	 */
	public abstract D newDataset(double max);
	
	/**
	 * This is a private plugin which writes the value inside the meter or gauge.<br>
	 * Usable only for gauge and meter.<br>
	 * It calculates the automatically the font size based on the space available inside the doughnut.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 * @param <O> options instance type
	 * @param <D> dataset instance type
	 */
	private static class ChartLabel<O extends MeterOptions, D extends MeterDataset> extends AbstractPlugin {
		
		/**
		 * Plugin ID 
		 */
		private static final String ID = "meterdisplay";
		// default font increment
		private static final int FONT_SIZE_INCREMENT = 4;
		// minimum font size
		private static final int MINIMUM_FONT_SIZE = 12;
		// default padding
		private static final int PADDING = 4;
		// max percentage
		private static final double MAX_PERCENTAGE = 100D;
		// string format of font style
		private static final String FONT_TEMPLATE = "{0} {1}px {2}";
		// SQRT of 2 to calculate the sqare inside the doughnut
		private static final double SQRT_2 = Math.sqrt(2);
		// chart instance
		private final BaseMeterChart<O,D> superChart;
		
		/**
		 * Builds the object storing the chart instance.
		 * 
		 * @param chart chart instance
		 */
		ChartLabel(BaseMeterChart<O,D> chart) {
			this.superChart = chart;
		}

		/* (non-Javadoc)
		 * @see org.pepstock.charba.client.Plugin#getId()
		 */
		@Override
		public String getId() {
			return ID;
		}

		/* (non-Javadoc)
		 * @see org.pepstock.charba.client.plugins.AbstractPlugin#onAfterDatasetsDraw(org.pepstock.charba.client.AbstractChart, double)
		 */
		@Override
		//public void onAfterDatasetsDraw(AbstractChart<?, ?> chart, double easing, JavaScriptObject options) {
		public void onAfterDraw(AbstractChart<?, ?> chart, double easing, JavaScriptObject options) {
			// gets the list of datasets
			List<Dataset> datasets = superChart.getData().getDatasets();
			// checks if not empty
			if (!datasets.isEmpty()){
				// gets chart item
				ChartItem item = superChart.getChartItem();
				@SuppressWarnings("unchecked")
				D dataset = (D) datasets.get(0);
				// let's draw the value inside the doughnut
				execute(chart, item, dataset, superChart.getOptions());
			}
		}
		
		/**
		 * Draws the value inside the inner radius of doughnut.
		 * @param chart chart instance
		 * @param item chart item with CHART.JS properties needed to calculate the area where to drwan the value
		 * @param dataset the dataset instance
		 * @param options the chart options
		 */
		private void execute(AbstractChart<?, ?> chart, ChartItem item, D dataset, O options) {
			// calculate the side of the square where to draw the value
			final int sideOfSquare = (int) ((item.getInnerRadius() * 2) / SQRT_2);
			// gets canvas context 2d
			Context2d ctx = chart.getCanvas().getContext2d();
			// gets the chart area of CHART.JS
			ChartAreaItem area = item.getChartArea();
			// calculate the center point of the square
			final int centerX = (area.getRight() - area.getLeft()) / 2 + area.getLeft();
			final int centerY = (area.getBottom() - area.getTop()) / 2 + area.getTop();
			// gets max value 
			final double maxValue = MeterDisplay.percentage.equals(options.getDisplay()) || MeterDisplay.percentageAndLabel.equals(options.getDisplay()) ? MAX_PERCENTAGE : dataset.getMax();
			// gets value
			final double value =  MeterDisplay.percentage.equals(options.getDisplay()) || MeterDisplay.percentageAndLabel.equals(options.getDisplay()) ? dataset.getValue() / dataset.getMax() : dataset.getValue();
			// gets max value into string to check font size
			final String maxValueToShow = getFormattedValue(maxValue, options.getFormat());
			// value to show with format required
			final String valueToShow = getFormattedValue(value, options.getFormat());
			// gets font style
			final FontStyle style = options.getFontStyle() == null ? FontStyle.normal : options.getFontStyle();
			// gets font family
			final String fontFamily = options.getFontFamily() == null ? Defaults.getGlobal().getDefaultFontFamily() : options.getFontFamily();
			// gets font color
			final String fontColor = options.getDisplayFontColor() == null ? MeterOptions.DEFAULT_DISPLAY_COLOR.toRGBA() : options.getDisplayFontColor().toRGBA();
			// gets the label
			final String label = dataset.getLabel();
			// calculates the font size
			int fontSize = calculateFontSize(ctx, sideOfSquare, maxValueToShow, style, fontFamily);
			// sets color to canvas
			ctx.setFillStyle(fontColor);
			// sets alignment
			ctx.setTextAlign(TextAlign.CENTER);
			// checks if it must draw also the label
			if ((MeterDisplay.valueAndLabel.equals(options.getDisplay()) || MeterDisplay.percentageAndLabel.equals(options.getDisplay())) && label != null) {
				// sets font 
				ctx.setFont(getFont(style, fontSize, fontFamily));
				// sets alignment from center point
				ctx.setTextBaseline(TextBaseline.BOTTOM);
				// draws text
				ctx.fillText(valueToShow, centerX, centerY - PADDING);
				// re-calculates the font size for label
				fontSize = calculateFontSize(ctx, sideOfSquare, label, style, fontFamily);
				ctx.setFont(getFont(style, fontSize, fontFamily));
				// sets alignment from center point
				ctx.setTextBaseline(TextBaseline.TOP);
				// draws text
				ctx.fillText(dataset.getLabel(), centerX, centerY + PADDING);
			} else {
				// if here it must draw ONLY the value
				// sets font
				ctx.setFont(getFont(style, fontSize, fontFamily));
				// sets alignment from center point
				ctx.setTextBaseline(TextBaseline.MIDDLE);
				// draws text
				ctx.fillText(valueToShow, centerX, centerY);
			}
		}
		
		/**
		 * Calculates the font size based on available space into square into doughnut inner radius.
		 * @param ctx canvas context
		 * @param sideOfSquare side of square 
		 * @param value value to display
		 * @param style font style
		 * @param fontFamily font family
		 * @return the font size to use 
		 */
		private int calculateFontSize(Context2d ctx, int sideOfSquare, String value, FontStyle style, String fontFamily) {
			// half of side of square
			int fontSize = (int) (sideOfSquare / 2);
			boolean check = true;
			// loop to calculate the size
			while (check) {
				// sets font
				ctx.setFont(getFont(style, fontSize, fontFamily));
				// gets metrics
				TextMetrics metrics = ctx.measureText(value);
				// if the width is inside of side (and padding) or
				// is the minimum size of font
				// exit
				if ((metrics.getWidth() + PADDING * 2) < sideOfSquare || fontSize <= MINIMUM_FONT_SIZE) {
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
		 * Formats the value to disply
		 * @param value value to display
		 * @param format number format to use
		 * @return the formatted value to display
		 */
		private String getFormattedValue(double value, String format) {
			// checks if format is set otherwise it uses the default
			return format == null ? NumberFormat.getFormat(MeterOptions.DEFAULT_FORMAT).format(value) : NumberFormat.getFormat(format).format(value);
		}

		/**
		 * Builds the font string to use in the canvas object.<br>
		 * The format is [fontStyle] [fontSize] [fontFamily].
		 * @param style font style to use
		 * @param fontSize font size
		 * @param fontFamily font family
		 * @return the font string to use in the canvas object.
		 */
		private String getFont(FontStyle style, int fontSize, String fontFamily) {
			// gets template
			final String result = FONT_TEMPLATE;
			// formats
			return result.replaceAll("\\{0\\}", style.name()).replaceAll("\\{1\\}", String.valueOf(fontSize)).replaceAll("\\{2\\}", fontFamily);
		}
	}
}