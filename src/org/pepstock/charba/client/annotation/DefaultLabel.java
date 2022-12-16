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
package org.pepstock.charba.client.annotation;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.defaults.IsDefaultPadding;
import org.pepstock.charba.client.defaults.globals.DefaultPadding;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.enums.TextAlign;

/**
 * This is the {@link AnnotationPlugin#ID} plugin LABEL annotation DEFAULTS options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class DefaultLabel implements IsDefaultsLabelAnnotation {

	// defaults options instance
	static final DefaultLabel INSTANCE = new DefaultLabel();
	// default padding instance
	private final IsDefaultPadding padding = new DefaultPadding(LabelAnnotation.DEFAULT_PADDING);

	/**
	 * To avoid any instantiation
	 */
	private DefaultLabel() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsAnnotation#getType()
	 */
	@Override
	public AnnotationType getType() {
		return AnnotationType.LABEL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsLabelAnnotation#getCallout()
	 */
	@Override
	public IsDefaultsCallout getCallout() {
		return DefaultCallout.INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsBackgroundColorHandler#getBackgroundColorAsString()
	 */
	@Override
	public String getBackgroundColorAsString() {
		return LabelAnnotation.DEFAULT_BACKGROUND_COLOR_AS_STRING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsBoxAnnotation#getBorderWidth()
	 */
	@Override
	public int getBorderWidth() {
		return LabelAnnotation.DEFAULT_BORDER_WIDTH;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsBorderRadiusHandler#getBorderRadius()
	 */
	@Override
	public int getBorderRadius() {
		return LabelAnnotation.DEFAULT_BORDER_RADIUS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsExtendedBorderOptionsHandler#getBorderCapStyle()
	 */
	@Override
	public CapStyle getBorderCapStyle() {
		return LabelAnnotation.DEFAULT_BORDER_CAP_STYLE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsExtendedBorderOptionsHandler#getBorderJoinStyle()
	 */
	@Override
	public JoinStyle getBorderJoinStyle() {
		return LabelAnnotation.DEFAULT_BORDER_JOIN_STYLE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsLabelHandler#getFont()
	 */
	@Override
	public IsDefaultFont getFont() {
		return Defaults.get().getGlobal().getFont();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsLabelHandler#getPadding()
	 */
	@Override
	public IsDefaultPadding getPadding() {
		return padding;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsLabelHandler#getColorAsString()
	 */
	@Override
	public String getColorAsString() {
		return LabelAnnotation.DEFAULT_COLOR_AS_STRING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsLabelHandler#getTextAlign()
	 */
	@Override
	public TextAlign getTextAlign() {
		return LabelAnnotation.DEFAULT_TEXT_ALIGN;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.annotation.IsDefaultsLabelAnnotation#getPosition()
	 */
	@Override
	public IsDefaultsAlignPosition getPosition() {
		return DefaultAlignPosition.INSTANCE;
	}

}