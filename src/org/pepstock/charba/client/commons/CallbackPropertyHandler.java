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
import java.util.Map.Entry;
import java.util.Set;

import org.pepstock.charba.client.commons.CallbackProxy.Proxy;
import org.pepstock.charba.client.dom.DOMBuilder;
import org.pepstock.charba.client.items.Undefined;

/**
 * It is managing property where can set a callback.<br>
 * It uses a cache in order to be able to use the callback even if defined at default or chart options level, both for chart or plugin options.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of callback
 *
 */
public final class CallbackPropertyHandler<T> {

	// hash code property prefix.
	private static final String HASHCODE_PROPERTY_SUFFIX = "CharbaCallbackKey";
	// scope property prefix.
	private static final String SCOPE_PROPERTY_SUFFIX = "CharbaCallbackScope";
	// property key to manage and where storing the function callback proxy
	private final Key property;
	// property key used to store the class name plus hash code, as key of callback instance in the cache
	private final Key hashCodeProperty;
	// property key used to store the scope of callback
	private final Key scopeProperty;
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
		StringBuilder hashCodeString = new StringBuilder();
		hashCodeString.append(this.property.value()).append(HASHCODE_PROPERTY_SUFFIX);
		// creates the key to use to store the key of callback
		this.hashCodeProperty = Key.create(hashCodeString.toString());
		// creates the scope string
		StringBuilder scopeString = new StringBuilder();
		scopeString.append(this.property.value()).append(SCOPE_PROPERTY_SUFFIX);
		// creates the key to use to store the scope of callback
		this.scopeProperty = Key.create(scopeString.toString());
	}

	/**
	 * Returns the property key to manage
	 * 
	 * @return the property key to manage
	 */
	public Key getProperty() {
		return property;
	}

	/**
	 * Returns the property key used to store the class name plus hash code, as key of callback instance in the cache.
	 * 
	 * @return the property key used to store the class name plus hash code, as key of callback instance in the cache
	 */
	public Key getHashCodeProperty() {
		return hashCodeProperty;
	}

	/**
	 * Stores the callback in the the cache, storing the proxy function and the hash code property key (unique id of callback) in the native object.
	 * 
	 * @param container container instance of native object to use to store the properties
	 * @param scope the scope of callback, could be the default, chart type options, chart options, plugin objects, data sets
	 * @param callback the java callback instance to cache
	 * @param proxy the proxy java script function to store in the native object
	 */
	public void setCallback(AbstractNode container, String scope, T callback, Proxy proxy) {
		// gets the scope if exists in the object
		String currentScope = manageScope(container, scope);
		// checks if scope is consistent (if yes, also container is consistent)
		if (currentScope != null) {
			// gets hash code property if exists
			String value = container.getValue(hashCodeProperty, Undefined.STRING);
			// checks if hash code property already exists
			// because it could be already defined with another callback
			if (value != null) {
				// removes the scope
				removeScopeFromCallback(value, currentScope);
				// removes the properties
				container.remove(property);
				container.remove(hashCodeProperty);
				container.remove(scopeProperty);
			}
			// checks if callback and proxy are consistent
			if (callback != null && proxy != null) {
				// creates new key
				String key = createKey(callback);
				// checks if key is consistent
				if (key != null) {
					// adds or updates the existing wrapper
					updateOrAddScopeFromCallback(key, callback, currentScope);
					// stores the property
					container.setValueAndAddToParent(property, proxy);
					// stores the hash code property
					container.setValueAndAddToParent(hashCodeProperty, key);
					// stores the property
					container.setValueAndAddToParent(scopeProperty, currentScope);
					// checks if the scope is for chart
					if (DOMBuilder.get().isUniqueChartId(currentScope)) {
						// notify to manager
						CallbackPropertyManager.get().addHandler(this);
					}
				}
			}
		}
	}

	/**
	 * Returns the callback stored in the native object or <code>null</code> if not exists.
	 * 
	 * @param container container instance of native object to use to get the unique key to retrieve the callback from cache
	 * @return the callback stored in the native object or <code>null</code> if not exists
	 */
	public T getCallback(NativeObjectContainer container) {
		return getCallback(container, null);
	}

	/**
	 * Returns the callback stored in the native object or the default value passed as argument, if not exists.
	 * 
	 * @param container container instance of native object to use to get the unique key to retrieve the callback from cache
	 * @param defaultValue default value to return if the callback does not exist
	 * @return the callback stored in the native object or the default value passed as argument, if not exists
	 */
	public T getCallback(NativeObjectContainer container, T defaultValue) {
		// checks if scope and container are consistent
		if (container != null && container.has(hashCodeProperty)) {
			// gets hash code property if exists
			String value = container.getValue(hashCodeProperty, Undefined.STRING);
			// gets the wrappers
			CallbackWrapper<T> wrapper = wrappers.get(value);
			// checks if wrapper is consistent
			if (wrapper != null) {
				// returns if the wrapper exists
				return wrapper.getInstance();
			}
		}
		// if here, arguments are not consistent
		// or the callback is not stored
		// then returns default value
		return defaultValue;
	}

	/**
	 * Updates or/and add the wrapper, with the callback instance, adding the scope.
	 * 
	 * @param key property key used to store the class name plus hash code, as key of callback instance in the cache
	 * @param callback the java callback instance to cache
	 * @param scope the scope of callback, could be the default, chart type options, chart options, plugin objects, data sets
	 */
	private void updateOrAddScopeFromCallback(String key, T callback, String scope) {
		// checks key has been stored
		// creating new wrapper
		// stores the scope
		wrappers.computeIfAbsent(key, mapKey -> new CallbackWrapper<>(callback)).getScopes().add(scope);
	}

	/**
	 * Removes an existing scope from callback wrapper.
	 * 
	 * @param key property key used to store the class name plus hash code, as key of callback instance in the cache
	 * @param scope the scope of callback, could be the default, chart type options, chart options, plugin objects, data sets
	 */
	private void removeScopeFromCallback(String key, String scope) {
		// checks key is consistent and has been stored
		if (key != null && wrappers.containsKey(key)) {
			// gets the wrappers
			CallbackWrapper<T> wrapper = wrappers.get(key);
			// removes scope from wrapper
			// and checks there is any other scope related to the callback
			if (wrapper.getScopes().remove(scope) && wrapper.getScopes().isEmpty()) {
				// if here there is not
				// and then removes the wrapper
				wrappers.remove(key);
			}
		}
	}
	
	/**
	 * Called by {@link CallbackPropertyManager} when a chart is destroy in order to remove the scope (and then teh callback if needed) from cache.
	 * 
	 * @param scope the scope of the callback, is chart id
	 * @return <code>true</code> if this callback handler has got 1 or more scope related to charts.
	 */
	boolean removeChartScope(String scope) {
		// flags to know if there is at least 1 chart scope
		boolean hasChartScope = false;
		// checks if is chart id
		if (DOMBuilder.get().isUniqueChartId(scope)) {
			// list of key to remove from cache
			final Set<String> keysToRemove = new HashSet<>();
			// scans all wrappers to remove scope
			for (Entry<String, CallbackWrapper<T>> entry : wrappers.entrySet()) {
				// removes scope from wrapper
				// and checks there is any other scope related to the callback
				if (entry.getValue().getScopes().remove(scope) && entry.getValue().getScopes().isEmpty()) {
					// if here there is not
					// and then removes the wrapper
					keysToRemove.add(entry.getKey());
				}
				// checks and sets if there is a chart scope
				hasChartScope = entry.getValue().hasChartScope() || hasChartScope;
			}
			// checks if it should remove some empty keys
			for (String key : keysToRemove) {
				// removes from wrappers
				wrappers.remove(key);
			}
		}
		return hasChartScope;
	}

	/**
	 * Creates the property key used to store the class name plus hash code, as key of callback instance in the cache.<br>
	 * <br>
	 * <code>[callbackClassName]-[callbackHashCode]</code> <br>
	 * 
	 * @param callback the java callback instance to cache
	 * @return property key used to store the class name plus hash code, as key of callback instance in the cache
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
	 * Creates the scope which is to be stored in the callback wrapper in order to know where the callback is used.<br>
	 * <br>
	 * <code>[scope]-[incremental]</code> <br>
	 * 
	 * @param container container instance of native object to use to store the properties
	 * @param scope the scope of callback, could be the default, chart type options, chart options, plugin objects, data sets
	 * @return the scope which is to be stored in the callback wrapper in order to know where the callback is used
	 */
	private String manageScope(NativeObjectContainer container, String scope) {
		// checks if container is consistent
		if (container != null) {
			// gets the scope from container
			String currentScope = container.getValue(scopeProperty, scope);
			// checks if the scope is consistent and not
			// a chart ID
			if (!DOMBuilder.get().isUniqueChartId(currentScope)) {
				// creates result with scope
				StringBuilder sb = new StringBuilder(scope);
				// gets the incremental id of container and
				// returns the new scope
				return sb.append(Constants.MINUS).append(container.getIncrementalId()).toString();
			}
			// if here the scope was already stored
			return currentScope;
		}
		// if here the object container is not consistent
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
		
		/**
		 * Checks and returns <code>true</code> if there is a scope related to a chart instance.
		 * 
		 * @return <code>true</code> if there is a scope related to a chart instance
		 */
		private boolean hasChartScope() {
			// scans scopes
			for (String scope : scopes) {
				// checks if is a chart scope
				if (DOMBuilder.get().isUniqueChartId(scope)) {
					// if yes, returns true
					return true;
				}
			}
			// if here there isn't any chart scope
			return false;
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
