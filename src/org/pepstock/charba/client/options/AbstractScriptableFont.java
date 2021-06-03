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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.callbacks.ChartContext;
import org.pepstock.charba.client.callbacks.FontCallback;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultFont;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Weight;

/**
 * Object can be provided with additional configuration by callbacks to define font options at runtime, and with the possibility to reset {@link FontCallback} if the
 * {@link IsScriptableFontProvider} instance has been set with a {@link FontCallback}.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of scriptable context
 */
public abstract class AbstractScriptableFont<T extends ChartContext> extends AbstractFont {

	private final IsScriptableFontProvider<T> scriptableFontProvider;

	/**
	 * Creates an empty font to use for chart configuration.
	 * 
	 * @param scriptableFontProvider the provider of font callback
	 * @param defaultValues default provider
	 */
	protected AbstractScriptableFont(IsScriptableFontProvider<T> scriptableFontProvider, IsDefaultFont defaultValues) {
		this(scriptableFontProvider, defaultValues, null);
	}

	/**
	 * Creates a font to use for chart configuration, wrapping a native object instance.
	 * 
	 * @param scriptableFontProvider the provider of font callback
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	protected AbstractScriptableFont(IsScriptableFontProvider<T> scriptableFontProvider, IsDefaultFont defaultValues, NativeObject nativeObject) {
		super(defaultValues, nativeObject);
		// checks if font container is consistent
		// stores font container
		this.scriptableFontProvider = Checker.checkAndGetIfValid(scriptableFontProvider, "Scriptable font provider argument");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsFont#setSize(int)
	 */
	@Override
	public void setSize(int size) {
		// resets callback
		resetCallback();
		// stores the values
		super.setSize(size);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsFont#setStyle(org.pepstock.charba.client.enums.FontStyle)
	 */
	@Override
	public void setStyle(FontStyle style) {
		// resets callback
		resetCallback();
		// stores the values
		super.setStyle(style);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsFont#setFamily(java.lang.String)
	 */
	@Override
	public void setFamily(String family) {
		// resets callback
		resetCallback();
		// stores the values
		super.setFamily(family);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsFont#setWeight(org.pepstock.charba.client.enums.Weight)
	 */
	@Override
	public void setWeight(Weight weight) {
		// resets callback
		resetCallback();
		// stores the values
		super.setWeight(weight);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsFont#setLineHeight(double)
	 */
	@Override
	public void setLineHeight(double lineHeight) {
		// resets callback
		resetCallback();
		// stores the values
		super.setLineHeight(lineHeight);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsFont#setLineHeight(java.lang.String)
	 */
	@Override
	public void setLineHeight(String lineHeight) {
		// resets callback
		resetCallback();
		// stores the values
		super.setLineHeight(lineHeight);
	}

	/**
	 * Invokes when any property of the font is being set, in order to reset the {@link FontCallback} if exists
	 */
	protected void resetCallback() {
		// checks if the font has been set previously as a callback
		if (scriptableFontProvider.getFontCallback() != null) {
			// if yes, resets it
			scriptableFontProvider.setFont((FontCallback<T>) null);
		}
	}

}