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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.Defaults;

/**
 * Implements options for chart with lines to be showed.
 *
 * @author Andrea "Stock" Stocchero
 */
interface HasShowLines {

	/**
	 * Returns a options which will implements show lines and span gaps.
	 * 
	 * @return a options instance
	 */
	ConfigurationOptions getOptions();

	/**
	 * If <code>false</code>, the lines between points are not drawn.
	 * 
	 * @param showLine if <code>false</code>, the lines between points are not drawn.
	 */
	default void setShowLines(boolean showLine) {
		// checks if options is consistent
		if (getOptions() != null) {
			getOptions().getConfiguration().setShowLines(showLine);
		}
	}

	/**
	 * If <code>false</code>, the lines between points are not drawn.
	 * 
	 * @return if <code>false</code>, the lines between points are not drawn.
	 */
	default boolean isShowLines() {
		// checks if options is consistent
		if (getOptions() != null) {
			return getOptions().getConfiguration().isShowLines();
		}
		// if here, options is not consistent
		return Defaults.get().getGlobal().isShowLines();
	}

}