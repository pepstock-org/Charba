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
package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultPointLabels;

/**
 * It is used to configure the point labels that are shown on the perimeter of the scale.<br>
 * Note that these options only apply if display is true.
 * 
 * @author Andrea "Stock" Stocchero
 * @version 2.0
 *
 */
public final class PointLabels extends FontItem<Scale, IsDefaultPointLabels> implements IsDefaultPointLabels{

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		display
	}
	
	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script
	 * properties.
	 * 
	 * @param scale scale/axis of this object.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	PointLabels(Scale scale, Key childKey, IsDefaultPointLabels defaultValues, NativeObject nativeObject) {
		super(scale, childKey, defaultValues, nativeObject);
	}

	/**
	 * If true, labels are shown.
	 * 
	 * @param display if true, labels are shown.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.display, display);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, labels are shown.
	 * 
	 * @return if true, labels are shown.
	 */
	public boolean isDisplay() {
		return getValue(Property.display, getDefaultValues().isDisplay());
	}	
}