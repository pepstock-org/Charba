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
package org.pepstock.charba.client.data;

import org.pepstock.charba.client.commons.Key;

/**
 * This is an interface to set a dataset has container of fill and stroke style based on point background and border color.<br>
 * Out of the box ONLY {@link LiningDataset} has implemented this interface 
 *  
 * @author Andrea "Stock" Stocchero
 *
 */
public interface HasPointFillStrokeStyles {

	/**
	 * Returns the property which is mapping the point fill style.
	 * 
	 * @return the property which is mapping the point fill style
	 */
	Key getPointFillStyleProperty();

	/**
	 * Returns the property which is mapping the point stroke style.
	 * 
	 * @return the property which is mapping the point stroke style
	 */
	Key getPointStrokeStyleProperty();

}
