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
package org.pepstock.charba.client;

/**
 * Interface which allows an object to be called to set a native object into chart configuration.<br>
 * The chart configuration are items options, data and plugins.
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @see Configuration
 */
public interface ConfigurationElement {

	/**
	 * Called to enable to load into a configuration object the specific configuration item (by native object).
	 * 
	 * @param configuration chart configuration instance
	 */
	void load(Configuration configuration);
}
