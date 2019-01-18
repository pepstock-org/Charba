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
package org.pepstock.charba.client.jsinterop;

/**
 * Enumerates all out-of-the-box types of a chart.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public enum ChartType implements Type
{
	/**
	 * A line chart is a way of plotting data points on a line. Often, it is used to show trend data, or the comparison of two
	 * data sets.
	 */
	line(ScaleType.multi),
	/**
	 * A bar chart provides a way of showing data values represented as vertical bars. It is sometimes used to show trend data,
	 * and the comparison of multiple data sets side by side.
	 */
	bar(ScaleType.multi),
	/**
	 * A horizontal bar chart is a variation on a vertical bar chart. It is sometimes used to show trend data, and the
	 * comparison of multiple data sets side by side.
	 */
	horizontalBar(ScaleType.multi),
	/**
	 * Pie chart is probably the most commonly used chart. It is divided into segments, the arc of each segment shows the
	 * proportional value of each piece of data. It is excellent at showing the relational proportions between data. Pie and
	 * doughnut charts are effectively the same class in Chart.js, but have one different default value - their
	 * cutoutPercentage. This equates what percentage of the inner should be cut out. This defaults to 0 for pie charts, and 50
	 * for doughnuts.
	 */
	pie(ScaleType.none),
	/**
	 * Doughnut chart is probably the most commonly used chart. It is divided into segments, the arc of each segment shows the
	 * proportional value of each piece of data. It is excellent at showing the relational proportions between data. Pie and
	 * doughnut charts are effectively the same class in Chart.js, but have one different default value - their
	 * cutoutPercentage. This equates what percentage of the inner should be cut out. This defaults to 0 for pie charts, and 50
	 * for doughnuts.
	 */
	doughnut(ScaleType.none),
	/**
	 * A radar chart is a way of showing multiple data points and the variation between them. They are often useful for
	 * comparing the points of two or more different data sets.
	 */
	radar(ScaleType.single),
	/**
	 * Polar area charts are similar to pie charts, but each segment has the same angle - the radius of the segment differs
	 * depending on the value. This type of chart is often useful when we want to show a comparison data similar to a pie chart,
	 * but also show a scale of values for context.
	 */
	polarArea(ScaleType.single),
	/**
	 * Scatter charts are based on basic line charts with the x axis changed to a linear axis. To use a scatter chart, data must
	 * be passed as objects containing X and Y properties.
	 */
	scatter(ScaleType.multi),
	/**
	 * A bubble chart is used to display three dimensions of data at the same time. The location of the bubble is determined by
	 * the first two dimensions and the corresponding horizontal and vertical axes. The third dimension is represented by the
	 * size of the individual bubbles.
	 */
	bubble(ScaleType.multi);

	private final ScaleType scaleType;

	/**
	 * Creates the type with scale type of this kind of chart.
	 * 
	 * @param scaleType scale type of this kind of chart.
	 */
	private ChartType(ScaleType scaleType) {
		this.scaleType = scaleType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.Type#scaleType()
	 */
	@Override
	public ScaleType scaleType() {
		return scaleType;
	}

	/**
	 * Gets the chart type by a string.
	 * 
	 * @param value string value to search
	 * @return the chart type instance which matches with the string otherwise <code>null</code>.
	 */
	public static Type get(String value) {
		// checks if the value is consistent
		if (value != null) {
			// scans all chart types
			for (Type type : values()) {
				// checks the name of chart type
				if (type.name().equals(value)) {
					return type;
				}
			}
		}
		// is null if not found
		return null;
	}
}