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
package org.pepstock.charba.client.enums;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.plugins.AbstractPluginOptionsFactory;

/**
 * Enumeration with all possible plugin options scope, where they are set to the chart or dataset.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 * @see AbstractPluginOptionsFactory
 */
public enum PluginOptionsScope implements Key
{
	/**
	 * Represents a plugin options set globally.
	 */
	global,
	/**
	 * Represents a plugin options set to all charts of the same type.
	 */
	chartType
}