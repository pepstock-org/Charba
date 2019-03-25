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
package org.pepstock.charba.client.plugins;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract plugin options where to set all the configuration needed to the plugin.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractPluginCachedOptions extends AbstractPluginOptions {

	// list of chart ids or global where this options has been set
	// this is mandatory in order to clean up the cache of plugin options
	// when they are not longer needed
	private final List<String> references = new ArrayList<>();
	// options factory instance
	private final AbstractPluginCachedOptionsFactory<?> factory;
	// flag to know if it must be registered
	private boolean isRegistered = false;

	/**
	 * Creates new plugin options with its factory, plugin ID and a flag to know if it must register the options to the cache or
	 * if it will be postponed.<br>
	 * The deferred registration is needed to implement options builder in order do not register options not used.
	 * 
	 * @param pluginId plugin ID
	 * @param factory plugin options factory
	 * @param deferredRegistration if <code>true</code> the options is not registered
	 */
	protected AbstractPluginCachedOptions(String pluginId, AbstractPluginCachedOptionsFactory<?> factory, boolean deferredRegistration) {
		// creates an empty native object
		super(pluginId);
		// stores factory and pluginId
		this.factory = factory;
		// checks if it must deferred the registration to
		// the factory
		if (!deferredRegistration) {
			// registers into cache
			factory.registerOptions(this);
			// sets falg
			isRegistered = true;
		}
	}

	/**
	 * Returns the list of references of this options.<br>
	 * Called by factory in order to manage correctly the cache and removes this option when it doesn't have any reference.
	 * 
	 * @return the list of references of this options
	 */
	protected List<String> getReferences() {
		return references;
	}

	/**
	 * Registers the options to the factory to manage the cache of options.
	 */
	protected void register() {
		// checks if registered
		// if yes, do nothing
		if (!isRegistered) {
			// registers into cache
			factory.registerOptions(this);
		}
	}

}
