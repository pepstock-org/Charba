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
package org.pepstock.charba.client.utils.toast;

import java.util.List;

import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;

/**
 * Configures the toast label.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Label extends AbstractContentElement<List<String>> {

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script properties.
	 * 
	 * @param options plugins options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Label(AbstractReadOnlyToastOptions options, Key childKey, IsDefaultContentElement defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
	}

	/**
	 * Sets the title content to display.<br>
	 * If specified as an array, text is rendered on multiple lines.
	 *
	 * @param content the title text to display.<br>
	 *            If specified as an array, text is rendered on multiple lines.
	 */
	public void setContent(String... content) {
		setArrayValueAndAddToParent(Property.CONTENT, ArrayString.fromOrNull(content));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.utils.toast.AbstractContentElement#setContent(java.lang.Object)
	 */
	@Override
	public void setContent(List<String> content) {
		setArrayValueAndAddToParent(Property.CONTENT, ArrayString.fromOrNull(content));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.utils.toast.AbstractContentElement#getContent()
	 */
	@Override
	public List<String> getContent() {
		// reads as array
		// and returns it
		ArrayString array = getArrayValue(Property.CONTENT);
		return ArrayListHelper.list(array);
	}

}