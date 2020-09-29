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
package org.pepstock.charba.client.annotation;

import org.pepstock.charba.client.annotation.enums.DrawTime;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;

/**
 * This is the {@link Annotation#ID} plugin DEFAULTS options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class DefaultsOptions extends AbstractPluginOptions {

	// defaults options instance
	static final DefaultsOptions DEFAULTS_INSTANCE = new DefaultsOptions();

	/**
	 * Creates an empty options without any global options.<br>
	 * It will use the constants as of plugin properties.
	 */
	private DefaultsOptions() {
		this(null);
	}

	/**
	 * Creates the object wrapping the global options if there are.
	 * 
	 * @param nativeObject native object which maps global options.
	 */
	DefaultsOptions(NativeObject nativeObject) {
		super(Annotation.ID, nativeObject);
	}

	/**
	 * Returns the draw time which defines when the annotations are drawn.
	 * 
	 * @return the draw time which defines when the annotations are drawn
	 */
	DrawTime getDrawTime() {
		return getValue(AnnotationOptions.Property.DRAW_TIME, DrawTime.values(), AnnotationOptions.DEFAULT_DRAW_TIME);
	}

}
