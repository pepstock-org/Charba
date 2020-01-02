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

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.plugins.AbstractPluginCachedOptionsFactory;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;

/**
 * Factory to get the options (from chart) related to {@link AnnotationPlugin#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class AnnotationOptionsFactory extends AbstractPluginCachedOptionsFactory<AnnotationOptions> {

	/**
	 * To avoid any instantiation. Use the static reference into {@link AnnotationPlugin#FACTORY}.<br>
	 * Adds itself as charts life cycle listener to manage the cache of data labels options, in order to clean the instances
	 * when the charts will be destroy.
	 * 
	 * @param pluginId plugin ID
	 */
	AnnotationOptionsFactory(String pluginId) {
		super(pluginId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
	 */
	@Override
	public AnnotationOptions create(NativeObject nativeObject) {
		// gets the options checking if cached
		AbstractPluginOptions options = getOptions(nativeObject);
		// checks if consistent and the right class
		if (options instanceof AnnotationOptions) {
			// cast and returns it
			return (AnnotationOptions) options;
		}
		// creates the options by the native object and the defaults
		// and ignores the native object passed into method
		return new AnnotationOptions();
	}
}
