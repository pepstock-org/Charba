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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.dom.events.NativeAbstractMouseEvent;

/**
 * Base object which maps the CHART.JS chart items which represents a box.<br>
 * This is a wrapper of the CHART.JS item with all needed info.<br>
 * Implements all <code>get</code> methods to change java script object properties.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class BaseBoxItem extends NativeObjectContainer implements IsArea, HasInsideChecker {

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		TOP("top"),
		RIGHT("right"),
		BOTTOM("bottom"),
		LEFT("left"),
		WIDTH("width"),
		HEIGHT("height");

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
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	BaseBoxItem(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the top of chart area.
	 * 
	 * @return the top of chart area.
	 */
	@Override
	public final double getTop() {
		return getValue(Property.TOP, Undefined.DOUBLE);
	}

	/**
	 * Returns the right of chart area.
	 * 
	 * @return the right of chart area.
	 */
	@Override
	public final double getRight() {
		return getValue(Property.RIGHT, Undefined.DOUBLE);
	}

	/**
	 * Returns the bottom of chart area.
	 * 
	 * @return the bottom of chart area.
	 */
	@Override
	public final double getBottom() {
		return getValue(Property.BOTTOM, Undefined.DOUBLE);
	}

	/**
	 * Returns the left of chart area.
	 * 
	 * @return the left of chart area.
	 */
	@Override
	public final double getLeft() {
		return getValue(Property.LEFT, Undefined.DOUBLE);
	}

	/**
	 * Returns the width in pixel.
	 * 
	 * @return the width in pixel.
	 */
	@Override
	public final double getWidth() {
		return getValue(Property.WIDTH, Undefined.DOUBLE);
	}

	/**
	 * Returns the height in pixel.
	 * 
	 * @return the height in pixel.
	 */
	@Override
	public final double getHeight() {
		return getValue(Property.HEIGHT, Undefined.DOUBLE);
	}

	/**
	 * Returns <code>true</code> if the chart event is inside of this box, otherwise <code>false</code>.
	 * 
	 * @param event event to check if inside the box
	 * @return <code>true</code> if the chart event is inside of this box, otherwise <code>false</code>
	 */
	@Override
	public boolean isInside(NativeAbstractMouseEvent event) {
		// checks if consistent
		if (event != null && IsArea.isConsistent(this)) {
			// checks X
			final boolean isX = event.getLayerX() >= getLeft() && event.getLayerX() <= getRight();
			// checks Y
			final boolean isY = event.getLayerY() >= getTop() && event.getLayerY() <= getBottom();
			return isX && isY;
		}
		// if here, the area is not consistent
		// then returns false
		return false;
	}
}