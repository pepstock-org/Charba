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
import org.pepstock.charba.client.utils.Window;

/**
 * FIXME
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class CallbackPropertyHandler<T> {

	private static final String HASHCODE_PROPERTY_SuFFIX = "CharbaCallbackKey";

	private final Key property;

	private final Key hashCodeProperty;

	private final Map<String, CallbackWrapper<T>> wrappers = new HashMap<>();

	public CallbackPropertyHandler(Key property) {
		this.property = Key.checkAndGetIfValid(property);
		// creates the hash code string
		StringBuilder sb = new StringBuilder();
		sb.append(this.property.value()).append(HASHCODE_PROPERTY_SuFFIX);
		// creates the key to use to store the key of callback
		this.hashCodeProperty = Key.create(sb.toString());
	}

	/**
	 * Returns the property to manage
	 * 
	 * @return the property
	 */
	public Key getProperty() {
		return property;
	}

	/**
	 * @return the hashCodeProperty
	 */
	public Key getHashCodeProperty() {
		return hashCodeProperty;
	}

	/**
	 * 
	 * @param container
	 * @param scope
	 * @param callback
	 * @param proxy
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
				if (!Constants.EMPTY_STRING.equalsIgnoreCase(key)) {
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

	public T getCallback(NativeObjectContainer container, String scope) {
		return getCallback(container, scope, null);
	}

	public T getCallback(NativeObjectContainer container, String scope, T defaultValue) {
		// checks if scope and container are consistent
		if (container != null && container.has(hashCodeProperty)) {
			// gets hash code property if exists
			String value = container.getValue(hashCodeProperty, UndefinedValues.STRING);
			// gets the wrappers
			CallbackWrapper<T> wrapper = wrappers.get(value);
			// checks if scope is sotres
			if (wrapper != null && wrapper.getScopes().contains(scope)) {

				// FIXME
				Window.getConsole().log("container", container);
				Window.getConsole().log("wrapper", wrapper.toString());

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
	 * 
	 * @param key
	 * @param scope
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
	 * 
	 * @param key
	 * @param scope
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

	private String createKey(Object callback) {
		// checks if callback is consistent
		if (callback != null) {
			StringBuilder sb = new StringBuilder();
			return sb.append(callback.getClass()).append(Constants.MINUS).append(callback.hashCode()).toString();
		}
		return Constants.EMPTY_STRING;
	}

	/**
	 * FIXME
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 * @param <T>
	 */
	private static final class CallbackWrapper<T> {

		private final T instance;

		private final Set<String> scopes = new HashSet<>();

		private CallbackWrapper(T instance) {
			this.instance = instance;
		}

		/**
		 * @return the instance
		 */
		private T getInstance() {
			return instance;
		}

		/**
		 * @return the scopes
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
