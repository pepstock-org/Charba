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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultPlugins;
import org.pepstock.charba.client.plugins.AbstractPluginCachedOptionsFactory;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;
import org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory;

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
	 */
	AnnotationOptionsFactory() {
		super(AnnotationPlugin.ID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory#create(org.pepstock.charba.client.commons.NativeObject,
	 * org.pepstock.charba.client.defaults.IsDefaultPlugins)
	 */
	@Override
	public AnnotationOptions create(NativeObject nativeObject, IsDefaultPlugins defaultValues) {
		// gets the options checking if cached
		AbstractPluginOptions options = getOptions(nativeObject);
		// checks if consistent and the right class
		if (options instanceof AnnotationOptions) {
			// cast and returns it
			return (AnnotationOptions) options;
		}
		// creates the options by the native object and the defaults
		// and ignores the native object passed into method
		// checks if defaults options are consistent
		if (defaultValues != null) {
			// defaults global options instance
			DefaultsOptions defaultsOptions = loadGlobalsPluginOptions(defaultValues, AnnotationPlugin.DEFAULTS_FACTORY);
			// creates the options by the native object and the defaults
			return new AnnotationOptions(defaultsOptions);
		}
		// creates the options by the native object and the defaults
		return new AnnotationOptions(DefaultsOptions.DEFAULTS_INSTANCE);

	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.plugins.AbstractPluginCachedOptionsFactory#onBeforeConfigure(org.pepstock.charba.client.IsChart)
	 */
	@Override
	public void onBeforeConfigure(IsChart chart) {
		// checks if chart is consistent
		if (IsChart.isConsistent(chart) && chart.getOptions().getPlugins().hasOptions(getPluginId())) {
			// gets the plugin options from chart options, if there
			AnnotationOptions options = chart.getOptions().getPlugins().getOptions(getPluginId(), this);
			// reset id for all annotations
			options.getAnnotations().forEach(AbstractAnnotation::resetAnnotationId);
		}
		// invokes super implementation
		super.onBeforeConfigure(chart);
	}

	/**
	 * Internal factory to create options from default global option for the plugin
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	static class AnnotationDefaultsOptionsFactory extends AbstractPluginOptionsFactory<DefaultsOptions> {

		/**
		 * To avoid any instantiation.
		 */
		AnnotationDefaultsOptionsFactory() {
			super(AnnotationPlugin.ID);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory#create(org.pepstock.charba.client.commons.
		 * NativeObject, org.pepstock.charba.client.defaults.IsDefaultPlugins)
		 */
		@Override
		public DefaultsOptions create(NativeObject nativeObject, IsDefaultPlugins defaultValues) {
			// check if native object is consistent
			if (nativeObject != null) {
				// creates the default global option by native object
				return new DefaultsOptions(nativeObject);
			}
			return DefaultsOptions.DEFAULTS_INSTANCE;
		}

	}

}
