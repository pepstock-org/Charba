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
package org.pepstock.charba.client.jsinterop.impl.plugins;

import java.util.List;

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.jsinterop.AbstractChart;
import org.pepstock.charba.client.jsinterop.ChartNode;
import org.pepstock.charba.client.jsinterop.commons.JsHelper;
import org.pepstock.charba.client.jsinterop.events.DatasetRangeSelectionEvent;
import org.pepstock.charba.client.jsinterop.items.ChartAreaNode;
import org.pepstock.charba.client.jsinterop.items.ScaleItem;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;

/**
 * MAnages the selection on canvas, drawing selection area and implementing mouse listeners for canvas.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 1.8
 */
final class SelectionHandler implements MouseDownHandler, MouseUpHandler, MouseMoveHandler {
	
	// chart instance
	private final AbstractChart<?, ?> chart;
	// plugin options
	private final DatasetsItemsSelectorOptions options;
	// current selection area
	private final SelectionArea area = new SelectionArea();
	// current selection dataset items
	private final SelectionDatasetItems items = new SelectionDatasetItems();
	// current mouse track of selection
	private SelectionTrack track = null;
	// status if selected
	private SelectionStatus status = SelectionStatus.ready;
	// copy of chart canvas as image to apply when is drwaing into canvas
	private ImageElement snapshot = null;
	// amount of datasets items
	private int datasetsItemsCount = 0;

