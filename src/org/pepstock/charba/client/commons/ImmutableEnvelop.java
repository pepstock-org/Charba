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

/**
 * This object is a container of hidden object which can set by constructor and immutable afterwards.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 * @param <T> type of envelop content.
 */
public abstract class ImmutableEnvelop<T> implements Envelop {

	// instance of enveloped content
	private T content = null;
	// flag to know if it can be nullable
	private final boolean nullable;

	/**
	 * Create an envelop with the content passed as argument.
	 * 
	 * @param content content to set as initial value
	 */
	protected ImmutableEnvelop(T content) {
		this(content, false);
	}

	/**
	 * Create an envelop with the content passed as argument and a flag to set if the content can be nullable.
	 * 
	 * @param content content to set as initial value
	 * @param nullable if <code>true</code>, the content can be <code>null</code>
	 */
	protected ImmutableEnvelop(T content, boolean nullable) {
		this.content = content;
		this.nullable = nullable;
	}

	/**
	 * Returns the content of envelop.
	 * 
	 * @return the content of envelop
	 */
	public final T getContent() {
		return content;
	}

	/**
	 * Stores the content of envelop.
	 * 
	 * @param content the content of envelop to store
	 */
	final void setContentInternally(T content) {
		this.content = content;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Envelop#hasContent()
	 */
	@Override
	public final boolean hasContent() {
		return this.content != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Envelop#isNullable()
	 */
	@Override
	public final boolean isNullable() {
		return nullable;
	}

}
