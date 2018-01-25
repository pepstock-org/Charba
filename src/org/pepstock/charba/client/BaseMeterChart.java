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
 * METER chart implementation.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class BaseMeterChart<O extends MeterOptions, D extends MeterDataset> extends AbstractChart<O, D> {
	
	private static final String UNABLE_TO_ADD_PLUGIN = "Unable to add plugin. ";

	protected static final double DEFAULT_MAX = 100D;
	
	private final ChartLabel<O,D> plugin;
	
	/**
	 * Builds the object.
	 */
	protected BaseMeterChart() {
		try {
			plugin = new ChartLabel<O,D>(this);
			getPlugins().add(plugin);
		} catch (InvalidPluginIdException e) {
			throw new UnsupportedOperationException(UNABLE_TO_ADD_PLUGIN + e.getMessage(), e);
		}
	}
	
	protected final ChartItem getChart(){
		return (ChartItem)super.chart;
	}

	public abstract D newDataset(double max);
	
	
	private static class ChartLabel<O extends MeterOptions, D extends MeterDataset> extends AbstractPlugin {
		
		/**
		 * Plugin ID 
		 */
		private static final String ID = "meterdisplay";
		
		private static final int FONT_SIZE_INCREMENT = 4;

		private static final int MINIMUM_FONT_SIZE = 12;

		private static final int PADDING = 4;

		private static final double MAX_PERCENTAGE = 100D;

		private static final String FONT_TEMPLATE = "{0} {1}px {2}";

		private static final double SQRT_2 = Math.sqrt(2);

		private final BaseMeterChart<O,D> superChart;
		
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
			List<Dataset> datasets = superChart.getData().getDatasets();
			if (!datasets.isEmpty()){
				ChartItem m = superChart.getChart();
				@SuppressWarnings("unchecked")
				D dataset = (D) datasets.get(0);
				execute(chart, m, dataset, superChart.getOptions());
			}
		}
		
		private void execute(AbstractChart<?, ?> chart, ChartItem item, D dataset, O options) {
			final int sideOfSquare = (int) ((item.getInnerRadius() * 2) / SQRT_2);

			Context2d ctx = chart.getCanvas().getContext2d();
			final int centerX = chart.getCanvas().getOffsetWidth() / 2;
			final int centerY = chart.getCanvas().getOffsetHeight() / 2;

			// gets value
			final double maxValue = MeterDisplay.percentage.equals(options.getDisplay()) || MeterDisplay.percentageAndLabel.equals(options.getDisplay()) ? MAX_PERCENTAGE : dataset.getMax();

			final double value =  MeterDisplay.percentage.equals(options.getDisplay()) || MeterDisplay.percentageAndLabel.equals(options.getDisplay()) ? dataset.getValue() / dataset.getMax() : dataset.getValue();
			// gets max value into string to check font size
			final String maxValueToShow = getFormattedValue(maxValue, options.getFormat());
			// value to show with format required
			final String valueToShow = getFormattedValue(value, options.getFormat());

			final FontStyle style = options.getFontStyle() == null ? FontStyle.normal : options.getFontStyle();

			final String fontFamily = options.getFontFamily() == null ? Defaults.getGlobal().getDefaultFontFamily() : options.getFontFamily();

			final String fontColor = options.getLabelFontColor() == null ? MeterOptions.DEFAULT_LABEL_COLOR : options.getLabelFontColor();

			final String label = dataset.getLabel();

			int fontSize = calculateFontSize(ctx, sideOfSquare, maxValueToShow, style, fontFamily);

			ctx.setFillStyle(fontColor);
			ctx.setTextAlign(TextAlign.CENTER);
			if ((MeterDisplay.valueAndLabel.equals(options.getDisplay()) || MeterDisplay.percentageAndLabel.equals(options.getDisplay())) && label != null) {
				ctx.setFont(getFont(style, fontSize, fontFamily));
				ctx.setTextBaseline(TextBaseline.BOTTOM);
				ctx.fillText(valueToShow, centerX, centerY - PADDING);

				fontSize = calculateFontSize(ctx, sideOfSquare, label, style, fontFamily);
				ctx.setFont(getFont(style, fontSize, fontFamily));
				ctx.setTextBaseline(TextBaseline.TOP);
				ctx.fillText(dataset.getLabel(), centerX, centerY + PADDING);

			} else {
				ctx.setFont(getFont(style, fontSize, fontFamily));
				ctx.setTextBaseline(TextBaseline.MIDDLE);
				ctx.fillText(valueToShow, centerX, centerY);
			}
		}
		
		private int calculateFontSize(Context2d ctx, int sideOfSquare, String value, FontStyle style, String fontFamily) {
			int fontSize = (int) (sideOfSquare / 2);
			boolean check = true;
			while (check) {
				ctx.setFont(getFont(style, fontSize, fontFamily));
				TextMetrics metrics = ctx.measureText(value);
				if ((metrics.getWidth() + PADDING * 2) < sideOfSquare || fontSize <= MINIMUM_FONT_SIZE) {
					check = false;
				} else {
					fontSize = fontSize - FONT_SIZE_INCREMENT;
				}
			}
			return fontSize;
		}

		private String getFormattedValue(double value, String format) {
			return format == null ? NumberFormat.getFormat(MeterOptions.DEFAULT_FORMAT).format(value) : NumberFormat.getFormat(format).format(value);
		}

		private String getFont(FontStyle style, int fontSize, String fontFamily) {
			String result = FONT_TEMPLATE;
			result = result.replaceAll("\\{0\\}", style.name()).replaceAll("\\{1\\}", String.valueOf(fontSize)).replaceAll("\\{2\\}", fontFamily);
			return result;
		}
	}
}