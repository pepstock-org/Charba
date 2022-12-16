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
import org.pepstock.charba.client.defaults.IsDefaultAnimation;

/**
 * It animates charts out of the box.<br>
 * A number of options are provided to configure how the animation looks and how long it takes.<br>
 * This configuration item is configuring the common animation properties, ANIMATION name space.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Animation extends AbstractAnimation<Key, IsDefaultAnimation> implements IsAnimation {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ANIMATE_ROTATE("animateRotate"),
		ANIMATE_SCALE("animateScale");

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

	// default values
	private final IsDefaultAnimation defaultValues;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node of the chart options.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Animation(AbstractNode parent, Key childKey, IsDefaultAnimation defaultValues, NativeObject nativeObject) {
		super(parent, childKey, defaultValues, nativeObject);
		// stores defaults which has been already checked on super class
		this.defaultValues = defaultValues;
	}

	/**
	 * If <code>true</code>, the chart will animate in with a rotation animation.
	 * 
	 * @param animateRotate If <code>true</code>, the chart will animate in with a rotation animation.
	 */
	@Override
	public void setAnimateRotate(boolean animateRotate) {
		setValueAndAddToParent(Property.ANIMATE_ROTATE, animateRotate);
	}

	/**
	 * If <code>true</code>, the chart will animate in with a rotation animation.
	 * 
	 * @return if <code>true</code>, the chart will animate in with a rotation animation.
	 */
	@Override
	public boolean isAnimateRotate() {
		return getValue(Property.ANIMATE_ROTATE, defaultValues.isAnimateRotate());
	}

	/**
	 * If <code>true</code>, will animate scaling the chart from the center outwards.
	 * 
	 * @param animateScale If <code>true</code>, will animate scaling the chart from the center outwards.
	 */
	@Override
	public void setAnimateScale(boolean animateScale) {
		setValueAndAddToParent(Property.ANIMATE_SCALE, animateScale);
	}

	/**
	 * If <code>true</code>, will animate scaling the chart from the center outwards.
	 * 
	 * @return If <code>true</code>, will animate scaling the chart from the center outwards.
	 */
	@Override
	public boolean isAnimateScale() {
		return getValue(Property.ANIMATE_SCALE, defaultValues.isAnimateScale());
	}

}