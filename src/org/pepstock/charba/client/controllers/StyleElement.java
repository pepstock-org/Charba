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
package org.pepstock.charba.client.controllers;

import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.items.ChartNode;
import org.pepstock.charba.client.items.DatasetItem;
import org.pepstock.charba.client.items.ScaleItem;

/**
 * Element used by controller by <code>removeHoverStyle</code> and <code>setHoverStyle</code> methods.
 * It contrians information about the dataset, chart and scales to use.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class StyleElement extends DatasetItem {
	
	private final ChartNode chartNode;
	
	private final InternalScaleItem xScale;
	
	private final InternalScaleItem yScale;

	/**
	 * Needed for GWt injection
	 */
	private enum Property implements Key
	{
		_chart,
		_xScale,
		_yScale
	}

	/**
	 * Wraps the CHART.JS java script object.
	 * 
	 * @param javaScriptObject CHART.JS java script object
	 */
	StyleElement(GenericJavaScriptObject javaScriptObject) {
		super(javaScriptObject);
		// checks and set the chart node if exists
		if (has(Property._chart)) {
			chartNode = new ChartNode((GenericJavaScriptObject) getValue(Property._chart));
		} else {
			chartNode = null;
		}
		// checks and set the X scale if exists
		if (has(Property._xScale)) {
			xScale = new InternalScaleItem((GenericJavaScriptObject) getValue(Property._xScale));
		} else {
			xScale = null;
		}
		// checks and set the Y scale if exists
		if (has(Property._yScale)) {
			yScale = new InternalScaleItem((GenericJavaScriptObject) getValue(Property._yScale));
		} else {
			yScale = null;
		}
	}
	
	/**
	 * Returns the chart node instance.
	 * @return  the chart node instance otherwise <code>null</code> if not exists.
	 */
	public ChartNode getChartNode() {
		return chartNode;
	}

	/**
	 * Returns the X scale instance.
	 * @return  the X scale instance otherwise <code>null</code> if not exists.
	 */
	public ScaleItem getXScale() {
		return xScale;
	}

	/**
	 * Returns the Y scale instance.
	 * @return  the Y scale instance otherwise <code>null</code> if not exists.
	 */
	public ScaleItem getYScale() {
		return yScale;
	}
	
	/**
	 * Overrides the protected method of the container to export the java script object.
	 * @return export the java script object.
	 */
	GenericJavaScriptObject getObject() {
		return super.getJavaScriptObject();
	}
	
	/**
	 * Internal class to extend scale items.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	protected static class InternalScaleItem extends ScaleItem{

		/**
		 * Wraps the CHART.JS java script object.
		 * 
		 * @param javaScriptObject CHART.JS java script object
		 */
		protected InternalScaleItem(GenericJavaScriptObject javaScriptObject) {
			super(javaScriptObject);
		}
		
	}
	
}
