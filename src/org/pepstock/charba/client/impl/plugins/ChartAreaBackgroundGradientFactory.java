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

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.colors.Area;
import org.pepstock.charba.client.colors.CanvasObjectFactory;
import org.pepstock.charba.client.colors.Center;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.Radius;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.items.ChartAreaNode;

/**
 * Utility used by CHART {@link ChartBackgroundColor#ID} PLUGIN.<br>
 * Utility class which creates a canvas gradient java script object using a Charba gradient.<br>
 * The area used is the {@link ChartAreaNode}.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 * @see Gradient
 */
final class ChartAreaBackgroundGradientFactory extends CanvasObjectFactory {
	// singleton instance
	private static final ChartAreaBackgroundGradientFactory INSTANCE = new ChartAreaBackgroundGradientFactory();
	// scope
	private static final String SCOPE = ChartBackgroundColor.ID + "area";

	/**
	 * To avoid any instantiation
	 */
	private ChartAreaBackgroundGradientFactory() {
		super(Key.create(SCOPE));
	}

	/**
	 * Singleton method to get instance.
	 * 
	 * @return singleton instance
	 */
	static ChartAreaBackgroundGradientFactory get() {
		return INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.CanvasObjectFactory#getArea(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.colors.Gradient)
	 */
	@Override
	protected Area getArea(IsChart chart, Gradient gradient) {
		// IGNORE THE SCOPE of gradient
		// ALWAYS CHART AREA
		final Area area = new Area();
		// gets chart area
		ChartAreaNode chartArea = chart.getNode().getChartArea();
		// sets area dimension
		area.setTop(chartArea.getTop());
		area.setLeft(chartArea.getLeft());
		area.setRight(chartArea.getRight());
		area.setBottom(chartArea.getBottom());
		return area;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.CanvasObjectFactory#getCenter(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.colors.Gradient, int)
	 */
	@Override
	protected Center getCenter(IsChart chart, Gradient gradient, int datasetIndex) {
		// IGNORE THE SCOPE of gradient
		// ALWAYS CHART AREA
		final Center center = new Center();
		// gets chart area
		ChartAreaNode chartArea = chart.getNode().getChartArea();
		// CHART
		// the center of canvas has the following coordinates:
		// X - the difference between right and left, divided by 2 plus left
		// Y - the difference between bottom and top, divided by 2 plus top
		center.setX((chartArea.getWidth() / 2D) + chartArea.getLeft());
		center.setY((chartArea.getHeight() / 2D) + chartArea.getTop());
		return center;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.CanvasObjectFactory#getRadius(org.pepstock.charba.client.IsChart, org.pepstock.charba.client.colors.Gradient, int)
	 */
	@Override
	protected Radius getRadius(IsChart chart, Gradient gradient, int datasetIndex) {
		// by default is the center of chart area
		final Radius radius = new Radius();
		// gets chart area
		ChartAreaNode chartArea = chart.getNode().getChartArea();
		// CHART
		// by default is the center of chart area
		radius.setInner(0D);
		// radius - if max value between the difference between right and left and the difference between bottom and
		// top,
		// divided by 2
		radius.setOuter(Math.max(chartArea.getWidth(), chartArea.getHeight()) / 2D);
		return radius;
	}

}