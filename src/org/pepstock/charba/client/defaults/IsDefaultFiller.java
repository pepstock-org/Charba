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

import org.pepstock.charba.client.enums.DefaultPluginId;
import org.pepstock.charba.client.enums.DrawTime;

/**
 * Interface to define {@link DefaultPluginId#FILLER} plugin defaults.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultFiller {

	/**
	 * Returns <code>true</code> if the fill area will be recursively extended to the visible target defined by the fill value of hidden data set targets.
	 * 
	 * @return <code>true</code> if the fill area will be recursively extended to the visible target defined by the fill value of hidden data set targets
	 */
	boolean isPropagate();

	/**
	 * Returns the draw time which defines when the filling will be applied.
	 * 
	 * @return the draw time which defines when the filling will be applied
	 */
	DrawTime getDrawTime();

}
