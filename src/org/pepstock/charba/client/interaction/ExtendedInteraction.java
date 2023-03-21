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
package org.pepstock.charba.client.interaction;

import org.pepstock.charba.client.Chart;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.NativeObject;

import jsinterop.annotations.JsFunction;

/**
 * This class can be used when a custom interaction mode is already available and implemented in java script (for instance, controllers or plugins which added a specific
 * interaction mode).<br>
 * Java script FUNCTION callback called to invoke a custom interactioner.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsFunction
public interface ExtendedInteraction {

	/**
	 * Method of function to be called to invoke a custom interaction mode.
	 * 
	 * @param chart the chart we are returning items from
	 * @param event the event we are find things at
	 * @param options options to use
	 * @param useFinalPosition use final element position (animation target)
	 * @return items that are found
	 */
	ArrayObject call(Chart chart, NativeObject event, NativeObject options, boolean useFinalPosition);
}
