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
package org.pepstock.charba.client.impl.plugins;

import java.util.List;

import org.pepstock.charba.client.ChartNode;
import org.pepstock.charba.client.Helpers;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.Checker;
import org.pepstock.charba.client.commons.Constants;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.dom.BaseElement;
import org.pepstock.charba.client.dom.BaseEventTarget.EventListenerCallback;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Context2dItem;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.dom.elements.TextMetricsItem;
import org.pepstock.charba.client.dom.enums.CursorType;
import org.pepstock.charba.client.dom.enums.MouseEventType;
import org.pepstock.charba.client.dom.enums.TextBaseline;
import org.pepstock.charba.client.dom.events.MouseEventInit;
import org.pepstock.charba.client.dom.events.NativeAbstractMouseEvent;
import org.pepstock.charba.client.dom.events.NativeBaseEvent;
import org.pepstock.charba.client.dom.events.NativeCustomEvent;
import org.pepstock.charba.client.dom.events.NativeMouseEvent;
import org.pepstock.charba.client.enums.ModifierKey;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.events.DatasetRangeSelectionEvent;
import org.pepstock.charba.client.impl.plugins.enums.Align;
import org.pepstock.charba.client.impl.plugins.enums.DatasetSeletionEventType;
import org.pepstock.charba.client.impl.plugins.enums.Render;
import org.pepstock.charba.client.items.ChartAreaNode;
import org.pepstock.charba.client.items.IsArea;
import org.pepstock.charba.client.items.ScaleItem;
import org.pepstock.charba.client.items.ScaleValueItem;
import org.pepstock.charba.client.items.Undefined;
import org.pepstock.charba.client.options.IsImmutableFont;
import org.pepstock.charba.client.options.Scale;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Manages the selection on canvas, drawing selection area and implementing mouse listeners for canvas.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
final class SelectionHandler {

	// custom event type for programmatically selection
	static final String INTERNAL_MOUSE_DOWN = MouseEventType.MOUSE_DOWN.value() + Constants.UNDERSCORE + System.currentTimeMillis();
	// custom event type for programmatically selection
	static final String INTERNAL_MOUSE_UP = MouseEventType.MOUSE_UP.value() + Constants.UNDERSCORE + System.currentTimeMillis();

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the click function
	private final CallbackProxy<EventListenerCallback> mouseDownCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover function
	private final CallbackProxy<EventListenerCallback> mouseMoveCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the up function
	private final CallbackProxy<EventListenerCallback> mouseUpCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the leave function
	private final CallbackProxy<EventListenerCallback> mouseLeaveCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the click function
	private final CallbackProxy<EventListenerCallback> internalMouseDownCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the up function
	private final CallbackProxy<EventListenerCallback> internalMouseUpCallbackProxy = JsHelper.get().newCallbackProxy();

	// chart instance
	private final IsChart chart;
	// plugin options
	private final DatasetsItemsSelectorOptions options;
	// current selection area
	private final SelectionArea area = new SelectionArea();
	// stores the original top padding value
	private final int paddingTop;
	// stores the original bottom padding value
	private final int paddingBottom;
	// current plugin area
	private IsArea pluginArea = null;
	// current mouse track of selection
	private SelectionTrack track = null;
	// status if selected
	private SelectionStatus status = SelectionStatus.READY;
	// cursor before hover the selection cleaner
	private CursorType cursorOverSelectionCleaner = null;
	// this is a flag to prevent click event after drawing
	// of selection area
	private boolean preventClickEvent = false;

	/**
	 * Creates the selection handler with chart instance and the options (if exist) in the chart options.
	 * 
	 * @param chart chart instance
	 * @param options plugin options
	 */
	SelectionHandler(IsChart chart, DatasetsItemsSelectorOptions options) {
		// stores items
		this.chart = chart;
		this.options = options;
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// sets function to proxy callback in order to invoke the java interface
		this.mouseDownCallbackProxy.setCallback(this::onMouseDown);
		// sets function to proxy callback in order to invoke the java interface
		this.mouseMoveCallbackProxy.setCallback(this::onMouseMove);
		// sets function to proxy callback in order to invoke the java interface
		this.mouseUpCallbackProxy.setCallback(this::onMouseUp);
		// sets function to proxy callback in order to invoke the java interface
		this.mouseLeaveCallbackProxy.setCallback(this::onMouseLeave);
		// sets function to proxy callback in order to invoke the java interface
		this.internalMouseDownCallbackProxy.setCallback(this::onMouseDown);
		// sets function to proxy callback in order to invoke the java interface
		this.internalMouseUpCallbackProxy.setCallback(this::onMouseUp);
		// stores original padding values
		this.paddingTop = chart.getOptions().getLayout().getPadding().getTop();
		this.paddingBottom = chart.getOptions().getLayout().getPadding().getBottom();
		// gets selection item
		SelectionCleaner selectionCleaner = options.getSelectionCleaner();
		// checks if display is required
		if (selectionCleaner.isDisplay()) {
			// calculates the height of element
			calculateSelectionCleanerHeight();
			// calculates the width of element
			calculateSelectionCleanerWidth();
			int additionalPadding = selectionCleaner.getMargin();
			additionalPadding += selectionCleaner.getHeight();
			additionalPadding += selectionCleaner.getMargin();
			selectionCleaner.setLayoutPadding(additionalPadding);
			// based on the position, it must define space to show the label
			// to clear the selection, leveraging on padding of chart layout
			if (selectionCleaner.getPosition().equals(Position.TOP)) {
				// if the selection cleaner must be set on TOP
				// gets the padding set by chart configuration
				int padding = paddingTop;
				// adds on required padding, the space needed to show the selection cleaner
				// based on FONT SIZE, plus margins from border
				padding = padding + additionalPadding;
				// sets new padding top
				// on both nodes to be updated
				chart.getOptions().getLayout().getPadding().setTop(padding);
				chart.getNode().getOptions().getLayout().getPadding().setTop(padding);
			} else {
				// if the selection cleaner must be set on BOTTOM (other values of position are ignored)
				// gets the padding set by chart configuration
				int padding = paddingBottom;
				// adds on required padding, the space needed to show the selection cleaner
				// based on FONT SIZE, plus margins from border
				padding = padding + additionalPadding;
				// sets new padding bottom
				// on both nodes to be updated
				chart.getOptions().getLayout().getPadding().setBottom(padding);
				chart.getNode().getOptions().getLayout().getPadding().setBottom(padding);
			}
		}
	}

