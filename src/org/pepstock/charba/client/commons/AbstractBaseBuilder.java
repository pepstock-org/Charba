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
 * base calss to implement builder classes in order to use a builder in consistent manner, a builder instance for a result object one.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractBaseBuilder implements IsBuilder {

	// built flag
	private boolean built = false;

	/**
	 * Sets the built status of the builder.
	 * 
	 * @param built the built status of the builder
	 */
	protected final void setBuilt(boolean built) {
		// checks if consistent
		IsBuilder.checkIfValid(this);
		// stores value
		this.built = built;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.IsBuilder#isBuilt()
	 */
	@Override
	public final boolean isBuilt() {
		return built;
	}
}