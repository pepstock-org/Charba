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
package org.pepstock.charba.client.plugins;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.Helpers;
import org.pepstock.charba.client.commons.NativeObject;

/**
 * Commons methods of extension, plugins out-of-the-box written in java script, to import and manage by Charba.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of plugin options
 *
 */
public abstract class AbstractExtensionPlugin<T extends AbstractPluginOptions> {

	// instanc of default options
	private T defaults = null;
	// flag is the plugins has been registered
	private boolean registered = false;

	/**
	 * Reduces the visibility of constructor.
	 */
	protected AbstractExtensionPlugin() {
		// do nothing
	}

	/**
	 * Returns <code>true</code> if the plugin has been already registered.
	 * 
	 * @return <code>true</code> if the plugin has been already registered
	 */
	protected final boolean isRegistered() {
		return registered;
	}

	/**
	 * Returns the original defaults of plugin.
	 * 
	 * @return the original defaults of plugin
	 */
	protected final T getDefaults() {
		return defaults;
	}

	/**
	 * This is invoked before storing the plugins options as default.<br>
	 * Applies the default to the options passed as argument.
	 * 
	 * @param options options instance where to apply the defaults.
	 */
	protected final void applyingDefaults(T options) {
		// checks if options are consistent has got the same plugin id
		if (options != null && defaults != null && options.getPluginId().equalsIgnoreCase(defaults.getPluginId())) {
			// merges the original defaults on this object
			Helpers.get().mergeIf(options.nativeObject(), defaults.nativeObject());
		}
	}

	/**
	 * Loads the default values from CHART.JS default in order to maintain the defaults.
	 * 
	 * @param factory factory to load default options
	 */
	protected final void loadDefaults(AbstractPluginOptionsFactory<T> factory) {
		// checks if plugin has been registered and
		// factory is consistent
		if (!isRegistered() && factory != null) {
			// sets the flag
			registered = true;
			// ------------------------------------
			// RETRIEVE defaults set by plugin OOTB
			// ------------------------------------
			T globalDefaults;
			// checks if there is an options
			if (Defaults.get().getGlobal().getPlugins().hasOptions(factory.getPluginId())) {
				// gets the original defaults
				T originalDefaults = Defaults.get().getGlobal().getPlugins().getOptions(factory.getPluginId(), factory);
				// clones the native object
				// in order to preserve this defaults
				NativeObject objectDefaults = Helpers.get().clone(originalDefaults.nativeObject());
				// creates the defaults
				globalDefaults = factory.create(objectDefaults, null);
			} else {
				// no defaults has been set
				// then a completely empty object as default
				globalDefaults = factory.create(null, null);
			}
			// stores the defaults
			this.defaults = globalDefaults;
		}
	}

}