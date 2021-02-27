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
package org.pepstock.charba.client.impl.plugins;

import java.util.List;

import org.pepstock.charba.client.ChartNode;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.dom.BaseEventTarget.EventListenerCallback;
import org.pepstock.charba.client.dom.BaseEventTypes;
import org.pepstock.charba.client.dom.BaseNativeEvent;
import org.pepstock.charba.client.dom.DOMBuilder;
import org.pepstock.charba.client.dom.elements.Context2dItem;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.dom.elements.TextMetricsItem;
import org.pepstock.charba.client.dom.enums.CursorType;
import org.pepstock.charba.client.dom.enums.TextBaseline;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.enums.Weight;
import org.pepstock.charba.client.events.DatasetRangeSelectionEvent;
import org.pepstock.charba.client.impl.plugins.enums.Align;
import org.pepstock.charba.client.impl.plugins.enums.Render;
import org.pepstock.charba.client.items.ChartAreaNode;
import org.pepstock.charba.client.items.ScaleItem;
import org.pepstock.charba.client.utils.Utilities;

/**
 * Manages the selection on canvas, drawing selection area and implementing mouse listeners for canvas.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
final class SelectionHandler {

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the click function
	private final CallbackProxy<EventListenerCallback> mouseDownCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the hover function
	private final CallbackProxy<EventListenerCallback> mouseMoveCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the leave function
	private final CallbackProxy<EventListenerCallback> mouseUpCallbackProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the leave function
	private final CallbackProxy<EventListenerCallback> mouseLeaveCallbackProxy = JsHelper.get().newCallbackProxy();

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
	// current selection dataset items
	// current mouse track of selection
	private SelectionTrack track = null;
	// status if selected
	private SelectionStatus status = SelectionStatus.READY;
	// copy of chart canvas as image to apply when is drwaing into canvas
	private Img snapshot = null;
	// previous chart area
	private String previousChartAreaAsString = null;
	// previous data URL chart as png
	private String previousDataURL = null;
	// flag if do not send any event after refresh
	private boolean skipNextFireEvent = false;
	// cursor before hover the clear selection
	private CursorType cursorOverClearSelection = null;
	// this is a flag to prevent click event after drawing
	// of selection area
	private boolean preventClickEvent = false;

	/**
	 * Creates the selection handler with chart instance and the options (if exist) into chart options.
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
		// fires the event
		this.mouseDownCallbackProxy.setCallback((context, event) -> onMouseDown(event));
		// fires the event
		this.mouseMoveCallbackProxy.setCallback((context, event) -> onMouseMove(event));
		// fires the event
		this.mouseUpCallbackProxy.setCallback((context, event) -> onMouseUp(event));
		// fires the event
		this.mouseLeaveCallbackProxy.setCallback((context, event) -> onMouseLeave(event));
		// stores original padding values
		this.paddingTop = chart.getOptions().getLayout().getPadding().getTop();
		this.paddingBottom = chart.getOptions().getLayout().getPadding().getBottom();
		// gets selection item
		ClearSelection clearSelection = options.getClearSelection();
		// checks if display is required
		if (clearSelection.isDisplay()) {
			// calculates the height of element
			calculateClearSelectionHeight();
			// calculates the width of element
			calculateClearSelectionWidth();
			int additionalPadding = clearSelection.getMargin();
			additionalPadding += clearSelection.getHeight();
			additionalPadding += clearSelection.getMargin();
			clearSelection.setLayoutPadding(additionalPadding);
			// based on the position, it must define space to show the label
			// to clear the selection, leveraging on padding of chart layout
			if (clearSelection.getPosition().equals(Position.TOP)) {
				// if the clear selection must be set on TOP
				// gets the padding set by chart configuration
				int padding = paddingTop;
				// adds on required padding, the space needed to show the clear selection
				// based on FONT SIZE, plus margins from border
				padding = padding + additionalPadding;
				// sets new padding top
				chart.getOptions().getLayout().getPadding().setTop(padding);
			} else {
				// if the clear selection must be set on BOTTOM (other values of position are ignored)
				// gets the padding set by chart configuration
				int padding = paddingBottom;
				// adds on required padding, the space needed to show the clear selection
				// based on FONT SIZE, plus margins from border
				padding = padding + additionalPadding;
				// sets new padding bottom
				chart.getOptions().getLayout().getPadding().setBottom(padding);
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
		chart.getCanvas().addEventListener(BaseEventTypes.MOUSE_DOWN, mouseDownCallbackProxy.getProxy());
		chart.getCanvas().addEventListener(BaseEventTypes.MOUSE_MOVE, mouseMoveCallbackProxy.getProxy());
		chart.getCanvas().addEventListener(BaseEventTypes.MOUSE_LEAVE, mouseLeaveCallbackProxy.getProxy());
		chart.getCanvas().addEventListener(BaseEventTypes.MOUSE_UP, mouseUpCallbackProxy.getProxy());
	}

	/**
	 * Adds events listener to canvas element of chart.
	 */
	private void removeListeners() {
		// adds to the canvas all event listeners
		chart.getCanvas().removeEventListener(BaseEventTypes.MOUSE_DOWN, mouseDownCallbackProxy.getProxy());
		chart.getCanvas().removeEventListener(BaseEventTypes.MOUSE_MOVE, mouseMoveCallbackProxy.getProxy());
		chart.getCanvas().removeEventListener(BaseEventTypes.MOUSE_LEAVE, mouseLeaveCallbackProxy.getProxy());
		chart.getCanvas().removeEventListener(BaseEventTypes.MOUSE_UP, mouseUpCallbackProxy.getProxy());
	}

	/**
	 * Manages the mouse down event on canvas.
	 * 
	 * @param event canvas mouse event.
	 */
	void onMouseDown(BaseNativeEvent event) {
		// removes the default behavior of mouse down on canvas
		// this removes the canvas selection
		event.preventDefault();
		// if the mouse down event points
		// are in chart area and has got datasets items
		if (isEventInChartArea(event)) {
			// sets cursor
			chart.getCanvas().getStyle().setCursorType(CursorType.CROSSHAIR);
			// then start selection with X coordinate
			startSelection(event.getX());
		}
	}

	/**
	 * Manages the mouse move event on canvas.
	 * 
	 * @param event canvas mouse event.
	 */
	void onMouseMove(BaseNativeEvent event) {
		// if the mouse move event points
		// are out of chart area
		if (!isEventInChartArea(event)) {
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
			// updates the selection into canvas
			updateSelection(event.getX(), false);
		} else if (isEventInClearSelection(event) && getStatus().equals(SelectionStatus.SELECTED)) {
			// if here
			// the mouse is hovering the clear selection
			// checks if was already hover
			// using the cursor previously saved
			if (cursorOverClearSelection == null) {
				// gets cursor
				cursorOverClearSelection = Utilities.getCursorOfChart(chart);
			}
			// sets cursor pointer because hover the clear selection
			chart.getCanvas().getStyle().setCursorType(CursorType.POINTER);
		} else if (cursorOverClearSelection != null) {
			// if here, the mouse is not selecting and not hover the clear selection
			// but before it was on clear selection therefore reset the cursor and the instance
			chart.getCanvas().getStyle().setCursorType(cursorOverClearSelection);
			cursorOverClearSelection = null;
		}
	}

	/**
	 * Manages the mouse leave event on canvas.
	 * 
	 * @param event canvas mouse event.
	 */
	void onMouseLeave(BaseNativeEvent event) {
		// if here the cursor went out of the canvas
		// the same of mouse up
		onMouseUp(event);
	}

	/**
	 * Manages the mouse up event on canvas.
	 * 
	 * @param event canvas mouse event.
	 */
	void onMouseUp(BaseNativeEvent event) {
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
			// updates the selection into canvas
			updateSelection(event.getX(), false);
			endSelection(event);
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
	 * Sets if next event firing should be skipped.
	 * 
	 * @param skipNextFireEvent the skipNextFireEvent to set
	 */
	void setSkipNextFireEvent(boolean skipNextFireEvent) {
		this.skipNextFireEvent = skipNextFireEvent;
	}

	/**
	 * Returns the image which is snapshot of chart.
	 * 
	 * @return the snapshot
	 */
	Img getSnapshot() {
		return snapshot;
	}

	/**
	 * @param snapshot the snapshot to set
	 */
	void setSnapshot(Img snapshot) {
		this.snapshot = snapshot;
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
		// gets chart AREA
		ChartNode node = chart.getNode();
		ChartAreaNode chartArea = node.getChartArea();
		// sets TOP and BOTTOM
		// always related to area dimension
		area.setTop(chartArea.getTop());
		area.setBottom(chartArea.getBottom());
		// initializes the mouse track
		track = new SelectionTrack(x);
	}

	/**
	 * Update an existing selection on canvas by new X coordinate.<br>
	 * Can be invokes by mouse move or refresh of chart (like resizing).
	 * 
	 * @param x new X coordinate.
	 * @param refresh if <code>true</code> the chart is refreshing therefore it doesn't clear the canvas
	 */
	void updateSelection(double x, boolean refresh) {
		// gets context
		Context2dItem ctx = chart.getCanvas().getContext2d();
		// save context
		ctx.save();
		// the snapshot is the image of chart (without any selection).
		// Every time the selection is updating, it removes the previous
		// selection putting the original chart (image snapshot) and then
		// draws new selection
		applySnapshot(ctx, refresh);
		// gets chart AREA
		ChartNode node = chart.getNode();
		ChartAreaNode chartArea = node.getChartArea();
		// checks if X coordinate is inside of
		// chart area
		// if less then area, used left as current track point
		// if great then area, used right as current track point
		// otherwise use X coordinate passed as current point
		track.setCurrent(Math.min(Math.max(x, chartArea.getLeft()), chartArea.getRight()));
		// sets the left part of selection area
		area.setLeft(track.getStart());
		// sets the right part of selection area, max must be right of chart area
		area.setRight(track.getEnd());
		// sets the selecting color into canvas
		ctx.setFillColor(options.getColorAsString());
		// draws the rectangle of area selection
		ctx.fillRect(area.getLeft(), area.getTop(), area.getRight() - area.getLeft(), area.getBottom() - area.getTop());
		// borders
		applyAreaBorder(ctx);
		// option instance
		DatasetsItemsSelectorOptions pOptions = getOptions();
		// gets clear selection configuration
		ClearSelection clearSelection = pOptions.getClearSelection();
		// checks if clear selection must be draw
		if (clearSelection.isDisplay()) {
			// draws clear selection
			drawClearSelection();
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
			// sets the border color into canvas
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
	 * The snapshot is the image of chart (without any selection).<br>
	 * Every time the selection is updating, it removes the previous selection putting the original chart (image snapshot) and then draws new selection.
	 * 
	 * @param ctx rendering interface used to draw on a canvas
	 * @param refresh if is called during a refresh
	 */
	private void applySnapshot(Context2dItem ctx, boolean refresh) {
		// the snapshot is the image of chart (without any selection).
		// Every time the selection is updating, it removes the previous
		// selection putting the original chart (image snapshot) and then
		// draws new selection
		if (snapshot != null) {
			// checks if is doing a refresh
			// in case of refresh, it doesn't clear the canvas
			if (!refresh) {
				// clears the canvas because the chart could have a transparent background color therefore
				// before to apply the image/snapshot, must clear the canvas (related to issue #26)
				ctx.clearRect(0, 0, chart.getCanvas().getOffsetWidth(), chart.getCanvas().getOffsetHeight());
			}
			// draws a scaled image setting width and height
			ctx.drawImage(snapshot, 0, 0, chart.getCanvas().getOffsetWidth(), chart.getCanvas().getOffsetHeight());
		}
	}

	/**
	 * Complete an existing selection on canvas by an event.<br>
	 * Can be invokes by mouse up or refresh of chart (like resizing).
	 * 
	 * @param event event which will complete the selection
	 */
	void endSelection(BaseNativeEvent event) {
		endSelection(event, false);
	}

	/**
	 * Complete an existing selection on canvas by an event.<br>
	 * Can be invokes by mouse up or refresh of chart (like resizing).
	 * 
	 * @param event event which will complete the selection
	 * @param skipNextFireEvent if <code>true</code>, does not send any event
	 */
	void endSelection(BaseNativeEvent event, boolean skipNextFireEvent) {
		// sets status
		setStatus(SelectionStatus.SELECTED);
		// checks if it must send event
		// and if an area has been selected
		if (!skipNextFireEvent && track != null && track.isValid() && chart.isEventHandled(DatasetRangeSelectionEvent.TYPE)) {
			// gets chart node
			ChartNode node = chart.getNode();
			// gets the scale element of chart
			// using the X axis id of plugin options
			ScaleItem scaleItem = node.getScales().getItems().get(options.getXAxisID().value());
			// stores the values of selected area from scale
			track.setStartValue(scaleItem.getValueForPixel(area.getLeft()));
			track.setEndValue(scaleItem.getValueForPixel(area.getRight()));
			// fires the event that scale items selection
			chart.fireEvent(new DatasetRangeSelectionEvent(event, scaleItem.getValueAtPixel(area.getLeft()), scaleItem.getValueAtPixel(area.getRight())));
		}
	}

	/**
	 * Recalculates the selection area and track and draws the area when a chart is updated or resized.
	 */
	void refresh() {
		// gets chart node
		ChartNode node = chart.getNode();
		ChartAreaNode chartArea = node.getChartArea();
		// gets the scale element of chart
		// using the X axis id of plugin options
		ScaleItem scaleItem = node.getScales().getItems().get(options.getXAxisID().value());
		// gets START pixels by value
		double startPixel = scaleItem.getPixelForValue(track.getStartValue());
		// checks if the selection area is still consistent
		if (startPixel > chartArea.getRight()) {
			// does not refresh anything
			return;
		}
		// gets END pixels by value
		double endPixel = scaleItem.getPixelForValue(track.getEndValue());
		// checks if the selection area is still consistent
		if (endPixel > chartArea.getRight()) {
			// changes the track accordingly
			track.setCurrent(chartArea.getRight());
			track.setEndValue(scaleItem.getValueForPixel(track.getEnd()));
			// reset end pixel
			endPixel = chartArea.getRight();
		}
		// workaround for category scale
		// when only 1 label has been selected
		if (AxisType.CATEGORY.equals(scaleItem.getType()) && Double.compare(track.getStartValue(), track.getEndValue()) == 0) {
			// gets a reference of next value
			// if start value is 0 it will use the value 1 to get the pixels gap
			// otherwise it will use the previous value
			double nextValueIndex = (track.getStartValue() == 0D) ? 1D : track.getStartValue() - 1;
			// gets the pixel by value
			double nextEndPixel = scaleItem.getPixelForValue(nextValueIndex);
			// if the pixel is consistent
			if (!Double.isNaN(nextEndPixel)) {
				// calculates the gap
				// if start value equals to 0 (first) then uses values from 0 to 1
				// if start value greater than 0 (no first) then uses values from current value minus 1
				double gap = (track.getStartValue() == 0D) ? (nextEndPixel - startPixel) / 2D : (startPixel - nextEndPixel) / 2D;
				// increments the start and end points
				startPixel = startPixel - gap;
				endPixel = endPixel + gap;
			} else {
				// if here the pixel of the other value is not consistent
				// then uses the whole area
				startPixel = chartArea.getLeft();
				endPixel = chartArea.getRight();
			}
		}
		// this is new start selection point
		startSelection((int) Math.ceil(startPixel));
		updateSelection((int) endPixel, true);
		// when here, the area has been draw
		// then complete the selection
		endSelection(DOMBuilder.get().createChangeEvent(), skipNextFireEvent);
		skipNextFireEvent = false;
	}

	/**
	 * Checks if the chart is changed.<br>
	 * It checks:<br>
	 * <ul>
	 * <li>the dimension of chart
	 * <li>data image of chart
	 * </ul>
	 * 
	 * @param dataUrl data image for chart
	 * @return <code>true</code> if chart is changed, otherwise <code>false</code>.
	 */
	boolean isChartChanged(String dataUrl) {
		// gets the chart area in json format
		String chartAreaAsString = chart.getNode().getChartArea().toString();
		// checks if previous values are null (first round)
		if (previousDataURL == null && previousChartAreaAsString == null) {
			// saves the current data image and dimensions of chart
			previousDataURL = dataUrl;
			previousChartAreaAsString = chartAreaAsString;
			return true;
		}
		// checks if dimension of chart is changed
		if (!chartAreaAsString.equalsIgnoreCase(previousChartAreaAsString)) {
			// saves the current data image and dimensions of chart
			previousDataURL = dataUrl;
			previousChartAreaAsString = chartAreaAsString;
			return true;
		}
		// checks if data image of chart is changed
		if (!dataUrl.equalsIgnoreCase(previousDataURL)) {
			// saves the current data image and dimensions of chart
			previousDataURL = dataUrl;
			previousChartAreaAsString = chartAreaAsString;
			return true;
		}
		// if here the chart is NOT changed
		return false;
	}

	// -----------------------------------------
	// CLEAR SELECTION methods
	// -----------------------------------------

	/**
	 * Calculates the height of clear selection element and sets all values for label and image as well.<br>
	 * Height is composed by:<br>
	 * <ul>
	 * <li>border width
	 * <li>padding
	 * <li>image height or font size
	 * <li>padding
	 * <li>border width
	 * </ul>
	 */
	private void calculateClearSelectionHeight() {
		// option instance
		DatasetsItemsSelectorOptions pOptions = getOptions();
		// gets clear selection configuration
		ClearSelection clearSelection = pOptions.getClearSelection();
		// creates an instance to stores the height
		// adding the border width top
		// and adding 1 to border width
		double height = clearSelection.isUseSelectionStyle() ? ClearSelection.BORDER_WIDTH + 1 : 0;
		// adds padding top
		height += clearSelection.getPadding();
		// checking what must be rendered
		if (Render.IMAGE.equals(clearSelection.getRender())) {
			// if here is ONLY image
			// gets image height
			double imgHeight = clearSelection.getImage().getHeight();
			// adds to total height
			height += imgHeight;
			// stores image height
			clearSelection.setImageHeight(imgHeight);
			// stores 0 for label height
			clearSelection.setLabelHeight(ClearSelection.DEFAULT_VALUE);
		} else {
			// if here there is a label
			// therefore the height is based on font size
			double fontSize = clearSelection.getFontSize();
			// adds font size to height
			height += fontSize;
			// sets the height to image or
			// 0 if there is ONLY a label to show
			clearSelection.setImageHeight(Render.LABEL.equals(clearSelection.getRender()) ? ClearSelection.DEFAULT_VALUE : fontSize);
			// stores label height
			clearSelection.setLabelHeight(fontSize);
		}
		// adds padding bottom
		height += clearSelection.getPadding();
		// adds border width bottom
		// and adding 1 to border width
		height += clearSelection.isUseSelectionStyle() ? ClearSelection.BORDER_WIDTH + 1 : 0;
		// stores height
		clearSelection.setHeight(height);
	}

	/**
	 * Calculates the width of clear selection element and sets all values for label and image as well.<br>
	 * Width is composed by:<br>
	 * <ul>
	 * <li>border width
	 * <li>padding
	 * <li>image width or label width or image width plus label width with spacing
	 * <li>padding
	 * <li>border width
	 * </ul>
	 */
	private void calculateClearSelectionWidth() {
		// option instance
		DatasetsItemsSelectorOptions pOptions = getOptions();
		// gets clear selection configuration
		ClearSelection clearSelection = pOptions.getClearSelection();
		// -----
		// calculate IMAGE
		// -----
		double imgWidth = clearSelection.getImage().getWidth();
		// maintains the image aspect ratio
		double aspectRatio = clearSelection.getImage().getHeight() / imgWidth;
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
		ctx.setFont(Utilities.toCSSFontProperty(clearSelection.getFontStyle(), Weight.NORMAL, clearSelection.getFontSize(), clearSelection.getFontFamily()));
		// gets metrics
		TextMetricsItem metrics = ctx.measureText(clearSelection.getLabel());
		// stores the label width
		double labelWidth = metrics.getWidth();
		ctx.restore();
		// -----
		// calculate width
		// clear selection element
		// -----
		// adds border width left
		// and adding 1 to border width
		double width = clearSelection.isUseSelectionStyle() ? ClearSelection.BORDER_WIDTH + 1 : 0;
		// adds padding left
		width += clearSelection.getPadding();
		// checking what must be rendered
		if (Render.IMAGE.equals(clearSelection.getRender())) {
			// if here is rendering only image
			// adds image width
			width += imgWidth;
			// stores image width
			clearSelection.setImageWidth(imgWidth);
			// stores 0 for label width
			clearSelection.setLabelWidth(ClearSelection.DEFAULT_VALUE);
		} else if (Render.LABEL.equals(clearSelection.getRender())) {
			// if here is rendering only label
			// adds label width
			width += labelWidth;
			// stores 0 for image width
			clearSelection.setImageWidth(ClearSelection.DEFAULT_VALUE);
			// stores label width
			clearSelection.setLabelWidth(labelWidth);
		} else {
			// if here, it draws both image and label
			// it does not matter the sequence to calculate the width
			// adds label width
			width += labelWidth;
			// adds spacing
			width += clearSelection.getSpacing();
			// adds image width
			width += imgWidth;
			// stores image width
			clearSelection.setImageWidth(imgWidth);
			// stores label width
			clearSelection.setLabelWidth(labelWidth);
		}
		// adds padding right
		width += clearSelection.getPadding();
		// adds border width right
		// and adding 1 to border width
		width += clearSelection.isUseSelectionStyle() ? ClearSelection.BORDER_WIDTH + 1 : 0;
		// stores width
		clearSelection.setWidth(width);
	}

	/**
	 * Calculates X and Y coordinates for clear selection element and for image and label
	 */
	void calculateClearSelectionPositions() {
		// option instance
		DatasetsItemsSelectorOptions pOptions = getOptions();
		// gets clear selection element
		ClearSelection clearSelection = pOptions.getClearSelection();
		// checks if is enabled
		if (clearSelection.isDisplay()) {
			// checks position of clear selection
			// ---------------------------------
			// calculate Y points
			// ---------------------------------
			calculatePointsY(clearSelection);
			// ---------------------------------
			// calculate X points
			// ---------------------------------
			calculatePointsX(clearSelection);
		}
	}

	/**
	 * Calculates X coordinates and items for clear selection element by alignment and rendering
	 * 
	 * @param clearSelection clear selection instance
	 */
	private void calculatePointsX(ClearSelection clearSelection) {
		// ---------------------------------
		// calculate X points
		// ---------------------------------
		// the X point depends on alignment required by configuration
		double x = 0;
		// checks all alignment types
		if (Align.LEFT.equals(clearSelection.getAlign())) {
			// if left
			// X is equals to margin
			clearSelection.setX(clearSelection.getMargin());
			// stores the x of clear selection element
			x = clearSelection.getMargin();
		} else if (Align.LEFT_CHART_AREA.equals(clearSelection.getAlign())) {
			// gets chart AREA
			ChartNode node = chart.getNode();
			ChartAreaNode areaInstance = node.getChartArea();
			// stores the x of clear selection element
			// setting the left value of chart area
			clearSelection.setX(areaInstance.getLeft());
			// sets to left for further calculations
			x = areaInstance.getLeft();
		} else if (Align.CENTER.equals(clearSelection.getAlign())) {
			// calculates the center of width
			// by canvas width and clear selection element width
			x = (chart.getCanvas().getOffsetWidth() - clearSelection.getWidth()) / 2;
			// stores the x of clear selection element
			clearSelection.setX(x);
		} else if (Align.CENTER_CHART_AREA.equals(clearSelection.getAlign())) {
			// gets chart AREA
			ChartNode node = chart.getNode();
			ChartAreaNode areaInstance = node.getChartArea();
			// calculates the center of width
			// by chart area width and clear selection element width
			x = (areaInstance.getRight() - areaInstance.getLeft() - clearSelection.getWidth()) / 2;
			// stores the x of clear selection element
			clearSelection.setX(x);
		} else if (Align.RIGHT_CHART_AREA.equals(clearSelection.getAlign())) {
			// gets chart AREA
			ChartNode node = chart.getNode();
			ChartAreaNode areaInstance = node.getChartArea();
			// the x value is the right point of chart area minus
			// width of clear selection element
			x = areaInstance.getRight() - clearSelection.getWidth();
			// stores the x of clear selection element
			clearSelection.setX(x);
		} else if (Align.RIGHT.equals(clearSelection.getAlign())) {
			// the x value is the width of canvas minus
			// width of clear selection element and margin
			x = chart.getCanvas().getOffsetWidth() - clearSelection.getWidth() - clearSelection.getMargin();
			// stores the x of clear selection element
			clearSelection.setX(x);
		}
		// for all elements
		// adds border width left
		// and adding 1 to border width
		x += clearSelection.isUseSelectionStyle() ? ClearSelection.BORDER_WIDTH + 1 : 0;
		// adds padding left
		// to have the X starting point
		x += clearSelection.getPadding();
		// calculates X by rendering
		adjustPointsXByRender(clearSelection, x);
	}

	/**
	 * Adjusts X coordinates and items for clear selection element by rendering, using x value calculated previously.
	 * 
	 * @param clearSelection clear selection instance
	 * @param x value calculated applying the alignment
	 */
	private void adjustPointsXByRender(ClearSelection clearSelection, double x) {
		// calculates point X based on render type
		if (Render.LABEL.equals(clearSelection.getRender())) {
			// if here ONLY label
			// stores to 0 the image
			clearSelection.setImageX(ClearSelection.DEFAULT_VALUE);
			// stores X point for label
			clearSelection.setLabelX(x);
		} else if (Render.LABEL_IMAGE.equals(clearSelection.getRender())) {
			// if here, before label and then image
			// stores X point for label
			clearSelection.setLabelX(x);
			// adds label width
			x += clearSelection.getLabelWidth();
			// adds spacing
			x += clearSelection.getSpacing();
			// stores X point for image
			clearSelection.setImageX(x);
		} else if (Render.IMAGE_LABEL.equals(clearSelection.getRender())) {
			// stores X point for image
			clearSelection.setImageX(x);
			// adds image width
			x += clearSelection.getImageWidth();
			// adds spacing
			x += clearSelection.getSpacing();
			// stores X point for label
			clearSelection.setLabelX(x);
		} else if (Render.IMAGE.equals(clearSelection.getRender())) {
			// if here ONLY image
			// stores X point for image
			clearSelection.setImageX(x);
			// stores 0 to label point X
			clearSelection.setLabelX(ClearSelection.DEFAULT_VALUE);
		}
	}

	/**
	 * Calculates Y coordinates and items for clear selection element by position.
	 * 
	 * @param clearSelection clear selection instance
	 */
	private void calculatePointsY(ClearSelection clearSelection) {
		// checks position of clear selection
		// ---------------------------------
		// calculate Y points
		// ---------------------------------
		// if the position is top
		if (clearSelection.getPosition().equals(Position.TOP)) {
			// for all elements the Y value is equals to margin
			// set into configuration
			double y = clearSelection.getMargin();
			clearSelection.setY(y);
			// and adding 1 to border width
			y += clearSelection.isUseSelectionStyle() ? ClearSelection.BORDER_WIDTH + 1 : 0;
			// adds padding top
			y += clearSelection.getPadding();
			clearSelection.setImageY(y);
			clearSelection.setLabelY(y);
		} else {
			// calculates the Y point from bottom, using the canvas dimension
			// removing height of clear selection element and margin
			double y = chart.getCanvas().getOffsetHeight() - clearSelection.getHeight() - clearSelection.getMargin();
			// for all elements the Y value is equals
			clearSelection.setY(y);
			// and adding 1 to border width
			y += clearSelection.isUseSelectionStyle() ? ClearSelection.BORDER_WIDTH + 1 : 0;
			// adds padding top
			y += clearSelection.getPadding();
			clearSelection.setImageY(y);
			clearSelection.setLabelY(y);
		}
	}

	/**
	 * Called when the selection is clearing
	 */
	void removeClearSelection() {
		// option instance
		DatasetsItemsSelectorOptions pOptions = getOptions();
		// gets clear selection element
		ClearSelection clearSelection = pOptions.getClearSelection();
		// if clear selection element is enabled
		if (clearSelection.isDisplay()) {
			// gets context from canvas
			Context2dItem ctx = chart.getCanvas().getContext2d();
			// saves context
			ctx.save();
			// clear area from canvas of clear selection element
			ctx.clearRect(clearSelection.getX(), clearSelection.getY(), clearSelection.getWidth(), clearSelection.getHeight());
			// restores context
			ctx.restore();
		}
	}

	/**
	 * Draws the clear selection element into canvas of chart
	 */
	private void drawClearSelection() {
		// gets context from canvas
		Context2dItem ctx = chart.getCanvas().getContext2d();
		// option instance
		DatasetsItemsSelectorOptions pOptions = getOptions();
		// gets clear selection element
		ClearSelection clearSelection = pOptions.getClearSelection();
		if (clearSelection.isUseSelectionStyle()) {
			// sets the selecting color into canvas
			ctx.setFillColor(options.getColorAsString());
			// draws the rectangle of area selection
			ctx.fillRect(clearSelection.getX(), clearSelection.getY(), clearSelection.getWidth(), clearSelection.getHeight());
			// borders
			if (options.getBorderWidth() > 0) {
				ctx.setLineWidth(ClearSelection.BORDER_WIDTH);
				List<Integer> borderDash = options.getBorderDash();
				// sets the selecting color into canvas
				ctx.setStrokeColor(options.getBorderColorAsString());
				if (!borderDash.isEmpty()) {
					ctx.setLineDash(options.getBorderDash());
				}
				ctx.setLineDashOffset(options.getBorderDashOffset());
				// gets increment
				double borderIncrement = ClearSelection.BORDER_WIDTH / 2D;
				// draw border fixing the dimensions of rectangle
				ctx.strokeRect(clearSelection.getX() + borderIncrement, clearSelection.getY() + borderIncrement, clearSelection.getWidth() - ClearSelection.BORDER_WIDTH, clearSelection.getHeight() - ClearSelection.BORDER_WIDTH);
			}
		}
		// checks based on render type what must be draw
		if (Render.LABEL.equals(clearSelection.getRender())) {
			// sets font
			ctx.setFont(Utilities.toCSSFontProperty(clearSelection.getFontStyle(), Weight.NORMAL, clearSelection.getFontSize(), clearSelection.getFontFamily()));
			// sets color to canvas
			ctx.setFillColor(clearSelection.getFontColorAsString());
			// sets alignment from center point
			ctx.setTextBaseline(TextBaseline.TOP);
			// draws text
			ctx.fillText(clearSelection.getLabel(), clearSelection.getLabelX(), clearSelection.getLabelY());
		} else if (Render.LABEL_IMAGE.equals(clearSelection.getRender())) {
			// sets font
			ctx.setFont(Utilities.toCSSFontProperty(clearSelection.getFontStyle(), Weight.NORMAL, clearSelection.getFontSize(), clearSelection.getFontFamily()));
			// sets color to canvas
			ctx.setFillColor(clearSelection.getFontColorAsString());
			// sets alignment from center point
			ctx.setTextBaseline(TextBaseline.TOP);
			// draws text
			ctx.fillText(clearSelection.getLabel(), clearSelection.getLabelX(), clearSelection.getLabelY());
			// draws scaled image
			ctx.drawImage(clearSelection.getImage(), clearSelection.getImageX(), clearSelection.getImageY(), clearSelection.getImageWidth(), clearSelection.getImageHeight());
		} else if (Render.IMAGE_LABEL.equals(clearSelection.getRender())) {
			// draws scaled image
			ctx.drawImage(clearSelection.getImage(), clearSelection.getImageX(), clearSelection.getImageY(), clearSelection.getImageWidth(), clearSelection.getImageHeight());
			// sets font
			ctx.setFont(Utilities.toCSSFontProperty(clearSelection.getFontStyle(), Weight.NORMAL, clearSelection.getFontSize(), clearSelection.getFontFamily()));
			// sets color to canvas
			ctx.setFillColor(clearSelection.getFontColorAsString());
			// sets alignment from center point
			ctx.setTextBaseline(TextBaseline.TOP);
			// draws text
			ctx.fillText(clearSelection.getLabel(), clearSelection.getLabelX(), clearSelection.getLabelY());
		} else if (Render.IMAGE.equals(clearSelection.getRender())) {
			// draws scaled image
			ctx.drawImage(clearSelection.getImage(), clearSelection.getImageX(), clearSelection.getImageY(), clearSelection.getImageWidth(), clearSelection.getImageHeight());
		}
	}

	/**
	 * Checks if the coordinate of event is inside the clear selection element.
	 * 
	 * @param event event to be checked.
	 * @return <code>true</code> if inside the element, otherwise <code>false</code>.
	 */
	private boolean isEventInClearSelection(BaseNativeEvent event) {
		// option instance
		DatasetsItemsSelectorOptions pOptions = getOptions();
		// gets clear selection element
		ClearSelection clearSelection = pOptions.getClearSelection();
		// checks if inside
		boolean isX = event.getX() >= clearSelection.getX() && event.getX() <= (clearSelection.getX() + clearSelection.getWidth());
		boolean isY = event.getY() >= clearSelection.getY() && event.getY() <= (clearSelection.getY() + clearSelection.getHeight());
		return isX && isY;
	}

	/**
	 * Checks if the coordinate of event is inside the chart area.
	 * 
	 * @param event event to be checked.
	 * @return <code>true</code> if inside the area, otherwise <code>false</code>.
	 */
	private boolean isEventInChartArea(BaseNativeEvent event) {
		// gets chart AREA
		ChartNode node = chart.getNode();
		ChartAreaNode areaInstance = node.getChartArea();
		// checks if inside
		boolean isX = event.getX() >= areaInstance.getLeft() && event.getX() <= areaInstance.getRight();
		boolean isY = event.getY() >= areaInstance.getTop() && event.getY() <= areaInstance.getBottom();
		return isX && isY;
	}

}
