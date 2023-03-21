/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client;

import org.pepstock.charba.client.commons.BaseEnvelop;

/**
 * This object is a container of hidden object.<br>
 * It can not be instantiated in order that public methods can be invoked in safe mode.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 * @param <T> type of envelop content.
 */
public final class ChartEnvelop<T> extends BaseEnvelop<T> {

	/**
	 * Create an envelop with a <code>null</code> content.
	 */
	ChartEnvelop() {
	}

	/**
	 * Create an envelop with a <code>null</code> content and a flag to set if the content can be nullable.
	 * 
	 * @param nullable if <code>true</code>, the content can be <code>null</code>
	 */
	ChartEnvelop(boolean nullable) {
		super(nullable);
	}

	/**
	 * Create an envelop with the content passed as argument.
	 * 
	 * @param content content to set as initial value
	 * @param nullable if <code>true</code>, the content can be <code>null</code>
	 */
	ChartEnvelop(T content, boolean nullable) {
		super(content, nullable);
	}

	/**
	 * Create an envelop with the content passed as argument and a flag to set if the content can be nullable.
	 * 
	 * @param content content to set as initial value
	 */
	ChartEnvelop(T content) {
		super(content);
	}

}