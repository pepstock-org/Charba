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
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.utils.Utilities;
import org.pepstock.charba.client.utils.toast.enums.DefaultToastType;

/**
 * Comfortable object to create custom {@link IsToastType} by a builder.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ToastTypeBuilder extends AbstractTypeBuilder {

	// template to inject the style for new type
	// {0} : name of toast type
	// {1} : color of toast type
	// {2} : background color of toast type
	private static final String CSS_TEMPLATE = "#ct-container .ct-toast>div.ct-{0}{background-color:{2}}#ct-container .ct-toast>div.ct-{0} .ct-title,#ct-container .ct-toast>div.ct-{0} .ct-text{color:{1}}";
	// stores all new custom toast types
	private static final Map<String, StandardToastType> CUSTOM_TYPES = new HashMap<>();

	// type color instance
	private final IsColor color;

	/**
	 * To avoid any instantiation.
	 * 
	 * @param name name to use inside the native object as name of property
	 * @param color color of the toast type for text
	 * @param backgroundColor background color of toast
	 */
	private ToastTypeBuilder(Key name, IsColor color, IsColor backgroundColor) {
		super(name, backgroundColor);
		this.color = color;
	}

	/**
	 * Returns new builder instance, to build a custom toast type.
	 * 
	 * @param name name to use inside the native object as name of property
	 * @param backgroundColor background color of toast
	 * @return new builder instance
	 */
	public static ToastTypeBuilder create(String name, String backgroundColor) {
		return create(Key.create(name), backgroundColor);
	}

	/**
	 * Returns new builder instance, to build a custom toast type.
	 * 
	 * @param name name to use inside the native object as name of property
	 * @param backgroundColor background color of toast
	 * @return new builder instance
	 */
	public static ToastTypeBuilder create(Key name, String backgroundColor) {
		return create(name, DefaultToastType.DEFAULT.getColor(), ColorBuilder.parse(backgroundColor));
	}

	/**
	 * Returns new builder instance, to build a custom toast type.
	 * 
	 * @param name name to use inside the native object as name of property
	 * @param backgroundColor background color of toast
	 * @return new builder instance
	 */
	public static ToastTypeBuilder create(String name, IsColor backgroundColor) {
		return create(Key.create(name), backgroundColor);
	}

	/**
	 * Returns new builder instance, to build a custom toast type.
	 * 
	 * @param name name to use inside the native object as name of property
	 * @param backgroundColor background color of toast
	 * @return new builder instance
	 */
	public static ToastTypeBuilder create(Key name, IsColor backgroundColor) {
		return create(name, DefaultToastType.DEFAULT.getColor(), backgroundColor);
	}

	/**
	 * Returns new builder instance, to build a custom toast type.
	 * 
	 * @param name name to use inside the native object as name of property
	 * @param color color of the toast type for text
	 * @param backgroundColor background color of toast
	 * @return new builder instance
	 */
	public static ToastTypeBuilder create(String name, String color, String backgroundColor) {
		return create(Key.create(name), color, backgroundColor);
	}

	/**
	 * Returns new builder instance, to build a custom toast type.
	 * 
	 * @param name name to use inside the native object as name of property
	 * @param color color of the toast type for text
	 * @param backgroundColor background color of toast
	 * @return new builder instance
	 */
	public static ToastTypeBuilder create(Key name, String color, String backgroundColor) {
		return create(name, ColorBuilder.parse(color), ColorBuilder.parse(backgroundColor));
	}

	/**
	 * Returns new builder instance, to build a custom toast type.
	 * 
	 * @param name name to use inside the native object as name of property
	 * @param color color of the toast type for text
	 * @param backgroundColor background color of toast
	 * @return new builder instance
	 */
	public static ToastTypeBuilder create(String name, IsColor color, IsColor backgroundColor) {
		return create(Key.create(name), color, backgroundColor);
	}

	/**
	 * Returns new builder instance, to build a custom toast type.
	 * 
	 * @param name name to use inside the native object as name of property
	 * @param color color of the toast type for text
	 * @param backgroundColor background color of toast
	 * @return new builder instance
	 */
	public static ToastTypeBuilder create(Key name, IsColor color, IsColor backgroundColor) {
		// check if key is consistent
		checkName(name);
		// check if colors are consistent
		IsColor.checkIfValid(color);
		IsColor.checkIfValid(backgroundColor);
		// stores arguments and
		// returns builder
		return new ToastTypeBuilder(name, color, backgroundColor);
	}

	/**
	 * Returns new toast type.
	 * 
	 * @return new toast type.
	 */
	public IsToastType build() {
		// checks if id as argument is a default one
		for (DefaultToastType defToastType : DefaultToastType.values()) {
			// checks if id is equals to default
			if (Key.equals(defToastType, getName())) {
				// if equals, returns the default id
				return defToastType;
			}
		}
		// gets toast type from map
		StandardToastType type = CUSTOM_TYPES.computeIfAbsent(getName().value(), mapKey -> new StandardToastType(getName(), color, getBackgroundColor()));
		// checks if it has been injected
		if (!type.isInjected()) {
			// creates CSS statement from template
			String content = Utilities.applyTemplate(CSS_TEMPLATE, type.value(), type.getColor().toRGBA(), type.getBackgroundColor().toRGBA());
			// injects CSS resource
			Injector.ensureCssInjected(new CssInjectableResource(type, false, content));
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
	static IsToastType get(String name) {
		// checks i argument is consistent
		if (name != null) {
			return CUSTOM_TYPES.get(name);
		}
		// if here, argument not consistent
		// then returns null
		return null;
	}

}
