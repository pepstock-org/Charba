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
package org.pepstock.charba.client.defaults.chart;

import org.pepstock.charba.client.defaults.IsDefaultGrid;
import org.pepstock.charba.client.options.Grid;

/**
 * Defaults for grid option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartGrid implements IsDefaultGrid {

	private final Grid grid;

	/**
	 * Creates the object by grid option element instance.
	 * 
	 * @param grid grid option element instance.
	 */
	DefaultChartGrid(Grid grid) {
		this.grid = grid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGrid#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return grid.isDisplay();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGrid#getColorAsString()
	 */
	@Override
	public String getColorAsString() {
		return grid.getColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGrid#getLineWidth()
	 */
	@Override
	public int getLineWidth() {
		return grid.getLineWidth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGrid#isDrawOnChartArea()
	 */
	@Override
	public boolean isDrawOnChartArea() {
		return grid.isDrawOnChartArea();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGrid#isDrawTicks()
	 */
	@Override
	public boolean isDrawTicks() {
		return grid.isDrawTicks();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGrid#getTickLength()
	 */
	@Override
	public int getTickLength() {
		return grid.getTickLength();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGrid#isOffset()
	 */
	@Override
	public boolean isOffset() {
		return grid.isOffset();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGrid#isCircular()
	 */
	@Override
	public boolean isCircular() {
		return grid.isCircular();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGrid#getZ()
	 */
	@Override
	public int getZ() {
		return grid.getZ();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGrid#getTickBorderDashOffset()
	 */
	@Override
	public double getTickBorderDashOffset() {
		return grid.getTickBorderDashOffset();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGrid#getTickColorAsString()
	 */
	@Override
	public String getTickColorAsString() {
		return grid.getTickColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultGrid#getTickWidth()
	 */
	@Override
	public int getTickWidth() {
		return grid.getTickWidth();
	}

}