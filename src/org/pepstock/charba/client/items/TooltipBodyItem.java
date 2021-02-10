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

import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;

/**
 * This object is passed by CHART.JS to the callback to manage tooltip body.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class TooltipBodyItem extends NativeObjectContainer {

	// static factory to create a tooltip body item from a native object.
	static final TooltipBodyItemFactory FACTORY = new TooltipBodyItemFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BEFORE("before"),
		LINES("lines"),
		AFTER("after");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}

	}

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	TooltipBodyItem(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns text to render before the body section.
	 * 
	 * @return text to render before the body section.
	 */
	public List<String> getBefore() {
		// gets array from native object
		ArrayString array = getArrayValue(Property.BEFORE);
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
		ArrayString array = getArrayValue(Property.LINES);
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
		ArrayString array = getArrayValue(Property.AFTER);
		// returns list
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Inner class to create tooltip body item by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	static class TooltipBodyItemFactory implements NativeObjectContainerFactory<TooltipBodyItem> {

		/**
		 * To avoid any instatiation
		 */
		private TooltipBodyItemFactory() {
			// do nothing
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons. NativeObject)
		 */
		@Override
		public TooltipBodyItem create(NativeObject nativeObject) {
			return new TooltipBodyItem(nativeObject);
		}
	}

}