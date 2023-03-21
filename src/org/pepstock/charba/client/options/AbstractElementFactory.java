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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObjectContainer;

/**
 * Abstract implementation to get custom elements from {@link Options#getElements()}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractElementFactory<T extends NativeObjectContainer> implements ElementFactory<T> {

	// key of the object element
	private final Key elementKey;

	/**
	 * Creates the factory by the key of object, as string.
	 * 
	 * @param elementKeyAsString the key of object, as string.
	 */
	protected AbstractElementFactory(String elementKeyAsString) {
		this(Key.create(elementKeyAsString));
	}

	/**
	 * Creates the factory by the key of object.
	 * 
	 * @param elementKey the key of object.
	 */
	protected AbstractElementFactory(Key elementKey) {
		this.elementKey = Key.checkAndGetIfValid(elementKey);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.ElementFactory#getElementKey()
	 */
	@Override
	public final Key getElementKey() {
		return elementKey;
	}

}