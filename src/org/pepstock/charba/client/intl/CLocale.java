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

import org.pepstock.charba.client.commons.Key;

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
 * See <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Intl">MDN page</a> for more details.
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

	// reference for default locale
	private static CLocale defaultLocale = US;

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
	 * Sets the current value of the default locale for this instance.
	 *
	 * @param locale the default locale for this instance
	 */
	public static void setDefault(CLocale locale) {
		// checks if locale argument is consistent
		if (locale != null) {
			// stores the default locale
			defaultLocale = locale;
		}
	}

	/**
	 * Gets the current value of the default locale for this instance.
	 *
	 * @return the default locale for this instance
	 */
	public static CLocale getDefault() {
		return defaultLocale;
	}

}
