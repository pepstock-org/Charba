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
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NodeUpdateListener;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Weight;

/**
 * Base class to map the font properties of an element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class AbstractFont extends AbstractReadOnlyFont implements IsFont {

	/**
	 * Creates an empty font to use for chart configuration.
	 * 
	 * @param defaultValues default provider
	 */
	protected AbstractFont(IsDefaultFont defaultValues) {
		this(defaultValues, null);
	}

	/**
	 * Creates a font to use for chart configuration, wrapping a native object instance.
	 * 
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	protected AbstractFont(IsDefaultFont defaultValues, NativeObject nativeObject) {
		this(null, defaultValues, nativeObject);
	}

	/**
	 * Creates a font to use for chart configuration, wrapping a native object instance.
	 * 
	 * @param parent the native object container which font belongs to.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	protected AbstractFont(AbstractNode parent, IsDefaultFont defaultValues, NativeObject nativeObject) {
		super(parent, defaultValues, nativeObject);
	}

	/**
	 * Sets an update listener instance to be invoked at every update.
	 * 
	 * @param updateListener an update listener instance to be invoked at every update
	 */
	public final void setUpdateListener(NodeUpdateListener updateListener) {
		getDelegated().setUpdateListener(updateListener);
	}

	/**
	 * Returns the update listener instance if there is.
	 * 
	 * @return the update listener
	 */
	public final NodeUpdateListener getUpdateListener() {
		return getDelegated().getUpdateListener();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsFont#setSize(int)
	 */
	@Override
	public void setSize(int size) {
		getDelegated().setSize(size);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsFont#setStyle(org.pepstock.charba.client.enums.FontStyle)
	 */
	@Override
	public void setStyle(FontStyle style) {
		getDelegated().setStyle(style);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsFont#setFamily(java.lang.String)
	 */
	@Override
	public void setFamily(String family) {
		getDelegated().setFamily(family);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsFont#setWeight(org.pepstock.charba.client.enums.Weight)
	 */
	@Override
	public void setWeight(Weight weight) {
		getDelegated().setWeight(weight);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsFont#setLineHeight(double)
	 */
	@Override
	public void setLineHeight(double lineHeight) {
		getDelegated().setLineHeight(lineHeight);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsFont#setLineHeight(java.lang.String)
	 */
	@Override
	public void setLineHeight(String lineHeight) {
		getDelegated().setLineHeight(lineHeight);
	}

}