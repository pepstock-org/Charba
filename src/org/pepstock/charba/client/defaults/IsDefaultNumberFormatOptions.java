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
package org.pepstock.charba.client.defaults;

import java.util.List;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.globals.DefaultNumberFormatOptions;
import org.pepstock.charba.client.intl.NumberFormatOptions;
import org.pepstock.charba.client.intl.enums.CompactDisplay;
import org.pepstock.charba.client.intl.enums.Currency;
import org.pepstock.charba.client.intl.enums.CurrencyDisplay;
import org.pepstock.charba.client.intl.enums.CurrencySign;
import org.pepstock.charba.client.intl.enums.MeasureUnit;
import org.pepstock.charba.client.intl.enums.MeasureUnitDisplay;
import org.pepstock.charba.client.intl.enums.Notation;
import org.pepstock.charba.client.intl.enums.SignDisplay;
import org.pepstock.charba.client.intl.enums.Style;
import org.pepstock.charba.client.items.NumberFormatItem;

/**
 * Interface to define the number format options for internationalization.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface IsDefaultNumberFormatOptions extends IsDefaultBaseFormatOptions {

	/**
	 * Returns the compact display when {@link Notation#COMPACT} is set.
	 * 
	 * @return the compact display when {@link Notation#COMPACT} is set
	 */
	CompactDisplay getCompactDisplay();

	/**
	 * Returns the currency to use in currency formatting.
	 * 
	 * @return the currency to use in currency formatting
	 */
	Currency getCurrency();

	/**
	 * Returns how to display the currency in currency formatting.
	 * 
	 * @return how to display the currency in currency formatting
	 */
	CurrencyDisplay getCurrencyDisplay();

	/**
	 * In many locales, accounting format means to wrap the number with parentheses instead of appending a minus sign.<br>
	 * You can enable this formatting by setting the currency sign option to "accounting" otherwise "standard".
	 * 
	 * @return the currency format to use on formatting
	 */
	CurrencySign getCurrencySign();

	/**
	 * Returns the formatting that should be displayed for the number.
	 * 
	 * @return the formatting that should be displayed for the number
	 */
	Notation getNotation();

	/**
	 * Returns when to display the sign for the number.
	 * 
	 * @return when to display the sign for the number
	 */
	SignDisplay getSignDisplay();

	/**
	 * Returns the formatting style to use.
	 * 
	 * @return the formatting style to use
	 */
	Style getStyle();

	/**
	 * Returns an unmodifiable list of units to use in unit formatting.
	 * 
	 * @return an unmodifiable list of unit to use in unit formatting
	 */
	List<MeasureUnit> getUnitsOfMeasure();

	/**
	 * Returns the unit formatting style to use in unit formatting.
	 * 
	 * @return the unit formatting style to use in unit formatting
	 */
	MeasureUnitDisplay getUnitOfMeasureDisplay();

	/**
	 * Returns <code>true</code> whether to use grouping separators, such as thousands separators or thousand/lakh/crore separators.
	 * 
	 * @return <code>true</code> whether to use grouping separators, such as thousands separators or thousand/lakh/crore separators
	 */
	boolean isUseGrouping();

	/**
	 * Returns the minimum number of integer digits to use.
	 * 
	 * @return the minimum number of integer digits to use
	 */
	int getMinimumIntegerDigits();

	/**
	 * Returns the minimum number of fraction digits to use.<br>
	 * The default for plain number and percent formatting is 0; the default for currency formatting is the number of minor unit digits provided by the currency, see
	 * {@link Currency#getMinimumFractionDigits()}.
	 * 
	 * @return the minimum number of fraction digits to use.<br>
	 *         The default for plain number and percent formatting is 0; the default for currency formatting is the number of minor unit digits provided by the currency, see
	 *         {@link Currency#getMinimumFractionDigits()}
	 */
	default int getMinimumFractionDigits() {
		// the default for plain number and percent formatting is 0
		// the default for currency formatting is the number of minor unit digits provided by the currency.
		// gets style to get the default
		Style style = getStyle();
		// creates the default reference
		int defaultDigits = 0;
		// checks if there is the currency style is activate
		if (Style.CURRENCY.equals(style)) {
			// get currency
			Currency currency = getCurrency();
			// checks if currency is consistent
			if (Key.isValid(currency)) {
				// sets default
				defaultDigits = currency.getMinimumFractionDigits();
			}
		}
		return defaultDigits;
	}

	/**
	 * Returns the maximum number of fraction digits to use.<br>
	 * The default for plain number formatting is the larger of {@link NumberFormatOptions#getMinimumFractionDigits()} and 3.<br>
	 * The default for currency formatting is the larger of {@link NumberFormatOptions#getMinimumFractionDigits()} and the number of minor unit digits provided by currency, see
	 * {@link Currency#getMinimumFractionDigits()}.<br>
	 * The default for percent formatting is the larger of {@link NumberFormatOptions#getMinimumFractionDigits()} and 0.
	 * 
	 * @return the maximum number of fraction digits to use.<br>
	 *         The default for plain number formatting is the larger of {@link NumberFormatOptions#getMinimumFractionDigits()} and 3.<br>
	 *         The default for currency formatting is the larger of {@link NumberFormatOptions#getMinimumFractionDigits()} and the number of minor unit digits provided by currency,
	 *         see {@link Currency#getMinimumFractionDigits()}.<br>
	 *         The default for percent formatting is the larger of {@link NumberFormatOptions#getMinimumFractionDigits()} and 0.
	 */
	default int getMaximumFractionDigits() {
		// the default for plain number formatting is the larger of minimumFractionDigits and 3
		// the default for currency formatting is the larger of minimumFractionDigits and the number of minor unit digits provided by currency
		// the default for percent formatting is the larger of minimumFractionDigits and 0.
		// gets style to get the default
		Style style = getStyle();
		// creates the default reference
		// the default for plain number formatting is the larger of minimumFractionDigits and 3
		int defaultDigits = Math.max(getMinimumFractionDigits(), 3);
		// checks if there is the currency style is activate
		if (Style.CURRENCY.equals(style)) {
			// get currency
			Currency currency = getCurrency();
			// checks if currency is consistent
			if (Key.isValid(currency)) {
				// the default for currency formatting is the larger of minimumFractionDigits and the number of minor unit digits provided by currency
				defaultDigits = Math.max(getMinimumFractionDigits(), currency.getMinimumFractionDigits());
			}
		} else if (Style.PERCENT.equals(style)) {
			// the default for percent formatting is the larger of minimumFractionDigits and 0.
			defaultDigits = Math.max(getMinimumFractionDigits(), 0);
		}
		return defaultDigits;
	}

	/**
	 * Returns the minimum number of significant digits to use.
	 * 
	 * @return the minimum number of significant digits to use
	 */
	int getMinimumSignificantDigits();

	/**
	 * Returns the maximum number of significant digits to use.
	 * 
	 * @return the maximum number of significant digits to use
	 */
	int getMaximumSignificantDigits();
	
	/**
	 * Creates a number format options instance using default or cloning current instance.
	 * 
	 * @return a number format options instance using default or cloning current instance
	 */
	default NumberFormatItem create() {
		return create(new DefaultNumberFormatOptions());
	}
	
	/**
	 * Creates a number format options instance using default or cloning current instance.
	 * 
	 * @param defaultValues default provider
	 * @return a number format options instance using default or cloning current instance
	 */
	default NumberFormatItem create(IsDefaultNumberFormatOptions defaultValues) {
		// creates new number format item
		NumberFormatItem result = new NumberFormatItem(defaultValues);
		// loads number format
		result.setLocaleMatcher(getLocaleMatcher());
		result.setNumberingSystem(getNumberingSystem());
		result.setCompactDisplay(getCompactDisplay());
		result.setCurrency(getCurrency());
		result.setCurrencyDisplay(getCurrencyDisplay());
		result.setCurrencySign(getCurrencySign());
		result.setNotation(getNotation());
		result.setSignDisplay(getSignDisplay());
		result.setStyle(getStyle());
		result.setUnitsOfMeasure(getUnitsOfMeasure());
		result.setUnitOfMeasureDisplay(getUnitOfMeasureDisplay());
		result.setUseGrouping(isUseGrouping());
		result.setMinimumIntegerDigits(getMinimumIntegerDigits());
		result.setMinimumFractionDigits(getMinimumFractionDigits());
		result.setMaximumFractionDigits(getMaximumFractionDigits());
		result.setMinimumSignificantDigits(getMinimumIntegerDigits());
		result.setMaximumSignificantDigits(getMinimumIntegerDigits());
		// returns number format item
		return result;
	}

}
