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

/**
 * Interface to define MAJOR tick object defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultMajor {

	/**
	 * If <code>true</code>, major tick options are used to show major ticks.
	 * 
	 * @return if <code>true</code>, major tick options are used to show major ticks
	 */
	boolean isEnabled();
}