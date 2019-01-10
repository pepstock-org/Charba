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
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainerFactory;

/**
 * This is a wrapper of the CHART.JS item which contains the legends hit box.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public final class LegendHitBoxItem extends SizeItem{

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	LegendHitBoxItem(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the padding left in pixel.
	 * 
	 * @return the padding left in pixel. Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#INTEGER}.
	 */
	public int getLeft() {
		return getValue(Position.left, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the padding top in pixel.
	 * 
	 * @return the padding top in pixel. Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#INTEGER}.
	 */
	public int getTop() {
		return getValue(Position.top, UndefinedValues.INTEGER);
	}

	/**
	 * Inner class to create legend hit box item by a native object to use in {@link org.pepstock.charba.client.jsinterop.commons.ArrayObjectContainerList}.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @since 2.0
	 */
	static class LegendHitBoxItemFactory implements NativeObjectContainerFactory<LegendHitBoxItem>{

		/* (non-Javadoc)
		 * @see org.pepstock.charba.client.jsinterop.commons.ArrayObjectContainerList.Factory#create(org.pepstock.charba.client.jsinterop.commons.NativeObject)
		 */
		@Override
		public LegendHitBoxItem create(NativeObject nativeObject) {
			return new LegendHitBoxItem(nativeObject);
		}
		
	}
	
}
