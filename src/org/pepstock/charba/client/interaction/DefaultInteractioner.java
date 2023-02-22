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
import org.pepstock.charba.client.enums.DefaultInteractionMode;

/**
 * Maps a default interaction mode implementation, using the java script implementation provided by CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class DefaultInteractioner extends AbstractNativeInteractioner<ExtendedInteraction> {

	/**
	 * Creates the interactioner passing the DEFAULT interaction mode instance as argument.
	 * 
	 * @param mode DEFAULT interaction mode instance
	 * @param callback java script implementation instance
	 */
	DefaultInteractioner(DefaultInteractionMode mode, ExtendedInteraction callback) {
		super(mode, callback);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.interaction.AbstractNativeInteractioner#invoke(org.pepstock.charba.client.Chart, org.pepstock.charba.client.commons.NativeObject,
	 * org.pepstock.charba.client.commons.NativeObject, boolean)
	 */
	@Override
	protected final ArrayObject invoke(Chart chart, NativeObject event, NativeObject options, boolean useFinalPosition) {
		return getCallback().call(chart, event, options, useFinalPosition);
	}
}
