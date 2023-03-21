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

/**
 * Abstract object for absolute and relative dataset index fill, which maintains common values.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 * @see RelativeDatasetIndexFill
 * @see AbsoluteDatasetIndexFill
 *
 */
abstract class AbstractDatasetIndexFill implements IsFill {
	// filling mode
	private final FillingMode mode;
	// index as integer, used by absolute dataset index
	private final int index;
	// index as string, used by relative dataset index
	private final String indexAsString;

	/**
	 * Creates the fill object with all necessary data.
	 * 
	 * @param mode filling mode. Only absolute or relative dataset index
	 * @param index dataset index as integer (only absolute dataset index)
	 * @param indexAsString dataset index as string (only relative dataset index)
	 */
	AbstractDatasetIndexFill(FillingMode mode, int index, String indexAsString) {
		this.mode = mode;
		this.index = index;
		this.indexAsString = indexAsString;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.enums.IsFill#getMode()
	 */
	@Override
	public final FillingMode getMode() {
		return mode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.enums.IsFill#getValueAsInt()
	 */
	@Override
	public int getValueAsInt() {
		return index;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.enums.IsFill#getValue()
	 */
	@Override
	public String getValue() {
		return indexAsString;
	}

}