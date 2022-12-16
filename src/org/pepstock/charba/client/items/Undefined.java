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

import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Img;

/**
 * Class with default values when the java script object returns an UNDEFINED value.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Undefined {

	/**
	 * Default value for INTEGER instances, {@link Integer#MIN_VALUE}.
	 */
	public static final int INTEGER = Integer.MIN_VALUE;

	/**
	 * Default value for DOUBLE instances, {@link Double#NaN}.
	 */
	public static final double DOUBLE = Double.NaN;

	/**
	 * Default value for STRING instances, <b>null</b>.
	 */
	public static final String STRING = null;

	/**
	 * Default value for BOOLEAN instances, {@link Boolean#FALSE}.
	 */
	public static final boolean BOOLEAN = Boolean.FALSE;

	/**
	 * Default value for ImageElement instances, <b>null</b>.
	 */
	public static final Img IMAGE_ELEMENT = null;

	/**
	 * Default value for HTMLCanvasElement instances, <b>null</b>.
	 */
	public static final Canvas CANVAS_ELEMENT = null;

	/**
	 * To avoid any instantiation
	 */
	private Undefined() {
		// do nothing
	}

	/**
	 * Returns <code>true</code> if the value is undefined.
	 * 
	 * @param value value to be checked
	 * @return <code>true</code> if the value is undefined
	 */
	public static boolean is(int value) {
		return !isNot(value);
	}

	/**
	 * Returns <code>true</code> if the value is undefined.
	 * 
	 * @param value value to be checked
	 * @return <code>true</code> if the value is undefined
	 */
	public static boolean is(double value) {
		return !isNot(value);
	}

	/**
	 * Returns <code>true</code> if the value is not undefined.
	 * 
	 * @param value value to be checked
	 * @return <code>true</code> if the value is not undefined
	 */
	public static boolean isNot(int value) {
		return value != Undefined.INTEGER;
	}

	/**
	 * Returns <code>true</code> if the value is not undefined.
	 * 
	 * @param value value to be checked
	 * @return <code>true</code> if the value is not undefined
	 */
	public static boolean isNot(double value) {
		return !Double.isNaN(value) && !Double.isInfinite(value);
	}

}