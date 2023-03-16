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

import java.util.List;

import org.pepstock.charba.client.callbacks.ChartContext;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.AbstractReadOnlyPoint;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.ArrayUtil;
import org.pepstock.charba.client.commons.IsPoint;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;

/**
 * Calling some methods on your chart instance passing an argument of an event, will return the elements at the event position.<br>
 * The elements are mapped by this object.<br>
 * This is the CHART.JS item with all needed info about a data element, used to draw the chart.<br>
 * This is the base element created by CHART.JS which is extended by the controllers to manage own elements.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class ChartElement extends AbstractReadOnlyPoint {

	// static instance for the data set item factory
	static final UndefinedDataElementFactory FACTORY = new UndefinedDataElementFactory();
	// data element type when undefined
	static final String UNDEFINED_TYPE = "_undefined";

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ACTIVE("active"),
		CONTEXT("$context"),
		OPTIONS("options"),
		SKIP("skip"),
		STOP("stop");

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

	// element type instance
	private final String type;
	// element options instance
	private final ChartElementOptions options;
	// context instance
	private final ChartContext context;

	/**
	 * Creates the element using its type and a native java script object which contains all properties.
	 * 
	 * @param type chart element type
	 * @param nativeObject native java script object which contains all properties.
	 */
	protected ChartElement(String type, NativeObject nativeObject) {
		this(null, null, type, nativeObject);
	}

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param type chart element type
	 * @param nativeObject native object to map java script properties
	 */
	protected ChartElement(AbstractNode parent, Key childKey, String type, NativeObject nativeObject) {
		super(parent, childKey, nativeObject);
		// gets factory
		ChartElementFactory factory = ChartElementFactories.get().getFactory(type);
		// stores chart element type
		this.type = factory.getType();
		// sets the element options
		this.options = factory.createOptions(this, getValue(Property.OPTIONS));
		// checks if context is consistent
		if (has(Property.CONTEXT)) {
			// sets context
			this.context = factory.createContext(getValue(Property.CONTEXT));
		} else {
			// if here no context, then store a null
			this.context = null;
		}
	}

	/**
	 * Returns the data set item options.
	 *
	 * @return the data set item options.
	 */
	public final String getType() {
		return type;
	}

	/**
	 * Returns the context of the element.
	 * 
	 * @return the context of the element
	 */
	public final ChartContext getContext() {
		return context;
	}

	/**
	 * Returns the element options.
	 *
	 * @return the element options.
	 */
	public ChartElementOptions getOptions() {
		return options;
	}

	/**
	 * Returns if element is active.
	 * 
	 * @return <code>true</code> if the element is active.
	 */
	public final boolean isActive() {
		return getValue(Property.ACTIVE, Undefined.BOOLEAN);
	}

	/**
	 * Returns <code>true</code> if skipped.
	 * 
	 * @return <code>true</code> if skipped.
	 */
	public final boolean isSkipped() {
		return getValue(Property.SKIP, Undefined.BOOLEAN);
	}

	/**
	 * Returns <code>true</code> if stopped.
	 * 
	 * @return <code>true</code> if stopped.
	 */
	public final boolean isStop() {
		return getValue(Property.STOP, Undefined.BOOLEAN);
	}

	/**
	 * Returns the center point of the element.
	 * 
	 * @return the center point of the element.
	 */
	public final IsPoint getCenterPoint() {
		return getCenterPoint(true);
	}

	/**
	 * Returns the center point of the element.
	 * 
	 * @param useFinalPosition if the position must be calculated with final dimensions or also during the animation.
	 * @return the center point of the element.
	 */
	public final IsPoint getCenterPoint(boolean useFinalPosition) {
		return new InternalCenterPoint(NativeJsItemsHelper.getCenterPoint(getNativeObject(), useFinalPosition));
	}

	/**
	 * Returns the list of properties of the element, using the final position.
	 * 
	 * @param keys array of keys to request
	 * @return an element instance.
	 */
	public final ElementProperties getFinalPositionProps(Key... keys) {
		// gets array
		ArrayString array = ArrayString.fromOrEmpty(keys);
		// gets elements
		return getFinalPositionProps(array, ArrayListHelper.list(keys, array));
	}

	/**
	 * Returns the list of properties of the element, using the final position.
	 * 
	 * @param keys list of keys to request
	 * @return an element instance.
	 */
	public final ElementProperties getFinalPositionProps(List<Key> keys) {
		return getFinalPositionProps(ArrayString.fromOrEmpty(ArrayUtil.toKeys(keys)), keys);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		return "Element [x=" + getX() + ", y=" + getY() + "]";
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
	 * Returns the list of properties of the element, using the final position.
	 * 
	 * @param nativeKeys array of keys to request
	 * @param keys list of keys to request
	 * @return an element instance.
	 */
	private ElementProperties getFinalPositionProps(ArrayString nativeKeys, List<Key> keys) {
		// checks if array is consistent
		if (!nativeKeys.isEmpty()) {
			// gets and creates the result
			return new ElementProperties(keys, NativeJsItemsHelper.getProps(getNativeObject(), nativeKeys, true));
		}
		// if here, the keys are not consistent
		return new ElementProperties(keys, null);
	}

	/**
	 * Inner class to create a chart element by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	private static class UndefinedDataElementFactory implements ChartContextElementFactory {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public ChartElement create(NativeObject nativeObject) {
			return new ChartElement(UNDEFINED_TYPE, nativeObject);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.items.DataElementFactory#getType()
		 */
		@Override
		public String getType() {
			return UNDEFINED_TYPE;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.items.ChartElementFactory#createOptions(org.pepstock.charba.client.items.ChartElement, org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public ChartElementOptions createOptions(ChartElement element, NativeObject nativeObject) {
			return new ChartElementOptions(nativeObject);
		}

	}

	/**
	 * Maps the center point of the element.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class InternalCenterPoint extends AbstractReadOnlyPoint {

		InternalCenterPoint(NativeObject nativeObject) {
			super(nativeObject);
		}

	}
}