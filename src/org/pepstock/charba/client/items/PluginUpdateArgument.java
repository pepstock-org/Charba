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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.options.TransitionMode;
import org.pepstock.charba.client.plugins.PluginsEnvelop;

/**
 * This is a wrapper of java script object which the argument passed to plugin function.<br>
 * This object is used in the plugins methods of CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class PluginUpdateArgument extends NativeObjectContainer {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		MODE("mode");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}

	}

	/**
	 * Creates the item using an envelop of the native java script object which contains all properties.
	 * 
	 * @param envelop envelop of the native java script object which contains all properties.
	 */
	public PluginUpdateArgument(PluginsEnvelop<NativeObject> envelop) {
		super(Envelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Returns the update mode.
	 * 
	 * @return the update mode.
	 */
	public final TransitionMode getMode() {
		// gets string value
		String mode = getValue(Property.MODE, Undefined.STRING);
		// returns the update mode is consistent
		return mode != null ? TransitionMode.create(mode) : null;
	}
}