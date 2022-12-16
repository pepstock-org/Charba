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
package org.pepstock.charba.client.geo.callbacks;

import org.pepstock.charba.client.geo.Feature;

/**
 * Callback to implement to find a {@link Feature} from a list that pass the test implemented by this callback.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface FeatureFindCallback {

	/**
	 * Return a value that coerces to <code>true</code> to find the {@link Feature}, or to <code>false</code> otherwise.
	 * 
	 * @param feature current element being processed in the list.
	 * @param index the index of the current element being processed in the list
	 * @return a value that coerces to <code>true</code> to find the {@link Feature}, or to <code>false</code> otherwise
	 */
	boolean find(Feature feature, int index);

}