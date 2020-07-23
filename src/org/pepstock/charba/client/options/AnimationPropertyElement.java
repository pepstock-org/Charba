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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultAnimationPropertyElement;
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;

/**
 * FIXME It animates charts out of the box. A number of options are provided to configure how the animation looks and how long it takes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class AnimationPropertyElement extends AbstractAnimationElement<IsAnimationProperty, IsDefaultAnimationPropertyElement> implements IsDefaultAnimationPropertyElement{

	/**
	 * Creates an animation options to configure a specific property.
	 * 
	 * @param property property instance used to get for animation options
	 */
	public AnimationPropertyElement(IsAnimationProperty property) {
		this(property, DefaultsBuilder.get().getOptions().getAnimation().getProperty(IsAnimationProperty.checkAndGetIfValid(property)));
	}

	/**
	 * Creates an animation options to configure a specific property.
	 * 
	 * @param property property instance used to get for animation options
	 * @param defaultValues default provider
	 */
	public AnimationPropertyElement(IsAnimationProperty property, IsDefaultAnimationPropertyElement defaultValues) {
		this(null, property, defaultValues, null);
		// stores the type
		setType(property.type());
	}

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent options parent node of the chart options.
	 * @param property property instance used to get for animation options
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	AnimationPropertyElement(AbstractNode parent, IsAnimationProperty property, IsDefaultAnimationPropertyElement defaultValues, NativeObject nativeObject) {
		super(parent, property, defaultValues, nativeObject);
		// checks if property is valid
		IsAnimationProperty.checkIfValid(property);
	}

}