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
package org.pepstock.charba.client.intl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.ChartEnvelop;
import org.pepstock.charba.client.Helpers;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayUtil;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions;
import org.pepstock.charba.client.defaults.globals.DefaultNumberFormatOptions;
import org.pepstock.charba.client.intl.enums.CompactDisplay;
import org.pepstock.charba.client.intl.enums.Currency;
import org.pepstock.charba.client.intl.enums.CurrencyDisplay;
import org.pepstock.charba.client.intl.enums.CurrencySign;
import org.pepstock.charba.client.intl.enums.MeasureUnit;
import org.pepstock.charba.client.intl.enums.MeasureUnitDisplay;
import org.pepstock.charba.client.intl.enums.Notation;
import org.pepstock.charba.client.intl.enums.SignDisplay;
import org.pepstock.charba.client.intl.enums.Style;
import org.pepstock.charba.client.items.Undefined;

/**
 * The object configures a number formatter.<br>
 * See <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Intl/NumberFormat/NumberFormat">MDN</a> for more details.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class NumberFormatOptions extends BaseFormatOptions<IsDefaultNumberFormatOptions> implements IsDefaultNumberFormatOptions {

	/**
	 * Public factory to create a number format options from a native object.
	 */
	public static final NumberFormatOptionsFactory FACTORY = new NumberFormatOptionsFactory();
	// Pairs of simple units can be concatenated with "-per-" to make a compound unit.
	private static final String COMPOUND_UNIT_SEPARATOR = "-per-";

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		COMPACT_DISPLAY("compactDisplay"),
		CURRENCY("currency"),
		CURRENCY_DISPLAY("currencyDisplay"),
		CURRENCY_SIGN("currencySign"),
		NOTATION("notation"),
		SIGN_DISPLAY("signDisplay"),
		STYLE("style"),
		UNIT("unit"),
		UNIT_DISPLAY("unitDisplay"),
		USE_GROUPING("useGrouping"),
		MINIMUM_INTEGER_DIGITS("minimumIntegerDigits"),
		MINIMUM_FRACTION_DIGITS("minimumFractionDigits"),
		MAXIMUM_FRACTION_DIGITS("maximumFractionDigits"),
		MINIMUM_SIGNIFICANT_DIGITS("minimumSignificantDigits"),
		MAXIMUM_SIGNIFICANT_DIGITS("maximumSignificantDigits");

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
	 * Creates new number format options object.
	 */
	public NumberFormatOptions() {
		this(null);
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	NumberFormatOptions(NativeObject nativeObject) {
		this(nativeObject, DefaultNumberFormatOptions.INSTANCE);
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 * @param defaultValues default values for the options
	 */
	NumberFormatOptions(NativeObject nativeObject, IsDefaultNumberFormatOptions defaultValues) {
		super(nativeObject, defaultValues);
	}

	/**
	 * Set the compact display when {@link Notation#COMPACT} is set.
	 * 
	 * @param display the compact display when {@link Notation#COMPACT} is set
	 */
	public void setCompactDisplay(CompactDisplay display) {
		setValue(Property.COMPACT_DISPLAY, display);
	}

	/**
	 * Returns the compact display when {@link Notation#COMPACT} is set.
	 * 
	 * @return the compact display when {@link Notation#COMPACT} is set
	 */
	@Override
	public CompactDisplay getCompactDisplay() {
		return getValue(Property.COMPACT_DISPLAY, CompactDisplay.values(), getDefaultValues().getCompactDisplay());
	}

	/**
	 * Sets the currency to use in currency formatting.
	 * 
	 * @param currency the currency to use in currency formatting
	 */
	public void setCurrency(Currency currency) {
		setValue(Property.CURRENCY, currency);
	}

	/**
	 * Returns the currency to use in currency formatting.
	 * 
	 * @return the currency to use in currency formatting
	 */
	@Override
	public Currency getCurrency() {
		return getValue(Property.CURRENCY, Currency.values(), getDefaultValues().getCurrency());
	}

	/**
	 * Sets how to display the currency in currency formatting.
	 * 
	 * @param currencyDisplay how to display the currency in currency formatting
	 */
	public void setCurrencyDisplay(CurrencyDisplay currencyDisplay) {
		setValue(Property.CURRENCY_DISPLAY, currencyDisplay);
	}

	/**
	 * Returns how to display the currency in currency formatting.
	 * 
	 * @return how to display the currency in currency formatting
	 */
	@Override
	public CurrencyDisplay getCurrencyDisplay() {
		return getValue(Property.CURRENCY_DISPLAY, CurrencyDisplay.values(), getDefaultValues().getCurrencyDisplay());
	}

	/**
	 * In many locales, accounting format means to wrap the number with parentheses instead of appending a minus sign.<br>
	 * You can enable this formatting by setting the currency sign option to "accounting" otherwise "standard".
	 * 
	 * @param currencySign the currency format to use on formatting
	 */
	public void setCurrencySign(CurrencySign currencySign) {
		setValue(Property.CURRENCY_SIGN, currencySign);
	}

	/**
	 * In many locales, accounting format means to wrap the number with parentheses instead of appending a minus sign.<br>
	 * You can enable this formatting by setting the currency sign option to "accounting" otherwise "standard".
	 * 
	 * @return the currency format to use on formatting
	 */
	@Override
	public CurrencySign getCurrencySign() {
		return getValue(Property.CURRENCY_SIGN, CurrencySign.values(), getDefaultValues().getCurrencySign());
	}

	/**
	 * Sets the formatting that should be displayed for the number.
	 * 
	 * @param notation the formatting that should be displayed for the number
	 */
	public void setNotation(Notation notation) {
		setValue(Property.NOTATION, notation);
	}

	/**
	 * Returns the formatting that should be displayed for the number.
	 * 
	 * @return the formatting that should be displayed for the number
	 */
	@Override
	public Notation getNotation() {
		return getValue(Property.NOTATION, Notation.values(), getDefaultValues().getNotation());
	}

	/**
	 * Sets when to display the sign for the number.
	 * 
	 * @param signDisplay when to display the sign for the number
	 */
	public void setSignDisplay(SignDisplay signDisplay) {
		setValue(Property.SIGN_DISPLAY, signDisplay);
	}

	/**
	 * Returns when to display the sign for the number.
	 * 
	 * @return when to display the sign for the number
	 */
	@Override
	public SignDisplay getSignDisplay() {
		return getValue(Property.SIGN_DISPLAY, SignDisplay.values(), getDefaultValues().getSignDisplay());
	}

	/**
	 * Sets the formatting style to use.
	 * 
	 * @param style the formatting style to use
	 */
	public void setStyle(Style style) {
		setValue(Property.STYLE, style);
	}

	/**
	 * Returns the formatting style to use.
	 * 
	 * @return the formatting style to use
	 */
	@Override
	public Style getStyle() {
		return getValue(Property.STYLE, Style.values(), getDefaultValues().getStyle());
	}

	/**
	 * Sets the unit to use in unit formatting.<br>
	 * If more that 1 unit has been passed, a compound unit has been created.
	 * 
	 * @param units the units to use in units formatting
	 */
	public void setUnitsOfMeasure(MeasureUnit... units) {
		setValue(Property.UNIT, createCompoundUnitIdentifier(units));
	}

	/**
	 * Sets the unit to use in unit formatting.<br>
	 * If more that 1 unit has been passed, a compound unit has been created.
	 * 
	 * @param units the units to use in units formatting
	 */
	public void setUnitsOfMeasure(List<MeasureUnit> units) {
		// checks if argument is consistent
		if (ArrayListHelper.isConsistent(units)) {
			// creates the string to parse
			setValue(Property.UNIT, createCompoundUnitIdentifier(ArrayUtil.toMeasureUnits(units)));
		} else {
			// removes the key
			// because the argument is not consistent
			remove(Property.UNIT);
		}
	}

	/**
	 * Returns an unmodifiable list of units to use in unit formatting.
	 * 
	 * @return an unmodifiable list of unit to use in unit formatting
	 */
	@Override
	public List<MeasureUnit> getUnitsOfMeasure() {
		return parseCompoundUnitIdentifier(getValue(Property.UNIT, Undefined.STRING));
	}

	/**
	 * Creates a compound unit identifier by units passed as argument.<br>
	 * Pairs of simple units can be concatenated with "-per-" to make a compound unit.
	 * 
	 * @param units array of units to join to create the identifier
	 * @return a compound unit identifier
	 */
	private String createCompoundUnitIdentifier(MeasureUnit... units) {
		// check if argument is consistent
		if (units != null && units.length > 0) {
			// creates a builder
			StringBuilder sb = new StringBuilder(Key.checkAndGetIfValid(units[0]).value());
			// scans the arguments starting from second item of array
			for (int i = 1; i < units.length; i++) {
				// adds separator and unit value
				sb.append(COMPOUND_UNIT_SEPARATOR).append(units[i].value());
			}
			// returns value
			return sb.toString();
		}
		// if here the argument is not consistent
		// then returns undefined value
		return Undefined.STRING;
	}

	/**
	 * Returns an unmodifiable list of units of units parsing a string which can represent a single unit or a compound one.
	 * 
	 * @param compoundUnitIdentifier compound identifier to be parsed
	 * @return an unmodifiable list of units
	 */
	private List<MeasureUnit> parseCompoundUnitIdentifier(String compoundUnitIdentifier) {
		// checks if argument is consistent
		if (compoundUnitIdentifier != null && compoundUnitIdentifier.trim().length() > 0) {
			// creates result reference
			List<MeasureUnit> result = new LinkedList<>();
			// splits the argument by separator
			String[] tokens = compoundUnitIdentifier.split(COMPOUND_UNIT_SEPARATOR);
			// checks if tokens are consistent
			if (tokens.length > 0) {
				// scans tokens
				for (String token : tokens) {
					// retrieves the unit and adds it to result
					result.add(Key.getKeyByValue(MeasureUnit.values(), token));
				}
				// returns result
				return Collections.unmodifiableList(result);
			}
		}
		// if here, the argument is not consistent
		// then returns an empty list
		return getDefaultValues().getUnitsOfMeasure();
	}

	/**
	 * Sets the unit formatting style to use in unit formatting.
	 * 
	 * @param unitDisplay the unit formatting style to use in unit formatting
	 */
	public void setUnitOfMeasureDisplay(MeasureUnitDisplay unitDisplay) {
		setValue(Property.UNIT_DISPLAY, unitDisplay);
	}

	/**
	 * Returns the unit formatting style to use in unit formatting.
	 * 
	 * @return the unit formatting style to use in unit formatting
	 */
	@Override
	public MeasureUnitDisplay getUnitOfMeasureDisplay() {
		return getValue(Property.UNIT_DISPLAY, MeasureUnitDisplay.values(), getDefaultValues().getUnitOfMeasureDisplay());
	}

	/**
	 * Sets <code>true</code> whether to use grouping separators, such as thousands separators or thousand/lakh/crore separators.
	 * 
	 * @param useGrouping <code>true</code> whether to use grouping separators, such as thousands separators or thousand/lakh/crore separators
	 */
	public void setUseGrouping(boolean useGrouping) {
		setValue(Property.USE_GROUPING, useGrouping);
	}

	/**
	 * Returns <code>true</code> whether to use grouping separators, such as thousands separators or thousand/lakh/crore separators.
	 * 
	 * @return <code>true</code> whether to use grouping separators, such as thousands separators or thousand/lakh/crore separators
	 */
	@Override
	public boolean isUseGrouping() {
		return getValue(Property.USE_GROUPING, getDefaultValues().isUseGrouping());
	}

	// -----------------------------------------------------------------------------------------
	// The following properties fall in the two groups:
	// minimumIntegerDigits, minimumFractionDigits, and maximumFractionDigits in one group,
	// minimumSignificantDigits and maximumSignificantDigits in the other
	// If at least one property from the second group is defined, then the first group is ignored.
	// ------------------------------------------------------------------------------------------
	// first group
	// ------------------------------------------------------------------------------------------

	/**
	 * Sets the minimum number of integer digits to use.<br>
	 * Possible values are from 1 to 21.
	 * 
	 * @param minimumIntegerDigits the minimum number of integer digits to use
	 */
	public void setMinimumIntegerDigits(int minimumIntegerDigits) {
		setValue(Property.MINIMUM_INTEGER_DIGITS, Math.max(Math.min(minimumIntegerDigits, 21), 1));
	}

	/**
	 * Returns the minimum number of integer digits to use.
	 * 
	 * @return the minimum number of integer digits to use
	 */
	@Override
	public int getMinimumIntegerDigits() {
		return getValue(Property.MINIMUM_INTEGER_DIGITS, getDefaultValues().getMinimumIntegerDigits());
	}

	/**
	 * Sets the minimum number of fraction digits to use.<br>
	 * Possible values are from 0 to 20.
	 * 
	 * @param minimumFractionDigits the minimum number of fraction digits to use
	 */
	public void setMinimumFractionDigits(int minimumFractionDigits) {
		setValue(Property.MINIMUM_FRACTION_DIGITS, Math.max(Math.min(minimumFractionDigits, 20), 0));
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
		return getValue(Property.MINIMUM_FRACTION_DIGITS, getDefaultValues().getMinimumFractionDigits());
	}

	/**
	 * Sets the maximum number of fraction digits to use.<br>
	 * Possible values are from 0 to 20.
	 * 
	 * @param maximumFractionDigits the maximum number of fraction digits to use
	 */
	public void setMaximumFractionDigits(int maximumFractionDigits) {
		setValue(Property.MAXIMUM_FRACTION_DIGITS, Math.max(Math.min(maximumFractionDigits, 20), 0));
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
		return getValue(Property.MAXIMUM_FRACTION_DIGITS, getDefaultValues().getMaximumFractionDigits());
	}

	// -----------------------------------------------------------------------------------------
	// The following properties fall in the two groups:
	// minimumIntegerDigits, minimumFractionDigits, and maximumFractionDigits in one group,
	// minimumSignificantDigits and maximumSignificantDigits in the other
	// If at least one property from the second group is defined, then the first group is ignored.
	// -----------------------------------------------------------------------------------------
	// second group
	// -----------------------------------------------------------------------------------------

	/**
	 * Sets the minimum number of significant digits to use.<br>
	 * Possible values are from 1 to 21.
	 * 
	 * @param minimumIntegerDigits the minimum number of significant digits to use
	 */
	public void setMinimumSignificantDigits(int minimumIntegerDigits) {
		setValue(Property.MINIMUM_SIGNIFICANT_DIGITS, Math.max(Math.min(minimumIntegerDigits, 21), 1));
	}

	/**
	 * Returns the minimum number of significant digits to use.
	 * 
	 * @return the minimum number of significant digits to use
	 */
	@Override
	public int getMinimumSignificantDigits() {
		return getValue(Property.MINIMUM_SIGNIFICANT_DIGITS, getDefaultValues().getMinimumSignificantDigits());
	}

	/**
	 * Sets the maximum number of significant digits to use.<br>
	 * Possible values are from 1 to 21.
	 * 
	 * @param minimumIntegerDigits the maximum number of significant digits to use
	 */
	public void setMaximumSignificantDigits(int minimumIntegerDigits) {
		setValue(Property.MAXIMUM_SIGNIFICANT_DIGITS, Math.max(Math.min(minimumIntegerDigits, 21), 1));
	}

	/**
	 * Returns the maximum number of significant digits to use.
	 * 
	 * @return the maximum number of significant digits to use
	 */
	@Override
	public int getMaximumSignificantDigits() {
		return getValue(Property.MAXIMUM_SIGNIFICANT_DIGITS, getDefaultValues().getMaximumSignificantDigits());
	}

	/**
	 * Loads the {@link NativeObject} in the envelop passed as argument.<br>
	 * This method is called from {@link Helpers#formatNumber(double, CLocale, NumberFormatOptions)}.
	 * 
	 * @param envelop envelop where loads the {@link NativeObject}
	 */
	public void load(ChartEnvelop<NativeObject> envelop) {
		// checks if envelop is consistent
		if (envelop != null) {
			// stored native object in the envelop
			envelop.setContent(getNativeObject());
		}
	}

	/**
	 * Creates a number format options by a native object and a default values instance.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	public static class NumberFormatOptionsFactory implements NativeObjectContainerFactory<NumberFormatOptions> {

		/**
		 * To avoid any instantiation
		 */
		private NumberFormatOptionsFactory() {
			// do nothing
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public NumberFormatOptions create(NativeObject nativeObject) {
			return create(nativeObject, null);
		}

		/**
		 * Creates a number format options by a native object and a default values instance.
		 * 
		 * @param nativeObject native object instance to be wrapped.
		 * @param defaultValues default values for the options
		 * @return a number format options instance
		 */
		public NumberFormatOptions create(NativeObject nativeObject, IsDefaultNumberFormatOptions defaultValues) {
			return new NumberFormatOptions(nativeObject, defaultValues == null ? DefaultNumberFormatOptions.INSTANCE : defaultValues);
		}

	}

}
