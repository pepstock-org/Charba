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

import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.StandardKey;
import org.pepstock.charba.client.plugins.InvalidPluginIdException;
import org.pepstock.charba.client.plugins.PluginIdChecker;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Defintions about how elements appear in the tooltip, hovering the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Plugins extends JavaScriptObjectContainer {

	/**
	 * Empty constructor to reduce its visibility
	 */
	Plugins() {
	}

	/**
	 * FIXME
	 * 
	 * @param mode which elements appear in the tooltip.
	 * @see org.pepstock.charba.client.enums.InteractionMode
	 */
	public void setOptions(String pluginId, JavaScriptObject options) throws InvalidPluginIdException {
		// checks the plugin id
		PluginIdChecker.check(pluginId);
		Key key = new StandardKey(pluginId);
		if (options == null){
			removeIfExists(key);
		} else {
			setValue(key, options);
		}
	}

	/**
	 * FIXME
	 * 
	 * @return which elements appear in the tooltip. Default is {@link org.pepstock.charba.client.defaults.global.Hover#getMode()}.
	 * @see org.pepstock.charba.client.enums.InteractionMode
	 */
	public JavaScriptObject getOptions(String pluginId) throws InvalidPluginIdException{
		// checks the plugin id
		PluginIdChecker.check(pluginId);
		return getValue(new StandardKey(pluginId));
	}

}