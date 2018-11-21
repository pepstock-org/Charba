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
package org.pepstock.charba.client.jsinterop.items;

import org.pepstock.charba.client.jsinterop.commons.Checker;
import org.pepstock.charba.client.jsinterop.commons.NativeName;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

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
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name=NativeName.OBJECT)
public final class TooltipItem extends NativeObject {
	
	@JsProperty(name = "xLabel")
	native String getNativeXLabel();
	
	@JsProperty(name = "yLabel")
	native String getNativeYLabel();
	
	@JsProperty(name = "datasetIndex")
	native int getNativeDatasetIndex();
	
	@JsProperty(name = "index")
	native int getNativeIndex();
	
	@JsProperty(name = "x")
	native int getNativeX();
	
	@JsProperty(name = "y")
	native int getNativeY();

	/**
	 * Returns the X location of label.
	 * 
	 * @return the X location of label. Default is {@link org.pepstock.charba.client.items.UndefinedValues#STRING}.
	 */
	@JsOverlay
	public String getXLabel() {
		return Checker.check(getNativeXLabel(), UndefinedValues.STRING);
	}

	/**
	 * Returns the Y location of label.
	 * 
	 * @return the Y location of label. Default is {@link org.pepstock.charba.client.items.UndefinedValues#STRING}.
	 */
	@JsOverlay
	public String getYLabel() {
		return Checker.check(getNativeYLabel(), UndefinedValues.STRING);
	}

	/**
	 * Returns the dataset index of the chart
	 * 
	 * @return the dataset index of the chart. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 * @see org.pepstock.charba.client.data.Data#getDatasets()
	 */
	@JsOverlay
	public int getDatasetIndex() {
		return Checker.check(getNativeDatasetIndex(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the index of the data inside the dataset.
	 * 
	 * @return the index of the data inside the dataset. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 * @see org.pepstock.charba.client.data.Dataset#getData()
	 * @see org.pepstock.charba.client.data.Data#getLabels()
	 */
	@JsOverlay
	public int getIndex() {
		return Checker.check(getNativeIndex(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the X location of tooltip item.
	 * 
	 * @return the X location of tooltip item. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public int getX() {
		return Checker.check(getNativeX(), UndefinedValues.INTEGER);
	}

	/**
	 * Returns the Y location of tooltip item.
	 * 
	 * @return the Y location of tooltip item. Default is {@link org.pepstock.charba.client.items.UndefinedValues#INTEGER}.
	 */
	@JsOverlay
	public int getY() {
		return Checker.check(getNativeY(), UndefinedValues.INTEGER);
	}
}