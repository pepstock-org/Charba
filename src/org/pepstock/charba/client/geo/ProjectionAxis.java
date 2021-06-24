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

import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.configuration.Axis;
import org.pepstock.charba.client.configuration.AxisType;
import org.pepstock.charba.client.controllers.ControllerMapperFactory;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.enums.AxisKind;
import org.pepstock.charba.client.enums.ScaleDataType;
import org.pepstock.charba.client.geo.enums.Projection;
import org.pepstock.charba.client.options.ScaleId;

/**
 * A map projection is a way to flatten a globe's surface into a plane in order to make a map.<br>
 * This requires a systematic transformation of the latitudes and longitudes of locations from the surface of the globe into locations on a plane.<br>
 * This is the scale which is managing the map projection.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ProjectionAxis extends Axis {

	/**
	 * Projection axis id.
	 */
	public static final ScaleId ID = ScaleId.create("xy");
	/**
	 * Projection axis type.
	 */
	public static final AxisType TYPE = AxisType.create("projection", null, ID, ScaleDataType.NUMBER);
	// projection mapper factory
	private final ProjectionAxisRemappedOptionsFactory factory;
	// projection mapper
	private ProjectionAxisMapper mapper;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param nativeObject native object to map java script properties
	 */
	public ProjectionAxis(IsChart chart) {
		super(chart, ID, TYPE, AxisKind.X);
		// gets the controller type
		ControllerType chartType = GeoUtils.checkAndGetControllerType(chart);
		// creates the factory
		this.factory = new ProjectionAxisRemappedOptionsFactory(chartType);
		// initializes the mapper
		afterAxisConfigurationUpdate();
	}

	/**
	 * Reloads the extended scale
	 */
	void afterAxisConfigurationUpdate() {
		// creates mapper
		this.mapper = getConfiguration().getRemappedOptions(factory);
	}

	/**
	 * Sets a map projection which is a way to flatten a globe's surface into a plane in order to make a map.
	 * 
	 * @param projection a map projection which is a way to flatten a globe's surface into a plane in order to make a map
	 */
	public void setProjection(Projection projection) {
		mapper.setProjection(projection);
	}

	/**
	 * Returns a map projection which is a way to flatten a globe's surface into a plane in order to make a map.
	 * 
	 * @return a map projection which is a way to flatten a globe's surface into a plane in order to make a map
	 */
	public Projection getProjection() {
		return mapper.getProjection();
	}

	/**
	 * Sets how much the map will be scaled.
	 * 
	 * @param projectionScale how much the map will be scaled
	 */
	public void setProjectionScale(double projectionScale) {
		mapper.setProjectionScale(projectionScale);
	}

	/**
	 * Returns how much the map will be scaled.
	 * 
	 * @return how much the map will be scaled
	 */
	public double getProjectionScale() {
		return mapper.getProjectionScale();
	}

	/**
	 * Sets a map projection offset value.
	 * 
	 * @param x x offset where the map has been placed
	 * @param y y offset where the map has been placed
	 */
	public void setProjectionOffset(double x, double y) {
		mapper.setProjectionOffset(x, y);
	}

	/**
	 * Returns a map projection offset value.
	 * 
	 * @return a map projection offset value.
	 */
	public List<Double> getProjectionOffset() {
		return mapper.getProjectionOffset();
	}

	/**
	 * Can create a options mapper in order to re-map the CHART.JS options where needed in order to add additional properties and nodes for GEO charts.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class ProjectionAxisRemappedOptionsFactory extends ControllerMapperFactory<ProjectionAxisMapper> {

		/**
		 * Creates the factory of the mapper
		 * 
		 * @param type type of GEO chart
		 */
		private ProjectionAxisRemappedOptionsFactory(ControllerType chartType) {
			super(chartType);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public ProjectionAxisMapper create(NativeObject nativeObject) {
			return new ProjectionAxisMapper(nativeObject);
		}

	}

}
