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
package org.pepstock.charba.client.interaction;

import org.pepstock.charba.client.enums.IsInteractionMode;

/**
 * Abstract class which implements a interactioner.<br>
 * It helps to create a custom interactioner, creating a custom interaction mode using the name as string passed as argument.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractInteractioner implements Interactioner {

	private final IsInteractionMode mode;

	/**
	 * Creates the interactioner passing the interaction mode instance as argument.
	 * 
	 * @param mode interaction mode instance.
	 */
	AbstractInteractioner(IsInteractionMode mode) {
		this.mode = mode;
	}

	/**
	 * Creates the interactioner passing the name of interaction mode as argument, as string.
	 * 
	 * @param name of interaction mode.
	 */
	protected AbstractInteractioner(String name) {
		this(IsInteractionMode.create(name));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.interaction.Interactioner#getMode()
	 */
	@Override
	public final IsInteractionMode getMode() {
		return mode;
	}

}