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
package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.callbacks.LegendFilterCallback;
import org.pepstock.charba.client.jsinterop.callbacks.LegendFilterHandler;
import org.pepstock.charba.client.jsinterop.callbacks.LegendLabelsCallback;
import org.pepstock.charba.client.jsinterop.callbacks.LegendLabelsHandler;
import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.commons.Checker;
import org.pepstock.charba.client.jsinterop.commons.JsFactory;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultLegendLabels;
import org.pepstock.charba.client.jsinterop.items.LegendItem;
import org.pepstock.charba.client.jsinterop.items.LegendLabelItem;

import jsinterop.annotations.JsFunction;

/**
 * This is the labels configuration of the legend.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class LegendLabels extends FontItem<Legend, IsDefaultLegendLabels, NativeLegendLabels> {
	
	/**
	 * Called to generate legend items for each thing in the legend. Default implementation returns the text + styling for the color box.
	 * 
	 * @return array of legend items.
	 */
	@JsFunction
	interface ProxyGenerateLabelsCallback {
		LegendLabelItem[] call(Object context, Object chart);
	}

	/**
	 * Called to filter legend items out of the legend. Receives 1 parameter, a Legend Item.
	 * 
	 * @param item legend item to check.
	 * @return <code>true</code> to maintain the item, otherwise <code>false</code> to hide it.
	 */
	@JsFunction
	interface ProxyFilterCallback {
		boolean call(Object context, LegendItem item);
	}
	
	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		generateLabels,
		filter
	}
	
	private final CallbackProxy<ProxyGenerateLabelsCallback> labelsCallbackProxy = JsFactory.newCallbackProxy();

	private final CallbackProxy<ProxyFilterCallback> filterCallbackProxy = JsFactory.newCallbackProxy();
	
	private LegendFilterHandler filterHandler = null;
	
	private LegendFilterCallback filterCallback = null;
	
	private LegendLabelsHandler labelsHandler = null;
	
	private LegendLabelsCallback labelsCallback = null;
	
	LegendLabels(Legend legend, IsDefaultLegendLabels defaultValues, NativeLegendLabels delegated) {
		super(legend, defaultValues, delegated == null ? new NativeLegendLabels() : delegated);
		
		filterCallbackProxy.setCallback(new ProxyFilterCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.LegendLabels.ProxyFilterCallback#call(java.lang.Object, org.pepstock.charba.client.jsinterop.items.LegendItem)
			 */
			@Override
			public boolean call(Object context, LegendItem item) {
				return filterHandler != null ? filterHandler.onFilter(context, item) : true;
			}
			
		});
		
		labelsCallbackProxy.setCallback(new ProxyGenerateLabelsCallback() {

			/* (non-Javadoc)
			 * @see org.pepstock.charba.client.jsinterop.options.LegendLabels.ProxyGenerateLabelsCallback#call(java.lang.Object, java.lang.Object)
			 */
			@Override
			public LegendLabelItem[] call(Object context, Object chart) {
				return labelsHandler != null ? labelsHandler.generateLegendLabels(context, chart) : new LegendLabelItem[0];
			}
			
		});
	}

	/**
	 * Sets if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case).
	 * 
	 * @param usePointStyle if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case).
	 */
	public void setUsePointStyle(boolean usePointStyle) {
		getDelegated().setUsePointStyle(usePointStyle);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case).
	 * 
	 * @return if label style will match corresponding point style (size is based on fontSize, boxWidth is not used in this case). Default is false.
	 */
	public boolean isUsePointStyle() {
		return Checker.check(getDelegated().isUsePointStyle(), getDefaultValues().isUsePointStyle());
	}

	/**
	 * Sets the width of coloured box.
	 * 
	 * @param boxWidth width of coloured box.
	 */
	public void setBoxWidth(int boxWidth) {
		getDelegated().setBoxWidth(boxWidth);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the width of coloured box.
	 * 
	 * @return width of coloured box. Default is 40.
	 */
	public int getBoxWidth() {
		return Checker.check(getDelegated().getBoxWidth(), getDefaultValues().getBoxWidth());
	}

	/**
	 * Sets the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @param padding Padding to apply around labels. Only top and bottom are implemented.
	 */
	public void setPadding(int padding) {
		getDelegated().setPadding(padding);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @return Padding to apply around labels. Only top and bottom are implemented. Default is 10.
	 */
	public int getPadding() {
		return Checker.check(getDelegated().getPadding(), getDefaultValues().getPadding());
	}

	// -------------------
	// LABELS
	// -------------------
	/**
	 * @return the labelsCallback
	 */
	public LegendLabelsCallback getLabelsCallback() {
		return labelsCallback;
	}

	/**
	 * @param labelsCallback the labelsCallback to set
	 */
	public void setLabelsCallback(LegendLabelsCallback labelsCallback) {
		this.labelsCallback = labelsCallback;
	}
	
	/**
	 * @return the labelsHandler
	 */
	LegendLabelsHandler getLabelsHandler() {
		return labelsHandler;
	}

	/**
	 * @param labelsHandler the labelsHandler to set
	 */
	void setLabelsHandler(LegendLabelsHandler labelsHandler) {
		if (labelsHandler != null) {
			getDelegated().setFilter(labelsCallbackProxy.getProxy());
			// checks if the node is already added to parent
			checkAndAddToParent();
		} else {
			remove(Property.generateLabels);
		}
		this.labelsHandler = labelsHandler;
	}

	// -------------------
	// FILTER
	// -------------------
	/**
	 * @return the filterHandler
	 */
	LegendFilterHandler getFilterHandler() {
		return filterHandler;
	}

	/**
	 * @param filterHandler the filterHandler to set
	 */
	void setFilterHandler(LegendFilterHandler filterHandler) {
		if (filterHandler != null) {
			getDelegated().setFilter(filterCallbackProxy.getProxy());
			// checks if the node is already added to parent
			checkAndAddToParent();
		} else {
			remove(Property.filter);
		}
		this.filterHandler = filterHandler;
	}

	/**
	 * @return the filterCallback
	 */
	public LegendFilterCallback getFilterCallback() {
		return filterCallback;
	}

	/**
	 * @param filterCallback the filterCallback to set
	 */
	public void setFilterCallback(LegendFilterCallback filterCallback) {
		this.filterCallback = filterCallback;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.BaseModel#addToParent()
	 */
	@Override
	protected void addToParent() {
		if (getParent().getDelegated().getLabels()== null) {
			getParent().getDelegated().setLabels(getDelegated());
		}
	}
}