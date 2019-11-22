/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.enums;

import org.pepstock.charba.client.commons.Key;

import com.google.gwt.canvas.dom.client.Context2d.LineCap;

/**
 * Determines how the end points of every line are drawn.<br>
 * There are three possible values for this property and those are: butt, round and square. By default this property is set to
 * butt.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum CapStyle implements Key
{
	/**
	 * The ends of lines are squared off at the end points.<br>
	 * Default.
	 */
	BUTT("butt", LineCap.BUTT),
	/**
	 * The ends of lines are rounded.
	 */
	ROUND("round", LineCap.ROUND),
	/**
	 * The ends of lines are squared off by adding a box with an equal width and half the height of the line's thickness.
	 */
	SQUARE("square", LineCap.SQUARE);

	// name value of property
	private final String value;
	// GWT line cap
	private final LineCap lineCap;

	/**
	 * Creates with the property value to use into native object.
	 * 
	 * @param value value of property name
	 * @param lineCap the GWT constant for line cap style
	 */
	private CapStyle(String value, LineCap lineCap) {
		this.value = value;
		this.lineCap = lineCap;
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

	/**
	 * Returns the GWT constant for line cap style.
	 * 
	 * @return the GWT constant for line cap style
	 */
	public LineCap getLineCap() {
		return lineCap;
	}
}