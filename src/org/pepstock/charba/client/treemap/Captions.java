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
package org.pepstock.charba.client.treemap;

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultOptions;

/**
 * The captions can control if and how a caption, to represent the group of the chart, can be shown in the rectangle,
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Captions extends AbstractLabels {

	/**
	 * Default captions display, <b>{@value DEFAULT_DISPLAY}</b>.
	 */
	public static final boolean DEFAULT_DISPLAY = true;

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param parent parent node to use to add this element where changed
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default options of the controller
	 * @param nativeObject native object to map java script properties
	 */
	Captions(AbstractNode parent, Key childKey, IsDefaultOptions defaultValues, NativeObject nativeObject) {
		super(parent, childKey, defaultValues, nativeObject, DEFAULT_DISPLAY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.treemap.AbstractLabels#isMultilineLabel()
	 */
	@Override
	boolean isMultilineLabel() {
		return false;
	}

}