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

import org.pepstock.charba.client.commons.ImmutableEnvelop;

/**
 * This object is a container of hidden object to pass to other packages.<br>
 * It can not be instantiated in order that public methods can be invoked in safe mode.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of envelop content.
 * 
 */
public final class OptionsEnvelop<T> extends ImmutableEnvelop<T> {
	
	/**
	 * Create an envelop with the content passed as argument.
	 * 
	 * @param content content to set as initial value
	 */
	OptionsEnvelop(T content) {
		super(content);
	}

	/**
	 * Create an envelop with the content passed as argument and a flag to set if the content can be nullable.
	 * 
	 * @param content content to set as initial value
	 * @param nullable if <code>true</code>, the content can be <code>null</code>
	 */
	OptionsEnvelop(T content, boolean nullable) {
		super(content, nullable);
	}

}
