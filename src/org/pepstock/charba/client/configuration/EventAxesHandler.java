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

import org.pepstock.charba.client.ScaleType;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.events.AddHandlerEvent;
import org.pepstock.charba.client.events.AxisClickEvent;
import org.pepstock.charba.client.events.AxisEnterEvent;
import org.pepstock.charba.client.events.AxisHoverEvent;
import org.pepstock.charba.client.events.AxisLeaveEvent;
import org.pepstock.charba.client.events.RemoveHandlerEvent;
import org.pepstock.charba.client.items.ScaleItem;
import org.pepstock.charba.client.items.ScalesNode;
import org.pepstock.charba.client.items.Undefined;

/**
 * It manages click, hover, enter and leave events on axes instances.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class EventAxesHandler extends AbstractEventElementHandler {

	// amount of axis click event handlers
	private int onAxisClickHandlers = 0;
	// amount of axis hover event handlers
	private int onAxisHoverHandlers = 0;
	// amount of axis enter event handlers
	private int onAxisEnterHandlers = 0;
	// amount of axis leave event handlers
	private int onAxisLeaveHandlers = 0;
	// hovered axis instance item
	private ScaleItem hoveredAxis = null;

	/**
	 * Builds the object storing the configuration options.
	 * 
	 * @param configuration chart configuration instance
	 */
	EventAxesHandler(ConfigurationOptions configuration) {
		super(configuration);
	}

	/**
	 * Returns <code>true</code> if there is any axis click handler, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if there is any axis click handler, otherwise <code>false</code>.
	 */
	boolean hasAxisClickHandlers() {
		return onAxisClickHandlers > 0;
	}

	/**
	 * Returns <code>true</code> if there is any axis hover handler, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if there is any axis hover handler, otherwise <code>false</code>.
	 */
	boolean hasAxisHoverHandlers() {
		return onAxisHoverHandlers > 0;
	}

	/**
	 * Returns <code>true</code> if there is any axis enter handler, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if there is any axis enter handler, otherwise <code>false</code>.
	 */
	boolean hasAxisEnterHandlers() {
		return onAxisEnterHandlers > 0;
	}

	/**
	 * Returns <code>true</code> if there is any axis leave handler, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if there is any axis leave handler, otherwise <code>false</code>.
	 */
	boolean hasAxisLeaveHandlers() {
		return onAxisLeaveHandlers > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.events.AddHandlerEventHandler#onAdd(org.pepstock.charba.client.events.AddHandlerEvent)
	 */
	@Override
	public final void onAdd(AddHandlerEvent event) {
		// check if a axis click handler has been added
		if (event.isRecognize(AxisClickEvent.TYPE)) {
			// checks if is the first one in order to add the listeners to the canvas
			checkAndManageCanvasClickListeners(true, !hasAxisClickHandlers());
			// increments handlers of axis click
			onAxisClickHandlers++;
		} else if (event.isRecognize(AxisHoverEvent.TYPE)) {
			// check if a axis hover handler has been added
			// checks if is the first one in order to add the listeners to the canvas
			checkAndManageCanvasHoverListeners(true, !hasAxisHoverHandlers() && !hasAxisLeaveHandlers() && !hasAxisEnterHandlers());
			// increments handlers of axis hover
			onAxisHoverHandlers++;
		} else if (event.isRecognize(AxisEnterEvent.TYPE)) {
			// check if a axis enter handler has been added
			// checks if is the first one in order to add the listeners to the canvas
			checkAndManageCanvasHoverListeners(true, !hasAxisHoverHandlers() && !hasAxisLeaveHandlers() && !hasAxisEnterHandlers());
			// increments handlers of axis enter
			onAxisEnterHandlers++;
		} else if (event.isRecognize(AxisLeaveEvent.TYPE)) {
			// check if a axis leave handler has been added
			// checks if is the first one in order to add the listeners to the canvas
			checkAndManageCanvasHoverListeners(true, !hasAxisHoverHandlers() && !hasAxisLeaveHandlers() && !hasAxisEnterHandlers());
			// increments handlers of axis leave
			onAxisLeaveHandlers++;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.events.RemoveHandlerEventHandler#onRemove(org.pepstock.charba.client.events. RemoveHandlerEvent)
	 */
	@Override
	public final void onRemove(RemoveHandlerEvent event) {
		// check if a axis click handler has been removed
		if (event.isRecognize(AxisClickEvent.TYPE)) {
			// decrements handlers of axis click
			onAxisClickHandlers--;
			// checks if no handlers are defined
			checkAndManageCanvasClickListeners(false, !hasAxisClickHandlers());
		} else if (event.isRecognize(AxisHoverEvent.TYPE)) {
			// check if a axis hover handler has been removed
			// decrements handlers of axis hover
			onAxisHoverHandlers--;
			// checks if no handlers are defined
			checkAndManageCanvasHoverListeners(false, !hasAxisHoverHandlers() && !hasAxisLeaveHandlers() && !hasAxisEnterHandlers());
		} else if (event.isRecognize(AxisEnterEvent.TYPE)) {
			// check if a axis enter handler has been removed
			// decrements handlers of axis enter
			onAxisEnterHandlers--;
			// checks if no handlers are defined
			checkAndManageCanvasHoverListeners(false, !hasAxisHoverHandlers() && !hasAxisLeaveHandlers() && !hasAxisEnterHandlers());
		} else if (event.isRecognize(AxisLeaveEvent.TYPE)) {
			// check if a axis hover handler has been removed
			// decrements handlers of axis leave
			onAxisLeaveHandlers--;
			// checks if no handlers are defined
			checkAndManageCanvasHoverListeners(false, !hasAxisHoverHandlers() && !hasAxisLeaveHandlers() && !hasAxisEnterHandlers());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractEventElementHandler#handleClickEventOnElements(org.pepstock.charba.client.dom.BaseNativeEvent)
	 */
	@Override
	void handleClickEventOnElements(BaseNativeEvent event) {
		// checks if there is any handler, the event is not in chart area because if there is managed as chart click and the chart has got scales
		if (hasAxisClickHandlers()) {
			// gets scale item
			ScaleItem scaleItem = searchScaleItem(event);
			// checks if scale item is consistent
			if (scaleItem != null) {
				// gets axis instance
				Axis axis = searchAxis(scaleItem);
				// fires the click event on the chart scale
				getConfiguration().getChart().fireEvent(new AxisClickEvent(event, scaleItem, axis, scaleItem.getValueAtEvent(event)));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractEventElementHandler#handleHoverEventOnElements(org.pepstock.charba.client.dom.BaseNativeEvent)
	 */
	@Override
	void handleHoverEventOnElements(BaseNativeEvent event) {
		// checks if there is any handler, the event is not in chart area because if there is managed as chart click and the chart has got scales
		if (hasAxisHoverHandlers() || hasAxisLeaveHandlers() || hasAxisEnterHandlers()) {
			// gets scale item
			ScaleItem scaleItem = searchScaleItem(event);
			// checks if scale item is consistent
			if (scaleItem != null) {
				// gets axis instance
				Axis axis = searchAxis(scaleItem);
				// checks if axis was already hovered
				if (hoveredAxis != null) {
					// checks if is the same scale item
					if (hoveredAxis.getId().equals(scaleItem.getId()) && hasAxisHoverHandlers()) {
						// fires the hover event on the chart scale
						getConfiguration().getChart().fireEvent(new AxisHoverEvent(event, scaleItem, axis));
					} else if (!hoveredAxis.getId().equals(scaleItem.getId())) {
						// leaves the stored one
						handleLeaveEventOnElements(event);
						// checks if events must be fired
						if (hasAxisEnterHandlers()) {
							// fires the enter event on the chart scale
							getConfiguration().getChart().fireEvent(new AxisEnterEvent(event, scaleItem, axis));
						}
						// adds as hovered
						hoveredAxis = scaleItem;
					}
				} else {
					// checks if events must be fired
					if (hasAxisEnterHandlers()) {
						// fires the enter event on the chart scale
						getConfiguration().getChart().fireEvent(new AxisEnterEvent(event, scaleItem, axis));
					}
					// adds as hovered
					hoveredAxis = scaleItem;
				}
			} else {
				// checks and performs leave event
				handleLeaveEventOnElements(event);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.configuration.AbstractEventElementHandler#handleLeaveEventOnElements(org.pepstock.charba.client.dom.BaseNativeEvent)
	 */
	@Override
	void handleLeaveEventOnElements(BaseNativeEvent event) {
		// checks if there is any handler, the event is not in chart area because if there is managed as chart click and the chart has got scales
		if (hoveredAxis != null && hasAxisLeaveHandlers()) {
			// if here, the user is not hovering any scale
			// there is was hovering a scale before
			// gets axis instance
			Axis axis = searchAxis(hoveredAxis);
			// fires the click event on the chart scale
			getConfiguration().getChart().fireEvent(new AxisLeaveEvent(event, hoveredAxis, axis));
		}
		// adds as hovered
		hoveredAxis = null;
	}

	/**
	 * Searches the {@link ScaleItem} instance related to the event.<br>
	 * If the {@link BaseNativeEvent} instance is not related to any {@link ScaleItem}, returns <code>null</code>.
	 * 
	 * @param event event generated on chart
	 * @return the {@link ScaleItem} instance related to the event or <code>null</code>
	 */
	private ScaleItem searchScaleItem(BaseNativeEvent event) {
		// checks if there is any handler, the event is not in chart area because if there is managed as chart click and the chart has got scales
		if (!getConfiguration().getChart().getNode().getChartArea().isInside(event) && !ScaleType.NONE.equals(getConfiguration().getChart().getType().scaleType())) {
			// gets the scales
			ScalesNode scales = getConfiguration().getChart().getNode().getScales();
			// checks if event is inside a scale box
			if (scales.isInside(event)) {
				// gets scale item
				return scales.getScaleIsInside(event);
			}
		}
		// if here, the event is not related to any scale
		// then returns null
		return null;
	}

	/**
	 * Searches the {@link Axis} instance related to the scale item.<br>
	 * If the {@link ScaleItem} instance is not related to any configured {@link Axis}, returns <code>null</code>.
	 * 
	 * @param scaleItem scale item instance
	 * @return the {@link Axis} instance related to the scale item or <code>null</code>
	 */
	private Axis searchAxis(ScaleItem scaleItem) {
		// creates axis reference
		Axis axis = null;
		// checks if event is inside a scale box
		if (scaleItem != null) {
			// gets id of scale
			final int charbaIdOfScale = scaleItem.getCharbaId();
			// checks if undefined
			// means no axis configured in the chart
			if (Undefined.isNot(charbaIdOfScale)) {
				// gets the axis by id
				axis = getConfiguration().getAxisById(charbaIdOfScale);
			}
		}
		// returns axis instance
		// can be null
		return axis;
	}

}