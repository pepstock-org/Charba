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
package org.pepstock.charba.client.labels;

/**
 * It implements default global options if there are and provides all default values for {@link LabelsPlugin#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class DefaultsLabel implements IsDefaultsLabel {

	// defaults options instance
	static final DefaultsLabel INSTANCE = new DefaultsLabel();

	/**
	 * Creates an empty options without any default global options. it will use the constants as default of plugin properties.
	 */
	private DefaultsLabel() {
		// do nothing
	}
}