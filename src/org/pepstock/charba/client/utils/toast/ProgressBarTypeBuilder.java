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
* @return builder instance */
package org.pepstock.charba.client.utils.toast;

import java.util.HashMap;
import java.util.Map;

import org.pepstock.charba.client.Injector;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.utils.Utilities;
import org.pepstock.charba.client.utils.toast.enums.DefaultProgressBarType;

/**
 * Comfortable object to create custom {@link IsProgressBarType} by a builder.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ProgressBarTypeBuilder extends AbstractTypeBuilder {

	// template to inject the style for new type
	// {0} : name of toast type
	// {1} : background color of toast type
	private static final String CSS_TEMPLATE = "#ct-container .ct-toast>div .progress-bar.{0}{opacity:.9;background:{1}}";
	// stores all new custom toast types
	private static final Map<String, StandardProgressBarType> CUSTOM_TYPES = new HashMap<>();

	/**
	 * To avoid any instantiation.
	 * 
	 * @param name name to use inside the native object as name of property
	 * @param backgroundColor background color of toast
	 * @param gradient gradient instance as background
	 */
	private ProgressBarTypeBuilder(Key name, IsColor backgroundColor, Gradient gradient) {
		super(name, backgroundColor, gradient);
	}

	// ----------------------------------
	// BACKGROUND COLOR
	// ----------------------------------

	/**
	 * Returns new builder instance, to build a custom toast type.
	 * 
	 * @param name name to use inside the native object as name of property
	 * @param backgroundColor background color of toast
	 * @return new builder instance
	 */
	public static ProgressBarTypeBuilder create(String name, String backgroundColor) {
		return create(Key.create(name), backgroundColor);
	}

	/**
	 * Returns new builder instance, to build a custom toast type.
	 * 
	 * @param name name to use inside the native object as name of property
	 * @param backgroundColor background color of toast
	 * @return new builder instance
	 */
	public static ProgressBarTypeBuilder create(Key name, String backgroundColor) {
		return create(name, ColorBuilder.parse(backgroundColor));
	}

	/**
	 * Returns new builder instance, to build a custom toast type.
	 * 
	 * @param name name to use inside the native object as name of property
	 * @param backgroundColor background color of toast
	 * @return new builder instance
	 */
	public static ProgressBarTypeBuilder create(String name, IsColor backgroundColor) {
		return create(Key.create(name), backgroundColor);
	}

	/**
	 * Returns new builder instance, to build a custom toast type.
	 * 
	 * @param name name to use inside the native object as name of property
	 * @param backgroundColor background color of toast
	 * @return new builder instance
	 */
	public static ProgressBarTypeBuilder create(Key name, IsColor backgroundColor) {
		// check if key is consistent
		checkName(name);
		// check if colors are consistent
		IsColor.checkIfValid(backgroundColor);
		// stores arguments and
		// returns builder
		return new ProgressBarTypeBuilder(name, backgroundColor, null);
	}

	// ----------------------------------
	// GRADIENT
	// ----------------------------------

	/**
	 * Returns new builder instance, to build a custom toast type.
	 * 
	 * @param name name to use inside the native object as name of property
	 * @param gradient background gradient of toast
	 * @return new builder instance
	 */
	public static ProgressBarTypeBuilder create(String name, Gradient gradient) {
		return create(Key.create(name), gradient);
	}

	/**
	 * Returns new builder instance, to build a custom toast type.
	 * 
	 * @param name name to use inside the native object as name of property
	 * @param gradient background gradient of toast
	 * @return new builder instance
	 */
	public static ProgressBarTypeBuilder create(Key name, Gradient gradient) {
		// stores arguments and
		// returns builder
		return create(name, null, Checker.checkAndGetIfValid(gradient, "Gradient "));
	}

	// ----------------------------------
	// COMMON builder
	// ----------------------------------

	/**
	 * Returns new builder instance, to build a custom toast type.
	 * 
	 * @param name name to use inside the native object as name of property
	 * @param backgroundColor background color of toast
	 * @param gradient gradient instance as background
	 * @return new builder instance
	 */
	private static ProgressBarTypeBuilder create(Key name, IsColor backgroundColor, Gradient gradient) {
		// check if key is consistent
		checkName(name);
		// stores arguments and
		// returns builder
		return new ProgressBarTypeBuilder(name, backgroundColor, gradient);
	}

	/**
	 * Returns new toast type.
	 * 
	 * @return new toast type.
	 */
	public IsProgressBarType build() {
		// checks if id as argument is a default one
		for (DefaultProgressBarType defToastType : DefaultProgressBarType.values()) {
			// checks if id is equals to default
			if (Key.equals(defToastType, getName())) {
				// if equals, returns the default id
				return defToastType;
			}
		}
		// gets toast type from map
		StandardProgressBarType type = CUSTOM_TYPES.computeIfAbsent(getName().value(), mapKey -> new StandardProgressBarType(getName(), getBackgroundColor(), getBackgroundAsGradient()));
		// checks if it has been injected
		if (!type.isInjected()) {
			// creates CSS statement from template
			String content = Utilities.applyTemplate(CSS_TEMPLATE, type.value(), type.toCSSBackground());
			// injects CSS resource
			Injector.ensureCssInjected(new CssInjectableResource(type, true, content));
			// resets flag
			type.setInjected(true);
		}
		return type;
	}

	/**
	 * Returns the custom toast type, if exists.
	 * 
	 * @param name name of toast type.
	 * @return the custom toast type of <code>null</code> if not exists.
	 */
	static IsProgressBarType get(String name) {
		// checks i argument is consistent
		if (name != null) {
			return CUSTOM_TYPES.get(name);
		}
		// if here, argument not consistent
		// then returns null
		return null;
	}

}
