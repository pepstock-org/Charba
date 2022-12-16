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
package org.pepstock.charba.client;

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.controllers.ControllerType;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.options.ScaledOptions;
import org.pepstock.charba.client.options.Scales;
import org.pepstock.charba.client.utils.Utilities;

/**
 * This object is mapping the default options related to the chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ChartOptions extends ScaledOptions {

	// exception pattern when the scale or scales methods is invoked and the scale type is not correct
	private static final String INVALID_SCALE_TYPE = "The options is referring to {0} chart with {1} scale type and not to MULTI/SINGLE scale type as requested";

	private final Type type;

	/**
	 * Creates the item using a native java script object which contains all properties and the default values to apply when the properties are not set.
	 * 
	 * @param type chart type
	 * @param nativeObject native java script object which contains all properties.
	 * @param defaultOptions default options to apply to chart options
	 */
	ChartOptions(Type type, NativeObject nativeObject, IsDefaultScaledOptions defaultOptions) {
		// the default of chart default ones are the CHART.JS one
		// checks consistency of type
		super(createScope(type), defaultOptions, nativeObject, true);
		// stores type
		this.type = type;
	}

	/**
	 * Returns the chart type.
	 * 
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.ScaledOptions#getScales()
	 */
	@Override
	public Scales getScales() {
		// checks if the chart is no-scales
		if (ScaleType.NONE.equals(type.scaleType())) {
			// if here, the chart is none scales therefore exception
			throw new UnsupportedOperationException(Utilities.applyTemplate(INVALID_SCALE_TYPE, type.value(), type.scaleType().name()));
		}
		// returns the scales
		return super.getScales();
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	NativeObject nativeObject() {
		return super.getNativeObject();
	}

	/**
	 * Creates a key for the chart options.<br>
	 * The format is the following:<br>
	 * <br>
	 * <code>chart-[chartType]</code><br>
	 * <br>
	 * where chart type is the type of the chart, see {@link ChartType} or {@link ControllerType}.
	 * 
	 * @param type the type of the chart
	 * @return a key for the chart options
	 */
	private static final String createScope(Type type) {
		// creates a string builder
		StringBuilder sb = new StringBuilder("chart-");
		// formats the key and returns it
		return sb.append(Type.checkAndGetIfValid(type).value()).toString();
	}

}