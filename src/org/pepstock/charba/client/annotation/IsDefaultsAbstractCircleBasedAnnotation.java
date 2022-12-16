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

import org.pepstock.charba.client.callbacks.RadiusCallback;

/**
 * This is the {@link AnnotationPlugin#ID} plugin annotation DEFAULTS options interface for annotation which are configured by a point and radius in a chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsAbstractCircleBasedAnnotation extends IsDefaultsAbstractPointedAnnotation, IsDefaultsRotationHandler {

	/**
	 * Returns the radius of the annotation.
	 * 
	 * @return the radius of the annotation.
	 */
	double getRadius();

	/**
	 * Returns the callback called to set the radius.
	 * 
	 * @return the callback called to set the radius
	 */
	default RadiusCallback<AnnotationContext> getRadiusCallback() {
		return null;
	}

}