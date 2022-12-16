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

import org.pepstock.charba.client.configuration.Tooltips;
import org.pepstock.charba.client.data.Labels;
import org.pepstock.charba.client.geo.Feature;

/**
 * Callback to implement to get the label for each {@link Feature} to use in the {@link Labels} and {@link Tooltips}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface FeatureLabelCallback {

	/**
	 * Return a value that represents the name for the feature to use on {@link Labels} and {@link Tooltips}.
	 * 
	 * @param feature current element being processed in the list.
	 * @return a value that represents the name for the feature to use on {@link Labels} and {@link Tooltips}
	 */
	String label(Feature feature);

}