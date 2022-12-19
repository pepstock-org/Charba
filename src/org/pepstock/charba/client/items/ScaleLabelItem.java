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

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.options.AbstractReadOnlyFont;

/**
 * The label item maps the labels computed by {@link ScaleItem}.<br>
 * This is a wrapper of the CHART.JS item with all needed info.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ScaleLabelItem extends AbstractNode {

	/**
	 * Public factory to create a scale label item from a native object.
	 */
	static final ScaleLebalItemFactory FACTORY = new ScaleLebalItemFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		LABEL("label"),
		FONT("font"),
		TEXT_OFFSET("textOffset"),
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

	// font instance
	private final InternalFont font;
	// options
	private final ScaleLabelOptions options;

	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	ScaleLabelItem(NativeObject nativeObject) {
		super(nativeObject);
		// creates internal objects
		this.font = new InternalFont(this, Defaults.get().getGlobal().getFont(), getValue(Property.FONT));
		this.options = new ScaleLabelOptions(getValue(Property.OPTIONS));
	}

	/**
	 * Returns the label of the scale. If the labels are stored as array, returns the array values joined by {@link Constants#LINE_SEPARATOR}.
	 * 
	 * @return the label of the scale.
	 */
	public String getLabel() {
		// checks if is array
		if (isType(Property.LABEL, ObjectType.ARRAY)) {
			// gets the array
			ArrayString array = getArrayValue(Property.LABEL);
			// returns the value of array joined
			return array.join(Constants.LINE_SEPARATOR);
		}
		// if here is not an array
		return getValue(Property.LABEL, Undefined.STRING);
	}

	/**
	 * Returns the labels of the scale.
	 * 
	 * @return the labels of the scale.
	 */
	public List<String> getLabels() {
		// checks if exists
		if (has(Property.LABEL)) {
			ArrayString array = getValueOrArray(Property.LABEL, Undefined.STRING);
			return ArrayListHelper.unmodifiableList(array);
		}
		// if here, the label does not exist
		// then an empty list
		return Collections.emptyList();
	}

	/**
	 * Returns the options applied to the scale label.
	 * 
	 * @return the options applied to the scale label.
	 */
	public ScaleLabelOptions getOptions() {
		return options;
	}

	/**
	 * Returns the font applied to the scale label.
	 * 
	 * @return the font applied to the scale label.
	 */
	public IsDefaultFont getFont() {
		return font;
	}

	/**
	 * Returns the offset of the text for scale item.
	 * 
	 * @return the offset of the text for scale item.
	 */
	public double getTextOffset() {
		return getValue(Property.TEXT_OFFSET, Undefined.DOUBLE);
	}

	/**
	 * Inner class to create label item by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	static class ScaleLebalItemFactory implements NativeObjectContainerFactory<ScaleLabelItem> {

		/**
		 * To avoid any instatiation
		 */
		private ScaleLebalItemFactory() {
			// do nothing
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public ScaleLabelItem create(NativeObject nativeObject) {
			return new ScaleLabelItem(nativeObject);
		}
	}

	/**
	 * Internal font to get from the scale label.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	static final class InternalFont extends AbstractReadOnlyFont {

		/**
		 * Creates a font, wrapping a native object instance.
		 * 
		 * @param parent the native object container which font belongs to.
		 * @param defaultValues default provider
		 * @param nativeObject native object to map java script properties
		 */
		private InternalFont(AbstractNode parent, IsDefaultFont defaultValues, NativeObject nativeObject) {
			super(parent, defaultValues, nativeObject);
		}

	}

}