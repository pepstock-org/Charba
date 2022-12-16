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
package org.pepstock.charba.client.defaults.globals;

import org.pepstock.charba.client.defaults.IsDefaultTime;
import org.pepstock.charba.client.enums.IsoWeekDay;
import org.pepstock.charba.client.enums.TimeUnit;

/**
 * CHART.JS default values for TIME element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class DefaultTime implements IsDefaultTime {

	/**
	 * To avoid any instantiation
	 */
	DefaultTime() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTime#getIsoWeekday()
	 */
	@Override
	public IsoWeekDay getIsoWeekday() {
		return IsoWeekDay.SUNDAY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTime#getRound()
	 */
	@Override
	public TimeUnit getRound() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTime#getTooltipFormat()
	 */
	@Override
	public String getTooltipFormat() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTime#getUnit()
	 */
	@Override
	public TimeUnit getUnit() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTime#getMinUnit()
	 */
	@Override
	public TimeUnit getMinUnit() {
		return TimeUnit.MILLISECOND;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTime#getParser()
	 */
	@Override
	public String getParser() {
		return null;
	}

}