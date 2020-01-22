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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.ScaleType;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;
import org.pepstock.charba.client.options.ScaledOptions;

/**
 * Wrapper of options node of CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class OptionsNode extends ScaledOptions {

	// stores chart or controller type
	private final Type type;

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param type chart or controller type
	 * @param nativeObject native java script object which contains all properties.
	 */
	public OptionsNode(Type type, NativeObject nativeObject) {
		super(DefaultsBuilder.get().getScaledOptions(), nativeObject);
		// checks if type is consistent
		Type.checkIfValid(type);
		// stores
		this.type = type;
	}

	/**
	 * Returns the chart type.
	 * 
	 * @return the chart type
	 */
	public final Type getType() {
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultScaledOptions#getScaleType()
	 */
	@Override
	public ScaleType getScaleType() {
		return type.scaleType();
	}

}
