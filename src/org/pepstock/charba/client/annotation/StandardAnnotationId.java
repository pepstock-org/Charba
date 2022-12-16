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
package org.pepstock.charba.client.annotation;

/**
 * This is a standard implementation of a annotation configuration object ID for {@link AnnotationPlugin#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class StandardAnnotationId implements AnnotationId {

	// value instance
	private final String value;

	/**
	 * Builds the object with the key value as string
	 * 
	 * @param value value of key as String
	 */
	StandardAnnotationId(String value) {
		// stores value
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return value;
	}

}