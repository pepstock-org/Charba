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
package org.pepstock.charba.client.datalabels;

import org.pepstock.charba.client.callbacks.AbstractDatasetScriptableContext;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.items.UndefinedValues;

/**
 * The option context is used to give contextual information when resolving options for {@link DataLabelsPlugin}.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DataLabelsScriptableContext extends AbstractDatasetScriptableContext {

	/**
	 * Creates the object with public object instance to be wrapped.
	 * 
	 * @param nativeObject public object instance to be wrapped.
	 */
	DataLabelsScriptableContext(NativeObject nativeObject) {
		super(nativeObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.callbacks.AbstractScriptableContext#isConsistent()
	 */
	@Override
	protected boolean isConsistent() {
		// checks if the data index and data set index are consistent
		return getDatasetIndex() != UndefinedValues.INTEGER && getDataIndex() != UndefinedValues.INTEGER;
	}

}
