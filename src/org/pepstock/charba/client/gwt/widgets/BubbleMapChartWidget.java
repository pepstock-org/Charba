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
package org.pepstock.charba.client.gwt.widgets;

import org.pepstock.charba.client.IsDatasetCreator;
import org.pepstock.charba.client.geo.BubbleMapChart;
import org.pepstock.charba.client.geo.BubbleMapDataset;
import org.pepstock.charba.client.geo.BubbleMapOptions;
import org.pepstock.charba.client.geo.Coordinates;
import org.pepstock.charba.client.geo.CoordinatesPoint;

/**
 * BUBBLEMAP chart GWT WIDGET implementation.<br>
 * A Bubble Map, as known as Proportional Symbol is used to render maps with dots that are scaled according to some numerical value.<br>
 * It is based on a regular bubble chart where the positioning is done using latitude and longitude to create a legend for the different radi.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class BubbleMapChartWidget extends AbstractChartWidget<BubbleMapChart> implements IsDatasetCreator<BubbleMapDataset> {

	/**
	 * Builds the object.
	 */
	public BubbleMapChartWidget() {
		this(new BubbleMapChart());
	}

	/**
	 * Builds the chart.<br>
	 * This is must be extended for controller which are based on this chart.
	 * 
	 * @param extendedChart new chart
	 */
	protected BubbleMapChartWidget(BubbleMapChart extendedChart) {
		super(extendedChart);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getOptions()
	 */
	@Override
	public BubbleMapOptions getOptions() {
		return getChart().getOptions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.gwt.widgets.AbstractChartWidget#newDataset(boolean)
	 */
	@Override
	public BubbleMapDataset newDataset(boolean hidden) {
		return getChart().newDataset(hidden);
	}

	/**
	 * Translates X and Y coordinates of the canvas where the chart is drawn in latitude and longitude.
	 * 
	 * @param x coordinate X of the canvas to translate in longitude
	 * @param y coordinate Y of the canvas to translate in latitude
	 * @return a {@link Coordinates} with the latitude and longitude, related to the passed X and Y
	 */
	public final Coordinates projectionInvert(double x, double y) {
		return getChart().projectionInvert(x, y);
	}

	/**
	 * Translates X and Y coordinates of the canvas where the chart is drawn in latitude and longitude.
	 * 
	 * @param point contains X and Y coordinates to translate
	 * @return a {@link Coordinates} with the latitude and longitude, related to the passed X and Y
	 */
	public final Coordinates projectionInvert(CoordinatesPoint point) {
		return getChart().projectionInvert(point);
	}

	/**
	 * Translates latitude and longitude in coordinates of the canvas where the chart is drawn.
	 * 
	 * @param latitude latitude to use to get the Y point
	 * @param longitude longitude to use to get the X point
	 * @return a {@link CoordinatesPoint} with X and Y, related to the passed latitude and longitude
	 */
	public final CoordinatesPoint projection(double latitude, double longitude) {
		return getChart().projection(latitude, longitude);
	}

	/**
	 * Translates latitude and longitude in coordinates of the canvas where the chart is drawn.
	 * 
	 * @param coordinates contains latitude and longitude to translate
	 * @return a {@link CoordinatesPoint} with X and Y, related to the passed latitude and longitude
	 */
	public final CoordinatesPoint projection(Coordinates coordinates) {
		return getChart().projection(coordinates);
	}

	/**
	 * Registers the BUBBLEMAP controller in CHART.JS.
	 */
	public static void register() {
		// registers the controller
		BubbleMapChart.register();
	}

}