	/**
	 * Returns the options of datasets items selector plugin.
	 * 
	 * @return the options the options of datasets items selector plugin
	 */
	DatasetsItemsSelectorOptions getOptions() {
		return options;
	}

	/**
	 * Cleans up an handler which is going to be removed.
	 */
	void destroy() {
		// removes listeners
		removeListeners();
		// restores original top bottom
		chart.getOptions().getLayout().getPadding().setTop(paddingTop);
		// restores original padding bottom
		chart.getOptions().getLayout().getPadding().setBottom(paddingBottom);
	}

	/**
	 * Adds events listener to canvas element of chart.
	 */
	void addListeners() {
		// adds to the canvas all event listeners
		chart.getCanvas().addEventListener(MouseEventType.MOUSE_DOWN, mouseDownCallbackProxy.getProxy());
		chart.getCanvas().addEventListener(MouseEventType.MOUSE_MOVE, mouseMoveCallbackProxy.getProxy());
		chart.getCanvas().addEventListener(MouseEventType.MOUSE_LEAVE, mouseLeaveCallbackProxy.getProxy());
		chart.getCanvas().addEventListener(MouseEventType.MOUSE_UP, mouseUpCallbackProxy.getProxy());
		chart.getCanvas().addEventListener(INTERNAL_MOUSE_DOWN, internalMouseDownCallbackProxy.getProxy());
		chart.getCanvas().addEventListener(INTERNAL_MOUSE_UP, internalMouseUpCallbackProxy.getProxy());
	}

	/**
	 * Adds events listener to canvas element of chart.
	 */
	private void removeListeners() {
		// adds to the canvas all event listeners
		chart.getCanvas().removeEventListener(MouseEventType.MOUSE_DOWN, mouseDownCallbackProxy.getProxy());
		chart.getCanvas().removeEventListener(MouseEventType.MOUSE_MOVE, mouseMoveCallbackProxy.getProxy());
		chart.getCanvas().removeEventListener(MouseEventType.MOUSE_LEAVE, mouseLeaveCallbackProxy.getProxy());
		chart.getCanvas().removeEventListener(MouseEventType.MOUSE_UP, mouseUpCallbackProxy.getProxy());
		chart.getCanvas().removeEventListener(INTERNAL_MOUSE_DOWN, internalMouseDownCallbackProxy.getProxy());
		chart.getCanvas().removeEventListener(INTERNAL_MOUSE_UP, internalMouseUpCallbackProxy.getProxy());
	}

	/**
	 * Manages the mouse down event on canvas.
	 * 
	 * @param event canvas mouse event.
	 */
	void onMouseDown(NativeBaseEvent event) {
		// checks if mouse event
		if (event instanceof NativeAbstractMouseEvent) {
			// casts to mouce event
			NativeAbstractMouseEvent mouseEvent = (NativeAbstractMouseEvent) event;
			// removes the default behavior of mouse down on canvas
			// this removes the canvas selection
			event.preventDefault();
			// gets modifier key options
			ModifierKey modifier = options.getModifierKey();
			// if the mouse down event points
			// are in chart area and has got datasets items
			// checking also if modifier key is configured and pressed
			if (isEventInChartArea(mouseEvent) && (modifier == null || modifier.isPressed(mouseEvent))) {
				// sets cursor
				chart.getCanvas().getStyle().setCursorType(CursorType.CROSSHAIR);
				// then start selection with X coordinate
				startSelection(mouseEvent.getLayerX());
			}
		}
	}

	/**
	 * Manages the mouse move event on canvas.
	 * 
	 * @param event canvas mouse event.
	 */
	void onMouseMove(NativeBaseEvent event) {
		// checks if mouse event
		if (event instanceof NativeAbstractMouseEvent) {
			// casts to mouce event
			NativeAbstractMouseEvent mouseEvent = (NativeAbstractMouseEvent) event;
			// if the mouse move event points
			// are out of chart area
			if (!isEventInChartArea(mouseEvent)) {
				// figures out as an end of selection
				onMouseUp(event);
				return;
			}
			// removes the default behavior of mouse down on canvas
			// this removes the canvas selection
			event.preventDefault();
			// if the status of is in selecting
			// means that mouse down is already done
			if (getStatus().equals(SelectionStatus.SELECTING)) {
				// updates the selection in the canvas
				updateSelection(mouseEvent.getLayerX());
				chart.draw();
			} else if (isEventInSelectionCleaner(mouseEvent) && getStatus().equals(SelectionStatus.SELECTED)) {
				// if here
				// the mouse is hovering the selection cleaner
				// checks if was already hover
				// using the cursor previously saved
				if (cursorOverSelectionCleaner == null) {
					// gets cursor
					cursorOverSelectionCleaner = Utilities.getCursorOfChart(chart);
				}
				// sets cursor pointer because hover the selection cleaner
				chart.getCanvas().getStyle().setCursorType(CursorType.POINTER);
			} else if (cursorOverSelectionCleaner != null) {
				// if here, the mouse is not selecting and not hover the selection cleaner
				// but before it was on selection cleaner therefore reset the cursor and the instance
				chart.getCanvas().getStyle().setCursorType(cursorOverSelectionCleaner);
				cursorOverSelectionCleaner = null;
			}
		}
	}

