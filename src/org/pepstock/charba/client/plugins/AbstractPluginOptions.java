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
import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * Abstract plugin options where to set all the configuration needed to the plugin.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractPluginOptions extends NativeObjectContainer {

	// static counter. Starts from min value of integer
	private static final AtomicInteger COUNTER = new AtomicInteger(Integer.MIN_VALUE);
	// list of chart ids or global where this options has been set
	// this is mandatory in order to clean up the cache of plugin options
	// when they are not longer needed
	private final List<String> references = new ArrayList<>();

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		// internal property to set unique id
		_charbaOptionsId
	}

	/**
	 * Creates new plugin options with its factory.
	 * 
	 * @param factory plugin options factory
	 */
	protected AbstractPluginOptions(AbstractPluginOptionsFactory<?> factory) {
		// creates an empty native object
		super();
		// sets unique id
		// needed for caching the instances
		setValue(Property._charbaOptionsId, COUNTER.incrementAndGet());
		// registers into cache
		factory.registerOptions(this);
	}

	/**
	 * Returns the unique ID of the options.
	 * 
	 * @return the unique ID of the options.
	 */
	public final int getId() {
		return getValue(Property._charbaOptionsId, UndefinedValues.INTEGER);
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

}
