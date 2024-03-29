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
package org.pepstock.charba.client.defaults.chart;

import org.pepstock.charba.client.defaults.IsDefaultAbstractTitle;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.defaults.IsDefaultPadding;
import org.pepstock.charba.client.enums.ElementAlign;
import org.pepstock.charba.client.enums.Position;

/**
 * Defaults for title and subtitle options elements, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class AbstractDefaultChartTitle implements IsDefaultAbstractTitle {

	private final IsDefaultAbstractTitle title;

	/**
	 * Creates the object by title or subtitle option element instance.
	 * 
	 * @param title title or subtitle option element instance.
	 */
	protected AbstractDefaultChartTitle(IsDefaultAbstractTitle title) {
		this.title = title;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTitle#getFont()
	 */
	@Override
	public IsDefaultFont getFont() {
		return title.getFont();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTitle#getPadding()
	 */
	@Override
	public IsDefaultPadding getPadding() {
		return title.getPadding();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTitle#getColorAsString()
	 */
	@Override
	public String getColorAsString() {
		return title.getColorAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTitle#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return title.isDisplay();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTitle#getPosition()
	 */
	@Override
	public Position getPosition() {
		return title.getPosition();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTitle#isFullSize()
	 */
	@Override
	public boolean isFullSize() {
		return title.isFullSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultTitle#getAlign()
	 */
	@Override
	public ElementAlign getAlign() {
		return title.getAlign();
	}

}