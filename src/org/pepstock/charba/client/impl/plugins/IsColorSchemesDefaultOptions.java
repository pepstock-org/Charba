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
package org.pepstock.charba.client.impl.plugins;

import org.pepstock.charba.client.impl.plugins.enums.SchemeScope;

/**
 * Maps the default global options for {@link ColorSchemes#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
interface IsColorSchemesDefaultOptions {

	/**
	 * Returns the color scheme category.
	 * 
	 * @return the color scheme category
	 */
	default String getSchemeCategory() {
		return ColorSchemesOptions.DEFAULT_SCHEME.category();
	}

	/**
	 * Returns the color scheme name.
	 * 
	 * @return the color scheme name
	 */
	default String getSchemeName() {
		return ColorSchemesOptions.DEFAULT_SCHEME.value();
	}

	/**
	 * Returns the color scheme scope when the scheme is applied to hoving flex datasets, like bars charts.
	 * 
	 * @return the color scheme cope when the scheme is applied to hoving flex datasets, like bars charts
	 */
	default SchemeScope getSchemeScope() {
		return ColorSchemesOptions.DEFAULT_SCHEME_SCOPE;
	}

	/**
	 * The transparency value for the background color. Must be a number between 0.0 (fully transparent) and 1.0 (no transparency).
	 * 
	 * @return the transparency value for the background color
	 */
	default double getBackgroundColorAlpha() {
		return ColorSchemesOptions.DEFAULT_BACKGROUND_ALPHA;
	}

	/**
	 * If set to <code>true</code>, the order of the colors in the selected scheme is reversed.
	 * 
	 * @return if set to <code>true</code>, the order of the colors in the selected scheme is reversed
	 */
	default boolean isReverse() {
		return ColorSchemesOptions.DEFAULT_REVERSE;
	}

}