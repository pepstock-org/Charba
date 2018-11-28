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
package org.pepstock.charba.client.jsinterop.items;

import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.jsinterop.commons.ArrayObjectContainerList.Factory;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

/**
 * JavaScript object which contains the legends hit box size.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LegendHitBoxItem extends SizeItem{

	/**
	 * @param nativeObject
	 */
	LegendHitBoxItem(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the padding left in pixel.
	 * 
	 * @return the padding left in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getLeft() {
		return getValue(Position.left, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the padding top in pixel.
	 * 
	 * @return the padding top in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getTop() {
		return getValue(Position.top, UndefinedValues.INTEGER);
	}

	static class LegendHitBoxItemFactory implements Factory<LegendHitBoxItem>{

		/* (non-Javadoc)
		 * @see org.pepstock.charba.client.jsinterop.commons.ArrayObjectContainerList.Factory#create(org.pepstock.charba.client.jsinterop.commons.NativeObject)
		 */
		@Override
		public LegendHitBoxItem create(NativeObject nativeObject) {
			return new LegendHitBoxItem(nativeObject);
		}
		
	}
	
}
