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
package org.pepstock.charba.client.impl.plugins;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.colors.tiles.TilesFactory;
import org.pepstock.charba.client.enums.PointStyle;
import org.pepstock.charba.client.items.LegendLabelItem;
import org.pepstock.charba.client.items.Undefined;

/**
 * Object used to invoke {@link TilesFactory} in order to get the {@link PointStyle} representation for legend.<br>
 * This object is used internally even if is public, used only by {@link HtmlLegend} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class HtmlLegendItem {
	// chart instance
	private final IsChart chart;
	// legend item instance
	private LegendLabelItem legendItem = null;
	// size of legend cell for color
	private int size = 0;
	// radius to apply to point style
	private double radius = 0D;

	/**
	 * Creates the object with a chart instance.<br>
	 * It is not visible externally of the packaged because it is used only by {@link HtmlLegend} plugin.
	 * 
	 * @param chart chart instance
	 */
	HtmlLegendItem(IsChart chart) {
		// checks chart instance
		IsChart.checkIfValid(chart);
		// stores chart
		this.chart = chart;
	}

	/**
	 * Returns the chart instance
	 * 
	 * @return the chart instance
	 */
	public IsChart getChart() {
		return chart;
	}

	/**
	 * Returns the legend item instance, needed to create the point style for legend.
	 * 
	 * @return the legend item instance
	 */
	public LegendLabelItem getLegendItem() {
		return legendItem;
	}

	/**
	 * Sets the legend item instance, needed to create the point style for legend.
	 * 
	 * @param legendItem the legend item instance
	 */
	void setLegendItem(LegendLabelItem legendItem) {
		this.legendItem = legendItem;
	}

	/**
	 * Returns the size of the tile for point style legend.
	 * 
	 * @return the size of the tile for point style legend
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Sets the size of the tile for point style legend.
	 * 
	 * @param size the size of the tile for point style legend
	 */
	void setSize(int size) {
		this.size = size;
	}

	/**
	 * Sets the radius of the tile for point style legend.
	 * 
	 * @return the radius of the tile for point style legend
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * Sets the radius of the tile for point style legend.
	 * 
	 * @param radius the radius of the tile for point style legend
	 */
	void setRadius(double radius) {
		this.radius = radius;
	}

	/**
	 * Returns the string JSON representation of the object as unique key or <code>null</code> if legend item is not consistent.
	 * 
	 * @return the string JSON representation of the object as unique key or <code>null</code> if legend item is not consistent
	 */
	public final String toUniqueKey() {
		// checks if legend item is consistent
		if (legendItem != null) {
			// set chart id as starting value
			StringBuilder builder = new StringBuilder(chart.getId());
			// appends the json of legend item
			builder.append(legendItem.toJSON().replace('\n', ' '));
			return builder.toString();
		}
		// if here, legend item non consistent
		return Undefined.STRING;
	}
}
