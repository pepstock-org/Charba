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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions;
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

/**
 * This is abstract numebr format object element of the chart options.<br>
 * 
 * @author Andrea "Stock" Stocchero
 * @see NumberFormatOptions
 */
abstract class AbstractNumberFormat<P extends AbstractModel<?, ?>> extends AbstractModel<P, IsDefaultNumberFormatOptions> implements IsNumberFormat {
	
	private final NumberFormatOptions numberFormatOptions;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param model options model of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	AbstractNumberFormat(P model, Key childKey, IsDefaultNumberFormatOptions defaultValues, NativeObject nativeObject) {
		super(model, childKey, defaultValues, nativeObject);
		// creates the number format option
		this.numberFormatOptions = NumberFormatOptions.FACTORY.create(getNativeObject(), getDefaultValues());
	}
	
	/**
	 * Sets the locale matching algorithm to use.
	 * 
	 * @param localeMatcher the locale matching algorithm to use
	 */
	@Override
	public final void setLocaleMatcher(LocaleMatcher localeMatcher) {
		numberFormatOptions.setLocaleMatcher(localeMatcher);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the locale matching algorithm to use.
	 * 
	 * @return the locale matching algorithm to use
	 */
	@Override
	public final LocaleMatcher getLocaleMatcher() {
		return numberFormatOptions.getLocaleMatcher();
	}

	/**
	 * Sets the numbering system to use.
	 * 
	 * @param numberingSystem the numbering system to use
	 */
	@Override
	public final void setNumberingSystem(NumberingSystem numberingSystem) {
		numberFormatOptions.setNumberingSystem(numberingSystem);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the numbering system to use.
	 * 
	 * @return the numbering system to use
	 */
	@Override
	public final NumberingSystem getNumberingSystem() {
		return numberFormatOptions.getNumberingSystem();
	}
	
	/**
	 * Set the compact display when {@link Notation#COMPACT} is set.<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Internet Explorer and Safari.
	 * 
	 * @param display the compact display when {@link Notation#COMPACT} is set
	 */
	@Override
	public final void setCompactDisplay(CompactDisplay display) {
		numberFormatOptions.setCompactDisplay(display);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the compact display when {@link Notation#COMPACT} is set.<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Internet Explorer and Safari.
	 * 
	 * @return the compact display when {@link Notation#COMPACT} is set
	 */
	@Override
	public final CompactDisplay getCompactDisplay() {
		return numberFormatOptions.getCompactDisplay();
	}

	/**
	 * Sets the currency to use in currency formatting.
	 * 
	 * @param currency the currency to use in currency formatting
	 */
	@Override
	public final void setCurrency(Currency currency) {
		numberFormatOptions.setCurrency(currency);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the currency to use in currency formatting.
	 * 
	 * @return the currency to use in currency formatting
	 */
	@Override
	public final Currency getCurrency() {
		return numberFormatOptions.getCurrency();
	}

	/**
	 * Sets how to display the currency in currency formatting.
	 * 
	 * @param currencyDisplay how to display the currency in currency formatting
	 */
	@Override
	public final void setCurrencyDisplay(CurrencyDisplay currencyDisplay) {
		numberFormatOptions.setCurrencyDisplay(currencyDisplay);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns how to display the currency in currency formatting.
	 * 
	 * @return how to display the currency in currency formatting
	 */
	@Override
	public final CurrencyDisplay getCurrencyDisplay() {
		return numberFormatOptions.getCurrencyDisplay();
	}

	/**
	 * In many locales, accounting format means to wrap the number with parentheses instead of appending a minus sign.<br>
	 * You can enable this formatting by setting the currency sign option to "accounting" otherwise "standard".<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Internet Explorer and Safari.
	 * 
	 * @param currencySign the currency format to use on formatting
	 */
	@Override
	public final void setCurrencySign(CurrencySign currencySign) {
		numberFormatOptions.setCurrencySign(currencySign);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * In many locales, accounting format means to wrap the number with parentheses instead of appending a minus sign.<br>
	 * You can enable this formatting by setting the currency sign option to "accounting" otherwise "standard".<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Internet Explorer and Safari.
	 * 
	 * @return the currency format to use on formatting
	 */
	@Override
	public final CurrencySign getCurrencySign() {
		return numberFormatOptions.getCurrencySign();
	}

	/**
	 * Sets the formatting that should be displayed for the number.<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Internet Explorer and Safari.
	 * 
	 * @param notation the formatting that should be displayed for the number
	 */
	@Override
	public final void setNotation(Notation notation) {
		numberFormatOptions.setNotation(notation);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the formatting that should be displayed for the number.<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Internet Explorer and Safari.
	 * 
	 * @return the formatting that should be displayed for the number
	 */
	@Override
	public final Notation getNotation() {
		return numberFormatOptions.getNotation();
	}

	/**
	 * Sets when to display the sign for the number.<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Internet Explorer and Safari.
	 * 
	 * @param signDisplay when to display the sign for the number
	 */
	@Override
	public final void setSignDisplay(SignDisplay signDisplay) {
		numberFormatOptions.setSignDisplay(signDisplay);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns when to display the sign for the number.<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Internet Explorer and Safari.
	 * 
	 * @return when to display the sign for the number
	 */
	@Override
	public final SignDisplay getSignDisplay() {
		return numberFormatOptions.getSignDisplay();
	}

	/**
	 * Sets the formatting style to use.
	 * 
	 * @param style the formatting style to use
	 */
	@Override
	public final void setStyle(Style style) {
		numberFormatOptions.setStyle(style);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the formatting style to use.
	 * 
	 * @return the formatting style to use
	 */
	@Override
	public final Style getStyle() {
		return numberFormatOptions.getStyle();
	}

	/**
	 * Sets the unit to use in unit formatting.<br>
	 * If more that 1 unit has been passed, a compound unit has been created.<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Internet Explorer and Safari.
	 * 
	 * @param units the units to use in units formatting
	 */
	@Override
	public final void setUnitsOfMeasure(MeasureUnit... units) {
		numberFormatOptions.setUnitsOfMeasure(units);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Sets the unit to use in unit formatting.<br>
	 * If more that 1 unit has been passed, a compound unit has been created.<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Internet Explorer and Safari.
	 * 
	 * @param units the units to use in units formatting
	 */
	@Override
	public final void setUnitsOfMeasure(List<MeasureUnit> units) {
		numberFormatOptions.setUnitsOfMeasure(units);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns an unmodifiable list of units to use in unit formatting.<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Internet Explorer and Safari.
	 * 
	 * @return an unmodifiable list of unit to use in unit formatting
	 */
	@Override
	public final List<MeasureUnit> getUnitsOfMeasure() {
		return numberFormatOptions.getUnitsOfMeasure();
	}

	/**
	 * Sets the unit formatting style to use in unit formatting.<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Internet Explorer and Safari.
	 * 
	 * @param unitDisplay the unit formatting style to use in unit formatting
	 */
	@Override
	public final void setUnitOfMeasureDisplay(MeasureUnitDisplay unitDisplay) {
		numberFormatOptions.setUnitOfMeasureDisplay(unitDisplay);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the unit formatting style to use in unit formatting.<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Internet Explorer and Safari.
	 * 
	 * @return the unit formatting style to use in unit formatting
	 */
	@Override
	public final MeasureUnitDisplay getUnitOfMeasureDisplay() {
		return numberFormatOptions.getUnitOfMeasureDisplay();
	}

	/**
	 * Sets <code>true</code> whether to use grouping separators, such as thousands separators or thousand/lakh/crore separators.
	 * 
	 * @param useGrouping <code>true</code> whether to use grouping separators, such as thousands separators or thousand/lakh/crore separators
	 */
	@Override
	public final void setUseGrouping(boolean useGrouping) {
		numberFormatOptions.setUseGrouping(useGrouping);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns <code>true</code> whether to use grouping separators, such as thousands separators or thousand/lakh/crore separators.
	 * 
	 * @return <code>true</code> whether to use grouping separators, such as thousands separators or thousand/lakh/crore separators
	 */
	@Override
	public final boolean isUseGrouping() {
		return numberFormatOptions.isUseGrouping();
	}

	/**
	 * Sets the minimum number of integer digits to use.<br>
	 * Possible values are from 1 to 21.
	 * 
	 * @param minimumIntegerDigits the minimum number of integer digits to use
	 */
	@Override
	public final void setMinimumIntegerDigits(int minimumIntegerDigits) {
		numberFormatOptions.setMinimumIntegerDigits(minimumIntegerDigits);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the minimum number of integer digits to use.
	 * 
	 * @return the minimum number of integer digits to use
	 */
	@Override
	public final int getMinimumIntegerDigits() {
		return numberFormatOptions.getMinimumIntegerDigits();
	}

	/**
	 * Sets the minimum number of fraction digits to use.<br>
	 * Possible values are from 0 to 20.
	 * 
	 * @param minimumFractionDigits the minimum number of fraction digits to use
	 */
	@Override
	public final void setMinimumFractionDigits(int minimumFractionDigits) {
		numberFormatOptions.setMinimumFractionDigits(minimumFractionDigits);
		// checks if all parents are attached
		checkAndAddToParent();
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
	public final int getMinimumFractionDigits() {
		return numberFormatOptions.getMinimumFractionDigits();
	}

	/**
	 * Sets the maximum number of fraction digits to use.<br>
	 * Possible values are from 0 to 20.
	 * 
	 * @param maximumFractionDigits the maximum number of fraction digits to use
	 */
	@Override
	public final void setMaximumFractionDigits(int maximumFractionDigits) {
		numberFormatOptions.setMaximumFractionDigits(maximumFractionDigits);
		// checks if all parents are attached
		checkAndAddToParent();
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
	public final int getMaximumFractionDigits() {
		return numberFormatOptions.getMaximumFractionDigits();
	}

	/**
	 * Sets the minimum number of significant digits to use.<br>
	 * Possible values are from 1 to 21.
	 * 
	 * @param minimumIntegerDigits the minimum number of significant digits to use
	 */
	@Override
	public final void setMinimumSignificantDigits(int minimumIntegerDigits) {
		numberFormatOptions.setMinimumSignificantDigits(minimumIntegerDigits);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the minimum number of significant digits to use.
	 * 
	 * @return the minimum number of significant digits to use
	 */
	@Override
	public final int getMinimumSignificantDigits() {
		return numberFormatOptions.getMinimumSignificantDigits();
	}

	/**
	 * Sets the maximum number of significant digits to use.<br>
	 * Possible values are from 1 to 21.
	 * 
	 * @param minimumIntegerDigits the maximum number of significant digits to use
	 */
	@Override
	public final void setMaximumSignificantDigits(int minimumIntegerDigits) {
		numberFormatOptions.setMaximumSignificantDigits(minimumIntegerDigits);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the maximum number of significant digits to use.
	 * 
	 * @return the maximum number of significant digits to use
	 */
	@Override
	public final int getMaximumSignificantDigits() {
		return numberFormatOptions.getMaximumSignificantDigits();
	}

}