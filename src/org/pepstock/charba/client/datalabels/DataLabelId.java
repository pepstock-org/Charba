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
package org.pepstock.charba.client.datalabels;

import org.pepstock.charba.client.commons.PropertyKey;

/**
 * Represents the label id of a label configuration object in the {@link DataLabelsPlugin#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface DataLabelId extends PropertyKey {

	/**
	 * Returns a key instance by its string value.
	 * 
	 * @param id string value to use
	 * @return new label configuration object id instance
	 */
	static DataLabelId create(String id) {
		// checks if passed id is consistent
		PropertyKey.checkIfValid(id);
		// creates new label id
		return new StandardDataLabelId(id);
	}

	/**
	 * Returns <code>true</code> if id passed as argument is not <code>null</code> and its value is not <code>null</code> as well and could be a valid label configuration object
	 * id.
	 * 
	 * @param id label configuration object id to be checked
	 * @return <code>true</code> if id passed as argument is not <code>null</code> and its value is not <code>null</code> as well and could be a valid label configuration object
	 *         id.
	 */
	static boolean isValid(DataLabelId id) {
		return PropertyKey.isValid(id);
	}

	/**
	 * Checks if id passed as argument is not <code>null</code> and its value is not <code>null</code> as well and could be a valid label configuration object id.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param id label configuration object id to be checked
	 */
	static void checkIfValid(DataLabelId id) {
		PropertyKey.checkIfValid(id);
	}

}