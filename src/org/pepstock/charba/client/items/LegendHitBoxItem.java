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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.events.ChartNativeEvent;

/**
 * This is a wrapper of the CHART.JS item which contains the legends hit box.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class LegendHitBoxItem extends SizeItem {

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
	 * @return the padding left in pixel. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getLeft() {
		return getValue(Position.LEFT, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the padding top in pixel.
	 * 
	 * @return the padding top in pixel. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getTop() {
		return getValue(Position.TOP, UndefinedValues.INTEGER);
	}

	/**
	 * Returns <code>true</code> if the chart event is inside of this box, otherwise <code>false</code>.
	 * 
	 * @param event event to check if inside the box
	 * @return <code>true</code> if the chart event is inside of this box, otherwise <code>false</code>
	 */
	public boolean isInside(ChartNativeEvent event) {
		// checks is properties are consistent
		if (has(Position.LEFT) && has(Position.TOP)) {
			// checks X
			boolean isX = event.getLayerX() >= getLeft() && event.getLayerX() <= (getLeft() + getWidth());
			// checks Y
			boolean isY = event.getLayerY() >= getTop() && event.getLayerY() <= (getTop() + getHeight());
			;
			return isX && isY;
		}
		// if here, no properties, then false
		return false;
	}

	/**
	 * Inner class to create legend hit box item by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	static final class LegendHitBoxItemFactory implements NativeObjectContainerFactory<LegendHitBoxItem> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.
		 * NativeObject)
		 */
		@Override
		public LegendHitBoxItem create(NativeObject nativeObject) {
			return new LegendHitBoxItem(nativeObject);
		}

	}

}
