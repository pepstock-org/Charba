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
package org.pepstock.charba.client.jsinterop.defaults.globals;

/**
 * Singleton builder of defaults options, with or without scales.
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 */
public final class DefaultsBuilder {

	// singleton instance
	private static final DefaultsBuilder INSTANCE  =new DefaultsBuilder();
	// the defaults options without scales
	private final DefaultOptions noneScale = new DefaultOptions();
	// the defaults options with scales
	private final DefaultScaledOptions multiScales = new DefaultScaledOptions();
	
	/**
	 * To avoid any instantiation
	 */
	private DefaultsBuilder() {
		// do nothing
	}

	/**
	 * Singleton method to return the instance
	 * 
	 * @return default builder instance
	 */
	public static DefaultsBuilder get() {
		return INSTANCE;
	}

	/**
	 * Returns the default options without scales.
	 * 	
	 * @return the default options without scales.
	 */
	public DefaultOptions getOptions() {
		return noneScale;
	}
	
	/**
	 * Returns the default options with scales.
	 * 	
	 * @return the default options with scales.
	 */
	public DefaultScaledOptions getScaledOptions() {
		return multiScales;
	}

}
