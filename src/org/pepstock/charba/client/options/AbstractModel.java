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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultPadding;
import org.pepstock.charba.client.items.Undefined;

/**
 * Base class for all options, which will wraps a native object and manages the relations about parent and children elements.<br>
 * Provides also a set of methods to manages callbacks and events.
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @param <P> parent node class
 * @param <D> defaults provider class
 */
public abstract class AbstractModel<P extends AbstractModel<?, ?>, D> extends AbstractNode {

	private final D defaultValues;

	private final P parent;

	/**
	 * Creates the object with default provider.<br>
	 * This is the root of elements tree and new native object is created.
	 * 
	 * @param defaultValues default provider
	 */
	AbstractModel(D defaultValues) {
		this(null, null, defaultValues);
	}

	/**
	 * Creates the object with parent element, the key of this element and default values.<br>
	 * The native object to map java script properties will be created empty.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 */
	AbstractModel(P parent, Key childKey, D defaultValues) {
		this(parent, childKey, defaultValues, null);
	}

	/**
	 * Creates the object with default provider and the native object.<br>
	 * This is the root of elements tree .
	 * 
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	AbstractModel(D defaultValues, NativeObject nativeObject) {
		this(null, null, defaultValues, nativeObject);
	}

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	AbstractModel(P parent, Key childKey, D defaultValues, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
		// checks if default value is consistent
		this.defaultValues = checkDefaultValuesArgument(defaultValues);
		this.parent = parent;
	}

	/**
	 * Returns the parent element.
	 * 
	 * @return the parent or <code>null</code> if is a root element.
	 */
	protected final P getParent() {
		return parent;
	}

	/**
	 * Returns the default provider instance.
	 * 
	 * @return the default provider instance.
	 */
	protected final D getDefaultValues() {
		return defaultValues;
	}

	/**
	 * Adds a proxy function (for events) to a model at the specific key.
	 * 
	 * @param model element where adding the function proxy
	 * @param key property name to use to add the function proxy
	 * @param proxy the function proxy instance to add
	 */
	protected final void setEventToModel(AbstractNode model, Key key, CallbackProxy.Proxy proxy) {
		setInternalCallbackToModel(model, key, proxy);
	}

	/**
	 * Adds a proxy function (for callbacks) to a model at the specific key.
	 * 
	 * @param model element where adding the function proxy
	 * @param key property name to use to add the function proxy
	 * @param proxy the function proxy instance to add
	 */
	protected final void setCallbackToModel(AbstractNode model, Key key, CallbackProxy.Proxy proxy) {
		setInternalCallbackToModel(model, key, proxy);
	}

	/**
	 * Resets a callback previously set overriding it with a options node at the specific key.
	 * 
	 * @param model element where resetting the callback
	 * @param key property name to use to reset the callback
	 * @param options the object to store overriding the callback
	 */
	protected final void resetCallbackToModel(AbstractNode model, Key key, AbstractNode options) {
		resetInternalCallbackToModel(model, key, options);
	}
	
	/**
	 * Loads the padding size or object from the abstract model, replacing the property, if is a number, with the object which is returned.
	 * 
	 * @param property property where the padding is stored inside the model.
	 * @param defaultPadding default padding instance
	 * @return a padding object, attached to this model
	 */
	protected final Padding loadPadding(Key property, IsDefaultPadding defaultPadding) {
		// checks key
		Key.checkIfValid(property);
		// loads padding as number
		int paddingSize = getValue(property, Undefined.INTEGER);
		// checks if padding is a number
		if (paddingSize != Undefined.INTEGER) {
			// removes the current node
			// because it must update in an object
			remove(property);
		}
		// loads and returns the padding
		return new Padding(this, property, defaultPadding, getValue(property), paddingSize);
	}
}
