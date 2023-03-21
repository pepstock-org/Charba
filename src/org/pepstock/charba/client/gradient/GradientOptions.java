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
package org.pepstock.charba.client.gradient;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;

/**
 * This is the {@link GradientPlugin#ID} plugin options where to set all configuration items needed to the plugin.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class GradientOptions extends AbstractPluginOptions {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BACKGROUND_COLOR("backgroundColor"),
		BORDER_COLOR("borderColor"),
		HOVER_BACKGROUND_COLOR("hoverBackgroundColor"),
		HOVER_BORDER_COLOR("hoverBorderColor"),
		POINT_BACKGROUND_COLOR("pointBackgroundColor"),
		POINT_BORDER_COLOR("pointBorderColor"),
		POINT_HOVER_BACKGROUND_COLOR("pointHoverBackgroundColor"),
		POINT_HOVER_BORDER_COLOR("pointHoverBorderColor");

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

	/**
	 * Creates new {@link GradientPlugin#ID} plugin options.
	 */
	public GradientOptions() {
		this(null);
	}

	/**
	 * Creates new {@link GradientPlugin#ID} plugin options.
	 * 
	 * @param nativeObject native object loaded from configuration
	 */
	GradientOptions(NativeObject nativeObject) {
		super(GradientPlugin.ID, nativeObject, false);
	}

	/**
	 * Returns the options to set the fill gradient under the line.
	 * 
	 * @return the options to set the fill gradient under the line.
	 */
	public PropertyOptions getBackgroundColor() {
		return new PropertyOptions(this, Property.BACKGROUND_COLOR, getValue(Property.BACKGROUND_COLOR));
	}

	/**
	 * Returns the options to set the gradient of the line.
	 * 
	 * @return the options to set the gradient of the line.
	 */
	public PropertyOptions getBorderColor() {
		return new PropertyOptions(this, Property.BORDER_COLOR, getValue(Property.BORDER_COLOR));
	}

	/**
	 * Returns the options to set the fill gradient of the elements when hovered.
	 * 
	 * @return the options to set the fill gradient of the elements when hovered
	 */
	public PropertyOptions getHoverBackgroundColor() {
		return new PropertyOptions(this, Property.HOVER_BACKGROUND_COLOR, getValue(Property.HOVER_BACKGROUND_COLOR));
	}

	/**
	 * Returns the options to set the stroke gradient of the elements when hovered.
	 * 
	 * @return the options to set the stroke gradient of the elements when hovered.
	 */
	public PropertyOptions getHoverBorderColor() {
		return new PropertyOptions(this, Property.HOVER_BORDER_COLOR, getValue(Property.HOVER_BORDER_COLOR));
	}

	/**
	 * Returns the options to set the fill gradient for points.
	 * 
	 * @return the options to set the fill gradient for points.
	 */
	public PropertyOptions getPointBackgroundColor() {
		return new PropertyOptions(this, Property.POINT_BACKGROUND_COLOR, getValue(Property.POINT_BACKGROUND_COLOR));
	}

	/**
	 * Returns the options to set the border gradient for points.
	 * 
	 * @return the options to set the border gradient for points.
	 */
	public PropertyOptions getPointBorderColor() {
		return new PropertyOptions(this, Property.POINT_BORDER_COLOR, getValue(Property.POINT_BORDER_COLOR));
	}

	/**
	 * Returns the options to set the point background gradient when hovered.
	 * 
	 * @return the options to set the point background gradient when hovered.
	 */
	public PropertyOptions getPointHoverBackgroundColor() {
		return new PropertyOptions(this, Property.POINT_HOVER_BACKGROUND_COLOR, getValue(Property.POINT_HOVER_BACKGROUND_COLOR));
	}

	/**
	 * Returns the options to set the point border gradient when hovered.
	 * 
	 * @return the options to set the point border gradient when hovered.
	 */
	public PropertyOptions getPointHoverBorderColor() {
		return new PropertyOptions(this, Property.POINT_HOVER_BORDER_COLOR, getValue(Property.POINT_HOVER_BORDER_COLOR));
	}

}