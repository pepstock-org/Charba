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
package org.pepstock.charba.client.jsinterop.configuration;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.events.AddHandlerEvent;
import org.pepstock.charba.client.events.AddHandlerEventHandler;
import org.pepstock.charba.client.events.RemoveHandlerEvent;
import org.pepstock.charba.client.events.RemoveHandlerEventHandler;
import org.pepstock.charba.client.jsinterop.options.Options;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;

/**
 * Is a JavaScript object container, which contains THE chart instance, which will manage chart events to events handlers.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.commons.ChartContainer
 */
public abstract class EventProvider extends ChartContainer implements AddHandlerEventHandler, RemoveHandlerEventHandler{

	/**
	 * Creates the chart configuration object with the chart instance 
	 * @param chart chart instance
	 * @see org.pepstock.charba.client.AbstractChart
	 */
	public EventProvider(AbstractChart<?, ?> chart, Options options) {
		super(chart, options);
		//FIXME
//		chart.addHandler(this, AddHandlerEvent.TYPE);
//		chart.addHandler(this, RemoveHandlerEvent.TYPE);
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.events.RemoveHandlerEventHandler#onRemove(org.pepstock.charba.client.events.RemoveHandlerEvent)
	 */
	@Override
	public void onRemove(RemoveHandlerEvent event) {
		removeHandler(event.getType());
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.events.AddHandlerEventHandler#onAdd(org.pepstock.charba.client.events.AddHandlerEvent)
	 */
	@Override
	public void onAdd(AddHandlerEvent event) {
		addHandler(event.getType());
	}

	/**
	 * Notifies the event provider when a new handler has been added.
	 * @param type type of event
	 * @param <H> type of event handler
	 */
	protected abstract <H extends EventHandler> void addHandler(Type<H> type);
	
	/**
	 * Notifies the event provider when a new handler has been deregistered
	 * @param type type of event handler
	 * @param <H> type of event handler
	 */
	protected abstract <H extends EventHandler> void removeHandler(Type<H> type);
}