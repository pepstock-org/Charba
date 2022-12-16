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
package org.pepstock.charba.client.controllers;

import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;

/**
 * Class to implement from controllers in order to extend the standard configuration of charts.<br>
 * It takes the java script object of standard options passing it to a wrapper to manage additional properties.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class ControllerMapperFactory<T extends NativeObjectContainer> extends AbstractControllerContainer implements NativeObjectContainerFactory<T> {

	/**
	 * Creates the controller by its type.
	 * 
	 * @param type the type of this controller.
	 */
	protected ControllerMapperFactory(ControllerType type) {
		super(type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create()
	 */
	@Override
	public final T create() {
		return create(null);
	}

}