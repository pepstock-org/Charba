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
package org.pepstock.charba.client.configuration;

import java.util.List;

import org.pepstock.charba.client.intl.NumberFormatOptions;
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
import org.pepstock.charba.client.options.IsNumberFormat;

/**
 * Manages a {@link NumberFormatOptions} in order to set and get all number format options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class NumberFormatter extends AbstractDynamicConfiguration<IsNumberFormat> implements IsNumberFormat {

	/**
	 * Creates the number formatter by a number format options provider.
	 * 
	 * @param provider number format provider instance
	 */
	NumberFormatter(IsProvider<IsNumberFormat> provider) {
		super(provider);
	}

	/**
	 * Sets the locale matching algorithm to use.
	 * 
	 * @param localeMatcher the locale matching algorithm to use
	 */
	@Override
	public void setLocaleMatcher(LocaleMatcher localeMatcher) {
		checkAndGet().setLocaleMatcher(localeMatcher);
	}

	/**
	 * Returns the locale matching algorithm to use.
	 * 
	 * @return the locale matching algorithm to use
	 */
	@Override
	public LocaleMatcher getLocaleMatcher() {
		return checkAndGet().getLocaleMatcher();
	}

	/**
	 * Sets the numbering system to use.
	 * 
	 * @param numberingSystem the numbering system to use
	 */
	@Override
	public void setNumberingSystem(NumberingSystem numberingSystem) {
		checkAndGet().setNumberingSystem(numberingSystem);
	}

	/**
	 * Returns the numbering system to use.
	 * 
	 * @return the numbering system to use
	 */
	@Override
	public NumberingSystem getNumberingSystem() {
		return checkAndGet().getNumberingSystem();
	}

	/**
	 * Set the compact display when {@link Notation#COMPACT} is set.<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Safari.
	 * 
	 * @param display the compact display when {@link Notation#COMPACT} is set
	 */
	@Override
	public void setCompactDisplay(CompactDisplay display) {
		checkAndGet().setCompactDisplay(display);
	}

	/**
	 * Returns the compact display when {@link Notation#COMPACT} is set.<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Safari.
	 * 
	 * @return the compact display when {@link Notation#COMPACT} is set
	 */
	@Override
	public CompactDisplay getCompactDisplay() {
		return checkAndGet().getCompactDisplay();
	}

	/**
	 * Sets the currency to use in currency formatting.
	 * 
	 * @param currency the currency to use in currency formatting
	 */
	@Override
	public void setCurrency(Currency currency) {
		checkAndGet().setCurrency(currency);
	}

	/**
	 * Returns the currency to use in currency formatting.
	 * 
	 * @return the currency to use in currency formatting
	 */
	@Override
	public Currency getCurrency() {
		return checkAndGet().getCurrency();
	}

	/**
	 * Sets how to display the currency in currency formatting.
	 * 
	 * @param currencyDisplay how to display the currency in currency formatting
	 */
	@Override
	public void setCurrencyDisplay(CurrencyDisplay currencyDisplay) {
		checkAndGet().setCurrencyDisplay(currencyDisplay);
	}

	/**
	 * Returns how to display the currency in currency formatting.
	 * 
	 * @return how to display the currency in currency formatting
	 */
	@Override
	public CurrencyDisplay getCurrencyDisplay() {
		return checkAndGet().getCurrencyDisplay();
	}

	/**
	 * In many locales, accounting format means to wrap the number with parentheses instead of appending a minus sign.<br>
	 * You can enable this formatting by setting the currency sign option to "accounting" otherwise "standard".<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Safari.
	 * 
	 * @param currencySign the currency format to use on formatting
	 */
	@Override
	public void setCurrencySign(CurrencySign currencySign) {
		checkAndGet().setCurrencySign(currencySign);
	}

	/**
	 * In many locales, accounting format means to wrap the number with parentheses instead of appending a minus sign.<br>
	 * You can enable this formatting by setting the currency sign option to "accounting" otherwise "standard".<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Safari.
	 * 
	 * @return the currency format to use on formatting
	 */
	@Override
	public CurrencySign getCurrencySign() {
		return checkAndGet().getCurrencySign();
	}

	/**
	 * Sets the formatting that should be displayed for the number.<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Safari.
	 * 
	 * @param notation the formatting that should be displayed for the number
	 */
	@Override
	public void setNotation(Notation notation) {
		checkAndGet().setNotation(notation);
	}

	/**
	 * Returns the formatting that should be displayed for the number.<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Safari.
	 * 
	 * @return the formatting that should be displayed for the number
	 */
	@Override
	public Notation getNotation() {
		return checkAndGet().getNotation();
	}

	/**
	 * Sets when to display the sign for the number.<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Safari.
	 * 
	 * @param signDisplay when to display the sign for the number
	 */
	@Override
	public void setSignDisplay(SignDisplay signDisplay) {
		checkAndGet().setSignDisplay(signDisplay);
	}

	/**
	 * Returns when to display the sign for the number.<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Safari.
	 * 
	 * @return when to display the sign for the number
	 */
	@Override
	public SignDisplay getSignDisplay() {
		return checkAndGet().getSignDisplay();
	}

	/**
	 * Sets the formatting style to use.
	 * 
	 * @param style the formatting style to use
	 */
	@Override
	public void setStyle(Style style) {
		checkAndGet().setStyle(style);
	}

	/**
	 * Returns the formatting style to use.
	 * 
	 * @return the formatting style to use
	 */
	@Override
	public Style getStyle() {
		return checkAndGet().getStyle();
	}

	/**
	 * Sets the unit to use in unit formatting.<br>
	 * If more that 1 unit has been passed, a compound unit has been created.<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Safari.
	 * 
	 * @param units the units to use in units formatting
	 */
	@Override
	public void setUnitsOfMeasure(MeasureUnit... units) {
		checkAndGet().setUnitsOfMeasure(units);
	}

	/**
	 * Sets the unit to use in unit formatting.<br>
	 * If more that 1 unit has been passed, a compound unit has been created.<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Safari.
	 * 
	 * @param units the units to use in units formatting
	 */
	@Override
	public void setUnitsOfMeasure(List<MeasureUnit> units) {
		checkAndGet().setUnitsOfMeasure(units);
	}

	/**
	 * Returns an unmodifiable list of units to use in unit formatting.<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Safari.
	 * 
	 * @return an unmodifiable list of unit to use in unit formatting
	 */
	@Override
	public List<MeasureUnit> getUnitsOfMeasure() {
		return checkAndGet().getUnitsOfMeasure();
	}

	/**
	 * Sets the unit formatting style to use in unit formatting.<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Safari.
	 * 
	 * @param unitDisplay the unit formatting style to use in unit formatting
	 */
	@Override
	public void setUnitOfMeasureDisplay(MeasureUnitDisplay unitDisplay) {
		checkAndGet().setUnitOfMeasureDisplay(unitDisplay);
	}

	/**
	 * Returns the unit formatting style to use in unit formatting.<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Safari.
	 * 
	 * @return the unit formatting style to use in unit formatting
	 */
	@Override
	public MeasureUnitDisplay getUnitOfMeasureDisplay() {
		return checkAndGet().getUnitOfMeasureDisplay();
	}

	/**
	 * Sets <code>true</code> whether to use grouping separators, such as thousands separators or thousand/lakh/crore separators.
	 * 
	 * @param useGrouping <code>true</code> whether to use grouping separators, such as thousands separators or thousand/lakh/crore separators
	 */
	@Override
	public void setUseGrouping(boolean useGrouping) {
		checkAndGet().setUseGrouping(useGrouping);
	}

	/**
	 * Returns <code>true</code> whether to use grouping separators, such as thousands separators or thousand/lakh/crore separators.
	 * 
	 * @return <code>true</code> whether to use grouping separators, such as thousands separators or thousand/lakh/crore separators
	 */
	@Override
	public boolean isUseGrouping() {
		return checkAndGet().isUseGrouping();
	}

	/**
	 * Sets the minimum number of integer digits to use.<br>
	 * Possible values are from 1 to 21.
	 * 
	 * @param minimumIntegerDigits the minimum number of integer digits to use
	 */
	@Override
	public void setMinimumIntegerDigits(int minimumIntegerDigits) {
		checkAndGet().setMinimumIntegerDigits(minimumIntegerDigits);
	}

	/**
	 * Returns the minimum number of integer digits to use.
	 * 
	 * @return the minimum number of integer digits to use
	 */
	@Override
	public int getMinimumIntegerDigits() {
		return checkAndGet().getMinimumIntegerDigits();
	}

	/**
	 * Sets the minimum number of fraction digits to use.<br>
	 * Possible values are from 0 to 20.
	 * 
	 * @param minimumFractionDigits the minimum number of fraction digits to use
	 */
	@Override
	public void setMinimumFractionDigits(int minimumFractionDigits) {
		checkAndGet().setMinimumFractionDigits(minimumFractionDigits);
	}

	/**
	 * Returns the minimum number of fraction digits to use.<br>
	 * The default for plain number and percent formatting is 0.<br>
	 * The default for currency formatting is the number of minor unit digits provided by the currency, see {@link Currency#getMinimumFractionDigits()}.
	 * 
	 * @return the minimum number of fraction digits to use.<br>
	 *         The default for plain number and percent formatting is 0.<br>
	 *         The default for currency formatting is the number of minor unit digits provided by the currency, see {@link Currency#getMinimumFractionDigits()}
	 */
	@Override
	public int getMinimumFractionDigits() {
		return checkAndGet().getMinimumFractionDigits();
	}

	/**
	 * Sets the maximum number of fraction digits to use.<br>
	 * Possible values are from 0 to 20.
	 * 
	 * @param maximumFractionDigits the maximum number of fraction digits to use
	 */
	@Override
	public void setMaximumFractionDigits(int maximumFractionDigits) {
		checkAndGet().setMaximumFractionDigits(maximumFractionDigits);
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
	@Override
	public int getMaximumFractionDigits() {
		return checkAndGet().getMaximumFractionDigits();
	}

	/**
	 * Sets the minimum number of significant digits to use.<br>
	 * Possible values are from 1 to 21.
	 * 
	 * @param minimumIntegerDigits the minimum number of significant digits to use
	 */
	@Override
	public void setMinimumSignificantDigits(int minimumIntegerDigits) {
		checkAndGet().setMinimumSignificantDigits(minimumIntegerDigits);
	}

	/**
	 * Returns the minimum number of significant digits to use.
	 * 
	 * @return the minimum number of significant digits to use
	 */
	@Override
	public int getMinimumSignificantDigits() {
		return checkAndGet().getMinimumSignificantDigits();
	}

	/**
	 * Sets the maximum number of significant digits to use.<br>
	 * Possible values are from 1 to 21.
	 * 
	 * @param minimumIntegerDigits the maximum number of significant digits to use
	 */
	@Override
	public void setMaximumSignificantDigits(int minimumIntegerDigits) {
		checkAndGet().setMaximumSignificantDigits(minimumIntegerDigits);
	}

	/**
	 * Returns the maximum number of significant digits to use.
	 * 
	 * @return the maximum number of significant digits to use
	 */
	@Override
	public int getMaximumSignificantDigits() {
		return checkAndGet().getMaximumSignificantDigits();
	}
}
