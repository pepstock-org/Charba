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
package org.pepstock.charba.client.commons;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.pepstock.charba.client.commons.CallbackProxy.Proxy;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * It is managing property where can set a callback.<br>
 * It uses a cache in order to be able to use the callbacks even if defined at default or chart options level, both for chart or plugin options.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of callback
 *
 */
public class CallbackPropertyHandler<T> {

	// hash code property prefix.
	private static final String HASHCODE_PROPERTY_SUFFIX = "CharbaCallbackKey";
	// property key to manage and where storing the function callback proxy
	private final Key property;
	// property key used to store the class name plus hash code, as key of callback instance into cache
	private final Key hashCodeProperty;
	// caches with the callback instance
	// K = unique key (className-hashCode), V = callback wrapper
	private final Map<String, CallbackWrapper<T>> wrappers = new HashMap<>();

	/**
	 * Creates the object storing the property key to manage.
	 * 
	 * @param property the property key to manage
	 */
	public CallbackPropertyHandler(Key property) {
		// checks the key consistency
		this.property = Key.checkAndGetIfValid(property);
		// creates the hash code string
		StringBuilder sb = new StringBuilder();
		sb.append(this.property.value()).append(HASHCODE_PROPERTY_SUFFIX);
		// creates the key to use to store the key of callback
		this.hashCodeProperty = Key.create(sb.toString());
	}

	/**
	 * Returns the property key to manage
	 * 
	 * @return the property key to manage
	 */
	public final Key getProperty() {
		return property;
	}

	/**
	 * Returns the property key used to store the class name plus hash code, as key of callback instance into cache.
	 * 
	 * @return the property key used to store the class name plus hash code, as key of callback instance into cache
	 */
	public final Key getHashCodeProperty() {
		return hashCodeProperty;
	}

	/**
	 * Stores the callback into the cache, storing the proxy function and the hash code property key (unique id of callback) into native object.
	 * 
	 * @param container container instance of native object to use to store the properties
	 * @param scope the scope of callback, could be the default, chart type options, chart options, plugin objects, datasets
	 * @param callback the java callback instance to cache
	 * @param proxy the proxy java script function to store into native object
	 */
	public void setCallback(NativeObjectContainer container, String scope, T callback, Proxy proxy) {
		// checks if scope and container are consistent
		if (scope != null && container != null) {
			// gets hash code property if exists
			String value = container.getValue(hashCodeProperty, UndefinedValues.STRING);
			// checks if hash code property already exists
			// because it could be already defined with another callback
			if (value != null) {
				// removes the scope
				removeScopeFromCallback(value, scope);
				// removes the properties
				container.remove(property);
				container.remove(hashCodeProperty);
			}
			// checks if callbacks and proxy are consistent
			if (callback != null && proxy != null) {
				// creates new key
				String key = createKey(callback);
				// checks if key is consistent
				if (key != null) {
					// adds or updates the existing wrapper
					updateOrAddScopeFromCallback(key, callback, scope);
					// stores the hash code property
					container.setValue(hashCodeProperty, key);
					// stores the property
					container.setValue(property, proxy);
				}
			}
		}
	}

	/**
	 * Returns the callback stored into native object or <code>null</code> if not exists.
	 * 
	 * @param container container instance of native object to use to get the unique key to retrieve the callback from cache
	 * @return the callback stored into native object or <code>null</code> if not exists
	 */
	public T getCallback(NativeObjectContainer container) {
		return getCallback(container, null);
	}

