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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.events.AddHandlerEvent;
import org.pepstock.charba.client.events.AddHandlerEventHandler;
import org.pepstock.charba.client.events.RemoveHandlerEvent;
import org.pepstock.charba.client.events.RemoveHandlerEventHandler;

/**
 * Is a JavaScript object container, which contains the chart instance and options element to store chart configuration, which
 * will manage chart events to events handlers.
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @param <T> options element to store chart configuration
 */
public abstract class EventProvider<T extends NativeObjectContainer> extends ConfigurationContainer<T> implements AddHandlerEventHandler, RemoveHandlerEventHandler {

	/**
	 * Creates the chart configuration object with the chart instance and options element to store chart configuration.
	 * 
	 * @param chart chart instance
	 * @param configuration options element.
	 */
	EventProvider(IsChart chart, T configuration) {
		super(chart, configuration);
		chart.addHandler(this, AddHandlerEvent.TYPE);
		chart.addHandler(this, RemoveHandlerEvent.TYPE);
	}

}