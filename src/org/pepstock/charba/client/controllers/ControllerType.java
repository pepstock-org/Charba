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
package org.pepstock.charba.client.controllers;

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.ScaleType;
import org.pepstock.charba.client.Type;

/**
 * Represent the type of new controller. Must be created for every controller implementation.<br>
 * It can be created extending an existing chart type or new type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ControllerType implements Type {
	// type of chart
	private final String type;
	// type of scale
	private final ScaleType scaleType;
	// type of extended chart
	private final ChartType chartType;

	/**
	 * Creates new chart type with scale type {@link ScaleType#multi} as default.
	 * 
	 * @param type new chart type as string.
	 */
	public ControllerType(String type) {
		this(type, ScaleType.multi);
	}

	/**
	 * Creates new chart type based on existing chart type, as extension.<br>
	 * Scale type is the existing chart one.
	 * 
	 * @param type new chart type as string.
	 * @param chartType existing chart type, as extension.
	 */
	public ControllerType(String type, ChartType chartType) {
		this(type, chartType, chartType.scaleType());
	}

	/**
	 * Creates new chart type with a specific scale type.
	 * 
	 * @param type new chart type as string.
	 * @param scaleType scale type of new chart.
	 */
	public ControllerType(String type, ScaleType scaleType) {
		this(type, null, scaleType);
	}

	/**
	 * Internal constructor which creates new chart.
	 * 
	 * @param type new chart type as string.
	 * @param chartType existing chart type, as extension or <code>null</code> if new chart does not extend an existing chart.
	 * @param scaleType scale type of new chart.
	 */
	private ControllerType(String type, ChartType chartType, ScaleType scaleType) {
		this.type = type;
		this.chartType = chartType;
		this.scaleType = scaleType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.commons.Key#name()
	 */
	@Override
	public String name() {
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.Type#scaleType()
	 */
	@Override
	public ScaleType scaleType() {
		return scaleType;
	}

	/**
	 * Returns the extended chart type of controller, otherwise <code>null</code>.
	 * 
	 * @return the extended chart type of controller, otherwise <code>null</code>
	 */
	public ChartType getChartType() {
		return chartType;
	}

	/**
	 * Returns <code>true</code> if this controller is extending an existing chart.
	 * 
	 * @return <code>true</code> if this controller is extending an existing chart, otherwise <code>false</code>.
	 */
	public boolean isExtended() {
		return chartType != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Type) {
			Type objType = (Type) obj;
			if (objType.name() != null && type != null) {
				return type.equals(objType.name());
			}
		}
		return false;
	}

}
