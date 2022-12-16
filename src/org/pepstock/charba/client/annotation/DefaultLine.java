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
 * This is the {@link AnnotationPlugin#ID} plugin LINE annotation DEFAULTS options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class DefaultLine implements IsDefaultsLineAnnotation {

	// defaults options instance
	static final DefaultLine INSTANCE = new DefaultLine();

	/**
	 * To avoid any instantiation
	 */
	private DefaultLine() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsAnnotation#getType()
	 */
	@Override
	public AnnotationType getType() {
		return AnnotationType.LINE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsLineAnnotation#getLabel()
	 */
	@Override
	public IsDefaultsLineLabel getLabel() {
		return DefaultLineLabel.INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsLineAnnotation#getArrowHeads()
	 */
	@Override
	public IsDefaultsArrowHeads getArrowHeads() {
		return DefaultArrowHeads.INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsBorderOptionsHandler#getBorderWidth()
	 */
	@Override
	public int getBorderWidth() {
		return LineAnnotation.DEFAULT_BORDER_WIDTH;
	}

}