	/**
	 * Manages the mouse leave event on canvas.
	 * 
	 * @param event canvas mouse event.
	 */
	void onMouseLeave(NativeBaseEvent event) {
		// if here the cursor went out of the canvas
		// the same of mouse up
		onMouseUp(event);
	}

	/**
	 * Manages the mouse up event on canvas.
	 * 
	 * @param event canvas mouse event.
	 */
	void onMouseUp(NativeBaseEvent event) {
		// checks if mouse event
		if (event instanceof NativeAbstractMouseEvent) {
			// casts to mouce event
			NativeAbstractMouseEvent mouseEvent = (NativeAbstractMouseEvent) event;
			// removes the default behavior of mouse down on canvas
			// this removes the canvas selection
			event.preventDefault();
			// this could be the end of selection
			// therefore will apply the logic of end selection
			// only if current status is selecting and selected
			if (getStatus().equals(SelectionStatus.SELECTING)) {
				// sets this flag to prevent to propagate a click event
				// that canvas is generating after mouse up
				preventClickEvent = true;
				// sets the cursor
				chart.getCanvas().getStyle().setCursorType(CursorType.DEFAULT);
				// updates the selection in the canvas
				updateSelection(mouseEvent.getLayerX());
				chart.draw();
				endSelection();
			}
		}
	}

	/**
	 * Returns a flag to prevent click event after drawing of selection area.
	 * 
	 * @return <code>true</code> if click event must be ignored
	 */
	boolean isPreventClickEvent() {
		return preventClickEvent;
	}

	/**
	 * Reset a flag to prevent click event after drawing of selection area.
	 */
	void resetPreventClickEvent() {
		preventClickEvent = false;
	}

	/**
	 * Returns the status of selection.
	 * 
	 * @return the status
	 */
	SelectionStatus getStatus() {
		return status;
	}

	/**
	 * Sets the status of selection.
	 * 
	 * @param status the status to set
	 */
	private void setStatus(SelectionStatus status) {
		this.status = status;
	}

	/**
	 * Start selection on canvas by the starting X coordinate.<br>
	 * Can be invokes by mouse down or refresh of chart (like resizing).
	 * 
	 * @param x the starting X coordinate.
	 */
	void startSelection(double x) {
		// sets status
		setStatus(SelectionStatus.SELECTING);
		// sets scale area
		pluginArea = getArea();
		// gets scale
		Scale axis = getAxis();
		// initializes the mouse track using the previous instance if exist
		track = new SelectionTrack(x, axis.isReverse(), track);
		// sets TOP and BOTTOM
		// always related to area dimension
		area.setTop(pluginArea.getTop());
		area.setBottom(pluginArea.getBottom());
		// sets the left part of selection area
		area.setLeft(track.getStart());
		// sets the right part of selection area, max must be right of chart area
		area.setRight(track.getStart());
	}

	/**
	 * Update an existing selection on canvas by new X coordinate.
	 * 
	 * @param x new X coordinate.
	 */
	void updateSelection(double x) {
		// checks if X coordinate is inside of
		// scale area
		// if less then area, used left as current track point
		// if great then area, used right as current track point
		// otherwise use X coordinate passed as current point
		track.setCurrent(Math.min(Math.max(x, pluginArea.getLeft()), pluginArea.getRight()));
		// sets the left part of selection area
		area.setLeft(track.getStart());
		// sets the right part of selection area, max must be right of chart area
		area.setRight(track.getEnd());
	}

	/**
	 * Draws an existing selection on canvas.
	 */
	void draw() {
		// gets context
		Context2dItem ctx = chart.getCanvas().getContext2d();
		// save context
		ctx.save();
		// sets the selecting color in the canvas
		ctx.setFillColor(options.getColorAsString());
		// draws the rectangle of area selection
		ctx.fillRect(Math.max(area.getLeft(), pluginArea.getLeft()), area.getTop(), Math.min(area.getRight(), pluginArea.getRight()) - area.getLeft(), area.getBottom() - area.getTop());
		// borders
		applyAreaBorder(ctx);
		// option instance
		DatasetsItemsSelectorOptions pOptions = getOptions();
		// gets selection cleaner configuration
		SelectionCleaner selectionCleaner = pOptions.getSelectionCleaner();
		// checks if selection cleaner must be draw
		if (selectionCleaner.isDisplay()) {
			// draws selection cleaner
			drawSelectionCleaner();
		}
		// restore context
		ctx.restore();
	}

