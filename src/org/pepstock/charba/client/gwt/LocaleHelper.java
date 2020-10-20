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
package org.pepstock.charba.client.gwt;

import org.pepstock.charba.client.intl.CLocale;
import org.pepstock.charba.client.intl.CLocaleBuilder;

import com.google.gwt.i18n.client.LocaleInfo;

/**
 * Utility to transform locale GWT resource into {@link CLocale} object used by Charba.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LocaleHelper {

	/**
	 * To avoid any instantiation
	 */
	private LocaleHelper() {
		// do nothing
	}

	/**
	 * Creates {@link CLocale} using the current {@link LocaleInfo}, created by GWT.
	 * 
	 * @return {@link CLocale} using the current {@link LocaleInfo}, created by GWT
	 */
	public static CLocale toLocaleFromCurrent() {
		return CLocaleBuilder.build(LocaleInfo.getCurrentLocale().getLocaleName());
	}

}
