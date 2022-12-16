/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.geo;

import org.pepstock.charba.client.configuration.Axis;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.data.Dataset;

/**
 * A choropleth chart is used to render maps with the area filled according to some numerical value.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ChoroplethChart extends BaseGeoChart<ChoroplethDataset> {

	/**
	 * Name of chart type <b>{@value TYPE}</b>
	 */
	public static final String TYPE = "choropleth";
	/**
	 * <b>Choropleth</b> controller type.
	 */
	public static final ControllerType CONTROLLER_TYPE = new ControllerType(TYPE, BaseGeoChart.GEO_EXTENDED_CHART_TYPE, BaseGeoController.PROVIDER);
	// chart options
	private final ChoroplethOptions options;

	/**
	 * Builds the object.
	 */
	public ChoroplethChart() {
		super(CONTROLLER_TYPE);
		// creates options
		options = new ChoroplethOptions(this, getDefaultChartOptions());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getOptions()
	 */
	@Override
	public ChoroplethOptions getOptions() {
		return options;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChart#newDataset(boolean)
	 */
	@Override
	public ChoroplethDataset newDataset(boolean hidden) {
		// hidden is ignored because a choropleth chart has got only 1 data set
		return new ChoroplethDataset();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.geo.BaseGeoChart#getControllerType()
	 */
	@Override
	ControllerType getControllerType() {
		return CONTROLLER_TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChart#checkDataset(org.pepstock.charba.client.data.Dataset)
	 */
	@Override
	protected boolean checkDataset(Dataset dataset) {
		return dataset instanceof ChoroplethDataset;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.HasAxes#checkAxis(org.pepstock.charba.client.configuration.Axis)
	 */
	@Override
	public boolean checkAxis(Axis axis) {
		return axis instanceof ProjectionAxis || axis instanceof IsColorAxis;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChart#checkAxes(org.pepstock.charba.client.configuration.Axis[])
	 */
	@Override
	public void checkAxes(Axis... axes) {
		// checks if the axis are consistent
		// choropleth accepts ONLY color and projection axes
		if (axes != null) {
			// invokes the common checking
			super.checkAxes(axes);
			// creates flags
			boolean color = false;
			boolean projection = false;
			// scans axes
			for (Axis axis : axes) {
				// checks if projection
				if (axis instanceof ProjectionAxis && !projection) {
					projection = true;
				} else if (axis instanceof IsColorAxis && !color) {
					color = true;
				} else {
					// if here, the set axes are not consistent
					throw new IllegalArgumentException("Axes argument is not consistent: Choropleth can have only 1 projection and 1 color axes");
				}
			}
		}
	}

	/**
	 * Registers the CHOROPLETH controller in CHART.JS.
	 */
	public static void register() {
		// registers the controller
		CONTROLLER_TYPE.register();
	}
}