	/**
	 * Apply the border to selection area if required.
	 * 
	 * @param ctx rendering interface used to draw on a canvas
	 */
	private void applyAreaBorder(Context2dItem ctx) {
		// checks if border width is consistent
		if (options.getBorderWidth() > 0) {
			// sets border width to context
			ctx.setLineWidth(options.getBorderWidth());
			// gets the border dash configuration
			List<Integer> borderDash = options.getBorderDash();
			// sets the border color in the canvas
			ctx.setStrokeColor(options.getBorderColorAsString());
			// if borer dash is consistent...
			if (!borderDash.isEmpty()) {
				// .. then sets the border dash
				ctx.setLineDash(options.getBorderDash());
			}
			// sets the border dasn offset
			ctx.setLineDashOffset(options.getBorderDashOffset());
			// draws the border of selected area
			ctx.strokeRect(area.getLeft(), area.getTop(), area.getRight() - area.getLeft(), area.getBottom() - area.getTop());
		}
	}

	/**
	 * Complete an existing selection on canvas by an event.<br>
	 * Can be invokes by mouse up or refresh of chart (like resizing).
	 */
	void endSelection() {
		endSelection(true);
	}

	/**
	 * Complete an existing selection on canvas by an event.<br>
	 * Can be invokes by mouse up or refresh of chart (like resizing).
	 * 
	 * @param fireEvent if <code>false</code>, does not send any event
	 */
	void endSelection(boolean fireEvent) {
		// sets status
		setStatus(SelectionStatus.SELECTED);
		// checks if it must send event
		// and if an area has been selected
		if (fireEvent && track != null && track.isValid() && chart.isEventHandled(DatasetRangeSelectionEvent.TYPE)) {
			// gets scale
			ScaleItem scaleItem = getXScale();
			// gets decimal (0..1) values
			double leftDecimal = scaleItem.getDecimalForPixel(area.getLeft());
			double rightDecimal = scaleItem.getDecimalForPixel(area.getRight());
			// stores the values of selected area from scale
			track.setStartDecimal(track.isReverse() ? rightDecimal : leftDecimal);
			track.setEndDecimal(track.isReverse() ? leftDecimal : rightDecimal);
			// creates custom event
			NativeCustomEvent customEvent = NativeCustomEvent.createCustomEvent(DatasetSeletionEventType.SET_SELECTION);
			// gets from item
			ScaleValueItem from = scaleItem.getValueAtPixel(area.getLeft());
			// gets to item
			ScaleValueItem to = scaleItem.getValueAtPixel(area.getRight());
			// fires the event that scale items selection
			chart.fireEvent(new DatasetRangeSelectionEvent(customEvent, track.isReverse() ? to : from, track.isReverse() ? from : to));
		}
	}

	/**
	 * Returns the X scale related to the plugin.
	 * 
	 * @return the X scale related to the plugin
	 */
	ScaleItem getXScale() {
		// gets chart node
		ChartNode node = chart.getNode();
		// gets the scale element of chart
		// using the X axis id of plugin options
		return node.getScales().getItems().get(options.getXAxisID().value());
	}

	/**
	 * Returns the Y scale related to the plugin.
	 * 
	 * @return the Y scale related to the plugin
	 */
	ScaleItem getYScale() {
		// checks if Y scale has been set
		if (options.getYAxisID() != null) {
			// gets chart node
			ChartNode node = chart.getNode();
			// gets the scale element of chart
			// using the X axis id of plugin options
			return node.getScales().getItems().get(options.getYAxisID().value());
		}
		// if here, y scale not consistent
		// then returns null
		return null;
	}

	/**
	 * Returns the axis related to the plugin.
	 * 
	 * @return the axis related to the plugin
	 */
	Scale getAxis() {
		// gets chart node
		ChartNode node = chart.getNode();
		// gets the scale element of chart
		// using the X axis id of plugin options
		return node.getOptions().getScales().getAxis(options.getXAxisID().value());
	}

	/**
	 * Recalculates the selection area and track and draws the area when a chart is updated or resized.
	 */
	void refresh() {
		// gets the scale element of chart
		// using the X axis id of plugin options
		ScaleItem scaleItem = getXScale();
		// gets START pixels by value
		double startPixel = scaleItem.getPixelForDecimal(track.getStartDecimal());
		// checks if the selection area is still consistent
		if (Undefined.is(startPixel) || startPixel > scaleItem.getRight()) {
			// does not refresh anything
			return;
		}
		// gets END pixels by value
		double endPixel = scaleItem.getPixelForDecimal(track.getEndDecimal());
		// checks if the selection area is still consistent
		if (Undefined.is(endPixel) || endPixel > scaleItem.getRight()) {
			// changes the track accordingly
			track.setCurrent(scaleItem.getRight());
			// reset end pixel
			endPixel = scaleItem.getRight();
		}
		// this is new start selection point
		startSelection((int) Math.ceil(startPixel));
		// updates pointer
		updateSelection((int) endPixel);
		// draws selection
		draw();
		// when here, the area has been draw
		// then complete the selection
		endSelection(false);
	}

