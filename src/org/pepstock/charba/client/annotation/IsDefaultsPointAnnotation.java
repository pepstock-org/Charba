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

import org.pepstock.charba.client.callbacks.PointStyleCallback;
import org.pepstock.charba.client.defaults.IsDefaultPointStyleHandler;

/**
 * This is the {@link AnnotationPlugin#ID} plugin <b>POINT</b> annotation DEFAULTS options interface.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultsPointAnnotation extends IsDefaultsAbstractCircleBasedAnnotation, IsDefaultsBackgroundColorHandler, IsDefaultPointStyleHandler, IsDefaultsExtendedShadowOptionsHandler {

	/**
	 * Returns the radius of the point.
	 * 
	 * @return the radius of the point.
	 */
	@Override
	default double getRadius() {
		return PointAnnotation.DEFAULT_RADIUS;
	}

	/**
	 * Returns the point style callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the point style callback, if set, otherwise <code>null</code>.
	 */
	default PointStyleCallback<AnnotationContext> getPointStyleCallback() {
		return null;
	}

}