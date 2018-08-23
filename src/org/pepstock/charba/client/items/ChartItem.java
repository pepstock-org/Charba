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
 * Object which maps the CHART.JS chart java script object.<br> Used only for meter and gauge charts.
 *  
 * @author Andrea "Stock" Stocchero
 *
 */
public class ChartItem extends JavaScriptObjectContainer {
	
	private final LegendNodeItem legendNode;
	
	private final ScalesItem scales;
	
	private final ChartAreaItem chartArea;

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
		
		// PIE & Polar
		borderWidth,
		outerRadius,
		innerRadius,
		radiusLength,
		// solo PIE
		offsetX,
		offsetY,
		// mancano SCALES vedere BOXES per PIE

	}

	public ChartItem(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
		legendNode = new LegendNodeItem((GenericJavaScriptObject)getValue(Property.legend));
		scales = new ScalesItem((GenericJavaScriptObject)getValue(Property.scales));
		chartArea = new ChartAreaItem((GenericJavaScriptObject)getValue(Property.chartArea));
	}
	
	// FIXME
	public final LegendNodeItem getLegend(){
		return legendNode;
	}

	// FIXME
	public final ScalesItem getScales(){
		return scales;
	}

	/**
	 * Returns the chart area item.
	 * @return the chart area item.
	 */
	public final ChartAreaItem getChartArea(){
		return chartArea;
	}

//	// FIXME
//	public final JavaScriptObject getTitle(){
//		return getJavaScriptObject(Property.titleBlock, UndefinedValues.);
//	}
//
//	// FIXME
//	public final JavaScriptObject getTooltip(){
//		return getJavaScriptObject(Property.tooltip, UndefinedValues.);
//	}
//

	public final int getId(){
		return getValue(Property.id, UndefinedValues.INTEGER);
	}

	public final int getWidth(){
		return getValue(Property.width, UndefinedValues.INTEGER);
	}

	public final int getHeight(){
		return getValue(Property.height, UndefinedValues.INTEGER);
	}

	public final double getAspectRatio(){
		return getValue(Property.aspectRatio, UndefinedValues.DOUBLE);
	}

	public final double getCurrentDevicePixelRatio(){
		return getValue(Property.currentDevicePixelRatio, UndefinedValues.DOUBLE);
	}

	public final boolean isAnimating(){
		return getValue(Property.animating, UndefinedValues.BOOLEAN);
	}

	/**
	 * Returns the border width value.
	 * @return the border width value.
	 */
	public final int getBorderWidth(){
		return getValue(Property.borderWidth, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the outer radius value.
	 * @return the outer radius value.
	 */
	public final double getOuterRadius(){
		return getValue(Property.outerRadius, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the inner radius value.
	 * @return the inner radius value.
	 */
	public final double getInnerRadius(){
		return getValue(Property.innerRadius, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the radius length value.
	 * @return the radius length value.
	 */
	public final double getRadiusLength(){
		return getValue(Property.radiusLength, UndefinedValues.DOUBLE);
	}
	
	/**
	 * Returns the offset X value.
	 * @return the offset X value.
	 */
	public final int getOffsetX(){
		return getValue(Property.offsetX, UndefinedValues.INTEGER);
	}
	
	/**
	 * Returns the offset Y value.
	 * @return the offset Y value.
	 */
	public final int getOffsetY(){
		return getValue(Property.offsetY, UndefinedValues.INTEGER);
	}


}