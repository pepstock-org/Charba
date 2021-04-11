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

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.GlobalOptions;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.Id;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.dom.BaseElement;
import org.pepstock.charba.client.dom.DOM;
import org.pepstock.charba.client.dom.NodeList;
import org.pepstock.charba.client.dom.elements.Meta;

/**
 * Unicode represents locales with a string, called a locale identifier.<br>
 * The locale identifier consists of a language identifier and extension tags.<br>
 * Language identifiers are the core of the locale, consisting of language, script, and region subtags.<br>
 * Additional information about the locale is stored in the optional extension tags.<br>
 * Extension tags hold information about locale aspects such as calendar type, clock type, and numbering system type.<br>
 * A Unicode BCP 47 locale identifier consists of<br>
 * <ul>
 * <li>a language code
 * <li>(optionally) a script code,
 * <li>(optionally) a region (or country) code
 * <li>(optionally) one or more variant codes
 * <li>(optionally) one or more extension sequences
 * </ul>
 * <br>
 * See <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Intl">MDN page</a> for more details.<br>
 * <b style="font-size: 16px">LOCALE auto recognition</b><br>
 * The <code>locale</code> client property can be specified using either a meta tag, as part of the query string in the host page’s URL or from default platform locale.<br>
 * If all cases are specified, the query string takes precedence, then meta data and finally the navigator language.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class CLocale {

	/**
	 * Useful constant for {@link Region#FRANCE}.
	 */
	public static final CLocale FRANCE = CLocaleBuilder.create(Language.FRENCH).setRegion(Region.FRANCE).build();
	/**
	 * Useful constant for {@link Region#GERMANY}.
	 */
	public static final CLocale GERMANY = CLocaleBuilder.create(Language.GERMAN).setRegion(Region.GERMANY).build();
	/**
	 * Useful constant for {@link Region#ITALY}.
	 */
	public static final CLocale ITALY = CLocaleBuilder.create(Language.ITALIAN).setRegion(Region.ITALY).build();
	/**
	 * Useful constant for {@link Region#JAPAN}.
	 */
	public static final CLocale JAPAN = CLocaleBuilder.create(Language.JAPANESE).setRegion(Region.JAPAN).build();
	/**
	 * Useful constant for {@link Region#CHINA}.
	 */
	public static final CLocale CHINA = CLocaleBuilder.create(Language.CHINESE).setRegion(Region.CHINA).build();
	/**
	 * Useful constant for {@link Region#UNITED_KINGDOM}.
	 */
	public static final CLocale UK = CLocaleBuilder.create(Language.ENGLISH).setRegion(Region.UNITED_KINGDOM).build();
	/**
	 * Useful constant for {@link Region#UNITED_STATES}.
	 */
	public static final CLocale US = CLocaleBuilder.create(Language.ENGLISH).setRegion(Region.UNITED_STATES).build();
	/**
	 * Useful constant for {@link Region#CANADA}.
	 */
	public static final CLocale CANADA = CLocaleBuilder.create(Language.ENGLISH).setRegion(Region.CANADA).build();
	/**
	 * Useful constant for {@link Region#CANADA}, for {@link Language#FRENCH}.
	 */
	public static final CLocale CANADA_FRENCH = CLocaleBuilder.create(Language.FRENCH).setRegion(Region.CANADA).build();
	/**
	 * Useful constant for {@link Region#RUSSIA}.
	 */
	public static final CLocale RUSSIA = CLocaleBuilder.create(Language.RUSSIAN).setRegion(Region.RUSSIA).build();
	/**
	 * Query string parameter key, used to retrieve the locale value, dynamically.
	 */
	public static final String LOCALE_QUERY_STRING_PARAM_KEY = "locale";
	/**
	 * DOM element id for meta element where to set the GWT property
	 */
	private static final String GWT_PROPERTY_KEY = "gwt:property";

	// locale key constant to retrieve the default locale from platform
	private static final Key LOCALE_KEY = Key.create(LOCALE_QUERY_STRING_PARAM_KEY);
	// reference for default locale
	private static CLocale defaultLocale = null;
	// flag to know if the default must be kept align with the defaults
	private static boolean mustKeepDefaultsAligned = false;

	private final String identifier;

	private final Language language;

	private final Script script;

	private final Region region;

	private final String variantAndExtension;

	/**
	 * Creates a locale using all arguments.
	 * 
	 * @param identifier locale identifier, it's a unique key here.
	 * @param language language instance needed to define a locale
	 * @param script script instance needed to define a locale
	 * @param region region instance needed to define a locale
	 * @param variantAndExtension variant and extension part needed to define a locale
	 */
	CLocale(String identifier, Language language, Script script, Region region, String variantAndExtension) {
		// checks if identifier is consistent
		if (identifier == null) {
			throw new IllegalArgumentException("Locale identifier argument is null");
		}
		// checks if language is consistent
		// language is the only one which MUST be consistent
		Key.checkIfValid(language);
		// stores all values
		this.identifier = identifier;
		this.language = language;
		this.script = script;
		this.region = region;
		this.variantAndExtension = variantAndExtension;
	}

	/**
	 * Returns the locale identifier which consists of a language identifier and extension tags.
	 * 
	 * @return the locale identifier which consists of a language identifier and extension tags
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * Returns the language of the locale.
	 * 
	 * @return the language of the locale
	 */
	public Language getLanguage() {
		return language;
	}

	/**
	 * Returns the script code of the locale.<br>
	 * It could be <code>null</code>.
	 * 
	 * @return the script code of the locale.<br>
	 *         It could be <code>null</code>.
	 */
	public Script getScript() {
		return script;
	}

	/**
	 * Returns the region of the locale.<br>
	 * It could be <code>null</code>.
	 * 
	 * @return the region of the locale.<br>
	 *         It could be <code>null</code>.
	 */
	public Region getRegion() {
		return region;
	}

	/**
	 * Returns the variant and extension parts of the locale.<br>
	 * It could be <code>null</code>.
	 * 
	 * @return the variant and extension parts of the locale.<br>
	 *         It could be <code>null</code>.
	 */
	public String getVariantAndExtension() {
		return variantAndExtension;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// checks if is the same object
		if (this == obj) {
			return true;
		}
		// checks if argument is null
		if (obj == null) {
			return false;
		}
		// checks if the class is the same
		if (getClass() != obj.getClass()) {
			return false;
		}
		// casts to a locale
		CLocale other = (CLocale) obj;
		// checks if identifier is consistent
		if (identifier != null) {
			// and compares the 2 identifiers
			return identifier.equals(other.identifier);
		}
		// if here, identifier is null
		// but that's not possible
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CLocale [identifier=" + identifier + "]";
	}

	/**
	 * Returns <code>true</code> if the default locale must kept aligned with the {@link GlobalOptions#setLocale(CLocale)}.
	 * 
	 * @return <code>true</code> if the default locale must kept aligned with the {@link GlobalOptions#setLocale(CLocale)}
	 */
	public static boolean mustKeepDefaultsAligned() {
		return mustKeepDefaultsAligned;
	}

	/**
	 * Sets <code>true</code> if the default locale must kept aligned with the {@link GlobalOptions#setLocale(CLocale)}.
	 * 
	 * @param keptAlignedWithDefaults <code>true</code> if the default locale must kept aligned with the {@link GlobalOptions#setLocale(CLocale)}
	 */
	public static void setMustKeepDefaultsAligned(boolean keptAlignedWithDefaults) {
		// checks if current flag is to align the defaults
		if (!CLocale.mustKeepDefaultsAligned && keptAlignedWithDefaults) {
			// sets the default to the defaults chart
			Defaults.get().getGlobal().setLocale(defaultLocale);
		}
		// stores the value
		CLocale.mustKeepDefaultsAligned = keptAlignedWithDefaults;
	}

	/**
	 * Sets the current value of the default locale for this instance.
	 *
	 * @param locale the default locale for this instance
	 */
	public static void setDefault(CLocale locale) {
		// checks if locale argument is consistent
		if (locale != null) {
			// stores the default locale
			setDefaultInternally(locale);
			// checks if it must set on chart default global
			if (mustKeepDefaultsAligned()) {
				// sets the default to the defaults chart
				Defaults.get().getGlobal().setLocale(defaultLocale);
			}
		}
	}

	/**
	 * Gets the current value of the default locale for this instance.
	 *
	 * @return the default locale for this instance
	 */
	public static CLocale getDefault() {
		// checks if the default has been set
		if (defaultLocale == null) {
			CLocale tempLocale;
			// extracts locale parameter from query string
			String localeFromQueryString = DOM.getLocation().getParameter(LOCALE_QUERY_STRING_PARAM_KEY);
			// extracts locale parameter from meta elements
			String localeFromMetaElements = getLocaleFromMetaElement();
			// extracts locale parameter from platform
			String localeFromPlatform = getDefaultLocale(LOCALE_KEY);
			// ---------------------
			// CHECK QUERY STRING
			// ---------------------
			// checks if consistent
			if (CLocaleBuilder.isValid(localeFromQueryString)) {
				// builds and store the locale
				tempLocale = CLocaleBuilder.build(localeFromQueryString);
			} else if (CLocaleBuilder.isValid(localeFromMetaElements)) {
				// ---------------------
				// CHECK META element language
				// ---------------------
				// builds and store the locale
				tempLocale = CLocaleBuilder.build(localeFromMetaElements);
			} else if (CLocaleBuilder.isValid(localeFromPlatform)) {
				// ---------------------
				// CHECK Platform LOCALE
				// ---------------------
				// builds and store the locale
				tempLocale = CLocaleBuilder.build(localeFromPlatform);
			} else {
				// if here, sets the static default as "us"
				tempLocale = US;
			}
			// sets internally
			setDefaultInternally(tempLocale);
		}
		// returns the locale
		return defaultLocale;
	}

	/**
	 * Sets the default locale privately, in order to avoid lazy setting on static reference.
	 * 
	 * @param locale the default locale for this instance
	 */
	private static void setDefaultInternally(CLocale locale) {
		// checks if locale argument is consistent
		if (locale != null) {
			// stores the default locale
			CLocale.defaultLocale = locale;
		}
	}

	/**
	 * Extracts the locale from {@link Meta} element with {@value GWT_PROPERTY_KEY} property, as implemented by GWT.<br>
	 * If there is not any locale setting, returns <code>null</code>.
	 * 
	 * @return the locale from {@link Meta} element with {@value GWT_PROPERTY_KEY} property, as implemented by GWT.<br>
	 *         If there is not any locale setting, returns <code>null</code>
	 */
	private static String getLocaleFromMetaElement() {
		// gets all nodes with TAG TD
		NodeList<BaseElement> elements = DOM.getDocument().getHead().getElementsByTagName(Meta.TAG);
		// scans all nodes
		for (int i = 0; i < elements.length(); i++) {
			// gets element
			BaseElement element = elements.item(i);
			// checks if the element is a meta element
			if (element instanceof Meta) {
				// casts to meta
				Meta meta = (Meta) element;
				// gets name
				String name = meta.getName();
				// gets content
				String content = meta.getContent();
				// checks if is the meta looked for
				if (name != null && content != null && GWT_PROPERTY_KEY.equalsIgnoreCase(name) && content.startsWith(LOCALE_QUERY_STRING_PARAM_KEY + Constants.EQ)) {
					// if here the property has been found
					// adds 1 because there is also the equals
					return content.substring(LOCALE_QUERY_STRING_PARAM_KEY.length() + 1);
				}
			}
		}
		// if here, the property is not found in the meta elements
		// then returns null
		return null;
	}
	

	/**
	 * Returns the default locale, set in the the platform.
	 * 
	 * @param key key of resolved options to get the locale, usually is "locale".
	 * @return the default locale, set in the the platform
	 */
	private static String getDefaultLocale(Key key) {
		// creates a number format
		NativeNumberFormat format = new NativeNumberFormat();
		// gets resolved options
		NativeObject options = format.resolvedOptions();
		// retrieves and returns the locale as string
		return Id.getStringProperty(key, options);
	}

}
