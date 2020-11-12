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
 * {@link ZoomPlugin#ID} plugin default options for the abstract element used by pan and zoom options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class DefaultsConfigurationItem implements IsDefaultsConfigurationItem {

	private final DefaultsRange range = new DefaultsRange();

	/**
	 * Creates an empty object.
	 */
	DefaultsConfigurationItem() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.zoom.IsDefaultsConfigurationItem#getRangeMin()
	 */
	@Override
	public final IsDefaultsRange getRangeMin() {
		return range;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.zoom.IsDefaultsConfigurationItem#getRangeMax()
	 */
	@Override
	public final IsDefaultsRange getRangeMax() {
		return range;
	}

}