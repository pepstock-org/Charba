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

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultAnimations;
import org.pepstock.charba.client.options.AbstractAnimations;

/**
 * This is the {@link AnnotationPlugin#ID} plugin options where to set all configuration related to annotation animations.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class Animations extends AbstractAnimations {

	/**
	 * Creates an empty animations config to use for chart configuration, wrapping a native object instance.
	 * 
	 * @param parent the native object container which animations belongs to.
	 * @param defaultValues default provider
	 */
	Animations(AnnotationOptions parent, IsDefaultAnimations defaultValues) {
		this(parent, defaultValues, null);
	}

	/**
	 * Creates a animations config to use for chart configuration, wrapping a native object instance.
	 * 
	 * @param parent the native object container which animations belongs to.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Animations(AnnotationOptions parent, IsDefaultAnimations defaultValues, NativeObject nativeObject) {
		super(parent, defaultValues, nativeObject);
	}

}