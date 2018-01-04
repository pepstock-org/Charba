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
package org.pepstock.charba.client.options;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.callbacks.LegendFilterHandler;
import org.pepstock.charba.client.callbacks.LegendLabelsCallback;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.items.LegendItem;

/**
 * This is the labels configuration of the legend.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LegendLabels extends AbstractLabel {

	private static final int DEFAULT_BOX_WIDTH = 40;

	private static final boolean DEFAULT_USE_POINT_STYLE = false;

	private LegendLabelsCallback labelsCallBack = null;

	private LegendFilterHandler filterHandler = null;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		boxWidth,
		usePointStyle,
		generateLabels,
		filter
	}

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart
	 *            chart instance
	 */
	LegendLabels(AbstractChart<?, ?> chart) {
		super(chart);
	}

	/**
	 * @return the legendCallBack
	 */
	public LegendLabelsCallback getLabelsCallBack() {
		return labelsCallBack;
	}

	/**
	 * @param legendLabelsCallBack
	 *            the legendCallBack to set
	 */
	public void setLabelsCallBack(LegendLabelsCallback legendLabelsCallBack) {
		// checks if the callback is already set into java script object
		if (hasToBeRegistered(legendLabelsCallBack, Property.generateLabels)) {
			// registers the handler into java script object
			registerNativeLegendLabelsHandler(getJavaScriptObject());
		}
		this.labelsCallBack = legendLabelsCallBack;
	}

	/**
	 * @return the legendFilterHandler
	 */
	public LegendFilterHandler getFilterHandler() {
		return filterHandler;
	}

	/**
	 * @param legendFilterHandler
	 *            the legendFilterHandler to set
	 */
	public void setLegendFilterHandler(LegendFilterHandler filterHandler) {
		// checks if the callback is already set into java script object
		if (hasToBeRegistered(filterHandler, Property.filter)) {
			// registers the handler into java script object
			registerNativeFilterLabelsHandler(getJavaScriptObject());
		}
		this.filterHandler = filterHandler;
	}

	/**
	 * Sets if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case).
	 * @param usePointStyle if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case).
	 */
	public void setUsePointStyle(boolean usePointStyle) {
		setValue(Property.usePointStyle, usePointStyle);
	}

	/**
	 * Returns if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case).
	 * @return if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case). Default is false.
	 */
	public boolean isUsePointStyle() {
		return getValue(Property.usePointStyle, DEFAULT_USE_POINT_STYLE);
	}

	/**
	 * Sets the width of coloured box.
	 * @param boxWidth width of coloured box.
	 */
	public void setBoxWidth(int boxWidth) {
		setValue(Property.boxWidth, boxWidth);
	}

	/**
	 * Returns the width of coloured box.
	 * @return width of coloured box. Default is 40.
	 */
	public int getBoxWidth() {
		return getValue(Property.boxWidth, DEFAULT_BOX_WIDTH);
	}

	/**
	 * Called to generate legend items for each thing in the legend. Default implementation returns the text + styling for the color box. 
	 * @return array of legend items.
	 */
	protected LegendItem[] generateLegendLabels() {
		// checks if callback is consistent
		if (labelsCallBack != null) {
			// calls callback
			LegendItem[] result = labelsCallBack.generateLegendLabels(getChart());
			// if result is null..
			if (result == null) {
				// .. returns a empty array.
				return new LegendItem[0];
			}
			// returns the generated array of legend items.
			return result;
		}
		// returns a empty array
		return new LegendItem[0];
	}

	/**
	 * Called to filter legend items out of the legend. Receives 1 parameter, a Legend Item.
	 * @param item legend item to check.
	 * @return <code>true</code> to maintain the item, otherwise <code>false</code> to hide it.
	 */
	protected boolean onFilter(LegendItem item) {
		// checks if callback is consistent
		if (filterHandler != null) {
			// calls callback
			return filterHandler.onFilter(getChart(), item);
		}
		return true;
	}
	
	/**
	 * Sets the java script code to activate the call back, adding functions.
	 * 
	 * @param options
	 *            java script object where adding new functions definition.
	 */
    private native void registerNativeLegendLabelsHandler(GenericJavaScriptObject options)/*-{
    	var self = this;
	    options.generateLabels = function(chart){
	    	// calls the default generateLabels function
	    	// to get the default.
	    	var labels = $wnd.Chart.defaults.global.legend.labels.generateLabels(chart);
	        var newLabels = self.@org.pepstock.charba.client.options.LegendLabels::generateLegendLabels()();
	        // checke if array is empty
	        // if empty, returns the default labels.
	        if (newLabels.length == 0){
	        	return labels;
	        } else {
	        	return newLabels;
	        }
	    }
	}-*/;

	/**
	 * Sets the java script code to activate the call back, adding functions.
	 * 
	 * @param options
	 *            java script object where adding new functions definition.
	 */
    private native void registerNativeFilterLabelsHandler(GenericJavaScriptObject options)/*-{
		var self = this;
	    options.filter = function(legendItem){
	    	return self.@org.pepstock.charba.client.options.LegendLabels::onFilter(Lorg/pepstock/charba/client/items/LegendItem;)(legendItem);
	    }
	}-*/;

}