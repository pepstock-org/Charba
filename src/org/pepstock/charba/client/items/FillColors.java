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

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;
import org.pepstock.charba.client.options.FillHandler;
import org.pepstock.charba.client.options.HasFill;
import org.pepstock.charba.client.options.OptionsEnvelop;

/**
 * The object enables to support multiple colors when filling from one dataset.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class FillColors extends NativeObjectContainer implements HasFill {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		TARGET("target"),
		ABOVE("above"),
		BELOW("below");

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

	// fill handler instance
	private final FillColorsHandler handler;

	/**
	 * Creates the item.
	 */
	public FillColors() {
		this((NativeObject) null);
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param envelop envelop with native java script object which contains all properties.
	 */
	public FillColors(OptionsEnvelop<NativeObject> envelop) {
		this(Envelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	FillColors(NativeObject nativeObject) {
		super(nativeObject);
		// stores handler
		this.handler = new FillColorsHandler(getNativeObject());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.HasFill#getFillHandler()
	 */
	@Override
	public FillHandler getFillHandler() {
		return handler;
	}

	/**
	 * Sets the above color for filling.
	 * 
	 * @param color the above color for filling
	 */
	public void setAboveColor(IsColor color) {
		setAboveColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the above color for filling.
	 * 
	 * @param color the above color for filling
	 */
	public void setAboveColor(String color) {
		setValue(Property.ABOVE, color);
	}

	/**
	 * Returns the above color for filling.
	 * 
	 * @return the above color for filling
	 */
	public IsColor getAboveColor() {
		return checkAndGetInternalColor(getAboveColorAsString());
	}

	/**
	 * Returns the above color for filling.
	 * 
	 * @return the above color for filling
	 */
	public String getAboveColorAsString() {
		return getValue(Property.ABOVE, Undefined.STRING);
	}

	/**
	 * Sets the below color for filling.
	 * 
	 * @param color the below color for filling
	 */
	public void setBelowColor(IsColor color) {
		setBelowColor(IsColor.checkAndGetValue(color));
	}

	/**
	 * Sets the below color for filling.
	 * 
	 * @param color the below color for filling
	 */
	public void setBelowColor(String color) {
		setValue(Property.BELOW, color);
	}

	/**
	 * Returns the below color for filling.
	 * 
	 * @return the below color for filling
	 */
	public IsColor getBelowColor() {
		return checkAndGetInternalColor(getBelowColorAsString());
	}

	/**
	 * Returns the below color for filling.
	 * 
	 * @return the below color for filling
	 */
	public String getBelowColorAsString() {
		return getValue(Property.BELOW, Undefined.STRING);
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	public NativeObject nativeObject() {
		return getNativeObject();
	}

	/**
	 * Returns the color for filling.
	 * 
	 * @param color as string format
	 * @return the color for filling
	 */
	private IsColor checkAndGetInternalColor(String color) {
		// checks if consistent
		if (color != null) {
			return ColorBuilder.parse(color);
		}
		// if here, color not consistent
		// then returns null
		return null;
	}

	/**
	 * This is fill handler to manage common fill methods.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class FillColorsHandler extends FillHandler {

		/**
		 * Creates the handler with the delegated object.
		 * 
		 * @param nativeObject delegated object
		 */
		private FillColorsHandler(NativeObject nativeObject) {
			super(null, DefaultsBuilder.get().getOptions().getElements().getLine().getFill(), new ItemsEnvelop<>(nativeObject));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.options.FillHandler#setFillColors(org.pepstock.charba.client.items.FillColors)
		 */
		@Override
		protected void setFillColors(FillColors colors) {
			// do nothing
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.options.FillHandler#getFillColors()
		 */
		@Override
		protected FillColors getFillColors() {
			return null;
		}

	}

}