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
package org.pepstock.charba.client.callbacks;

import org.pepstock.charba.client.commons.IsEnvelop;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.configuration.ConfigurationEnvelop;
import org.pepstock.charba.client.data.DataEnvelop;
import org.pepstock.charba.client.datalabels.DataLabelsEnvelop;
import org.pepstock.charba.client.labels.LabelsEnvelop;

/**
 * The option context is used to give contextual information when resolving options.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class DatasetContext extends AbstractDatasetContext {

	/**
	 * Creates the object with public object instance to be wrapped, called by <code>configuration</code> package.
	 * 
	 * @param envelop envelop of public object instance to be wrapped.
	 */
	public DatasetContext(ConfigurationEnvelop<NativeObject> envelop) {
		this(IsEnvelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates the object with public object instance to be wrapped, called by <code>data</code> package.
	 * 
	 * @param envelop envelop of public object instance to be wrapped.
	 */
	public DatasetContext(DataEnvelop<NativeObject> envelop) {
		this(IsEnvelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates the object with public object instance to be wrapped, called by <code>datalabels</code> package.
	 * 
	 * @param envelop envelop of public object instance to be wrapped.
	 */
	public DatasetContext(DataLabelsEnvelop<NativeObject> envelop) {
		this(IsEnvelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates the object with public object instance to be wrapped, called by <code>labels</code> package.
	 * 
	 * @param envelop envelop of public object instance to be wrapped.
	 */
	public DatasetContext(LabelsEnvelop<NativeObject> envelop) {
		this(IsEnvelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates the object with public object instance to be wrapped.
	 * 
	 * @param nativeObject public object instance to be wrapped.
	 */
	private DatasetContext(NativeObject nativeObject) {
		super(nativeObject);
	}

}