	/**
	 * Selects an area, invoked programmatically.
	 * 
	 * @param from starting scale value
	 * @param to ending scale value
	 */
	void setSelection(double from, double to) {
		// gets chart node
		ChartNode node = chart.getNode();
		// gets chart area
		ChartAreaNode cArea = node.getChartArea();
		// checks if area is consistent
		if (!IsArea.isConsistent(cArea)) {
			return;
		}
		// gets scale item
		ScaleItem scaleitem = getXScale();
		// normalized the from and to passed
		// if not consistent, uses the area
		final double normalizedFrom = Checker.validOrDefault(from, scaleitem.getLeft());
		final double normalizedTo = Checker.validOrDefault(to, scaleitem.getRight());
		// gets canvas
		Canvas canvas = chart.getCanvas();
		// extracts the scrolling element
		BaseElement scrollElement = canvas.getScrollingElement();
		// gets canvas client coordinates
		final double clientX = canvas.getAbsoluteLeft() + canvas.getScrollLeft() + scrollElement.getScrollLeft();
		final double clientY = canvas.getAbsoluteTop() + canvas.getScrollTop() + scrollElement.getScrollTop();
		// calculates the real FROM X coordinate
		final double xFrom = normalizedFrom + clientX;
		// calculates the real TO X coordinate
		final double xTo = normalizedTo + clientX;
		// calculates the real Y coordinate, mid of chart area
		final double y = (cArea.getHeight() / 2) + clientY;
		// creates an initialization dictionary to create mouse event
		MouseEventInit start = new MouseEventInit();
		// sets mouse down and client coordinates
		start.setClientX(xFrom);
		start.setClientY(y);
		// creates and fires the event
		canvas.dispatchEvent(NativeMouseEvent.createMouseEvent(INTERNAL_MOUSE_DOWN, start));
		MouseEventInit end = new MouseEventInit();
		// sets mouse down and client coordinates
		end.setClientX(xTo);
		end.setClientY(y);
		// creates and fires the event
		canvas.dispatchEvent(NativeMouseEvent.createMouseEvent(INTERNAL_MOUSE_UP, end));
	}

	// -----------------------------------------
	// SELECTION CLEANER methods
	// -----------------------------------------

	/**
	 * Calculates the height of selection cleaner element and sets all values for label and image as well.<br>
	 * Height is composed by:<br>
	 * <ul>
	 * <li>border width
	 * <li>padding
	 * <li>image height or font size
	 * <li>padding
	 * <li>border width
	 * </ul>
	 */
	private void calculateSelectionCleanerHeight() {
		// option instance
		DatasetsItemsSelectorOptions pOptions = getOptions();
		// gets selection cleaner configuration
		SelectionCleaner selectionCleaner = pOptions.getSelectionCleaner();
		// creates an instance to stores the height
		// adding the border width top
		// and adding 1 to border width
		double height = selectionCleaner.isUseSelectionStyle() ? SelectionCleaner.BORDER_WIDTH + 1 : 0;
		// adds padding top
		height += selectionCleaner.getPadding();
		// checking what must be rendered
		if (Render.IMAGE.equals(selectionCleaner.getRender())) {
			// if here is ONLY image
			// gets image height
			double imgHeight = selectionCleaner.getImage() != null ? selectionCleaner.getImage().getHeight() : 0;
			// adds to total height
			height += imgHeight;
			// stores image height
			selectionCleaner.setImageHeight(imgHeight);
			// stores 0 for label height
			selectionCleaner.setLabelHeight(SelectionCleaner.DEFAULT_VALUE);
		} else {
			// gets immutable font
			IsImmutableFont font = Helpers.get().toFont(selectionCleaner.getFont());
			// if here there is a label
			// therefore the height is based on line height
			double lineHeight = font.getLineHeight();
			// adds font size to height
			height += lineHeight;
			// sets the height to image or
			// 0 if there is ONLY a label to show
			selectionCleaner.setImageHeight(Render.LABEL.equals(selectionCleaner.getRender()) ? SelectionCleaner.DEFAULT_VALUE : lineHeight);
			// stores label height
			selectionCleaner.setLabelHeight(lineHeight);
		}
		// adds padding bottom
		height += selectionCleaner.getPadding();
		// adds border width bottom
		// and adding 1 to border width
		height += selectionCleaner.isUseSelectionStyle() ? SelectionCleaner.BORDER_WIDTH + 1 : 0;
		// stores height
		selectionCleaner.setHeight(height);
	}

