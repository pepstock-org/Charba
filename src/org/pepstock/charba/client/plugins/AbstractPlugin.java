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

import java.util.Collections;
import java.util.Set;

import org.pepstock.charba.client.Plugin;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.enums.Event;

/**
 * Implements a plugin interface to help who will create a plugin do not create all methods.<br>
 * The only method not implemented is <code>getId</code> which must implemented.<br>
 * All <code>onBefore*</code> cancelable methods return <code>true</code>.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractPlugin implements Plugin {

	private final String pluginId;

	/**
	 * Creates the plugin using the id passed as argument.
	 * 
	 * @param pluginId plugin id to use
	 */
	protected AbstractPlugin(String pluginId) {
		// checks if plugin is consistent
		PluginIdChecker.check(pluginId);
		// stores plugin
		this.pluginId = pluginId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.Plugin#getId()
	 */
	@Override
	public final String getId() {
		return pluginId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		return "Plugin [" + getId() + "]";
	}

	/**
	 * Generic options for plugins which want to disable the event catching.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	public static final class DisablingEventCatchingOptions extends AbstractPluginOptions {

		/**
		 * Creates the plugin options, disabling the event catching.
		 * 
		 * @param id plugin id
		 */
		public DisablingEventCatchingOptions(String id) {
			super(Checker.checkAndGetIfValid(id, "Plugin id"), null);
			// resets events do not catch them
			super.setEvents(Collections.emptySet());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.plugins.AbstractPluginOptions#setEvents(org.pepstock.charba.client.enums.Event[])
		 */
		@Override
		public void setEvents(Event... events) {
			// do nothing
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.plugins.AbstractPluginOptions#setEvents(java.util.Set)
		 */
		@Override
		public void setEvents(Set<Event> events) {
			// do nothing
		}

	}
}
