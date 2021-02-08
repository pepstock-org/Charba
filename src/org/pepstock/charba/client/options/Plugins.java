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
import java.util.List;

import org.pepstock.charba.client.ChartEnvelop;
import org.pepstock.charba.client.GlobalOptions;
import org.pepstock.charba.client.commons.IsEnvelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.configuration.ConfigurationEnvelop;
import org.pepstock.charba.client.defaults.IsDefaultPlugins;
import org.pepstock.charba.client.enums.DefaultPluginId;
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
public class Plugins extends AbstractModel<Options, IsDefaultPlugins> implements IsDefaultPlugins {

	// exception message when the plugin id is not the same of options passed as argument
	private static final String INVALID_ID_NOT_EQUALS_IN_OPTIONS = "Plugin id '{0}' is not equals to plugin id '{1}'of options";
	// exception message when the plugin id is not the same of factory passed as argument
	private static final String INVALID_ID_NOT_EQUALS_IN_FACTORY = "Plugin id '{0}' is not equals to plugin id '{1}'of factory";

	/**
	 * Creates the object wrapping another plugin.<br>This constructor is used ONLY from {@link GlobalOptions}.
	 * 
	 * @param envelop envelop passed by chart package in oredr to wrap another plugin.
	 */
	protected Plugins(ChartEnvelop<Plugins> envelop) {
		this(IsEnvelop.checkAndGetIfValid(envelop).getContent().getParent(), IsEnvelop.checkAndGetIfValid(envelop).getContent().getChildKey(), IsEnvelop.checkAndGetIfValid(envelop).getContent().getDefaultValues(),
				IsEnvelop.checkAndGetIfValid(envelop).getContent().getNativeObject());
	}

	/**
	 * Creates the object with the parent, the key of this element, defaults and native object to map java script properties.
	 * 
	 * @param options options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Plugins(Options options, Key childKey, IsDefaultPlugins defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
	}

	/**
	 * Returns the options for default plugin.
	 * 
	 * @param plugin default plugin id
	 * @return the options for default plugin
	 */
	NativeObject getDefaultPluginOptions(DefaultPluginId plugin) {
		return getValue(plugin);
	}

	/**
	 * Returns the unmodifiable list of registered plugin ids.
	 * 
	 * @return the unmodifiable list of registered plugin ids
	 */
	public final List<Key> getAllIds() {
		return Collections.unmodifiableList(keys());
	}

	/**
	 * Sets if a global plugin must be enabled or not.
	 * 
	 * @param pluginId plugin id.
	 * @param enabled <code>false</code> disable a global plugin.
	 */
	public final void setEnabled(String pluginId, boolean enabled) {
		setEnabled(PluginIdChecker.key(pluginId), enabled, false);
	}

	/**
	 * Sets if a default CHART.JS plugin must be enabled or not.<br>
	 * Callable only for chart configuration scope (no global or other).
	 * 
	 * @param envelop default CHART.JS plugin instance.
	 * @param enabled <code>false</code> disable a default CHART.JS plugin.
	 */
	public final void setEnabled(ConfigurationEnvelop<DefaultPluginId> envelop, boolean enabled) {
		// checks if envelop is valid
		if (IsEnvelop.isValid(envelop)) {
			setEnabled(envelop.getContent(), enabled, true);
		}
	}

	/**
	 * Sets if a global plugin must be enabled or not.<br>
	 * If <code>overrideDefaultPlugin</code>, passed as argument, is <code>true</code> that means this is called by configuration and it can enabled or disabled default plugins.
	 * 
	 * @param pluginId plugin id.
	 * @param enabled <code>false</code> disable a global plugin.
	 * @param overrideDefaultPlugin if <code>true</code> means this is called by configuration and it can enable or disable default plugins.
	 */
	private final void setEnabled(Key pluginId, boolean enabled, boolean overrideDefaultPlugin) {
		// checks if is a default plugin
		// if default plugin does nothing
		// but if override plugin is set, means that is done by configuration then allowed
		if (!DefaultPluginId.is(pluginId) || overrideDefaultPlugin) {
			// stores ONLY if enabled is false
			if (!enabled) {
				// stores false
				setValueAndAddToParent(pluginId, false);
			} else if (DefaultPluginId.is(pluginId)) {
				// if here, a default plugin is managing
				// it should add again the previous configuration
				reEnableDefaultPlugin(Key.getKeyByValue(DefaultPluginId.values(), pluginId.value()));
			} else if (!isType(pluginId, ObjectType.OBJECT)) {
				// if here, is not a default plugin and
				// then sets an empty object to enable the plugin
				setEmptyValue(pluginId);
			}
		}
	}
	
