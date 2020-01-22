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
package org.pepstock.charba.client.defaults;

import org.pepstock.charba.client.adapters.DateAdapterOptions;
import org.pepstock.charba.client.adapters.DateAdaptersOptionsFactory;

/**
 * Interface to define adapter options defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultAdapters {

	/**
	 * Returns the date adapter options, if exist.<br>
	 * It uses a factory instance to create a date adapter options.<br>
	 * If factory argument is not consistent, <code>null</code> is returned.
	 * 
	 * @param factory factory instance to create a date adapter options
	 * @param <T> type of date adapter options to return
	 * @return date adapter options used to configure the date adapter or an empty object if not exist.<br>
	 *         If factory argument is not consistent, <code>null</code> is returned.
	 */
	<T extends DateAdapterOptions> T getDate(DateAdaptersOptionsFactory<T> factory);
}