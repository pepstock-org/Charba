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

/**
 * Implements a <b>LABEL</b> to apply on a ELLIPSE annotation.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class EllipseLabel extends AbstractBoxedLabel implements IsDefaultsEllipseLabel, HasRotation {

	/**
	 * To avoid any instantiation because is added in the all {@link EllipseAnnotation}.
	 * 
	 * @param parent {@link EllipseAnnotation} instance which contains the label
	 * @param defaultValues default options instance
	 */
	EllipseLabel(EllipseAnnotation parent, IsDefaultsEllipseLabel defaultValues) {
		this(parent, null, defaultValues);
	}

	/**
	 * To avoid any instantiation because is added in the all {@link EllipseAnnotation}.
	 * 
	 * @param parent {@link EllipseAnnotation} instance which contains the label
	 * @param nativeObject native object to wrap, with all properties of a label
	 * @param defaultValues default options instance
	 */
	EllipseLabel(EllipseAnnotation parent, NativeObject nativeObject, IsDefaultsEllipseLabel defaultValues) {
		super(parent, nativeObject, defaultValues);
	}
}