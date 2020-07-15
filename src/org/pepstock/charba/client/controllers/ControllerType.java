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
 * It can be created ONLY extending an existing chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ControllerType implements Type {
	// type of chart
	private final String type;
	// type of extended chart
	private final Type chartType;

	/**
	 * Creates new chart type based on existing chart type, as extension.<br>
	 * Scale type is the existing chart one.
	 * 
	 * @param type new chart type as string.
	 * @param chartType existing chart type, as extension.
	 */
	public ControllerType(String type, Type chartType) {
		// checks type if consistent
		if (type == null) {
			throw new IllegalArgumentException("Type is null");
		}
		// scans all defualt chart type
		// because a controller type can non called as a default one
		for (ChartType defaultChartType : ChartType.values()) {
			// checks if the type is equals of any chart type
			if (defaultChartType.value().equalsIgnoreCase(type)) {
				// if equals exception
				throw new IllegalArgumentException("Type '" + type + "' is a default chart type");
			}
		}
		// checks chart type if is consistent
		Type.checkIfValid(chartType);
		this.type = type;
		this.chartType = chartType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Type#scaleType()
	 */
	@Override
	public ScaleType scaleType() {
		return chartType.scaleType();
	}

	/**
	 * Returns the extended chart type of controller.
	 * 
	 * @return the extended chart type of controller
	 */
	public Type getChartType() {
		return chartType;
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
		// checks if the object is a type
		if (obj instanceof Type) {
			// casts
			Type objType = (Type) obj;
			// checks if there are consistent
			if (objType.value() != null && type != null) {
				return type.equals(objType.value());
			}
		}
		return false;
	}

}