	/**
	 * Calculates the width of selection cleaner element and sets all values for label and image as well.<br>
	 * Width is composed by:<br>
	 * <ul>
	 * <li>border width
	 * <li>padding
	 * <li>image width or label width or image width plus label width with spacing
	 * <li>padding
	 * <li>border width
	 * </ul>
	 */
	private void calculateSelectionCleanerWidth() {
		// option instance
		DatasetsItemsSelectorOptions pOptions = getOptions();
		// gets selection cleaner configuration
		SelectionCleaner selectionCleaner = pOptions.getSelectionCleaner();
		// -----
		// calculate IMAGE
		// -----
		// gets image
		Img image = selectionCleaner.getImage();
		// check and gets the image size
		double imgWidth = image != null ? image.getWidth() : 1;
		double imgHeight = image != null ? image.getHeight() : 1;
		// maintains the image aspect ratio
		double aspectRatio = imgHeight / Math.max(imgWidth, 1);
		// calculates image width
		imgWidth = imgWidth * aspectRatio;
		// -----
		// calculate LABEL
		// -----
		// uses context of canvas and text metrics to calculate
		// the label width
		Context2dItem ctx = chart.getCanvas().getContext2d();
		// save context
		ctx.save();
		// sets font
		ctx.setFont(Helpers.get().toFontString(selectionCleaner.getFont()));
		// gets metrics
		TextMetricsItem metrics = ctx.measureText(selectionCleaner.getLabel());
		// stores the label width
		double labelWidth = metrics.getWidth();
		ctx.restore();
		// -----
		// calculate width
		// selection cleaner element
		// -----
		// adds border width left
		// and adding 1 to border width
		double width = selectionCleaner.isUseSelectionStyle() ? SelectionCleaner.BORDER_WIDTH + 1 : 0;
		// adds padding left
		width += selectionCleaner.getPadding();
		// checking what must be rendered
		if (Render.IMAGE.equals(selectionCleaner.getRender())) {
			// if here is rendering only image
			// adds image width
			width += imgWidth;
			// stores image width
			selectionCleaner.setImageWidth(imgWidth);
			// stores 0 for label width
			selectionCleaner.setLabelWidth(SelectionCleaner.DEFAULT_VALUE);
		} else if (Render.LABEL.equals(selectionCleaner.getRender())) {
			// if here is rendering only label
			// adds label width
			width += labelWidth;
			// stores 0 for image width
			selectionCleaner.setImageWidth(SelectionCleaner.DEFAULT_VALUE);
			// stores label width
			selectionCleaner.setLabelWidth(labelWidth);
		} else {
			// if here, it draws both image and label
			// it does not matter the sequence to calculate the width
			// adds label width
			width += labelWidth;
			// adds spacing
			width += selectionCleaner.getSpacing();
			// adds image width
			width += imgWidth;
			// stores image width
			selectionCleaner.setImageWidth(imgWidth);
			// stores label width
			selectionCleaner.setLabelWidth(labelWidth);
		}
		// adds padding right
		width += selectionCleaner.getPadding();
		// adds border width right
		// and adding 1 to border width
		width += selectionCleaner.isUseSelectionStyle() ? SelectionCleaner.BORDER_WIDTH + 1 : 0;
		// stores width
		selectionCleaner.setWidth(width);
	}

	/**
	 * Calculates X and Y coordinates for selection cleaner element and for image and label
	 */
	void calculateSelectionCleanerPositions() {
		// option instance
		DatasetsItemsSelectorOptions pOptions = getOptions();
		// gets selection cleaner element
		SelectionCleaner selectionCleaner = pOptions.getSelectionCleaner();
		// checks if is enabled
		if (selectionCleaner.isDisplay()) {
			// checks position of selection cleaner
			// ---------------------------------
			// calculate Y points
			// ---------------------------------
			calculatePointsY(selectionCleaner);
			// ---------------------------------
			// calculate X points
			// ---------------------------------
			calculatePointsX(selectionCleaner);
		}
	}

	/**
	 * Calculates X coordinates and items for selection cleaner element by alignment and rendering
	 * 
	 * @param selectionCleaner selection cleaner instance
	 */
	private void calculatePointsX(SelectionCleaner selectionCleaner) {
		// ---------------------------------
		// calculate X points
		// ---------------------------------
		// the X point depends on alignment required by configuration
		double x = 0;
		// checks all alignment types
		if (Align.LEFT.equals(selectionCleaner.getAlign())) {
			// if left
			// X is equals to margin
			selectionCleaner.setX(selectionCleaner.getMargin());
			// stores the x of selection cleaner element
			x = selectionCleaner.getMargin();
		} else if (Align.LEFT_CHART_AREA.equals(selectionCleaner.getAlign())) {
			// gets chart AREA
			ChartNode node = chart.getNode();
			ChartAreaNode areaInstance = node.getChartArea();
			// stores the x of selection cleaner element
			// setting the left value of chart area
			selectionCleaner.setX(areaInstance.getLeft());
			// sets to left for further calculations
			x = areaInstance.getLeft();
		} else if (Align.CENTER.equals(selectionCleaner.getAlign())) {
			// calculates the center of width
			// by canvas width and selection cleaner element width
			x = (chart.getCanvas().getOffsetWidth() - selectionCleaner.getWidth()) / 2;
			// stores the x of selection cleaner element
			selectionCleaner.setX(x);
		} else if (Align.CENTER_CHART_AREA.equals(selectionCleaner.getAlign())) {
			// gets chart AREA
			ChartNode node = chart.getNode();
			ChartAreaNode areaInstance = node.getChartArea();
			// calculates the center of width
			// by chart area width and selection cleaner element width
			x = (areaInstance.getRight() - areaInstance.getLeft() - selectionCleaner.getWidth()) / 2;
			// stores the x of selection cleaner element
			selectionCleaner.setX(x);
		} else if (Align.RIGHT_CHART_AREA.equals(selectionCleaner.getAlign())) {
			// gets chart AREA
			ChartNode node = chart.getNode();
			ChartAreaNode areaInstance = node.getChartArea();
			// the x value is the right point of chart area minus
			// width of selection cleaner element
			x = areaInstance.getRight() - selectionCleaner.getWidth();
			// stores the x of selection cleaner element
			selectionCleaner.setX(x);
		} else if (Align.RIGHT.equals(selectionCleaner.getAlign())) {
			// the x value is the width of canvas minus
			// width of selection cleaner element and margin
			x = chart.getCanvas().getOffsetWidth() - selectionCleaner.getWidth() - selectionCleaner.getMargin();
			// stores the x of selection cleaner element
			selectionCleaner.setX(x);
		}
		// for all elements
		// adds border width left
		// and adding 1 to border width
		x += selectionCleaner.isUseSelectionStyle() ? SelectionCleaner.BORDER_WIDTH + 1 : 0;
		// adds padding left
		// to have the X starting point
		x += selectionCleaner.getPadding();
		// calculates X by rendering
		adjustPointsXByRender(selectionCleaner, x);
	}

