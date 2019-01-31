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
package org.pepstock.charba.client.colors;

import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * Base object for pattern and gradient instances, based on canvas element.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class CanvasObject extends NativeObjectContainer {

	private final static AtomicInteger counter = new AtomicInteger(0);

	/**
	 * Name of properties of native object. ALL INTERNAL USE ONLY
	 */
	private enum Property implements Key
	{
		_charbaObjectID
	}

	/**
	 * Creates an empty canvas object.
	 */
	CanvasObject() {
		super();
		// increments the id
		// unique for every canvas object
		// stores the ID
		setValue(Property._charbaObjectID, counter.getAndIncrement());
	}

	/**
	 * Creates a canvas object by a native object.
	 * 
	 * @param nativeObject native object which will be wrapped.
	 */
	CanvasObject(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the unique canvas id.
	 * 
	 * @return the unique canvas id.
	 */
	public final int getId() {
		return getValue(Property._charbaObjectID, UndefinedValues.INTEGER);
	}

}