	/**
	 * Creates the selection handler with chart instance and the options (if exist) into chart options.
	 * 
	 * @param chart chart instance
	 * @param options plugin options
	 */
	SelectionHandler(AbstractChart<?, ?> chart, DatasetsItemsSelectorOptions options) {
		this.chart = chart;
		this.options = options;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.dom.client.MouseDownHandler#onMouseDown(com.google.gwt.event.dom.client.MouseDownEvent)
	 */
	@Override
	public void onMouseDown(MouseDownEvent event) {
		// removes the default behavior of mouse down on canvas
		// this removes the canvas selection
		event.preventDefault();
		// if the mouse down event points
		// are in chart area and has got datasets items
		if (datasetsItemsCount > 0 && isEventInChartArea(event)) {
			chart.getCanvas().getElement().getStyle().setCursor(Cursor.CROSSHAIR);
			// then start selection with X coordinate
			startSelection(event.getX());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.dom.client.MouseMoveHandler#onMouseMove(com.google.gwt.event.dom.client.MouseMoveEvent)
	 */
	@Override
	public void onMouseMove(MouseMoveEvent event) {
		// removes the default behavior of mouse down on canvas
		// this removes the canvas selection
		event.preventDefault();
		// if the status of is in selecting
		// means that mouse down is already done
		if (getStatus().equals(SelectionStatus.selecting)) {
			// if the mouse move event points
			// are out of chart area
			if (!isEventInChartArea(event)) {
				// figures out as an end of selection
				endSelection(event.getNativeEvent());
				return;
			}
			// updates the selection into canvas
			updateSelection(event.getX());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.dom.client.MouseUpHandler#onMouseUp(com.google.gwt.event.dom.client.MouseUpEvent)
	 */
	@Override
	public void onMouseUp(MouseUpEvent event) {
		// removes the default behavior of mouse down on canvas
		// this removes the canvas selection
		event.preventDefault();
		// this could be the end of selection
		// therefore will apply the logic of end selection
		// only if current status is selecting and selected
		if (getStatus().equals(SelectionStatus.selecting)) {
			// sets the cursor
			chart.getCanvas().getElement().getStyle().setCursor(Cursor.DEFAULT);
			endSelection(event.getNativeEvent());
		}
	}

	/**
	 * @return the status
	 */
	SelectionStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	private void setStatus(SelectionStatus status) {
		this.status = status;
	}

	/**
	 * @return the snapshot
	 */
	ImageElement getSnapshot() {
		return snapshot;
	}

	/**
	 * @param snapshot the snapshot to set
	 */
	void setSnapshot(ImageElement snapshot) {
		this.snapshot = snapshot;
	}

	/**
	 * @return the datasetsItemsCount
	 */
	int getDatasetsItemsCount() {
		return datasetsItemsCount;
	}

	/**
	 * @param datasetsItemsCount the datasetsItemsCount to set
	 */
	void setDatasetsItemsCount(int datasetsItemsCount) {
		this.datasetsItemsCount = datasetsItemsCount;
	}

	/**
	 * Start selection on canvas by the starting X coordinate.<br>
	 * Can be invokes by mouse down or refresh of chart (like resizing).
	 * 
	 * @param x the starting X coordinate.
	 */
	void startSelection(int x) {
		// sets status
		setStatus(SelectionStatus.selecting);
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
	 */
	void updateSelection(int x) {
		// the snapshot is the image of chart (without any selection).
		// Every time the selection is updating, it removes the previous
		// selection putting the original chart (image snapshot) and then
		// draws new selection
		if (snapshot != null) {
			chart.getCanvas().getContext2d().drawImage(snapshot, 0, 0);
		}
		// gets chart AREA
		ChartNode node = chart.getNode();
		ChartAreaNode chartArea = node.getChartArea();
		// checks if X coordinate is inside of
		// chart area
		if (x < chartArea.getLeft()) {
			// if less then area, used left as current track point
			track.setCurrent(chartArea.getLeft());
		} else if (x > chartArea.getRight()) {
			// if great then area, used right as current track point
			track.setCurrent(chartArea.getRight());
		} else {
			// otherwise use X coordinate passed as current point
			track.setCurrent(x);
		}
		// gets the scale element of chart
		// using the X axis id of plugin options
		ScaleItem scaleItem = node.getScales().getItems().get(options.getXAxisID());
		// calculates the amount of sections into chart based on
		// amount of dataset items
		// in case of time axis, it must be reduce by1 because the dataset items
		// are always located in line with tick
		int areaCount = chart.getType().equals(ChartType.line) ? getDatasetsItemsCount() - 1 : AxisType.time.equals(scaleItem.getType()) ? getDatasetsItemsCount() - 1 : getDatasetsItemsCount();
		// gets the left of chart area as starting point
		double scaleTickX = chartArea.getLeft();
		// calculates the section size for every dataset item
		// PAY attention to use DOUBLE because there is a problem
		// if rounds the values (does not select exactly the right section)
		double scaleTickLength = (double) scaleItem.getWidth() / (double) areaCount;
		// scans all sections
		for (int i = 0; i <= areaCount; i++) {
			// calculates the Y coordinate of section
			// adding to starting point the section size (always DOUBLE)
			double scaleTickY = scaleTickX + scaleTickLength;
			// checks if the X coordinate of track is inside of section
			if (track.getStart() >= scaleTickX && track.getStart() <= scaleTickY) {
				// sets the start dataset item index
				items.setStart(i);
				// sets the left part of selection area
				area.setLeft(scaleTickX);
			}
			// checks if the Y coordinate of track is inside of section
			if (track.getEnd() >= scaleTickX && track.getStart() <= scaleTickY) {
				// sets the end dataset item index
				items.setEnd(i);
				// sets the right part of selection area, max must be right of chart area
				area.setRight(Math.min(scaleTickY, chartArea.getRight()));
			}
			// increments the starting point of section
			scaleTickX = scaleTickX + scaleTickLength;
		}
		// sets the selecting color into canvas
		chart.getCanvas().getContext2d().setFillStyle(options.getColorAsString());
		// draws the rectangle of area selection
		chart.getCanvas().getContext2d().fillRect(area.getLeft(), area.getTop(), area.getRight() - area.getLeft(), area.getBottom() - area.getTop());
		// borders
		if (options.getBorderWidth() > 0) {
			chart.getCanvas().getContext2d().setLineWidth(options.getBorderWidth());
			List<Integer> borderDash = options.getBorderDash();
			// sets the selecting color into canvas
			chart.getCanvas().getContext2d().setStrokeStyle(options.getBorderColorAsString());
			if (!borderDash.isEmpty()) {
				JsHelper.get().setLineDash(chart.getCanvas().getContext2d(), options.getBorderDashAsJavaScriptObject());
			}
			chart.getCanvas().getContext2d().strokeRect(area.getLeft(), area.getTop(), area.getRight() - area.getLeft(), area.getBottom() - area.getTop());
		}
	}

	/**
	 * Complete an existing selection on canvas by an event.<br>
	 * Can be invokes by mouse up or refresh of chart (like resizing).
	 * 
	 * @param event event which will complete the selection
	 */
	void endSelection(NativeEvent event) {
		// sets status
		setStatus(SelectionStatus.selected);
		// gets chart node
		ChartNode node = chart.getNode();
		// gets the scale element of chart
		// using the X axis id of plugin options
		ScaleItem scaleItem = node.getScales().getItems().get(options.getXAxisID());
		// checks the type of chart and scale
		// LINE and axis TIME must be added by 1 end of datasets
		if (chart.getType().equals(ChartType.line) ||  AxisType.time.equals(scaleItem.getType())) {
			// fires the event that dataset items selection
			chart.fireEvent(new DatasetRangeSelectionEvent(event, items.getStart(), items.getEnd() + 1));
		} else if (chart.getType().equals(ChartType.bar)) {
			// fires the event that dataset items selection
			chart.fireEvent(new DatasetRangeSelectionEvent(event, items.getStart(), items.getEnd()));
		}
	}

	/**
	 * Recalculates the selection area and track and draws the area when a chart is updated or resized.
	 */
	void refresh() {		
		// gets chart AREA
		ChartNode node = chart.getNode();
		ChartAreaNode chartArea = node.getChartArea();
		// gets the scale element of chart
		// using the X axis id of plugin options
		ScaleItem scaleItem = node.getScales().getItems().get(options.getXAxisID());
		// calculates the amount of sections into chart based on
		// amount of dataset items
		int areaCount = chart.getType().equals(ChartType.line) ? getDatasetsItemsCount() - 1 : AxisType.time.equals(scaleItem.getType()) ? getDatasetsItemsCount() - 1 : getDatasetsItemsCount();
		// gets the left of chart area as starting point
		double scaleTickX = chartArea.getLeft();
		// calculates the section size for every dataset item
		// PAY attention to use DOUBLE because there is a problem
		// if rounds the values (does not select exactly the right section)
		double scaleTickLength = (double) scaleItem.getWidth() / (double) areaCount;
		// scans all sections
		for (int i = 0; i <= areaCount; i++) {
			// when the section index is equals of start of dataset item index
			if (items.getStart() == i) {
				// this is new start selection point
				startSelection((int) Math.ceil(scaleTickX));
			}
			// when the section index is equals of end of dataset item index
			if (items.getEnd() == i) {
				double middle = scaleTickX + scaleTickLength / 2;
				// this is new end selection point
				updateSelection((int) middle);
			}
			// increments the starting point of section
			scaleTickX = scaleTickX + scaleTickLength;
		}
		// when here, the area has been draw
		// then complete the selection
		endSelection(Document.get().createChangeEvent());
	}

	/**
	 * Checks if the coordinate of event is inside teh chart area.
	 * 
	 * @param event event to be checked.
	 * @return <code>true</code> if inside the area, otherwise <code>false</code>.
	 */
	private boolean isEventInChartArea(MouseEvent<?> event) {
		// gets chart AREA
		ChartNode node = chart.getNode();
		ChartAreaNode area = node.getChartArea();
		// checks if inside
		boolean isX = event.getX() >= area.getLeft() && event.getX() <= area.getRight();
		boolean isY = event.getY() >= area.getTop() && event.getY() <= area.getBottom();
		return isX && isY;
	}
	
}