	/**
	 * Adjusts X coordinates and items for selection cleaner element by rendering, using x value calculated previously.
	 * 
	 * @param selectionCleaner selection cleaner instance
	 * @param x value calculated applying the alignment
	 */
	private void adjustPointsXByRender(SelectionCleaner selectionCleaner, double x) {
		// calculates point X based on render type
		if (Render.LABEL.equals(selectionCleaner.getRender())) {
			// if here ONLY label
			// stores to 0 the image
			selectionCleaner.setImageX(SelectionCleaner.DEFAULT_VALUE);
			// stores X point for label
			selectionCleaner.setLabelX(x);
		} else if (Render.LABEL_IMAGE.equals(selectionCleaner.getRender())) {
			// if here, before label and then image
			// stores X point for label
			selectionCleaner.setLabelX(x);
			// adds label width
			x += selectionCleaner.getLabelWidth();
			// adds spacing
			x += selectionCleaner.getSpacing();
			// stores X point for image
			selectionCleaner.setImageX(x);
		} else if (Render.IMAGE_LABEL.equals(selectionCleaner.getRender())) {
			// stores X point for image
			selectionCleaner.setImageX(x);
			// adds image width
			x += selectionCleaner.getImageWidth();
			// adds spacing
			x += selectionCleaner.getSpacing();
			// stores X point for label
			selectionCleaner.setLabelX(x);
		} else if (Render.IMAGE.equals(selectionCleaner.getRender())) {
			// if here ONLY image
			// stores X point for image
			selectionCleaner.setImageX(x);
			// stores 0 to label point X
			selectionCleaner.setLabelX(SelectionCleaner.DEFAULT_VALUE);
		}
	}

	/**
	 * Calculates Y coordinates and items for selection cleaner element by position.
	 * 
	 * @param selectionCleaner selection cleaner instance
	 */
	private void calculatePointsY(SelectionCleaner selectionCleaner) {
		// checks position of selection cleaner
		// ---------------------------------
		// calculate Y points
		// ---------------------------------
		// if the position is top
		if (selectionCleaner.getPosition().equals(Position.TOP)) {
			// for all elements the Y value is equals to margin
			// set in the configuration
			double y = selectionCleaner.getMargin();
			selectionCleaner.setY(y);
			// and adding 1 to border width
			y += selectionCleaner.isUseSelectionStyle() ? SelectionCleaner.BORDER_WIDTH + 1 : 0;
			// adds padding top
			y += selectionCleaner.getPadding();
			selectionCleaner.setImageY(y);
			selectionCleaner.setLabelY(y);
		} else {
			// calculates the Y point from bottom, using the canvas dimension
			// removing height of selection cleaner element and margin
			double y = chart.getCanvas().getOffsetHeight() - selectionCleaner.getHeight() - selectionCleaner.getMargin();
			// for all elements the Y value is equals
			selectionCleaner.setY(y);
			// and adding 1 to border width
			y += selectionCleaner.isUseSelectionStyle() ? SelectionCleaner.BORDER_WIDTH + 1 : 0;
			// adds padding top
			y += selectionCleaner.getPadding();
			selectionCleaner.setImageY(y);
			selectionCleaner.setLabelY(y);
		}
	}

	/**
	 * Called when the selection is clearing
	 */
	void removeSelectionCleaner() {
		// option instance
		DatasetsItemsSelectorOptions pOptions = getOptions();
		// gets selection cleaner element
		SelectionCleaner selectionCleaner = pOptions.getSelectionCleaner();
		// if selection cleaner element is enabled
		if (selectionCleaner.isDisplay()) {
			// gets context from canvas
			Context2dItem ctx = chart.getCanvas().getContext2d();
			// saves context
			ctx.save();
			// clear area from canvas of selection cleaner element
			ctx.clearRect(selectionCleaner.getX(), selectionCleaner.getY(), selectionCleaner.getWidth(), selectionCleaner.getHeight());
			// restores context
			ctx.restore();
		}
	}

