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

import org.pepstock.charba.client.commons.Key;

/**
 * Contains all info for every item of tooltip.<br>
 * Created and passed by CHART.JS.<br>
 * It uses into the tooltips callbacks.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.callbacks.TooltipLabelCallback
 * @see org.pepstock.charba.client.callbacks.TooltipBodyCallback
 * @see org.pepstock.charba.client.callbacks.TooltipItemSortHandler
 * @see org.pepstock.charba.client.callbacks.TooltipFilterHandler
 * @see org.pepstock.charba.client.callbacks.TooltipFooterCallback
 * @see org.pepstock.charba.client.callbacks.TooltipTitleCallback
 *
 */
public final class TooltipItem extends BaseItem {

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		xLabel,
		yLabel,
		datasetIndex,
		index,
		x,
		y
	}

	/**
	 * Needed for GWt injection
	 */
	protected TooltipItem() {
		// do nothings
	}

	/**
	 * Returns the X location of label.
	 * 
	 * @return the X location of label.
	 */
	public final String getXLabel() {
		return getString(Property.xLabel.name());
	}

	/**
	 * Returns the Y location of label.
	 * 
	 * @return the Y location of label.
	 */
	public final String getYLabel() {
		return getString(Property.yLabel.name());
	}

	/**
	 * Returns the dataset index of the chart
	 * 
	 * @return the dataset index of the chart
	 * @see org.pepstock.charba.client.data.Data#getDatasets()
	 */
	public final int getDatasetIndex() {
		return getInt(Property.datasetIndex.name());
	}

	/**
	 * Returns the index of the data inside the dataset.
	 * 
	 * @return the index of the data inside the dataset.
	 * @see org.pepstock.charba.client.data.Dataset#getData()
	 * @see org.pepstock.charba.client.data.Data#getLabels()
	 */
	public final int getIndex() {
		return getInt(Property.index.name());
	}

	/**
	 * Returns the X location of tooltip item.
	 * 
	 * @return the X location of tooltip item.
	 */
	public final int getX() {
		return getInt(Property.x.name());
	}

	/**
	 * Returns the Y location of tooltip item.
	 * 
	 * @return the Y location of tooltip item.
	 */
	public final int getY() {
		return getInt(Property.y.name());
	}
}