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

import java.util.List;

import org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions;
import org.pepstock.charba.client.intl.enums.CompactDisplay;
import org.pepstock.charba.client.intl.enums.Currency;
import org.pepstock.charba.client.intl.enums.CurrencyDisplay;
import org.pepstock.charba.client.intl.enums.CurrencySign;
import org.pepstock.charba.client.intl.enums.LocaleMatcher;
import org.pepstock.charba.client.intl.enums.MeasureUnit;
import org.pepstock.charba.client.intl.enums.MeasureUnitDisplay;
import org.pepstock.charba.client.intl.enums.Notation;
import org.pepstock.charba.client.intl.enums.NumberingSystem;
import org.pepstock.charba.client.intl.enums.SignDisplay;
import org.pepstock.charba.client.intl.enums.Style;

/**
 * Defines a configuration element which is a number format options.
 * 
 * @author Andrea "Stock" Stocchero
 * @see AbstractNumberFormat
 */
public interface IsNumberFormat extends IsDefaultNumberFormatOptions{
	
	/**
	 * Sets the locale matching algorithm to use.
	 * 
	 * @param localeMatcher the locale matching algorithm to use
	 */
	void setLocaleMatcher(LocaleMatcher localeMatcher);

	/**
	 * Sets the numbering system to use.
	 * 
	 * @param numberingSystem the numbering system to use
	 */
	void setNumberingSystem(NumberingSystem numberingSystem);

	/**
	 * Set the compact display when {@link Notation#COMPACT} is set.<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Safari.
	 * 
	 * @param display the compact display when {@link Notation#COMPACT} is set
	 */
	void setCompactDisplay(CompactDisplay display);

	/**
	 * Sets the currency to use in currency formatting.
	 * 
	 * @param currency the currency to use in currency formatting
	 */
	void setCurrency(Currency currency);

	/**
	 * Sets how to display the currency in currency formatting.
	 * 
	 * @param currencyDisplay how to display the currency in currency formatting
	 */
	void setCurrencyDisplay(CurrencyDisplay currencyDisplay);

	/**
	 * In many locales, accounting format means to wrap the number with parentheses instead of appending a minus sign.<br>
	 * You can enable this formatting by setting the currency sign option to "accounting" otherwise "standard".<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Safari.
	 * 
	 * @param currencySign the currency format to use on formatting
	 */
	void setCurrencySign(CurrencySign currencySign);

	/**
	 * Sets the formatting that should be displayed for the number.<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Safari.
	 * 
	 * @param notation the formatting that should be displayed for the number
	 */
	void setNotation(Notation notation);

	/**
	 * Sets when to display the sign for the number.<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Safari.
	 * 
	 * @param signDisplay when to display the sign for the number
	 */
	void setSignDisplay(SignDisplay signDisplay);

	/**
	 * Sets the formatting style to use.
	 * 
	 * @param style the formatting style to use
	 */
	void setStyle(Style style);

	/**
	 * Sets the unit to use in unit formatting.<br>
	 * If more that 1 unit has been passed, a compound unit has been created.<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Safari.
	 * 
	 * @param units the units to use in units formatting
	 */
	void setUnitsOfMeasure(MeasureUnit... units);

	/**
	 * Sets the unit to use in unit formatting.<br>
	 * If more that 1 unit has been passed, a compound unit has been created.<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Safari.
	 * 
	 * @param units the units to use in units formatting
	 */
	void setUnitsOfMeasure(List<MeasureUnit> units);

	/**
	 * Sets the unit formatting style to use in unit formatting.<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Safari.
	 * 
	 * @param unitDisplay the unit formatting style to use in unit formatting
	 */
	void setUnitOfMeasureDisplay(MeasureUnitDisplay unitDisplay);

	/**
	 * Sets <code>true</code> whether to use grouping separators, such as thousands separators or thousand/lakh/crore separators.
	 * 
	 * @param useGrouping <code>true</code> whether to use grouping separators, such as thousands separators or thousand/lakh/crore separators
	 */
	void setUseGrouping(boolean useGrouping);

	/**
	 * Sets the minimum number of integer digits to use.<br>
	 * Possible values are from 1 to 21.
	 * 
	 * @param minimumIntegerDigits the minimum number of integer digits to use
	 */
	void setMinimumIntegerDigits(int minimumIntegerDigits);

	/**
	 * Sets the minimum number of fraction digits to use.<br>
	 * Possible values are from 0 to 20.
	 * 
	 * @param minimumFractionDigits the minimum number of fraction digits to use
	 */
	void setMinimumFractionDigits(int minimumFractionDigits);

	/**
	 * Sets the maximum number of fraction digits to use.<br>
	 * Possible values are from 0 to 20.
	 * 
	 * @param maximumFractionDigits the maximum number of fraction digits to use
	 */
	void setMaximumFractionDigits(int maximumFractionDigits);

	/**
	 * Sets the minimum number of significant digits to use.<br>
	 * Possible values are from 1 to 21.
	 * 
	 * @param minimumIntegerDigits the minimum number of significant digits to use
	 */
	void setMinimumSignificantDigits(int minimumIntegerDigits);

	/**
	 * Sets the maximum number of significant digits to use.<br>
	 * Possible values are from 1 to 21.
	 * 
	 * @param minimumIntegerDigits the maximum number of significant digits to use
	 */
	void setMaximumSignificantDigits(int minimumIntegerDigits);
}