	/**
	 * Invoked to re-apply the default plugin configuration when the plugin was disable.
	 * 
	 * @param pluginId default plugin id
	 */
	private void reEnableDefaultPlugin(DefaultPluginId pluginId) {
		// checks if the property is a boolean
		// because it means it has been disable previously
		if (isType(pluginId, ObjectType.BOOLEAN)) {
			// checks which type of default plugin
			// in order to store the configuration object of the plugin
			if (DefaultPluginId.LEGEND.equals(pluginId)) {
				// re-apply the legend to plugin
				setValue(pluginId, getParent().getLegend());
			} else if (DefaultPluginId.TITLE.equals(pluginId)) {
				// re-apply the title to plugin
				setValue(pluginId, getParent().getTitle());
			} else if (DefaultPluginId.TOOLTIP.equals(pluginId)) {
				// re-apply the tolltip to plugin
				setValue(pluginId, getParent().getTooltips());
			} else {
				// removes key it exist
				removeIfExists(pluginId);
			}
		}
	}

	/**
	 * Returns if a plugin is enabled or not.
	 * 
	 * @param pluginId plugin id.
	 * @return <code>false</code> if a global plugin is not enabled otherwise <code>true</code>.
	 */
	public final boolean isEnabled(DefaultPluginId pluginId) {
		return isEnabledByKey(pluginId);
	}

	/**
	 * Returns if a plugin is enabled or not.
	 * 
	 * @param pluginId plugin id.
	 * @return <code>false</code> if a plugin is not enabled otherwise <code>true</code>.
	 */
	@Override
	public final boolean isEnabled(String pluginId) {
		return isEnabledByKey(PluginIdChecker.key(pluginId));
	}

	/**
	 * Returns if a plugin is enabled or not.
	 * 
	 * @param pluginId plugin id.
	 * @return <code>false</code> if a global plugin is not enabled otherwise <code>true</code>.
	 */
	private boolean isEnabledByKey(Key pluginId) {
		// if boolean, checks if enable
		if (isType(pluginId, ObjectType.BOOLEAN)) {
			// if the property is not found, checks if the plugin was enable to all charts.
			return getValue(pluginId, getDefaultValues().isEnabled(pluginId.value()));
		}
		// if here, the property is not a boolean,
		// boolean is set ONLY if the plugin is disabled,
		// therefore here is enabled
		return has(pluginId) || getDefaultValues().isEnabled(pluginId.value());
	}

	/**
	 * Returns if a global plugin has been set or not.
	 * 
	 * @param pluginId plugin id.
	 * @return <code>false</code> if a global plugin has not been set otherwise <code>true</code>.
	 */
	public final boolean hasEnabled(String pluginId) {
		return has(PluginIdChecker.key(pluginId));
	}

	/**
	 * Removes the plugin options.
	 * 
	 * @param pluginId plugin id.
	 */
	public final void removeOptions(String pluginId) {
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
		// checks if options is consistent and not a default plugin
		if (options != null && !DefaultPluginId.is(options.getPluginId())) {
			// checks plugin ids
			Key pluginIdKey = PluginIdChecker.key(options.getPluginId());
			// stores configuration
			setValueAndAddToParent(pluginIdKey, options);
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
		} else if (!DefaultPluginId.is(pluginId)) {
			// if here is not a default plugin
			// checks plugin
			checkPluginIdConsistency(pluginIdKey, options);
			// stores configuration
			setValueAndAddToParent(pluginIdKey, options);
		}
	}

	/**
	 * Checks if there is any options for a specific plugin, by its id.
	 * 
	 * @param pluginId plugin id.
	 * @return <code>true</code> if there is an options, otherwise <code>false</code>.
	 */
	@Override
	public final boolean hasOptions(String pluginId) {
		// creates the key to avoid many calls to plugin checker
		Key pluginIdKey = PluginIdChecker.key(pluginId);
		// checks if is default plugins
		if (!DefaultPluginId.is(pluginId)) {
			// if boolean, there is not any options, therefore false
			// otherwise checks if there is the key. If there is and is NOT boolean
			// means that an options has been added.
			return !isType(pluginIdKey, ObjectType.BOOLEAN) && has(pluginIdKey);
		}
		// if here is default
		// return always false
		return false;
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
	public final <T extends AbstractPluginOptions> T getOptions(AbstractPluginOptionsFactory<T> factory) {
		// checks if factory is consistent and not a default plugin
		if (factory != null && !DefaultPluginId.is(factory.getPluginId())) {
			// creates the key to avoid many calls to plugin checker
			Key pluginIdKey = PluginIdChecker.key(factory.getPluginId());
			// checks if object
			if (isType(pluginIdKey, ObjectType.OBJECT)) {
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
	@Override
	public final <T extends AbstractPluginOptions> T getOptions(String pluginId, AbstractPluginOptionsFactory<T> factory) {
		// checks if factory is consistent and not a default plugin
		if (factory != null && !DefaultPluginId.is(pluginId)) {
			// creates the key to avoid many calls to plugin checker
			Key pluginIdKey = PluginIdChecker.key(pluginId);
			// checks plugin
			checkPluginIdConsistency(pluginIdKey, factory);
			// checks if object
			if (isType(pluginIdKey, ObjectType.OBJECT)) {
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