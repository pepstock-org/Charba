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

import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.NativeObject;
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
 * It wraps the {@link NativeNumberFormat} JavaScript object in order to format numbers.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class NumberFormatWrapper extends AbtsractFormatWrapper<NativeNumberFormat, Double, IsDefaultNumberFormatOptions> {

	// empty options constants
	private static final NativeObject EMPTY_OPTIONS = new NumberFormatOptions().nativeObject();

	/**
	 * Creates object that enables language sensitive number formatting, using the locale options and specific options.
	 * 
	 * @param locale a locale instance
	 * @param options options to configure the number format
	 */
	NumberFormatWrapper(CLocale locale, NumberFormatOptions options) {
		// create the native number format
		super(new NativeNumberFormat(locale.getIdentifier(), options != null ? options.nativeObject() : EMPTY_OPTIONS));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.intl.AbtsractFormatWrapper#format(java.lang.Object)
	 */
	@Override
	String format(Double value) {
		return getNativeFormat().format(value.doubleValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.intl.AbtsractFormatWrapper#resolvedOptions()
	 */
	@Override
	IsDefaultNumberFormatOptions resolvedOptions() {
		// creates a number format options by the native object
		// returned by resolved option
		NumberFormatOptions formatOptions = new NumberFormatOptions(getNativeFormat().resolvedOptions());
		// creates the resolved options
		return new ResolvedOptions(formatOptions);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.intl.AbtsractFormatWrapper#formatToParts(java.lang.Object)
	 */
	@Override
	ArrayObject formatToParts(Double value) {
		return getNativeFormat().formatToParts(value.doubleValue());
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
