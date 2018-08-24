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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JavaScriptObjectContainer;
import org.pepstock.charba.client.commons.Key;

/**
 * Main object node which maps CHART.JS java script object in order to retrieve chart information (for instances
 * dimensions of all chart elements) at runtime.  
 *  
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ChartNode extends JavaScriptObjectContainer {
	
	private final LegendNode legendNode;
	
	private final ScalesNode scales;
	
	private final ChartAreaItem chartArea;

	private final TitleNode title;
	
	private final TooltipNode tooltip;
	
	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		id,
		width,
		height,
		aspectRatio,
		legend,
		titleBlock,
		currentDevicePixelRatio,
		scales,
		tooltip,
		animating,
		chartArea,
		borderWidth,
		outerRadius,
		innerRadius,
		radiusLength,
		offsetX,
		offsetY,
	}

	/**
	 * Wraps the CHART.JS java script object.
	 * 
	 * @param javaScriptObject CHART.JS java script object
	 */
	public ChartNode(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
		// initializes the sub objects
		legendNode = new LegendNode((GenericJavaScriptObject) getValue(Property.legend));
		scales = new ScalesNode((GenericJavaScriptObject) getValue(Property.scales));
		chartArea = new ChartAreaItem((GenericJavaScriptObject) getValue(Property.chartArea));
		title = new TitleNode((GenericJavaScriptObject) getValue(Property.titleBlock));
		tooltip = new TooltipNode((GenericJavaScriptObject) getValue(Property.titleBlock));
	}

	/**
	 * Returns the legend item.
	 * 
	 * @return the legend item.
	 */
	public LegendNode getLegend() {
		return legendNode;
	}

	/**
	 * Returns the scales item.
	 * 
	 * @return the scales item.
	 */
	public ScalesNode getScales() {
		return scales;
	}

	/**
	 * Returns the chart area item.
	 * 
	 * @return the chart area item.
	 */
	public ChartAreaItem getChartArea() {
		return chartArea;
	}

	/**
	 * Returns the title item.
	 * 
	 * @return the title item.
	 */
	public TitleNode getTitle() {
		return title;
	}

	/**
	 * Returns the tooltip item.
	 * 
	 * @return the tooltip item.
	 */
	public TooltipNode getTooltip() {
		return tooltip;
	}

	/**
	 * Returns the CHART JS chart ID.
	 * 
	 * @return the CHART JS chart ID. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getId() {
		return getValue(Property.id, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the width in pixel.
	 * 
	 * @return the width in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getWidth() {
		return getValue(Property.width, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the height in pixel.
	 * 
	 * @return the height in pixel. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getHeight() {
		return getValue(Property.height, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the aspect ratio.
	 * 
	 * @return the aspect ratio. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getAspectRatio() {
		return getValue(Property.aspectRatio, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the current device pixel ratio.
	 * 
	 * @return the current device pixel ratio. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getCurrentDevicePixelRatio() {
		return getValue(Property.currentDevicePixelRatio, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns if the chart is animating or not.
	 * 
	 * @return if the chart is animating or not. Default is {@link org.pepstock.charba.client.items.UndefinedValues#BOOLEAN}.
	 */
	public boolean isAnimating() {
		return getValue(Property.animating, UndefinedValues.BOOLEAN);
	}

	/**
	 * Returns the border width value.
	 * 
	 * @return the border width value. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getBorderWidth() {
		return getValue(Property.borderWidth, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the outer radius value.
	 * 
	 * @return the outer radius value. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getOuterRadius() {
		return getValue(Property.outerRadius, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the inner radius value.
	 * 
	 * @return the inner radius value. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getInnerRadius() {
		return getValue(Property.innerRadius, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the radius length value.
	 * 
	 * @return the radius length value. Default is {@link org.pepstock.charba.client.items.UndefinedValues#DOUBLE}.
	 */
	public double getRadiusLength() {
		return getValue(Property.radiusLength, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the offset X value.
	 * 
	 * @return the offset X value. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getOffsetX() {
		return getValue(Property.offsetX, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the offset Y value.
	 * 
	 * @return the offset Y value. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	public int getOffsetY() {
		return getValue(Property.offsetY, UndefinedValues.INTEGER);
	}

}