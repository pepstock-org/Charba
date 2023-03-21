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
package org.pepstock.charba.client.enums;

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.items.Undefined;

/**
 * Fill object to configure chart to use an absolute dataset index.<br>
 * Absolute dataset index, as integer, is composed by integer value which must be greater than 0.<br>
 * Here are same examples: (1,2,3,...).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class AbsoluteDatasetIndexFill extends AbstractDatasetIndexFill {

	/**
	 * Default value for absolute dataset index, <b>{@value DEFAULT_VALUE_AS_INT}</b>.
	 */
	public static final int DEFAULT_VALUE_AS_INT = 0;
	// the name of fill
	private final String name;

	/**
	 * Creates a absolute dataset index fill using the absolute position.
	 * 
	 * @param index dataset index set by the absolute position.
	 */
	AbsoluteDatasetIndexFill(int index) {
		// creates the abstract object passing the filling mode (always absolute filling mode
		// and undefined string for index as string (absolute ONLY integer)
		super(FillingMode.ABSOLUTE_DATASET_INDEX, index, Undefined.STRING);
		// checks if the index is greater than 0
		Checker.checkIfGreaterThan(index, 1, "Index argument");
		// creates the name to return
		this.name = FillingMode.ABSOLUTE_DATASET_INDEX.value() + ":" + index;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return name;
	}

}