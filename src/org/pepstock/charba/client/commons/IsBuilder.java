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
 * Represents an options builder.<br>
 * Needed to check the consistency of builder after build.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsBuilder {

	/**
	 * Returns <code>true</code> if the builder haven't been invoked yet to create the object.
	 * 
	 * @param builder builder instance to be checked
	 * @return <code>true</code> if the builder haven't been invoked yet to create the object
	 */
	static boolean isValid(IsBuilder builder) {
		return builder != null && !builder.isBuilt();
	}

	/**
	 * Checks if the builder haven't been invoked yet to create the object.<br>
	 * If not, throw a {@link IllegalArgumentException}.
	 * 
	 * @param builder builder instance to be checked
	 */
	static void checkIfValid(IsBuilder builder) {
		if (!isValid(builder)) {
			throw new IllegalArgumentException("Builder is null or already consumed");
		}
	}

	/**
	 * Checks if the builder haven't been invoked yet to create the object.<br>
	 * If not, throw a {@link IllegalArgumentException}, otherwise it returns the builder.
	 * 
	 * @param builder builder instance to be checked
	 * @param <T> type of key
	 * @return the same key passed as argument
	 */
	static <T extends IsBuilder> T checkAndGetIfValid(T builder) {
		// checks if builder is consistent
		checkIfValid(builder);
		// if here, is consistent
		// then returns the argument
		return builder;
	}

	/**
	 * Returns <code>true</code> if new object has been already created by the builder.
	 * 
	 * @return <code>true</code> if new object has been already created by the builder
	 */
	boolean isBuilt();

}