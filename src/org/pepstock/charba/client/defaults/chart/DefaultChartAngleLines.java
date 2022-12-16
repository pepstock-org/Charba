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

import java.util.List;

import org.pepstock.charba.client.defaults.IsDefaultAngleLines;
import org.pepstock.charba.client.options.AngleLines;

/**
 * Defaults for angle lines option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartAngleLines implements IsDefaultAngleLines {

	private final AngleLines angleLines;

	/**
	 * Creates the object by angle lines option element instance.
	 * 
	 * @param angleLines option element instance.
	 */
	DefaultChartAngleLines(AngleLines angleLines) {
		this.angleLines = angleLines;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAngleLines#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return angleLines.isDisplay();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAngleLines#getColorAsString()
	 */
	@Override
	public String getColorAsString() {
		return angleLines.getColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAngleLines#getLineWidth()
	 */
	@Override
	public int getLineWidth() {
		return angleLines.getLineWidth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAngleLines#getBorderDashOffset()
	 */
	@Override
	public double getBorderDashOffset() {
		return angleLines.getBorderDashOffset();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaleLines#getBorderDash()
	 */
	@Override
	public List<Integer> getBorderDash() {
		return angleLines.getBorderDash();
	}

}