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

import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.commons.NativeObject;

/**
 * Common interface for all elements which has got a {@link DatasetContext}
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface ChartContextElementFactory extends ChartElementFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.items.ChartElementFactory#createContext(org.pepstock.charba.client.commons.NativeObject)
	 */
	@Override
	default DatasetContext createContext(NativeObject nativeObject) {
		return new DatasetContext(nativeObject);
	}

}