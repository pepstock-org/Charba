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
package org.pepstock.charba.client;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.Merger;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultScaledOptions;
import org.pepstock.charba.client.defaults.globals.DefaultsBuilder;

/**
 * Default overrides options (maps the java script object chart.overrides).
 * 
 * @author Andrea "Stock" Stocchero
 */
final class Overrides extends NativeObjectContainer {

	/**
	 * Creates the object with the native object which maps the java script object chart.overrides.
	 * 
	 * @param nativeObject native object which maps the java script object chart.defaults.global
	 */
	Overrides(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the chart options defaults by chart type.
	 * 
	 * @param type the type of chart
	 * @return the chart options defaults by chart type
	 */
	private NativeObject getChartOptions(Type type) {
		// checks if type is present
		if (isType(type, ObjectType.OBJECT)) {
			// checks if chart type is consistent
			return getValue(Key.checkAndGetIfValid(type));
		}
		// if here, the type doesn't exist
		return null;
	}

	/**
	 * Returns an options instance, to use as default options, based of type of chart.
	 * 
	 * @param type chart type.
	 * @return default options.
	 */
	ChartOptions chart(Type type) {
		// gets global options
		GlobalOptions global = Defaults.get().getGlobal();
		// checks if has got scales
		if (!ScaleType.NONE.equals(type.scaleType())) {
			// creates an envelop for options
			ChartEnvelop<ChartOptions> envelopOptions = new ChartEnvelop<>();
			// stores a temporary chart options
			envelopOptions.setContent(createChartOptions(type, DefaultsBuilder.get().getScaledOptions()));
			// creates an envelop for native object of options
			ChartEnvelop<NativeObject> envelop = new ChartEnvelop<>();
			// load the envelop
			Merger.get().load(type, envelopOptions, envelop);
			// creates defaults
			ChartOptions defaultOptions = new ChartOptions(type, envelop.getContent(), global.asDefault());
			// returns a default option with all configuration
			return createChartOptions(type, defaultOptions);
		} else {
			// if here, is not a scaled chart
			return createChartOptions(type, global.asDefault());
		}
	}

	/**
	 * Returns an options instance, to use as default options, based of type of chart.
	 * 
	 * @param type chart type
	 * @param defaultOptions defaults scaled options instance
	 * @return default options
	 */
	private ChartOptions createChartOptions(Type type, IsDefaultScaledOptions defaultOptions) {
		// creates a chart options using global a default scaled as default
		return new ChartOptions(type, getChartOptions(type), defaultOptions);
	}

}