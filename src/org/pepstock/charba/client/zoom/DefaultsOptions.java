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
package org.pepstock.charba.client.zoom;

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;

/**
 * {@link ZoomPlugin#ID} plugin default options.<br>
 * It contains all default values, PAN and ZOOM.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class DefaultsOptions extends AbstractPluginOptions {

	// defaults options instance
	static final DefaultsOptions DEFAULTS_INSTANCE = new DefaultsOptions();
	// default pan options
	private final DefaultsPan pan;
	// default Zoom options
	private final DefaultsZoom zoom;

	/**
	 * Creates an empty options without any default global options. It will use the constants as default of plugin properties.
	 */
	private DefaultsOptions() {
		this(null);
	}

	/**
	 * Creates the object wrapping the default global options if there are. It will use the constants as default of plugin properties.
	 * 
	 * @param nativeObject native object which maps default global options.
	 */
	DefaultsOptions(NativeObject nativeObject) {
		super(ZoomPlugin.ID, nativeObject);
		// reads default pan options from main object
		pan = new DefaultsPan(getValue(ZoomOptions.Property.PAN));
		// reads default zoom options from main object
		zoom = new DefaultsZoom(getValue(ZoomOptions.Property.ZOOM));
		// checks if padding is stored
		if (!has(ZoomOptions.Property.PAN)) {
			// sets the native object inside this object
			setValue(ZoomOptions.Property.PAN, pan);
		}
		// checks if padding is stored
		if (!has(ZoomOptions.Property.ZOOM)) {
			// sets the native object inside this object
			setValue(ZoomOptions.Property.ZOOM, zoom);
		}
	}

	/**
	 * Returns the pan element.
	 * 
	 * @return the pan element.
	 */
	DefaultsPan getPan() {
		return pan;
	}

	/**
	 * Returns the zoom element.
	 * 
	 * @return the zoom element.
	 */
	DefaultsZoom getZoom() {
		return zoom;
	}

}
