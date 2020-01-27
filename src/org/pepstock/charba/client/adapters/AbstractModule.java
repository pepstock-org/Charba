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
package org.pepstock.charba.client.adapters;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.resources.DateAdapterInjectionComplete;

/**
 * Every date adapter needs to have a module with some attributes of date adapter itself.<br>
 * It provides a single point of glass on date adapter.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractModule {

	// unique ID of date adapter module
	private final String id;
	// internal instance to know if the module has been injected
	private boolean injected = false;

	/**
	 * Creates a module using the argument as id of the adapter.
	 * 
	 * @param key the id of the adapter as key
	 */
	protected AbstractModule(Key key) {
		this(Key.isValid(key) ? key.value() : null);
	}

	/**
	 * Creates a module using the argument as id of the adapter.
	 * 
	 * @param id the id of the adapter as string
	 */
	protected AbstractModule(String id) {
		// check if id is consistent
		if (id == null) {
			// if not, exception
			throw new IllegalArgumentException("Date adapter id is null!");
		}
		// stores the id
		this.id = id;
	}

	/**
	 * Returns the id of the adapter.
	 * 
	 * @return the id of the adapter
	 */
	public final String getId() {
		return id;
	}

	/**
	 * Returns if the date adapter has been injected.
	 * 
	 * @return <code>false</code>, the date adapter has not been injected
	 */
	public final boolean isInjected() {
		return injected;
	}

	/**
	 * Creates a date adapter.
	 * 
	 * @return a date adapter
	 */
	public final DateAdapter createDateAdapter() {
		return createDateAdapter(null);
	}

	/**
	 * Creates a date adapter with specific options.
	 * 
	 * @param options date adapter options.
	 * @return a date adapter
	 */
	public DateAdapter createDateAdapter(DateAdapterOptions options) {
		// checks if the module is injected
		if (isInjected()) {
			// checks if adapter options is null
			if (options != null) {
				// if not null, uses the constructor wiht options
				return new DateAdapter(options);
			}
			// if here, creates adapter
			// without options
			return new DateAdapter();
		}
		// if here, the date adapter module is not ibjected
		// then exception
		throw new IllegalArgumentException(id + " module is not injected");
	}

	/**
	 * Is invoked when the date adapter has been injected.<br>
	 * It can be invoked only by the resources type of the module.
	 * 
	 * @param injectionComplete empty object which can be created only by resources type
	 */
	public final void injectionComplete(DateAdapterInjectionComplete injectionComplete) {
		// checks if injection complete is consistent
		if (injectionComplete != null) {
			// sets that it has been injected
			this.injected = true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id.hashCode();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public final boolean equals(Object obj) {
		// checks if the same instance
		if (this == obj) {
			return true;
		}
		// checks if instance is null
		if (obj == null) {
			return false;
		}
		// checks if have got the same class
		if (getClass() != obj.getClass()) {
			return false;
		}
		// casts to module
		AbstractModule other = (AbstractModule) obj;
		// checks if id is the same
		if (!id.equals(other.id)) {
			return false;
		}
		// if here they are equals
		// with same id
		return true;
	}
}
