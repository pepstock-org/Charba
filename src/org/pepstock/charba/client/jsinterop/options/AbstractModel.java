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
package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainer;

/**
 * Base class for all options, which will wraps a native object and manages the relations about parent and children
 * elements.<br>
 * Provides also a set of methods to manages callbacks and events.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 * 
 * @param <P> parent node class
 * @param <D> defaults provider class
 */
public abstract class AbstractModel<P extends AbstractModel<?, ?>, D> extends NativeObjectContainer {

	private final D defaultValues;

	private final P parent;

	private final Key childKey;

	/**
	 * Creates the object with default provider.<br>
	 * This is the root of elements tree and new native object is created.
	 * 
	 * @param defaultValues default provider
	 */
	protected AbstractModel(D defaultValues) {
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
	protected AbstractModel(P parent, Key childKey, D defaultValues) {
		this(parent, childKey, defaultValues, new NativeObject());
	}

	/**
	 * Creates the object with default provider and the native object.<br>
	 * This is the root of elements tree .
	 * 
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	protected AbstractModel(D defaultValues, NativeObject nativeObject) {
		this(null, null, defaultValues, nativeObject);
	}

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script
	 * properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	protected AbstractModel(P parent, Key childKey, D defaultValues, NativeObject nativeObject) {
		super(nativeObject);
		this.childKey = childKey;
		this.parent = parent;
		this.defaultValues = defaultValues;
	}

	/**
	 * Returns the property name to use to add this element to its parent.
	 * 
	 * @return the childKey or <code>null</code> if is a root element.
	 */
	protected final Key getChildKey() {
		return childKey;
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
	protected final void setEventToModel(AbstractModel<?, ?> model, Key key, CallbackProxy.Proxy proxy) {
		model.setValue(key, proxy);
		// checks if the node is already added to parent
		model.checkAndAddToParent();
	}

	/**
	 * Adds a proxy function (for callbacks) to a model at the specific key.
	 * 
	 * @param model element where adding the function proxy
	 * @param key property name to use to add the function proxy
	 * @param proxy the function proxy instance to add
	 */
	protected final void setCallbackToModel(AbstractModel<?, ?> model, Key key, CallbackProxy.Proxy proxy) {
		model.setValue(key, proxy);
		// checks if the node is already added to parent
		model.checkAndAddToParent();
	}

	/**
	 * Called recursively when a property has been set in the item.<br>
	 * This is mandatory because it could happen that the parent item is not present, therefore it must be added.
	 */
	protected final void checkAndAddToParent() {
		// checks if we are at root element
		// or if the parent hasn't got the key
		if (parent != null && !parent.has(childKey)) {
			// sets the java script of this element into parent
			parent.setValue(childKey, getNativeObject());
			// recursively call to parent of parent
			parent.checkAndAddToParent();
		}
	}

}
