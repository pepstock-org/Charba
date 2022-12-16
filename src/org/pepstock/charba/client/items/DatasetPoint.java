/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.callbacks.CallbacksEnvelop;
import org.pepstock.charba.client.commons.AbstractReadOnlyPoint;
import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.NativeObject;

/**
 * It wraps the parsed data values for the given item point.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DatasetPoint extends AbstractReadOnlyPoint {

	/**
	 * Creates the object with a native object passed as argument.
	 * 
	 * @param envelop envelop of native java script object which contains all properties.
	 */
	public DatasetPoint(CallbacksEnvelop<NativeObject> envelop) {
		super(Envelop.checkAndGetIfValid(envelop).getContent());
	}

	/**
	 * Creates the object with a native object passed as argument.
	 * 
	 * @param nativeObject native object which maps a data point
	 */
	DatasetPoint(NativeObject nativeObject) {
		super(nativeObject);
	}

}