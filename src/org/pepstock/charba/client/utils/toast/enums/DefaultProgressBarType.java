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
package org.pepstock.charba.client.utils.toast.enums;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.GradientBuilder;
import org.pepstock.charba.client.colors.GradientOrientation;
import org.pepstock.charba.client.colors.GradientType;
import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.utils.toast.IsProgressBarType;

/**
 * Enumerates the list of progress bar type for toasting.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum DefaultProgressBarType implements IsProgressBarType
{
	/**
	 * Default toast type - <span style="background:black; border-style: solid; height: 8px; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DEFAULT("default", HtmlColor.BLACK),
	/**
	 * Success toast type - <span style="background:#51C625; border-style: solid; height: 8px; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	SUCCESS("success", ColorBuilder.parse("#51C625")),
	/**
	 * Warning toast type - <span style="background:#DB9215; border-style: solid; height: 8px; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	WARNING("warning", ColorBuilder.parse("#DB9215")),
	/**
	 * Error toast type - <span style="background:#DB2B1D; border-style: solid; height: 8px; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ERROR("error", ColorBuilder.parse("#DB2B1D")),
	/**
	 * Info toast type - <span style="background:#27ABDB; border-style: solid; height: 8px; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	INFO("info", ColorBuilder.parse("#27ABDB")),
	/**
	 * Rainbow toast type - <span style="background:linear-gradient(45deg, #002024 0%, #a72c86 48%, #00d4ff 100%); border-style: solid; height: 8px; border-width:
	 * 1px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	RAINBOW("rainbow", null);

	// gradient instance ONLY for rainbow
	private static final Gradient RAINBOW_GRADIENT = GradientBuilder.create(GradientType.LINEAR, GradientOrientation.BOTTOM_RIGHT).addColorStop(0, "#002024").addColorStop(0.48, "#a72c86").addColorStop(1, "#00d4ff").build();

	// name value of property
	private final String value;
	// type color instance
	private final IsColor backgroundColor;

	/**
	 * Creates the progress bar type with its property name to use in the options.
	 * 
	 * @param value value to use inside the native object as name of property
	 * @param backgroundColor background color of toast progress bar
	 */
	private DefaultProgressBarType(String value, IsColor backgroundColor) {
		this.value = value;
		this.backgroundColor = backgroundColor;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.utils.toast.IsProgressBarType#getBackgroundColor()
	 */
	@Override
	public IsColor getBackgroundColor() {
		return backgroundColor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.utils.toast.IsProgressBarType#getBackgroundAsGradient()
	 */
	@Override
	public Gradient getBackgroundAsGradient() {
		// checks if rainbow
		if (RAINBOW.equals(this)) {
			return RAINBOW_GRADIENT;
		}
		// if here, all other rainbow
		return null;
	}

}