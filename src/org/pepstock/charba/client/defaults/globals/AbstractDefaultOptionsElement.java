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
package org.pepstock.charba.client.defaults.globals;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.GlobalOptions;
import org.pepstock.charba.client.defaults.IsDefaultOptionsElement;

/**
 * CHART.JS default values for ARC, LINE, BAR and POINT elements.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class AbstractDefaultOptionsElement implements IsDefaultOptionsElement {

	private final String borderColor;

	private final int borderWidth;

	/**
	 * Creates the object using the values passed as arguments.<br>
	 * Background and border colors will be taken from {@link GlobalOptions#getBackgroundColorAsString()} and {@link GlobalOptions#getBorderColorAsString()}.
	 * 
	 * @param borderWidth border width of element
	 */
	AbstractDefaultOptionsElement(int borderWidth) {
		this(null, borderWidth);
	}

	/**
	 * Creates the object using the values passed as arguments.<br>
	 * Background color will be taken from {@link GlobalOptions#getBackgroundColorAsString()}.
	 * 
	 * @param borderColor border color of element
	 * @param borderWidth order width of element
	 */
	AbstractDefaultOptionsElement(String borderColor, int borderWidth) {
		this.borderColor = borderColor;
		this.borderWidth = borderWidth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.elements.arc.IsReadableArc#getBackgroundColorAsString()
	 */
	@Override
	public final String getBackgroundColorAsString() {
		return Defaults.get().getGlobal().getBackgroundColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.elements.arc.IsReadableArc#getBorderWidth()
	 */
	@Override
	public final int getBorderWidth() {
		return borderWidth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.elements.arc.IsReadableArc#getBorderColorAsString()
	 */
	@Override
	public final String getBorderColorAsString() {
		return borderColor != null ? borderColor : Defaults.get().getGlobal().getBorderColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptionsElement#getHoverBackgroundColorAsString()
	 */
	@Override
	public String getHoverBackgroundColorAsString() {
		return getBackgroundColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptionsElement#getHoverBorderWidth()
	 */
	@Override
	public int getHoverBorderWidth() {
		return getBorderWidth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultOptionsElement#getHoverBorderColorAsString()
	 */
	@Override
	public String getHoverBorderColorAsString() {
		return getBorderColorAsString();
	}

}