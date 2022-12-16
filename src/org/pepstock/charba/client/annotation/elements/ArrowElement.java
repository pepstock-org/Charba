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
package org.pepstock.charba.client.annotation.elements;

import org.pepstock.charba.client.annotation.ArrowHeads;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;

/**
 * Maps the arrow options of a {@link AnnotationElement} at runtime.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class ArrowElement extends BaseElement {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DISPLAY("display"),
		FILL("fill"),
		LENGTH("length"),
		WIDTH("width");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
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

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param nativeObject native object to map java script properties
	 */
	ArrowElement(AbstractNode parent, Key childKey, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
	}

	/**
	 * Sets <code>true</code> whether the annotation arrow head should be displayed.
	 * 
	 * @param display <code>true</code> whether the arrow head should be displayed
	 */
	public final void setDisplay(boolean display) {
		setValueAndAddToParent(Property.DISPLAY, display);
	}

	/**
	 * Returns <code>true</code> whether the annotation arrow head should be displayed.
	 * 
	 * @return <code>true</code> whether the annotation arrow head should be displayed
	 */
	public final boolean isDisplay() {
		return getValue(Property.DISPLAY, ArrowHeads.DEFAULT_DISPLAY);
	}

	/**
	 * Sets <code>true</code> whether the arrow head must be closed and filled.
	 * 
	 * @param fill <code>true</code> whether the arrow head must be closed and filled.
	 */
	public final void setFill(boolean fill) {
		setValueAndAddToParent(Property.FILL, fill);
	}

	/**
	 * Returns <code>true</code> whether the arrow head must be closed and filled.
	 * 
	 * @return <code>true</code> whether the arrow head must be closed and filled.
	 */
	public final boolean isFill() {
		return getValue(Property.FILL, ArrowHeads.DEFAULT_FILL);
	}

	/**
	 * Sets the amount of pixels of the length of the arrow head.
	 * 
	 * @param length the amount of pixels of the length of the arrow head.
	 */
	public final void setLength(int length) {
		setValueAndAddToParent(Property.LENGTH, Checker.positiveOrZero(length));
	}

	/**
	 * Returns the amount of pixels of the length of the arrow head.
	 * 
	 * @return the amount of pixels of the length of the arrow head.
	 */
	public final int getLength() {
		return getValue(Property.LENGTH, ArrowHeads.DEFAULT_LENGTH);
	}

	/**
	 * Sets the amount of pixels of the width of the arrow head.
	 * 
	 * @param width the amount of pixels of the width of the arrow head
	 */
	public final void setWidth(int width) {
		setValueAndAddToParent(Property.WIDTH, Checker.positiveOrZero(width));
	}

	/**
	 * Returns the width of the arrow head.
	 * 
	 * @return the width of the arrow head
	 */
	public final int getWidth() {
		return getValue(Property.WIDTH, ArrowHeads.DEFAULT_WIDTH);
	}
}