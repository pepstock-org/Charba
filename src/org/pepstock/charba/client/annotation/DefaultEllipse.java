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
 * This is the {@link AnnotationPlugin#ID} plugin ELLIPSE annotation DEFAULTS options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class DefaultEllipse implements IsDefaultsEllipseAnnotation {

	// defaults options instance
	static final DefaultEllipse INSTANCE = new DefaultEllipse();

	/**
	 * To avoid any instantiation
	 */
	private DefaultEllipse() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsAnnotation#getType()
	 */
	@Override
	public AnnotationType getType() {
		return AnnotationType.ELLIPSE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsBorderOptionsHandler#getBorderWidth()
	 */
	@Override
	public int getBorderWidth() {
		return EllipseAnnotation.DEFAULT_BORDER_WIDTH;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsEllipseAnnotation#getLabel()
	 */
	@Override
	public IsDefaultsEllipseLabel getLabel() {
		return DefaultEllipseLabel.INSTANCE;
	}

}