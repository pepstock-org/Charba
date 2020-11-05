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
package org.pepstock.charba.client.data;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pepstock.charba.client.callbacks.Scriptable;
import org.pepstock.charba.client.callbacks.ScriptableContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions;
import org.pepstock.charba.client.colors.CanvasObject;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;

/**
 * Common utility to manage inside the configuration items, the canvas objects (pattern or gradient), set to the specific properties of the elements.<br>
 * It stores the canvas object information into a native object added to Charba configuration, on specific property names for Charba.<br>
 * The canvas object are stored into native object by the "original" property names to use to configure CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 * @param <T> type of canvas object (pattern or gradient) to manage
 * @see Pattern
 * @see Gradient
 * 
 * @see PatternsContainer
 * @see GradientsContainer
 */
abstract class AbstractContainer<T extends CanvasObject> extends NativeObjectContainer {

	// dataset instance
	private final Dataset dataset;
	// maps with all callback wrapper
	private final Map<String, CallbackWrapper> callbackWrappers = new HashMap<>();
	// flag to know if some objects are changed
	// needed to recalculate the canvas object at runtime
	private boolean changed = false;

	/**
	 * Creates the object by an empty native java script object and stores the dataset instance it belongs to.
	 * 
	 * @param dataset the dataset instance it belongs to
	 */
	AbstractContainer(Dataset dataset) {
		super();
		// redefines hashcode in order do not have
		// the property $H for hashcode
		super.redefineHashcode();
		// stores dataset
		this.dataset = dataset;
	}

	/**
	 * Returns <code>true</code> if some canvas objects are changed, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if some canvas objects are changed, otherwise <code>false</code>.
	 */
	final boolean isChanged() {
		return changed;
	}

	/**
	 * Sets <code>true</code> if some canvas objects are changed, otherwise <code>false</code>.
	 * 
	 * @param changed the changed to set
	 */
	final void setChanged(boolean changed) {
		this.changed = changed;
	}

	/**
	 * Returns the factory needed to creates canvas objects from a native java script object.
	 * 
	 * @return the factory needed to creates canvas objects
	 */
	abstract NativeObjectContainerFactory<T> getFactory();
	
	/**
	 * FIXME
	 * @param property
	 * @return
	 */
	abstract AbstractCanvasObjectCallback<T> createCallback(Key property);

	/**
	 * Stores and array of canvas object into native java script object.
	 * 
	 * @param key property name to use to stored it.
	 * @param objects array of canvas object
	 * @param defaultValue FIXME
	 */
	final void setObjects(Key key, ArrayObject objects, String defaultValue) {
		// checks key
		// checks if the value is consistent
		if (objects != null) {
			// stores the array
			setArrayValue(key, objects);
			// creates a callback wrapper
			// checks if callback does not exist
			if (!callbackWrappers.containsKey(key.value())) {
				// creates and stores a wrapper
				callbackWrappers.put(Key.checkAndGetIfValid(key).value(), new CallbackWrapper(dataset, key, createCallback(key), defaultValue));
			}
			// changes the flag
			changed = true;
		} else {
			// if null, remove the key and its value
			// if exists
			removeObjects(key);
		}
	}

	/**
	 * Returns a unmodifiable list of canvas objects.
	 * 
	 * @param key property name to use to get it.
	 * @return a unmodifiable list of canvas objects.
	 */
	final List<T> getObjects(Key key) {
		ArrayObject array = getArrayValue(key);
		return ArrayListHelper.unmodifiableList(array, getFactory());
	}

	/**
	 * Returns <code>true</code> if there is a canvas object stored by passed key, otherwise <code>false</code>.
	 * 
	 * @param key property name to use to get it.
	 * @return <code>true</code> if there is a canvas object stored by passed key, otherwise <code>false</code>.
	 */
	final boolean hasObjects(Key key) {
		return has(key);
	}

	/**
	 * Removes a stored canvas object by its property name, if exist.
	 * 
	 * @param key property name to use to remove it.
	 */
	final void removeObjects(Key key) {
		// checks key
		Key.checkAndGetIfValid(key);
		// checks if there is
		if (has(key)) {
			// and then remove
			remove(key);
			// removes wrappers
			callbackWrappers.remove(Key.checkAndGetIfValid(key).value());
			// changes flag
			changed = true;
		}
	}

	/**
	 * Returns <code>true</code> if there is at least a stored canvas object, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if there is at least a stored canvas object, otherwise <code>false</code>.
	 */
	final boolean isEmpty() {
		return super.empty();
	}

	/**
	 * Returns the unmodifiable list of property names of the native java script object.
	 * 
	 * @return the unmodifiable list of property names
	 */
	final List<Key> getKeys() {
		return Collections.unmodifiableList(keys());
	}
	
	/**
	 * FIXME
	 * @param key
	 * @return
	 */
	final CallbackProxy.Proxy getCallbackProxy(Key key) {
		// checks key consistency and
		// checks if wrapper has been stored
		if (callbackWrappers.containsKey(Key.checkAndGetIfValid(key).value())) {
			// returns the callback proxy
			return callbackWrappers.get(key.value()).getCallbackProxy().getProxy();
		}
		// if here, the callback wrapper does not exist, returns null
		return null;
	}
	
	/**
	 * FIXME
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static final class CallbackWrapper {
		
		// ---------------------------
		// -- CALLBACKS PROXIES ---
		// ---------------------------
		// callback proxy to invoke the background color function
		private final CallbackProxy<ScriptableFunctions.ProxyObjectCallback> callbackProxy = JsHelper.get().newCallbackProxy();
		// dataset instance
		private final Dataset dataset;
		// property instance
		private final Key property;
		// callback instance
		private final Scriptable<Object> callback;
		// default color
		private final String defaultValue;
		
		/**
		 * FIXME
		 */
		private CallbackWrapper(Dataset dataset, Key property, Scriptable<Object> callback, String defaultValue) {
			this.dataset = dataset;
			this.property = property;
			this.callback = callback;
			this.defaultValue = defaultValue;
			// -------------------------------
			// -- SET CALLBACKS to PROXIES ---
			// -------------------------------
			// gets value calling callback
			// FIXME has Pattern always a true is not correct
			callbackProxy.setCallback((contextFunction, context) -> getDataset().invokeColorCallback(new ScriptableContext(new DataEnvelop<>(context)), getCallback(), getProperty(), getDefaultValue(), true));
		}

		/**
		 * @return the callbackProxy
		 */
		CallbackProxy<ScriptableFunctions.ProxyObjectCallback> getCallbackProxy() {
			return callbackProxy;
		}

		/**
		 * @return the dataset
		 */
		private Dataset getDataset() {
			return dataset;
		}

		/**
		 * @return the property
		 */
		private Key getProperty() {
			return property;
		}

		/**
		 * @return the callback
		 */
		private Scriptable<Object> getCallback() {
			return callback;
		}

		/**
		 * @return the defaultValue
		 */
		private String getDefaultValue() {
			return defaultValue;
		}
		
		
	}

}
