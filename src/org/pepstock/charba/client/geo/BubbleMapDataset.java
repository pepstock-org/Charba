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

import java.util.List;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.data.BubbleDataset;
import org.pepstock.charba.client.data.DataPoint;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.defaults.IsDefaultOptions;

/**
 * It is a {@link BubbleDataset} with additional options for bubble map charts.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class BubbleMapDataset extends BubbleDataset implements IsGeoDataset<BubbleMapDataPoint> {

	// exception string message for setting data
	private static final String INVALID_SET_DATA_POINTS_CALL = "'setDataPoints' method is not invokable by a bubble map chart. Use 'setValues' method";
	// exception string message for getting data
	private static final String INVALID_GET_DATA_POINTS_CALL = "'getDataPoints' method is not invokable by a bubble map chart. Use 'getValues' method";
	// data point factory
	private static final BubbleMapDataPointFactory FACTORY = new BubbleMapDataPointFactory();
	// data set options handler instance
	private final GeoDatasetHandler<BubbleMapDataPoint> handler;

	/**
	 * Creates a data set.<br>
	 * It uses the global options has default.
	 */
	public BubbleMapDataset() {
		this(Defaults.get().getGlobal());
	}

	/**
	 * Creates a data set setting the defaults value.
	 * 
	 * @param defaultValues default options
	 */
	public BubbleMapDataset(IsDefaultOptions defaultValues) {
		this(BubbleMapChart.CONTROLLER_TYPE, defaultValues);
	}

	/**
	 * Creates a data set by defaults value and the controller type.
	 * 
	 * @param type controller type related to the data set
	 * @param defaultValues default options
	 */
	BubbleMapDataset(ControllerType type, IsDefaultOptions defaultValues) {
		super(type, defaultValues, Dataset.DEFAULT_HIDDEN);
		// creates handler
		this.handler = new GeoDatasetHandler<>(getNativeObject(), FACTORY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.geo.IsGeoDataset#getHandler()
	 */
	@Override
	public GeoDatasetHandler<BubbleMapDataPoint> getHandler() {
		return handler;
	}

	/**
	 * Throws an exception because not available.
	 * 
	 * @param datapoints ignored because will throw an exception
	 */
	@Override
	public void setDataPoints(DataPoint... datapoints) {
		throw new UnsupportedOperationException(INVALID_SET_DATA_POINTS_CALL);
	}

	/**
	 * Throws an exception because not available.
	 * 
	 * @param datapoints ignored because will throw an exception
	 */
	@Override
	public void setDataPoints(List<DataPoint> datapoints) {
		throw new UnsupportedOperationException(INVALID_SET_DATA_POINTS_CALL);
	}

	/**
	 * Throws an exception because not available.
	 * 
	 * @param binding ignored because will throw an exception
	 * @return nothing because will throw an exception
	 */
	@Override
	public List<DataPoint> getDataPoints(boolean binding) {
		throw new UnsupportedOperationException(INVALID_GET_DATA_POINTS_CALL);
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
	private static class BubbleMapDataPointFactory implements NativeObjectContainerFactory<BubbleMapDataPoint> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client. commons.NativeObject)
		 */
		@Override
		public BubbleMapDataPoint create(NativeObject nativeObject) {
			return new BubbleMapDataPoint(nativeObject);
		}

	}

}