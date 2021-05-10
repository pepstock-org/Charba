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
package org.pepstock.charba.client.zoom;

/**
 * {@link ZoomPlugin#ID} plugin default options interface for limits elements.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsDefaultLimits {

	/**
	 * Returns the limit for X scale.
	 * 
	 * @return the limit for X scale
	 */
	default IsDefaultScaleLimit getX() {
		return DefaultScaleLimit.INSTANCE;
	}

	/**
	 * Returns the limit for Y scale.
	 * 
	 * @return the limit for Y scale
	 */
	default IsDefaultScaleLimit getY() {
		return DefaultScaleLimit.INSTANCE;
	}

}