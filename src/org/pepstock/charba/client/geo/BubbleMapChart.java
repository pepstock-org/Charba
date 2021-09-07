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

import org.pepstock.charba.client.configuration.Axis;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.data.Dataset;

/**
 * A Bubble Map, as known as Proportional Symbol is used to render maps with dots that are scaled according to some numerical value.<br>
 * It is based on a regular bubble chart where the positioning is done using latitude and longitude to create a legend for the different radi.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class BubbleMapChart extends BaseGeoChart<BubbleMapDataset> {

	/**
	 * Name of chart type <b>{@value TYPE}</b>
	 */
	public static final String TYPE = "bubbleMap";
	/**
	 * <b>BubbleMap</b> controller type.
	 */
	public static final ControllerType CONTROLLER_TYPE = new ControllerType(TYPE, BaseGeoChart.GEO_EXTENDED_CHART_TYPE, BaseGeoController.PROVIDER);
	// chart options
	private final BubbleMapOptions options;

	/**
	 * Builds the object.
	 */
	public BubbleMapChart() {
		super(CONTROLLER_TYPE);
		// creates options
		options = new BubbleMapOptions(this, getDefaultChartOptions());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.IsChart#getOptions()
	 */
	@Override
	public BubbleMapOptions getOptions() {
		return options;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChart#newDataset(boolean)
	 */
	@Override
	public BubbleMapDataset newDataset(boolean hidden) {
		// hidden is ignored because a bubble map chart has got only 1 data set
		return new BubbleMapDataset();
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
		return dataset instanceof BubbleMapDataset;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.HasAxes#checkAxis(org.pepstock.charba.client.configuration.Axis)
	 */
	@Override
	public boolean checkAxis(Axis axis) {
		return axis instanceof ProjectionAxis || axis instanceof IsSizeAxis;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.AbstractChart#checkAxes(org.pepstock.charba.client.configuration.Axis[])
	 */
	@Override
	public void checkAxes(Axis... axes) {
		// checks if the axis are consistent
		// bubble map accepts ONLY size and projection axes
		if (axes != null) {
			// invokes the common checking
			super.checkAxes(axes);
			// creates flags
			boolean size = false;
			boolean projection = false;
			// scans axes
			for (Axis axis : axes) {
				// checks if projection
				if (axis instanceof ProjectionAxis && !projection) {
					projection = true;
				} else if (axis instanceof IsSizeAxis && !size) {
					size = true;
				} else {
					// if here, the set axes are not consistent
					throw new IllegalArgumentException("Axes argument is not consistent: BubbleMap can have only 1 projection and 1 size axes");
				}
			}
		}
	}

}