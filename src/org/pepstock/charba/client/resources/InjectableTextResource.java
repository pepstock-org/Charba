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
 * Default implementation for an injectable resource related to a text resource.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class InjectableTextResource extends AbstractInjectableResource {

	/**
	 * Creates an injectable resources using the name of text resource and the text of it.
	 * 
	 * @param resource text resource to be injected.
	 */
	public InjectableTextResource(TextResource resource) {
		this(resource != null ? resource.getName() : null, resource);
	}

	/**
	 * Creates an injectable resources using the text of it and the name as key, passed as argument.
	 * 
	 * @param key name of injectable resource as key , should be a unique value in the DOM.
	 * @param resource text resource to be injected.
	 */
	public InjectableTextResource(Key key, TextResource resource) {
		super(key, resource != null ? resource.getText() : null);
	}

	/**
	 * Creates an injectable resources using the text of it and the name, passed as argument.
	 * 
	 * @param name name of injectable resource, should be a unique value in the DOM.
	 * @param resource text resource to be injected.
	 */
	public InjectableTextResource(String name, TextResource resource) {
		super(name, resource != null ? resource.getText() : null);
	}

}