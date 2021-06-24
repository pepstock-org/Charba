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

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.data.BubbleDataset;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.defaults.IsDefaultOptions;

/**
 * The choropleth data set allows a region definition (by {@link Feature}) and specific value to be specified.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ChoroplethDataset extends BubbleDataset implements IsGeoDataset<ChoroplethDataPoint> {

	// data point factory
	private static final ChoroplethDataPointFactory FACTORY = new ChoroplethDataPointFactory();
	// dataset options handler instance
	private final GeoDatasetHandler<ChoroplethDataPoint> handler;

	/**
	 * Creates a data set.<br>
	 * It uses the global options has default.
	 */
	public ChoroplethDataset() {
		this(Defaults.get().getGlobal());
	}

	/**
	 * Creates a data set setting the defaults value.
	 * 
	 * @param defaultValues default options
	 */
	public ChoroplethDataset(IsDefaultOptions defaultValues) {
		this(ChoroplethChart.CONTROLLER_TYPE, defaultValues);
	}

	/**
	 * Creates a data set by defaults value and the controller type.
	 * 
	 * @param type controller type related to the data set
	 * @param defaultValues default options
	 */
	ChoroplethDataset(ControllerType type, IsDefaultOptions defaultValues) {
		super(type, defaultValues, Dataset.DEFAULT_HIDDEN);
		// creates handler
		this.handler = new GeoDatasetHandler<>(getNativeObject(), ChoroplethOptionsMapper.DEFAULT_CLIP_MAP, FACTORY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.geo.IsGeoDataset#getHandler()
	 */
	@Override
	public GeoDatasetHandler<ChoroplethDataPoint> getHandler() {
		return handler;
	}

	/**
	 * Throws an exception because not available.
	 * 
	 * @param values ignored because will throw an exception
	 */
	@Override
	public void setData(double... values) {
		throw new UnsupportedOperationException(GeoDatasetHandler.INVALID_SET_DATA_CALL);
	}

	/**
	 * Throws an exception because not available.
	 * 
	 * @param values ignored because will throw an exception
	 */
	@Override
	public void setData(List<Double> values) {
		throw new UnsupportedOperationException(GeoDatasetHandler.INVALID_SET_DATA_CALL);
	}

	/**
	 * Throws an exception because not available.
	 * 
	 * @param binding ignored because will throw an exception
	 * @return nothing because will throw an exception
	 */
	@Override
	public List<Double> getData(boolean binding) {
		throw new UnsupportedOperationException(GeoDatasetHandler.INVALID_GET_DATA_CALL);
	}

	/**
	 * Factory to create a data point from a native object, used for array container lists.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static class ChoroplethDataPointFactory implements NativeObjectContainerFactory<ChoroplethDataPoint> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client. commons.NativeObject)
		 */
		@Override
		public ChoroplethDataPoint create(NativeObject nativeObject) {
			return new ChoroplethDataPoint(nativeObject);
		}

	}

}