	/**
	 * Draws the selection cleaner element in the canvas of chart
	 */
	private void drawSelectionCleaner() {
		// gets context from canvas
		Context2dItem ctx = chart.getCanvas().getContext2d();
		// option instance
		DatasetsItemsSelectorOptions pOptions = getOptions();
		// gets selection cleaner element
		SelectionCleaner selectionCleaner = pOptions.getSelectionCleaner();
		if (selectionCleaner.isUseSelectionStyle()) {
			// sets the selecting color in the canvas
			ctx.setFillColor(options.getColorAsString());
			// draws the rectangle of area selection
			ctx.fillRect(selectionCleaner.getX(), selectionCleaner.getY(), selectionCleaner.getWidth(), selectionCleaner.getHeight());
			// borders
			if (options.getBorderWidth() > 0) {
				ctx.setLineWidth(SelectionCleaner.BORDER_WIDTH);
				List<Integer> borderDash = options.getBorderDash();
				// sets the selecting color in the canvas
				ctx.setStrokeColor(options.getBorderColorAsString());
				if (!borderDash.isEmpty()) {
					ctx.setLineDash(options.getBorderDash());
				}
				ctx.setLineDashOffset(options.getBorderDashOffset());
				// gets increment
				double borderIncrement = SelectionCleaner.BORDER_WIDTH / 2D;
				// draw border fixing the dimensions of rectangle
				ctx.strokeRect(selectionCleaner.getX() + borderIncrement, selectionCleaner.getY() + borderIncrement, selectionCleaner.getWidth() - SelectionCleaner.BORDER_WIDTH, selectionCleaner.getHeight() - SelectionCleaner.BORDER_WIDTH);
			}
		}
		IsImmutableFont immtableFont = Helpers.get().toFont(selectionCleaner.getFont());
		// checks based on render type what must be draw
		if (Render.LABEL.equals(selectionCleaner.getRender())) {
			// sets font
			ctx.setFont(immtableFont.toCSSString());
			// sets color to canvas
			ctx.setFillColor(selectionCleaner.getColorAsString());
			// sets alignment from center point
			ctx.setTextBaseline(TextBaseline.TOP);
			// draws text
			ctx.fillText(selectionCleaner.getLabel(), selectionCleaner.getLabelX(), selectionCleaner.getLabelY());
		} else if (Render.LABEL_IMAGE.equals(selectionCleaner.getRender())) {
			// sets font
			ctx.setFont(immtableFont.toCSSString());
			// sets color to canvas
			ctx.setFillColor(selectionCleaner.getColorAsString());
			// sets alignment from center point
			ctx.setTextBaseline(TextBaseline.TOP);
			// draws text
			ctx.fillText(selectionCleaner.getLabel(), selectionCleaner.getLabelX(), selectionCleaner.getLabelY());
			// checks if image is consistent
			if (selectionCleaner.getImage() != null) {
				// draws scaled image
				ctx.drawImage(selectionCleaner.getImage(), selectionCleaner.getImageX(), selectionCleaner.getImageY(), selectionCleaner.getImageWidth(), selectionCleaner.getImageHeight());
			}
		} else if (Render.IMAGE_LABEL.equals(selectionCleaner.getRender())) {
			// checks if image is consistent
			if (selectionCleaner.getImage() != null) {
				// draws scaled image
				ctx.drawImage(selectionCleaner.getImage(), selectionCleaner.getImageX(), selectionCleaner.getImageY(), selectionCleaner.getImageWidth(), selectionCleaner.getImageHeight());
			}
			// sets font
			ctx.setFont(immtableFont.toCSSString());
			// sets color to canvas
			ctx.setFillColor(selectionCleaner.getColorAsString());
			// sets alignment from center point
			ctx.setTextBaseline(TextBaseline.TOP);
			// draws text
			ctx.fillText(selectionCleaner.getLabel(), selectionCleaner.getLabelX(), selectionCleaner.getLabelY());
		} else if (Render.IMAGE.equals(selectionCleaner.getRender())) {
			// with ONLY image is better to throw an exception
			// instead of skip the image draw
			// draws scaled image
			ctx.drawImage(selectionCleaner.getImage(), selectionCleaner.getImageX(), selectionCleaner.getImageY(), selectionCleaner.getImageWidth(), selectionCleaner.getImageHeight());
		}
	}

	/**
	 * Checks if the coordinate of event is inside the selection cleaner element.
	 * 
	 * @param event event to be checked.
	 * @return <code>true</code> if inside the element, otherwise <code>false</code>.
	 */
	private boolean isEventInSelectionCleaner(NativeAbstractMouseEvent event) {
		// option instance
		DatasetsItemsSelectorOptions pOptions = getOptions();
		// gets selection cleaner element
		SelectionCleaner selectionCleaner = pOptions.getSelectionCleaner();
		// checks if inside
		boolean isX = event.getLayerX() >= selectionCleaner.getX() && event.getLayerX() <= (selectionCleaner.getX() + selectionCleaner.getWidth());
		boolean isY = event.getLayerY() >= selectionCleaner.getY() && event.getLayerY() <= (selectionCleaner.getY() + selectionCleaner.getHeight());
		return isX && isY;
	}

	/**
	 * Checks if the coordinate of event is inside the chart area.
	 * 
	 * @param event event to be checked.
	 * @return <code>true</code> if inside the area, otherwise <code>false</code>.
	 */
	private boolean isEventInChartArea(NativeAbstractMouseEvent event) {
		// gets plugin area
		IsArea selectionArea = getArea();
		// checks if inside
		boolean isX = event.getLayerX() >= selectionArea.getLeft() && event.getLayerX() <= selectionArea.getRight();
		boolean isY = event.getLayerY() >= selectionArea.getTop() && event.getLayerY() <= selectionArea.getBottom();
		return isX && isY;
	}

	/**
	 * Returns the plugin selected area
	 * 
	 * @return the selected area
	 */
	private IsArea getArea() {
		// gets result
		SelectionArea selectionArea = new SelectionArea();
		// sets x scale item
		ScaleItem x = getXScale();
		// sets X values
		selectionArea.setLeft(x.getLeft());
		selectionArea.setRight(x.getRight());
		// sets y scale item
		ScaleItem y = getYScale();
		// checks if y scale is consistent
		if (y != null) {
			// sets Y values
			selectionArea.setTop(y.getTop());
			selectionArea.setBottom(y.getBottom());
		} else {
			// gets chart AREA
			ChartNode node = chart.getNode();
			ChartAreaNode cArea = node.getChartArea();
			// stores chart area
			IsArea chartArea = cArea.toArea();
			// sets Y values
			selectionArea.setTop(chartArea.getTop());
			selectionArea.setBottom(chartArea.getBottom());
		}
		return selectionArea;
	}

}