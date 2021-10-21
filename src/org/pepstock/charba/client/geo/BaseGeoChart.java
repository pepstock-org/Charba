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
package org.pepstock.charba.client.geo;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.Charts;
import org.pepstock.charba.client.Controller;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.IsDatasetCreator;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.impl.plugins.ColorSchemes;
import org.pepstock.charba.client.impl.plugins.DatasetsItemsSelector;
import org.pepstock.charba.client.impl.plugins.HtmlLegend;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.resources.ResourceName;

/**
 * This is the base chart definition for GEO charts.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <D> data set type for the specific chart, extends BaseGeoDataset
 */
abstract class BaseGeoChart<D extends Dataset> extends AbstractChart implements IsDatasetCreator<D> {

	// controller instance
	private BaseGeoController geoController = null;
	// extending chart type
	// this is a custom implementation because
	// GEO controllers don't extend any existing chart type
	static final GeoExtendedChartType GEO_EXTENDED_CHART_TYPE = new GeoExtendedChartType();
	// maximum amount of datasets
	private static final int MAXIMUM_DATASETS_COUNT = 1;
	// maximum amount of axes
	private static final int MAXIMUM_AXES_COUNT = 2;

	/**
	 * Builds the chart.<br>
	 * This is must be extended for controller which are based on this type of chart.
	 * 
	 * @param type type of chart
	 */
	BaseGeoChart(Type type) {
		super(type);
	}

	/**
	 * Returns the controller type
	 * 
	 * @return the controller type
	 */
	abstract ControllerType getControllerType();

	/**
	 * Returns the controller instance or <code>null</code> if chart not initialized.
	 * 
	 * @return the controller instance
	 */
	final BaseGeoController getController() {
		return geoController;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChart#applyConfiguration()
	 */
	@Override
	protected final void applyConfiguration() {
		// because before creating chart options
		// new chart (by controller) must be defined
		// gets the controller type
		// could be choropleth or bubblemap
		ControllerType type = getControllerType();
		// registers the controller
		// if not register
		// checks for GEO controller
		if (type.register() && geoController == null) {
			// if here the controller is registered
			// then gets it
			Controller controllerInstance = Defaults.get().getControllers().getController(type);
			// checks if controller is a base GEO controller
			Checker.assertCheck(controllerInstance instanceof BaseGeoController, "Controller stored for " + getControllerType().value() + " is not a BaseGeoController");
			// casts to GEO controller
			geoController = (BaseGeoController) controllerInstance;
		}
		// gets GEO options
		BaseGeoOptions options;
		// checks if options is a GEO one
		Checker.assertCheck(getOptions() instanceof BaseGeoOptions, "Chart options are not a BaseGeoOptions instance");
		// cats and store
		options = (BaseGeoOptions) getOptions();
		// disables legend
		options.getLegend().setDisplay(false);
		// disables plugins which can not work with this controller.
		getOptions().getPlugins().setEnabled(ResourceName.LABELS_PLUGIN.value(), false);
		getOptions().getPlugins().setEnabled(ResourceName.ZOOM_PLUGIN.value(), false);
		getOptions().getPlugins().setEnabled(ResourceName.ANNOTATION_PLUGIN.value(), false);
		getOptions().getPlugins().setEnabled(HtmlLegend.ID, false);
		getOptions().getPlugins().setEnabled(DatasetsItemsSelector.ID, false);
		getOptions().getPlugins().setEnabled(ColorSchemes.ID, false);
	}

	/**
	 * Translates latitude and longitude in coordinates of the canvas where the chart is drawn.
	 * 
	 * @param latitude latitude to use to get the Y point
	 * @param longitude longitude to use to get the X point
	 * @return a {@link CoordinatesPoint} with X and Y, related to the passed latitude and longitude
	 */
	public final CoordinatesPoint projection(double latitude, double longitude) {
		// checks if arguments are consistent
		// and is chart is initialized
		if (Charts.hasNative(this) && Undefined.isNot(longitude) && Undefined.isNot(latitude)) {
			// gets the native charts
			Chart nativeChart = Charts.getNative(this);
			// invokes the transformation
			ArrayDouble result = JsGeoHelper.get().projection(nativeChart, latitude, longitude);
			// if the result is not consistent
			if (result != null && result.length() == 2) {
				// returns the point
				return new CoordinatesPoint(result.get(0), result.get(1));
			}
		}
		// if here, arguments are not consistent or invocation is not consistent
		// then returns an object with inconsistent point
		return new CoordinatesPoint(Undefined.DOUBLE, Undefined.DOUBLE);
	}

	/**
	 * Translates latitude and longitude in coordinates of the canvas where the chart is drawn.
	 * 
	 * @param coordinates contains latitude and longitude to translate
	 * @return a {@link CoordinatesPoint} with X and Y, related to the passed latitude and longitude
	 */
	public final CoordinatesPoint projection(Coordinates coordinates) {
		// checks if argument is consistent
		if (coordinates != null && coordinates.isConsistent()) {
			return projection(coordinates.getLatitude(), coordinates.getLongitude());
		}
		// if here, argument is not consistent
		// then returns an object with inconsistent point
		return new CoordinatesPoint(Undefined.DOUBLE, Undefined.DOUBLE);
	}

	/**
	 * Translates X and Y coordinates of the canvas where the chart is drawn in latitude and longitude.
	 * 
	 * @param x coordinate X of the canvas to translate in longitude
	 * @param y coordinate Y of the canvas to translate in latitude
	 * @return a {@link Coordinates} with the latitude and longitude, related to the passed X and Y
	 */
	public final Coordinates invert(double x, double y) {
		// checks if arguments are consistent
		// and is chart is initialized
		if (Charts.hasNative(this) && Undefined.isNot(y) && Undefined.isNot(x)) {
			// gets the native charts
			Chart nativeChart = Charts.getNative(this);
			// invokes the transformation
			ArrayDouble result = JsGeoHelper.get().invert(nativeChart, x, y);
			// if the result is not consistent
			if (result != null && result.length() == 2) {
				// returns the point
				return new Coordinates(result.get(1), result.get(0));
			}
		}
		// if here, arguments not consistent or invocation is not consistent
		// then returns an object with inconsistent coordinates
		return new Coordinates(Undefined.DOUBLE, Undefined.DOUBLE);
	}

	/**
	 * Translates X and Y coordinates of the canvas where the chart is drawn in latitude and longitude.
	 * 
	 * @param point contains X and Y coordinates to translate
	 * @return a {@link Coordinates} with the latitude and longitude, related to the passed X and Y
	 */
	public final Coordinates invert(CoordinatesPoint point) {
		// checks if argument is consistent
		if (point != null && point.isConsistent()) {
			return invert(point.getX(), point.getY());
		}
		// if here, argument is not consistent
		// then returns an object with inconsistent coordinates
		return new Coordinates(Undefined.DOUBLE, Undefined.DOUBLE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChart#getDatasetsCount()
	 */
	@Override
	protected final int getMaximumDatasetsCount() {
		// maximum datasets
		return MAXIMUM_DATASETS_COUNT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChart#getMaximumAxesCount()
	 */
	@Override
	protected final int getMaximumAxesCount() {
		return MAXIMUM_AXES_COUNT;
	}

}