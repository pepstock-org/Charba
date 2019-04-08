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
package org.pepstock.charba.client;

import org.pepstock.charba.client.events.AddHandlerEvent;
import org.pepstock.charba.client.events.ChartEventHandler;
import org.pepstock.charba.client.events.RemoveHandlerEvent;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Manager responsible for adding handlers to event sources and firing those handlers on passed in events.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class ChartHandlerManager extends HandlerManager {

	// chart instance, needed to add every new handler registration
	private final IsChart chart;

	/**
	 * Creates a handler manager with the given chart as source, specifying to maintain the order in which handlers are fired.
	 * 
	 * @param chart chart instance
	 */
	ChartHandlerManager(IsChart chart) {
		super(chart, false);
		// stores the chart instance
		this.chart = chart;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.HandlerManager#addHandler(com.google.gwt.event.shared.GwtEvent.Type,
	 * com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	public <H extends EventHandler> HandlerRegistration addHandler(Type<H> type, H handler) {
		// adds handler
		HandlerRegistration registration = super.addHandler(type, handler);
		// if the handler is a chart event handler one
		if (handler instanceof ChartEventHandler) {
			// sends the event
			fireEvent(new AddHandlerEvent(type));
		}
		// checks if is chart is a abstract chart instance
		if (chart instanceof AbstractChart<?>) {
			// cast to abstract chart to get the method
			AbstractChart<?> chartInstance = (AbstractChart<?>) chart;
			// stores the registration into chart
			// for cleaning up when chart will be destroy
			chartInstance.addHandlerRegistration(registration);
		}
		// retuns registration
		return registration;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.HandlerManager#removeHandler(com.google.gwt.event.shared.GwtEvent.Type,
	 * com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	public <H extends EventHandler> void removeHandler(Type<H> type, H handler) {
		// removes handler
		super.removeHandler(type, handler);
		// if the handler is a chart event handler one
		if (handler instanceof ChartEventHandler) {
			// sends the event
			fireEvent(new RemoveHandlerEvent(type));
		}
	}
}
