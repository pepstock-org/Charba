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

import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.Scriptable;
import org.pepstock.charba.client.callbacks.ScriptableFunctions.ProxyObjectCallback;
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
import org.pepstock.charba.client.data.Dataset.CanvasObjectKey;

/**
 * Common utility to manage inside the configuration items, the canvas objects (pattern or gradient), set to the specific properties of the elements.<br>
 * It stores the canvas object information in the a native object added to Charba configuration, on specific property names for Charba.<br>
 * The canvas object are stored in the native object by the "original" property names to use to configure CHART.JS.
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

	// data set instance
	private final Dataset dataset;
	// maps with all callback wrapper
	private final Map<String, CallbackWrapper> callbackWrappers = new HashMap<>();
	// flag to know if some objects are changed
	// needed to recalculate the canvas object at runtime
	private boolean changed = false;

	/**
	 * Creates the object by an empty native java script object and stores the data set instance it belongs to.
	 * 
	 * @param dataset the data set instance it belongs to
	 */
	AbstractContainer(Dataset dataset) {
		super();
		// stores data set
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
	 * Creates a callback to manage the canvas object.<br>
	 * This is delegated to the implementation of container.
	 * 
	 * @param key the property related to the canvas object to set in the dataset
	 * @return a callback to manage the canvas object
	 */
	abstract AbstractCanvasObjectCallback<T> createCallback(CanvasObjectKey key);

	/**
	 * Stores and array of canvas object in the native java script object.
	 * 
	 * @param key property name to use to stored it.
	 * @param objects array of canvas object
	 * @param defaultValue default value for the data set property, being a color, always as string
	 */
	final void setObjects(CanvasObjectKey key, ArrayObject objects, String defaultValue) {
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
	 * Returns the function <code>proxy</code> implemented in the additional java script source, created for the specific key.
	 * 
	 * @param key the property related to the canvas object to set in the dataset
	 * @return the proxy function <code>proxy</code> implemented in the additional java script source
	 */
	final CallbackProxy.Proxy getProxy(Key key) {
		// checks key consistency and
		// checks if wrapper has been stored
		if (callbackWrappers.containsKey(Key.checkAndGetIfValid(key).value())) {
			// returns the callback proxy
			return callbackWrappers.get(key.value()).getProxy();
		}
		// if here, the callback wrapper does not exist, returns null
		return null;
	}

	/**
	 * Internal class to wrap the data set callack to manage a key of a dataset.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static final class CallbackWrapper {

		// ---------------------------
		// -- CALLBACKS PROXIES ---
		// ---------------------------
		// callback proxy to invoke the background color function
		private final CallbackProxy<ProxyObjectCallback> callbackProxy = JsHelper.get().newCallbackProxy();
		// data set instance
		private final Dataset dataset;
		// property instance
		private final CanvasObjectKey property;
		// callback instance
		private final Scriptable<Object, DatasetContext> callback;
		// default color
		private final String defaultValue;

		/**
		 * Creates the object, getting all arguments n order to manage the data set callback.
		 * 
		 * @param dataset data set instance where the callback must be stored
		 * @param property the property related to the canvas object to set in the data set
		 * @param callback data set callback instance to manage the color
		 * @param defaultValue default value for the data set property, being a color, always as string
		 */
		private CallbackWrapper(Dataset dataset, CanvasObjectKey property, Scriptable<Object, DatasetContext> callback, String defaultValue) {
			this.dataset = dataset;
			this.property = property;
			this.callback = callback;
			this.defaultValue = defaultValue;
			// -------------------------------
			// -- SET CALLBACKS to PROXIES ---
			// -------------------------------
			// sets function to proxy callback in order to invoke the java interface
			this.callbackProxy.setCallback(context -> getDataset().invokeColorCallback(dataset.createContext(context), getCallback(), getProperty(), getDefaultValue()));
		}

		/**
		 * Returns the function <code>proxy</code> implemented in the additional java script source, created for the specific key.
		 * 
		 * @return the function <code>proxy</code> implemented in the additional java script source, created for the specific key
		 */
		CallbackProxy.Proxy getProxy() {
			return callbackProxy.getProxy();
		}

		/**
		 * Returns the data set instance where the callback must be stored.
		 * 
		 * @return the data set instance where the callback must be stored
		 */
		private Dataset getDataset() {
			return dataset;
		}

		/**
		 * Returns the property related to the canvas object to set in the dataset.
		 * 
		 * @return the property related to the canvas object to set in the dataset
		 */
		private CanvasObjectKey getProperty() {
			return property;
		}

		/**
		 * Returns the data set callback instance to manage the color.
		 * 
		 * @return the data set callback instance to manage the color
		 */
		private Scriptable<Object, DatasetContext> getCallback() {
			return callback;
		}

		/**
		 * Returns the default value for the data set property, being a color.
		 * 
		 * @return default value for the data set property, being a color
		 */
		private String getDefaultValue() {
			return defaultValue;
		}

	}

}
