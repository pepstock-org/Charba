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

import org.pepstock.charba.client.ChartEnvelop;
import org.pepstock.charba.client.commons.Envelop;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.options.ScaledOptions;

/**
 * Wrapper of options node of CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class OptionsNode extends ScaledOptions {

	/**
	 * Creates the item using an envelop with the native java script object which contains all properties.
	 * 
	 * @param chartId scope of the options, in this case the chart id.
	 * @param defaultValues default provider instance.
	 * @param envelop envelop with the native java script object which contains all properties.
	 */
	public OptionsNode(String chartId, IsDefaultScaledOptions defaultValues, ChartEnvelop<NativeObject> envelop) {
		super(chartId, defaultValues, Envelop.checkAndGetIfValid(envelop).getContent(), true);
	}
}