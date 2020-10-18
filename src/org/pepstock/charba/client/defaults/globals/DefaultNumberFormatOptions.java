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
package org.pepstock.charba.client.defaults.globals;

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions;
import org.pepstock.charba.client.intl.enums.CompactDisplay;
import org.pepstock.charba.client.intl.enums.Currency;
import org.pepstock.charba.client.intl.enums.CurrencyDisplay;
import org.pepstock.charba.client.intl.enums.CurrencySign;
import org.pepstock.charba.client.intl.enums.MeasureUnit;
import org.pepstock.charba.client.intl.enums.MeasureUnitDisplay;
import org.pepstock.charba.client.intl.enums.Notation;
import org.pepstock.charba.client.intl.enums.SignDisplay;
import org.pepstock.charba.client.intl.enums.Style;

/**
 * INTL default values for number format options for internationalization.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultNumberFormatOptions extends DefaultBaseFormatOptions implements IsDefaultNumberFormatOptions {

	public static final IsDefaultNumberFormatOptions INSTANCE = new DefaultNumberFormatOptions();

	private static final boolean DEFAULT_USE_GROUPING = true;

	private static final int DEFAULT_MINIMUM_INTEGER_DIGITS = 1;

	private static final int DEFAULT_MINIMUM_SIGNIFICANT_DIGITS = 1;

	private static final int DEFAULT_MAXIMUM_SIGNIFICANT_DIGITS = 21;

	/**
	 * To avoid any instantiation
	 */
	DefaultNumberFormatOptions() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions#getCompactDisplay()
	 */
	@Override
	public CompactDisplay getCompactDisplay() {
		return CompactDisplay.SHORT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions#getCurrency()
	 */
	@Override
	public Currency getCurrency() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions#getCurrencyDisplay()
	 */
	@Override
	public CurrencyDisplay getCurrencyDisplay() {
		return CurrencyDisplay.SYMBOL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions#getCurrencySign()
	 */
	@Override
	public CurrencySign getCurrencySign() {
		return CurrencySign.STANDARD;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions#getNotation()
	 */
	@Override
	public Notation getNotation() {
		return Notation.STANDARD;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions#getSignDisplay()
	 */
	@Override
	public SignDisplay getSignDisplay() {
		return SignDisplay.AUTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions#getStyle()
	 */
	@Override
	public Style getStyle() {
		return Style.DECIMAL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions#getUnitsOfMeasure()
	 */
	@Override
	public List<MeasureUnit> getUnitsOfMeasure() {
		return Collections.emptyList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions#getUnitOfMeasureDisplay()
	 */
	@Override
	public MeasureUnitDisplay getUnitOfMeasureDisplay() {
		return MeasureUnitDisplay.SHORT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions#isUseGrouping()
	 */
	@Override
	public boolean isUseGrouping() {
		return DEFAULT_USE_GROUPING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions#getMinimumIntegerDigits()
	 */
	@Override
	public int getMinimumIntegerDigits() {
		return DEFAULT_MINIMUM_INTEGER_DIGITS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions#getMinimumSignificantDigits()
	 */
	@Override
	public int getMinimumSignificantDigits() {
		return DEFAULT_MINIMUM_SIGNIFICANT_DIGITS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions#getMaximumSignificantDigits()
	 */
	@Override
	public int getMaximumSignificantDigits() {
		return DEFAULT_MAXIMUM_SIGNIFICANT_DIGITS;
	}

}
