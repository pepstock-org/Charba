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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.ScaleDataType;
import org.pepstock.charba.client.options.ScaleId;

/**
 * This is a standard implementation of a axis type
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class StandardAxisType implements AxisType {

	// type of scale
	private final String type;
	// type of extended scale
	private final AxisType baseType;
	// default scale id
	private final ScaleId defaultScaleId;
	// data type of scale
	private final ScaleDataType dataType;

	/**
	 * Builds the object with the scale axis value as string
	 * 
	 * @param type value of property name
	 * @param baseType base type (extended) for axis type
	 * @param defaultScaleId default scale id for this axis type
	 * @param dataType type of data which the scale can manage
	 */
	StandardAxisType(String type, AxisType baseType, ScaleId defaultScaleId, ScaleDataType dataType) {
		this.type = type;
		this.defaultScaleId = defaultScaleId;
		this.dataType = dataType;
		this.baseType = AxisType.isValid(baseType) ? baseType : null;
		// checks if argument is consistent
		AxisType.checkIfValid(this);
		// stores the type
		AxisType.register(this);
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
	 * @see org.pepstock.charba.client.configuration.AxisType#getDefaultScaleId()
	 */
	@Override
	public ScaleId getDefaultScaleId() {
		return defaultScaleId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AxisType#getDataType()
	 */
	@Override
	public ScaleDataType getDataType() {
		return dataType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AxisType#getBaseType()
	 */
	@Override
	public AxisType getBaseType() {
		return baseType == null ? this : baseType;
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
		// checks if is the same object
		if (this == obj) {
			return true;
		}
		// checks if argument is null
		if (obj == null) {
			return false;
		}
		// checks if the class is the same
		if (getClass() != obj.getClass()) {
			return false;
		}
		// casts to a standard tpe
		AxisType other = (AxisType) obj;
		// checks if keys are equals
		return Key.equals(this, other);
	}

}