	/**
	 * Returns the callback stored into native object or the default value passed as argument, if not exists.
	 * 
	 * @param container container instance of native object to use to get the unique key to retrieve the callback from cache
	 * @param defaultValue default value to return if the callback does not exist
	 * @return the callback stored into native object or the default value passed as argument, if not exists
	 */
	public T getCallback(NativeObjectContainer container, T defaultValue) {
		// checks if scope and container are consistent
		if (container != null && container.has(hashCodeProperty)) {
			// gets hash code property if exists
			String value = container.getValue(hashCodeProperty, UndefinedValues.STRING);
			// gets the wrappers
			CallbackWrapper<T> wrapper = wrappers.get(value);
			// checks if wrapper is consistent
			if (wrapper != null) {
				// returns if the wrapper exists
				return wrapper.getInstance();
			}
		}
		// if here, arguments are not consistent
		// or the callbacks is not stored
		// then returns default value
		return defaultValue;
	}

	/**
	 * Updates or/and add the wrapper, with the callback instance, adding the scope.
	 * 
	 * @param key property key used to store the class name plus hash code, as key of callback instance into cache
	 * @param callback the java callback instance to cache
	 * @param scope the scope of callback, could be the default, chart type options, chart options, plugin objects, datasets
	 */
	private void updateOrAddScopeFromCallback(String key, T callback, String scope) {
		// checks key has been stored
		if (!wrappers.containsKey(key)) {
			CallbackWrapper<T> newWrapper = new CallbackWrapper<>(callback);
			// stores wrappers
			wrappers.put(key, newWrapper);
		}
		// gets the wrappers
		CallbackWrapper<T> wrapper = wrappers.get(key);
		// stores the scope
		wrapper.getScopes().add(scope);
	}

	/**
	 * Removes an existing scope from callback wrapper.
	 * 
	 * @param key property key used to store the class name plus hash code, as key of callback instance into cache
	 * @param scope the scope of callback, could be the default, chart type options, chart options, plugin objects, datasets
	 */
	private void removeScopeFromCallback(String key, String scope) {
		// checks key is consistent and has been stored
		if (key != null && wrappers.containsKey(key)) {
			// gets the wrappers
			CallbackWrapper<T> wrapper = wrappers.get(key);
			// removes scope from wrapper
			if (wrapper.getScopes().remove(scope) && wrapper.getScopes().isEmpty()) {
				// if there is any other scope related to the callback
				// if here there is not
				// and then removes the wrapper
				wrappers.remove(key);
			}
		}
	}

	/**
	 * Creates the property key used to store the class name plus hash code, as key of callback instance into cache.<br>
	 * <br>
	 * <code>[callbackClassName]-[callbackHashCode]</code> <br>
	 * 
	 * @param callback the java callback instance to cache
	 * @return property key used to store the class name plus hash code, as key of callback instance into cache
	 */
	private String createKey(Object callback) {
		// checks if callback is consistent
		if (callback != null) {
			// creates the unique key for callback
			StringBuilder sb = new StringBuilder(callback.getClass().getName());
			return sb.append(Constants.MINUS).append(callback.hashCode()).toString();
		}
		// if callback is not consistent, returns null
		return null;
	}

	/**
	 * Internal class to wrap the java callback instance and its scopes.<br>
	 * This is the value type of the map.
	 * 
	 * @author Andrea "Stock" Stocchero
	 * @param <T> type of callback
	 * 
	 */
	private static final class CallbackWrapper<T> {

		// callback instance
		private final T instance;
		// set with all scopes where the callback is defined
		private final Set<String> scopes = new HashSet<>();

		/**
		 * Creates the wrapper with the java callback instance.
		 * 
		 * @param instance the java callback instance
		 */
		private CallbackWrapper(T instance) {
			this.instance = instance;
		}

		/**
		 * Returns the java callback instance.
		 * 
		 * @return the java callback instance
		 */
		private T getInstance() {
			return instance;
		}

		/**
		 * Returns the scope of callback, could be the default, chart type options, chart options, plugin objects, datasets.
		 * 
		 * @return the scope of callback, could be the default, chart type options, chart options, plugin objects, datasets
		 */
		private Set<String> getScopes() {
			return scopes;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "CallbackWrapper [instance=" + instance.hashCode() + ", scopes=" + scopes + "]";
		}

	}

}
