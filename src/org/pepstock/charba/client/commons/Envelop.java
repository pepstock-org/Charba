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
 * This object is a container of hidden object.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 * @param <T> type of envelop content.
 */
public abstract class Envelop<T> extends ImmutableEnvelop<T>{

	/**
	 * Create an envelop with a <code>null</code> content.
	 */
	protected Envelop() {
		this(null);
	}

	/**
	 * Create an envelop with the content passed as argument.
	 * 
	 * @param content content to set as initial value
	 */
	protected Envelop(T content) {
		super(content);
	}

	/**
	 * Stores the content of envelop.
	 * 
	 * @param content the content of envelop to store
	 */
	public final void setContent(T content) {
		super.setContentInternally(content);
	}

}
