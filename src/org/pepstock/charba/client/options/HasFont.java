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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.defaults.IsDefaultFontContainer;

/**
 * Interface to map the font and color options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface HasFont extends IsDefaultFontContainer {

	/**
	 * Returns a font container instance to use in the default methods of this interface.
	 * 
	 * @return a font container instance
	 */
	FontContainer getFontContainer();

	/**
	 * Returns the font element.
	 * 
	 * @return the font
	 */
	@Override
	default IsFont getFont() {
		// checks if font container is consistent
		if (getFontContainer() != null) {
			return getFontContainer().getFont();
		}
		// if here, font container is not consistent
		// returns nulls
		return null;
	}

	/**
	 * Sets the font color.
	 * 
	 * @param color font color.
	 */
	default void setColor(IsColor color) {
		setColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the font color.
	 * 
	 * @param color font color.
	 */
	default void setColor(String color) {
		// checks if font container is consistent
		if (getFontContainer() != null) {
			getFontContainer().setColor(color);
		}
	}

	/**
	 * Returns the font color as string.
	 * 
	 * @return font color as string
	 */
	@Override
	default String getColorAsString() {
		// checks if font container is consistent
		if (getFontContainer() != null) {
			return getFontContainer().getColorAsString();
		}
		// if here, font container is not consistent
		// returns the default colot
		return Defaults.get().getGlobal().getColorAsString();
	}

	/**
	 * Returns the font color.
	 * 
	 * @return font color
	 */
	default IsColor getColor() {
		return ColorBuilder.parse(getColorAsString());
	}

}