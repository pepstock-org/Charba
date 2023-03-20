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

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.defaults.IsDefaultAnimations;

/**
 * Abstract configuration to animate charts out of the box.<br>
 * A number of options are provided to configure how the animation looks and how long it takes.<br>
 * This configuration item is configuring the common animations properties, ANIMATIONS name space.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class AbstractAnimations extends NativeObjectContainer implements IsAnimations {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ANIMATIONS("animations");

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

	// delegated animation
	private final Animations delegated;

	/**
	 * Creates a animations config to use for chart configuration, wrapping a native object instance.
	 * 
	 * @param parent the native object container which animations belongs to.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	protected AbstractAnimations(AbstractNode parent, IsDefaultAnimations defaultValues, NativeObject nativeObject) {
		super(nativeObject);
		// creates a font to wrap
		this.delegated = new Animations(parent, Property.ANIMATIONS, defaultValues, getNativeObject());
	}

	/**
	 * Returns the delegated animations item.
	 * 
	 * @return the delegated animations item
	 */
	final Animations getDelegated() {
		return delegated;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultAnimations#contains(org.pepstock.charba.client.commons.Key)
	 */
	@Override
	public boolean contains(Key collection) {
		return getDelegated().contains(collection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsAnimations#setEnabled(org.pepstock.charba.client.commons.Key, boolean)
	 */
	@Override
	public void setEnabled(Key collection, boolean enabled) {
		getDelegated().setEnabled(collection, enabled);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsAnimations#isEnabled(org.pepstock.charba.client.commons.Key)
	 */
	@Override
	public boolean isEnabled(Key collection) {
		return getDelegated().isEnabled(collection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsAnimations#get(org.pepstock.charba.client.commons.Key)
	 */
	@Override
	public AnimationCollection get(Key collection) {
		return getDelegated().get(collection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsAnimations#set(org.pepstock.charba.client.commons.Key, org.pepstock.charba.client.options.AnimationCollection)
	 */
	@Override
	public void set(Key collection, AnimationCollection animationCollection) {
		getDelegated().set(collection, animationCollection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsAnimations#create(org.pepstock.charba.client.commons.Key)
	 */
	@Override
	public AnimationCollection create(Key collection) {
		return getDelegated().create(collection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsAnimations#delete(org.pepstock.charba.client.commons.Key)
	 */
	@Override
	public void delete(Key collection) {
		getDelegated().delete(collection);
	}

}