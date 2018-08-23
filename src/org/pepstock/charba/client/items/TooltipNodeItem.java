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

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;

/**
 * This item contains the new size of the chart after it has been resized.<br>
 * This object has been created ONLY when a resize event occurs.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.events.ChartResizeEvent
 * @see org.pepstock.charba.client.events.ChartResizeEventHandler
 */
public final class TooltipNodeItem extends JavaScriptObjectContainer {
	
	private final TooltipModel model;
	
	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		_model
	}
	
	TooltipNodeItem(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
		model = new TooltipModel((GenericJavaScriptObject)getValue(Property._model));
		
	}

	/**
	 * @return the model
	 */
	public final TooltipModel getModel() {
		return model;
	}

}