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

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.defaults.IsDefaultOptions;

/**
 * FIXME The Meter chart allows a number of properties to be specified for each dataset. These are used to set display properties for a specific dataset.<br>
 * The minimum value of data is 0 (see {@link ChoroplethDataset#MINIMUM_VALUE}).<br>
 * The dataset will have always 2 data and setting the color of data, the first is the value color and the second is the empty one.<br>
 * To set the data, is mandatory to use {@link ChoroplethDataset#setValue(double)}) method.
 * 
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ChoroplethDataset extends BaseGeoDataset {

	// common options handler instance
	private final CommonOptionsHandler optionsHandler;

	/**
	 * Creates a dataset. It uses the global options has default.
	 */
	public ChoroplethDataset() {
		this(Defaults.get().getGlobal());
	}

	/**
	 * Creates a dataset setting the defaults value.
	 * 
	 * @param defaultValues default options
	 */
	public ChoroplethDataset(IsDefaultOptions defaultValues) {
		this(ChoroplethChart.CONTROLLER_TYPE, defaultValues);
	}

	/**
	 * Creates a dataset setting the maximum value of dataset and defaults value and the controller type in case of extension
	 * 
	 * @param type controller type related to the dataset
	 * @param defaultValues default options
	 */
	ChoroplethDataset(ControllerType type, IsDefaultOptions defaultValues) {
		super(type, defaultValues);
		// creates handler
		this.optionsHandler = new CommonOptionsHandler(getNativeObject(), ChoroplethOptionsMapper.DEFAULT_CLIP_MAP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.geo.HasCommonOptions#getCommonOptionsHandler()
	 */
	@Override
	public CommonOptionsHandler getCommonOptionsHandler() {
		return optionsHandler;
	}

}