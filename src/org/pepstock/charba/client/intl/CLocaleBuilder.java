/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.intl;

import java.util.HashMap;
import java.util.Map;

import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.items.Undefined;
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
		LocaleRegExpGroups uncheckedGroups = applyRegExp(localeIdentifier);
		// checks if the groups instance is consistent
		LocaleRegExpGroups groups = Checker.checkAndGetIfValid(uncheckedGroups, "Locale argument");
		// checks if the groups is consistent
		Checker.assertCheck(groups.isConsistent(), "Locale argument is not consistent");
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

	/**
	 * Returns an instance of locale created from the fields set on this builder.
	 * 
	 * @param localeIdentifier locale identifier to parse to get a locale.
	 * @return a locale instance.
	 */
	public static boolean isValid(String localeIdentifier) {
		LocaleRegExpGroups groups = applyRegExp(localeIdentifier);
		// checks if the groups is consistent
		return groups != null && groups.isConsistent();
	}

	/**
	 * Parses the locale identifier by the regular expression.<br>
	 * Returns <code>null</code> if the argument is not consistent as locale.
	 * 
	 * @param localeIdentifier locale identifier to parse to get a locale.
	 * @return groups object, result of regular expression execution
	 */
	private static LocaleRegExpGroups applyRegExp(String localeIdentifier) {
		// checks if argument is consistent
		if (localeIdentifier != null && localeIdentifier.trim().length() > 0) {
			RegExpResult result = REGEXP_LOCALE.exec(localeIdentifier.trim());
			// checks if the locale string was parsed correctly
			if (result != null) {
				// gets and returns the group with all token
				return result.groups(FACTORY);
			}
		}
		// if here, the argument is not consistent or
		// not parsed by regexp
		// then returns null
		return null;
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
		// stores in the cache
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
	 * Internal native object factory to pass to {@link RegExpResult#groups(NativeObjectContainerFactory)} method in order to read the regular expression tokens, by the key set in
	 * the regular expression pattern.
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
		 * Creates the object with native object instance to be wrapped.
		 * 
		 * @param nativeObject native object instance to be wrapped.
		 */
		private LocaleRegExpGroups(NativeObject nativeObject) {
			super(nativeObject);
		}

		/**
		 * Returns the language.
		 * 
		 * @return the language
		 */
		private Language getLanguage() {
			return Language.getByCode(getValue(Property.LANGUAGE, Undefined.STRING));
		}

		/**
		 * Returns the script.
		 * 
		 * @return the script
		 */
		private Script getScript() {
			return getValue(Property.SCRIPT, Script.values(), null);
		}

		/**
		 * Returns the region.
		 * 
		 * @return the region
		 */
		private Region getRegion() {
			return getValue(Property.REGION, Region.values(), null);
		}

		/**
		 * Returns the variant and extension part of locale.
		 * 
		 * @return the variant and extension part of locale
		 */
		private String getVariantAndExtension() {
			return getValue(Property.VARIANT_AND_EXTENSION, Undefined.STRING);
		}

		/**
		 * Returns <code>true</code> if the groups received by regular expression is consistent.
		 * 
		 * @return <code>true</code> if the groups received by regular expression is consistent
		 */
		private boolean isConsistent() {
			// checks if the mandatory language property is there
			if (!isType(Property.LANGUAGE, ObjectType.STRING) || Language.getByCode(getValue(Property.LANGUAGE, Undefined.STRING)) == null) {
				return false;
			}
			// checks region, if exists it must be a recognized region
			if (isType(Property.REGION, ObjectType.STRING) && !Key.hasKeyByValue(Region.values(), getValue(Property.REGION, Undefined.STRING))) {
				return false;
			}
			// checks script, if exists it must be a recognized script
			// it's the last check that is true is true all method
			return !(isType(Property.SCRIPT, ObjectType.STRING) && !Key.hasKeyByValue(Script.values(), getValue(Property.SCRIPT, Undefined.STRING)));
		}

	}
}