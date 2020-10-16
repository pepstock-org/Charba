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

import java.util.HashMap;
import java.util.Map;

import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.utils.RegExp;
import org.pepstock.charba.client.utils.RegExpResult;

/**
 * The builder is used to build instances of {@link CLocale} from values configured by the setters.<br>
 * A {@link CLocale} object created by a builder is well-formed and can be transformed to a well-formed IETF BCP 47 language and it's also cached.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class CLocaleBuilder {

	// REGEXP pattern for locale
	private static final String REGEXP_LOCALE_PATTERN = "^(?<language>[A-Za-z]{2,4})([_-](?<script>[A-Za-z]{4}|[0-9]{3}))?([_-](?<region>[A-Za-z]{2}|[0-9]{3}))?((?<variantAndExtension>[A-Za-z0-9-_]+))?$";
	// REGEXP for locale
	private static final RegExp REGEXP_LOCALE = new RegExp(REGEXP_LOCALE_PATTERN);
	// groups factory to parse the regular expression result
	private static final LocaleRegExpGroupsFactory FACTORY = new LocaleRegExpGroupsFactory();
	// cache of built locales
	private static final Map<String, CLocale> LOCALES = new HashMap<>();

	private final Language language;

	private Script script = null;

	private Region region = null;

	private String variantAndExtension = null;

	/**
	 * Constructs a builder by the language passed as argument.
	 * 
	 * @param language language for locale building
	 */
	private CLocaleBuilder(Language language) {
		this.language = language != null ? language : Language.ENGLISH;
	}

	/**
	 * Creates a builder by the {@link Language} passed as argument.
	 * 
	 * @param language language for locale building
	 * @return a locale builder instance
	 */
	public static CLocaleBuilder create(Language language) {
		// checks if language is consistent
		Key.checkIfValid(language);
		// creates the builder
		return new CLocaleBuilder(language);
	}

	/**
	 * Sets the script code of the locale.
	 * 
	 * @param script the script code of the locale
	 * @return a locale builder instance
	 */
	public CLocaleBuilder setScript(Script script) {
		this.script = script;
		return this;
	}

	/**
	 * Sets the region to use for locale.
	 *
	 * @param region the region to use for locale.
	 * @return a locale builder instance
	 */
	public CLocaleBuilder setRegion(Region region) {
		this.region = region;
		return this;
	}

	/**
	 * Sets the variant and extension parts of the locale.
	 * 
	 * @param variantAndExtension the variant and extension parts of the locale
	 * @return a locale builder instance
	 */
	public CLocaleBuilder setVariantAndExtension(String variantAndExtension) {
		this.variantAndExtension = variantAndExtension;
		return this;
	}

	/**
	 * Returns an instance of locale created from the fields set on this builder.
	 *
	 * @return a locale instance.
	 */
	public CLocale build() {
		// creates locale key
		String localeKey = createLocaleIdentifier(language, script, region, variantAndExtension);
		// creates and returns the locale
		return createLocale(localeKey, language, script, region, variantAndExtension);
	}

	/**
	 * Returns an instance of locale created from the fields set on this builder.
	 * 
	 * @param localeIdentifier locale identifier to parse to get a locale.
	 * @return a locale instance.
	 */
	public static CLocale build(String localeIdentifier) {
		// checks if argument is consistent
		if (localeIdentifier != null && localeIdentifier.trim().length() > 0) {
			RegExpResult result = REGEXP_LOCALE.exec(localeIdentifier);
			// checks if the locale string was parsed correctly
			if (result != null) {
				// gets the group with all token
				LocaleRegExpGroups groups = result.groups(FACTORY);
				// checks if the groups is consistent
				if (groups != null) {
					// gets the references
					// from groups
					Language language = groups.getLanguage();
					Script script = groups.getScript();
					Region region = groups.getRegion();
					String variantAndExtension = groups.getVariantAndExtension();
					// creates the identifier
					String identifier = createLocaleIdentifier(language, script, region, variantAndExtension);
					// creates and returns the locale
					return createLocale(identifier, language, script, region, variantAndExtension);
				}
			}
		}
		// if here the locale argument passed as argument
		// if not consistent
		throw new IllegalArgumentException("Locale argument is not consistent");
	}

	/**
	 * Creates a locale if not already created and cached, otherwise will return the cached one.
	 * 
	 * @param identifier locale identifier, it's a unique key here.
	 * @param language language instance needed to define a locale
	 * @param script script instance needed to define a locale
	 * @param region region instance needed to define a locale
	 * @param variantAndExtension variant and extension part needed to define a locale
	 * @return a locale instance
	 */
	private static CLocale createLocale(String identifier, Language language, Script script, Region region, String variantAndExtension) {
		// checks if is in cache
		if (LOCALES.containsKey(identifier)) {
			// returns the cached locale
			return LOCALES.get(identifier);
		}
		// creates new locale
		CLocale locale = new CLocale(identifier, language, script, region, variantAndExtension);
		// stores into cache
		LOCALES.put(identifier, locale);
		return locale;
	}

	/**
	 * Creates the string identifier which is used for unique key of a locale and its locale value.<br>
	 * The key for locale will have the following format:<br>
	 * <br>
	 * <code>Language[-Script][-Region][-VariantAndExtension]</code> <br>
	 * 
	 * @param language language instance needed to define a locale
	 * @param script script instance needed to define a locale
	 * @param region region instance needed to define a locale
	 * @param variantAndExtension variant and extension part needed to define a locale
	 * @return the string identifier which is used for unique key of a locale and its locale value
	 */
	private static String createLocaleIdentifier(Language language, Script script, Region region, String variantAndExtension) {
		// checks if language is consistent
		Key.checkIfValid(language);
		// creates builder with the language
		StringBuilder sb = new StringBuilder(language.value());
		// checks if script code is consistent
		if (Key.isValid(script)) {
			// adds the script code
			sb.append(Constants.MINUS).append(script.value());
		}
		// checks if region is consistent
		if (Key.isValid(region)) {
			// adds the region
			sb.append(Constants.MINUS).append(region.value());
		}
		// checks if variant and extension parts are consistent
		if (variantAndExtension != null && variantAndExtension.trim().length() > 0) {
			// checks also if is starting with minus (separator)
			if (!variantAndExtension.startsWith(Constants.MINUS)) {
				// if not, it adds it
				sb.append(Constants.MINUS);
			}
			// adds variant and extension
			sb.append(variantAndExtension);
		}
		// returns as string
		return sb.toString();
	}

	/**
	 * Internal native object factory to pass to {@link RegExpResult#groups(NativeObjectContainerFactory)} method in order to read the regular expression tokens, by the key set
	 * into regular expression pattern.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class LocaleRegExpGroupsFactory implements NativeObjectContainerFactory<LocaleRegExpGroups> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.NativeObject)
		 */
		@Override
		public LocaleRegExpGroups create(NativeObject nativeObject) {
			return new LocaleRegExpGroups(nativeObject);
		}

	}

	/**
	 * This object is mapping the result of {@link RegExpResult#groups(NativeObjectContainerFactory)} in order to get the tokens of regexp result.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	private static class LocaleRegExpGroups extends NativeObjectContainer {

		/**
		 * Name of properties of native object.
		 */
		private enum Property implements Key
		{
			LANGUAGE("language"),
			SCRIPT("script"),
			REGION("region"),
			VARIANT_AND_EXTENSION("variantAndExtension");

			// name value of property
			private final String value;

			/**
			 * Creates with the property value to use into native object.
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

		private final Language language;

		/**
		 * Creates the object with native object instance to be wrapped.
		 * 
		 * @param nativeObject native object instance to be wrapped.
		 */
		private LocaleRegExpGroups(NativeObject nativeObject) {
			super(nativeObject);
			// checks if the mandatory language property is there
			if (!isType(Property.LANGUAGE, ObjectType.STRING)) {
				throw new IllegalArgumentException("Language was not recognized from regular expression");
			}
			// gets the language if there is
			String value = getValue(Property.LANGUAGE, UndefinedValues.STRING);
			// get the item from enumeration
			this.language = Language.getByCode(value);
			// checks if the mandatory language property is consistent
			if (this.language == null) {
				throw new IllegalArgumentException("Language '" + value + "' is invalid");
			}
		}

		/**
		 * Returns the language.
		 * 
		 * @return the language
		 */
		private Language getLanguage() {
			return language;
		}

		/**
		 * Returns the script.
		 * 
		 * @return the script
		 */
		private Script getScript() {
			return checkAndGet(Property.SCRIPT, Script.values());
		}

		/**
		 * Returns the region.
		 * 
		 * @return the region
		 */
		private Region getRegion() {
			return checkAndGet(Property.REGION, Region.values());
		}

		/**
		 * Returns the variant and extension part of locale.
		 * 
		 * @return the variant and extension part of locale
		 */
		private String getVariantAndExtension() {
			return getValue(Property.VARIANT_AND_EXTENSION, UndefinedValues.STRING);
		}

		/**
		 * Checks the type of the property and then returns the right enumeration item if it matches with the string value of native object.
		 * 
		 * @param property property key of native object to look for
		 * @param enumeration the items of the enumeration
		 * @param <T> type of property, extending key
		 * @return the item of enumeration which matches with the string value.
		 */
		private <T extends Key> T checkAndGet(Property property, T[] enumeration) {
			// checks if the property is there
			if (isType(property, ObjectType.STRING)) {
				// gets the value if there is
				String value = getValue(property, UndefinedValues.STRING);
				// gets and returns the item from enumeration
				return Key.getKeyByValue(enumeration, value);
			}
			// if here, the item is not consistent
			// then return null
			return null;
		}

	}
}
