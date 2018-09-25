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
import org.pepstock.charba.client.jsinterop.commons.AssignHelper;
import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
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
	public interface GenerateLabelsCallback {

		LegendLabelItem[] call(Object context, Object chart);
	}

	/**
	 * Called to filter legend items out of the legend. Receives 1 parameter, a Legend Item.
	 * 
	 * @param item legend item to check.
	 * @return <code>true</code> to maintain the item, otherwise <code>false</code> to hide it.
	 */
	@JsFunction
	public interface FilterCallback {

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
	
	private final CallbackProxy<GenerateLabelsCallback> generateLabelsCallbackProxy = JsFactory.newCallbackProxy();

	private final CallbackProxy<FilterCallback> filterCallbackProxy = JsFactory.newCallbackProxy();
	
	LegendLabels(Legend legend, IsDefaultLegendLabels defaultValues, NativeLegendLabels delegated) {
		super(legend, defaultValues, delegated == null ? new NativeLegendLabels() : delegated);
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
		return AssignHelper.check(getDelegated().isUsePointStyle(), getDefaultValues().isUsePointStyle());
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
		return AssignHelper.check(getDelegated().getBoxWidth(), getDefaultValues().getBoxWidth());
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
		return AssignHelper.check(getDelegated().getPadding(), getDefaultValues().getPadding());
	}
	
	/**
	 * @param legendCallBack the legendCallBack to set
	 */
	public void setGenerateLabelsCallback(GenerateLabelsCallback legendCallBack) {
		if (legendCallBack != null) {
			generateLabelsCallbackProxy.setCallback(legendCallBack);
			getDelegated().setGenerateLabels(generateLabelsCallbackProxy.getProxy());
			// checks if the node is already added to parent
			checkAndAddToParent();
		} else {
			remove(Property.generateLabels);
		}
	}

	/**
	 * @param filterCallBack the legendCallBack to set
	 */
	public void setFilterCallback(FilterCallback filterCallBack) {
		if (filterCallBack != null) {
			filterCallbackProxy.setCallback(filterCallBack);
			getDelegated().setFilter(filterCallbackProxy.getProxy());
			// checks if the node is already added to parent
			checkAndAddToParent();
		} else {
			remove(Property.filter);
		}
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