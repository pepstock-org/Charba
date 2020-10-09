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
package org.pepstock.charba.client.data;

import org.pepstock.charba.client.ChartNode;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.colors.Area;
import org.pepstock.charba.client.colors.CanvasObjectFactory;
import org.pepstock.charba.client.colors.Center;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.GradientScope;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.colors.Radius;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.CanvasGradientItem;
import org.pepstock.charba.client.dom.elements.CanvasPatternItem;
import org.pepstock.charba.client.items.ChartAreaNode;
import org.pepstock.charba.client.items.DatasetItem;
import org.pepstock.charba.client.items.DatasetMetaItem;

/**
 * Utility class which creates a canvas gradient and pattern java script objects using a Charba gradient or pattern.<br>
 * A Charba gradient or pattern describes how a canvas gradient or pattern must be created.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 * @see Gradient
 * @see Pattern
 * @see CanvasGradientItem
 * @see CanvasPatternItem
 */
public final class DatasetCanvasObjectFactory extends CanvasObjectFactory {

	// singleton instance
	private static final DatasetCanvasObjectFactory INSTANCE = new DatasetCanvasObjectFactory();

	/**
	 * To avoid any instantiation
	 */
	private DatasetCanvasObjectFactory() {
		// do nothing
	}

	/**
	 * Singleton method to get instance.
	 * 
	 * @return signleton instance
	 */
	public static DatasetCanvasObjectFactory get() {
		return INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.CanvasObjectFactory#getArea(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.colors.Gradient)
	 */
	@Override
	protected Area getArea(IsChart chart, Gradient gradient) {
		// creates instance to return
		final Area area = new Area();
		// sets the coordinates of scope
		// CHART AREA
		ChartAreaNode chartArea = chart.getNode().getChartArea();
		// depending of scope (canvas or chart area)
		if (GradientScope.CANVAS.equals(gradient.getScope()) || !chartArea.isConsistent()) {
			// gets canvas
			Canvas canvas = chart.getCanvas();
			// sets the coordinates of scope
			// CANVAS
			area.setTop(0D);
			area.setLeft(0D);
			area.setRight(canvas.getOffsetWidth());
			area.setBottom(canvas.getOffsetHeight());
		} else {
			area.setTop(chartArea.getTop());
			area.setLeft(chartArea.getLeft());
			area.setRight(chartArea.getRight());
			area.setBottom(chartArea.getBottom());
		}
		return area;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.CanvasObjectFactory#getCenter(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.colors.Gradient, int, int)
	 */
	@Override
	protected Center getCenter(IsChart chart, Gradient gradient, int datasetIndex, int index) {
		// creates center instance to return
		final Center center = new Center();
		// gets chart node
		ChartNode node = chart.getNode();
		// gets chart area
		ChartAreaNode chartArea = node.getChartArea();
		// depending of scope (canvas or chart area)
		if (GradientScope.CANVAS.equals(gradient.getScope()) || !chartArea.isConsistent()) {
			// gets canvas
			Canvas canvas = chart.getCanvas();
			// CANVAS
			// the center of canvas has the following coordinates:
			// X - the width divided by 2
			// Y - the height divided by 2
			center.setX(canvas.getOffsetWidth() / 2D);
			center.setY(canvas.getOffsetHeight() / 2D);
		} else {
			// CHART
			// the center of canvas has the following coordinates:
			// X - the difference between right and left, divided by 2 plus left
			// Y - the difference between bottom and top, divided by 2 plus top
			center.setX(((chartArea.getRight() - chartArea.getLeft()) / 2D) + chartArea.getLeft());
			center.setY(((chartArea.getBottom() - chartArea.getTop()) / 2D) + chartArea.getTop());
		}
		return center;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.CanvasObjectFactory#getRadius(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.colors.Gradient, int, int)
	 */
	@Override
	protected Radius getRadius(IsChart chart, Gradient gradient, int datasetIndex, int index) {
		// creates radius instance to return
		final Radius radius = new Radius();
		// gets chart node
		ChartNode node = chart.getNode();
		// gets chart area
		ChartAreaNode chartArea = node.getChartArea();
		// gets dataset item at index 0
		DatasetMetaItem datasetMetaItem = chart.getDatasetMeta(datasetIndex);
		// depending of scope (canvas or chart area)
		if (GradientScope.CANVAS.equals(gradient.getScope()) || !chartArea.isConsistent()) {
			// CANVAS
			// checks if the radius is already calculated by CHART.JS
			// depending on chart type
			if (datasetMetaItem != null && !Double.isNaN(datasetMetaItem.getController().getInnerRadius()) && !Double.isNaN(datasetMetaItem.getController().getOuterRadius())) {
				// manages radius by chart node
				manageRadiusByChartNode(chart, datasetMetaItem, datasetIndex, index, radius);
			} else {
				// gets canvas
				Canvas canvas = chart.getCanvas();
				// by default is the center of chart area
				radius.setInner(0D);
				// radius - if max value between width and height, divided by 2
				radius.setOuter((Math.max(canvas.getOffsetWidth(), canvas.getOffsetHeight()) / 2D));
			}
		} else {
			// CHART
			// checks if the radius is already calculated by CHART.JS
			// depending on chart type
			if (datasetMetaItem != null && !Double.isNaN(datasetMetaItem.getController().getInnerRadius()) && !Double.isNaN(datasetMetaItem.getController().getOuterRadius())) {
				// manages radius by chart node
				manageRadiusByChartNode(chart, datasetMetaItem, datasetIndex, index, radius);
			} else {
				// by default is the center of chart area
				radius.setInner(0);
				// radius - if min value between the difference between right and left and the difference between bottom and
				// top,
				// divided by 2
				radius.setOuter((Math.min((chartArea.getRight() - chartArea.getLeft()), (chartArea.getBottom() - chartArea.getTop())) / 2D));
			}
		}
		return radius;
	}

	/**
	 * Manages radius values inspecting chart node and its values.
	 * 
	 * @param chart chart instance
	 * @param node chart node instance
	 * @param datasetIndex dataset index
	 * @param index data index
	 * @param radius radius instance to be updated
	 */
	private void manageRadiusByChartNode(IsChart chart, DatasetMetaItem node, int datasetIndex, int index, Radius radius) {
		// gets meta data
		DatasetMetaItem metaItem = chart.getDatasetMeta(datasetIndex);
		// checks if datasetIndex is consistent
		if (metaItem != null && index < metaItem.getDatasets().size() && index >= 0) {
			DatasetItem item = metaItem.getDatasets().get(index);
			// checks if chart is circular or not
			if (!Double.isNaN(item.getInnerRadius()) && !Double.isNaN(item.getOuterRadius())) {
				// uses the inner radius
				radius.setInner(Math.max(item.getInnerRadius(), node.getController().getInnerRadius()));
				// uses the outer radius
				radius.setOuter(Math.max(item.getOuterRadius(), node.getController().getOuterRadius()));
			} else {
				// uses the inner radius
				radius.setInner(node.getController().getInnerRadius());
				// uses the outer radius
				radius.setOuter(node.getController().getOuterRadius());
			}
		} else {
			// uses the inner radius
			radius.setInner(node.getController().getInnerRadius());
			// uses the outer radius
			radius.setOuter(node.getController().getOuterRadius());
		}
	}

}
