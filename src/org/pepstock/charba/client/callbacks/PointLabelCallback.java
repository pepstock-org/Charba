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
package org.pepstock.charba.client.callbacks;

import java.util.List;

import org.pepstock.charba.client.configuration.Axis;

/**
 * Callback function to transform data labels to point labels.<br>
 * It can return a {@link List} of strings (for multiple lines).
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface PointLabelCallback {

	/**
	 * Callback function to transform data labels to point labels. The default implementation simply returns the current string.
	 * 
	 * @param axis axis instance where this callback as been defined
	 * @param item label of current label
	 * @param index index of the label
	 * @return new label to apply to point label.<br>
	 *         It can return a {@link List} of strings (for multiple lines) or a string (for single line).
	 */
	Object onCallback(Axis axis, String item, int index);

}