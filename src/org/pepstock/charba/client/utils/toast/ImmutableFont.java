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
package org.pepstock.charba.client.utils.toast;

import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Weight;
import org.pepstock.charba.client.items.FontItem;
import org.pepstock.charba.client.items.Undefined;

/**
 * This is an read-only font class in order to re-used the same implementation in the default for all elements where font is needed.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class ImmutableFont implements IsDefaultFont {

	// sets all references for all font
	// options
	private final int size;
	private final FontStyle style;
	private final String family;
	private final Weight weight;
	private final double lineHeight;
	private final String lineHeightAsString;

	/**
	 * Creates a immutable font using a font instance as source.
	 * 
	 * @param source source of font instance
	 */
	ImmutableFont(IsDefaultFont source) {
		this(source.getSize(), source.getStyle(), source.getFamily(), source.getWeight(), source.getLineHeight());
	}

	/**
	 * Creates the font instance with all properties.
	 * 
	 * @param size font size
	 * @param style font style
	 * @param family font family
	 * @param weight the weight of the font
	 * @param lineHeightAsString the line height of font as string
	 */
	ImmutableFont(int size, FontStyle style, String family, Weight weight, String lineHeightAsString) {
		this.size = size;
		this.style = style;
		this.family = family;
		this.weight = weight;
		this.lineHeight = Undefined.DOUBLE;
		this.lineHeightAsString = lineHeightAsString;
	}

	/**
	 * Creates the font instance with all properties.
	 * 
	 * @param size font size
	 * @param style font style
	 * @param family font family
	 * @param weight the weight of the font
	 * @param lineHeight the line height of font
	 */
	ImmutableFont(int size, FontStyle style, String family, Weight weight, double lineHeight) {
		this.size = size;
		this.style = style;
		this.family = family;
		this.weight = weight;
		this.lineHeight = lineHeight;
		this.lineHeightAsString = String.valueOf(lineHeight);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFont#getSize()
	 */
	@Override
	public int getSize() {
		return size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFont#getStyle()
	 */
	@Override
	public FontStyle getStyle() {
		return style;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFont#getFamily()
	 */
	@Override
	public String getFamily() {
		return family;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFont#getWeight()
	 */
	@Override
	public Weight getWeight() {
		return weight;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFont#getLineHeight()
	 */
	@Override
	public double getLineHeight() {
		return lineHeight;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFont#getLineHeightAsString()
	 */
	@Override
	public String getLineHeightAsString() {
		return lineHeightAsString;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFont#create()
	 */
	@Override
	public FontItem create() {
		return null;
	}

}