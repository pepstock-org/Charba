/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.utils.toast.enums;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.HtmlColor;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.utils.toast.IsToastType;

/**
 * Enumerates the list of default notification type for toasting.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum DefaultToastType implements IsToastType
{
	/**
	 * Default toast type -
	 * <span style="border-radius: 8px; color:#616161; font-size:16px; line-height: 1.6; background-color:white; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;Toast
	 * content&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DEFAULT("default", ColorBuilder.parse("#616161"), HtmlColor.WHITE),
	/**
	 * Success toast type -
	 * <span style="border-radius: 8px; color:white; font-size:16px; line-height: 1.6; background-color:#51C625; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;Toast
	 * content&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	SUCCESS("success", HtmlColor.WHITE, ColorBuilder.parse("#51C625")),
	/**
	 * Warning toast type -
	 * <span style="border-radius: 8px; color:white; font-size:16px; line-height: 1.6; background-color:#DB9215; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;Toast
	 * content&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	WARNING("warning", HtmlColor.WHITE, ColorBuilder.parse("#DB9215")),
	/**
	 * Error toast type -
	 * <span style="border-radius: 8px; color:white; font-size:16px; line-height: 1.6; background-color:#DB2B1D; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;Toast
	 * content&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	ERROR("error", HtmlColor.WHITE, ColorBuilder.parse("#DB2B1D")),
	/**
	 * Info toast type -
	 * <span style="border-radius: 8px; color:white; font-size:16px; line-height: 1.6; background-color:#27ABDB; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;Toast
	 * content&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	INFO("info", HtmlColor.WHITE, ColorBuilder.parse("#27ABDB")),
	/**
	 * Dark toast type -
	 * <span style="border-radius: 8px; color:white; font-size:16px; line-height: 1.6; background-color:black; border-style: solid; border-width: 1px;">&nbsp;&nbsp;&nbsp;Toast
	 * content&nbsp;&nbsp;&nbsp;&nbsp;</span>
	 */
	DARK("dark", HtmlColor.WHITE, HtmlColor.BLACK);

	// name value of property
	private final String value;
	// type color instance
	private final IsColor color;
	// type color instance
	private final IsColor backgroundColor;

	/**
	 * Creates the toast type with its property name to use in the options and colors to apply.
	 * 
	 * @param value value to use inside the native object as name of property
	 * @param color color of the toast type for text
	 * @param backgroundColor background color of toast
	 */
	private DefaultToastType(String value, IsColor color, IsColor backgroundColor) {
		this.value = value;
		this.color = color;
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
	 * @see org.pepstock.charba.client.utils.toast.IsToastType#getColor()
	 */
	@Override
	public IsColor getColor() {
		return color;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.utils.toast.IsToastType#getBackgroundColor()
	 */
	@Override
	public IsColor getBackgroundColor() {
		return backgroundColor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.utils.toast.IsToastType#getBackgroundAsPattern()
	 */
	@Override
	public Pattern getBackgroundAsPattern() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.utils.toast.IsToastType#getBackgroundAsGradient()
	 */
	@Override
	public Gradient getBackgroundAsGradient() {
		return null;
	}

}