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
import org.pepstock.charba.client.defaults.IsDefaultPlugins;
import org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory;

/**
 * Factory to get the options (from chart) related to {@link Annotation#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class AnnotationOptionsFactory extends AbstractPluginOptionsFactory<AnnotationOptions> {

	/**
	 * To avoid any instantiation. Use the static reference into {@link Annotation#FACTORY}.<br>
	 * Adds itself as charts life cycle listener to manage the cache of data labels options, in order to clean the instances when the charts will be destroy.
	 */
	AnnotationOptionsFactory() {
		super(Annotation.ID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory#create(org.pepstock.charba.client.commons.NativeObject,
	 * org.pepstock.charba.client.defaults.IsDefaultPlugins)
	 */
	@Override
	public AnnotationOptions create(NativeObject nativeObject, IsDefaultPlugins defaultValues) {
		// gets the reference for defaults
		final IsDefaultsAnnotationOptions defaultOptions;
		// checks if defaults argument is consistent
		if (defaultValues != null) {
			// uses the defaults global options instance
			defaultOptions = loadDefaultsPluginOptions(defaultValues, Annotation.DEFAULTS_FACTORY);
		} else {
			// uses the predefined defaults
			defaultOptions = AnnotationDefaultsOptions.DEFAULTS_INSTANCE;
		}
		// creates the options by the native object and the defaults
		return new AnnotationOptions(defaultOptions != null ? defaultOptions : AnnotationDefaultsOptions.DEFAULTS_INSTANCE, nativeObject);
	}

	/**
	 * Internal factory to create options from default global option for the plugin
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	static class AnnotationDefaultsOptionsFactory extends AbstractPluginOptionsFactory<AnnotationOptions> {

		/**
		 * To avoid any instantiation.
		 */
		AnnotationDefaultsOptionsFactory() {
			super(Annotation.ID);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory#create(org.pepstock.charba.client.commons.NativeObject,
		 * org.pepstock.charba.client.defaults.IsDefaultPlugins)
		 */
		@Override
		public AnnotationOptions create(NativeObject nativeObject, IsDefaultPlugins defaultValues) {
			return new AnnotationOptions(AnnotationDefaultsOptions.DEFAULTS_INSTANCE, nativeObject);
		}
	}

}
