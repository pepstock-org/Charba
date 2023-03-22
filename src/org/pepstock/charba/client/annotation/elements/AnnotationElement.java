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
import org.pepstock.charba.client.callbacks.ChartContext;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.IsPoint;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.ChartElement;
import org.pepstock.charba.client.items.ChartElementFactory;
import org.pepstock.charba.client.items.Undefined;

/**
 * Maps all properties of the annotation element, the implementation of the annotation options in the plugin.<br>
 * It provides all dimensions of the element and sub elements.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class AnnotationElement extends ChartElement implements HasAnnotationPropertiesHandler {

	/**
	 * MATRIX element type.
	 */
	public static final String TYPE = "annotation";
	/**
	 * Static instance for the MATRIX element factory
	 */
	public static final ChartElementFactory FACTORY = new AnnotationElementFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		// label element
		LABEL("label"),
		// options
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

	// label instance
	private final AnnotationElement label;
	// properties handler instance
	private final AnnotationPropertiesHandler handler;

	/**
	 * Creates the item using an envelop of the native java script object which contains all properties.
	 * 
	 * @param envelop envelop of the nativeObject native java script object which contains all properties.
	 */
	public AnnotationElement(AnnotationEnvelop<NativeObject> envelop) {
		this(Envelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates the object with native object to map java script properties.
	 * 
	 * @param nativeObject native object to map java script properties
	 */
	AnnotationElement(NativeObject nativeObject) {
		this(null, null, nativeObject);
	}

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param nativeObject native object to map java script properties
	 */
	AnnotationElement(AbstractNode parent, Key childKey, NativeObject nativeObject) {
		super(parent, childKey, TYPE, nativeObject);
		// loads inner elements
		if (has(Property.LABEL)) {
			// sets the element label
			this.label = new AnnotationElement(this, Property.LABEL, getValue(Property.LABEL));
		} else {
			// if not there, is null
			this.label = null;
		}
		// creates handler
		this.handler = new AnnotationPropertiesHandler(getNativeObject());
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
	 * Returns the inner label element of the element.
	 * 
	 * @return the inner label element of the element
	 */
	public AnnotationElement getLabel() {
		return label;
	}

	/**
	 * Returns whether the coordinates, passed as arguments, are inside the element or not.
	 * 
	 * @param point the point instance to check.
	 * @return <code>true</code> if point is inside the element
	 */
	public boolean inRange(IsPoint point) {
		return inRange(point, true);
	}

	/**
	 * Returns whether the coordinates, passed as arguments, are inside the element or not.
	 * 
	 * @param point the point instance to check.
	 * @param useFinalPosition if the position must be calculated with final dimensions or also during the animation.
	 * @return <code>true</code> if point is inside the element
	 */
	public boolean inRange(IsPoint point, boolean useFinalPosition) {
		return point != null && point.isConsistent() && inRange(point.getX(), point.getY(), useFinalPosition);
	}

	/**
	 * Returns whether the coordinates, passed as arguments, are inside the element or not.
	 * 
	 * @param x coordinate x of the point to check.
	 * @param y coordinate y of the point to check.
	 * @return <code>true</code> if point is inside the element
	 */
	public boolean inRange(double x, double y) {
		return inRange(x, y, true);
	}

	/**
	 * Returns whether the coordinates, passed as arguments, are inside the element or not.
	 * 
	 * @param x coordinate x of the point to check.
	 * @param y coordinate y of the point to check.
	 * @param useFinalPosition if the position must be calculated with final dimensions or also during the animation.
	 * @return <code>true</code> if point is inside the element
	 */
	public boolean inRange(double x, double y, boolean useFinalPosition) {
		return Undefined.isNot(x) && Undefined.isNot(y) && NativeJsAnnotationHelper.inRange(getNativeObject(), x, y, useFinalPosition);
	}

	/**
	 * Returns the element options or <code>null</code> if options are not stored in the element.
	 *
	 * @return the element options or <code>null</code> if options are not stored in the element.
	 */
	@Override
	public OptionsElement getOptions() {
		return (OptionsElement) super.getOptions();
	}

	/**
	 * Inner class to create annotation element by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static class AnnotationElementFactory implements ChartElementFactory {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public AnnotationElement create(NativeObject nativeObject) {
			return new AnnotationElement(nativeObject);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.items.ElementFactory#getType()
		 */
		@Override
		public String getType() {
			return TYPE;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.items.ChartElementFactory#createOptions(org.pepstock.charba.client.items.ChartElement, org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public OptionsElement createOptions(ChartElement parent, NativeObject nativeObject) {
			// checks if parent is consistent
			Checker.assertCheck(parent instanceof AnnotationElement, "Element of the options is not an AnnotationElement");
			// creates and returns options
			return new OptionsElement(parent, Property.OPTIONS, nativeObject);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.items.ChartElementFactory#createContext(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public ChartContext createContext(NativeObject nativeObject) {
			// context for annotation is NOT retrieved
			return null;
		}

	}
}