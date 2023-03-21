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
package org.pepstock.charba.client.impl.plugins.enums;

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.GradientBuilder;
import org.pepstock.charba.client.colors.GradientOrientation;
import org.pepstock.charba.client.colors.GradientScope;
import org.pepstock.charba.client.colors.GradientType;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.impl.plugins.ColorScheme;

/**
 * Internal interface of enumerated color scheme.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsEnumeratedScheme extends ColorScheme {

	/**
	 * Returns the internal enumerated scheme instance.
	 * 
	 * @return the internal enumerated scheme instance
	 */
	ColorScheme getScheme();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	default String value() {
		// checks if scheme is consistent
		return Checker.checkAndGetIfValid(getScheme(), "Color scheme").value();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.impl.plugins.ColorScheme#category()
	 */
	@Override
	default String category() {
		// checks if scheme is consistent
		if (getScheme() != null) {
			return getScheme().category();
		}
		// returns the default category
		return ColorScheme.super.category();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.impl.plugins.ColorScheme#getColors()
	 */
	@Override
	default List<IsColor> getColors() {
		// checks if scheme is consistent
		if (getScheme() != null) {
			return getScheme().getColors();
		}
		// if here scheme is not consistent
		// then returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Creates a LINEAR gradient, with <code>topDown</code> orientation and <code>chart</code> scope.
	 * 
	 * @return new gradient based on color scheme
	 */
	default Gradient createGradient() {
		return createGradient(GradientType.LINEAR);
	}

	/**
	 * Creates a gradient by a type, with <code>chart</code> scope.
	 * 
	 * @param type gradient type
	 * @return new gradient based on color scheme
	 */
	default Gradient createGradient(GradientType type) {
		return createGradient(type, GradientOrientation.getDefaultByType(type));
	}

	/**
	 * Creates a gradient by a type and an orientation, with <code>chart</code> scope.
	 * 
	 * @param type gradient type
	 * @param orientation orientation of gradient
	 * @return new gradient based on color scheme
	 */
	default Gradient createGradient(GradientType type, GradientOrientation orientation) {
		return createGradient(type, orientation, GradientScope.CHART);
	}

	/**
	 * Creates a gradient by a type and a scope.
	 * 
	 * @param type gradient type
	 * @param scope scope of gradient
	 * @return new gradient based on color scheme
	 */
	default Gradient createGradient(GradientType type, GradientScope scope) {
		return createGradient(type, GradientOrientation.getDefaultByType(type), scope);
	}

	/**
	 * Creates a gradient by a type, an orientation and a scope.
	 * 
	 * @param type gradient type
	 * @param orientation orientation of gradient
	 * @param scope scope of gradient
	 * @return new gradient based on color scheme
	 */
	default Gradient createGradient(GradientType type, GradientOrientation orientation, GradientScope scope) {
		// creates a gradient builder
		GradientBuilder builder = GradientBuilder.create(type, orientation, scope);
		// sets the colors
		builder.setColors(getColors());
		// creates a gradient and returns it
		return builder.build();
	}

}