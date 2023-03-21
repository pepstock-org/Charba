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
package org.pepstock.charba.client.colors;

/**
 * Internal interface of enumerated color, like {@link HtmlColor} and {@link GwtMaterialColor}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsEnumeratedColor extends IsColor {

	/**
	 * Returns the color instance.
	 * 
	 * @return the color instance
	 */
	IsColor getColor();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#getRed()
	 */
	@Override
	default int getRed() {
		// check if color instance is consistent
		IsColor.checkIfValid(getColor());
		return getColor().getRed();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#getGreen()
	 */
	@Override
	default int getGreen() {
		// check if color instance is consistent
		IsColor.checkIfValid(getColor());
		return getColor().getGreen();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#getBlue()
	 */
	@Override
	default int getBlue() {
		// check if color instance is consistent
		IsColor.checkIfValid(getColor());
		return getColor().getBlue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.IsColor#getAlpha()
	 */
	@Override
	default double getAlpha() {
		// check if color instance is consistent
		IsColor.checkIfValid(getColor());
		return getColor().getAlpha();
	}
}