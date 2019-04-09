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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.colors.Area;
import org.pepstock.charba.client.colors.CanvasObjectFactory;
import org.pepstock.charba.client.colors.Center;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.GradientScope;
import org.pepstock.charba.client.colors.Radius;

import com.google.gwt.canvas.client.Canvas;

/**
 * Utility used by CHART {@link ChartBackgroundColor#ID} PLUGIN.<br>
 * Utility class which creates a canvas gradient java script object using a Charba gradient.<br>
 * A Charba gradient describes how a GWT canvas gradient must be created.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 * @see Gradient
 * @see com.google.gwt.canvas.dom.client.CanvasGradient
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
	 * org.pepstock.charba.client.colors.GradientScope)
	 */
	@Override
	protected final Area getArea(IsChart chart, GradientScope scope) {
		// gets canvas
		Canvas canvas = chart.getCanvas();
		// IGNORE THE SCOPE of gradient
		// ALWAYS CANVAS
		// these are the coordinates instances of canvas
		// left - the x coordinate of the starting point of the canvas
		// top - the y coordinate of the starting point of the canvas
		// right - the x coordinate of the ending point of the canvas
		// bottom - the y coordinate of the ending point of the canvas
		final Area area = new Area();
		area.setTop(0D);
		area.setLeft(0D);
		area.setRight(canvas.getOffsetWidth());
		area.setBottom(canvas.getOffsetHeight());
		return area;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.CanvasObjectFactory#getCenter(org.pepstock.charba.client.IsChart,
	 * org.pepstock.charba.client.colors.Gradient, int, int)
	 */
	@Override
	protected final Center getCenter(IsChart chart, Gradient gradient, int datasetIndex, int index) {
		// gets canvas
		Canvas canvas = chart.getCanvas();
		// IGNORE THE SCOPE of gradient
		// ALWAYS CANVAS
		// the center of canvas has the following coordinates:
		// X - the width divided by 2
		// Y - the height divided by 2
		// these are the coordinates of center of canvas
		final Center center = new Center();
		center.setX(canvas.getOffsetWidth() / 2D);
		center.setY(canvas.getOffsetHeight() / 2D);
		return center;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.CanvasObjectFactory#getRadius(org.pepstock.charba.client.IsChart,
	 * org.pepstock.charba.client.colors.Gradient, int, int)
	 */
	@Override
	protected final Radius getRadius(IsChart chart, Gradient gradient, int datasetIndex, int index) {
		// gets canvas
		Canvas canvas = chart.getCanvas();
		// by default is the center of chart area
		// radius - if max value between width and height, divided by 2
		final Radius radius = new Radius();
		radius.setInner(0);
		radius.setOuter(Math.max(canvas.getOffsetWidth(), canvas.getOffsetHeight()) / 2D);
		return radius;
	}

}
