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
package org.pepstock.charba.client.annotation.enums;

import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;

/**
 * Enumerates the types of the content option of the label.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum ContentType
{
	STRING,
	ARRAY,
	IMAGE,
	CANVAS,
	CALLBACK,
	UNDEFINED;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		CONTENT("content");

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
	 * Returns the content type reading the content property.
	 * 
	 * @param container the label container to use to get the content
	 * @return the content type of the content option
	 */
	public static ContentType get(NativeObjectContainer container) {
		// checks if container is consistent
		if (container != null) {
			// checks if is a image
			if (JsHelper.get().isImage(container, Property.CONTENT)) {
				return IMAGE;
			} else if (JsHelper.get().isCanvas(container, Property.CONTENT)) {
				// checks if is a canvas
				return CANVAS;
			} else if (container.isType(Property.CONTENT, ObjectType.ARRAY)) {
				// checks if is an array
				return ARRAY;
			} else if (container.isType(Property.CONTENT, ObjectType.FUNCTION)) {
				// checks if is a callback
				return CALLBACK;
			}
			// if here, can be only a string or undefined
			return STRING;
		}
		// if here, passed container is not consistent
		// then returns undefined
		return UNDEFINED;
	}

}