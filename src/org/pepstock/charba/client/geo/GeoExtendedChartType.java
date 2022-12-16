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

import org.pepstock.charba.client.ScaleType;
import org.pepstock.charba.client.Type;

/**
 * This is a fake controller in order to set it as extended chart type for GEO charts.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class GeoExtendedChartType implements Type {

	// name of this extended type
	private static final String TYPE = "geo";

	/**
	 * To avoid any instantiation
	 */
	GeoExtendedChartType() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Type#scaleType()
	 */
	@Override
	public ScaleType scaleType() {
		return ScaleType.MULTI;
	}

}