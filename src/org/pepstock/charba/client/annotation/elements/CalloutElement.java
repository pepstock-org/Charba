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

import org.pepstock.charba.client.annotation.Callout;
import org.pepstock.charba.client.annotation.enums.CalloutPosition;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Maps the label options of a {@link AnnotationElement} at runtime.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class CalloutElement extends BaseElement {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DISPLAY("display"),
		MARGIN("margin"),
		POSITION("position"),
		SIDE("side"),
		START("start");

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
	CalloutElement(AbstractNode parent, Key childKey, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
	}

	/**
	 * Sets <code>true</code> whether the label should be displayed.
	 * 
	 * @param display <code>true</code> whether the label should be displayed
	 */
	public void setDisplay(boolean display) {
		setValue(Property.DISPLAY, display);
	}

	/**
	 * Returns <code>true</code> whether the label should be displayed.
	 * 
	 * @return <code>true</code> whether the label should be displayed
	 */
	public boolean isDisplay() {
		return getValue(Property.DISPLAY, Callout.DEFAULT_DISPLAY);
	}

	/**
	 * Sets the amount of pixels between the label and the callout separator.
	 * 
	 * @param margin the amount of pixels between the label and the callout separator
	 */
	public void setMargin(int margin) {
		setValue(Property.MARGIN, Checker.positiveOrZero(margin));
	}

	/**
	 * Returns the amount of pixels between the label and the callout separator.
	 * 
	 * @return the amount of pixels between the label and the callout separator.
	 */
	public int getMargin() {
		return getValue(Property.MARGIN, Callout.DEFAULT_MARGIN);
	}

	/**
	 * Sets the width of the starter line of callout pointer.
	 * 
	 * @param side the width of the starter line of callout pointer
	 */
	public void setSide(int side) {
		setValue(Property.SIDE, Checker.positiveOrZero(side));
	}

	/**
	 * Returns the width of the starter line of callout pointer.
	 * 
	 * @return the width of the starter line of callout pointer
	 */
	public int getSide() {
		return getValue(Property.SIDE, Callout.DEFAULT_SIDE);
	}

	/**
	 * Sets the separator dimension in pixels to use as starting point for callout pointer.
	 * 
	 * @param start the separator dimension in pixels to use as starting point for callout pointer
	 */
	public void setStart(int start) {
		setValue(Property.START, Checker.positiveOrZero(start));
	}

	/**
	 * Returns the separator dimension in pixels to use as starting point for callout pointer.
	 * 
	 * @return the separator dimension in pixels to use as starting point for callout pointer
	 */
	public int getStart() {
		return getValue(Property.START, Callout.DEFAULT_START);
	}

	/**
	 * Sets the percentage of the separator dimension to use as starting point for callout pointer.
	 * 
	 * @param start the percentage of the separator dimension to use as starting point for callout pointer
	 */
	public void setStartAsPercentage(double start) {
		setValue(Property.START, Utilities.getAsPercentage(start, 0));
	}

	/**
	 * Returns the percentage of the separator dimension to use as starting point for callout pointer.
	 * 
	 * @return the percentage of the separator dimension to use as starting point for callout pointer
	 */
	public double getStartAsPercentage() {
		return Utilities.getAsPercentage(getValue(Property.START, Undefined.STRING), Callout.DEFAULT_START_AS_PERCENTAGE);
	}

	/**
	 * Sets the position of callout, with respect to the label.
	 * 
	 * @param position the position of callout, with respect to the label
	 */
	public void setPosition(CalloutPosition position) {
		setValue(Property.POSITION, position);
	}

	/**
	 * Returns the position of callout, with respect to the label.
	 * 
	 * @return the position of callout, with respect to the label.
	 */
	public CalloutPosition getPosition() {
		return getValue(Property.POSITION, CalloutPosition.values(), CalloutPosition.AUTO);
	}

}