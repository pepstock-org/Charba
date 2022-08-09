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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.enums.AxisKind;
import org.pepstock.charba.client.enums.DefaultScaleId;

/**
 * This is a standard implementation of a scale id
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class StandardScaleId extends AbstractStandardKey implements ScaleId {

	// default axis kind
	private final AxisKind axisKind;

	/**
	 * Builds the object with the scale id value as string
	 * 
	 * @param value value of key as String
	 */
	StandardScaleId(String value) {
		super(value);
		// checks if argument is consistent
		ScaleId.checkIfValid(this);
		// gets the default axis kind
		this.axisKind = DefaultScaleId.getAxisKindByScaleId(value, AxisKind.X);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.ScaleId#getAxisKind()
	 */
	@Override
	public AxisKind getAxisKind() {
		return axisKind;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.AbstractStandardKey#hashCode()
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.AbstractStandardKey#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
