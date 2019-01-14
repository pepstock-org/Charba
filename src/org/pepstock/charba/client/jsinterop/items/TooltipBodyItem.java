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

import java.util.List;

import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayString;
import org.pepstock.charba.client.jsinterop.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainer;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainerFactory;

/**
 * This object is passed by CHART.JS to the callback to manage tooltip body.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class TooltipBodyItem extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		before,
		lines,
		after
	}

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	public TooltipBodyItem(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns text to render before the body section.
	 * 
	 * @return text to render before the body section.
	 */
	public List<String> getBefore() {
		// gets array from native object
		ArrayString array = getArrayValue(Property.before);
		// returns list
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns all lines of body section.
	 * 
	 * @return all lines of body section.
	 */
	public List<String> getLines() {
		// gets array from native object
		ArrayString array = getArrayValue(Property.lines);
		// returns list
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns text to render after the body section
	 * 
	 * @return text to render after the body section
	 */
	public List<String> getAfter() {
		// gets array from native object
		ArrayString array = getArrayValue(Property.after);
		// returns list
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Inner class to create tooltip body item by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @since 2.0
	 */
	static final class TooltipBodyItemFactory implements NativeObjectContainerFactory<TooltipBodyItem> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.pepstock.charba.client.jsinterop.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.jsinterop
		 * .commons.NativeObject)
		 */
		@Override
		public TooltipBodyItem create(NativeObject nativeObject) {
			return new TooltipBodyItem(nativeObject);
		}
	}

}