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
package org.pepstock.charba.client.dom.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * Enumerates the set of values how an element is positioned in a document.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum Position implements Key
{
	/**
	 * The element is positioned according to the normal flow of the document.<br>
	 * The top, right, bottom, left, and z-index properties have no effect.<br>
	 * This is the default value.
	 */
	STATIC("static"),
	/**
	 * The element is positioned according to the normal flow of the document, and then offset relative to itself based on the values of top, right, bottom, and left.<br>
	 * The offset does not affect the position of any other elements; thus, the space given for the element in the page layout is the same as if position were static.
	 */
	RELATIVE("relative"),
	/**
	 * The element is removed from the normal document flow, and no space is created for the element in the page layout.<br>
	 * It is positioned relative to its closest positioned ancestor, if any; otherwise, it is placed relative to the initial containing block. Its final position is determined by
	 * the values of top, right, bottom, and left.
	 */
	ABSOLUTE("absolute"),
	/**
	 * The element is removed from the normal document flow, and no space is created for the element in the page layout.<br>
	 * It is positioned relative to the initial containing block established by the viewport, except when one of its ancestors has a transform, perspective, or filter property set
	 * to something other than none
	 */
	FIXED("fixed"),
	/**
	 * The element is positioned according to the normal flow of the document, and then offset relative to its nearest scrolling ancestor and containing block (nearest block-level
	 * ancestor), including table-related elements, based on the values of top, right, bottom, and left. The offset does not affect the position of any other elements.
	 */
	STICKY("sticky");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private Position(String value) {
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