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
 * This is the {@link AnnotationPlugin#ID} plugin LINE annotation ARROWHEADS options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class DefaultArrowHeads extends DefaultArrow implements IsDefaultsArrowHeads {

	// defaults options instance
	static final DefaultArrowHeads INSTANCE = new DefaultArrowHeads();

	private final DefaultArrow start = new DefaultArrow();

	private final DefaultArrow end = new DefaultArrow();

	/**
	 * To avoid any instantiation
	 */
	private DefaultArrowHeads() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsArrowHeads#getStart()
	 */
	@Override
	public IsDefaultsArrow getStart() {
		return start;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsArrowHeads#getEnd()
	 */
	@Override
	public IsDefaultsArrow getEnd() {
		return end;
	}

}