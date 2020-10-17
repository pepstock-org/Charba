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

import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
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
 * The object is a constructor for objects that enable language sensitive number formatting.<br>
 * See <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Intl/NumberFormat">MDN</a> for more details.<br>
 * <br>
 * <b style="font-size: 16px">PAY ATTENTION</b><br>
 * {@link NumberFormat#formatToParts(double)} is not supported on Internet Explorer.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class NumberFormat {
	// empty options constants
	private static final NativeObject EMPTY_OPTIONS = new NumberFormatOptions().nativeObject();
	// format parts factory instance
	private static final FormatPartsFactory FACTORY = new FormatPartsFactory();
	// locale instance
	private final CLocale locale;
	// native number format instance
	private final NativeNumberFormat nativeNumberFormat;
	// resolver instance instance
	// at the beginning is not set but it will be only once
	private IsDefaultNumberFormatOptions resolvedOptions = null;

	/**
	 * Creates object that enable language sensitive number formatting, using the locale options.
	 * 
	 * @param locale a locale instance
	 */
	public NumberFormat(CLocale locale) {
		this(locale, null);
	}

	/**
	 * Creates object that enables language sensitive number formatting, using the locale options and specific options.
	 * 
	 * @param locale a locale instance
	 * @param options options to configure the number format
	 */
	public NumberFormat(CLocale locale, NumberFormatOptions options) {
		// checks if locale is consistent
		if (locale == null) {
			throw new IllegalArgumentException("Locale argument is null");
		}
		// stores locale
		this.locale = locale;
		// checks if the locale is supported
		ArrayString supportedLocales = NativeNumberFormat.supportedLocalesOf(ArrayString.fromOrEmpty(locale.getIdentifier()), BaseFormatOptions.LOOKUP.nativeObject());
		// checks if is supported
		if (supportedLocales.length() == 0) {
			// if not, exception
			throw new IllegalArgumentException("Locale '" + locale.getIdentifier() + "' is not supported");
		}
		// create the native number format
		this.nativeNumberFormat = new NativeNumberFormat(locale.getIdentifier(), options != null ? options.nativeObject() : EMPTY_OPTIONS);
	}

	/**
	 * Returns the locale which has initialized the number format.
	 * 
	 * @return the locale which has initialized the number format
	 */
	public CLocale getLocale() {
		return locale;
	}

	/**
	 * Formats a number according to the locale and formatting options of this object.
	 * 
	 * @param value the number to format
	 * @return the number into a string according to the locale and formatting options
	 */
	public String format(double value) {
		return nativeNumberFormat.format(value);
	}

	/**
	 * Returns a new object with properties reflecting the locale and number formatting options computed during initialization of this object.
	 * 
	 * @return new object with properties reflecting the locale and number formatting options computed during initialization of this object
	 */
	public IsDefaultNumberFormatOptions resolvedOptions() {
		// checks if already called
		if (resolvedOptions == null) {
			// creates a number format options by the native object
			// returned by resolved option
			NumberFormatOptions formatOptions = new NumberFormatOptions(nativeNumberFormat.resolvedOptions());
			// creates the resolved options
			resolvedOptions = new ResolvedOptions(formatOptions);
		}
		// returns the resolved options
		return resolvedOptions;
	}

	/**
	 * Returns an array of objects containing the locale-specific tokens from which it possible to build custom strings while preserving the locale-specific parts.<br>
	 * It is useful for custom formatting of number strings.<br>
	 * <br>
	 * <b style="font-size: 16px">PAY ATTENTION</b><br>
	 * This method is not supported on Internet Explorer.
	 * 
	 * @param value number to format
	 * @return an array of objects containing the formatted number in parts.
	 */
	public List<FormatPart> formatToParts(double value) {
		// gets array of object
		ArrayObject array = nativeNumberFormat.formatToParts(value);
		// and transforms it in a list
		return ArrayListHelper.unmodifiableList(array, FACTORY);
	}

	/**
	 * Internal format part factory in order to be used when {@link NumberFormat#formatToParts(double)} is invoked.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class FormatPartsFactory implements NativeObjectContainerFactory<FormatPart> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public FormatPart create(NativeObject nativeObject) {
			return new FormatPart(nativeObject);
		}

	}

	/**
	 * Wrapper of a number format options in order to be consumed by its interface, when the resolve options methods has been invoked.<br>
	 * Wrapper is need in order to return only the interface with "get" methods.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class ResolvedOptions implements IsDefaultNumberFormatOptions {
		// format options instance
		private final NumberFormatOptions delegated;

		/**
		 * Creates the resolved options wrapping a normal number format options.
		 * 
		 * @param delegated number format options to o wrap
		 */
		private ResolvedOptions(NumberFormatOptions delegated) {
			this.delegated = delegated;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultBaseFormatOptions#getLocaleMatcher()
		 */
		@Override
		public LocaleMatcher getLocaleMatcher() {
			return delegated.getLocaleMatcher();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultBaseFormatOptions#getNumberingSystem()
		 */
		@Override
		public NumberingSystem getNumberingSystem() {
			return delegated.getNumberingSystem();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions#getCompactDisplay()
		 */
		@Override
		public CompactDisplay getCompactDisplay() {
			return delegated.getCompactDisplay();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions#getCurrency()
		 */
		@Override
		public Currency getCurrency() {
			return delegated.getCurrency();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions#getCurrencyDisplay()
		 */
		@Override
		public CurrencyDisplay getCurrencyDisplay() {
			return delegated.getCurrencyDisplay();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions#getCurrencySign()
		 */
		@Override
		public CurrencySign getCurrencySign() {
			return delegated.getCurrencySign();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions#getNotation()
		 */
		@Override
		public Notation getNotation() {
			return delegated.getNotation();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions#getSignDisplay()
		 */
		@Override
		public SignDisplay getSignDisplay() {
			return delegated.getSignDisplay();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions#getStyle()
		 */
		@Override
		public Style getStyle() {
			return delegated.getStyle();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions#getUnitsOfMeasure()
		 */
		@Override
		public List<MeasureUnit> getUnitsOfMeasure() {
			return delegated.getUnitsOfMeasure();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions#getUnitOfMeasureDisplay()
		 */
		@Override
		public MeasureUnitDisplay getUnitOfMeasureDisplay() {
			return delegated.getUnitOfMeasureDisplay();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions#isUseGrouping()
		 */
		@Override
		public boolean isUseGrouping() {
			return delegated.isUseGrouping();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions#getMinimumIntegerDigits()
		 */
		@Override
		public int getMinimumIntegerDigits() {
			return delegated.getMinimumIntegerDigits();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions#getMinimumFractionDigits()
		 */
		@Override
		public int getMinimumFractionDigits() {
			return delegated.getMinimumFractionDigits();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions#getMaximumFractionDigits()
		 */
		@Override
		public int getMaximumFractionDigits() {
			return delegated.getMaximumFractionDigits();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions#getMinimumSignificantDigits()
		 */
		@Override
		public int getMinimumSignificantDigits() {
			return delegated.getMinimumSignificantDigits();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.defaults.IsDefaultNumberFormatOptions#getMaximumSignificantDigits()
		 */
		@Override
		public int getMaximumSignificantDigits() {
			return delegated.getMaximumSignificantDigits();
		}

	}

}
