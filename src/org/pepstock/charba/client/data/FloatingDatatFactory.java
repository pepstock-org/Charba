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

import org.pepstock.charba.client.commons.ArrayDouble;
import org.pepstock.charba.client.commons.NativeArrayContainerFactory;

/**
 * Factory to create a floating data from a native array, used for array container lists.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class FloatingDatatFactory implements NativeArrayContainerFactory<ArrayDouble, FloatingData> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.NativeArrayContainerFactory#create(org.pepstock.charba.client.commons.Array)
	 */
	@Override
	public FloatingData create(ArrayDouble nativeArray) {
		return new FloatingData(nativeArray);
	}

}
