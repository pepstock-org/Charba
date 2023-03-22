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
package org.pepstock.charba.client.annotation.elements;

import org.pepstock.charba.client.annotation.AnnotationEnvelop;
import org.pepstock.charba.client.commons.AbstractPoint;
import org.pepstock.charba.client.commons.AbstractReadOnlyPoint;
import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.IsPoint;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.Undefined;

/**
 * Maps all properties of the annotation element, the implementation of the annotation options in the plugin.<br>
 * It provides all dimensions of the element and sub elements.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class AnnotationProperties extends AbstractReadOnlyPoint implements HasAnnotationPropertiesHandler {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		CENTER_X("centerX"),
		CENTER_Y("centerY"),
		OPTIONS("options");

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

	// properties handler instance
	private final AnnotationPropertiesHandler handler;
	// options instance
	private final OptionsElement options;

	/**
	 * Creates an empty item.
	 */
	public AnnotationProperties() {
		this((NativeObject) null);
	}

	/**
	 * Creates the item using an envelop of the native java script object which contains all properties.
	 * 
	 * @param envelop envelop of the nativeObject native java script object which contains all properties.
	 */
	public AnnotationProperties(AnnotationEnvelop<NativeObject> envelop) {
		this(Envelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates the object with native object to map java script properties.
	 * 
	 * @param nativeObject native object to map java script properties
	 */
	AnnotationProperties(NativeObject nativeObject) {
		super(null, null, nativeObject);
		// creates handler
		this.handler = new AnnotationPropertiesHandler(getNativeObject());
		// gets options
		this.options = new OptionsElement(this, Property.OPTIONS, getValue(Property.OPTIONS));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.elements.HasAnnotationPropertiesHandler#getHandler()
	 */
	@Override
	public AnnotationPropertiesHandler getHandler() {
		return handler;
	}

	/**
	 * Returns the element options or <code>null</code> if options are not stored in the element.
	 *
	 * @return the element options or <code>null</code> if options are not stored in the element.
	 */
	public OptionsElement getOptions() {
		return options;
	}

	/**
	 * Returns the center point of the element.
	 * 
	 * @return the center point of the element.
	 */
	public IsPoint getCenterPoint() {
		return new InternalCenterPoint(getValue(Property.CENTER_X, Undefined.DOUBLE), getValue(Property.CENTER_Y, Undefined.DOUBLE));
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	public NativeObject nativeObject() {
		return super.getNativeObject();
	}

	/**
	 * Maps the center point of the element.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class InternalCenterPoint extends AbstractPoint {

		/**
		 * Creates an object with X and Y coordinates of the point
		 * 
		 * @param x the X coordinate of the point
		 * @param y the Y coordinate of the point
		 */
		InternalCenterPoint(double x, double y) {
			super(x, y);
		}

	}
}