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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.dom.events.NativeBaseEvent;
import org.pepstock.charba.client.dom.events.NativeAbstractMouseEvent;
import org.pepstock.charba.client.events.AddHandlerEvent;
import org.pepstock.charba.client.events.RemoveHandlerEvent;
import org.pepstock.charba.client.events.SubtitleClickEvent;
import org.pepstock.charba.client.events.SubtitleEnterEvent;
import org.pepstock.charba.client.events.SubtitleLeaveEvent;

/**
 * It manages click, enter and leave events on subtitle instances.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class EventSubtitleHandler extends AbstractEventElementHandler {

	// amount of subtitle click event handlers
	private int onSubtitleClickHandlers = 0;
	// amount of subtitle enter event handlers
	private int onSubtitleEnterHandlers = 0;
	// amount of subtitle leave event handlers
	private int onSubtitleLeaveHandlers = 0;
	// hovered subtitle flag
	private boolean hoveredSubtitle = false;

	/**
	 * Builds the object storing the configuration options.
	 * 
	 * @param configuration chart configuration instance
	 */
	EventSubtitleHandler(ConfigurationOptions configuration) {
		super(configuration);
	}

	/**
	 * Returns <code>true</code> if there is any subtitle click handler, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if there is any subtitle click handler, otherwise <code>false</code>.
	 */
	boolean hasSubtitleClickHandlers() {
		return onSubtitleClickHandlers > 0;
	}

	/**
	 * Returns <code>true</code> if there is any subtitle enter handler, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if there is any subtitle enter handler, otherwise <code>false</code>.
	 */
	boolean hasSubtitleEnterHandlers() {
		return onSubtitleEnterHandlers > 0;
	}

	/**
	 * Returns <code>true</code> if there is any subtitle leave handler, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if there is any subtitle leave handler, otherwise <code>false</code>.
	 */
	boolean hasSubtitleLeaveHandlers() {
		return onSubtitleLeaveHandlers > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.events.AddHandlerEventHandler#onAdd(org.pepstock.charba.client.events.AddHandlerEvent)
	 */
	@Override
	public final void onAdd(AddHandlerEvent event) {
		// check if a subtitle click handler has been added
		if (event.isRecognize(SubtitleClickEvent.TYPE)) {
			// checks if is the first one in order to add the listeners to the canvas
			checkAndManageCanvasClickListeners(true, !hasSubtitleClickHandlers());
			// increments handlers of title click
			onSubtitleClickHandlers++;
		} else if (event.isRecognize(SubtitleEnterEvent.TYPE)) {
			// check if a subtitle enter handler has been added
			// checks if is the first one in order to add the listeners to the canvas
			checkAndManageCanvasHoverListeners(true, !hasSubtitleEnterHandlers() && !hasSubtitleLeaveHandlers());
			// increments handlers of subtitle enter
			onSubtitleEnterHandlers++;
		} else if (event.isRecognize(SubtitleLeaveEvent.TYPE)) {
			// check if a subtitle leave handler has been added
			// checks if is the first one in order to add the listeners to the canvas
			checkAndManageCanvasHoverListeners(true, !hasSubtitleEnterHandlers() && !hasSubtitleLeaveHandlers());
			// increments handlers of subtitle leave
			onSubtitleLeaveHandlers++;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.events.RemoveHandlerEventHandler#onRemove(org.pepstock.charba.client.events. RemoveHandlerEvent)
	 */
	@Override
	public final void onRemove(RemoveHandlerEvent event) {
		// check if a subtitle click handler has been removed
		if (event.isRecognize(SubtitleClickEvent.TYPE)) {
			// decrements handlers of subtitle click
			onSubtitleClickHandlers--;
			// checks if no handlers are defined
			checkAndManageCanvasClickListeners(false, !hasSubtitleClickHandlers());
		} else if (event.isRecognize(SubtitleEnterEvent.TYPE)) {
			// check if a subtitle enter handler has been removed
			// decrements handlers of title enter
			onSubtitleEnterHandlers--;
			// checks if no handlers are defined
			checkAndManageCanvasClickListeners(false, !hasSubtitleEnterHandlers() && !hasSubtitleLeaveHandlers());
		} else if (event.isRecognize(SubtitleLeaveEvent.TYPE)) {
			// check if a subtitle leave handler has been removed
			// decrements handlers of subtitle leave
			onSubtitleLeaveHandlers--;
			// checks if no handlers are defined
			checkAndManageCanvasClickListeners(false, !hasSubtitleEnterHandlers() && !hasSubtitleLeaveHandlers());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractEventElementHandler#handleClickEventOnElements(org.pepstock.charba.client.dom.events.NativeBaseEvent)
	 */
	@Override
	void handleClickEventOnElements(NativeBaseEvent event) {
		// checks if event is a mouse event
		if (event instanceof NativeAbstractMouseEvent) {
			// casts to mouse event
			NativeAbstractMouseEvent mouseEvent = (NativeAbstractMouseEvent) event;
			// checks if subtitle has been selected and there is any handler
			if (hasSubtitleClickHandlers() && getConfiguration().getChart().getNode().getSubtitle().isInside(mouseEvent)) {
				// fires the click event on the chart subtitle
				getConfiguration().getChart().fireEvent(new SubtitleClickEvent(mouseEvent, getConfiguration().getChart().getNode().getOptions().getSubtitle()));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractEventElementHandler#handleHoverEventOnElements(org.pepstock.charba.client.dom.events.NativeBaseEvent)
	 */
	@Override
	void handleHoverEventOnElements(NativeBaseEvent event) {
		// checks if event is a mouse event
		if (event instanceof NativeAbstractMouseEvent) {
			// casts to mouse event
			NativeAbstractMouseEvent mouseEvent = (NativeAbstractMouseEvent) event;
			// checks if subtitle has been selected and there is any handler
			if (getConfiguration().getChart().getNode().getSubtitle().isInside(mouseEvent)) {
				// checks if already hovered
				if (!hoveredSubtitle) {
					// checks if handler is set
					if (hasSubtitleEnterHandlers()) {
						// fires the enter event on the chart title
						getConfiguration().getChart().fireEvent(new SubtitleEnterEvent(mouseEvent, getConfiguration().getChart().getNode().getOptions().getSubtitle()));
					}
					// resets flag
					hoveredSubtitle = true;
				}
			} else {
				// if here checks and performs leave event
				handleLeaveEventOnElements(event);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractEventElementHandler#handleLeaveEventOnElements(org.pepstock.charba.client.dom.events.NativeBaseEvent)
	 */
	@Override
	void handleLeaveEventOnElements(NativeBaseEvent event) {
		// checks if event is a mouse event
		if (event instanceof NativeAbstractMouseEvent) {
			// casts to mouse event
			NativeAbstractMouseEvent mouseEvent = (NativeAbstractMouseEvent) event;
			// checks if subtitle has been left and there is any handler
			if (hoveredSubtitle && hasSubtitleLeaveHandlers()) {
				// fires the leave event on the chart subtitle
				getConfiguration().getChart().fireEvent(new SubtitleLeaveEvent(mouseEvent, getConfiguration().getChart().getNode().getOptions().getSubtitle()));
				// resets status
				hoveredSubtitle = false;
			}
		}
	}

}