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
package org.pepstock.charba.client.utils.toast;

import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;

/**
 * Defines a toast progress bar type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsProgressBarType extends Key {

	/**
	 * Returns the background {@link IsColor} of the progress bar.
	 * 
	 * @return the background {@link IsColor} of the progress bar
	 */
	IsColor getBackgroundColor();

	/**
	 * Returns the background {@link Gradient} of the toast.
	 * 
	 * @return the background {@link Gradient} of the toast
	 */
	Gradient getBackgroundAsGradient();

}