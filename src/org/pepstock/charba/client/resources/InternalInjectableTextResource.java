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
package org.pepstock.charba.client.resources;

import org.pepstock.charba.client.commons.Key;

import com.google.gwt.resources.client.TextResource;

/**
 * Internal implementation for an injectable resource related to a text resource and used by moudle out of the box provided by CHARBA for deferred resources.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class InternalInjectableTextResource extends AbstractInjectableResource implements IsInternalInjectableTextResource {

	/**
	 * Creates an injectable resources using the text of it and the name as key, passed as argument.
	 * 
	 * @param key name of injectable resource as key , should be a unique value in the DOM.
	 * @param resource text resource to be injected.
	 */
	InternalInjectableTextResource(Key key, TextResource resource) {
		super(Key.checkAndGetIfValid(key), resource != null ? resource.getText() : null);
	}

}