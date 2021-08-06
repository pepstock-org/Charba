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

import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.events.AddHandlerEvent;
import org.pepstock.charba.client.events.RemoveHandlerEvent;
import org.pepstock.charba.client.events.TitleClickEvent;
import org.pepstock.charba.client.events.TitleEnterEvent;
import org.pepstock.charba.client.events.TitleLeaveEvent;

/**
 * It manages click, enter and leave events on title instances.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class EventTitleHandler extends AbstractEventElementHandler {

	// amount of title click event handlers
	private int onTitleClickHandlers = 0;
	// amount of title enter event handlers
	private int onTitleEnterHandlers = 0;
	// amount of title leave event handlers
	private int onTitleLeaveHandlers = 0;
	// hovered title flag
	private boolean hoveredTitle = false;

	/**
	 * Builds the object storing the configuration options.
	 * 
	 * @param configuration chart configuration instance
	 */
	EventTitleHandler(ConfigurationOptions configuration) {
		super(configuration);
	}

	/**
	 * Returns <code>true</code> if there is any title click handler, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if there is any title click handler, otherwise <code>false</code>.
	 */
	boolean hasTitleClickHandlers() {
		return onTitleClickHandlers > 0;
	}

	/**
	 * Returns <code>true</code> if there is any title enter handler, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if there is any title enter handler, otherwise <code>false</code>.
	 */
	boolean hasTitleEnterHandlers() {
		return onTitleEnterHandlers > 0;
	}

	/**
	 * Returns <code>true</code> if there is any title leave handler, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if there is any title leave handler, otherwise <code>false</code>.
	 */
	boolean hasTitleLeaveHandlers() {
		return onTitleLeaveHandlers > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.events.AddHandlerEventHandler#onAdd(org.pepstock.charba.client.events.AddHandlerEvent)
	 */
	@Override
	public final void onAdd(AddHandlerEvent event) {
		// check if a title click handler has been added
		if (event.isRecognize(TitleClickEvent.TYPE)) {
			// checks if is the first one in order to add the listeners to the canvas
			checkAndManageCanvasClickListeners(true, !hasTitleClickHandlers());
			// increments handlers of title click
			onTitleClickHandlers++;
		} else if (event.isRecognize(TitleEnterEvent.TYPE)) {
			// check if a title enter handler has been added
			// checks if is the first one in order to add the listeners to the canvas
			checkAndManageCanvasHoverListeners(true, !hasTitleEnterHandlers() && !hasTitleLeaveHandlers());
			// increments handlers of title enter
			onTitleEnterHandlers++;
		} else if (event.isRecognize(TitleLeaveEvent.TYPE)) {
			// check if a title leave handler has been added
			// checks if is the first one in order to add the listeners to the canvas
			checkAndManageCanvasHoverListeners(true, !hasTitleEnterHandlers() && !hasTitleLeaveHandlers());
			// increments handlers of title leave
			onTitleLeaveHandlers++;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.events.RemoveHandlerEventHandler#onRemove(org.pepstock.charba.client.events. RemoveHandlerEvent)
	 */
	@Override
	public final void onRemove(RemoveHandlerEvent event) {
		// check if a title click handler has been removed
		if (event.isRecognize(TitleClickEvent.TYPE)) {
			// decrements handlers of title click
			onTitleClickHandlers--;
			// checks if no handlers are defined
			checkAndManageCanvasClickListeners(false, !hasTitleClickHandlers());
		} else if (event.isRecognize(TitleEnterEvent.TYPE)) {
			// check if a title enter handler has been removed
			// decrements handlers of title enter
			onTitleEnterHandlers--;
			// checks if no handlers are defined
			checkAndManageCanvasClickListeners(false, !hasTitleEnterHandlers() && !hasTitleLeaveHandlers());
		} else if (event.isRecognize(TitleLeaveEvent.TYPE)) {
			// check if a title leave handler has been removed
			// decrements handlers of title leave
			onTitleLeaveHandlers--;
			// checks if no handlers are defined
			checkAndManageCanvasClickListeners(false, !hasTitleEnterHandlers() && !hasTitleLeaveHandlers());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractEventElementHandler#handleClickEventOnElements(org.pepstock.charba.client.dom.BaseNativeEvent)
	 */
	@Override
	void handleClickEventOnElements(BaseNativeEvent event) {
		// checks if title has been selected and there is any handler
		if (hasTitleClickHandlers() && getConfiguration().getChart().getNode().getTitle().isInside(event)) {
			// fires the click event on the chart title
			getConfiguration().getChart().fireEvent(new TitleClickEvent(event, getConfiguration().getChart().getNode().getOptions().getTitle()));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractEventElementHandler#handleHoverEventOnElements(org.pepstock.charba.client.dom.BaseNativeEvent)
	 */
	@Override
	void handleHoverEventOnElements(BaseNativeEvent event) {
		// checks if title has been selected and there is any handler
		if (getConfiguration().getChart().getNode().getTitle().isInside(event)) {
			// checks if already hovered
			if (!hoveredTitle) {
				// checks if handler is set
				if (hasTitleEnterHandlers()) {
					// fires the enter event on the chart title
					getConfiguration().getChart().fireEvent(new TitleEnterEvent(event, getConfiguration().getChart().getNode().getOptions().getTitle()));
				}
				// resets flag
				hoveredTitle = true;
			}
		} else {
			// if here checks and performs leave event
			handleLeaveEventOnElements(event);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractEventElementHandler#handleLeaveEventOnElements(org.pepstock.charba.client.dom.BaseNativeEvent)
	 */
	@Override
	void handleLeaveEventOnElements(BaseNativeEvent event) {
		// checks if title has been left and there is any handler
		if (hoveredTitle && hasTitleLeaveHandlers()) {
			// fires the leave event on the chart title
			getConfiguration().getChart().fireEvent(new TitleLeaveEvent(event, getConfiguration().getChart().getNode().getOptions().getTitle()));
			// resets status
			hoveredTitle = false;
		}
	}

}