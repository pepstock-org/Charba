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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainer;

/**
 * Contains all info for every item of tooltip.<br>
 * Created and passed by CHART.JS.<br>
 * It uses into the PLUGINS.
 * 
 * @author Andrea "Stock" Stocchero
 * @vesion 2.0
 */
public final class TooltipPluginItem extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		easing,
		tooltip
	}
	
	private final TooltipNode node;
	
	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	public TooltipPluginItem(NativeObject nativeObject) {
		super(nativeObject);
		// creates sub element
		node = new TooltipNode(getValue(Property.tooltip));
	}

	/**
	 * Returns the total number of animation frames.
	 * 
	 * @return the total number of animation frames. Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#DOUBLE}.
	 */
	public double getEasing() {
		return getValue(Property.easing, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the tooltip model.
	 * 
	 * @return the tooltip model.
	 */
	public TooltipNode getTooltip() {
		return node;
	}
}