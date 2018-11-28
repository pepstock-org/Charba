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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayObjectContainerList.Factory;
import org.pepstock.charba.client.jsinterop.commons.ArrayString;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainer;

/**
 * This object is passed by CHART.JS to the callback to manage tooltip body.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.items.TooltipModel
 */
public final class TooltipBodyItem extends NativeObjectContainer {
	
	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		before,
		lines,
		after
	}

	/**
	 * @param nativeObject
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
		ArrayString array = getArrayValue(Property.before);
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns all lines of body section.
	 * 
	 * @return all lines of body section.
	 */
	public List<String> getLines() {
		ArrayString array = getArrayValue(Property.lines);
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns text to render after the body section
	 * 
	 * @return text to render after the body section
	 */
	public List<String> getAfter() {
		ArrayString array = getArrayValue(Property.after);
		return ArrayListHelper.unmodifiableList(array);
	}
	
	static class TooltipBodyItemFactory implements Factory<TooltipBodyItem>{
		/* (non-Javadoc)
		 * @see org.pepstock.charba.client.jsinterop.commons.ArrayObjectContainerList.Factory#create(org.pepstock.charba.client.jsinterop.commons.NativeObject)
		 */
		@Override
		public TooltipBodyItem create(NativeObject nativeObject) {
			return new TooltipBodyItem(nativeObject);
		}
	}

}