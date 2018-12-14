package org.pepstock.charba.client.plugins.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.events.DatasetRangeSelectionEvent;
import org.pepstock.charba.client.items.ChartAreaItem;
import org.pepstock.charba.client.items.ChartNode;
import org.pepstock.charba.client.items.DatasetItem;
import org.pepstock.charba.client.items.ScaleItem;
import org.pepstock.charba.client.items.ScalesNode;

import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.ui.Image;

final class SelectionHandler implements MouseDownHandler, MouseUpHandler, MouseMoveHandler{
	
	Logger LOG = Logger.getLogger("handler");

	private final AbstractChart<?, ?> chart;

	private final List<DatasetItem> visibleDatasetItems = new LinkedList<>();

	private final SelectionArea area = new SelectionArea();
	
	private final SelectionDatasetItems items = new SelectionDatasetItems();
	
	private SelectionTrack track = null;
	
	private boolean isSelecting = false;
	
	private boolean selected = false;
	
	private String snapshot = null;
	
	private final IsColor color = ColorBuilder.build(16, 150, 24);
	
	/**
	 * @param chart
	 */
	SelectionHandler(AbstractChart<?, ?> chart) {
		this.chart = chart;
	}
	
	/* (non-Javadoc)
	 * @see com.google.gwt.event.dom.client.MouseDownHandler#onMouseDown(com.google.gwt.event.dom.client.MouseDownEvent)
	 */
	@Override
	public void onMouseDown(MouseDownEvent event) {
		event.preventDefault();
		if (isEventInChartArea(event)) {
			ChartNode node = chart.getChartNode();
			ChartAreaItem chartArea = node.getChartArea();
			area.setTop(chartArea.getTop());
			area.setBottom(chartArea.getBottom());
			track = new SelectionTrack(event.getX());
			isSelecting = true;
		}
	}
	/* (non-Javadoc)
	 * @see com.google.gwt.event.dom.client.MouseMoveHandler#onMouseMove(com.google.gwt.event.dom.client.MouseMoveEvent)
	 */
	@Override
	public void onMouseMove(MouseMoveEvent event) {
		event.preventDefault();
		if (isSelecting) {
			if (!isEventInChartArea(event)) {
				onMouseUp(null);
				return;
			}
			if (snapshot != null) {
				Image img = new Image(snapshot);
				chart.getCanvas().getContext2d().drawImage(ImageElement.as(img.getElement()), 0, 0);
			}
			track.setCurrent(handleXPosition(event));
			handleSelectionArea();
			chart.getCanvas().getContext2d().setFillStyle(color.alpha(0.5).toRGBA());
			chart.getCanvas().getContext2d().fillRect(area.getLeft(), area.getTop(), area.getRight() - area.getLeft(), area.getBottom() - area.getTop());
			setSelected(true);
		}
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.event.dom.client.MouseUpHandler#onMouseUp(com.google.gwt.event.dom.client.MouseUpEvent)
	 */
	@Override
	public void onMouseUp(MouseUpEvent event) {
		event.preventDefault();
		isSelecting = false;
		// TODO
		if (chart.getType().equals(ChartType.line)) {
			chart.fireEvent(new DatasetRangeSelectionEvent(event.getNativeEvent(), items.getStart(), items.getEnd()+1));
		}
	}

	/**
	 * @return the selected
	 */
	boolean isSelected() {
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 * @return the snapshot
	 */
	String getSnapshot() {
		return snapshot;
	}

	/**
	 * @param snapshot the snapshot to set
	 */
	void setSnapshot(String snapshot) {
		this.snapshot = snapshot;
	}

	/**
	 * @return the visibleDatasetItems
	 */
	List<DatasetItem> getVisibleDatasetItems() {
		return visibleDatasetItems;
	}

	private boolean isEventInChartArea(MouseEvent<?> event) {
		ChartNode node = chart.getChartNode();
		ChartAreaItem area = node.getChartArea();
		boolean isX = event.getX() >= area.getLeft() && event.getX() <= area.getRight();
		boolean isY = event.getY() >= area.getTop() && event.getY() <= area.getBottom();
		return isX && isY;
	}

	private int handleXPosition(MouseEvent<?> event) {
		ChartNode node = chart.getChartNode();
		ChartAreaItem area = node.getChartArea();
		if (event.getX() < area.getLeft()) {
			return area.getLeft();
		}
		if (event.getX() > area.getRight()) {
			return area.getRight();
		}
		return event.getX();
	}
	
	private void handleSelectionArea(){
		ChartNode node = chart.getChartNode();
		ChartAreaItem chartArea = node.getChartArea();
		ScaleItem scale = node.getScales().getItems().get(ScalesNode.DEFAULT_X_AXIS_ID);
		int areaCount = chart.getType().equals(ChartType.line) ? visibleDatasetItems.size() - 1 : visibleDatasetItems.size() ;
		double scaleTickX = chartArea.getLeft();
		// arrotondamento.....
		double scaleTickLength = (double)scale.getWidth() / (double)areaCount; 
		for (int i=0; i<=areaCount; i++) {
			double scaleTickY = scaleTickX + scaleTickLength; 
			if (track.getStart() >= scaleTickX && track.getStart() <= scaleTickY) {
				items.setStart(i);
				area.setLeft(scaleTickX);
			}
			if (track.getEnd() >= scaleTickX && track.getStart() <= scaleTickY) {
				items.setEnd(i);
				area.setRight(Math.min(scaleTickY, chartArea.getRight()));
			}
			scaleTickX = scaleTickX + scaleTickLength;
		}
	}
}
