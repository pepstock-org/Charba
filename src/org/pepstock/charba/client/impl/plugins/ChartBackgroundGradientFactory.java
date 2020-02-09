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

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.colors.Area;
import org.pepstock.charba.client.colors.CanvasObjectFactory;
import org.pepstock.charba.client.colors.Center;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.Radius;
import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Div;

/**
 * Utility used by CHART {@link ChartBackgroundColor#ID} PLUGIN.<br>
 * Utility class which creates a canvas gradient java script object using a Charba gradient.<br>
 * A Charba gradient describes how a GWT canvas gradient must be created.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 * @see Gradient
 */
final class ChartBackgroundGradientFactory extends CanvasObjectFactory {
	// singleton instance
	private static final ChartBackgroundGradientFactory INSTANCE = new ChartBackgroundGradientFactory();

	/**
	 * To avoid any instantiation
	 */
	private ChartBackgroundGradientFactory() {
		// do nothing
	}

	/**
	 * Singleton method to get instance.
	 * 
	 * @return signleton instance
	 */
	static ChartBackgroundGradientFactory get() {
		return INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.CanvasObjectFactory#getArea(org.pepstock.charba.client.IsChart,
	 * org.pepstock.charba.client.colors.Gradient)
	 */
	@Override
	protected Area getArea(IsChart chart, Gradient gradient) {
		// IGNORE THE SCOPE of gradient
		// ALWAYS CANVAS
		// these are the coordinates instances of canvas
		// left - the x coordinate of the starting point of the canvas
		// top - the y coordinate of the starting point of the canvas
		final Area area = new Area();
		area.setTop(0D);
		area.setLeft(0D);
		// checks if chart is abstract one
		if (IsChart.isAbstractChart(chart)) {
			// gets simple panel
			AbstractChart<?> abstractChart = (AbstractChart<?>) chart;
			// gets div element
			Div element = abstractChart.getChartElement();
			// right - the x coordinate of the ending point of the widget
			// bottom - the y coordinate of the ending point of the widget
			area.setRight(element.getOffsetWidth());
			area.setBottom(element.getOffsetHeight());
		} else if (IsChart.isValid(chart)) {
			// gets canvas
			Canvas canvas = chart.getCanvas();
			// right - the x coordinate of the ending point of the canvas
			// bottom - the y coordinate of the ending point of the canvas
			area.setRight(canvas.getOffsetWidth());
			area.setBottom(canvas.getOffsetHeight());
		}
		return area;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.CanvasObjectFactory#getCenter(org.pepstock.charba.client.IsChart,
	 * org.pepstock.charba.client.colors.Gradient, int, int)
	 */
	@Override
	protected Center getCenter(IsChart chart, Gradient gradient, int datasetIndex, int index) {
		// IGNORE THE SCOPE of gradient
		// ALWAYS CANVAS
		// the center of canvas has the following coordinates:
		// X - the width divided by 2
		// Y - the height divided by 2
		final Center center = new Center();
		// checks if chart is abstract one
		if (IsChart.isAbstractChart(chart)) {
			// gets simple panel
			AbstractChart<?> abstractChart = (AbstractChart<?>) chart;
			// gets div element
			Div element = abstractChart.getChartElement();
			// these are the coordinates of center of widget
			center.setX(element.getOffsetWidth() / 2D);
			center.setY(element.getOffsetHeight() / 2D);
		} else if (IsChart.isValid(chart)) {
			// gets canvas
			Canvas canvas = chart.getCanvas();
			// these are the coordinates of center of canvas
			center.setX(canvas.getOffsetWidth() / 2D);
			center.setY(canvas.getOffsetHeight() / 2D);
		}
		return center;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.CanvasObjectFactory#getRadius(org.pepstock.charba.client.IsChart,
	 * org.pepstock.charba.client.colors.Gradient, int, int)
	 */
	@Override
	protected Radius getRadius(IsChart chart, Gradient gradient, int datasetIndex, int index) {
		// by default is the center of chart area
		final Radius radius = new Radius();
		radius.setInner(0);
		// checks if chart is abstract one
		if (IsChart.isAbstractChart(chart)) {
			// gets simple panel
			AbstractChart<?> abstractChart = (AbstractChart<?>) chart;
			// gets div element
			Div element = abstractChart.getChartElement();
			// radius - if max value between width and height, divided by 2
			radius.setOuter(Math.max(element.getOffsetWidth(), element.getOffsetHeight()) / 2D);
		} else if (IsChart.isValid(chart)) {
			// gets canvas
			Canvas canvas = chart.getCanvas();
			// radius - if max value between width and height, divided by 2
			radius.setOuter(Math.max(canvas.getOffsetWidth(), canvas.getOffsetHeight()) / 2D);
		}
		return radius;
	}

}
