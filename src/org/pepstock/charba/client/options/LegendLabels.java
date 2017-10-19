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

public final class LegendLabels extends AbstractLabel {
	
	private static final int DEFAULT_BOX_WIDTH = 40;
	
	private static final boolean DEFAULT_USE_POINT_STYLE = false;
	
	private LegendLabelsCallback labelsCallBack = null;
	
	private LegendFilterHandler filterHandler = null;
	
	private enum Property implements Key{
		boxWidth,
		usePointStyle,
		generateLabels,
		filter
	}
	
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
	 * @param legendLabelsCallBack the legendCallBack to set
	 */
	public void setLabelsCallBack(LegendLabelsCallback legendLabelsCallBack) {
		if (hasToBeRegistered( legendLabelsCallBack, Property.generateLabels)){
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
	 * @param legendFilterHandler the legendFilterHandler to set
	 */
	public void setLegendFilterHandler(LegendFilterHandler filterHandler) {
		if (hasToBeRegistered(filterHandler, Property.filter)){
			registerNativeFilterLabelsHandler(getJavaScriptObject());
		}
		this.filterHandler = filterHandler;
	}

	public void setUsePointStyle(boolean usePointStyle) {
		setValue(Property.usePointStyle, usePointStyle);
	}

    public boolean isUsePointStyle(){
    	return getValue(Property.usePointStyle, DEFAULT_USE_POINT_STYLE);
    }

	public void setBoxWidth(int boxWidth) {
		setValue(Property.boxWidth, boxWidth);
	}

    public int getBoxWidth(){
    	return getValue(Property.boxWidth, DEFAULT_BOX_WIDTH);
    }

	protected LegendItem[] generateLegendLabels() {
		if (labelsCallBack != null){
			LegendItem[] result = labelsCallBack.generateLegendLabels(getChart());
			if (result == null){
				return new LegendItem[0];
			}
			return result;
		}
		return new LegendItem[0];
	}

	protected boolean onFilter(LegendItem item){
		if (filterHandler != null){
			return filterHandler.onFilter(getChart(), item);
		}
		return true;
	}

	
    private native void registerNativeLegendLabelsHandler(GenericJavaScriptObject options)/*-{
    	var self = this;
	    options.generateLabels = function(chart){
	    	var labels = $wnd.Chart.defaults.global.legend.labels.generateLabels(chart);
	        var newLabels = self.@org.pepstock.charba.client.options.LegendLabels::generateLegendLabels()();
	        if (newLabels.length == 0){
	        	return labels;
	        } else {
	        	return newLabels;
	        }
	    }
	}-*/;

    private native void registerNativeFilterLabelsHandler(GenericJavaScriptObject options)/*-{
		var self = this;
	    options.filter = function(legendItem){
	    	return self.@org.pepstock.charba.client.options.LegendLabels::onFilter(Lorg/pepstock/charba/client/items/LegendItem;)(legendItem);
	    }
	}-*/;

}