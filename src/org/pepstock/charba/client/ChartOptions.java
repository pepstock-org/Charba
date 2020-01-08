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
package org.pepstock.charba.client;

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.options.Scale;
import org.pepstock.charba.client.options.ScaledOptions;
import org.pepstock.charba.client.options.Scales;

/**
 * This object is mapping the default options related to the chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ChartOptions extends ScaledOptions {

	private final Type type;

	/**
	 * Creates the item using a native java script object which contains all properties and the default values to apply when the properties are not set.
	 * 
	 * @param type chart type
	 * @param nativeObject native java script object which contains all properties.
	 * @param defaultsOptions default options to apply to chart options
	 */
	ChartOptions(Type type, NativeObject nativeObject, IsDefaultScaledOptions defaultsOptions) {
		// the default of chart default ones are the CHART.JS one
		super(defaultsOptions, nativeObject);
		// checks consistency of type
		Type.checkIfValid(type);
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
		// checks if the chart is multi-scales
		if (ScaleType.MULTI.equals(type.scaleType())) {
			// returns the scales
			return super.getScales();
		}
		// if here, the chart is not multi scales therefore exception
		throw new UnsupportedOperationException("The options is not referring to a multi scaled chart!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.ScaledOptions#getScale()
	 */
	@Override
	public Scale getScale() {
		// checks if the chart is single-scales
		if (ScaleType.SINGLE.equals(type.scaleType())) {
			// returns the scale
			return super.getScale();
		}
		// if here, the chart is not single scale therefore exception
		throw new UnsupportedOperationException("The options is not referring to a single scaled chart!");
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	NativeObject getObject() {
		return super.getNativeObject();
	}

}
