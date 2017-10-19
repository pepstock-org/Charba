package org.pepstock.charba.client;

import java.util.logging.Logger;

import org.pepstock.charba.client.events.AddHandlerEvent;
import org.pepstock.charba.client.events.ChartEventHandler;
import org.pepstock.charba.client.events.RemoveHandlerEvent;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;

final class ChartHandlerManager extends HandlerManager {

	final Logger LOG = Logger.getLogger("TEST");
	
	ChartHandlerManager(AbstractChart<?, ?> chart) {
		super(chart, false);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.HandlerManager#addHandler(com.google.gwt.event.shared.GwtEvent.Type, com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	public <H extends EventHandler> HandlerRegistration addHandler(Type<H> type, H handler) {
		HandlerRegistration registration =  super.addHandler(type, handler);
		if (handler instanceof ChartEventHandler){
			fireEvent(new AddHandlerEvent(type));
		}
		return registration;
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.HandlerManager#removeHandler(com.google.gwt.event.shared.GwtEvent.Type, com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	public <H extends EventHandler> void removeHandler(Type<H> type, H handler) {
		super.removeHandler(type, handler);
		if (handler instanceof ChartEventHandler){
			fireEvent(new RemoveHandlerEvent(type));
		}
	}
}
