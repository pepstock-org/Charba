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
package org.pepstock.charba.client.options;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultPlugins;
import org.pepstock.charba.client.enums.DefaultPlugin;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;
import org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory;
import org.pepstock.charba.client.plugins.PluginIdChecker;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Definitions about plugins options. This is used to configure plugins (mainly the global ones).<br>
 * Every plugin could have own configuration structure.<br>
 * The java script object key is the plugin id.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Plugins extends AbstractModel<Options, IsDefaultPlugins> implements IsDefaultPlugins {

	// exception message when the plugin id is not the same in all options passed as list
	private static final String INVALID_ID_NOT_EQUALS_IN_ALL_OPTIONS = "Plugin id '{0}' is not equals into all options '{1}'";
	// exception message when the plugin id is not the same of options passed as argument
	private static final String INVALID_ID_NOT_EQUALS_IN_OPTIONS = "Plugin id '{0}' is not equals to plugin id '{1}'of options";
	// exception message when the plugin id is not the same of factory passed as argument
	private static final String INVALID_ID_NOT_EQUALS_IN_FACTORY = "Plugin id '{0}' is not equals to plugin id '{1}'of factory";

	/**
	 * Creates the object with the parent, the key of this element and native object to map java script properties.<br>
	 * No default values for this element.
	 * 
	 * @param options options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Plugins(Options options, Key childKey, IsDefaultPlugins defaultValues, NativeObject nativeObject) {
		// no default values for this element
		super(options, childKey, defaultValues, nativeObject);
	}

	/**
	 * Returns the unmodifiable list of registered plugin ids.
	 * 
	 * @return the unmodifiable list of registered plugin ids
	 */
	public List<Key> getAllIds() {
		return Collections.unmodifiableList(keys());
	}

	/**
	 * Sets if a global plugin must be enabled or not.
	 * 
	 * @param pluginId plugin id.
	 * @param enabled <code>false</code> disable a global plugin.
	 */
	public void setEnabled(String pluginId, boolean enabled) {
		setValue(PluginIdChecker.key(pluginId), enabled);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Sets if a default CHART.JS plugin must be enabled or not.
	 * 
	 * @param plugin default CHART.JS plugin instance.
	 * @param enabled <code>false</code> disable a default CHART.JS plugin.
	 */
	public void setEnabled(DefaultPlugin plugin, boolean enabled) {
		setEnabled(plugin.value(), enabled);
	}

	/**
	 * Returns if a global plugin is enabled or not.
	 * 
	 * @param pluginId plugin id.
	 * @return <code>false</code> if a global plugin is not enabled otherwise <code>true</code>.
	 */
	public boolean isEnabled(String pluginId) {
		// creates the key to avoid many calls to plugin checker
		Key pluginIdKey = PluginIdChecker.key(pluginId);
		// gets the type of property
		ObjectType type = type(pluginIdKey);
		// if boolean, checks if enable
		if (ObjectType.BOOLEAN.equals(type)) {
			// if the property is not found, checks if the plugin was enable to all charts.
			return getValue(pluginIdKey, Defaults.get().getPlugins().isEnabledAllCharts(pluginId));
		}
		// if here, the property can exist or not.
		// if exist, means that the options have been added
		// and then it enables the plugin
		return has(pluginIdKey);
	}

	/**
	 * Returns if a global plugin is enabled or not, forced directly by global plugin manager
	 * 
	 * @param pluginId plugin id.
	 * @return <code>true</code> if a global plugin is not enabled otherwise <code>false</code>.
	 */
	public boolean isForcedlyDisabled(String pluginId) {
		// creates the key to avoid many calls to plugin checker
		Key pluginIdKey = PluginIdChecker.key(pluginId);
		// gets the type of property
		ObjectType type = type(pluginIdKey);
		// if boolean, checks if enable
		if (ObjectType.BOOLEAN.equals(type)) {
			// if the property is not found, checks if the plugin was enable to all charts.
			return !getValue(pluginIdKey, true);
		}
		// if here, the property can exist or not.
		// therefore is not disabled
		return false;
	}

	/**
	 * Returns if a default CHART.JS plugin is enabled or not, forced directly by global plugin manager
	 * 
	 * @param plugin a default CHART.JS plugin.
	 * @return <code>true</code> if a default CHART.JS plugin is not enabled otherwise <code>false</code>.
	 */
	public boolean isForcedlyDisabled(DefaultPlugin plugin) {
		return isForcedlyDisabled(plugin.value());
	}

	/**
	 * Returns if a global plugin has been set or not.
	 * 
	 * @param pluginId plugin id.
	 * @return <code>false</code> if a global plugin has not been set otherwise <code>true</code>.
	 */
	public boolean hasEnabled(String pluginId) {
		return has(PluginIdChecker.key(pluginId));
	}

	/**
	 * Removes the plugin options.
	 * 
	 * @param pluginId plugin id.
	 */
	public void removeOptions(String pluginId) {
		// checks if there is a stored plugin options
		if (hasOptions(pluginId)) {
			// checks plugin ids
			Key pluginIdKey = PluginIdChecker.key(pluginId);
			// removes configuration if exists
			remove(pluginIdKey);
		}
	}

	/**
	 * Sets the plugin options.
	 * 
	 * @param options plugin options used to configure the plugin
	 * @param <T> type of plugin options to store
	 */
	public <T extends AbstractPluginOptions> void setOptions(T options) {
		// checks if options is consistent
		if (options != null) {
			// checks plugin ids
			Key pluginIdKey = PluginIdChecker.key(options.getPluginId());
			// stores configuration
			setValue(pluginIdKey, options);
			// checks if the node is already added to parent
			checkAndAddToParent();
		}
	}

	/**
	 * Sets the plugin options as list.<br>
	 * If the list is empty, it does nothing
	 * 
	 * @param options list of plugin options used to configure the plugin.
	 * @param <T> type of plugin options to store
	 */
	public <T extends AbstractPluginOptions> void setOptions(List<T> options) {
		// checks if options are consistent and not empty
		if (options != null && !options.isEmpty()) {
			// creates a reference of plugin id
			String pluginId = null;
			// scans all options to check if the options have got the same plugin id
			for (AbstractPluginOptions option : options) {
				// checks plugin id
				if (pluginId != null && !pluginId.equals(option.getPluginId())) {
					// if here, the plugin ID is not equals into all options
					// then exception
					throw new IllegalArgumentException(Utilities.applyTemplate(INVALID_ID_NOT_EQUALS_IN_ALL_OPTIONS, pluginId, option.getPluginId()));
				}
				// stores the pluginId
				pluginId = option.getPluginId();
			}
			// creates plugin ids
			Key pluginIdKey = PluginIdChecker.key(pluginId);
			// stores configuration
			setArrayValue(pluginIdKey, ArrayObject.fromOrNull(options));
			// checks if the node is already added to parent
			checkAndAddToParent();
		}
	}

	/**
	 * Sets the plugin options.<br>
	 * If passed options is <code>null</code>, the configuration of plugin will be removed.
	 * 
	 * @param pluginId plugin id.
	 * @param options plugin options used to configure the plugin.<br>
	 *            Pass <code>null</code> to remove the configuration if exist.
	 * @param <T> type of plugin options to store
	 */
	public <T extends AbstractPluginOptions> void setOptions(String pluginId, T options) {
		// checks plugin ids
		Key pluginIdKey = PluginIdChecker.key(pluginId);
		// if null and there is an options, removes the configuration
		if (options == null && hasOptions(pluginId)) {
			// removes configuration if exists
			remove(pluginIdKey);
		} else {
			// checks plugin
			checkPluginIdConsistency(pluginIdKey, options);
			// stores configuration
			setValue(pluginIdKey, options);
			// checks if the node is already added to parent
			checkAndAddToParent();
		}
	}

	/**
	 * Sets the plugin options as list.<br>
	 * If passed options is <code>null</code>, the configuration of plugin will be removed.
	 * 
	 * @param pluginId plugin id.
	 * @param options list of plugin options used to configure the plugin.<br>
	 *            Pass <code>null</code> to remove the configuration if exist.
	 * @param <T> type of plugin options to store
	 */
	public <T extends AbstractPluginOptions> void setOptions(String pluginId, List<T> options) {
		// checks plugin ids
		Key pluginIdKey = PluginIdChecker.key(pluginId);
		// if null and there is an options, removes the configuration
		if (options == null && hasOptions(pluginId)) {
			// removes configuration if exists
			remove(pluginIdKey);
		} else if (options != null) {
			// scans all options to check if the options have got the same plugin id
			for (AbstractPluginOptions option : options) {
				// checks plugin
				checkPluginIdConsistency(pluginIdKey, option);
			}
			// stores configuration
			setArrayValue(pluginIdKey, ArrayObject.fromOrNull(options));
			// checks if the node is already added to parent
			checkAndAddToParent();
		}
	}

	/**
	 * Checks if there is any options for a specific plugin, by its id.
	 * 
	 * @param pluginId plugin id.
	 * @return <code>true</code> if there is an options, otherwise <code>false</code>.
	 */
	public boolean hasOptions(String pluginId) {
		// creates the key to avoid many calls to plugin checker
		Key pluginIdKey = PluginIdChecker.key(pluginId);
		// gets the type of property
		ObjectType type = type(pluginIdKey);
		// if boolean, there is not any options, therefore false
		// otherwise checks if there is the key. If there is and is NOT boolean
		// means that an options has been added.
		return !ObjectType.BOOLEAN.equals(type) && has(pluginIdKey);
	}

	/**
	 * Returns the options type.
	 * 
	 * @param pluginId plugin id.
	 * @return the options type
	 */
	public ObjectType getOptionsType(String pluginId) {
		return type(PluginIdChecker.key(pluginId));
	}

	/**
	 * Returns the plugin options, if exist.<br>
	 * It uses a factory instance to create a plugin options.<br>
	 * If factory argument is not consistent, <code>null</code> is returned.
	 * 
	 * @param factory factory instance to create a plugin options
	 * @param <T> type of plugin options to return
	 * @return plugin options used to configure the plugin or an empty object if not exist.<br>
	 *         If factory argument is not consistent, <code>null</code> is returned.
	 */
	public <T extends AbstractPluginOptions> T getOptions(AbstractPluginOptionsFactory<T> factory) {
		// checks if factory is consistent
		if (factory != null) {
			// creates the key to avoid many calls to plugin checker
			Key pluginIdKey = PluginIdChecker.key(factory.getPluginId());
			// gets the type of property
			ObjectType type = type(pluginIdKey);
			// checks if object
			if (ObjectType.OBJECT.equals(type)) {
				// creates the object using the defaults options
				return factory.create(getValue(pluginIdKey), getDefaultValues());
			} else {
				// if here returns an empty object
				return factory.create(null, getDefaultValues());
			}
		}
		// if here factory is not consistent
		return null;
	}

	/**
	 * Returns the plugin options, if exist.<br>
	 * It uses a factory instance to create a plugin options.<br>
	 * If factory argument is not consistent, <code>null</code> is returned.
	 * 
	 * @param pluginId plugin id.
	 * @param factory factory instance to create a plugin options
	 * @param <T> type of plugin options to return
	 * @return plugin options used to configure the plugin or an empty object if not exist.<br>
	 *         If factory argument is not consistent, <code>null</code> is returned.
	 */
	public <T extends AbstractPluginOptions> T getOptions(String pluginId, AbstractPluginOptionsFactory<T> factory) {
		// checks if factory is consistent
		if (factory != null) {
			// creates the key to avoid many calls to plugin checker
			Key pluginIdKey = PluginIdChecker.key(pluginId);
			// checks plugin
			checkPluginIdConsistency(pluginIdKey, factory);
			// gets the type of property
			ObjectType type = type(pluginIdKey);
			// checks if object
			if (ObjectType.OBJECT.equals(type)) {
				// creates the object using the defaults options
				return factory.create(getValue(pluginIdKey), getDefaultValues());
			} else {
				// if here returns an empty object
				return factory.create(null, getDefaultValues());
			}
		}
		// if here factory is not consistent
		return null;
	}

	/**
	 * Returns the plugin options as list, if exist.<br>
	 * It uses a factory instance to create a plugin options.
	 * 
	 * @param factory factory instance to create a plugin options.
	 * @param <T> type of plugin options to return
	 * @return the plugin options as list or empty list if not exist.
	 */
	public <T extends AbstractPluginOptions> List<T> getOptionsAsList(AbstractPluginOptionsFactory<T> factory) {
		// checks if factory is consistent
		if (factory != null) {
			// creates the key to avoid many calls to plugin checker
			Key pluginIdKey = PluginIdChecker.key(factory.getPluginId());
			// gets the type of property
			ObjectType type = type(pluginIdKey);
			// checks if array
			if (ObjectType.ARRAY.equals(type)) {
				// gets the array from native object
				ArrayObject array = getArrayValue(pluginIdKey);
				// creates the result
				List<T> result = new LinkedList<>();
				// scans all native object from array
				for (int i = 0; i < array.length(); i++) {
					// creates the object using the defaults options
					// and adds to result list
					result.add(factory.create(array.get(i), getDefaultValues()));
				}
				return result;
			}
		}
		// if here returns an empty list
		return new LinkedList<>();
	}

	/**
	 * Returns the plugin options as list, if exist.<br>
	 * It uses a factory instance to create a plugin options.
	 * 
	 * @param pluginId plugin id.
	 * @param factory factory instance to create a plugin options.
	 * @param <T> type of plugin options to return
	 * @return the plugin options as list or empty list if not exist.
	 */
	public <T extends AbstractPluginOptions> List<T> getOptionsAsList(String pluginId, AbstractPluginOptionsFactory<T> factory) {
		// checks if factory is consistent
		if (factory != null) {
			// creates the key to avoid many calls to plugin checker
			Key pluginIdKey = PluginIdChecker.key(pluginId);
			// checks plugin
			checkPluginIdConsistency(pluginIdKey, factory);
			// gets the type of property
			ObjectType type = type(pluginIdKey);
			// checks if array
			if (ObjectType.ARRAY.equals(type)) {
				// gets the array from native object
				ArrayObject array = getArrayValue(pluginIdKey);
				// creates the result
				List<T> result = new LinkedList<>();
				// scans all native object from array
				for (int i = 0; i < array.length(); i++) {
					// creates the object using the defaults options
					// and adds to result list
					result.add(factory.create(array.get(i), getDefaultValues()));
				}
				return result;
			}
		}
		// if here returns an empty list
		return new LinkedList<>();
	}

	/**
	 * Checks if the plugin id, passed as key, is equals to plugin options.<br>
	 * If not, an {@link IllegalArgumentException} will be thrown.
	 * 
	 * @param pluginIdKey plugin id as key
	 * @param options plugin options instance
	 */
	private void checkPluginIdConsistency(Key pluginIdKey, AbstractPluginOptions options) {
		// checks if arguments are consistent and the plugin ids are not equals
		if (Key.isValid(pluginIdKey) && options != null && !pluginIdKey.value().equals(options.getPluginId())) {
			// if here, the plugin ID is not equals to the option
			// then exception
			throw new IllegalArgumentException(Utilities.applyTemplate(INVALID_ID_NOT_EQUALS_IN_OPTIONS, pluginIdKey.value(), options.getPluginId()));
		}
	}

	/**
	 * Checks if the plugin id, passed as key, is equals to plugin options factory.<br>
	 * If not, an {@link IllegalArgumentException} will be thrown.
	 * 
	 * @param pluginIdKey plugin id as key
	 * @param factory plugin options factory instance
	 */
	private void checkPluginIdConsistency(Key pluginIdKey, AbstractPluginOptionsFactory<?> factory) {
		// checks if arguments are consistent and the plugin ids are not equals

		if (Key.isValid(pluginIdKey) && factory != null && !pluginIdKey.value().equals(factory.getPluginId())) {
			// if here, the plugin ID is not equals to the option
			// then exception
			throw new IllegalArgumentException(Utilities.applyTemplate(INVALID_ID_NOT_EQUALS_IN_FACTORY, pluginIdKey.value(), factory.getPluginId()));
		}
	}

}