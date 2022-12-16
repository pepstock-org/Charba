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
package org.pepstock.charba.client.commons;

/**
 * This is the interface to map the envelop.<br>
 * It adds helpful methods to check the envelop.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
public interface Envelop {

	/**
	 * Returns <code>true</code> if envelop passed as argument is not <code>null</code> and its content is not <code>null</code> as well.
	 * 
	 * @param envelop envelop to be checked
	 * @return <code>true</code> if envelop passed as argument is not <code>null</code> and its content is not <code>null</code> as well.
	 */
	static boolean isValid(Envelop envelop) {
		return envelop != null && (envelop.hasContent() || envelop.isNullable());
	}

	/**
	 * Checks if envelop passed as argument is not <code>null</code> and its content is not <code>null</code> as well.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param envelop envelop to be checked
	 */
	static void checkIfValid(Envelop envelop) {
		if (!isValid(envelop)) {
			throw new IllegalArgumentException("Envelop is null or not consistent");
		}
	}

	/**
	 * Checks if envelop passed as argument is not <code>null</code> and its content is not <code>null</code> as well.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param envelop key to be checked
	 * @param <T> type of envelop
	 * @return the same key passed as argument
	 */
	static <T extends Envelop> T checkAndGetIfValid(T envelop) {
		// checks if key is consistent
		checkIfValid(envelop);
		// if here, is consistent
		// then returns the argument
		return envelop;
	}

	/**
	 * Returns <code>true</code> if the content of envelop is not <code>null</code>.
	 * 
	 * @return <code>true</code> if the content of envelop is not <code>null</code>
	 */
	boolean hasContent();

	/**
	 * Returns <code>true</code> if the content of envelop can be <code>null</code>.
	 * 
	 * @return <code>true</code> if the content of envelop can be <code>null</code>
	 */
	boolean isNullable();

}