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

import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Weight;
import org.pepstock.charba.client.items.FontItem;

/**
 * Defaults for font option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartFont implements IsDefaultFont {

	private final IsDefaultFont delegatedFont;

	/**
	 * Creates the object by font option element instance.
	 * 
	 * @param font option element instance.
	 */
	public DefaultChartFont(IsDefaultFont font) {
		this.delegatedFont = font;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFont#getSize()
	 */
	@Override
	public int getSize() {
		return delegatedFont.getSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFont#getStyle()
	 */
	@Override
	public FontStyle getStyle() {
		return delegatedFont.getStyle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFont#getFamily()
	 */
	@Override
	public String getFamily() {
		return delegatedFont.getFamily();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFont#getWeight()
	 */
	@Override
	public Weight getWeight() {
		return delegatedFont.getWeight();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFont#getLineHeight()
	 */
	@Override
	public double getLineHeight() {
		return delegatedFont.getLineHeight();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFont#getLineHeightAsString()
	 */
	@Override
	public String getLineHeightAsString() {
		return delegatedFont.getLineHeightAsString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFont#create()
	 */
	@Override
	public FontItem create() {
		return delegatedFont.create();
	}

}